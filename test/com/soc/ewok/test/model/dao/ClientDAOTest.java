package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.ClientDAO;
import com.soc.ewok.model.Client;

public class ClientDAOTest extends AbstractDAOTest {

	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de rol
		executaScript("test/resources/scripts/ClientData.sql"); 
	}

	@Test
	public void altaCorrecta() throws SQLException {
		ClientDAO dao = new ClientDAO(createDataSource());
		Date data = new Date();
		// Creo un client
		Client c = new Client();
		// Omplo les dades
		c.setNom("Nom");
		c.setCognoms("Cognoms");
		c.setTelefon("666666666");
		c.setEmail("client@email.com");
		c.setDataAlta(data);
		c.setDni("00000000A");
		c.setIdUsuari("unemail@delclient.com");
		// Dono d'alta el client
		dao.alta(c);
		// Recupero de nou el client
		// (alta m'ha d'haver assignat el nou id)
		Client nou = dao.obtenirPerId(c.getId());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(c, nou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		ClientDAO dao = new ClientDAO(createDataSource());
		// Creo un client dels que existeixen a la bd
		Client c = new Client();
		c.setId(1l);
		c.setNom("Un altre");
		// El modifico
		dao.modificar(c);
		// El recupero i comprovo que està modificat
		Client nou = dao.obtenirPerId(1l);
		Assert.assertEquals(c, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		ClientDAO dao = new ClientDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		Client c = dao.obtenirPerId(1l);
		Assert.assertNotNull(c);
		// Esborrem l'objecte
		dao.esborrar(c.getId());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		Client cEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(cEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		ClientDAO dao = new ClientDAO(createDataSource());
		List<Client> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres 
		Assert.assertEquals(3, tots.size());
		// Comprovem un a un que són correctes
		Assert.assertEquals(tots.get(0).getId(), (Long)1l);
		Assert.assertEquals(tots.get(0).getNom(), "Client1");
		Assert.assertEquals(tots.get(0).getCognoms(), "CognomsClient1");
		Assert.assertEquals(tots.get(0).getTelefon(), "666666666");
		Assert.assertEquals(tots.get(0).getEmail(), "Client1@email.com");
		Assert.assertTrue(sonIguals(tots.get(0).getDataAlta(), 2014, 05, 01, 10, 00, 00));
		Assert.assertEquals(tots.get(0).getDni(), "00000000A");
		Assert.assertEquals(tots.get(0).getIdUsuari(), "unemail@email.com");
		Assert.assertEquals(tots.get(1).getId(), (Long)2l);
		Assert.assertEquals(tots.get(1).getNom(), "Client2");
		Assert.assertEquals(tots.get(1).getCognoms(), "CognomsClient2");
		Assert.assertEquals(tots.get(1).getTelefon(), "766666666");
		Assert.assertEquals(tots.get(1).getEmail(), "Client2@email.com");
		Assert.assertTrue(sonIguals(tots.get(1).getDataAlta(), 2014, 05, 01, 10, 00, 00));
		Assert.assertEquals(tots.get(1).getDni(), "00000000B");
		Assert.assertEquals(tots.get(1).getIdUsuari(), "unaltreemail@email.com");
		Assert.assertEquals(tots.get(2).getId(), (Long)3l);
		Assert.assertEquals(tots.get(2).getNom(), "Client3");
		Assert.assertEquals(tots.get(2).getCognoms(), "CognomsClient3");
		Assert.assertEquals(tots.get(2).getTelefon(), "866666666");
		Assert.assertEquals(tots.get(2).getEmail(), "Client3@email.com");
		Assert.assertTrue(sonIguals(tots.get(2).getDataAlta(), 2014, 05, 01, 10, 00, 00));
		Assert.assertEquals(tots.get(2).getDni(), "00000000C");
		Assert.assertEquals(tots.get(2).getIdUsuari(), "unemailmes@email.com");
	}
}
