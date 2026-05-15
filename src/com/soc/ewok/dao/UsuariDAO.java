package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;
import com.soc.ewok.model.Usuari;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

/**
 * DAO per a la classe {@link Usuari}
 * 
 * @author Francesc
 *
 */
public class UsuariDAO {

	// CONSTANTS
	/** query per esborrar un resgistre per eMail */
	private static final String SQL_ESBORRAR = "DELETE FROM "
			+ ConstantsSQL.USUARI_TAULA + " WHERE "
			+ ConstantsSQL.USUARI_CAMP_EMAIL + " = ?";

	/** Query per donar d'alta un usuari amb tots els seus camps */
	private static final String SQL_ALTA = "INSERT INTO "
			+ ConstantsSQL.USUARI_TAULA + "(" + ConstantsSQL.USUARI_CAMP_EMAIL
			+ ", " + ConstantsSQL.USUARI_CAMP_PASSWORD + ", "
			+ ConstantsSQL.USUARI_CAMP_ACTIU + ")" + " VALUES (?,?,?)";

	/** Query per modificar tots els camps d'un usuari per eMail */
	private static final String SQL_MODIFICAR = "UPDATE "
			+ ConstantsSQL.USUARI_TAULA + " SET "
			+ ConstantsSQL.USUARI_CAMP_PASSWORD + " = ? " + " WHERE "
			+ ConstantsSQL.USUARI_CAMP_ACTIU + " = ?";

	/** Query per obtenir tots els usuaris de base de dades */
	private static final String SQL_SELECT_ALL = "SELECT "
			+ ConstantsSQL.USUARI_CAMP_EMAIL + ", "
			+ ConstantsSQL.USUARI_CAMP_PASSWORD + ", "
			+ ConstantsSQL.USUARI_CAMP_ACTIU + " FROM "
			+ ConstantsSQL.USUARI_TAULA + " ORDER BY "
			+ ConstantsSQL.USUARI_CAMP_EMAIL;

	/** Query per obtenir un usuari i tots els seus rols */
	private static final String SQL_SELECT_BY_EMAIL_ROLS = "SELECT * FROM "
			+ ConstantsSQL.USUARI_TAULA + " u " + "LEFT JOIN "
			+ ConstantsSQL.ROL_USUARI_TAULA + " ru ON u."
			+ ConstantsSQL.USUARI_CAMP_EMAIL + "=" + "ru."
			+ ConstantsSQL.ROL_USUARI_CAMP_EMAIL + " LEFT JOIN "
			+ ConstantsSQL.ROL_TAULA + " r on ru."
			+ ConstantsSQL.ROL_USUARI_CAMP_ID + "=r."
			+ ConstantsSQL.ROL_CAMP_ID + " WHERE u."
			+ ConstantsSQL.USUARI_CAMP_EMAIL + " = ?";

	/** Query per esborrar les relacions rolusuari d'un usuari */
	private static final String SQL_ESBORRA_RELACIO_ROLUSUARI = "DELETE FROM "
			+ ConstantsSQL.ROL_USUARI_TAULA + " WHERE "
			+ ConstantsSQL.ROL_USUARI_CAMP_EMAIL + " = ?";

	// VARIABLES MEMBRE DE LA CLASSE
	DBWrapper dw;
	RolDAO rd;

	/**
	 * Constructor de la classe
	 * 
	 * @param ds
	 *            DataSource a usar per connectar-se a base de dades
	 */
	public UsuariDAO(DataSource ds) {
		dw = new DBWrapper(ds);
		rd = new RolDAO(ds);
	}

	/**
	 * Dóna d'alta un registre a base de dades.
	 * 
	 * @param u Usuari a donar d'alta
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void alta(final Usuari u) throws SQLException {
		dw.executeSql(SQL_ALTA, new IPrepareStatement() {
			@Override
			public void prepareParams(Connection con, PreparedStatement st)
					throws SQLException {
				st.setString(1, u.getMail());
				st.setString(2, u.getPassword());
				st.setBoolean(3, u.getActiu());
			}
		});
	}

	/**
	 * Esborra un usuari de base de dades
	 * 
	 * @param email
	 *            Identificador de l'usuari a esborrar
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 */
	public void esborrar(final String email) throws SQLException {
		// primer eliminem les relacions rolusuari i després l'usuari

		// esborrat de relacions rolusuari
		dw.executeSql(
				SQL_ESBORRAR,
				new IPrepareStatement() {
	
					@Override
					public void prepareParams(Connection con,
							PreparedStatement st) throws SQLException {
						st.setString(1, email);
					}
				},
				new GenericExecuteQueryProcess() {

					@Override
					public void doPreProcess(Connection con, PreparedStatement st) throws SQLException {
						// esborrat de usuari
						dw.executeSql(
								SQL_ESBORRA_RELACIO_ROLUSUARI,
								new IPrepareStatement() {
									@Override
									public void prepareParams(Connection con, PreparedStatement st)
											throws SQLException {
										st.setString(1, email);
									}
								},
								null,
								con
						);
					}
				},
				null
			);
	}

	/**
	 * Modifica un usuari a base de dades
	 * 
	 * @param u
	 *            , usuari a modificar. Usarà l'email per buscar el registre i
	 *            actualitzarà tots els seus camps
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 */
	public void modificar(final Usuari u) throws SQLException {
		dw.executeSql(SQL_MODIFICAR, new IPrepareStatement() {
			@Override
			public void prepareParams(Connection con, PreparedStatement st)
					throws SQLException {
				st.setString(1, u.getPassword());
				st.setBoolean(2, u.getActiu());
			}
		});
	}

	/**
	 * Recupera un usuari i tots els seus rols per l'id (email) a un Usuari
	 * sense rols se li ha d'assignar per defecte una llista buida
	 * 
	 * @param email
	 *            identificatiu de l'usuari a recuperar
	 * @return L'usuari i un llistat de Rols si es troba, o null si no existeix
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 */
	public Usuari obtenirPerEmail(final String email) throws SQLException {
		final Usuari us = new Usuari();

		//preparem la query amb l'id i l'executem 
		dw.executeQuery(
				SQL_SELECT_BY_EMAIL_ROLS, 
			//inserim l'id a l'statement
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setString(1, email);
				}
			},
				// processem el resultat
				new GenericExecuteQueryProcess() {
					@Override
					public boolean processRow(Connection con,
							PreparedStatement st, ResultSet rset)
							throws SQLException {
						// Si l'usuari no s'ha omplert ja:
						// Obtinc el registre de la fila actual
						// i el fico a l'objecte que retornaré
						if (us.getMail() == null) {
							crearUsuari(rset, us);
						}
						// si hi ha rol l'afegim a l'usuari
						rset.getLong(ConstantsSQL.ROL_CAMP_ID); // obtenim id si
																// n'hi ha
						if (rset.wasNull()) {
							// si no hi ha rol cridem al mètode addRol amb null
							// així carregarà una llista de rols buida
							// (obligatori)
							us.addRol(null);
						} else {
							// hi ha rol -> l'obtenim amb el rolDAO i l'afegim
							us.addRol(rd.crearRol(rset));
						}
						// Indico al DBWrapper que segueixi amb el
						// següent registre
						return true;
					}

				});
		return us.getMail() == null ? null : us;
	}

	/**
	 * Recupera de base de dades tots els usuaris ordenats per nom
	 * 
	 * @return La llista d' usuaris. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 */
	public List<Usuari> obtenirTots() throws SQLException {
		final List<Usuari> elsUsuaris = new Vector<Usuari>();

		dw.executeQuery(SQL_SELECT_ALL, new GenericExecuteQueryProcess() {
			@Override
			public boolean processRow(Connection con, PreparedStatement st,
					ResultSet rset) throws SQLException {
				// Obtinc el registre de la fila actual
				// i el fico a la llista
				elsUsuaris.add(crearUsuari(rset));
				// Indico al DBWrapper que segueixi amb el
				// següent registre
				return true;
			}
		});
		return elsUsuaris;
	}

	/**
	 * Funció d'utilitat per crear un objecte Usuari a partir de la fila actual
	 * d'un recordset
	 * 
	 * @param rset
	 *            El recordset d'on treurem l' usuari
	 * @return Usuari creat
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 */
	protected Usuari crearUsuari(ResultSet rset) throws SQLException {
		return crearUsuari(rset, null);
	}

	/**
	 * Funció d'utilitat per omplir un objecte Usuari amb els valors de la fila
	 * actual d'un recordset
	 * 
	 * @param rset
	 *            El recordset d'on treurem l' usuari
	 * @param u
	 *            L'usuari a omplir. Pot ser null i es crearà un Usuari nou
	 * @return L'usuari amb els camps recuperats
	 * @throws SQLException
	 *             En cas que es produeixi un error de base de dades
	 */
	protected Usuari crearUsuari(ResultSet rset, Usuari u) throws SQLException {
		// Si no em passen usuari, el creo
		if (u == null) {
			u = new Usuari();
		}
		// Omplo l' usuari desde el recordset
		u.setMail(rset.getString(ConstantsSQL.USUARI_CAMP_EMAIL));
		u.setPassword(rset.getString(ConstantsSQL.USUARI_CAMP_PASSWORD));
		u.setActiu(rset.getBoolean(ConstantsSQL.USUARI_CAMP_ACTIU));

		// Retorno l'objecte recuperat
		return u;
	}
}
