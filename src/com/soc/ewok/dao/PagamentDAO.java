package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.soc.ewok.model.Pagament;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

/**
 * DAO per a la classe {@link Pagament}
 * @author CarlosM
 *
 */

public class PagamentDAO {
	private static final String SQL_ESBORRAR =
			"DELETE FROM " + ConstantsSQL.PAGAMENTS_TAULA + 
			" WHERE " + ConstantsSQL.PAGAMENTS_CAMP_ID + " = ?";
	
	private static final String SQL_ALTA = 
			"INSERT INTO " + ConstantsSQL.PAGAMENTS_TAULA + 
			"(" + ConstantsSQL.PAGAMENTS_CAMP_IDCOMANDA + ", " +
			ConstantsSQL.PAGAMENTS_CAMP_QUANTITAT +  ", " +
			ConstantsSQL.PAGAMENTS_CAMP_IDFORMAPAGAMENT + ", " +
			ConstantsSQL.PAGAMENTS_CAMP_DATA + ")" + 
			" VALUES (?, ?, ?, ?)";
	
	private static final String SQL_MODIFICAR = 
			"UPDATE " + ConstantsSQL.PAGAMENTS_TAULA + 
			" SET " + ConstantsSQL.PAGAMENTS_CAMP_IDCOMANDA + " = ?, " +
			ConstantsSQL.PAGAMENTS_CAMP_QUANTITAT +  " = ?, " +
			ConstantsSQL.PAGAMENTS_CAMP_IDFORMAPAGAMENT + " = ?, " +
			ConstantsSQL.PAGAMENTS_CAMP_DATA + " = ?" +
			" WHERE " + ConstantsSQL.PAGAMENTS_CAMP_ID + " = ?";

	private static final String SQL_SELECT_ALL = 
			"SELECT " + ConstantsSQL.PAGAMENTS_CAMP_ID + ", " + 
			ConstantsSQL.PAGAMENTS_CAMP_IDCOMANDA + ", " +
			ConstantsSQL.PAGAMENTS_CAMP_QUANTITAT +  ", " +
			ConstantsSQL.PAGAMENTS_CAMP_IDFORMAPAGAMENT + ", " +
			ConstantsSQL.PAGAMENTS_CAMP_DATA + 
			" FROM " + ConstantsSQL.PAGAMENTS_TAULA +
			" ORDER BY " + ConstantsSQL.PAGAMENTS_CAMP_DATA;
	
	private static final String SQL_SELECT_BY_ID = 
			"SELECT " + ConstantsSQL.PAGAMENTS_CAMP_ID + ", " + 
			ConstantsSQL.PAGAMENTS_CAMP_IDCOMANDA + ", " +
			ConstantsSQL.PAGAMENTS_CAMP_QUANTITAT +  ", " +
			ConstantsSQL.PAGAMENTS_CAMP_IDFORMAPAGAMENT + ", " +
			ConstantsSQL.PAGAMENTS_CAMP_DATA + 
			" FROM " + ConstantsSQL.PAGAMENTS_TAULA +
			" WHERE " + ConstantsSQL.PAGAMENTS_CAMP_ID + " = ?";

	DBWrapper dw;
	
	/**
	 * Constructor de la classe 
	 * @param ds DataSource a usar per connectar-se a base de dades
	 */
	public PagamentDAO(DataSource ds) {
		dw = new DBWrapper(ds);
	}

	/**
	 * Dóna d'alta un registre a base de dades.
	 * Actualitzarà l'id amb el valor generat pel gestor
	 * @param r Pagament a donar d'alta
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void alta(final Pagament p) throws SQLException {
		dw.executeSql(
			SQL_ALTA, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1,  p.getnIdComanda());
					st.setFloat(2, p.getQuantitat());
					st.setLong(3,  p.getIdFormaPagament());
					//st.setDate(4,  (Date) p.getData());
					st.setTimestamp(4, DBWrapper.getParameterFromDate(p.getData()));
					
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
					p.setnId(keys.getLong(1));
				}
			}
		);
	}

	/**
	 * Esborra un Pagament de base de dades
	 * @param id Identificador del Pagament a esborrar 
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
	 * Modifica un Pagament a base de dades
	 * @param p El Pagament a modificarà. Usarà l'id
	 * per buscar el registre i actualitzarà tots els seus camps
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public void modificar(final Pagament p) throws SQLException {
		dw.executeSql(
			SQL_MODIFICAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1,  p.getnIdComanda());
					st.setFloat(2, p.getQuantitat());
					st.setLong(3,  p.getIdFormaPagament());
					st.setTimestamp(4,(Timestamp) p.getData());
					st.setLong(5, p.getnId());
				}
			}
		);	
	}

	/**
	 * Recupera un Pagament per id
	 * @param id del Pagament a recuperar
	 * @return El pagament si es troba, o null si no existeis
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public Pagament obtenirPerId(final long id) throws SQLException {
		final Pagament elPagament = new Pagament();
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
					crearPagament(rset, elPagament);
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return false;
				}
			}
		);
		return elPagament.getnId() == null ? null : elPagament;
	}

	/**
	 * Recupera de base de dades tots els Pagament ordenats per data
	 * @return La llista de Pagament. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public List<Pagament> obtenirTots() throws SQLException {
		final List<Pagament> elsPagament = new Vector<Pagament>();
		dw.executeQuery(
			SQL_SELECT_ALL, 
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					elsPagament.add(crearPagament(rset));
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return true;
				}
			}
		);
		return elsPagament;
	}

	/**
	 * Funció d'utilitat per crear un objecte Pagament a partir de la fila actual
	 * d'un recordset 
	 * @param rset El recordset d'on treurem el Pagament
	 * @return El Pagament creat
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	protected Pagament crearPagament(ResultSet rset) throws SQLException {
		return crearPagament(rset, null);
	}

	/**
	 * Funció d'utilitat per omplir un objecte Pagament amb els valors
	 * de la fila actual d'un recordset
	 * @param rset El recordset d'on treurem el Pagament
	 * @param r El Pagament a omplir. Pot ser null i es crearà un Pagament nou
	 * @return El Pagament amb els camps recuperats
	 * @throws SQLException El recordset d'on treurem el Pagament
	 */
	protected Pagament crearPagament(ResultSet rset, Pagament p) throws SQLException {
		// Si no em passen rol, el creo
		if (p == null) {
			p = new Pagament();
		}
		// Omplo el rol desde el recordset
		
		p.setnId(rset.getLong(ConstantsSQL.PAGAMENTS_CAMP_ID));
		p.setnIdComanda(rset.getLong(ConstantsSQL.PAGAMENTS_CAMP_IDCOMANDA));
		p.setQuantitat(rset.getFloat(ConstantsSQL.PAGAMENTS_CAMP_QUANTITAT));
		p.setIdFormaPagament(rset.getLong(ConstantsSQL.PAGAMENTS_CAMP_IDFORMAPAGAMENT));
		p.setData(rset.getTimestamp(ConstantsSQL.PAGAMENTS_CAMP_DATA));
		// Retorno l'opbjecte recuperat
		return p;
	}
}


