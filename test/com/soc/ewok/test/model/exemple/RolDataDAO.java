package com.soc.ewok.test.model.exemple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.soc.ewok.model.Rol;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

/**
 * DAO per a la classe {@link Rol}
 * @author Administrador
 *
 */
public class RolDataDAO {
	// Constants de taula ROL
	static final String ROLDATA_TAULA = "rolData";
	static final String ROLDATA_CAMP_ID = "Id";
	static final String ROLDATA_CAMP_NOM = "Nom";
	static final String ROLDATA_CAMP_DATA_CREACIO = "DataCreacio";

	/** Query per esborrar un registre per id */
	private static final String SQL_ESBORRAR =
			"DELETE FROM " + ROLDATA_TAULA + 
			" WHERE " + ROLDATA_CAMP_ID + " = ?";
	
	/** Query per donar d'alta un rol amb tots els seus camps */
	private static final String SQL_ALTA = 
			"INSERT INTO " + ROLDATA_TAULA + 
			"(" + ROLDATA_CAMP_NOM + ", " + ROLDATA_CAMP_DATA_CREACIO + ")" + 
			" VALUES (?, ?)";
	
	/** Query per modificar tots els camps d'un rol per id */
	private static final String SQL_MODIFICAR = 
			"UPDATE " + ROLDATA_TAULA + 
			" SET " + ROLDATA_CAMP_NOM + " = ? " + 
			", " + ROLDATA_CAMP_DATA_CREACIO + " = ? " +
			"WHERE " + ROLDATA_CAMP_ID + " = ?";

	/** Query per obtenir tots els rols de base de dades */
	private static final String SQL_SELECT_ALL = 
			"SELECT * " +
			"FROM " + ROLDATA_TAULA +
			" ORDER BY " + ROLDATA_CAMP_NOM;
	
	/** Query per obtenir un rol per id */
	private static final String SQL_SELECT_BY_ID = 
			"SELECT * " +
			"FROM " + ROLDATA_TAULA +
			" WHERE " + ROLDATA_CAMP_ID + " = ?";

	DBWrapper dw;

	/**
	 * Constructor de la classe 
	 * @param ds DataSource a usar per connectar-se a base de dades
	 */
	public RolDataDAO(DataSource ds) {
		dw = new DBWrapper(ds);
	}

	/**
	 * Dóna d'alta un registre a base de dades.
	 * Actualitzarà l'id amb el valor generat pel gestor
	 * @param r Rol a donar d'alta
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void alta(final RolData r) throws SQLException {
		dw.executeSql(
			SQL_ALTA, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setString(1, r.getNom());
					st.setTimestamp(
						2, 
						DBWrapper.getParameterFromDate(
							r.getDataCreacio())
					);
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
					r.setId(keys.getLong(1));
				}
			}
		);
	}

	/**
	 * Esborra un rol de base de dades
	 * @param id Identificador del rol a esborrar 
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
	 * Modifica un rol a base de dades
	 * @param r El rol a modificar. Usarà l'id
	 * per buscar el registre i actualitzarà tots els seus camps
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public void modificar(final RolData r) throws SQLException {
		dw.executeSql(
			SQL_MODIFICAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setString(1, r.getNom());
					st.setTimestamp(2, DBWrapper.getParameterFromDate(r.getDataCreacio()));
					st.setLong(3, r.getId());
				}
			}
		);	
	}

	/**
	 * Recupera un rol per id
	 * @param id del rol a recuperar
	 * @return El rol si es troba, o null si no existeis
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public RolData obtenirPerId(final long id) throws SQLException {
		final RolData elRol = new RolData();
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
					crearRol(rset, elRol);
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return false;
				}
			}
		);
		return elRol.getId() == null ? null : elRol;
	}

	/**
	 * Recupera de base de dades tots els rols ordenats per nom
	 * @return La llista de rols. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public List<RolData> obtenirTots() throws SQLException {
		final List<RolData> elsRols = new Vector<RolData>();
		dw.executeQuery(
			SQL_SELECT_ALL, 
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					elsRols.add(crearRol(rset));
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return true;
				}
			}
		);
		return elsRols;
	}

	/**
	 * Funció d'utilitat per crear un objecte Rol a partir de la fila actual
	 * d'un recordset 
	 * @param rset El recordset d'on treurem el Rol
	 * @return El Rol creat
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	protected RolData crearRol(ResultSet rset) throws SQLException {
		return crearRol(rset, null);
	}

	/**
	 * Funció d'utilitat per omplir un objecte Rol amb els valors
	 * de la fila actual d'un recordset
	 * @param rset El recordset d'on treurem el Rol
	 * @param r El Rol a omplir. Pot ser null i es crearà un Rol nou
	 * @return El Rol amb els camps recuperats
	 * @throws SQLException El recordset d'on treurem el Rol
	 */
	protected RolData crearRol(ResultSet rset, RolData r) throws SQLException {
		// Si no em passen rol, el creo
		if (r == null) {
			r = new RolData();
		}
		// Omplo el rol desde el recordset
		r.setId(rset.getLong(ROLDATA_CAMP_ID));
		r.setNom(rset.getString(ROLDATA_CAMP_NOM));
		r.setDataCreacio(rset.getTimestamp(ROLDATA_CAMP_DATA_CREACIO));
		// Retorno l'opbjecte recuperat
		return r;
	}
}



