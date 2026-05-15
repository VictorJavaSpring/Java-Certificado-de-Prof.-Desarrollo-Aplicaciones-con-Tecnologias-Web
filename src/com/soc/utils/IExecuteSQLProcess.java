package com.soc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Interficie de callback per fer un pre i post procés
 * abans i després d'executar una query
 * @author Administrador
 *
 */
public interface IExecuteSQLProcess {
	/**
	 * Métode callback per a fer el preprocés
	 * @param con Connexió usada per executar la query
	 * @param st Statement usat per executar la query
	 * @throws SQLException En cas que hi hagi algun problema amb la base de dades
	 */
	public void doPreProcess(
		Connection con, 
		PreparedStatement st) throws SQLException;
	
	/**
	 * Métode callback per a fer el postprocés
	 * @param con Connexió usada per executar la query
	 * @param st Statement usat per executar la query
	 * @throws SQLException En cas que hi hagi algun problema amb la base de dades
	 */
	public void doPostProcess(
		Connection con, 
		PreparedStatement st) throws SQLException;
}
