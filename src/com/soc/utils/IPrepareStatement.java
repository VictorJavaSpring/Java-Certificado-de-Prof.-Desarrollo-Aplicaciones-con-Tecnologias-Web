package com.soc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Métode de callback per preparar un statement.
 * Típicament l'usarem per indicar els paràmetres
 * @author Administrador
 *
 */
public interface IPrepareStatement {
	/**
	 * Métode per preparar el statement
	 * @param con Connexió usada per executar la query
	 * @param st Statement usat per executar la query
	 * @throws SQLException En cas que hi hagi algun problema amb la base de dades
	 */
	public void prepareParams(
			Connection con, 
			PreparedStatement st) throws SQLException;
}
