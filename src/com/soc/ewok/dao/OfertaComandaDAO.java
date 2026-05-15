package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.sql.DataSource;
import com.soc.ewok.model.OfertaComanda;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

public class OfertaComandaDAO {
	/** Query per esborrar un registre per id */
	private static final String SQL_ESBORRAR =
			"DELETE FROM " + ConstantsSQL.OFERTACOMANDA_TAULA + 
			" WHERE " + ConstantsSQL.OFERTACOMANDA_CAMP_ID + " = ?";
	
	/** Query per donar d'alta un OfertaComanda amb tots els seus camps */
	private static final String SQL_ALTA = 
			"INSERT INTO " + ConstantsSQL.OFERTACOMANDA_TAULA + 
			" (" + 
			ConstantsSQL.OFERTACOMANDA_CAMP_LIMITAINFERIOR + ", " +
			ConstantsSQL.OFERTACOMANDA_CAMP_PCTDESCOMPTE + ", " + 
			ConstantsSQL.OFERTACOMANDA_CAMP_INICI_VIGENCIA + ", " +
			ConstantsSQL.OFERTACOMANDA_CAMP_FI_VIGENCIA +
			")" + 
			" VALUES (?,?,?,?)";
	
	/** Query per modificar tots els camps d'un OFERTACOMANDA per id */
	private static final String SQL_MODIFICAR = 
			"UPDATE " + ConstantsSQL.OFERTACOMANDA_TAULA + 
			" SET " + ConstantsSQL.OFERTACOMANDA_CAMP_LIMITAINFERIOR + " = ?, " +
			ConstantsSQL.OFERTACOMANDA_CAMP_PCTDESCOMPTE + " = ?, " +
			ConstantsSQL.OFERTACOMANDA_CAMP_INICI_VIGENCIA + " = ?, " +
			ConstantsSQL.OFERTACOMANDA_CAMP_FI_VIGENCIA + " = ?" +
			" WHERE " + ConstantsSQL.OFERTACOMANDA_CAMP_ID + " = ?";

	/** Query per obtenir tots els OfertaComanda de base de dades */
	private static final String SQL_SELECT_ALL = 
			"SELECT " + ConstantsSQL.OFERTACOMANDA_CAMP_ID + ", " + 
					ConstantsSQL.OFERTACOMANDA_CAMP_LIMITAINFERIOR + ", " +
					ConstantsSQL.OFERTACOMANDA_CAMP_PCTDESCOMPTE + ", " +
					ConstantsSQL.OFERTACOMANDA_CAMP_INICI_VIGENCIA + ", " +
					ConstantsSQL.OFERTACOMANDA_CAMP_FI_VIGENCIA +
			" FROM " + ConstantsSQL.OFERTACOMANDA_TAULA +
			" ORDER BY " + ConstantsSQL.OFERTACOMANDA_CAMP_ID;
	
	/** Query per obtenir un OfertaComanda per id */
	private static final String SQL_SELECT_BY_ID = 
			"SELECT " + ConstantsSQL.OFERTACOMANDA_CAMP_ID + ", " + 
						ConstantsSQL.OFERTACOMANDA_CAMP_LIMITAINFERIOR + ", " +
						ConstantsSQL.OFERTACOMANDA_CAMP_PCTDESCOMPTE + ", " +
						ConstantsSQL.OFERTACOMANDA_CAMP_INICI_VIGENCIA + ", " +
						ConstantsSQL.OFERTACOMANDA_CAMP_FI_VIGENCIA +
			" FROM " + ConstantsSQL.OFERTACOMANDA_TAULA +
			" WHERE " + ConstantsSQL.OFERTACOMANDA_CAMP_ID + " = ?";

	DBWrapper dw;

	/**
	 * Constructor de la classe 
	 * @param ds DataSource a usar per connectar-se a base de dades
	 */
	public OfertaComandaDAO(DataSource ds) {
		dw = new DBWrapper(ds);
	}

	/**
	 * Dóna d'alta un registre a base de dades.
	 * Actualitzarà l'id amb el valor generat pel gestor
	 * @param op OfertaComanda a donar d'alta
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void alta(final OfertaComanda op) throws SQLException {
		dw.executeSql(
			SQL_ALTA, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setFloat(1, op.getLimitInferior());
					st.setFloat(2, op.getPctDescompte());
					st.setTimestamp(3, DBWrapper.getParameterFromDate(op.getIniciVigencia()));
					st.setTimestamp(4, DBWrapper.getParameterFromDate(op.getFiVigencia()));
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
					op.setId(keys.getLong(1));
				}
			}
		);
	}

	/**
	 * Esborra un OfertaComanda de la base de dades
	 * @param id Identificador del OfertaComanda a esborrar 
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
	 * Modifica una OfertaComanda a la base de dades
	 * @param op La OfertaComanda a modificar. Usarà l'id
	 * per buscar el registre i actualitzarà tots els seus camps
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public void modificar(final OfertaComanda op) throws SQLException {
		dw.executeSql(
			SQL_MODIFICAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setFloat(1, op.getLimitInferior());
					st.setFloat(2, op.getPctDescompte());
					st.setTimestamp(3, DBWrapper.getParameterFromDate(op.getIniciVigencia()));
					st.setTimestamp(4, DBWrapper.getParameterFromDate(op.getFiVigencia()));
					st.setLong(5, op.getId());
				}
			}
		);	
	}
	
	/**
	 * Recupera un OfertaComanda per id
	 * @param id del OfertaComanda a recuperar
	 * @return El OfertaComanda trobat o null si no existeix a la base de dades
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public OfertaComanda obtenirPerId(final long id) throws SQLException {
		final OfertaComanda elOfertaComanda = new OfertaComanda();
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
					crearOfertaComanda(rset, elOfertaComanda);
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return false;
				}
			}
		);
		return elOfertaComanda.getId()==null ? null : elOfertaComanda;
	}

	/**
	 * Recupera de base de dades tots els OfertaComanda ordenats per nom
	 * @return La llista de OfertaComanda. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public List<OfertaComanda> obtenirTots() throws SQLException {
		final List<OfertaComanda> elsOfertaComanda = new Vector<OfertaComanda>();
		dw.executeQuery(
			SQL_SELECT_ALL, 
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					elsOfertaComanda.add(crearOfertaComanda(rset));
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return true;
				}
			}
		);
		return elsOfertaComanda;
	}

	/**
	 * Funció d'utilitat per crear un objecte OfertaComanda a partir de la fila actual
	 * d'un resultset 
	 * @param rset El resultset d'on treurem la OfertaComanda
	 * @return la OfertaComanda creada
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	private OfertaComanda crearOfertaComanda(ResultSet rset) throws SQLException {
		return crearOfertaComanda(rset, null);
	}

	/**
	 * Funció d'utilitat per omplir un objecte OfertaComanda amb els valors
	 * de la fila actual d'un resultset
	 * @param rset El resultset d'on treurem la OfertaComanda
	 * @param op la OfertaComanda a omplir. Pot ser null i es crearà un Producte nou
	 * @return la OfertaComanda amb els camps recuperats
	 * @throws SQLException El resultset d'on treurem el OfertaComanda
	 */
	private OfertaComanda crearOfertaComanda(ResultSet rset, OfertaComanda op) throws SQLException {
		// Si no em passen Producte, el creo
		if (op == null) {
			op = new OfertaComanda();
		}
		// Omplo la OfertaComanda desde el recordset
		op.setId(rset.getLong(ConstantsSQL.OFERTACOMANDA_CAMP_ID));
		op.setLimitInferior(rset.getFloat(ConstantsSQL.OFERTACOMANDA_CAMP_LIMITAINFERIOR));
		op.setPctDescompte(rset.getFloat(ConstantsSQL.OFERTACOMANDA_CAMP_PCTDESCOMPTE));
		op.setIniciVigencia(rset.getTimestamp(ConstantsSQL.OFERTACOMANDA_CAMP_INICI_VIGENCIA));
		op.setFiVigencia(rset.getTimestamp(ConstantsSQL.OFERTACOMANDA_CAMP_FI_VIGENCIA));
		// Retorno l'objecte recuperat
		return op;
	}
}


