package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.soc.ewok.model.TipusProducte;

import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

public class TipusProducteDAO {
	
	/** Query per esborrar un registre per id */
	private static final String SQL_ESBORRAR =
			"DELETE FROM " + ConstantsSQL.TIPUSPRODUCTE_TAULA + 
			" WHERE " + ConstantsSQL.TIPUSPRODUCTE_CAMP_ID + " = ?";
	
	/** Query per donar d'alta d'un tipu de producte amb tots els seus camps */
	private static final String SQL_ALTA = 
			"INSERT INTO " + ConstantsSQL.TIPUSPRODUCTE_TAULA + 
			"(" + ConstantsSQL.TIPUSPRODUCTE_CAMP_NOM + ", "
			+ ConstantsSQL.TIPUSPRODUCTE_CAMP_CODI + ")" +
			" VALUES (?, ?)";
	
	/** Query per modificar tots els camps d'un tipo de producte per id */
	private static final String SQL_MODIFICAR = 
			"UPDATE " + ConstantsSQL.TIPUSPRODUCTE_TAULA + 
			" SET " + ConstantsSQL.TIPUSPRODUCTE_CAMP_NOM + " = ? " +
			", " + ConstantsSQL.TIPUSPRODUCTE_CAMP_CODI + " = ? " +
			" WHERE " + ConstantsSQL.TIPUSPRODUCTE_CAMP_ID + " = ?";

	/** Query per obtenir tots els tipus de productes de base de dades */
	private static final String SQL_SELECT_ALL = 
			"SELECT * " +  
			" FROM " + ConstantsSQL.TIPUSPRODUCTE_TAULA +
			" ORDER BY " + ConstantsSQL.TIPUSPRODUCTE_CAMP_NOM;
	
	/** Query per obtenir un tipu de producte per id */
	private static final String SQL_SELECT_BY_ID = 
			"SELECT * " +
			" FROM " + ConstantsSQL.TIPUSPRODUCTE_TAULA +
			" WHERE " + ConstantsSQL.TIPUSPRODUCTE_CAMP_ID + " = ?";

	DBWrapper dw;
	
	
	/**
	 * Constructor de la classe 
	 * @param ds DataSource a usar per connectar-se a base de dades
	 */
	public TipusProducteDAO(DataSource ds) {
		dw = new DBWrapper(ds);
	}

	/**
	 * Dóna d'alta un registre a base de dades.
	 * Actualitzarà l'id amb el valor generat pel gestor
	 * @param tp TipusPrroducte a donar d'alta
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void alta(final TipusProducte tp) throws SQLException {
		dw.executeSql(
			SQL_ALTA, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setString(1, tp.getNom());
					st.setString(2, tp.getCodi());
					
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
					tp.setId(keys.getLong(1));
				}
			}
		);
	}

	/**
	 * Esborra un Tipu de Producte de base de dades
	 * @param id Identificador del Tipu de Producte a esborrar 
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
	 * Modifica un Tipu de producte a base de dades
	 * @param tp El TipusProducte a modificar. Usarà l'id
	 * per buscar el registre i actualitzarà tots els seus camps
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public void modificar(final TipusProducte tp) throws SQLException {
		dw.executeSql(
			SQL_MODIFICAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setString(1, tp.getNom());						
					st.setString(2, tp.getCodi());
					st.setLong(3, tp.getId());
				}
			}
		);	
	}

	/**
	 * Recupera un Tipus de Producte per id
	 * @param id del Tipus de Producte a recuperar
	 * @return El Tipus de Producte si es troba, o null si no existeis
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public TipusProducte obtenirPerId(final long id) throws SQLException {
		final TipusProducte elTipo = new TipusProducte();
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
						ResultSet tpset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a l'objecte que retornaré
					crearTipusProducte(tpset, elTipo);
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return false;
				}
			}
		);
		return elTipo.getId() == null ? null : elTipo;
	}

	/**
	 * Recupera de base de dades tots els tipus de producte ordenats per nom
	 * @return La llista de Tipus de Producte. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public List<TipusProducte> obtenirTots() throws SQLException {
		final List<TipusProducte> elTipo = new Vector<TipusProducte>();
		dw.executeQuery(
			SQL_SELECT_ALL, 
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet tpset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					elTipo.add(crearTipusProducte(tpset));
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return true;
				}
			}
		);
		return elTipo;
	}

	/**
	 * Funció d'utilitat per crear un objecte TipusProducte a partir de la fila actual
	 * d'un recordset 
	 * @param tpset El recordset d'on treurem el TipusProducte
	 * @return el TipusProducte creat
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	protected TipusProducte crearTipusProducte(ResultSet tpset) throws SQLException {
		return crearTipusProducte(tpset, null);
	}

	/**
	 * Funció d'utilitat per omplir un objecte TipusProducte amb els valors
	 * de la fila actual d'un recordset
	 * @param tpset El recordset d'on treurem el TipusProducte
	 * @param tp El TipusProducte a omplir. Pot ser null i es crearà un TipusProducte nou
	 * @return El TipusProducte amb els camps recuperats
	 * @throws SQLException El recordset d'on treurem la Unitat
	 */
	protected TipusProducte crearTipusProducte(ResultSet tpset, TipusProducte tp) throws SQLException {
		// Si no em passen rol, el creo
		if (tp == null) {
			tp = new TipusProducte();
		}
		// Omplo el rol desde el recordset
		tp.setId(tpset.getLong(ConstantsSQL.TIPUSPRODUCTE_CAMP_ID));
		tp.setNom(tpset.getString(ConstantsSQL.TIPUSPRODUCTE_CAMP_NOM));
		tp.setCodi(tpset.getString(ConstantsSQL.TIPUSPRODUCTE_CAMP_CODI));
		// Retorno l'opbjecte recuperat
		return tp;
	}


}
