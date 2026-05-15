package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;


import javax.sql.DataSource;

import com.soc.ewok.model.ComentariClient;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

/**
 * DAO per a la classe {@link ComentariClient}
 * @author Francesc
 *
 */
public class ComentariClientDAO {
	
	
		/** Query per esborrar un registre per id */
		private static final String SQL_ESBORRAR =
				"DELETE FROM " + ConstantsSQL.COMENTARI_TAULA + 
				" WHERE " + ConstantsSQL.COMENTARI_CAMP_ID + " = ?";
		
		/** Query per donar d'alta un comentari de client amb tots els seus camps */
		private static final String SQL_ALTA = 
				"INSERT INTO " + ConstantsSQL.COMENTARI_TAULA + 
				"(" + ConstantsSQL.COMENTARI_CAMP_IDCLIENT + ", " + ConstantsSQL.COMENTARI_CAMP_COMENTARI + 
				", " + ConstantsSQL.COMENTARI_CAMP_DATA + ", " + ConstantsSQL.COMENTARI_CAMP_IDAUTOR + ")" + 
				" VALUES (?,?,?,?)";
		
		/** Query per modificar tots els camps d'un comentari de client per id */
		private static final String SQL_MODIFICAR = 
				"UPDATE " + ConstantsSQL.COMENTARI_TAULA + 
				" SET " + ConstantsSQL.COMENTARI_CAMP_IDCLIENT + " = ?, " +
						ConstantsSQL.COMENTARI_CAMP_COMENTARI + " = ?, " +
						ConstantsSQL.COMENTARI_CAMP_DATA + " = ?, " +
						ConstantsSQL.COMENTARI_CAMP_IDAUTOR + " = ? " +
				" WHERE " + ConstantsSQL.COMENTARI_CAMP_ID + " = ?";

		/** Query per obtenir tots els comentaris de base de dades ordenats per id */
		private static final String SQL_SELECT_ALL = 
				"SELECT * "  +
				" FROM " + ConstantsSQL.COMENTARI_TAULA +
				" ORDER BY " + ConstantsSQL.COMENTARI_CAMP_ID;
		
		/** Query per obtenir un comentari per id */
		private static final String SQL_SELECT_BY_ID = 
				"SELECT * " + 
				" FROM " + ConstantsSQL.COMENTARI_TAULA +
				" WHERE " + ConstantsSQL.COMENTARI_CAMP_ID + " = ?";

		DBWrapper dw;

		/**
		 * Constructor de la classe 
		 * @param ds DataSource a usar per connectar-se a base de dades
		 */
		public ComentariClientDAO(DataSource ds) {
			dw = new DBWrapper(ds);
		}

		/**
		 * Dóna d'alta un registre a base de dades.
		 * Actualitzarà l'id amb el valor generat pel gestor
		 * @param cc Comentari de Client a donar d'alta
		 * @throws SQLException En cas que hi hagi un error de base de dades
		 */
		public void alta(final ComentariClient cc) throws SQLException {
			dw.executeSql(
				SQL_ALTA, 
				new IPrepareStatement() {
					@Override
					public void prepareParams(
							Connection con, 
							PreparedStatement st) throws SQLException {
						
						st.setLong(1, cc.getIdClient());
						st.setString(2, cc.getComentari());
						st.setTimestamp(3, DBWrapper.getParameterFromDate(cc.getData()));
						st.setString(4, cc.getIdAutor());
						
					}
				},
				new GenericExecuteQueryProcess() {		
					@Override
					public void doPostProcess(Connection con, PreparedStatement st)
							throws SQLException {
						// Uso el recordset de camps generats
						// que em dona l'statement per assignar
						// l'id al Comentari de Client original
						ResultSet keys = st.getGeneratedKeys();
						keys.next();
						cc.setId(keys.getLong(1));
					}
				}
			);
		}

		/**
		 * Esborra un Comentari de Client de la base de dades
		 * @param id Identificador del Comentari de Client a esborrar 
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
		 * Modifica un Comentari de Client a la base de dades
		 * @param cc El Comentari de Client a modificar. Usarà l'id
		 * per buscar el registre i actualitzarà tots els seus camps
		 * @throws SQLException En cas que es produeixi un error de base de dades
		 */
		public void modificar(final ComentariClient cc) throws SQLException {
			dw.executeSql(
				SQL_MODIFICAR, 
				new IPrepareStatement() {
					@Override
					public void prepareParams(
							Connection con, 
							PreparedStatement st) throws SQLException {
						st.setLong(1, cc.getIdClient());
						st.setString(2,cc.getComentari());
						st.setTimestamp(3, DBWrapper.getParameterFromDate(cc.getData()));
						st.setString(4, cc.getIdAutor());
						st.setLong(5, cc.getId());
							
					}
				}
			);	
		}

		/**
		 * Recupera un Comentari de Client per id
		 * @param id del comentari de client a recuperar
		 * @return El comentari de client si es troba, o null si no existeix
		 * @throws SQLException En cas que es produeixi un error de base de dades
		 */
		public ComentariClient obtenirPerId(final long id) throws SQLException {
			final ComentariClient elComentari = new ComentariClient();
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
						crearComentariClient(rset, elComentari);
						// Indico al DBWrapper que segueixi amb el 
						// següent registre
						return false;
					}
				} 
			);
			return elComentari.getId() == null ? null : elComentari;
		
			
		}

		/**
		 * Recupera de base de dades tots els comentaris ordenats per nom
		 * @return La llista de comentaris. Si no n'hi ha, retornarà una llista buida
		 * @throws SQLException En cas que es produeixi un error de base de dades
		 */
		public List<ComentariClient> obtenirTots() throws SQLException {
			final List<ComentariClient> elsComentaris = new Vector<ComentariClient>();
			dw.executeQuery(
				SQL_SELECT_ALL, 
				new GenericExecuteQueryProcess() {
					@Override
					public boolean processRow(Connection con, PreparedStatement st,
							ResultSet rset) throws SQLException {
						// Obtinc el registre de la fila actual
						// i el fico a la llista
						elsComentaris.add(crearComentariClient(rset));
						// Indico al DBWrapper que segueixi amb el 
						// següent registre
						return true;
					}
				}
			);
			return elsComentaris;
		}

		/**
		 * Funció d'utilitat per crear un objecte ComentariClient a partir de la fila actual
		 * d'un recordset 
		 * @param rset El recordset d'on treurem el ComentariClient
		 * @return El ComentariClient creat
		 * @throws SQLException En cas que es produeixi un error de base de dades
		 */
		protected ComentariClient crearComentariClient(ResultSet rset) throws SQLException {
			return crearComentariClient(rset, null);
		}

		/**
		 * Funció d'utilitat per omplir un objecte ComentariClient amb els valors
		 * de la fila actual d'un recordset
		 * @param rset El recordset d'on treurem el ComentariClient
		 * @param cc El ComentariCLient a omplir. Pot ser null i es crearà un ComentariClient nou
		 * @return El ComentariClient amb els camps recuperats
		 * @throws SQLException El recordset d'on treurem el ComentariClient
		 */
		protected ComentariClient crearComentariClient(ResultSet rset, ComentariClient cc) throws SQLException {
			// Si no em passen ComentariClient, el creo
			if (cc == null) {
				cc = new ComentariClient();
			}
			// Omplo el ComentariClient desde el recordset
			cc.setId(rset.getLong(ConstantsSQL.COMENTARI_CAMP_ID));
			cc.setIdClient(rset.getLong(ConstantsSQL.COMENTARI_CAMP_IDCLIENT));
			cc.setComentari(rset.getString(ConstantsSQL.COMENTARI_CAMP_COMENTARI));
			cc.setData(rset.getTimestamp(ConstantsSQL.COMENTARI_CAMP_DATA));
			cc.setIdAutor(rset.getString(ConstantsSQL.COMENTARI_CAMP_IDAUTOR));
			// Retorno l'opbjecte recuperat
			return cc;
		}
	}


