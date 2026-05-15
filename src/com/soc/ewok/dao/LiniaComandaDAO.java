package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.soc.ewok.controller.EWokController;
import com.soc.ewok.model.Comanda;
import com.soc.ewok.model.EEstatComanda;
import com.soc.ewok.model.EEstatLiniaComanda;
import com.soc.ewok.model.LiniaComanda;
import com.soc.ewok.model.Producte;
import com.soc.ewok.model.TipusProducte;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;


/**
 * DAO per a la classe {@link LiniaComanda }
 * @author JordiF
 *
 */
public class LiniaComandaDAO {

	/** Query per esborrar un registre per id */
	private static final String SQL_ESBORRAR =
			"DELETE FROM " + ConstantsSQL.LINIACOMANDA_TAULA + 
			" WHERE " + ConstantsSQL.LINIACOMANDA_CAMP_ID + " = ?" +
			" AND " + ConstantsSQL.LINIACOMANDA_CAMP_NUMLINIA + "= ?" ;
	
	/** Query per donar d'alta un LiniaComanda amb tots els seus camps */
	private static final String SQL_ALTA = 
			"INSERT INTO " + ConstantsSQL.LINIACOMANDA_TAULA + 
			"(" + ConstantsSQL.LINIACOMANDA_CAMP_ID + "," +
			ConstantsSQL.LINIACOMANDA_CAMP_NUMLINIA + "," +
			ConstantsSQL.LINIACOMANDA_CAMP_IDPRODUCTE + "," +
			ConstantsSQL.LINIACOMANDA_CAMP_QUANTITAT + "," +
			ConstantsSQL.LINIACOMANDA_CAMP_PREUBRUT + "," +
			ConstantsSQL.LINIACOMANDA_CAMP_PREUVENDA + "," +
			ConstantsSQL.LINIACOMANDA_CAMP_ESTAT + ")" +
			" VALUES (?,?,?,?,?,?,?)";
	
	/** Query per modificar tots els camps d'un LiniaComanda per id */
	private static final String SQL_MODIFICAR = 
			"UPDATE " + ConstantsSQL.LINIACOMANDA_TAULA +
			" SET "+
			ConstantsSQL.LINIACOMANDA_CAMP_IDPRODUCTE + " = ?," +
			ConstantsSQL.LINIACOMANDA_CAMP_QUANTITAT + " = ?," +
			ConstantsSQL.LINIACOMANDA_CAMP_PREUBRUT + " = ?," +
			ConstantsSQL.LINIACOMANDA_CAMP_PREUVENDA + " = ?," +
			ConstantsSQL.LINIACOMANDA_CAMP_ESTAT + " = ?" +
			" WHERE " + ConstantsSQL.LINIACOMANDA_CAMP_ID + " = ?" +
			" AND " + ConstantsSQL.LINIACOMANDA_CAMP_NUMLINIA + " = ?";

	/** Query per obtenir tots els LiniaComanda de base de dades */
	private static final String SQL_SELECT_ALL = 
			"SELECT *, " + ConstantsSQL.LINIACOMANDA_CAMP_ESTAT + " as " +
			ConstantsSQL.LINIACOMANDA_CAMP_ALIAS_ESTAT + " FROM " + 
			ConstantsSQL.LINIACOMANDA_TAULA +
			" ORDER BY " + ConstantsSQL.LINIACOMANDA_CAMP_ID + ", "
			+ ConstantsSQL.LINIACOMANDA_CAMP_NUMLINIA;
	
	/** Query per obtenir un LiniaComanda per id */
	private static final String SQL_SELECT_BY_ID = 
			"SELECT *, " + ConstantsSQL.LINIACOMANDA_CAMP_ESTAT + " as " +
			ConstantsSQL.LINIACOMANDA_CAMP_ALIAS_ESTAT + " FROM " + 
			ConstantsSQL.LINIACOMANDA_TAULA +
			" WHERE " + ConstantsSQL.LINIACOMANDA_CAMP_ID + " = ?" +
			" AND " + ConstantsSQL.LINIACOMANDA_CAMP_NUMLINIA + " = ?";
	
	/** Query per obtenir LiniaComanda de comandes per estat de comanda **/
	private static final String SQL_SELECT_COMANDAESTAT = 
					"SELECT LC. *, P.*, C.*, LC." + 
					ConstantsSQL.LINIACOMANDA_CAMP_ESTAT + " as " +
					ConstantsSQL.LINIACOMANDA_CAMP_ALIAS_ESTAT  
					+ " FROM " +	
					ConstantsSQL.LINIACOMANDA_TAULA + " LC" +
					" JOIN " + ConstantsSQL.COMANDA_TAULA + " C" +
					" ON LC." + ConstantsSQL.LINIACOMANDA_CAMP_ID + " = C." + ConstantsSQL.COMANDA_CAMP_ID +
					" JOIN " + ConstantsSQL.PRODUCTE_TAULA + " P" +
					" ON LC." + ConstantsSQL.LINIACOMANDA_CAMP_IDPRODUCTE + " = P." + ConstantsSQL.PRODUCTE_CAMP_ID +
					" JOIN " + ConstantsSQL.TIPUSPRODUCTE_TAULA + " TP" +
					" ON P." + ConstantsSQL.PRODUCTE_CAMP_ID_TIPUS_PRODUCTE + " = TP." + ConstantsSQL.TIPUSPRODUCTE_CAMP_ID +
					" WHERE " + 
					"(C." + ConstantsSQL.COMANDA_CAMP_ESTAT + " = ? OR C." + ConstantsSQL.COMANDA_CAMP_ESTAT + " = ?) AND " +
					"(LC." + ConstantsSQL.LINIACOMANDA_CAMP_ESTAT + " = ? OR LC." + ConstantsSQL.LINIACOMANDA_CAMP_ESTAT + " = ?) AND " +
					"(TP." + ConstantsSQL.TIPUSPRODUCTE_CAMP_CODI + " = ? OR TP." + ConstantsSQL.TIPUSPRODUCTE_CAMP_CODI + " = ?)" +
					" ORDER BY C." + ConstantsSQL.COMENTARI_CAMP_DATA + " ASC";
	
	/** Query per obtenir les Linies de Comanda per estat **/
	private static final String SQL_SELECT_ALL_BY_ID_ESTAT_ENTREGAT = 
			"SELECT * FROM " + ConstantsSQL.LINIACOMANDA_TAULA +
			" WHERE " + ConstantsSQL.LINIACOMANDA_CAMP_ALIAS_ESTAT + " = ? AND " + 
			ConstantsSQL.LINIACOMANDA_CAMP_ID + " = ? ";

	
	DBWrapper dw;
	
	/**
	 * Constructor de la classe {@link LiniaComanda}
	 * @param ds objecte Datasource per poder conectar a Base de Dades
	 */
	public LiniaComandaDAO(DataSource ds) {
		
		dw = new DBWrapper(ds);
	}
	
	
	/**
	 * Dóna d'alta un registre de la taula LiniaComanda de la BD
	 * Actualitzarà l'id amb el valor generat pel gestor
	 * @param l objecte LiniaComanda a donar d´alta
	 * @throws SQLException es llençara en cas d´error de conexió a BD
	 */
	public void alta(final LiniaComanda l) throws SQLException {
		alta(l, null);
	}
	
	public void alta(final LiniaComanda l, Connection con) throws SQLException {
		dw.executeSql(
			SQL_ALTA, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					if (l.getId()!=null) {
						st.setLong(1,l.getId());
						} else  {
							st.setNull(1, Types.INTEGER);
						}
					if (l.getLinia()!=null) {
						st.setLong(2, l.getLinia());
						} else {
							st.setNull(2, Types.INTEGER);
						}
					if (l.getIdProducte()!=null) {
						st.setLong(3, l.getIdProducte());
						} else {
							st.setNull(3, Types.INTEGER);
						}
					if (l.getQuantitat()!=null) {
						st.setInt(4, l.getQuantitat());
						} else {
							st.setNull(4, Types.INTEGER);
						}
					if (l.getPreuBrut()!=null) {
						st.setFloat(5, l.getPreuBrut());
						} else {
							st.setNull(5, Types.FLOAT);
						}
					if (l.getPreuVenda()!=null) {
						st.setFloat(6, l.getPreuVenda());
						} else {
							st.setNull(6, Types.FLOAT);
						}
					if (l.getEstat()!=null) {
						st.setInt(7, l.getEstat().ordinal());
						} else {
							st.setNull(7, Types.INTEGER);
						}
					
				}
			},
			null,
			con
		);
	}
	
	/**
	 * Esborra un registre de la taula LiniaComanda de la BD
	 * @param id Identificador del LiniaComanda a Esborrar
	 * @throws SQLException es llençara en cas d´error de conexió a BD
	 */
	public void esborrar(final long id,final long id2) throws SQLException {
		dw.executeSql(
			SQL_ESBORRAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1, id);
					st.setLong(2, id2);
				}
			}
		);	
	}
	
	/** 
	 * Modifica un registre de la taula LiniaComanda de la BD
	 * @param l. objecte LiniaComanda a modificar. Usarà l'id
	 * per buscar el registre i actualitzarà tots els seus camps
	 * @throws SQLException Es llençara en cas d´error de conexió a BD
	 */
	public void modificar(final LiniaComanda l) throws SQLException {
		dw.executeSql(
			SQL_MODIFICAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1, l.getIdProducte());
					st.setInt(2, l.getQuantitat());
					st.setFloat(3, l.getPreuBrut());
					st.setFloat(4, l.getPreuVenda());
					st.setInt(5, l.getEstat().ordinal());
					st.setLong(6,l.getId());
					st.setLong(7, l.getLinia());

				}
			}
		);	
	}
	
	/**
	 * Recupera un objecte LiniaComanda per id
	 * @param id de l´objecte Linia Comanda a recuperar
	 * @param id2 Numero de linia
	 * @return l´objecte si es troba, o null si no existeix
	 * @throws SQLException Es llençara en cas d´error de conexió a BD
	 */
	public LiniaComanda obtenirPerId(final long id,final long id2) throws SQLException {
		final LiniaComanda l = new LiniaComanda();
		dw.executeQuery(
			SQL_SELECT_BY_ID, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1, id);
					st.setLong(2, id2);
				}
			},
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a l'objecte que retornaré
					crearLiniaComanda(rset, l);
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return false;
				}
			}
		);
		return l.getId() == null ? null : l;
	}

	/**
	 * Recupera de base de dades tots els objectes LiniaComanda ordenats per LiniaComanda
	 * @return La llista de LiniaComanda. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException Es llençara en cas d´error de conexió a BD
	 */
	public List<LiniaComanda> obtenirTots() throws SQLException {
		final List<LiniaComanda> ls = new Vector<LiniaComanda>();
		dw.executeQuery(
			SQL_SELECT_ALL, 
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					ls.add(crearLiniaComanda(rset));
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return true;
				}
			}
		);
		return ls;
	}
	
	/**
	 * Funció d'utilitat per crear un objecte LiniaComanda a partir de la fila actual
	 * d'un recordset 
	 * @param rset El recordset d'on treurem l´objecte LiniaComanda
	 * @return l´objecte LiniaComanda creat
	 * @throws SQLException es llençara en cas que es produeixi un error de conexió a BD
	 */
	protected LiniaComanda crearLiniaComanda(ResultSet rset) throws SQLException {
		return crearLiniaComanda(rset, null);
	}
	
	/**
	 * Funció d'utilitat per crear un objecte LiniaComanda a partir de la fila actual
	 * d'un recordset 
	 * @param rset El recordset d'on treurem l´objecte LiniaComanda
	 * @param l L'objecte LiniaComanda
	 * @return l´objecte LiniaComanda creat
	 * @throws SQLException es llençara en cas que es produeixi un error de conexió a BD
	 */
	protected LiniaComanda crearLiniaComanda(ResultSet rset, LiniaComanda l) throws SQLException{
		// Si no em passen l´objecte LiniaComanda, el creo
		if (l == null) {
			l = new LiniaComanda();
		}
		// Omplo l´objecte LiniaComanda desde el recordset
		l.setId(rset.getLong(ConstantsSQL.LINIACOMANDA_CAMP_ID));
		l.setLinia(rset.getLong(ConstantsSQL.LINIACOMANDA_CAMP_NUMLINIA));
		l.setIdProducte(rset.getLong(ConstantsSQL.LINIACOMANDA_CAMP_IDPRODUCTE));
		l.setQuantitat(rset.getInt(ConstantsSQL.LINIACOMANDA_CAMP_QUANTITAT));
		l.setPreuBrut(rset.getFloat(ConstantsSQL.LINIACOMANDA_CAMP_PREUBRUT));
		l.setPreuVenda(rset.getFloat(ConstantsSQL.LINIACOMANDA_CAMP_PREUVENDA));
		l.setEstat(EEstatLiniaComanda.values()[rset.getInt(ConstantsSQL.LINIACOMANDA_CAMP_ALIAS_ESTAT)]);
		// Retorno l'objecte recuperat
		return l;
	}

	public List<LiniaComanda> obtenirLiniaComandaEstatComanda() throws SQLException {
				final List<LiniaComanda> ls = new Vector<LiniaComanda>();
				
				dw.executeQuery(
					SQL_SELECT_COMANDAESTAT ,
					new IPrepareStatement() {
						@Override
						public void prepareParams(
								Connection con, 
								PreparedStatement st) throws SQLException {
							st.setInt(1, EEstatComanda.validada.ordinal());
							st.setInt(2, EEstatComanda.pagada.ordinal());
							st.setInt(3, EEstatLiniaComanda.inicial.ordinal());
							st.setInt(4, EEstatLiniaComanda.enPreparacio.ordinal());
							st.setString(5, TipusProducte.CODI_PLAT);
							st.setString(6, TipusProducte.CODI_PLAT0);
						}
					},
					new GenericExecuteQueryProcess() {
						@Override
						public boolean processRow(Connection con, PreparedStatement st,
								ResultSet rset) throws SQLException {
							// Obtinc el registre de la fila actual
							// i el fico a la llista
							
							// Creo la linia de comanda
							LiniaComanda l = crearLiniaComanda(rset);   
							// Creo el producte 
							ProducteDAO pDAO = new ProducteDAO(EWokController.getGlobalDatasource());
							Producte p = pDAO.crearProducteLinia(rset);
							// Creo la comanda
							ComandaDAO cDAO = new ComandaDAO(EWokController.getGlobalDatasource());
							Comanda c = cDAO.crearComandaLinia(rset);
							// L'afegeixo a la linia de comanda
							l.setComanda(c);
							l.setProducte(p);
														
							// Afegeixo la Linia de comanda a la lista
							ls.add(l);
							// Indico al DBWrapper que segueixi amb el 
							// següent registre
							return true;
						}
					}
				);
				return ls;
	}
	
	
	// SQL_SELECT_ALL_BY_ESTAT
	public List<LiniaComanda> obtenirLiniesComandaEstatEntregat( final Long idComanda) throws SQLException {
		final List<LiniaComanda> ls = new Vector<LiniaComanda>();
		
		dw.executeQuery(
				SQL_SELECT_ALL_BY_ID_ESTAT_ENTREGAT ,
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1, idComanda);
					st.setInt(2, EEstatLiniaComanda.entregat.ordinal());
				}
			},
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					
					// Creo la linia de comanda
					LiniaComanda l = crearLiniaComanda(rset);  
					// Afegeixo la Linia de comanda a la lista
					ls.add(l);
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return true;
				}
			}
		);
		return ls;
	}
}



	


