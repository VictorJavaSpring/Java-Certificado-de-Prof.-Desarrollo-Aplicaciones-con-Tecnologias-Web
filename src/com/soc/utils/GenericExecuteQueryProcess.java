package com.soc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe abstracta d'implementació buida de {@link IExecuteQueryProcess}
 * @author Administrador
 *
 */
public abstract class GenericExecuteQueryProcess implements IExecuteQueryProcess {

	/**
	 * @see com.soc.utils.IExecuteSQLProcess#doPreProcess(Connection, PreparedStatement)
	 */
	@Override
	public void doPreProcess(Connection con, PreparedStatement st)
			throws SQLException {
	}

	/**
	 * @see com.soc.utils.IExecuteSQLProcess#doPostProcess(Connection, PreparedStatement)
	 */
	@Override
	public void doPostProcess(Connection con, PreparedStatement st)
			throws SQLException {
	}

	/**
	 * @see com.soc.utils.IExecuteQueryProcess#processRow(Connection, PreparedStatement, ResultSet)
	 */
	@Override
	public boolean processRow(Connection con, PreparedStatement st,
			ResultSet rset) throws SQLException {
		return false;
	}
}
