package com.soc.ewok.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.soc.ewok.model.Components;
import com.soc.ewok.model.Preu;
import com.soc.ewok.model.Producte;
import com.soc.ewok.model.ProducteComponent;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

/**
 * Classe d'utilitats per a gestionar les operacions CRUD per a l'entitat
 * Producte i entitats relacionades
 * 
 * @author JordiM
 *
 */
public class ProducteDAO {

	/** alias per a taules per construir queries */
	private static final String ALIAS_TCOMPONENT = "co";
	private static final String ALIAS_TPRODCOMPONENT = "pc";
	private static final String ALIAS_TPRODUCTES = "p";

	/** Arrays amb noms de camps de les Entitats més usades */
	private static final String[] PRODUCTE_CAMPS = {
			ConstantsSQL.PRODUCTE_CAMP_ID, ConstantsSQL.PRODUCTE_CAMP_NOM,
			ConstantsSQL.PRODUCTE_CAMP_DESCRIPCIO_CURTA,
			ConstantsSQL.PRODUCTE_CAMP_DESCRIPCIO,
			ConstantsSQL.PRODUCTE_CAMP_INICI_VIGENCIA,
			ConstantsSQL.PRODUCTE_CAMP_FI_VIGENCIA,
			ConstantsSQL.PRODUCTE_CAMP_ID_UNITAT,
			ConstantsSQL.PRODUCTE_CAMP_ID_TIPUS_PRODUCTE };
	private static final String[] COMPONENT_CAMPS = {
			ConstantsSQL.COMPONENTS_CAMP_QUANTITAT,
			ConstantsSQL.COMPONENTS_CAMP_ORDRE };

	/** Query per esborrar un registre per id */
	private static final String SQL_ESBORRAR = "DELETE FROM "
			+ ConstantsSQL.PRODUCTE_TAULA + " WHERE "
			+ ConstantsSQL.PRODUCTE_CAMP_ID + " = ?";

	/** Query per donar d'alta un producte amb tots els seus camps */
	private static final String SQL_ALTA = "INSERT INTO "
			+ ConstantsSQL.PRODUCTE_TAULA + " ("
			+ ConstantsSQL.PRODUCTE_CAMP_NOM + ", "
			+ ConstantsSQL.PRODUCTE_CAMP_DESCRIPCIO_CURTA + ", "
			+ ConstantsSQL.PRODUCTE_CAMP_DESCRIPCIO + ", "
			+ ConstantsSQL.PRODUCTE_CAMP_INICI_VIGENCIA + ", "
			+ ConstantsSQL.PRODUCTE_CAMP_FI_VIGENCIA + ", "
			+ ConstantsSQL.PRODUCTE_CAMP_ID_UNITAT + ", "
			+ ConstantsSQL.PRODUCTE_CAMP_ID_TIPUS_PRODUCTE + ")"
			+ " VALUES (?,?,?,?,?,?,?)";

	/** Query per modificar tots els camps d'un producte per id */
	private static final String SQL_MODIFICAR = "UPDATE "
			+ ConstantsSQL.PRODUCTE_TAULA + " SET "
			+ ConstantsSQL.PRODUCTE_CAMP_NOM + " = ?, "
			+ ConstantsSQL.PRODUCTE_CAMP_DESCRIPCIO_CURTA + " = ?, "
			+ ConstantsSQL.PRODUCTE_CAMP_DESCRIPCIO + " = ?, "
			+ ConstantsSQL.PRODUCTE_CAMP_INICI_VIGENCIA + " = ?, "
			+ ConstantsSQL.PRODUCTE_CAMP_FI_VIGENCIA + " = ?, "
			+ ConstantsSQL.PRODUCTE_CAMP_ID_UNITAT + " = ?, "
			+ ConstantsSQL.PRODUCTE_CAMP_ID_TIPUS_PRODUCTE + " = ? "
			+ " WHERE " + ConstantsSQL.PRODUCTE_CAMP_ID + " = ?";

	/** Query per obtenir tots els productes de base de dades */
	private static final String SQL_SELECT_ALL = "SELECT "
			+ ConstantsSQL.PRODUCTE_CAMP_ID + ", "
			+ ConstantsSQL.PRODUCTE_CAMP_NOM + ", "
			+ ConstantsSQL.PRODUCTE_CAMP_DESCRIPCIO_CURTA + ", "
			+ ConstantsSQL.PRODUCTE_CAMP_DESCRIPCIO + ", "
			+ ConstantsSQL.PRODUCTE_CAMP_INICI_VIGENCIA + ", "
			+ ConstantsSQL.PRODUCTE_CAMP_FI_VIGENCIA + ", "
			+ ConstantsSQL.PRODUCTE_CAMP_ID_UNITAT + ", "
			+ ConstantsSQL.PRODUCTE_CAMP_ID_TIPUS_PRODUCTE + " FROM "
			+ ConstantsSQL.PRODUCTE_TAULA + " ORDER BY "
			+ ConstantsSQL.PRODUCTE_CAMP_NOM;

	/** Query per obtenir un producte per id */
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM "
			+ ConstantsSQL.PRODUCTE_TAULA + " WHERE "
			+ ConstantsSQL.PRODUCTE_CAMP_ID + " = ?";

	/** Query per obtenir tots els productes D'UN TIPUS de base de dades */
	private static final String SQL_SELECT_ALL_BY_TYPE = "SELECT * FROM "
			+ ConstantsSQL.PRODUCTE_TAULA + " WHERE "
			+ ConstantsSQL.PRODUCTE_CAMP_ID_TIPUS_PRODUCTE + " = ?"
			+ " ORDER BY " + ConstantsSQL.PRODUCTE_CAMP_NOM;

	/**
	 * Query per obtenir tots els productes D'UN TIPUS de base de dades i que
	 * estiguin VIGENTS
	 */
	private static final String SQL_SELECT_ALL_BY_TYPE_CURRENT = "SELECT "
			+ getCampsSelectQualificat(ALIAS_TPRODUCTES, PRODUCTE_CAMPS)
			+ ", tp." + ConstantsSQL.TIPUSPRODUCTE_CAMP_CODI + ", pr."
			+ ConstantsSQL.PREU_CAMP_PREU + " FROM "
			+ ConstantsSQL.PRODUCTE_TAULA + " " + ALIAS_TPRODUCTES
			+ " LEFT JOIN " + ConstantsSQL.PREU_TAULA + " pr" + " ON "
			+ ALIAS_TPRODUCTES + "." + ConstantsSQL.PRODUCTE_CAMP_ID + "=pr."
			+ ConstantsSQL.PREU_CAMP_IDPRODUCTE + " LEFT JOIN "
			+ ConstantsSQL.TIPUSPRODUCTE_TAULA + " tp" + " ON "
			+ ALIAS_TPRODUCTES + "."
			+ ConstantsSQL.PRODUCTE_CAMP_ID_TIPUS_PRODUCTE + "=tp."
			+ ConstantsSQL.TIPUSPRODUCTE_CAMP_ID + " WHERE tp."
			+ ConstantsSQL.TIPUSPRODUCTE_CAMP_CODI + " = ?" + 
			" ORDER BY p." + ConstantsSQL.PRODUCTE_CAMP_NOM;

	/** Query per obtenir un producte compost i tots els seus components */
	private static final String SQL_SELECT_PRODUCTE_COMPONENTS = "SELECT "
			+ getCampsSelectQualificat(ALIAS_TPRODUCTES, PRODUCTE_CAMPS) + ", "
			+ getCampsSelectQualificatAlias(ALIAS_TPRODCOMPONENT, PRODUCTE_CAMPS) + ", "
			+ getCampsSelectQualificat(ALIAS_TCOMPONENT, COMPONENT_CAMPS) + 
			", pr." + ConstantsSQL.PREU_CAMP_PREU + 
			" FROM " + ConstantsSQL.PRODUCTE_TAULA + " " + ALIAS_TPRODUCTES +
			" LEFT JOIN " + ConstantsSQL.COMPONENTS_TAULA + " " +
			ALIAS_TCOMPONENT + " ON " + ALIAS_TPRODUCTES + "." +
			ConstantsSQL.PRODUCTE_CAMP_ID + "=" + ALIAS_TCOMPONENT + "." +
			ConstantsSQL.COMPONENTS_CAMP_IDPRODUCTE + 
			" LEFT JOIN " + ConstantsSQL.PRODUCTE_TAULA + " " + ALIAS_TPRODCOMPONENT +
			" ON " + ALIAS_TCOMPONENT + "." + ConstantsSQL.COMPONENTS_CAMP_IDCOMPONENT +
			"=" + ALIAS_TPRODCOMPONENT + "." + ConstantsSQL.PRODUCTE_CAMP_ID + 
			" LEFT JOIN " + ConstantsSQL.PREU_TAULA + " pr" + " ON " +
			ALIAS_TPRODCOMPONENT + "." + ConstantsSQL.PRODUCTE_CAMP_ID +
			"=pr." + ConstantsSQL.PREU_CAMP_IDPRODUCTE + 
			" WHERE " + ALIAS_TPRODUCTES + "." + ConstantsSQL.PRODUCTE_CAMP_ID +
			"= ? " + "ORDER BY " + ALIAS_TCOMPONENT + "."
			+ ConstantsSQL.COMPONENTS_CAMP_ORDRE;

	/** Query per esborrar els components de un producte compost */
	private static final String SQL_ESBORRA_COMPONENTS_UNPRODUCTE_BY_ID = "DELETE FROM "
			+ ConstantsSQL.COMPONENTS_TAULA
			+ " WHERE "
			+ ConstantsSQL.COMPONENTS_CAMP_IDPRODUCTE + " = ?";

	/** Query per inserir els components d'un producte amb tots els seus camps */
	private static final String SQL_ALTA_COMPONENTS = "INSERT INTO "
			+ ConstantsSQL.COMPONENTS_TAULA + " ("
			+ ConstantsSQL.COMPONENTS_CAMP_IDPRODUCTE + ", "
			+ ConstantsSQL.COMPONENTS_CAMP_IDCOMPONENT + ", "
			+ ConstantsSQL.COMPONENTS_CAMP_QUANTITAT + ", "
			+ ConstantsSQL.COMPONENTS_CAMP_ORDRE + ")" + " VALUES (?,?,?,?)";

	/** Query per inserir el preu d'un producte amb tots els seus camps */
	private static final String SQL_ALTA_PREU = "INSERT INTO "
			+ ConstantsSQL.PREU_TAULA + " (" + ConstantsSQL.PREU_CAMP_PREU
			+ ", " + ConstantsSQL.PREU_CAMP_INICIVIGENCIA + ", "
			+ ConstantsSQL.PREU_CAMP_FINALVIGENCIA + ", "
			+ ConstantsSQL.PREU_CAMP_IDPRODUCTE + ")" + " VALUES (?,?,?,?)";

	// //////////////////////////////////////////////////////////////////////////////////////
	// VAR MEMBRE
	DBWrapper dw;

	/**
	 * Constructor de la classe
	 * 
	 * @param ds
	 *            DataSource a usar per connectar-se a base de dades
	 */
	public ProducteDAO(DataSource ds) {
		dw = new DBWrapper(ds);
	}

	/**
	 * Dóna d'alta un registre a base de dades. Actualitzarà l'id amb el valor
	 * generat pel gestor
	 * 
	 * @param p
	 *            Producte a donar d'alta
	 * @throws SQLException
	 *             En cas que hi hagi un error de base de dades
	 */
	public void alta(final Producte p) throws SQLException {
		dw.executeSql(SQL_ALTA, new IPrepareStatement() {
			@Override
			public void prepareParams(Connection con, PreparedStatement st)
					throws SQLException {
				st.setString(1, p.getNom());
				st.setString(2, p.getDescripcioCurta());
				st.setString(3, p.getDescripcio());
				st.setTimestamp(4,
						DBWrapper.getParameterFromDate(p.getIniciVigencia()));
				st.setTimestamp(5,
						DBWrapper.getParameterFromDate(p.getFiVigencia()));
				st.setLong(6, p.getIdUnitat());
				st.setLong(7, p.getIdTipusProducte());
			}
		}, new GenericExecuteQueryProcess() {
			@Override
			public void doPostProcess(Connection con, PreparedStatement st)
					throws SQLException {
				// Uso el recordset de camps generats
				// que em dona l'statement per assignar
				// l'id al Rol original
				ResultSet keys = st.getGeneratedKeys();
				keys.next();
				p.setId(keys.getLong(1));
			}
		});
	}

	/**
	 * Esborra un producte de la base de dades
	 * 
	 * @param id
	 *            Identificador del producte a esborrar
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 */
	public void esborrar(final long id) throws SQLException {
		dw.executeSql(SQL_ESBORRAR, new IPrepareStatement() {
			@Override
			public void prepareParams(Connection con, PreparedStatement st)
					throws SQLException {
				st.setLong(1, id);
			}
		});
	}

	/**
	 * Modifica un producte a la base de dades
	 * 
	 * @param p
	 *            El producte a modificar. Usarà l'id per buscar el registre i
	 *            actualitzarà tots els seus camps
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 */
	public void modificar(final Producte p) throws SQLException {
		dw.executeSql(SQL_MODIFICAR, new IPrepareStatement() {
			@Override
			public void prepareParams(Connection con, PreparedStatement st)
					throws SQLException {
				st.setString(1, p.getNom());
				st.setString(2, p.getDescripcioCurta());
				st.setString(3, p.getDescripcio());
				st.setTimestamp(4,
						DBWrapper.getParameterFromDate(p.getIniciVigencia()));
				st.setTimestamp(5,
						DBWrapper.getParameterFromDate(p.getFiVigencia()));
				st.setLong(6, p.getIdUnitat());
				st.setLong(7, p.getIdTipusProducte());
				st.setLong(8, p.getId());
			}
		});
	}

	/**
	 * Recupera un producte per id
	 * 
	 * @param id
	 *            del producte a recuperar
	 * @return El producte trobat o null si no existeix a la base de dades
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 */
	public Producte obtenirPerId(final long id) throws SQLException {
		final Producte elProducte = new Producte();
		dw.executeQuery(SQL_SELECT_BY_ID, new IPrepareStatement() {
			@Override
			public void prepareParams(Connection con, PreparedStatement st)
					throws SQLException {
				st.setLong(1, id);
			}
		}, new GenericExecuteQueryProcess() {
			@Override
			public boolean processRow(Connection con, PreparedStatement st,
					ResultSet rset) throws SQLException {
				// Obtinc el registre de la fila actual
				// i el fico a l'objecte que retornaré
				crearProducte(rset, elProducte);
				// Indico al DBWrapper que segueixi amb el
				// següent registre
				return false;
			}
		});
		return elProducte.getId() == null ? null : elProducte;
	}

	/**
	 * Recupera un producteCompost per id
	 * 
	 * @param id
	 *            del producte a recuperar
	 * @return El producte trobat o null si no existeix a la base de dades
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 * @author JordiM
	 */
	public Producte obtenirProducteCompostPerId(final long id)
			throws SQLException {
		final Producte elProducte = new Producte();
		dw.executeQuery(SQL_SELECT_PRODUCTE_COMPONENTS,
				new IPrepareStatement() {
					@Override
					public void prepareParams(Connection con,
							PreparedStatement st) throws SQLException {
						st.setLong(1, id);
						
					}
				}, new GenericExecuteQueryProcess() {
					@Override
					public boolean processRow(Connection con,
							PreparedStatement st, ResultSet rset)
							throws SQLException {
						// Si l'objecte no s'ha omplert ja:
						// Obtinc el registre de la fila actual
						// i el fico a l'objecte que retornaré
						if (elProducte.getId() == null) {
							crearProducte(rset, elProducte);
						}
						// si hi ha component l'afegim a l'usuari
						rset.getString(ALIAS_TCOMPONENT + "."
								+ ConstantsSQL.COMPONENTS_CAMP_QUANTITAT);

						if (rset.wasNull()) {
							// si no hi ha component cridem al mètode
							// addComponent amb null
							// així carregarà una llista de compnents buida
							// (obligatori)
							elProducte.addComponent(null);
						} else {
							// hi ha component -> l'obtenim amb el DAO i
							// l'afegim
							elProducte.addComponent(crearProducteComponent(
									rset, ALIAS_TPRODCOMPONENT));
						}
						// Indico al DBWrapper que segueixi amb el
						// següent registre
						return true;
					}
				});
		return elProducte.getId() == null ? null : elProducte;
	}

	/**
	 * Recupera de base de dades tots els productes ordenats per nom
	 * 
	 * @return La llista de productes. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 */
	public List<Producte> obtenirTots() throws SQLException {
		final List<Producte> elsProductes = new Vector<Producte>();
		dw.executeQuery(SQL_SELECT_ALL, new GenericExecuteQueryProcess() {
			@Override
			public boolean processRow(Connection con, PreparedStatement st,
					ResultSet rset) throws SQLException {
				// Obtinc el registre de la fila actual
				// i el fico a la llista
				elsProductes.add(crearProducte(rset));
				// Indico al DBWrapper que segueixi amb el
				// següent registre
				return true;
			}
		});
		return elsProductes;
	}

	/**
	 * Recupera de base de dades els productes filtrats per tipus de producte
	 * ordenats per nom
	 * 
	 * @return La llista de productes. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 * @author JordiM
	 */
	public List<Producte> obtenirTotsXTipusId(final long id)
			throws SQLException {
		final List<Producte> elsProductes = new Vector<Producte>();
		dw.executeQuery(SQL_SELECT_ALL_BY_TYPE, new IPrepareStatement() {
			@Override
			public void prepareParams(Connection con, PreparedStatement st)
					throws SQLException {
				st.setLong(1, id);
			}
		}, new GenericExecuteQueryProcess() {
			@Override
			public boolean processRow(Connection con, PreparedStatement st,
					ResultSet rset) throws SQLException {
				// Obtinc el registre de la fila actual
				// i el fico a la llista
				elsProductes.add(crearProducte(rset));
				// Indico al DBWrapper que segueixi amb el
				// següent registre
				return true;
			}
		});
		return elsProductes;
	}

	/**
	 * Recupera de base de dades els productes(amb camps extres) del tipus
	 * (codi) que son vigents ordenats per nom.
	 * 
	 * @param codi
	 *            Identificador dels tipus de producte a filtrar
	 * @return La llista de productes. Si no n'hi ha, retornarà una llista
	 *         buida. La llista està composada per objectes ProducteComponent
	 *         que deriven de Producte i incorporen més var membre
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 * @author JordiM
	 */
	public List<Producte> obtenirTotsXTipusIdVigents(final String codi)
			throws SQLException {
		final List<Producte> elsProductes = new Vector<Producte>();
		dw.executeQuery(SQL_SELECT_ALL_BY_TYPE_CURRENT,
				new IPrepareStatement() {
					@Override
					public void prepareParams(Connection con,
							PreparedStatement st) throws SQLException {
						st.setString(1, codi);
//						st.setTimestamp(2,
//								DBWrapper.getParameterFromDate(new Date()));
//						st.setTimestamp(3,
//								DBWrapper.getParameterFromDate(new Date()));
//						st.setTimestamp(4,
//								DBWrapper.getParameterFromDate(new Date()));
//						st.setTimestamp(5,
//								DBWrapper.getParameterFromDate(new Date()));
					}
				}, new GenericExecuteQueryProcess() {
					@Override
					public boolean processRow(Connection con,
							PreparedStatement st, ResultSet rset)
							throws SQLException {
						// Obtinc el registre de la fila actual
						// i el fico a la llista
						elsProductes.add(crearProducteComponent(rset));
						// Indico al DBWrapper que segueixi amb el
						// següent registre
						return true;
					}
				});
		return elsProductes;
	}

	/**
	 * métode per modificar les dades relacionades d'un producte compost
	 * eliminarà la relacio de components actuals per inserir les noves
	 * inserirà un nou preu relacionat amb el productecompost
	 * @param pr nou Preu a inserir
	 * @param lc llista de objectes Components a inserir
	 * @throws SQLException Error en la acció sql
	 * @author JordiM
	 */
	public void modificaProducteCompost(
			final Preu pr,
			final List<Components> lc)
					throws SQLException {

		// alta de preu
		dw.executeSql( 				//executeSql#1, inicia la transacció
				SQL_ALTA_PREU,
				new IPrepareStatement() {

					@Override
					public void prepareParams(
							Connection con,
							PreparedStatement st
							) throws SQLException {
						st.setFloat(1, pr.getPreu());
						st.setTimestamp(2, DBWrapper.getParameterFromDate(pr.getIniciVigencia()));
						st.setTimestamp(3, DBWrapper.getParameterFromDate(pr.getFinalVigencia()));
						st.setLong(4, pr.getIdProducte());
					}
				},
				new GenericExecuteQueryProcess() { //proc del executeSql#1
					
					@Override
					public void doPreProcess(
							Connection con,
							PreparedStatement st) //preProcess del executeSql#1 
							throws SQLException {
							
						// esborrat de components antics del producte compost
							dw.executeSql(								//executeSql#2 continua la transaccio ha de passar connexio
									SQL_ESBORRA_COMPONENTS_UNPRODUCTE_BY_ID,
									new IPrepareStatement() {
										@Override
										public void prepareParams(
												Connection con,
												PreparedStatement st) throws SQLException {
											st.setLong(1, pr.getIdProducte());
										}
									},
									new GenericExecuteQueryProcess() {
										// fer alta de tot els components.
										//aquestes altes no tenen retorn de clau id
										@Override
										public void doPostProcess(
												Connection con,
												PreparedStatement st) throws SQLException {
											for (final Components co : lc) {
												dw.executeSql(			//executeSql#3 continua la transaccio ha de passar connexio
														SQL_ALTA_COMPONENTS,
														new IPrepareStatement() {
															@Override
															public void prepareParams(
																	Connection con,
																	PreparedStatement st)
																	throws SQLException {
																st.setLong(1,
																		co.getIdProducte());
																st.setLong(2,
																		co.getIdComponent());
																st.setInt(3,
																		co.getQuantitat());
																st.setInt(4, co.getOrdre());
															}
														},
														null,
														con
												);
											}
										}
									},
									con
							);
					}
					@Override
					public void doPostProcess(Connection con, PreparedStatement st) //postProcess del executeSql#1 
							throws SQLException {
						// Uso el recordset de camps generats
						// que em dona l'statement per assignar
						// l'id al objecte original
						ResultSet keys = st.getGeneratedKeys();
						keys.next();
						pr.setId(keys.getLong(1));
					}
			},
			null		//con del executeSql#1
		);
	}

	/**
	 * Esborra els components d'un producte de la base de dades
	 * 
	 * @param id
	 *            Identificador del producte del qual s'ha d'esborrar els
	 *            components
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 */
//	public void esborrarComponents(final long id) throws SQLException {
//		dw.executeSql(SQL_ESBORRA_COMPONENTS_UNPRODUCTE_BY_ID,
//				new IPrepareStatement() {
//					@Override
//					public void prepareParams(Connection con,
//							PreparedStatement st) throws SQLException {
//						st.setLong(1, id);
//					}
//				});
//	}
//
	/**
	 * Dóna d'alta un registre a base de dades.
	 * 
	 * @param c
	 *            Components a donar d'alta
	 * @throws SQLException
	 *             En cas que hi hagi un error de base de dades
	 */
//	public void altaComponent(final Components c) throws SQLException {
//		dw.executeSql(SQL_ALTA_COMPONENTS, new IPrepareStatement() {
//			@Override
//			public void prepareParams(Connection con, PreparedStatement st)
//					throws SQLException {
//				st.setLong(1, c.getIdProducte());
//				st.setLong(2, c.getIdComponent());
//				st.setInt(3, c.getQuantitat());
//				st.setInt(4, c.getOrdre());
//			}
//		});
//	}

	/**
	 * Dóna d'alta un registre a base de dades. Actualitzarà l'id amb el valor
	 * generat pel gestor
	 * 
	 * @param p
	 *            Preu a donar d'alta
	 * @throws SQLException
	 *             En cas que hi hagi un error de base de dades
	 */
//	public void altaPreu(final Preu p) throws SQLException {
//		dw.executeSql(SQL_ALTA_PREU, new IPrepareStatement() {
//			@Override
//			public void prepareParams(Connection con, PreparedStatement st)
//					throws SQLException {
//				st.setFloat(1, p.getPreu());
//				st.setTimestamp(2,
//						DBWrapper.getParameterFromDate(p.getIniciVigencia()));
//				st.setTimestamp(3,
//						DBWrapper.getParameterFromDate(p.getFinalVigencia()));
//				st.setLong(4, p.getIdProducte());
//			}
//		}, new GenericExecuteQueryProcess() {
//			@Override
//			public void doPostProcess(Connection con, PreparedStatement st)
//					throws SQLException {
//				// Uso el recordset de camps generats
//				// que em dona l'statement per assignar
//				// l'id al Rol original
//				ResultSet keys = st.getGeneratedKeys();
//				keys.next();
//				p.setId(keys.getLong(1));
//			}
//		});
//	}

	/**
	 * Funció d'utilitat per crear un objecte Producte a partir de la fila
	 * actual d'un resultset
	 * 
	 * @param rset
	 *            El resultset d'on treurem el Producte
	 * @return El Producte creat
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 */
	private Producte crearProducte(ResultSet rset) throws SQLException {
		// cas d'ús 1 . només em passen el resultset
		return crearProducte(rset, null, null);
	}

	/**
	 * Funció d'utilitat per crear un objecte Producte a partir de la fila
	 * actual d'un resultset
	 * 
	 * @param rset
	 *            El resultset d'on treurem el Producte
	 * @param p
	 *            El Producte a omplir amb les dades del resultset
	 * @return El Producte omplert
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 */
	private Producte crearProducte(ResultSet rset, Producte p)
			throws SQLException {
		// cas d'ús 2 . em passen el resultset i el producte a omplir amb les
		// dades del rset
		return crearProducte(rset, p, null);
	}

	/**
	 * Funció d'utilitat per omplir un objecte Producte amb els valors de la
	 * fila actual d'un resultset
	 * 
	 * @param rset
	 *            El resultset d'on treurem el Producte
	 * @param r
	 *            El Producte a omplir. Pot ser null i es crearà un Producte nou
	 * @param prefix
	 *            Prefix per a obtenir dades de la segona taula PRODUCTE en la
	 *            query SQL_SELECT_PRODUCTE_COMPONENTS
	 * @return El Producte amb els camps recuperats
	 * @throws SQLException
	 *             El resultset d'on treurem el Producte
	 * @author JordiM
	 */
	private Producte crearProducte(ResultSet rset, Producte p, String prefix)
			throws SQLException {
		// cas d'ús 3. em passen tots els paràmetres

		// Si no em passen Producte, el creo
		if (p == null) {
			p = new Producte();
		}

		// per als productes compostos usem dues taules Producte i en distingim
		// els camps amb el prefix "pc"
		// per als camps dels productes component. si no hi ha prefix és que
		// s'ha de crear el producte principal
		if (prefix == null)
			prefix = "";

		// Omplo el Producte desde el recordset
		p.setId(rset.getLong(prefix + ConstantsSQL.PRODUCTE_CAMP_ID));
		p.setNom(rset.getString(prefix + ConstantsSQL.PRODUCTE_CAMP_NOM));
		p.setDescripcioCurta(rset.getString(prefix
				+ ConstantsSQL.PRODUCTE_CAMP_DESCRIPCIO_CURTA));
		p.setDescripcio(rset.getString(prefix
				+ ConstantsSQL.PRODUCTE_CAMP_DESCRIPCIO));
		p.setIniciVigencia(rset.getTimestamp(prefix
				+ ConstantsSQL.PRODUCTE_CAMP_INICI_VIGENCIA));
		p.setFiVigencia(rset.getTimestamp(prefix
				+ ConstantsSQL.PRODUCTE_CAMP_FI_VIGENCIA));
		p.setIdUnitat(rset.getLong(prefix
				+ ConstantsSQL.PRODUCTE_CAMP_ID_UNITAT));
		p.setIdTipusProducte(rset.getLong(prefix
				+ ConstantsSQL.PRODUCTE_CAMP_ID_TIPUS_PRODUCTE));
		// Retorno l'objecte recuperat
		return p;
	}

	private Producte crearProducteComponent(ResultSet rset) {
		return crearProducteComponent(rset, null);
	}

	/**
	 * Funció d'utilitat per omplir un objecte ProducteComponent amb els valors
	 * de la fila actual d'un resultset
	 * 
	 * @param rset
	 *            El resultset d'on treurem el ProducteComponent
	 * @return El Producte amb els camps recuperats
	 * @throws SQLException
	 *             El resultset d'on treurem el Producte
	 * @author JordiM
	 */
	private ProducteComponent crearProducteComponent(ResultSet rset,
			String alias) {
		// creem un ProducteRelacions i l'assignem a un contenidor pare Producte
		// per poder usar els métodes del pare
		ProducteComponent pr = new ProducteComponent();

		// omplo amb les dades de producte
		try {
			crearProducte(rset, pr, alias);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// omplo les dades de la relació

		// preu
		try {
			rset.getFloat("pr." + ConstantsSQL.PREU_CAMP_PREU);
			if (!rset.wasNull()) {
				((ProducteComponent) pr).setPreu(rset.getFloat("pr."
						+ ConstantsSQL.PREU_CAMP_PREU));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// quantitat
		if (alias != null) {
			try {
				rset.getInt(ALIAS_TCOMPONENT + "."
						+ ConstantsSQL.COMPONENTS_CAMP_QUANTITAT);
				if (!rset.wasNull()) {
					((ProducteComponent) pr).setQuantitat(rset
							.getInt(ALIAS_TCOMPONENT + "."
									+ ConstantsSQL.COMPONENTS_CAMP_QUANTITAT));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// retorno l'objecte
		return pr;
	}

	/**
	 * Funcio d'utilitat que crea un producte a partir de la fila actual de un
	 * resultset
	 * 
	 * @param rset
	 *            El resultset d'on treurem les dades
	 * @retunr El Producte creat
	 * @throws SQLException
	 *             el resultset
	 */
	public Producte crearProducteLinia(ResultSet rset) {
		Producte p = new Producte();
		try {
			p = crearProducte(rset);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * Utilitat per a obtenir una cadena de camps d'una taula en format preparat
	 * per a un SELECT de forma que es qualifiquen els noms de camp amb alias de
	 * camp i de taula
	 * 
	 * @param qTaula
	 *            Alias de taula
	 * @param camps
	 *            Array amb els noms dels camps
	 * @return cadena amb els noms dels camps qualificats i amb alias:
	 *         qTaula.camp AS aliasCamp[, qTaula.camp AS aliasCamp, ..]
	 * @author JordiM
	 */
	private static String getCampsSelectQualificatAlias(String qTaula,
			String[] camps) {
		// 1.- obtenim els noms dels camps amb el prefix de taula (qTaula.camp)
		String[] campsTaula = getCampsambPrefix(camps, qTaula + ".");
		// 2.- obtenim els alias dels camps
		String[] Aliascamps = getCampsambPrefix(camps, qTaula);

		// muntem l'string de select en format (qTaula.camp AS aliasCamp)
		StringBuilder campselect = new StringBuilder();
		int i = 0;
		// afegim la primera linia
		campselect.append(campsTaula[i] + " AS " + Aliascamps[i]);
		for (i = 1; i < campsTaula.length; i++) {
			campselect.append(", " + campsTaula[i] + " AS " + Aliascamps[i]);
		}
		return campselect.toString();
	}

	/**
	 * Utilitat per obtenir una cadena de camps d'una taula en format preparat
	 * per a un SELECT de forma que es qualifiquen els noms de camp amb un
	 * prefix de taula
	 * 
	 * @param qTaula
	 *            Qualificador de taula per a posar com a prefix
	 * @param camps
	 *            Array de strings amb els noms dels camps
	 * @return Retorna un String amb els noms dels camps qualificats:
	 *         qTaula.camp[, qTaula.camp , qTaula.camp ..]
	 * @author JordiM
	 */
	private static String getCampsSelectQualificat(String qTaula, String[] camps) {
		// 1.- obtenim els noms dels camps amb el prefix de taula (qTaula.camp)
		String[] campsTaula = getCampsambPrefix(camps, qTaula + ".");

		// muntem l'string de select en format (qTaula.camp AS aliasCamp)
		StringBuilder campselect = new StringBuilder();
		int i = 0;
		// afegim la primera linia
		campselect.append(campsTaula[i]);
		for (i = 1; i < campsTaula.length; i++) {
			campselect.append(", " + campsTaula[i]);
		}
		return campselect.toString();
	}

	/**
	 * utilitat per afegir un prefix al nom dels camps del array rebut
	 * 
	 * @param ar
	 *            Array de Strings amb els noms dels camps
	 * @param pre
	 *            Prefix a afegir al nom del camp
	 * @return Un array de String amb els noms canviats
	 * @author JordiM
	 */
	private static String[] getCampsambPrefix(String[] ar, String pre) {
		String[] nou = new String[ar.length];
		int i = 0;
		for (String str : ar) {
			nou[i] = pre + str;
			i++;
		}
		return nou;
	}
}