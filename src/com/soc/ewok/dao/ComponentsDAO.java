package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.soc.ewok.model.Components;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

/**
 * DAO per a la classe {@link Components}
 * @author Xavier
 *
 */

public class ComponentsDAO {
	/** Query per esborrar un registre per id */
	private static final String SQL_ESBORRAR =
			"DELETE FROM " + ConstantsSQL.COMPONENTS_TAULA + 
			" WHERE " + ConstantsSQL.COMPONENTS_CAMP_IDPRODUCTE + " = ?" +
			" AND " + ConstantsSQL.COMPONENTS_CAMP_IDCOMPONENT + " = ?";
	
	/** Query per donar d'alta un component amb tots els seus camps */
	private static final String SQL_ALTA = 
			"INSERT INTO " + ConstantsSQL.COMPONENTS_TAULA + 
			"(" + ConstantsSQL.COMPONENTS_CAMP_IDPRODUCTE + ", " + 
				  ConstantsSQL.COMPONENTS_CAMP_IDCOMPONENT + ", " + 
				  ConstantsSQL.COMPONENTS_CAMP_QUANTITAT + ", " + 
			      ConstantsSQL.COMPONENTS_CAMP_ORDRE + ")" + 
			" VALUES (?, ?, ?, ?)";
	
	/** Query per modificar tots els camps d'un component per id */
	private static final String SQL_MODIFICAR = 
			"UPDATE " + ConstantsSQL.COMPONENTS_TAULA + 
			" SET " + ConstantsSQL.COMPONENTS_CAMP_QUANTITAT + " = ?, " +
			  		  ConstantsSQL.COMPONENTS_CAMP_ORDRE + " = ? " +
			" WHERE " + ConstantsSQL.COMPONENTS_CAMP_IDPRODUCTE + " = ?" +
			" AND " + ConstantsSQL.COMPONENTS_CAMP_IDCOMPONENT + " = ?";
	
	
	/** Query per obtenir tots els components de base de dades */
	private static final String SQL_SELECT_ALL = 
			"SELECT *" +					
			" FROM " + ConstantsSQL.COMPONENTS_TAULA +
			" ORDER BY " + ConstantsSQL.COMPONENTS_CAMP_IDPRODUCTE;
	
	/** Query per obtenir un component per id */
	private static final String SQL_SELECT_BY_ID = 
			"SELECT *" +
			" FROM " + ConstantsSQL.COMPONENTS_TAULA +
			" WHERE " + ConstantsSQL.COMPONENTS_CAMP_IDPRODUCTE + " = ?" +
			" AND " + ConstantsSQL.COMPONENTS_CAMP_IDCOMPONENT + " = ?";

	DBWrapper dw;
	
	/**
	 * Constructor de la classe
	 * @param ds DataSource a usar per connectar-se a base de dades
	 */
	public ComponentsDAO(DataSource ds) {
		dw = new DBWrapper(ds);
	}
	
	/**
	 * Dóna d'alta un registre a base de dades.
	 * Actualitzarà l'id amb el valor generat pel gestor
	 * @param c Component a donar d'alta
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void alta(final Components c) throws SQLException {
		dw.executeSql(
			SQL_ALTA, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1, c.getIdProducte());
					st.setLong(2, c.getIdComponent());	
					st.setInt(3, c.getQuantitat());
					st.setInt(4, c.getOrdre());	
				}
			}
		);
	}
	
	/**
	 * Esborra un component de base de dades
	 * @param id Identificador del component a esborrar
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public void esborrar(final long id1, final long id2) throws SQLException {
		dw.executeSql(
			SQL_ESBORRAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1, id1);
					st.setLong(2, id2);
				}
			}
		);	
	}
	
	/**
	 * Modifica un component a base de dades 
	 * @param c El component a modificar- Usarà l'id
	 * per buscar el registre i actualitzarà tots els camps
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public void modificar(final Components c) throws SQLException {
		dw.executeSql(
			SQL_MODIFICAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1, c.getIdProducte());
					st.setLong(2, c.getIdComponent());	
					st.setInt(3, c.getQuantitat());
					st.setInt(4, c.getOrdre());	
				}
			}
		);	
	}
	
	/**
	 * Recupera un component per id
	 * @param id1 del component a recuperar
	 * @param id2 del component a recuperar
	 * @return El component si es troba, o null si no existeix
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public Components obtenirPerId(final long id1, final long id2) throws SQLException {
		final Components elComponent = new Components();
		dw.executeQuery(
			SQL_SELECT_BY_ID, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setLong(1, id1);
					st.setLong(2, id2);
				}
			},
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a l'objecte que retornaré
					crearComponent(rset, elComponent);
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return false;
				}
			}
		);
		return elComponent.getIdProducte() == null &&
			   elComponent.getIdComponent() == null? null : elComponent;
	}
	
	/**
	 * Recupera de base de dades tots els components ordenats per nom
	 * @return La llista de components. Si no n'hi ha, retornarà una llista buida
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	public List<Components> obtenirTots() throws SQLException {
		final List<Components> elsComponents = new Vector<Components>();
		dw.executeQuery(
			SQL_SELECT_ALL, 
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					elsComponents.add(crearComponent(rset));
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return true;
				}
			}
		);
		return elsComponents;
	}
	
	/**
	 * Funció d'utilitat per crear un objecte Component a partir de la fila actual
	 * d'un recordset 
	 * @param rset El recordset d'on treurem el Component
	 * @return El Component creat
	 * @throws SQLException En cas que es produeixi un error de base de dades
	 */
	private Components crearComponent(ResultSet rset) throws SQLException {
		return crearComponent(rset, null);
	}
	
	/**
	 * Funció d'utilitat per omplir un objecte Component amb els valors
	 * de la fila actual d'un recordset 
	 * @param rset El recordset d'on treurem el Component
	 * @param c El Component a omplir. Pot ser null i es crearà un Component nou
	 * @return El Component amb els camps recuperats
	 * @throws SQLException El recordset d'on treurem el Component
	 */
	private Components crearComponent(ResultSet rset, Components c) throws SQLException {
		// Si no em passen component, el creo
		if (c == null) {
			c = new Components();
		}
		// Omplo el component desde el recordset
		c.setIdProducte(rset.getLong(ConstantsSQL.COMPONENTS_CAMP_IDPRODUCTE));
		c.setIdComponent(rset.getLong(ConstantsSQL.COMPONENTS_CAMP_IDCOMPONENT));
		c.setQuantitat(rset.getInt(ConstantsSQL.COMPONENTS_CAMP_QUANTITAT));
		c.setOrdre(rset.getInt(ConstantsSQL.COMPONENTS_CAMP_ORDRE));
		// Retorno l'objecte recuperat
		return c;
	}
}
