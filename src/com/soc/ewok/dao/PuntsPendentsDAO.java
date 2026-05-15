package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.soc.ewok.model.PuntsPendents;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

public class PuntsPendentsDAO {

	/** Query per donar d'alta un registre amb tots els seus camps */
	private static final String SQL_ALTA = "INSERT INTO " + 
			ConstantsSQL.PUNTSPENDENTS_TAULA + " (" + 
			ConstantsSQL.PUNTSPENDENTS_CAMP_NUM_PUNTS + ", " + 
			ConstantsSQL.PUNTSPENDENTS_CAMP_DATA_CADUCITAT + ", " + 
			ConstantsSQL.PUNTSPENDENTS_CAMP_ID_CLIENT + ") VALUES (?, ?, ?)";

	/** Query per esborrar un registre per id */
	private static final String SQL_ESBORRAR = "DELETE FROM " + 
			ConstantsSQL.PUNTSPENDENTS_TAULA + " WHERE " + 
			ConstantsSQL.PUNTSPENDENTS_CAMP_ID + " = ?";

	/** Query per modificar tots els camps d'un registre per id */
	private static final String SQL_MODIFICAR = "UPDATE " + 
			ConstantsSQL.PUNTSPENDENTS_TAULA + " SET " + 
			ConstantsSQL.PUNTSPENDENTS_CAMP_NUM_PUNTS + " = ? , " + 
			ConstantsSQL.PUNTSPENDENTS_CAMP_DATA_CADUCITAT + " = ? , " + 
			ConstantsSQL.PUNTSPENDENTS_CAMP_ID_CLIENT + " = ? WHERE " + 
			ConstantsSQL.PUNTSPENDENTS_CAMP_ID + " = ?";

	private static final String SQL_SELECT_BY_ID = "SELECT " + 
			ConstantsSQL.PUNTSPENDENTS_CAMP_ID + ", " + 
			ConstantsSQL.PUNTSPENDENTS_CAMP_NUM_PUNTS + ", " +
			ConstantsSQL.PUNTSPENDENTS_CAMP_DATA_CADUCITAT + ", " +
			ConstantsSQL.PUNTSPENDENTS_CAMP_ID_CLIENT + " FROM " + 
			ConstantsSQL.PUNTSPENDENTS_TAULA + " WHERE " + 
			ConstantsSQL.PUNTSPENDENTS_CAMP_ID + " = ?";

	private static final String SQL_SELECT_ALL = "SELECT " + 
			ConstantsSQL.PUNTSPENDENTS_CAMP_ID + ", " + 
			ConstantsSQL.PUNTSPENDENTS_CAMP_NUM_PUNTS + ", " +
			ConstantsSQL.PUNTSPENDENTS_CAMP_DATA_CADUCITAT + ", " +
			ConstantsSQL.PUNTSPENDENTS_CAMP_ID_CLIENT + " FROM " + 
			ConstantsSQL.PUNTSPENDENTS_TAULA + " ORDER BY " + 
			ConstantsSQL.PUNTSPENDENTS_CAMP_ID;
	
	DBWrapper dw;

	/**
	 * Constructor de la classe 
	 * @param ds DataSource a usar per connectar-se a base de dades
	 */
	public PuntsPendentsDAO(DataSource ds) {
		dw = new DBWrapper(ds);
	}

	/**
	 * Dóna d'alta un registre a base de dades.
	 * Actualitzarà l'id amb el valor generat pel gestor
	 * @param pp PuntsPendents a donar d'alta
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void alta(final PuntsPendents pp) throws SQLException {
		dw.executeSql(
				SQL_ALTA, 
				new IPrepareStatement() {
					@Override
					public void prepareParams(
							Connection con, 
							PreparedStatement st) throws SQLException {
						st.setShort(1, pp.getNumPunts());
						st.setTimestamp(
								2, 
								DBWrapper.getParameterFromDate(pp.getDataCaducitat())
								);
						st.setLong(3, pp.getIdClient());

					}
				},
				new GenericExecuteQueryProcess() {		
					@Override
					public void doPostProcess(Connection con, PreparedStatement st)
							throws SQLException {
						// Uso el recordset de camps generats
						// que em dona l'statement per assignar
						// l'id al PuntsPendents original
						ResultSet keys = st.getGeneratedKeys();
						keys.next();
						pp.setId(keys.getLong(1));
					}
				}
				);
	}

	/**
	 * Esborra un PuntsPendents de base de dades
	 * @param id Identificador del PuntsPendents a esborrar 
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
	 * Modifica un PuntsPendents a base de dades
	 * @param pp El PuntsPendents a modificar. Usarà l'id
	 * per buscar el registre i actualitzarà tots els seus camps
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public void modificar(final PuntsPendents pp) throws SQLException {
		dw.executeSql(
				SQL_MODIFICAR, 
				new IPrepareStatement() {
					@Override
					public void prepareParams(
							Connection con, 
							PreparedStatement st) throws SQLException {
						st.setShort(1, pp.getNumPunts());
						st.setTimestamp(
								2, 
								DBWrapper.getParameterFromDate(pp.getDataCaducitat())
								);
						st.setLong(3, pp.getIdClient());
						st.setLong(4, pp.getId());
					}
				}
				);
	}

	/**
	 * Recupera un PuntsPendents per id
	 * @param id del PuntsPendents a recuperar
	 * @return El PuntsPendents si es troba, o null si no existeix
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public PuntsPendents obtenirPerId(final long id) throws SQLException {
		final PuntsPendents elPP = new PuntsPendents();
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
						crearPuntsPendents(rset, elPP);
						// Indico al DBWrapper que segueixi amb el 
						// següent registre
						return false;
					}
				}
				);
		return elPP.getId() == null ? null : elPP;
	}

	/**
	 * Recupera de base de dades tots els PuntsPendents ordenats per id
	 * @return La llista de PuntsPendents. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public List<PuntsPendents> obtenirTots() throws SQLException {
		final List<PuntsPendents> elsPP = new Vector<PuntsPendents>();
		dw.executeQuery(
				SQL_SELECT_ALL, 
				new GenericExecuteQueryProcess() {
					@Override
					public boolean processRow(Connection con, PreparedStatement st,
							ResultSet rset) throws SQLException {
						// Obtinc el registre de la fila actual
						// i el fico a la llista
						elsPP.add(crearPuntsPendents(rset));
						// Indico al DBWrapper que segueixi amb el 
						// següent registre
						return true;
					}
				}
				);
		return elsPP;
	}

	/**
	 * Funció d'utilitat per crear un objecte PuntsPendents a partir de la fila
	 * actual d'un recordset 
	 * @param rset El recordset d'on treurem el PuntsPendents
	 * @return El PuntsPendents creat
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	protected PuntsPendents crearPuntsPendents(ResultSet rset) throws SQLException {
		return crearPuntsPendents(rset, null);
	}

	/**
	 * Funció d'utilitat per omplir un objecte PuntsPendents amb els valors
	 * de la fila actual d'un recordset
	 * @param rset El recordset d'on treurem el PuntsPendents
	 * @param x El PuntsPendents a omplir. Pot ser null i es crearà un PuntsPendents nou
	 * @return El PuntsPendents amb els camps recuperats
	 * @throws SQLException El recordset d'on treurem el PuntsPendents
	 */
	protected PuntsPendents crearPuntsPendents(ResultSet rset, PuntsPendents pp) throws SQLException {
		// Si no em passen PuntsPendents, el creo
		if (pp == null) {
			pp = new PuntsPendents();
		}
		// Omplo el PuntsPendents desde el recordset
		pp.setId(rset.getLong(ConstantsSQL.PUNTSPENDENTS_CAMP_ID));
		pp.setNumPunts(rset.getShort(ConstantsSQL.PUNTSPENDENTS_CAMP_NUM_PUNTS));
		pp.setDataCaducitat(rset.getTimestamp(ConstantsSQL.PUNTSPENDENTS_CAMP_DATA_CADUCITAT));
		pp.setIdClient(rset.getLong(ConstantsSQL.PUNTSPENDENTS_CAMP_ID_CLIENT));
		// Retorno l'objecte recuperat
		return pp;
	}
}

