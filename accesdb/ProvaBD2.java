package com.soc.accesbd.probes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import java.sql.PreparedStatement;


public class ProvaBD {
	
	 // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/soc_tardes";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "mysql";
	
		public static void main(String[] args) {
//			 System.out.println("populating selected database...");
//		      populate();
//		      System.out.println("selected database populated.");
		      System.out.println("Fent les querys.....");
		    querys();
		      System.out.println("Querys fetes.");
		      }//end main
	   
	public static void populate(){
		
		String noms[] = new String [] {
			   "Victor", "Pere", "Joan", "Anna", "Pau", "Marta" , "Jenny", "Edixon", "Sara", "Carles", "Jordi", "Marta"};
		   
		String cognoms[]= new String [] {
				"Lopes", "Gonsales", "Peres", "Martinez", "Roig", "Tudury", "Di Zio"};  

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
		System.out.println("Connected database successfully...");
		System.out.println("User: " + USER);
		
		// Obtenim un Prepared statement per poder fer la query
					  String sqlPrepared = 
					  "insert into prova "+
						"(num_curt, num_llarg, nom, cognom ) "
					  + " values (?,?,?,?)";
				  
					stm = con.prepareStatement(sqlPrepared);
						long tempsIni = System.currentTimeMillis();
						
						final int REGS= 1000;
						  System.out.println("Creating prepareStatement...");

						  for (int i = 0; i < REGS; i++) {
							  stm.setInt(1, r.nextInt(11));
							  stm.setInt(2, r.nextInt(1000000));
							  stm.setString(3, noms[r.nextInt(noms.length)]);
							  stm.setString(4, cognoms[r.nextInt(cognoms.length)] + " " + cognoms[r.nextInt(cognoms.length)]
									  );

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
						  
	public static void querys(){
		// Carreguem la classe del driver en memòria
					try {
						// Register JDBC driver
						Class.forName(JDBC_DRIVER);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Connection con = null;
					//Statement stm = null;
					PreparedStatement stm = null;
					ResultSet rs = null;

					// Open a connection
				      System.out.println("Connecting to a selected database...");
				      
				try {
					con = DriverManager.getConnection
							(DB_URL, USER, PASS);
					System.out.println("Connected database successfully...");
					System.out.println("User: " + USER);

					// Obtenim un Prepared statement per poder fer la query
					System.out.println("Creating statement...");
					  String sqlPrepared = 
					  "insert into prova "+
						"(num_curt, num_llarg, nom, cognom ) "
					  + " values (?,?,?,?)";
					  
						stm = con.prepareStatement(sqlPrepared);
//
//						System.out.println("Creating prepareStatement...");
//						
//						long tempsIni = System.currentTimeMillis();
//						final int REGS= 1000;
//						  for (int i = 0; i < REGS; i++) {
//							  stm.setInt(1, r.nextInt(11));
//							  stm.setInt(2, r.nextInt(1000000));
//							  stm.setString(3, noms[r.nextInt(noms.length)]);
//							  stm.setString(4, cognoms[r.nextInt(cognoms.length)] + " " + cognoms[r.nextInt(cognoms.length)]
//									  );
//							  stm.executeUpdate(); // ja té el sql, no cal ficarlo
//							  //System.out.println(sqlPrepared);
//						  }
//						  long millis = System.currentTimeMillis() - tempsIni;
//						  System.out.println("s'han insertat  " + REGS + " en " + millis + " milisegons");
				      ////////////////////////////////////////////////////////////
					// avans	stm = con.prepareStatement(sqlPrepared);

			// Per executar una instrucció SQL que no retorna registres
							//String sql ="";
//							stm.executeUpdate(
//						"");
						//stm.executeUpdate(
//						"insert into prova (Nom, Cognom, Numcurt, Numllarg) values ('Joan', 'Pera', 22,23244232)");
					// Obtenim un statement per poder fer la query

					
				      ////////////////////////////////////////////////////////////
					//
//					System.out.println("Creating statement where nom = 'Victor' or nom = 'Jenny' ...");
//					stm = con.createStatement();
			// Per executar un SELECT que retorna registres
//			  String sqlSELECT = "SELECT id, nom, cognom, num_curt, num_llarg FROM prova where nom = 'Victor' or nom = 'Jenny' ";
//			  String sqlSortingORDERASC = " ORDER BY num_curt ASC";
//			  // " ORDER BY first DESC";
//			  String sqlAsc = sqlSELECT + sqlSortingORDERASC;
//			//String sql ="select * from prova";
			  // Per fer update
			  // String sqlDELETE = "DELETE FROM prova " +"WHERE id = 0";
			  //String sqlUPDATE = "UPDATE prova " + "SET numcurt = 30 WHERE id in (4, 6)";
			  //System.out.println("Inserting records into the table...");
			 //stm.executeUpdate(sqlAsc);
//			  System.out.println("Fetching records with condition ORDER BY numcurt ASC ");
//
//				rs = stm.executeQuery(sqlAsc);
//				  System.out.println(" ");


			// Bucle per recorrer tots els registres retornats
//			while(rs.next()) {
//			     // Retrieve by column name
//				int id  = rs.getInt("id");
//			     int numcurt = rs.getInt("num_curt");
//			     int numllarg  = rs.getInt("num_llarg");
//			     String nom = rs.getString("nom");
//			     String cognom  = rs.getString("cognom");
//				// Accedeixo al registre actual
//				System.out.println(
//					"Id: " + id +". Nom: " +nom +". Cognom: " + cognom +". Numcurt: " +numcurt + ". Numllarg: " + numllarg
//						);
//
//			}
//			rs.close();
//			System.out.println(" ");

				 // Select all records having ID equal or greater than 4
			      System.out.println("Fetching records with condition SELECT num_curt, count(*), nom FROM prova GROUP BY num_curt");
			      /* String sqlSELECT2 = "SELECT id, nom, cognom, num_curt, num_llarg FROM prova " +" WHERE cognom = 'Tudury' and cognom ='Roig' ";
			      String sqlEXERCICI2 = "SELECT * FROM prova " +
							"WHERE num_curt = 1 OR num_curt = 3 OR num_curt = 5 " + 
						  " ORDER BY id DESC";
				  
				  String sqlEXERCICI3 = "SELECT * FROM prova " +
							"WHERE num_curt > 3 AND num_curt < 8 " + 
						  " ORDER BY id DESC";
				  
				  String sqlEXERCICI4 = "SELECT count(*) FROM prova " +
							"WHERE num_curt = 1 OR num_curt = 3 OR num_curt = 5  " + 
						  " ORDER BY id DESC";
				  
				  String sqlEXERCICI5 = "SELECT count(*) FROM prova " +
						"WHERE num_curt = 1 OR num_curt = 3 OR num_curt = 5  " + 
						  " ORDER BY id DESC";*/
					String sqlEXERCICI6 = "SELECT num_curt, count(*), nom FROM prova GROUP BY num_curt ";
			      //String sqlSELECT = "SELECT id, nom, cognom, num_curt, num_llarg FROM prova ";
//				  String sqlSortingWHEREORDER = " WHERE num_curt > 5 and nom ='Victor' " ;
//				  String sqlSortingWHERE = " WHERE num_llarg < 2000 AND num_curt in (1,6,9) " ;
//				  String sqlSortingWHERE2 = " WHERE cognom LIKE 'M% R%' " ;
//				  String sqlSortingWHERE3 = "  WHERE nom LIKE 'V%ictor' " ;
//				  String sqlSortingWHERE4 = "  WHERE nom LIKE = 'Victor%' " ;
//				  String sqlSortingORDERcurt = " ORDER BY num_curt DESC, num_llarg, cognom DESC";
				  // cognom LIKE '[M-Z]%'
//				  String sqlEXERCICI1 = "SELECT * FROM prova " +
//					" WHERE nom LIKE '%a' AND (cognom >= 'M' AND num_curt = 5) " + 
//				  " ORDER BY id DESC";
				  
				  // " ORDER BY first DESC";
				 // String sqlAsc = sqlSELECT + sqlSortingWHERE4 + sqlSortingORDERcurt ;
					//System.out.println(sqlEXERCICI1);

				//String sql ="select * from prova";
				  // Per fer update
				  // String sqlDELETE = "DELETE FROM prova " +"WHERE id = 0";
				  //String sqlUPDATE = "UPDATE prova " + "SET numcurt = 30 WHERE id in (4, 6)";
				  //System.out.println("Inserting records into the table...");
				 //stm.executeUpdate(sqlAsc);
			      ////////////////////////////////////////////
			     // EX 7: Quins noms apareixen com a mínim en 6 categories de nom_curt diferents
			  	// EX 8: QuinEs CATEGORIES de num_curt agrupats en tots els reg q no tinguin cap registre amb num_llarg < 50000 */
//			  	SELECT num_curt, count(*) as numsCurts  , min(num_llarg) as minim_llarg
//			  	FROM prova 
//			  	/* WHERE num_llarg < 10000 /* FILTRA REGISTRES ORIGINALS <>  5 */
//			  	GROUP BY num_curt
//			  	HAVING minim_llarg > 10000  /*FILTRA ELS GRUPS FETS */
//			  	ORDER BY numsCurts asc
			      ////////////////////////////
			     /* String sqlEXERCICI7 = "SELECT num_curt as ncurt, nom as n, count(*) as c" +
			  			" FROM prova " + 
			  			"GROUP BY num_curt, nom " +
			  			" HAVING c = 6" +
			  			" ORDER BY num_curt, n";*/
			      
			      
			      
			      ///////////////////////////
			      rs = stm.executeQuery(sqlEXERCICI6);

			   // Bucle per recorrer tots els registres retornats
					while(rs.next()) {
						//int id  = rs.getInt("id");
					     int numcurt = rs.getInt("num_curt");
					     //int numllarg  = rs.getInt("num_llarg");
					     String nom = rs.getString("nom");
					     //String cognom  = rs.getString("cognom");

						// Accedeixo al registre actual
						System.out.println(
							"Nom: " + nom+". Numcurt: "  + numcurt
						);
					}
					      rs.close();
						  System.out.println(" ");
//
//					      // Select all records having p in nom
//					      System.out.println("Fetching records with conditionhaving p in nom.WHERE nom LIKE %p%");
//
//						String sqlSELECT3 = "SELECT id, nom, cognom, num_curt, num_llarg FROM prova " + " WHERE nom LIKE 'p%' ";
//					      rs = stm.executeQuery(sqlSELECT3);
//					   // Bucle per recorrer tots els registres retornats
//							while(rs.next()) {
//								int id  = rs.getInt("id");
//							     int numcurt = rs.getInt("num_curt");
//							     int numllarg  = rs.getInt("num_llarg");
//							     String nom = rs.getString("nom");
//							     String cognom  = rs.getString("cognom");
//								// Accedeixo al registre actual
//								System.out.println(
//									"Id: " + id +". Nom: " +nom +". Cognom: " + cognom +". Numcurt: " + numcurt + ". Numllarg: " + numllarg
//								);
//								}
//							      rs.close();
//								  System.out.println(" ");

							
				} catch (SQLException se) {
				      //Handle errors for JDBC
					se.printStackTrace();
					
			   }catch(Exception e){
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
								  
		
	public static void mainNonPrepared(String[] args) {
		// Carreguem la classe del driver en memòria
		try {
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;

		// Open a connection
	      System.out.println("Connecting to a selected database...");
	      
	try {
		con = DriverManager.getConnection
				(DB_URL, 
						USER, 
						PASS);
		System.out.println("Connected database successfully...");
		System.out.println("User: " + USER);

// Per executar una instrucció SQL que no retorna registres
				//String sql ="";
//				stm.executeUpdate(
//						"insert into prova (Nom, Cognom, Numcurt, Numllarg) values ('Joan', 'Pera', 22,23244232)");
		// Obtenim un statement per poder fer la query
		System.out.println("Creating statement...");
		stm = con.createStatement();
		
		
// Per executar un SELECT que retorna registres
  String sqlSELECT = "SELECT id, nom, cognom, num_curt, num_llarg FROM prova";
  String sqlSortingORDERASC = " ORDER BY num_curt ASC";
  // " ORDER BY first DESC";
  String sqlAsc = sqlSELECT + sqlSortingORDERASC;
//String sql ="select * from prova";
  // Per fer update
  // String sqlDELETE = "DELETE FROM prova " +"WHERE id = 0";
  //String sqlUPDATE = "UPDATE prova " + "SET numcurt = 30 WHERE id in (4, 6)";
  //System.out.println("Inserting records into the table...");
 //stm.executeUpdate(sqlAsc);
  System.out.println("Fetching records with condition ORDER BY numcurt ASC ");

	rs = stm.executeQuery(sqlAsc);
	  System.out.println(" ");


// Bucle per recorrer tots els registres retornats
while(rs.next()) {
     // Retrieve by column name
	int id  = rs.getInt("id");
     int numcurt = rs.getInt("num_curt");
     int numllarg  = rs.getInt("num_llarg");
     String nom = rs.getString("nom");
     String cognom  = rs.getString("cognom");
	// Accedeixo al registre actual
	System.out.println(
		"Id: " + id +". Nom: " +nom +". Cognom: " + cognom +". Numcurt: " + numcurt + ". Numllarg: " + numllarg
			);

}
rs.close();
System.out.println(" ");

	 // Select all records having ID equal or greater than 4
      System.out.println("Fetching records with condition ID equal or greater than 4..WHERE id >= 4 ");
      String sqlSELECT2 = "SELECT id, nom, cognom, num_curt, num_llarg FROM prova" +" WHERE id >= 4 ";
      rs = stm.executeQuery(sqlSELECT2);
      // ResultSet rs = stm.executeQuery(sqlSELECT2);

   // Bucle per recorrer tots els registres retornats
		while(rs.next()) {
			int id  = rs.getInt("id");
		     int numcurt = rs.getInt("num_curt");
		     int numllarg  = rs.getInt("num_llarg");
		     String nom = rs.getString("nom");
		     String cognom  = rs.getString("cognom");

			// Accedeixo al registre actual
			System.out.println(
				"Id: " + id +". Nom: " +nom +". Cognom: " + cognom +". Numcurt: " +numcurt + ". Numllarg: " + numllarg
			);
		}
		      rs.close();
			  System.out.println(" ");

		      

		      // Select all records having p in nom
		      System.out.println("Fetching records with conditionhaving p in nom.WHERE nom LIKE %p%");

			String sqlSELECT3 = "SELECT nom, cognom, num_curt, num_llarg FROM prova"  +" WHERE nom LIKE '%p%' ";
		      rs = stm.executeQuery(sqlSELECT3);
		   // Bucle per recorrer tots els registres retornats
				while(rs.next()) {
					int id  = rs.getInt("id");
				     int numcurt = rs.getInt("num_curt");
				     int numllarg  = rs.getInt("num_llarg");
				     String nom = rs.getString("nom");
				     String cognom  = rs.getString("cognom");
					// Accedeixo al registre actual
					System.out.println(
						"Id: " + id +". Nom: " +nom +". Cognom: " + cognom +". Numcurt: " +numcurt + ". Numllarg: " + numllarg
					);
					}
				      rs.close();
					  System.out.println(" ");

				
	} catch (SQLException se) {
	      //Handle errors for JDBC
		se.printStackTrace();
		
   }catch(Exception e){
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
