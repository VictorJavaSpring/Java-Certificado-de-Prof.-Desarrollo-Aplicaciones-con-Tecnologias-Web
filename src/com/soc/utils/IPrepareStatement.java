package com.soc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * metode de callback per preparar un statement.
 * tepicament l'usarem per indicar els parametres
 * @author Administrador
 *
 */
public interface IPrepareStatement {
	/**
	 * metode per preparar el statement
	 * @param con Connexio usada per executar la query
	 * @param st Statement usat per executar la query
	 * @throws SQLException En cas que hi hagi algun problema amb la base de dades
	 */
	public void prepareParams(
			Connection con, 
			PreparedStatement st) throws SQLException;
}
