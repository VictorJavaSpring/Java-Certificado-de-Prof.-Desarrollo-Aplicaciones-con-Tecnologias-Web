package com.soc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

/**
 * Classe genèrica per a la gestió de connexions 
 * a base de dades i execució de querys amb resultat o sense
 * @author Administrador
 *
 */
public class DBWrapper {
	/**
	 * Funció d'utilitat per convertir una java.util.Date en una
	 * java.sql.date per poder ser usat en un paràmetre
	 * @param d La java.util.date
	 * @return La java.sql.date equivalent
	 */
	public static Timestamp getParameterFromDate(java.util.Date d) {
		//les dates poden ser null en alguns camps. protegim la funció de accessos a null
		if (d==null) return null;

		//si no és null
		return new Timestamp(d.getTime());
	}

	/** El DataSouce per obtenir connexions a base de dades */
	DataSource ds;

	/**
	 * Constructor de la classe
	 * @param ds DataSource a usar per aquest objecte
	 */
	public DBWrapper(DataSource ds) {
		this.ds = ds;
	}

	/**
	 * Executa una query que no rep resultat
	 * @param sql La query a executar
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void executeSql(String sql) throws SQLException {
		executeSql(sql, null, null, null);
	}

	/**
	 * Executa una query que no rep resultat amb la possibilitat
	 * de passar un objecte de callback per a preparar l'statement
	 * @param sql La query a executar
	 * @param prep L'objecte de callback de preparació d'statement
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void executeSql(
			String sql, 
			IPrepareStatement prep) throws SQLException {
		executeSql(sql, prep, null, null);
	}
	/**
	 * Executa una query que no rep resultat amb la possibilitat
	 * de passar un objecte de callback per a preparar l'statement i un
	 * altre per a fer pre/post-procés
	 * @param sql La query a executar
	 * @param prep L'objecte de callback de preparació d'statement
	 * @param proc L'objecte de callback per fer pre/post-procés
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void executeSql(
			String sql, 
			IPrepareStatement prep,
			IExecuteSQLProcess proc) throws SQLException {
		executeSql(sql, prep, proc, null);
	}
	/**
	 * Executa una query que no rep resultat amb la possibilitat
	 * de passar un objecte de callback per a preparar l'statement i un
	 * altre per a fer pre/post-procés
	 * @param sql La query a executar
	 * @param prep L'objecte de callback de preparació d'statement
	 * @param proc L'objecte de callback per fer pre/post-procés
	 * @param con Objecte conexió 
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 */
	public void executeSql(
			String sql, 
			IPrepareStatement prep,
			IExecuteSQLProcess proc, 
			Connection con) throws SQLException {

		boolean connexioPropia = false;
		System.out.println("Inici PreparedSql: " + sql);

		PreparedStatement st = null;
		try {
			//només si con és null
			if(con == null){
				con = ds.getConnection();
				//iniciem transacció
				con.setAutoCommit(false);
				connexioPropia = true;
			}
			
			st = con.prepareStatement(sql);
			// Preparo el statement a través del callback
			if (prep != null) {
				prep.prepareParams(con, st);
			}
			// Si cal fer un preprocess, el faig
			if (proc != null) {
				proc.doPreProcess(con, st);
			}
			// Executo la query
			System.out.println("Executo PreparedSql: " + sql);
			st.executeUpdate();
			// Si cal fer un postrocess, el faig			
			if (proc != null) {
				proc.doPostProcess(con, st);
			}
			//només si no ens han passat connexió
			if(connexioPropia)
				con.commit();
			
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {}
			
			//si no ens han passat connexió
			if(connexioPropia){
				try {
					if (con != null){
						con.setAutoCommit(true);
						con.close();
					}
				} catch (SQLException e) {}
			}
			System.out.println("Final PreparedSql: " + sql);
		}
	}
	
	/**
	 * Executa una query que retorna resultat
	 * @param sql La query a executar
	 * @param proc Objecte de callback per a tractar cada fila del resultat
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 * @throws IllegalArgumentException En cas que no es objecte de callback 
	 */
	public void executeQuery(
			String sql, 
			IExecuteQueryProcess proc) 
				throws SQLException {
		executeQuery(sql, null, proc);
	}
	
	/**
	 * Executa una query que retorna resultat amb possibilitat d'especificar
	 * un objecte de callback per preparar l'statement
	 * @param sql La query a executar
	 * @param proc Objecte de callback per a tractar cada fila del resultat
	 * @param prep Objecte de callback per a preparar l'statement
	 * @throws SQLException En cas que hi hagi un error de base de dades
	 * @throws IllegalArgumentException En cas que no es objecte de callback 
	 */
	public void executeQuery(
			String sql, 
			IPrepareStatement prep,
			IExecuteQueryProcess proc) 
				throws SQLException {
		System.out.println("Inici PreparedQuery: " + sql);
		if (proc == null) {
			throw new IllegalArgumentException(
					"Cal passar un IExecuteQueryProcessRow");
		}
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			//iniciem transacció
			con.setAutoCommit(false);
			
			st = con.prepareStatement(sql);
			// Preparo l'statement
			if (prep != null) {
				prep.prepareParams(con, st);
			}
			// Faig el preprocess
			proc.doPreProcess(con, st);
			// Executo la query
			System.out.println("Executo PreparedQuery: " + sql);
			rs = st.executeQuery();
			// Recorro el recordset
			while (rs.next()) {
				// Processo la fila
				if (!proc.processRow(con, st, rs))
					break;
			}
			// Faig el postprocess
			proc.doPostProcess(con, st);
			//
			con.commit();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
			}
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
			}
			try {
				if (con != null){
					con.setAutoCommit(true);
					con.close();
				}
			} catch (SQLException e) {
			}
			System.out.println("Final PreparedQuery: " + sql);
		}
	}
}
