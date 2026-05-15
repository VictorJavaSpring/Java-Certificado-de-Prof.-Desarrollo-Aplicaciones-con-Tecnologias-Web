package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.ComandaDAO;
import com.soc.ewok.model.Comanda;
import com.soc.ewok.model.EEstatComanda;

public class ComandaDAOTest extends AbstractDAOTest {

	@Override
	public void preparaBD() throws SQLException {
		// Creem l esquema a traves de la classe base
		super.preparaBD();
		// Executem l'script de carrega de dades de comanda
		executaScript("test/resources/scripts/ComandaData.sql"); 
	}

	@Test
	public void altaCorrecta() throws SQLException {
		ComandaDAO dao = new ComandaDAO(createDataSource());
		// Creo una comanda
		Comanda c = new Comanda();
		Date dataTest = new Date();
		// Omplo les dades
		c.setIdClient(1l);
		c.setPunts((short) 20);
		c.setDescompte(2.5f);
		c.setData(dataTest);
		c.setAdreca(8l);
		c.setComentaris("Comentaris1");
		c.setEstat(EEstatComanda.pagada);
		c.setPreuFinal(44.5f);
		// Donc d'alta la comanda
		dao.alta(c);
		// Recupero de nou la comanda
		// (alta m'ha d'haver assignat el nou id)
		Comanda nou = dao.obtenirPerId(c.getId());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(c, nou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		ComandaDAO dao = new ComandaDAO(createDataSource());
		// Creo una comanda de les que existeixen a la bd
		Comanda c = new Comanda();
		
		Date dataTest = new Date();

		c.setId(1l);
		c.setIdClient(3l);
		c.setPunts((short) 20);
		c.setDescompte(2.5f);
		c.setData(dataTest);
		c.setAdreca(8l);
		c.setComentaris("Comentaris1");
		c.setEstat(EEstatComanda.entregada);
		c.setPreuFinal(44.5f);
		// la modifico
		dao.modificar(c);
		Comanda nou = dao.obtenirPerId(1l);
		// la recuperem i comprobem que esta modificada
		Assert.assertEquals(c, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		ComandaDAO dao = new ComandaDAO(createDataSource());
		// Recuperem l objecte que esborrarem
		// per assegurarnos que hi es
		Comanda c = dao.obtenirPerId(1l);
		Assert.assertNotNull(c);
		// Esborrem lobjecte
		dao.esborrar(c.getId());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		Comanda cEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(cEsborrat);
	}
	
	@Test
	public void obtenirTots() throws SQLException {
		ComandaDAO dao = new ComandaDAO(createDataSource());
		List<Comanda> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres comandes
		// obtenirTots fa servir SQL...(ordenades per IdClient)
		Assert.assertEquals(3, tots.size());
		// Comprovem que el un a un són correctes
		Assert.assertEquals(tots.get(0).getId(), (Long)1l);
		Assert.assertEquals(tots.get(0).getIdClient(), (Long)1l);
		Assert.assertEquals(tots.get(0).getComentaris(),"Timo de comentaris1");
		Assert.assertTrue(sonIguals(tots.get(0).getData(), 1974, 03, 22, 01, 02, 03));
		Assert.assertEquals(tots.get(0).getEstat(),EEstatComanda.validada);

		Assert.assertEquals(tots.get(1).getId(), (Long)2l);
		Assert.assertEquals(tots.get(1).getIdClient(), (Long)2l);
		Assert.assertEquals(tots.get(1).getComentaris(),"Timo de comentaris2");
		Assert.assertTrue(sonIguals(tots.get(1).getData(), 2013, 3, 22, 11, 22, 33));
		Assert.assertEquals(tots.get(1).getEstat(),EEstatComanda.pagada);

		Assert.assertEquals(tots.get(2).getId(), (Long)3l);
		Assert.assertEquals(tots.get(2).getIdClient(), (Long)3l);
		Assert.assertEquals(tots.get(2).getComentaris(),"Timo de comentaris3");
		Assert.assertTrue(sonIguals(tots.get(2).getData(), 2015, 3, 22, 11, 12, 13));
		Assert.assertEquals(tots.get(2).getEstat(),EEstatComanda.preparada);
	}
}
