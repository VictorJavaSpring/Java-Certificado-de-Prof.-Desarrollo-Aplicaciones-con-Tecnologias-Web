package com.soc.ewok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.soc.ewok.model.Client;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;
import com.soc.utils.IPrepareStatement;

public class ClientDAO {
	private static final String SQL_ESBORRAR =
			"DELETE FROM " + ConstantsSQL.CLIENT_TAULA + 
			" WHERE " + ConstantsSQL.CLIENT_CAMP_ID + " = ?";
	
	private static final String SQL_ALTA = 
			"INSERT INTO " + ConstantsSQL.CLIENT_TAULA + 
			"(" + ConstantsSQL.CLIENT_CAMP_NOM +
			", " + ConstantsSQL.CLIENT_CAMP_COGNOMS +
			", " + ConstantsSQL.CLIENT_CAMP_TELEFON +
			", " + ConstantsSQL.CLIENT_CAMP_EMAIL +
			", " + ConstantsSQL.CLIENT_CAMP_DATA_ALTA +
			", " + ConstantsSQL.CLIENT_CAMP_DNI +
			", " + ConstantsSQL.CLIENT_CAMP_ID_USUARI +
			") VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_MODIFICAR = 
			"UPDATE " + ConstantsSQL.CLIENT_TAULA + 
			" SET " + ConstantsSQL.CLIENT_CAMP_NOM + " = ?" +
			", " + ConstantsSQL.CLIENT_CAMP_COGNOMS + " = ?" +
			", " + ConstantsSQL.CLIENT_CAMP_TELEFON + " = ?" +
			", " + ConstantsSQL.CLIENT_CAMP_EMAIL + " = ?" +
			", " + ConstantsSQL.CLIENT_CAMP_DATA_ALTA + " = ?" +
			", " + ConstantsSQL.CLIENT_CAMP_DNI + " = ?" +
			", " + ConstantsSQL.CLIENT_CAMP_ID_USUARI + " = ?" +
			" WHERE " + ConstantsSQL.CLIENT_CAMP_ID + " = ?";

	private static final String SQL_SELECT_ALL = 
			"SELECT * FROM " + ConstantsSQL.CLIENT_TAULA +
			" ORDER BY " + ConstantsSQL.CLIENT_CAMP_COGNOMS + 
			", "  + ConstantsSQL.CLIENT_CAMP_NOM;
	
	private static final String SQL_SELECT_BY_ID = 
			"SELECT * FROM " + ConstantsSQL.CLIENT_TAULA +
			" WHERE " + ConstantsSQL.CLIENT_CAMP_ID + " = ?";

	DBWrapper dw;

	public ClientDAO(DataSource ds) {
		dw = new DBWrapper(ds);
	}

	public void alta(final Client c) throws SQLException {
		dw.executeSql(
			SQL_ALTA, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setString(1, c.getNom());
					st.setString(2, c.getCognoms());
					st.setString(3, c.getTelefon());
					st.setString(4, c.getEmail());
					st.setTimestamp(5, DBWrapper.getParameterFromDate(c.getDataAlta()));
					st.setString(6, c.getDni());
					st.setString(7, c.getIdUsuari());				
					
				}
			},
			new GenericExecuteQueryProcess() {		
				@Override
				public void doPostProcess(Connection con, PreparedStatement st)
						throws SQLException {
					// Uso el recordset de camps generats
					// que em dóna l'statement per assignar
					// l'id al Client original
					ResultSet keys = st.getGeneratedKeys();
					keys.next();
					c.setId(keys.getLong(1));					
				}
			}
		);
	}

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

	public void modificar(final Client c) throws SQLException {
		dw.executeSql(
			SQL_MODIFICAR, 
			new IPrepareStatement() {
				@Override
				public void prepareParams(
						Connection con, 
						PreparedStatement st) throws SQLException {
					st.setString(1, c.getNom());
					st.setString(2, c.getCognoms());
					st.setString(3, c.getTelefon());
					st.setString(4, c.getEmail());
					st.setTimestamp(5, DBWrapper.getParameterFromDate(c.getDataAlta()));
					st.setString(6, c.getDni());
					st.setString(7, c.getIdUsuari());					
					st.setLong(8, c.getId());
				}
			}
		);	
	}

	public Client obtenirPerId(final long id) throws SQLException {
		final Client elClient = new Client();
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
					crearClient(rset, elClient);
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return false;
				}
			}
		);
		// Si no hi ha client, que retorni null; si n'hi ha, que el retorni
		return elClient.getId() == null ? null : elClient;
	}

	public List<Client> obtenirTots() throws SQLException {
		final List<Client> elsClients = new Vector<Client>();
		dw.executeQuery(
			SQL_SELECT_ALL, 
			new GenericExecuteQueryProcess() {
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					elsClients.add(crearClient(rset));
					// Indico al DBWrapper que segueixi amb el 
					// següent registre
					return true;
				}
			}
		);
		return elsClients;
	}

	private Client crearClient(ResultSet rset) throws SQLException {
		return crearClient(rset, null);
	}

	private Client crearClient(ResultSet rset, Client c) throws SQLException {
		// Si no em passen client, el creo
		if (c == null) {
			c = new Client();
		}
		// Omplo el client des del resultset
		c.setId(rset.getLong(ConstantsSQL.CLIENT_CAMP_ID));
		c.setNom(rset.getString(ConstantsSQL.CLIENT_CAMP_NOM));
		c.setCognoms(rset.getString(ConstantsSQL.CLIENT_CAMP_COGNOMS));
		c.setTelefon(rset.getString(ConstantsSQL.CLIENT_CAMP_TELEFON));
		c.setEmail(rset.getString(ConstantsSQL.CLIENT_CAMP_EMAIL));
		c.setDataAlta(rset.getTimestamp(ConstantsSQL.CLIENT_CAMP_DATA_ALTA));
		c.setDni(rset.getString(ConstantsSQL.CLIENT_CAMP_DNI));
		c.setIdUsuari(rset.getString(ConstantsSQL.CLIENT_CAMP_ID_USUARI));
		
		// Retorno l'objecte recuperat
		return c;
	}
}



