package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.soc.ewok.model.FormaPagament;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

/**
 * DAO per a la classe {@link FormaPagamnet}
 * @author CarlosM
 *
 */
public class FormaPagamentDAO {
	/** Query per esborrar un registre per id */
	private static final String SQL_ESBORRAR =
			"DELETE FROM " + ConstantsSQL.FORMAPAGAMENTS_TAULA + 
			" WHERE " + ConstantsSQL.FORMAPAGAMENTS_CAMP_ID + " = ?";
	
	/** Query per donar d'alta un rol amb tots els seus camps */
	private static final String SQL_ALTA = 
			"INSERT INTO " + ConstantsSQL.FORMAPAGAMENTS_TAULA + 
			"(" + ConstantsSQL.FORMAPAGAMENTS_CAMP_NOM + ")" + 
			" VALUES (?)";
	
	/** Query per modificar tots els camps d'un rol per id */
	private static final String SQL_MODIFICAR = 
			"UPDATE " + ConstantsSQL.FORMAPAGAMENTS_TAULA + 
			" SET " + ConstantsSQL.FORMAPAGAMENTS_CAMP_NOM + " = ? " +
			" WHERE " + ConstantsSQL.FORMAPAGAMENTS_CAMP_ID + " = ?";

	/** Query per obtenir tots els rols de base de dades */
	private static final String SQL_SELECT_ALL = 
			"SELECT " + ConstantsSQL.FORMAPAGAMENTS_CAMP_ID + ", " + ConstantsSQL.ROL_CAMP_NOM +
			" FROM " + ConstantsSQL.FORMAPAGAMENTS_TAULA +
			" ORDER BY " + ConstantsSQL.FORMAPAGAMENTS_CAMP_NOM;
	
	/** Query per obtenir un rol per id */
	private static final String SQL_SELECT_BY_ID = 
			"SELECT " + ConstantsSQL.FORMAPAGAMENTS_CAMP_ID + ", " + ConstantsSQL.ROL_CAMP_NOM +
			" FROM " + ConstantsSQL.FORMAPAGAMENTS_TAULA +
			" WHERE " + ConstantsSQL.FORMAPAGAMENTS_CAMP_ID + " = ?";

	DBWrapper dw;

	/**
	 * Constructor de la classe 
	 * @param ds DataSource a usar per connectar-se a base de dades
	 */
	public FormaPagamentDAO(DataSource ds) {
		dw = new DBWrapper(ds);
	}

	/**
	 * Dóna d'alta un registre a base de dades.
	 * Actualitzarà l'id amb el valor generat pel gestor
	 * @param fp FormaPagament a donar d'alta
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void alta(final FormaPagament fp) throws SQLException {
		dw.executeSql(
			SQL_ALTA, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setString(1, fp.getsNom());
				}
			},
			new GenericExecuteQueryProcess() {		
				@Override
				public void doPostProcess(Connection con, PreparedStatement st)
						throws SQLException {
					// Uso el recordset de camps generats
					// que em dona l'statement per assignar
					// l'id al Rol original
					ResultSet keys = st.getGeneratedKeys();
					keys.next();
					fp.setnId(keys.getLong(1));
				}
			}
		);
	}

	/**
	 * Esborra una FormaPagament de base de dades
	 * @param id Identificador de la FormaPagament a esborrar 
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public void esborrar(final long id) throws SQLException {
		dw.executeSql(
			SQL_ESBORRAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1, id);
				}
			}
		);	
	}

	/** 
	 * Modifica una FormaPagament a base de dades
	 * @param fp la FormaPagament a modificar. Usarà l'id
	 * per buscar el registre i actualitzarà tots els seus camps
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public void modificar(final FormaPagament fp) throws SQLException {
		dw.executeSql(
			SQL_MODIFICAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setString(1, fp.getsNom());
					st.setLong(2, fp.getnId());
				}
			}
		);	
	}

	/**
	 * Recupera una FormaPagament per id
	 * @param id de la FormaPagament a recuperar
	 * @return La FormaPagament si es troba, o null si no existeix
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public FormaPagament obtenirPerId(final long id) throws SQLException {
		final FormaPagament laFormaPagament = new FormaPagament();
		dw.executeQuery(
			SQL_SELECT_BY_ID, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1, id);
				}
			},
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a l'objecte que retornaré
					crearFormaPagament(rset, laFormaPagament);
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return false;
				}
			}
		);
		return laFormaPagament.getnId() == null ? null : laFormaPagament;
	}

	/**
	 * Recupera de base de dades tots les FormaPagament ordenats per nom
	 * @return La llista de FormaPagament. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public List<FormaPagament> obtenirTots() throws SQLException {
		final List<FormaPagament> lesFormaPagament = new Vector<FormaPagament>();
		dw.executeQuery(
			SQL_SELECT_ALL, 
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					lesFormaPagament.add(crearFormaPagament(rset));
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return true;
				}
			}
		);
		return lesFormaPagament;
	}

	/**
	 * Funció d'utilitat per crear un objecte FormaPagament a partir de la fila actual
	 * d'un recordset 
	 * @param rset El recordset d'on treurem la FormaPagament
	 * @return La FormaPagament creada
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	protected FormaPagament crearFormaPagament(ResultSet rset) throws SQLException {
		return crearFormaPagament(rset, null);
	}

	/**
	 * Funció d'utilitat per omplir un objecte Rol amb els valors
	 * de la fila actual d'un recordset
	 * @param rset El recordset d'on treurem el Rol
	 * @param r El Rol a omplir. Pot ser null i es crearà un Rol nou
	 * @return El Rol amb els camps recuperats
	 * @throws SQLException El recordset d'on treurem el Rol
	 */
	protected FormaPagament crearFormaPagament(ResultSet rset, FormaPagament fp) throws SQLException {
		// Si no em passen rol, el creo
		if (fp == null) {
			fp = new FormaPagament();
		}
		// Omplo el rol desde el recordset
		fp.setnId(rset.getLong(ConstantsSQL.FORMAPAGAMENTS_CAMP_ID));
		fp.setsNom(rset.getString(ConstantsSQL.FORMAPAGAMENTS_CAMP_NOM));
		// Retorno l'opbjecte recuperat
		return fp;
	}
}



