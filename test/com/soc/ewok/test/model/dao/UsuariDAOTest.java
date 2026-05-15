package com.soc.ewok.test.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.RolDAO;
import com.soc.ewok.dao.UsuariDAO;
import com.soc.ewok.model.Rol;
import com.soc.ewok.model.Usuari;
import com.soc.utils.DBWrapper;
import com.soc.utils.GenericExecuteQueryProcess;

public class UsuariDAOTest extends AbstractDAOTest {
	
	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de rol
		
		executaScript("test/resources/scripts/UsuariData.sql");
		executaScript("test/resources/scripts/RolData4UsuariDAOtest.sql");
		
	}

	@Test
	public void altaCorrecta() throws SQLException {
		UsuariDAO dao = new UsuariDAO(createDataSource());
		// Creo un Usuari
		Usuari u = new Usuari();
		// Omplo les dades
		u.setMail("user4@user.com");
		u.setPassword("user4");
		u.setActiu(true);
		// Dono d'alta l'usuari
		dao.alta(u);
		// Recupero de nou l' usuari
		
		Usuari nou = dao.obtenirPerEmail(u.getMail());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(u, nou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		UsuariDAO dao = new UsuariDAO(createDataSource());
		// Obtinc un usuari desde base de dades
		Usuari u = dao.obtenirPerEmail("user1@user.com");
		// El modifico
		u.setPassword("patata");
		dao.modificar(u);
		// El recupero i comprobo que està modificat
		Usuari nou = dao.obtenirPerEmail("user1@user.com");
		Assert.assertEquals(u, nou);
	}

	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		UsuariDAO dao = new UsuariDAO(createDataSource());
		List<Usuari> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres usuaris
		Assert.assertEquals(4, tots.size());
		// Comprovem un a un que són correctes
		Assert.assertEquals(tots.get(0).getMail(), "user1@user.com");
		Assert.assertEquals(tots.get(0).getPassword(), "user1");
		Assert.assertEquals(tots.get(0).getActiu(), true);
		//
		Assert.assertEquals(tots.get(1).getMail(), "user2@user.com");
		Assert.assertEquals(tots.get(1).getPassword(), "user2");
		Assert.assertEquals(tots.get(1).getActiu(), false);
		//
		Assert.assertEquals(tots.get(2).getMail(), "user3@user.com");
		Assert.assertEquals(tots.get(2).getPassword(), "user3");
		Assert.assertEquals(tots.get(2).getActiu(), true);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		UsuariDAO dao = new UsuariDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		Usuari u = dao.obtenirPerEmail("user5@user.com");
		Assert.assertNotNull(u);
		
		//comprovo que té rols relacionats
		DBWrapper dbw = new DBWrapper(createDataSource());
		String sql = "SELECT * FROM rolusuari WHERE eMail='user5@user.com'";
		final List<Integer> l = new Vector<Integer>();
		
		dbw.executeQuery(sql ,
				new GenericExecuteQueryProcess() {		
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					
					l.add(rset.getRow());
					// Indico al DBWrapper que segueixi amb el
					// següent registre
					return true;
					}
			}
		);
		Assert.assertEquals(l.size(),2);
		
		// Esborrem l'objecte
		dao.esborrar(u.getMail());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		Usuari uEsborrat = dao.obtenirPerEmail("user5@user.com");
		Assert.assertNull(uEsborrat);
		
		//comprovar que no hi ha valors per a user5 a la taula rolusuari 

		final List<Integer> ll = new Vector<Integer>();
		
		dbw.executeQuery(sql ,
				new GenericExecuteQueryProcess() {		
				@Override
				public boolean processRow(Connection con, PreparedStatement st,
						ResultSet rset) throws SQLException {
					// Obtinc el registre de la fila actual
					// i el fico a la llista
					
					ll.add(rset.getRow());
					// Indico al DBWrapper que segueixi amb el
					// següent registre
					return true;
					}
			}
		);
		Assert.assertEquals(ll.size(),0);
	}
	
	@Test
	public void obtenirAmbRols() throws SQLException {
		UsuariDAO dao = new UsuariDAO(createDataSource());
		RolDAO dao2 = new RolDAO(createDataSource());
		// Creo un Usuari
		Usuari u = new Usuari();
		// Omplo les dades
		u.setMail("user4@user.com");
		u.setPassword("user4");
		u.setActiu(true);
		// Dono d'alta l'usuari
		dao.alta(u);
		//li asssigno Rols
		Rol r1 = dao2.obtenirPerId(1l);
		u.addRol(r1);
		u.addRol(dao2.obtenirPerId(2l));
		// Recupero de nou l' usuari
		Usuari nou = dao.obtenirPerEmail(u.getMail());
		System.out.println(nou.toString());
		// Miro que els dos objectes Usuari són iguals
		Assert.assertEquals(u, nou);
		//miro que tenen el mateix nombre de Rols
		Assert.assertEquals(u.getRols().size(), nou.getRols().size());
		//miro que els Rols continguts son iguals
		Assert.assertEquals(u.getRols().get(0), nou.getRols().get(0));
		Assert.assertEquals(u.getRols().get(1), nou.getRols().get(1));
	}


}
