package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.PreuDAO;
import com.soc.ewok.model.Preu;


public class PreuDAOTest extends AbstractDAOTest {
	Date dInicial = new Date();
	Date dFinal = new Date(dInicial.getTime() + 100000);
	Date dInicialModificada = new Date(dInicial.getTime() + 200000);
	Date dFinalModificada = new Date(dFinal.getTime() + 300000);
	
	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de preu
		executaScript("test/resources/scripts/PreuData.sql"); 
	}

	@Test
	public void altaCorrecta() throws SQLException {
		PreuDAO dao = new PreuDAO(createDataSource());
		// Creo un preu
		Preu p = new Preu();
		// Omplo les dades
		p.setPreu(20f);
		p.setIniciVigencia(dInicial);
		p.setFinalVigencia(dFinal);
		p.setIdProducte(1l);
		// Dono d'alta el preu
		dao.alta(p);
		// Recupero de nou el preu
		// (alta m'ha d'haver assignat el nou id)
		Preu nou = dao.obtenirPerId(p.getId());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(p, nou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		PreuDAO dao = new PreuDAO(createDataSource());
		// Creo un preu dels que existeixen a la bd
		Preu p = dao.obtenirPerId(1l);
		// El modifico
		p.setPreu(22f);
		p.setIniciVigencia(dInicialModificada);
		p.setFinalVigencia(dFinalModificada);
		p.setIdProducte(1l);
		dao.modificar(p);
		// El recupero i comprobo que està modificat
		Preu nou = dao.obtenirPerId(1l);
		Assert.assertEquals(p, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		PreuDAO dao = new PreuDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		Preu p = dao.obtenirPerId(1l);
		Assert.assertNotNull(p);
		// Esborrem l'objecte
		dao.esborrar(p.getId());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		Preu pEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(pEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		PreuDAO dao = new PreuDAO(createDataSource());
		List<Preu> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres rols
		Assert.assertEquals(3, tots.size());
		// Comprovem que un a un són correctes
		Assert.assertEquals(tots.get(0).getId(), (Long)1l);
		Assert.assertEquals(tots.get(0).getPreu(), (Float)10f);
		Assert.assertTrue(sonIguals(tots.get(0).getIniciVigencia(), 2015, 4, 25, 0, 0, 0));
		Assert.assertTrue(sonIguals(tots.get(0).getFinalVigencia(), 2016, 4, 24, 23, 59, 59));
		Assert.assertEquals(tots.get(0).getIdProducte(), (Long)1l);
		
		Assert.assertEquals(tots.get(1).getId(), (Long)2l);
		Assert.assertEquals(tots.get(1).getPreu(), (Float)20f);
		Assert.assertTrue(sonIguals(tots.get(0).getIniciVigencia(), 2015, 4, 25, 0, 0, 0));
		Assert.assertTrue(sonIguals(tots.get(0).getFinalVigencia(), 2016, 4, 24, 23, 59, 59));
		Assert.assertEquals(tots.get(1).getIdProducte(), (Long)2l);
		
		Assert.assertEquals(tots.get(2).getId(), (Long)3l);
		Assert.assertEquals(tots.get(2).getPreu(), (Float)30f);
		Assert.assertTrue(sonIguals(tots.get(0).getIniciVigencia(), 2015, 4, 25, 0, 0, 0));
		Assert.assertTrue(sonIguals(tots.get(0).getFinalVigencia(), 2016, 4, 24, 23, 59, 59));
		Assert.assertEquals(tots.get(2).getIdProducte(), (Long)3l);
	}
}
