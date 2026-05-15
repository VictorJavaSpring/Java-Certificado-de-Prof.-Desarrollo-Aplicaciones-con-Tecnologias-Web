package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.soc.ewok.model.Unitat;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

	/**
	 * DAO per a la classe {@link Unitat}
	 * @author Edixon
	 *
	 */
	public class UnitatDAO {
		
		/** Query per esborrar un registre per id */
		private static final String SQL_ESBORRAR =
				"DELETE FROM " + ConstantsSQL.UNITAT_TAULA + 
				" WHERE " + ConstantsSQL.UNITAT_CAMP_ID + " = ?";
		
		/** Query per donar d'alta una unitat de medida amb tots els seus camps */
		private static final String SQL_ALTA = 
				"INSERT INTO " + ConstantsSQL.UNITAT_TAULA + 
				"(" + ConstantsSQL.UNITAT_CAMP_NOM + ", "
				+ ConstantsSQL.UNITAT_CAMP_ACRONIM + ")" +
				" VALUES (?, ?)";
		
		/** Query per modificar tots els camps d'una unitat per id */
		private static final String SQL_MODIFICAR = 
				"UPDATE " + ConstantsSQL.UNITAT_TAULA + 
				" SET " + ConstantsSQL.UNITAT_CAMP_NOM + " = ? " +
				", " + ConstantsSQL.UNITAT_CAMP_ACRONIM + " = ? " +
				" WHERE " + ConstantsSQL.UNITAT_CAMP_ID + " = ?";

		/** Query per obtenir tots las unitats de base de dades */
		private static final String SQL_SELECT_ALL = 
				"SELECT * " +  
				" FROM " + ConstantsSQL.UNITAT_TAULA +
				" ORDER BY " + ConstantsSQL.UNITAT_CAMP_NOM;
		
		/** Query per obtenir un rol per id */
		private static final String SQL_SELECT_BY_ID = 
				"SELECT * " +
				" FROM " + ConstantsSQL.UNITAT_TAULA +
				" WHERE " + ConstantsSQL.UNITAT_CAMP_ID + " = ?";

		DBWrapper dw;
		
		
		/**
		 * Constructor de la classe 
		 * @param ds DataSource a usar per connectar-se a base de dades
		 */
		public UnitatDAO(DataSource ds) {
			dw = new DBWrapper(ds);
		}

		/**
		 * Dóna d'alta un registre a base de dades.
		 * Actualitzarà l'id amb el valor generat pel gestor
		 * @param u Unitat a donar d'alta
		 * @throws SQLException En cas que hi hagi un error de base de dades
		 */
		public void alta(final Unitat u) throws SQLException {
			dw.executeSql(
				SQL_ALTA, 
				new IPrepareStatement() {
					@Override
					public void prepareParams(
							Connection con, 
							PreparedStatement st) throws SQLException {
						st.setString(1, u.getNom());
						st.setString(2, u.getAcron());
						
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
						u.setId(keys.getLong(1));
					}
				}
			);
		}

		/**
		 * Esborra una Unitat de base de dades
		 * @param id Identificador de la Unitat a esborrar 
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
		 * Modifica una Unitat a base de dades
		 * @param u El Unitat a modificar. Usarà l'id
		 * per buscar el registre i actualitzarà tots els seus camps
		 * @throws SQLException En cas que es produeixi un error de base de dades
		 */
		public void modificar(final Unitat u) throws SQLException {
			dw.executeSql(
				SQL_MODIFICAR, 
				new IPrepareStatement() {
					@Override
					public void prepareParams(
							Connection con, 
							PreparedStatement st) throws SQLException {
						st.setString(1, u.getNom());						
						st.setString(2, u.getAcron());
						st.setLong(3, u.getId());
					}
				}
			);	
		}

		/**
		 * Recupera una Unitat per id
		 * @param id de la Unitat a recuperar
		 * @return La Unitat si es troba, o null si no existeis
		 * @throws SQLException En cas que es produeixi un error de base de dades
		 */
		public Unitat obtenirPerId(final long id) throws SQLException {
			final Unitat laUnitat = new Unitat();
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
							ResultSet uset) throws SQLException {
						// Obtinc el registre de la fila actual
						// i el fico a l'objecte que retornaré
						crearUnitat(uset, laUnitat);
						// Indico al DBWrapper que segueixi amb el 
						// següent registre
						return false;
					}
				}
			);
			return laUnitat.getId() == null ? null : laUnitat;
		}

		/**
		 * Recupera de base de dades tots las unitats ordenats per nom
		 * @return La llista de unitats. Si no n'hi ha, retornarà una llista buida
		 * @throws SQLException En cas que es produeixi un error de base de dades
		 */
		public List<Unitat> obtenirTots() throws SQLException {
			final List<Unitat> laUnitat = new Vector<Unitat>();
			dw.executeQuery(
				SQL_SELECT_ALL, 
				new GenericExecuteQueryProcess() {
					@Override
					public boolean processRow(Connection con, PreparedStatement st,
							ResultSet uset) throws SQLException {
						// Obtinc el registre de la fila actual
						// i el fico a la llista
						laUnitat.add(crearUnitat(uset));
						// Indico al DBWrapper que segueixi amb el 
						// següent registre
						return true;
					}
				}
			);
			return laUnitat;
		}

		/**
		 * Funció d'utilitat per crear un objecte Unitat a partir de la fila actual
		 * d'un recordset 
		 * @param uset El recordset d'on treurem la Unitat
		 * @return la Unitat creat
		 * @throws SQLException En cas que es produeixi un error de base de dades
		 */
		protected Unitat crearUnitat(ResultSet uset) throws SQLException {
			return crearUnitat(uset, null);
		}

		/**
		 * Funció d'utilitat per omplir un objecte Unitat amb els valors
		 * de la fila actual d'un recordset
		 * @param uset El recordset d'on treurem la Unitat
		 * @param u La Unitat a omplir. Pot ser null i es crearà una Unitat nou
		 * @return La Unitat amb els camps recuperats
		 * @throws SQLException El recordset d'on treurem la Unitat
		 */
		protected Unitat crearUnitat(ResultSet uset, Unitat u) throws SQLException {
			// Si no em passen rol, el creo
			if (u == null) {
				u = new Unitat();
			}
			// Omplo el rol desde el recordset
			u.setId(uset.getLong(ConstantsSQL.UNITAT_CAMP_ID));
			u.setNom(uset.getString(ConstantsSQL.UNITAT_CAMP_NOM));
			u.setAcron(uset.getString(ConstantsSQL.UNITAT_CAMP_ACRONIM));
			// Retorno l'opbjecte recuperat
			return u;
		}


}

