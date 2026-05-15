package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.soc.ewok.model.Preu;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

/**
 * DAO per a la classe {@link Preu}
 * @author Xavier
 *
 */
public class PreuDAO {
	/** Query per esborrar un registre per id */
	private static final String SQL_ESBORRAR =
			"DELETE FROM " + ConstantsSQL.PREU_TAULA + 
			" WHERE " + ConstantsSQL.PREU_CAMP_ID + " = ?";
	
	/** Query per donar d'alta un preu amb tots els seus camps */
	private static final String SQL_ALTA = 
			"INSERT INTO " + ConstantsSQL.PREU_TAULA + 
			"(" + ConstantsSQL.PREU_CAMP_PREU + ", " + 
			      ConstantsSQL.PREU_CAMP_INICIVIGENCIA + ", " +
			      ConstantsSQL.PREU_CAMP_FINALVIGENCIA + ", " + 
			      ConstantsSQL.PREU_CAMP_IDPRODUCTE + ")" + 
			" VALUES (?, ?, ?, ?)";
	
	/** Query per modificar tots els camps d'un preu per id */
	private static final String SQL_MODIFICAR = 
			"UPDATE " + ConstantsSQL.PREU_TAULA + 
			" SET " + ConstantsSQL.PREU_CAMP_PREU + " = ?, " +
			  		  ConstantsSQL.PREU_CAMP_INICIVIGENCIA + " = ?, " +
			  		  ConstantsSQL.PREU_CAMP_FINALVIGENCIA + " = ?, " +
			  		  ConstantsSQL.PREU_CAMP_IDPRODUCTE + " = ? " +
			" WHERE " + ConstantsSQL.PREU_CAMP_ID + " = ?";
	
	/** Query per obtenir tots els preus de base de dades */
	private static final String SQL_SELECT_ALL = 
			"SELECT *" +						
			" FROM " + ConstantsSQL.PREU_TAULA +
			" ORDER BY " + ConstantsSQL.PREU_CAMP_PREU;
	
	/** Query per obtenir un preu per id */
	private static final String SQL_SELECT_BY_ID = 
			"SELECT *" + 	 
			" FROM " + ConstantsSQL.PREU_TAULA +
			" WHERE " + ConstantsSQL.PREU_CAMP_ID + " = ?";

	/** Query per obtenir un preu VIGENT PER idProducte  */
	private static final String SQL_SELECT_VIGENT_BY_IDPROD = 
			"SELECT *" + 	 
			" FROM " + ConstantsSQL.PREU_TAULA +
			" WHERE " + ConstantsSQL.PREU_CAMP_IDPRODUCTE + " = ?" + 
			" AND " + ConstantsSQL.PREU_CAMP_INICIVIGENCIA + " <= ? " +
			" AND " + ConstantsSQL.PREU_CAMP_FINALVIGENCIA + " >= ?";

	DBWrapper dw;
	
	/**
	 * Constructor de la classe
	 * @param ds DataSource a usar per connectar-se a base de dades
	 */
	public PreuDAO(DataSource ds) {
		dw = new DBWrapper(ds);
	}
	
	/**
	 * Dóna d'alta un registre a base de dades.
	 * Actualitzarà l'id amb el valor generat pel gestor
	 * @param p Preu a donar d'alta
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void alta(final Preu p) throws SQLException {
		dw.executeSql(
			SQL_ALTA, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setFloat(1, p.getPreu());
					st.setTimestamp(2, DBWrapper.getParameterFromDate(p.getIniciVigencia()));
					st.setTimestamp(3, DBWrapper.getParameterFromDate(p.getFinalVigencia()));
					st.setLong(4, p.getIdProducte());	
				}
			},
			new GenericExecuteQueryProcess() {		
				@Override
				public void doPostProcess(Connection con, PreparedStatement st)
						throws SQLException {
					// Uso el recordset de camps generats
					// que em dona l'statement per assignar
					// l'id al Preu original
					ResultSet keys = st.getGeneratedKeys();
					keys.next();
					p.setId(keys.getLong(1));
				}
			}
		);
	}
	
	/**
	 * Esborra un preu de base de dades
	 * @param id Indentificador del preu a esborrar
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
	 * Modifica un preu a base da dades
	 * @param p El preu a modificar. Usarà l'id
	 * per buscar el registre i actualitzarà tots els seus camps
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public void modificar(final Preu p) throws SQLException {
		dw.executeSql(
			SQL_MODIFICAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setFloat(1, p.getPreu());
					st.setTimestamp(2, DBWrapper.getParameterFromDate(p.getIniciVigencia()));
					st.setTimestamp(3, DBWrapper.getParameterFromDate(p.getFinalVigencia()));
					st.setLong(4, p.getIdProducte());	
					st.setLong(5, p.getId());	
				}
			}
		);	
	}
	
	/**
	 * Recupera un preu per id
	 * @param id del preu a recuperar
	 * @return El preu si es troba, o null si no existeix
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public Preu obtenirPerId(final long id) throws SQLException {
		final Preu elPreu = new Preu();
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
					crearPreu(rset, elPreu);
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return false;
				}
			}
		);
		return elPreu.getId() == null ? null : elPreu;
	}
	
	/**
	 * Recupera de base de dades tots els preus ordenats per nom
	 * @return La llista de preus. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public List<Preu> obtenirTots() throws SQLException {
		final List<Preu> elsPreus = new Vector<Preu>();
		dw.executeQuery(
			SQL_SELECT_ALL, 
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					elsPreus.add(crearPreu(rset));
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return true;
				}
			}
		);
		return elsPreus;
	}
	/**
	 * Recupera un preu vigent per id
	 * @param id del preu a recuperar
	 * @return El preu si es troba, o null si no existeix
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 * @author JordiM
	 */
	public Preu obtenirVigentPerId(final long id) throws SQLException {
		final Preu elPreu = new Preu();
		dw.executeQuery(
			SQL_SELECT_VIGENT_BY_IDPROD, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1, id);
					st.setTimestamp(2,
							DBWrapper.getParameterFromDate(new Date()));
					st.setTimestamp(3,
							DBWrapper.getParameterFromDate(new Date()));
				}
			},
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a l'objecte que retornaré
					crearPreu(rset, elPreu);
					// Indico al DBWrapper que segueixi amb el 
					// següent registre. si hi ha més d'un registre vàlid agafarem l'últim
					return true;
				}
			}
		);
		return elPreu.getId() == null ? null : elPreu;
	}
	
	/**
	 * Funció d'utilitat per crear un objecte Preu a partir de la fila actual
	 * d'un recordset 
	 * @param rset El recordset d'on treurem el Preu
	 * @return El Preu creat
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	private Preu crearPreu(ResultSet rset) throws SQLException {
		return crearPreu(rset, null);
	}
	
	/**
	 * Funció d'utilitat per omplir un objecte Preu amb els valors
	 * de la fila actual d'un recordset 
	 * @param rset El recordset d'on treurem el Preu
	 * @param p El Preu a omplir. Pot ser null i es crearà un Preu nou
	 * @return El Preu amb els camps recuperats
	 * @throws SQLExceptionEl recordset d'on treurem el Preu
	 */
	private Preu crearPreu(ResultSet rset, Preu p) throws SQLException {
		// Si no em passen preu, el creo
		if (p == null) {
			p = new Preu();
		}
		// Omplo el preu desde el recordset
		p.setId(rset.getLong(ConstantsSQL.PREU_CAMP_ID));
		p.setPreu(rset.getFloat(ConstantsSQL.PREU_CAMP_PREU));
		p.setIniciVigencia(rset.getTimestamp(ConstantsSQL.PREU_CAMP_INICIVIGENCIA));
		p.setFinalVigencia(rset.getTimestamp(ConstantsSQL.PREU_CAMP_FINALVIGENCIA));
		p.setIdProducte(rset.getLong(ConstantsSQL.PREU_CAMP_IDPRODUCTE));
		// Retorno l'opbjecte recuperat
		return p;
	}
}
