package com.soc.ewok.test.model.dao;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.sql.DataSource;

//import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.h2.tools.RunScript;
import org.junit.Before;

public abstract class AbstractDAOTest {
	protected static final String JDBC_DRIVER = "org.h2.Driver";
	protected static final String JDBC_URL = "jdbc:h2:mem:eWok;DB_CLOSE_DELAY=-1";
	protected static final String USER = "sa";
	protected static final String PASSWORD = "";

	@SuppressWarnings("deprecation")
	public static boolean sonIguals(Date d, 
			int any, int mes, int dia, int hora, int minut, int segon) {
		return d.getTime() == new Date(any - 1900, mes - 1, dia, hora, minut, segon).getTime();
	}
	
	protected DataSource createDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(JDBC_DRIVER);
		ds.setUrl(JDBC_URL);
		ds.setUsername(USER);
		ds.setPassword(PASSWORD);
		ds.setMaxActive(4);
		ds.setMaxIdle(1);
		return ds;
	}

	@Before
	public void preparaBD() throws SQLException {
		executaScript("test/resources/scripts/DBCreation.sql");
	}
	
	protected void executaScript(String script) throws SQLException {
		RunScript.execute(
				JDBC_URL,
				USER, PASSWORD, 
				script, 
				Charset.forName("utf-8"), 
				false);
	}
}



