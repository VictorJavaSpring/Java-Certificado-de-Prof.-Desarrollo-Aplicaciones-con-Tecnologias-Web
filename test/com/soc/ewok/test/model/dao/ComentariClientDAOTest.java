package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.ComentariClientDAO;
import com.soc.ewok.model.ComentariClient;


public class ComentariClientDAOTest extends AbstractDAOTest{
	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de rol
		executaScript("test/resources/scripts/ComentariClientData.sql");
	}

	@Test
	public void altaCorrecta() throws SQLException {
		ComentariClientDAO dao = new ComentariClientDAO(createDataSource());
		Date dataTest = new Date();
		// Creo un comentari
		ComentariClient cc = new ComentariClient();
		// Omplo les dades
		cc.setIdClient(10l);
		cc.setComentari("El client sempre anula la comanda");
		cc.setData(dataTest);
		cc.setIdAutor("IdAutor");
		// Donc d'alta el comentari
		dao.alta(cc);
		// Recupero de nou el comentari
		// (alta m'ha d'haver assignat el nou id)
		ComentariClient nou = dao.obtenirPerId(cc.getId());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(cc, nou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		ComentariClientDAO dao = new ComentariClientDAO(createDataSource());
		// Obtinc un comentari desde base de dades
		ComentariClient cc = dao.obtenirPerId(1l);
		// El modifico
		cc.setComentari("No deixa mai propina");
		dao.modificar(cc);
		// El recupero i comprobo que està modificat
		ComentariClient nou = dao.obtenirPerId(1l);
		Assert.assertEquals(cc, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		ComentariClientDAO dao = new ComentariClientDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		ComentariClient cc = dao.obtenirPerId(1l);
		Assert.assertNotNull(cc);
		// Esborrem l'objecte
		dao.esborrar(cc.getId());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		ComentariClient ccEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(ccEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		ComentariClientDAO dao = new ComentariClientDAO(createDataSource());
		List<ComentariClient> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres rols
		Assert.assertEquals(3, tots.size());
		// Comprovem que el un a un són correctes
		Assert.assertEquals(tots.get(0).getId(), (Long)1l);
		Assert.assertEquals(tots.get(0).getIdClient(), 10);
		Assert.assertEquals(tots.get(0).getComentari(), "Bon Client");
		Assert.assertTrue(sonIguals(tots.get(0).getData(), 2014, 05, 01, 10, 00, 00));
		Assert.assertEquals(tots.get(0).getIdAutor(), "100");
		//
		Assert.assertEquals(tots.get(1).getId(), (Long)2l);
		Assert.assertEquals(tots.get(1).getIdClient(), 9);
		Assert.assertEquals(tots.get(1).getComentari(), "Mal Client");
		Assert.assertTrue(sonIguals(tots.get(1).getData(), 2014, 05, 01, 10, 00, 00));
		Assert.assertEquals(tots.get(1).getIdAutor(), "90");
		//
		Assert.assertEquals(tots.get(2).getId(), (Long)3l);
		Assert.assertEquals(tots.get(2).getIdClient(), 8);
		Assert.assertEquals(tots.get(2).getComentari(), "Mig Client");
		Assert.assertTrue(sonIguals(tots.get(2).getData(), 2014, 05, 01, 10, 00, 00));
		Assert.assertEquals(tots.get(2).getIdAutor(), "80");
	}

}
