package com.soc.ewok.controller.utils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSource;

import com.mysql.jdbc.Driver;
import com.soc.ewok.controller.EWokController;

/**
 * Application Lifecycle Listener implementation class DataSourceManager
 *
 */
public class CopyOfDataSourceManagerMemoryLeaks implements ServletContextListener {
	private static final int CON_MAX_ACTIVE = 10;
	private static final String CON_PASSWORD = "soctardes"; // Vosaltres mysql
	private static final String CON_USER = "soctardes";
	private static final String CON_URL = "jdbc:mysql://localhost:3306/eWok";
	private static final String CON_DRIVER_CLASS = "com.mysql.jdbc.Driver";

	/**
	 * Default constructor.
	 */
	public CopyOfDataSourceManagerMemoryLeaks() {
	}

	/**
	 * Inicialitza el datasource global que far� servir tota l'aplicaci�
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent srvEv) {
		// De moment creem el datasource a partir de constants
		// Cal fer-ho per par�metres de configuraci�
		BasicDataSource bsd = new BasicDataSource();
		bsd.setDriverClassName(CON_DRIVER_CLASS);
		bsd.setUrl(CON_URL);
		bsd.setUsername(CON_USER);
		bsd.setPassword(CON_PASSWORD);	
		bsd.setMaxActive(CON_MAX_ACTIVE);
		// Posem el datasource a la aplicaci�
		EWokController.setGlobalDatasource(bsd);
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent srvEv) {
		// This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
        Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
        	Logger log = Logger.getGlobal();
            Driver driver = (Driver) drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                log.log(Level.INFO, "deregistering jdbc driver: %s", driver);
                //LOG.log(Level.INFO, String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e1) {
                log.log(Level.INFO, "Error deregistering driver %s", driver);

             //   LOG.log(Level.SEVERE, String.format("Error deregistering driver %s", driver), e);
            }

        }

		// Treiem el datasource del contexte
		EWokController.setGlobalDatasource(null);
	}

}
