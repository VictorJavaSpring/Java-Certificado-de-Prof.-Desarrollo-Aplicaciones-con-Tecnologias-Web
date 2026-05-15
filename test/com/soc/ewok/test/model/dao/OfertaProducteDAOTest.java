package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.OfertaProducteDAO;
import com.soc.ewok.model.OfertaProducte;


public class OfertaProducteDAOTest extends AbstractDAOTest {
	
	Date datainici = new Date();
	Date datafinal = new Date(datainici.getTime() + 1000000);
	
	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de ofertaproducte
		executaScript("test/resources/scripts/OfertaProducteData.sql");
	}

	@Test
	public void altaCorrecta() throws SQLException {
		OfertaProducteDAO dao = new OfertaProducteDAO(createDataSource());
		// Creo una ofertaproducte
		
		OfertaProducte r = new OfertaProducte();
		// Omplo les dades
		
		r.setNom("nom");
		r.setPctDescompte(15.00f);
		r.setIniciVigencia(datainici);
		r.setFiVigencia(datafinal);
		r.setOtext("descripcio ofertaproducte");
		r.setIdProducte(4l);
		
		// Donc d'alta el ofertaproducte
		dao.alta(r);
		// Recupero de nou el ofertaproducte
		// (alta m'ha d'haver assignat el nou id)
		OfertaProducte nou = dao.obtenirPerId(r.getId());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(r, nou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		OfertaProducteDAO dao = new OfertaProducteDAO(createDataSource());
		// Creo un objecte dels que existeixen a la bd
		OfertaProducte r = new OfertaProducte();
		r.setId(1l);
		r.setPctDescompte(15.00f);
		r.setIniciVigencia(datainici);
		r.setFiVigencia(datafinal);
		r.setNom("nom");
		r.setOtext("text");
		r.setIdProducte(4l);
		// El modifico
		dao.modificar(r);
		// El recupero i comprobo que està modificat
		OfertaProducte nou = dao.obtenirPerId(1l);
		Assert.assertEquals(r, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		OfertaProducteDAO dao = new OfertaProducteDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		OfertaProducte r = dao.obtenirPerId(1l);
		Assert.assertNotNull(r);
		// Esborrem l'objecte
		dao.esborrar(r.getId());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		OfertaProducte rEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(rEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		OfertaProducteDAO dao = new OfertaProducteDAO(createDataSource());
		List<OfertaProducte> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres rols
		Assert.assertEquals(3, tots.size());
		// Comprovem que el un a un són correctes
		Assert.assertEquals(tots.get(0).getId(), (Long)1l);
		Assert.assertEquals(tots.get(0).getNom(), "Special Hot eWok Dish");
		Assert.assertEquals(tots.get(0).getPctDescompte(), (Float)15.00f);
		Assert.assertTrue(sonIguals(tots.get(0).getIniciVigencia(), 2015, 1, 1, 0, 0, 0));
		Assert.assertTrue(sonIguals(tots.get(0).getFiVigencia(), 2015, 3, 1, 0, 0, 0));
		Assert.assertEquals(tots.get(0).getOtext(), "Chicken or pork with red curry with a touch of coconout milk , fresh basil leaves, eggplant, fresh beans & green peppercorn");
		Assert.assertEquals(tots.get(0).getIdProducte(), (Long)4l);
		//
		Assert.assertEquals(tots.get(1).getId(), (Long)2l);
		Assert.assertEquals(tots.get(1).getNom(), "Jade eWok");
		Assert.assertEquals(tots.get(1).getPctDescompte(), (Float)20.00f);
		Assert.assertTrue(sonIguals(tots.get(1).getIniciVigencia(), 2015, 4, 1, 0, 0, 0));
		Assert.assertTrue(sonIguals(tots.get(1).getFiVigencia(), 2015, 8, 1, 0, 0, 0));
		Assert.assertEquals(tots.get(1).getOtext(), "Thai style stir fried king prawns, tofu, fresh ginger,mushroom, & shallots seasoned with oyster sauce");                                              
 		Assert.assertEquals(tots.get(1).getIdProducte(), (Long)4l);
		//
		Assert.assertEquals(tots.get(2).getId(), (Long)3l);
		Assert.assertEquals(tots.get(2).getNom(), "Noodle eWok");
		Assert.assertEquals(tots.get(2).getPctDescompte(), (Float)10.00f);
		Assert.assertTrue(sonIguals(tots.get(2).getIniciVigencia(), 2015, 9, 1, 0, 0, 0));
		Assert.assertTrue(sonIguals(tots.get(2).getFiVigencia(), 2015, 12, 1, 0, 0, 0));
		Assert.assertEquals(tots.get(2).getOtext(), "Laksa prepared with fine rice noodles,coconut milk, wild ginger, turmeric & special curry paste with fried tofu and bean sprouts");
		Assert.assertEquals(tots.get(2).getIdProducte(), (Long)4l);
	}

}
