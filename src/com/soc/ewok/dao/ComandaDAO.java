package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.soc.ewok.model.Comanda;
import com.soc.ewok.model.EEstatComanda;
import com.soc.ewok.model.EEstatLiniaComanda;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

/**
 * DAO per a la classe {@link Comanda}
 * @author Victor
 *
 */
public class ComandaDAO {
	
	/** Query per esborrar un registre per id */
	private static final String SQL_ESBORRAR =
			"DELETE FROM " + ConstantsSQL.COMANDA_TAULA + 
			" WHERE " + ConstantsSQL.COMANDA_CAMP_ID + " = ?";
	
	/** Query per donar d'alta una comanda amb tots els seus camps */
	private static final String SQL_ALTA = 
			"INSERT INTO " + ConstantsSQL.COMANDA_TAULA + 
			"(" + ConstantsSQL.COMANDA_CAMP_IDCLIENT +
			", " + ConstantsSQL.COMANDA_CAMP_PUNTS +
			", " + ConstantsSQL.COMANDA_CAMP_DESCOMPTE +
			", " + ConstantsSQL.COMANDA_CAMP_DATA +
			", " + ConstantsSQL.COMANDA_CAMP_ADRECA +
			", " + ConstantsSQL.COMANDA_CAMP_COMENTARIS + 
			", " + ConstantsSQL.COMANDA_CAMP_ESTAT +
			", " + ConstantsSQL.COMANDA_CAMP_PREUFINAL
			+ ")" + " VALUES (?,?,?,?,?,?,?,?)";
	
	/** Query per modificar tots els camps una comanda per Id */
	private static final String SQL_MODIFICAR = 
			"UPDATE " + 
			ConstantsSQL.COMANDA_TAULA + " SET " +  
			ConstantsSQL.COMANDA_CAMP_IDCLIENT + " = ? " + ", " + 
			ConstantsSQL.COMANDA_CAMP_PUNTS + " = ? " + ", " + 
			ConstantsSQL.COMANDA_CAMP_DESCOMPTE + " = ? " + ", " + 
			ConstantsSQL.COMANDA_CAMP_DATA + " = ? " + ", " + 
			ConstantsSQL.COMANDA_CAMP_ADRECA + " = ? " + ", " + 
			ConstantsSQL.COMANDA_CAMP_COMENTARIS + " = ? " + ", " + 
			ConstantsSQL.COMANDA_CAMP_ESTAT + " = ? " + ", " + 
			ConstantsSQL.COMANDA_CAMP_PREUFINAL + " = ? " + 
			" WHERE " + 
			ConstantsSQL.COMANDA_CAMP_ID + " = ?";
	
	/** Query per obtenir totes les comandes de base de dades (ordenades per IdClient) */
	private static final String SQL_SELECT_ALL = 
			"SELECT " + ConstantsSQL.COMANDA_CAMP_ID + ", " + ConstantsSQL.COMANDA_CAMP_IDCLIENT + 
			", " + ConstantsSQL.COMANDA_CAMP_PUNTS + ", " + ConstantsSQL.COMANDA_CAMP_DESCOMPTE +
			", " + ConstantsSQL.COMANDA_CAMP_DATA + ", " + ConstantsSQL.COMANDA_CAMP_ADRECA +
			", " + ConstantsSQL.COMANDA_CAMP_COMENTARIS + ", " + ConstantsSQL.COMANDA_CAMP_ESTAT +
			", " + ConstantsSQL.COMANDA_CAMP_PREUFINAL +
			" FROM " + ConstantsSQL.COMANDA_TAULA +
			" ORDER BY " + ConstantsSQL.COMANDA_CAMP_IDCLIENT;
	
	/** Query per obtenir una comanda per id  */
	private static final String SQL_SELECT_BY_ID = 
			"SELECT * FROM " + ConstantsSQL.COMANDA_TAULA + " WHERE " + 
					ConstantsSQL.COMANDA_CAMP_ID + " = ?";

	/** Query per obtenir una comanda per id (carregant les seves Linies de comanda) */
	private static final String SQL_SELECT_BY_ID_LCOMANDA = 
			"SELECT *, lc." + ConstantsSQL.LINIACOMANDA_CAMP_ESTAT + " as " +
					ConstantsSQL.LINIACOMANDA_CAMP_ALIAS_ESTAT  
					+ " FROM " + ConstantsSQL.COMANDA_TAULA + " c  LEFT JOIN " +
					ConstantsSQL.LINIACOMANDA_TAULA + " lc  on  c." + ConstantsSQL.COMANDA_CAMP_ID
					+ " = lc." + ConstantsSQL.LINIACOMANDA_CAMP_ID + " WHERE c." + 
					ConstantsSQL.COMANDA_CAMP_ID + " = ?";
	
	private static final String SQL_SELECT_BY_EMAIL_ROLS = "SELECT * FROM "
			+ ConstantsSQL.USUARI_TAULA + " u " + "LEFT JOIN "
			+ ConstantsSQL.ROL_USUARI_TAULA + " ru ON u."
			+ ConstantsSQL.USUARI_CAMP_EMAIL + "=" + "ru."
			+ ConstantsSQL.ROL_USUARI_CAMP_EMAIL + " LEFT JOIN "
			+ ConstantsSQL.ROL_TAULA + " r on ru."
			+ ConstantsSQL.ROL_USUARI_CAMP_ID + "=r."
			+ ConstantsSQL.ROL_CAMP_ID + " WHERE u."
			+ ConstantsSQL.USUARI_CAMP_EMAIL + " = ?";
	
	/** Query per obtenir una llista de comandes per estat, ordenades?? */
	private static final String SQL_SELECT_ALL_BY_ESTAT = 
			"SELECT * FROM " + ConstantsSQL.COMANDA_TAULA + " WHERE " +
					ConstantsSQL.COMANDA_CAMP_ESTAT + " = ?";
	
	/** Query per obtenir una llista de comandes per estat validada o preparada, ordenades?? */
	private static final String SQL_SELECT_ALL_BY_ESTATS_VALIPREPA = 
			"SELECT * FROM " + ConstantsSQL.COMANDA_TAULA + " WHERE " +
					ConstantsSQL.COMANDA_CAMP_ESTAT + " = ? OR " + 
					ConstantsSQL.COMANDA_CAMP_ESTAT + " = ?";

	// Constants globals
	DBWrapper dw;
	LiniaComandaDAO lc;
	
	/**
	 * Constructor de la classe 
	 * @param ds DataSource a usar per connectar-se a base de dades
	 */
	public ComandaDAO(DataSource ds) {
		dw = new DBWrapper(ds);
		lc = new LiniaComandaDAO(ds);
	}

	/**
	 * Dóna d'alta un registre a base de dades.
	 * Actualitzara l'id amb el valor generat pel gestor
	 * @param c Comanda a donar d'alta
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void alta(final Comanda c) throws SQLException {
		dw.executeSql(
			SQL_ALTA, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1,c.getIdClient());
					st.setShort(2, c.getPunts());
					st.setFloat(3, c.getDescompte());
					st.setTimestamp(
							4,
							DBWrapper.getParameterFromDate(
									c.getData())
							);
					st.setLong(5, c.getAdreca());
					st.setString(6, c.getComentaris());
					st.setInt(7, c.getEstat().ordinal());
					st.setFloat(8, c.getPreuFinal());
					
				}
			},
			new GenericExecuteQueryProcess() {		
				@Override
				public void doPostProcess(Connection con, PreparedStatement st)
						throws SQLException {
					// Uso el recordset de camps generats
					// que em dona l'statement per assignar
					// l'id a la Comanda original
					ResultSet keys = st.getGeneratedKeys();
					keys.next();
					c.setId(keys.getLong(1));
				}
			}
		);
	}

	/**
	 * Esborra una comanda de base de dades
	 * @param id Identificador de la comanda a esborrar 
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
	 * Modifica una comanda a base de dades
	 * @param c La comanda a modificar. Usara l'id
	 * 		per buscar el registre i actualitzarà tots els seus camps
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public void modificar(final Comanda c) throws SQLException {
		dw.executeSql(
			SQL_MODIFICAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1,c.getIdClient());
					st.setShort(2, c.getPunts());
					st.setFloat(3, c.getDescompte());
					st.setTimestamp(4, DBWrapper.getParameterFromDate(c.getData()));
					st.setLong(5, c.getAdreca());
					st.setString(6, c.getComentaris());
					st.setInt(7, c.getEstat().ordinal());
					st.setFloat(8, c.getPreuFinal());
					st.setLong(9, c.getId());
				}
			}
		);	
	}

	/**
	 * Recupera una comanda per id
	 * @param id de la comanda a recuperar
	 * @return La comanda si es troba, o null si no existeix
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public Comanda obtenirPerId(final long id) throws SQLException {
		final Comanda elComanda = new Comanda();
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
					// i l'introdueixo a l'objecte que retornara
					crearComanda(rset, elComanda);
					// Indico al DBWrapper que segueixi amb el 
					// seguent registre
					return false;
				}
			}
		);
		return elComanda.getId() == null ? null : elComanda;
	}
	
	
	/**
	 * Recupera una comanda amb la seva linia de comandes per id
	 * @param id de la comanda a recuperar
	 * @return La comanda si es troba, o null si no existeix
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public Comanda obtenirPerIdLC(final long id) throws SQLException {
		final Comanda elComanda = new Comanda();
		//preparem la query amb l'id i l'executem 
		dw.executeQuery(
			// SQL_SELECT_BY_ID que carregi tambe les seves linies de comanda
			SQL_SELECT_BY_ID_LCOMANDA, 
			// inserim l'id a l'statement
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1, id);
				}
			},
			// processem el resultat
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con,
						PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Si la comanda no s'ha omplert ja:
					// Obtinc el registre de la fila actual
					// i l'introdueixo a l'objecte que retornara
					if (elComanda.getId() == null) {
						crearComanda(rset, elComanda);
					}
					// si hi ha liniaComanda l'afegim a la comanda
					rset.getLong(ConstantsSQL.LINIACOMANDA_CAMP_ID);
					// obtenir la id si n'hi ha
					if (rset.wasNull()) {
						// si no hi ha liniaComanda cridem al metode addLiniaComanda amb null
						// aixo carregara una llista de liniesComanda buida
						// (obligatori)
						elComanda.addLiniaComanda(null);
					} else {
						// hi ha liniaComanda -> l'obtenim amb el liniaComandaDAO i l'afegim
						elComanda.addLiniaComanda(lc.crearLiniaComanda(rset));
					}
					// Indico al DBWrapper que segueixi amb el 
					// seguent registre
					return true;
				}
			}
		);
		return elComanda.getId() == null ? null : elComanda;
	}
	
	
	/**
	 * Obté una llista de les Comandes en curs amb estat validada o preparada
	 * @return una llista de les Comandes en curs amb estat validada o preparada
	 * @throws SQLException
	 */
	public List<Comanda> obtenirComandesEstatsValidada_Preparada() throws SQLException{
		final List<Comanda> lesComandesEnCursValidPrepa = new Vector<Comanda>();
		dw.executeQuery(
				SQL_SELECT_ALL_BY_ESTATS_VALIPREPA,
		new IPrepareStatement() {
			@Override
			public void prepareParams(
					Connection con, 
					PreparedStatement st) throws SQLException {
				st.setInt(1, EEstatComanda.validada.ordinal());
				st.setInt(2, EEstatComanda.preparada.ordinal());
			}
		},
		new GenericExecuteQueryProcess() {
			@Override
			public boolean processRow(Connection con, PreparedStatement st,
					ResultSet rset) throws SQLException {
				// Obtinc el registre de la fila actual
				// i el fico a la llista
				lesComandesEnCursValidPrepa.add(crearComanda(rset));
				// Indico al DBWrapper que segueixi amb el 
				// següent registre
				return true;
			}
		}
		);
	return lesComandesEnCursValidPrepa;
	}
	
	/**
	 * Obté una llista de les Comandes en curs amb estat Preparada
	 * @return una llista de les Comandes en curs amb estat Preparada
	 * @throws SQLException
	 */
	public List<Comanda> obtenirFiltreEstatPreparada() throws SQLException{
		//public List<Comanda> obtenirFiltreEstatValidada(final String estatComanda) throws SQLException{
		final List<Comanda> lesComandesEnCursPreparada = new Vector<Comanda>();
		dw.executeQuery(
		SQL_SELECT_ALL_BY_ESTAT,
		new IPrepareStatement() {
			@Override
			public void prepareParams(
					Connection con, 
					PreparedStatement st) throws SQLException {
				st.setInt(1, EEstatComanda.preparada.ordinal());
			}
		},
		new GenericExecuteQueryProcess() {
			@Override
			public boolean processRow(Connection con, PreparedStatement st,
					ResultSet rset) throws SQLException {
				// Obtinc el registre de la fila actual
				// i el fico a la llista
				lesComandesEnCursPreparada.add(crearComanda(rset));
				// Indico al DBWrapper que segueixi amb el 
				// següent registre
				return true;
			}
		}
		);
	return lesComandesEnCursPreparada;
	}
	
	
	/**
	 * Recupera de base de dades totes les comandes ordenades per IdClient
	 * @return La llista de comandes. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public List<Comanda> obtenirTots() throws SQLException {
		final List<Comanda> elsComandas = new Vector<Comanda>();
		dw.executeQuery(
			SQL_SELECT_ALL, 
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					elsComandas.add(crearComanda(rset));
					// Indico al DBWrapper que segueixi amb el 
					// seguent registre
					return true;
				}
			}
		);
		return elsComandas;
	}

	/**
	 * Funció d'utilitat per crear un objecte Comanda a partir de la fila actual
	 * d'un recordset 
	 * @param rset El recordset d'on treurem la Comanda
	 * @return La Comanda creada
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	private Comanda crearComanda(ResultSet rset) throws SQLException {
		return crearComanda(rset, null);
	}

	/**
	 * Funció d'utilitat per omplir un objecte Comanda amb els valors
	 * de la fila actual d'un recordset
	 * @param rset El recordset d'on treurem la Comanda
	 * @param c La Comanda a omplir. Pot ser null i es crearà una Comanda nova
	 * @return La Comanda amb els camps recuperats
	 * @throws SQLException El recordset d'on treurem la Comanda
	 */
	private Comanda crearComanda(ResultSet rset, Comanda c) throws SQLException {
		// Si no em passen comanda, la creo
		if (c == null) {
			c = new Comanda();
		}
		// Omplo la Comanda desde el recordset
		c.setId(rset.getLong(ConstantsSQL.COMANDA_CAMP_ID));
		c.setIdClient(rset.getLong(ConstantsSQL.COMANDA_CAMP_IDCLIENT));
		c.setPunts(rset.getShort(ConstantsSQL.COMANDA_CAMP_PUNTS));
		c.setDescompte(rset.getFloat(ConstantsSQL.COMANDA_CAMP_DESCOMPTE));
		c.setData(rset.getTimestamp(ConstantsSQL.COMANDA_CAMP_DATA));
		c.setAdreca(rset.getLong(ConstantsSQL.COMANDA_CAMP_ADRECA));
		c.setComentaris(rset.getString(ConstantsSQL.COMANDA_CAMP_COMENTARIS));
		c.setEstat(EEstatComanda.values()[rset.getInt(ConstantsSQL.COMANDA_CAMP_ESTAT)]);
		c.setPreuFinal(rset.getFloat(ConstantsSQL.COMANDA_CAMP_PREUFINAL));

		// Retorno l'objecte recuperat
		return c;
	}
	 /**
	 * Funcio d'utilitat que crea un producte a partir de la fila actual de un resultset
	 * 
	 * @param rset El resultset d'on treurem les dades
	 * @return La Comanda creada
	 */
	public Comanda crearComandaLinia(ResultSet rset) {
		Comanda c = new Comanda();
		try {
			c = crearComanda(rset);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
}



