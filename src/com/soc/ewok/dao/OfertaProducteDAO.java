package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.sql.DataSource;
import com.soc.ewok.model.OfertaProducte;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

/**
 * 
 * @author JordiM
 *
 */
public class OfertaProducteDAO {
	/** Query per esborrar un registre per id */
	private static final String SQL_ESBORRAR = 
			"DELETE FROM " + ConstantsSQL.OFERTAPRODUCTE_TAULA + 
			" WHERE " + ConstantsSQL.OFERTAPRODUCTE_CAMP_ID + " = ?";
	
	/** Query per donar d'alta una OfertaProducte amb tots els seus camps */
	private static final String SQL_ALTA = 
			"INSERT INTO " + ConstantsSQL.OFERTAPRODUCTE_TAULA + 
			" (" + 
			ConstantsSQL.OFERTAPRODUCTE_CAMP_PCTDESCOMPTE + ", " + 
			ConstantsSQL.OFERTAPRODUCTE_CAMP_NOM + ", " +
			ConstantsSQL.OFERTAPRODUCTE_CAMP_INICI_VIGENCIA + ", " +
			ConstantsSQL.OFERTAPRODUCTE_CAMP_FI_VIGENCIA + ", " +
			ConstantsSQL.OFERTAPRODUCTE_CAMP_DESCRIPCIO + ", " +
			ConstantsSQL.OFERTAPRODUCTE_CAMP_IDPRODUCTE +
			")" + 
			" VALUES (?,?,?,?,?,?)";
	
	/** Query per modificar tots els camps d'una OfertaProducte per id */
	private static final String SQL_MODIFICAR = 
			"UPDATE " + ConstantsSQL.OFERTAPRODUCTE_TAULA + 
			" SET " + ConstantsSQL.OFERTAPRODUCTE_CAMP_PCTDESCOMPTE + " = ?, " +
			ConstantsSQL.OFERTAPRODUCTE_CAMP_NOM + " = ?, " +
			ConstantsSQL.OFERTAPRODUCTE_CAMP_INICI_VIGENCIA + " = ?, " +
			ConstantsSQL.OFERTAPRODUCTE_CAMP_FI_VIGENCIA + " = ?, " +
			ConstantsSQL.OFERTAPRODUCTE_CAMP_DESCRIPCIO + " = ?, " +
			ConstantsSQL.OFERTAPRODUCTE_CAMP_IDPRODUCTE + " = ?" +
			" WHERE " + ConstantsSQL.OFERTAPRODUCTE_CAMP_ID + " = ?";

	/** Query per obtenir totes les OfertaProducte de tipus x */
	private static final String SQL_SELECT_ALL = 
			"SELECT * FROM " + ConstantsSQL.OFERTAPRODUCTE_TAULA +
			" ORDER BY " + ConstantsSQL.OFERTAPRODUCTE_CAMP_ID;
	

	/** Query per obtenir una OfertaProducte per id */
	private static final String SQL_SELECT_BY_ID = 
			"SELECT * FROM " + ConstantsSQL.OFERTAPRODUCTE_TAULA +
			" WHERE " + ConstantsSQL.OFERTAPRODUCTE_CAMP_ID + " = ?";

	DBWrapper dw;
	
	/**
	 * Constructor de la classe 
	 * @param ds DataSource a usar per connectar-se a base de dades
	 */
	public OfertaProducteDAO(DataSource ds) {
		dw = new DBWrapper(ds);
	}
	
	/**
	 * Dóna d'alta un registre a base de dades.
	 * Actualitzarà l'id amb el valor generat pel gestor
	 * @param of OfertaProducte a donar d'alta
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void alta (final OfertaProducte of) throws SQLException {
		dw.executeSql(
				SQL_ALTA,
				new IPrepareStatement() {
					@Override
					public void prepareParams(
							Connection con, 
							PreparedStatement st) throws SQLException {
						st.setFloat(1, of.getPctDescompte());
						st.setString(2, of.getNom());
						st.setTimestamp(3, DBWrapper.getParameterFromDate(of.getIniciVigencia()));
						st.setTimestamp(4, DBWrapper.getParameterFromDate(of.getFiVigencia()));
						st.setString(5, of.getOtext());
						st.setLong(6, of.getIdProducte());
					}
				},
				new GenericExecuteQueryProcess() {		
					@Override
					public void doPostProcess(Connection con, PreparedStatement st)
							throws SQLException {
						// Uso el recordset de camps generats
						// que em dona l'statement per assignar
						// l'id a la OfertaProducte original
						ResultSet keys = st.getGeneratedKeys();
						keys.next();
						of.setId(keys.getLong(1));
					}
				}		

			);
		
	}
	
	/**
	 * Esborra una OfertaProducte de base de dades
	 * @param id Identificador de la OfertaProducte a esborrar 
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
	 * Modifica una OfertaProducte a base de dades
	 * @param of La OfertaProducte a modificar. Usarà l'id
	 * per buscar el registre i actualitzarà tots els seus camps
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public void modificar(final OfertaProducte of) throws SQLException {
		dw.executeSql(
			SQL_MODIFICAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setFloat(1, of.getPctDescompte());
					st.setString(2, of.getNom());
					st.setTimestamp(3, DBWrapper.getParameterFromDate(of.getIniciVigencia()));
					st.setTimestamp(4, DBWrapper.getParameterFromDate(of.getFiVigencia()));
					st.setString(5, of.getOtext());
					st.setLong(6, of.getIdProducte());
					st.setLong(7, of.getId());
				}
			}
		);	
	}
	
	/**
	 * Recupera una OfertaProducte per id
	 * @param id de la OfertaProducte a recuperar
	 * @return La OfertaProducte si es troba, o null si no existeix
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public OfertaProducte obtenirPerId(final long id) throws SQLException {
		final OfertaProducte laOfertaProducte = new OfertaProducte();
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
					crearOfertaProducte(rset, laOfertaProducte);
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return false;
				}


			}
		);
		return laOfertaProducte.getId() == null ? null : laOfertaProducte;
	}
	/**
	 * Recupera de base de dades tots els ofertaproducte ordenats per nom
	 * @return La llista de ofertaproducte. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public List<OfertaProducte> obtenirTots() throws SQLException {
		final List<OfertaProducte> lesOfertesProducte = new Vector<OfertaProducte>();
		dw.executeQuery(
			SQL_SELECT_ALL, 
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					lesOfertesProducte.add(crearOfertaProducte(rset));
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return true;
				}
			}
		);
		return lesOfertesProducte;
	}

	/**
	 * Funció d'utilitat per crear un objecte OfertaProducte a partir de la fila actual
	 * d'un recordset 
	 * @param rset El recordset d'on treurem el OfertaProducte
	 * @return El OfertaProducte creat
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	private OfertaProducte crearOfertaProducte(ResultSet rset) throws SQLException {
		return crearOfertaProducte(rset, null);
		
	}
	/**
	 * Funció d'utilitat per omplir un objecte OfertaProducte amb els valors
	 * de la fila actual d'un recordset
	 * @param rset El recordset d'on treurem el OfertaProducte
	 * @param of la OfertaProducte a omplir. Pot ser null i es crearà un OfertaProducte nou
	 * @return la OfertaProducte amb els camps recuperats
	 * @throws SQLException El recordset d'on treurem el OfertaProducte
	 */
	private OfertaProducte crearOfertaProducte(ResultSet rset, OfertaProducte of)throws SQLException  {
		if (of == null) {
			of = new OfertaProducte();
		}
		// Omplo la OfertaProducte desde el recordset
		of.setId(rset.getLong(ConstantsSQL.OFERTAPRODUCTE_CAMP_ID));
		of.setNom(rset.getString(ConstantsSQL.OFERTAPRODUCTE_CAMP_NOM));
		of.setPctDescompte(rset.getFloat(ConstantsSQL.OFERTAPRODUCTE_CAMP_PCTDESCOMPTE));
		of.setIniciVigencia(rset.getTimestamp(ConstantsSQL.OFERTAPRODUCTE_CAMP_INICI_VIGENCIA));
		of.setFiVigencia(rset.getTimestamp(ConstantsSQL.OFERTAPRODUCTE_CAMP_FI_VIGENCIA));
		of.setOtext(rset.getString(ConstantsSQL.OFERTAPRODUCTE_CAMP_DESCRIPCIO));
		of.setIdProducte(rset.getLong(ConstantsSQL.OFERTAPRODUCTE_CAMP_IDPRODUCTE));
		
		// Retorno l'objecte recuperat
		return of;
		}

	}
	


	
	

