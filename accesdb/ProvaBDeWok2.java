package com.soc.accesbd.probes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.sql.PreparedStatement;


@SuppressWarnings("unused")
public class ProvaBDeWok {
	
	 // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/eWok";

	   //  Database credentials
	   static final String USER = "soctardes";
	   static final String PASS = "soctardes";
	
		public static void main(String[] args) {
			 System.out.println("populating selected database...");
		      populate();
		      System.out.println("selected database populated.");
//		      System.out.println("Fent les querys.....");
//		    querys();
//		      System.out.println("Querys fetes.");
		      }//end main
	   
	public static void populate(){
		
		Long id[] = new Long [] {
				   0l,1l,2l,3l,4l,5l,6l,7l,8l,9l};
		Long idClient[] = new Long [] {
				1l,2l,3l,4l,5l,6l,7l,8l,9l};
		short puntsGenerats[] = new short [] {
				0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		Float pctDescompte[] = new Float [] {
				0.1f,1.2f,2.1f,3.0f,4f,5f,6f,7f,8f,9f};
		String data[] = new String [] {
				"2013-03-22", "2015-03-22","2012-03-22","2001-03-22","1991-03-22","1994-02-22","1994-03-22"};
		Long adrecaEntrega[] = new Long [] {
				   1l,2l,3l,4l,5l,6l,7l,8l,9l};
					//  "Carrer Victor,69", "Carrer Pere", "Carrer Joan", "Carrer Anna", "Carrer Pau", "Carrer Marta" , "Carrer Jenny", "Carrer Edixon", "Carrer Sara", "Carrer Carles", "Carrer Jordi", "Carrer Marta"};
		String comentaris[] = new String [] {
			   "Victor", "Pere", "Joan", "Anna", "Pau", "Marta" , "Jenny", "Edixon", "Sara", "Carles", "Jordi", "Marta"};
		String estat[]= new String [] {
				"A", "B", "C", "D", "E", "F", "G"};  
		Float preuFinal[]= new Float [] {
				10.1f,12.2f,22.1f,3.0f,42f,5f,6f,7f,8f,9f};  

		Random r = new Random();

		// Carreguem la classe del driver en memòria
		try {
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection con = null;
		// avans Statement stm = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		// Open a connection
	      System.out.println("Connecting to a selected database...");
	      
	try {
		con = DriverManager.getConnection
				(DB_URL, USER, PASS);
		System.out.println("Connected database [" + DB_URL + "] successfully...");
		System.out.println("User: " + USER);
		
		// Obtenim un Prepared statement per poder fer la query
					  String sqlPrepared = 
					  "insert into Comanda "+
						"(IdClient, PuntsGenerats, PctDescompte, Data, AdrecaEntrega, Comentaris, Estat, PreuFinal) "
					  + " values (?,?,?,?,?,?,?,?)";
				  
					stm = con.prepareStatement(sqlPrepared);
						long tempsIni = System.currentTimeMillis();
						
						final int REGS= 1000;
						  System.out.println("Creating prepareStatement...");
//						  idClient=1;
						  for (int i = 0; i < REGS; i++) {
							  stm.setLong(1, idClient[r.nextInt(idClient.length)]);
							  stm.setShort(2, (short) r.nextInt(10));
							  stm.setFloat(3,pctDescompte[r.nextInt(pctDescompte.length)]);
									  stm.setString(4, "2001-03-22T11:12:12");
									  stm.setLong(5, r.nextInt(10));
							  stm.setString(6, "Comentaris1");
							  stm.setString(7, "A");
							  stm.setFloat(8,pctDescompte[r.nextInt(pctDescompte.length)]);
								System.out.println("Statement: "+stm);

							stm.executeUpdate(); // ja té el sql, no cal ficarlo		
						  }
						  long millis = System.currentTimeMillis() - tempsIni;
						  
						  System.out.println("s'han insertat  " + REGS + " en " + millis + " milisegons");
						  
	} catch (SQLException se) {
	      //Handle errors for JDBC
		se.printStackTrace();
		
 } catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	      
	} finally {
	      //finally block used to close resources
		try {
			if (rs != null) 
				rs.close();
		} catch (SQLException e) {
			}      // do nothing
		try {
			if (stm != null) 
				stm.close();
		} catch (SQLException e) {}
		try {
			if (con != null) 
				con.close();
		} catch (SQLException se) {
	         se.printStackTrace();
		}//end finally try
	}//end try
	   System.out.print("Goodbye, ");
	   System.out.println(USER);
}//end main
						  
}//end JDBCExample
