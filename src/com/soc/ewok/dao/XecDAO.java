package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.soc.ewok.model.Xec;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

public class XecDAO {

	/** Query per donar d'alta un xec amb tots els seus camps */
	private static final String SQL_ALTA = "INSERT INTO " + 
			ConstantsSQL.XEC_TAULA + " (" + 
			ConstantsSQL.XEC_CAMP_NUM_PUNTS + ", " + 
			ConstantsSQL.XEC_CAMP_DATA_CADUCITAT + ", " + 
			ConstantsSQL.XEC_CAMP_ID_COMANDA + ", " +
			ConstantsSQL.XEC_CAMP_ID_CLIENT + ") VALUES (?, ?, ?, ?)";
	
	/** Query per esborrar un registre per id */
	private static final String SQL_ESBORRAR = "DELETE FROM " + 
			ConstantsSQL.XEC_TAULA + " WHERE " + 
			ConstantsSQL.XEC_CAMP_ID + " = ?";
	
	/** Query per modificar tots els camps d'un xec per id */
	private static final String SQL_MODIFICAR = "UPDATE " + 
			ConstantsSQL.XEC_TAULA + " SET " + 
			ConstantsSQL.XEC_CAMP_NUM_PUNTS + " = ? , " + 
			ConstantsSQL.XEC_CAMP_DATA_CADUCITAT + " = ? , " + 
			ConstantsSQL.XEC_CAMP_ID_COMANDA + " = ? , " +
			ConstantsSQL.XEC_CAMP_ID_CLIENT + " = ? WHERE " + 
			ConstantsSQL.XEC_CAMP_ID + " = ?";
	
	private static final String SQL_SELECT_BY_ID = "SELECT " + 
			ConstantsSQL.XEC_CAMP_ID + ", " + 
			ConstantsSQL.XEC_CAMP_NUM_PUNTS + ", " +
			ConstantsSQL.XEC_CAMP_DATA_CADUCITAT + ", " +
			ConstantsSQL.XEC_CAMP_ID_COMANDA + ", " +
			ConstantsSQL.XEC_CAMP_ID_CLIENT + " FROM " + 
			ConstantsSQL.XEC_TAULA + " WHERE " + 
			ConstantsSQL.XEC_CAMP_ID + " = ?";
	
	private static final String SQL_SELECT_ALL = "SELECT " + 
			ConstantsSQL.XEC_CAMP_ID + ", " + 
			ConstantsSQL.XEC_CAMP_NUM_PUNTS + ", " +
			ConstantsSQL.XEC_CAMP_DATA_CADUCITAT + ", " +
			ConstantsSQL.XEC_CAMP_ID_COMANDA + ", " +
			ConstantsSQL.XEC_CAMP_ID_CLIENT + " FROM " + 
			ConstantsSQL.XEC_TAULA + " ORDER BY " + 
			ConstantsSQL.XEC_CAMP_ID;
	
	DBWrapper dw;

	/**
	 * Constructor de la classe 
	 * @param ds DataSource a usar per connectar-se a base de dades
	 */
	public XecDAO(DataSource ds) {
		dw = new DBWrapper(ds);
	}

	/**
	 * Dóna d'alta un registre a base de dades.
	 * Actualitzarà l'id amb el valor generat pel gestor
	 * @param x Xec a donar d'alta
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void alta(final Xec x) throws SQLException {
		dw.executeSql(
				SQL_ALTA, 
				new IPrepareStatement() {
					@Override
					public void prepareParams(
							Connection con, 
							PreparedStatement st) throws SQLException {
						st.setShort(1, x.getNumPunts());
						st.setTimestamp(
								2, 
								DBWrapper.getParameterFromDate(x.getDataCaducitat())
								);
						st.setLong(3, x.getIdComanda());
						st.setLong(4, x.getIdClient());

					}
				},
				new GenericExecuteQueryProcess() {		
					@Override
					public void doPostProcess(Connection con, PreparedStatement st)
							throws SQLException {
						// Uso el recordset de camps generats
						// que em dona l'statement per assignar
						// l'id al Xec original
						ResultSet keys = st.getGeneratedKeys();
						keys.next();
						x.setId(keys.getLong(1));
					}
				}
		);
	}
	
	/**
	 * Esborra un xec de base de dades
	 * @param id Identificador del xec a esborrar 
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
	 * Modifica un xec a base de dades
	 * @param x El xec a modificar. Usarà l'id
	 * per buscar el registre i actualitzarà tots els seus camps
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public void modificar(final Xec x) throws SQLException {
		dw.executeSql(
			SQL_MODIFICAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setShort(1, x.getNumPunts());
					st.setTimestamp(
							2, 
							DBWrapper.getParameterFromDate(x.getDataCaducitat())
							);
					st.setLong(3, x.getIdComanda());
					st.setLong(4, x.getIdClient());
					st.setLong(5, x.getId());
				}
			}
		);
	}
	
	/**
	 * Recupera un xec per id
	 * @param id del xec a recuperar
	 * @return El xec si es troba, o null si no existeix
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public Xec obtenirPerId(final long id) throws SQLException {
		final Xec elXec = new Xec();
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
					crearXec(rset, elXec);
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return false;
				}
			}
		);
		return elXec.getId() == null ? null : elXec;
	}

	/**
	 * Recupera de base de dades tots els xec ordenats per id
	 * @return La llista de xecs. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public List<Xec> obtenirTots() throws SQLException {
		final List<Xec> elsXecs = new Vector<Xec>();
		dw.executeQuery(
			SQL_SELECT_ALL, 
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					elsXecs.add(crearXec(rset));
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return true;
				}
			}
		);
		return elsXecs;
	}
	
	/**
	 * Funció d'utilitat per crear un objecte Xec a partir de la fila actual
	 * d'un recordset 
	 * @param rset El recordset d'on treurem el Xec
	 * @return El Xec creat
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	protected Xec crearXec(ResultSet rset) throws SQLException {
		return crearXec(rset, null);
	}

	/**
	 * Funció d'utilitat per omplir un objecte Xec amb els valors
	 * de la fila actual d'un recordset
	 * @param rset El recordset d'on treurem el Xec
	 * @param x El Xec a omplir. Pot ser null i es crearà un Xec nou
	 * @return El Xec amb els camps recuperats
	 * @throws SQLException El recordset d'on treurem el Xec
	 */
	protected Xec crearXec(ResultSet rset, Xec x) throws SQLException {
		// Si no em passen Xec, el creo
		if (x == null) {
			x = new Xec();
		}
		// Omplo el Xec desde el recordset
		x.setId(rset.getLong(ConstantsSQL.XEC_CAMP_ID));
		x.setNumPunts(rset.getShort(ConstantsSQL.XEC_CAMP_NUM_PUNTS));
		// Esto como lo modifico con el timestamp ???
		x.setDataCaducitat(rset.getDate(ConstantsSQL.XEC_CAMP_DATA_CADUCITAT));
		x.setIdComanda(rset.getLong(ConstantsSQL.XEC_CAMP_ID_COMANDA));
		x.setIdClient(rset.getLong(ConstantsSQL.XEC_CAMP_ID_CLIENT));
		// Retorno l'objecte recuperat
		return x;
	}
}
