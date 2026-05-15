package com.soc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interficie de callback per a processar
 * les files d'una query que retorni resultat
 * @author Administrador
 *
 */
public interface IExecuteQueryProcess extends IExecuteSQLProcess {
	/**
	 * Métode per processar una fila concreta
	 * @param con Connexió usada per executar la query
	 * @param st Statement usat per executar la query
	 * @param rset ResultSet resultat de la query
	 * @return true si cal processar la següent fila i false en cas contrari
	 * @throws SQLException En cas que hi hagi algun problema amb la base de dades
	 */
	public boolean processRow(
			Connection con,
			PreparedStatement st,
			ResultSet rset 
		) throws SQLException;
}
