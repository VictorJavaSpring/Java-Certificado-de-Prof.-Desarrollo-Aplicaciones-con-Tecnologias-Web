package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.OfertaComandaDAO;
import com.soc.ewok.model.OfertaComanda;


public class OfertaComandaDAOTest extends AbstractDAOTest {
	//creo dates accessibles des de qualsevol mètode 
	//per a provar iniciVigencia i fiVigencia on calgui
	
	Date data1 = new Date();
	Date data2 = new Date(data1.getTime() + 1000000);
	
	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de rol
		executaScript("test/resources/scripts/OfertaComandaData.sql"); 
	}

	@Test
	public void altaCorrecta() throws SQLException {
		OfertaComandaDAO dao = new OfertaComandaDAO(createDataSource());
		// Creo un Producte
		OfertaComanda r = new OfertaComanda();
		// Omplo les dades
		r.setLimitInferior(10.3f);
		r.setPctDescompte(15.00f);
		r.setIniciVigencia(data1);
		r.setFiVigencia(data2);
		
		// Dono d'alta el Producte
		dao.alta(r);
		// Recupero de nou el Producte
		// (alta m'ha d'haver assignat el nou id)
		OfertaComanda nou = dao.obtenirPerId(r.getId());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(r, nou);
	}
	@Test
	public void modificacioCorrecta() throws SQLException {
		OfertaComandaDAO dao = new OfertaComandaDAO(createDataSource());
		// Creo un objecte dels que existeixen a la bd
		OfertaComanda r = new OfertaComanda();
		r.setId(1l);
		r.setLimitInferior(33f);
		r.setPctDescompte(15.00f);
		r.setIniciVigencia(data1);
		r.setFiVigencia(data2);
		// El modifico
		dao.modificar(r);
		// El recupero i comprobo que està modificat
		OfertaComanda nou = dao.obtenirPerId(1l);
		Assert.assertEquals(r, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		OfertaComandaDAO dao = new OfertaComandaDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		OfertaComanda r = dao.obtenirPerId(1l);
		Assert.assertNotNull(r);
		// Esborrem l'objecte
		dao.esborrar(r.getId());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		OfertaComanda rEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(rEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		OfertaComandaDAO dao = new OfertaComandaDAO(createDataSource());
		List<OfertaComanda> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres rols
		Assert.assertEquals(3, tots.size());
		// Comprovem que el un a un són correctes
		Assert.assertEquals(tots.get(0).getId(), (Long)1l);
		Assert.assertEquals(tots.get(0).getLimitInferior(), (Float)25.30f);
		Assert.assertEquals(tots.get(0).getPctDescompte(), (Float)10.00f);
		Assert.assertTrue(sonIguals(tots.get(0).getIniciVigencia(), 2015, 1, 24, 0, 0, 0));
		Assert.assertTrue(sonIguals(tots.get(0).getFiVigencia(), 2015, 10, 24, 0, 0, 0));
		//
		Assert.assertEquals(tots.get(1).getId(), (Long)2l);
		Assert.assertEquals(tots.get(1).getLimitInferior(), (Float)25.30f);
		Assert.assertEquals(tots.get(1).getPctDescompte(), (Float)10.00f);
		Assert.assertTrue(sonIguals(tots.get(1).getIniciVigencia(), 2015, 3, 4, 0, 0, 0));
		Assert.assertTrue(sonIguals(tots.get(1).getFiVigencia(), 2015, 7, 31, 0, 0, 0));
		//
		Assert.assertEquals(tots.get(2).getId(), (Long)3l);
		Assert.assertEquals(tots.get(2).getLimitInferior(), (Float)25.30f);
		Assert.assertEquals(tots.get(2).getPctDescompte(), (Float)10.00f);
		Assert.assertTrue(sonIguals(tots.get(2).getIniciVigencia(), 2015, 5, 14, 0, 0, 0));
		Assert.assertTrue(sonIguals(tots.get(2).getFiVigencia(), 2015, 6, 15, 0, 0, 0));
	}
}
