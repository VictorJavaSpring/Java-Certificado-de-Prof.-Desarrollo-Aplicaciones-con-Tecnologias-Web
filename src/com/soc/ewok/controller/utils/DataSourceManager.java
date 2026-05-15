package com.soc.ewok.controller.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSource;
// @home import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.soc.ewok.controller.EWokController;

/**
 * Application Lifecycle Listener implementation class DataSourceManager
 *
 */
public class DataSourceManager implements ServletContextListener {
	private static final int CON_MAX_ACTIVE = 10;
	private static final String CON_PASSWORD = "soctardes"; // Vosaltres mysql
	private static final String CON_USER = "soctardes";
	private static final String CON_URL = "jdbc:mysql://localhost:3306/ewok";
	private static final String CON_DRIVER_CLASS = "com.mysql.jdbc.Driver";

	/**
	 * Default constructor.
	 */
	public DataSourceManager() {
	}

	/**
	 * Inicialitza el datasource global que far� servir tota l'aplicaci�
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent srvEv) {
		// De moment creem el datasource a partir de constants
		// Cal fer-ho per par�metres de configuraci�
		// @home BasicDataSource bsd = new BasicDataSource();
		BasicDataSource bsd = new BasicDataSource();
		bsd.setDriverClassName(CON_DRIVER_CLASS);
		bsd.setUrl(CON_URL);
		bsd.setUsername(CON_USER);
		bsd.setPassword(CON_PASSWORD);	
		bsd.setMaxIdle(CON_MAX_ACTIVE);
		// Posem el datasource a la aplicaci�
		EWokController.setGlobalDatasource(bsd);
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent srvEv) {
		// Treiem el datasource del contexte
		EWokController.setGlobalDatasource(null);
	}

}
