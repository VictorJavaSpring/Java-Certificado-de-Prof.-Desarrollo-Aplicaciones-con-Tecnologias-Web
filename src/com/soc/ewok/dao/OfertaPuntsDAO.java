package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.soc.ewok.model.OfertaPunts;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;
/**
 * DAO per a la classe {@link OfertaPunts}
 * @author Eduardo
 *
 */
public class OfertaPuntsDAO {
	
	/** Query per esborrar una Oferta de Punts per id */
	private static final String SQL_ESBORRAR =
			"DELETE FROM " + ConstantsSQL.OFERTAPUNTS_TAULA + 
			" WHERE " + ConstantsSQL.OFERTAPUNTS_CAMP_ID + " = ?";
	

	/** Query per donar d'alta una Oferta de Punts amb tots els seus camps */
	private static final String SQL_ALTA = 
			"INSERT INTO " + ConstantsSQL.OFERTAPUNTS_TAULA + " (" + 
			ConstantsSQL.OFERTAPUNTS_CAMP_EUROS_PER_PUNT + ", " + 
			ConstantsSQL.OFERTAPUNTS_CAMP_PUNTS_PER_XEC + ", " + 
			ConstantsSQL.OFERTAPUNTS_CAMP_DIES_VIGENCIA_PUNTS + ", " + 
			ConstantsSQL.OFERTAPUNTS_CAMP_DIES_VIGENCIA_XECS + ", " + 
			ConstantsSQL.OFERTAPUNTS_CAMP_INICI_VIGENCIA + ", " + 
			ConstantsSQL.OFERTAPUNTS_CAMP_FI_VIGENCIA + ")"+
			" VALUES (?,?,?,?,?,?)";

	
	
	
	/** Query per modificar tots els camps d'una Oferta de Punts per id */
	private static final String SQL_MODIFICAR = 
			"UPDATE " + ConstantsSQL.OFERTAPUNTS_TAULA + 
			" SET " + ConstantsSQL.OFERTAPUNTS_CAMP_EUROS_PER_PUNT + " = ?, " + 
				ConstantsSQL.OFERTAPUNTS_CAMP_PUNTS_PER_XEC + " = ?, " +
				ConstantsSQL.OFERTAPUNTS_CAMP_DIES_VIGENCIA_PUNTS + " = ?, " +
				ConstantsSQL.OFERTAPUNTS_CAMP_DIES_VIGENCIA_XECS + " = ?, " +
				ConstantsSQL.OFERTAPUNTS_CAMP_INICI_VIGENCIA + " = ?, " +
				ConstantsSQL.OFERTAPUNTS_CAMP_FI_VIGENCIA + " = ? " +
			" WHERE " + ConstantsSQL.OFERTAPUNTS_CAMP_ID + " = ? ";


	
	/** Query per obtenir les ofertes de punts  */
	private static final String SQL_SELECT_ALL = 
			"SELECT " + "*" +
			" FROM " + ConstantsSQL.OFERTAPUNTS_TAULA +
			" ORDER BY " + ConstantsSQL.OFERTAPUNTS_CAMP_EUROS_PER_PUNT + " DESC";
		
	
	
	/** Query per obtenir les ofertes de punts per id */
	private static final String SQL_SELECT_BY_ID = 
			"SELECT " + ConstantsSQL.OFERTAPUNTS_CAMP_ID + ", " + 
					ConstantsSQL.OFERTAPUNTS_CAMP_EUROS_PER_PUNT + ", " +
					ConstantsSQL.OFERTAPUNTS_CAMP_PUNTS_PER_XEC + ", " +
					ConstantsSQL.OFERTAPUNTS_CAMP_DIES_VIGENCIA_PUNTS + ", " +
					ConstantsSQL.OFERTAPUNTS_CAMP_DIES_VIGENCIA_XECS + ", " +
					ConstantsSQL.OFERTAPUNTS_CAMP_INICI_VIGENCIA + ", " +
					ConstantsSQL.OFERTAPUNTS_CAMP_FI_VIGENCIA + 
			" FROM " + ConstantsSQL.OFERTAPUNTS_TAULA +
			" WHERE " + ConstantsSQL.OFERTAPUNTS_CAMP_ID + " = ?";
	

	
		DBWrapper dw;
	
		/**
		 * Constructor de la classe 
		 * @param ds DataSource a usar per connectar-se a base de dades
		 */
			public OfertaPuntsDAO(DataSource ds) {
			dw = new DBWrapper(ds);
			}

			/* Dóna d'alta un registre a base de dades.
			 * Actualitzarà l'id amb el valor generat pel gestor
			 * @param op OfertaPunts a donar d'alta
			 * @throws SQLException En cas que hi hagi un error de base de dades
			 */
 
				public void alta(final OfertaPunts op) throws SQLException {
				dw.executeSql(
				SQL_ALTA, 
				new IPrepareStatement() {
					@Override
					public void prepareParams(
							Connection con,
							PreparedStatement st) throws SQLException {
							st.setFloat(1, op.getEurosPerPunt());
							st.setShort(2, op.getPuntsPerXec());
							st.setLong (3, op.getDiesVigenciaPunts());
							st.setLong (4, op.getDiesVigenciaXecs());
							st.setTimestamp(5, DBWrapper.getParameterFromDate(op.getIniciVigencia()));
							st.setTimestamp(6, DBWrapper.getParameterFromDate(op.getFiVigencia()));
									
							
				}
				},
				
				new GenericExecuteQueryProcess() {		
					@Override
					public void doPostProcess(Connection con, PreparedStatement st)
							throws SQLException {
						// Uso el recordset de camps generats
						// que em dona l'statement per assignar
						// l'id al OfertaPunts original
						ResultSet keys = st.getGeneratedKeys();
						keys.next();
						op.setId(keys.getLong(1));
					}
					}
					);
				}
		
				/* Esborra una Oferta Punts  de base de dades
				 * @param id Identificador de l'oferta  a esborrar 
				 * @throws SQLException En cas que es produeixi un error de base de dades */

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
				 * Modifica una Oferta punts a base de dades
				 * @param op L'oferta punts a modificar. Usarà l'id
				 * per buscar el registre i actualitzarà tots els seus camps
				 * @throws SQLException En cas que es produeixi un error de base de dades*/
				 
				public void modificar(final OfertaPunts op) throws SQLException {
					dw.executeSql(
						SQL_MODIFICAR, 
						new IPrepareStatement() {
							@Override
							public void prepareParams(
								Connection con, 
								PreparedStatement st) throws SQLException {
									st.setFloat(1, op.getEurosPerPunt());
									st.setShort(2, op.getPuntsPerXec());
									st.setLong (3, op.getDiesVigenciaPunts());
									st.setLong (4, op.getDiesVigenciaXecs());
									st.setTimestamp(5, DBWrapper.getParameterFromDate(op.getIniciVigencia()));
									st.setTimestamp(6, DBWrapper.getParameterFromDate(op.getFiVigencia()));
									st.setLong(7, op.getId());
							}
						}
					);	
				}


				/**
				 * Recupera una oferta punts per id
				 * @param id de oferta punts a recuperar
				 * @return L'oferta punts si es troba, o null si no existeis
				 * @throws SQLException En cas que es produeixi un error de base de dades */
				 
				public OfertaPunts obtenirPerId(final long id) throws SQLException {
					final OfertaPunts elOfertaPunts = new OfertaPunts();
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
								crearOfertaPunts(rset, elOfertaPunts);
								// Indico al DBWrapper que segueixi amb el 
								// següent registre
								return false;
							}
						}
					);
					return elOfertaPunts.getId() == null ? null : elOfertaPunts;
				}


				
				/* Recupera de base de dades totes les Ofertes de Punts ordenats per Euros per punt 
				 * @return La llista Oferta de Punts. Si no n'hi ha, retornarà una llista buida
				 * @throws SQLException En cas que es produeixi un error de base de dades */
				 
				public List<OfertaPunts> obtenirTots() throws SQLException {
					final List<OfertaPunts> lesOfertesPunts = new Vector<OfertaPunts>();
					dw.executeQuery(
						SQL_SELECT_ALL, 
						new GenericExecuteQueryProcess() {
							@Override
							public boolean processRow(Connection con, PreparedStatement st,
									ResultSet rset) throws SQLException {
								// Obtinc el registre de la fila actual
								// i el fico a la llista
								lesOfertesPunts.add(crearOfertaPunts(rset));
								// Indico al DBWrapper que segueixi amb el 
								// següent registre
								return true;
							}
						}
					);
					return lesOfertesPunts;
				}
	
				
				/**
				 * Funció d'utilitat per crear un objecte Oferta Punts a partir de la fila actual
				 * d'un recordset 
				 * @param rset El recordset d'on treurem el Oferta Punts
				 * @return El Oferta Punts creat
				 * @throws SQLException En cas que es produeixi un error de base de dades
				 */
				protected OfertaPunts crearOfertaPunts(ResultSet rset) throws SQLException {
					return crearOfertaPunts(rset, null);
				}
				
				/**
				 * Funció d'utilitat per omplir un objecte Oferta Punts  amb els valors
				 * de la fila actual d'un recordset
				 * @param rset El recordset d'on treurem el Oferta Punts
				 * @param r El Oferta Punts a omplir. Pot ser null i es crearà un Oferta PuntsRol nou
				 * @return El Oferta Punts amb els camps recuperats
				 * @throws SQLException El recordset d'on treurem el Oferta Punts
				 */
				protected OfertaPunts crearOfertaPunts(ResultSet rset, OfertaPunts op) throws SQLException {
					// Si no em passen Oferta Punts, el creo
					if (op == null) {
						op = new OfertaPunts();
					}
					// Omplo el rol desde el resultset
					op.setId(rset.getLong(ConstantsSQL.OFERTAPUNTS_CAMP_ID));
					op.setEurosPerPunt(rset.getFloat(ConstantsSQL.OFERTAPUNTS_CAMP_EUROS_PER_PUNT));
					op.setPuntsPerXec(rset.getShort(ConstantsSQL.OFERTAPUNTS_CAMP_PUNTS_PER_XEC));
					op.setDiesVigenciaPunts(rset.getLong(ConstantsSQL.OFERTAPUNTS_CAMP_DIES_VIGENCIA_PUNTS));
					op.setDiesVigenciaXecs(rset.getLong(ConstantsSQL.OFERTAPUNTS_CAMP_DIES_VIGENCIA_XECS));
					op.setIniciVigencia(rset.getTimestamp(ConstantsSQL.OFERTAPUNTS_CAMP_INICI_VIGENCIA));
					op.setFiVigencia(rset.getTimestamp(ConstantsSQL.OFERTAPUNTS_CAMP_FI_VIGENCIA));
					
					// Retorno l'objecte recuperat
					return op;
				}
				
				
}
				

					


