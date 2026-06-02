package com.soc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Interficie de callback per fer un pre i post proces
 * abans i despres d'executar una query
 * @author Administrador
 *
 */
public interface IExecuteSQLProcess {
	/**
	 * metode callback per a fer el preproces
	 * @param con Connexio usada per executar la query
	 * @param st Statement usat per executar la query
	 * @throws SQLException En cas que hi hagi algun problema amb la base de dades
	 */
	public void doPreProcess(
		Connection con, 
		PreparedStatement st) throws SQLException;
	
	/**
	 * metode callback per a fer el postproces
	 * @param con Connexio usada per executar la query
	 * @param st Statement usat per executar la query
	 * @throws SQLException En cas que hi hagi algun problema amb la base de dades
	 */
	public void doPostProcess(
		Connection con, 
		PreparedStatement st) throws SQLException;
}
