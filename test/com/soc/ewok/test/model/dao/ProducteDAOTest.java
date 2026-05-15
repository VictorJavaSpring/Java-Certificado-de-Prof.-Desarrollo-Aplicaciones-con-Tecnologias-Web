package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.ProducteDAO;
import com.soc.ewok.model.Producte;


public class ProducteDAOTest extends AbstractDAOTest {
	//creo dates accessibles des de qualsevol mètode 
	//per a provar iniciVigencia i fiVigencia on calgui
	
	Date data1 = new Date();
	Date data2 = new Date(data1.getTime() + 100000);
	
	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de rol
		executaScript("test/resources/scripts/ProducteData.sql"); 
	}

	@Test
	public void altaCorrecta() throws SQLException {
		ProducteDAO dao = new ProducteDAO(createDataSource());
		// Creo un Producte
		Producte r = new Producte();
		// Omplo les dades
		r.setNom("Nom");
		r.setDescripcioCurta("descriu");
		r.setDescripcio("descriume");
		r.setIniciVigencia(data1);
		r.setFiVigencia(data2);
		r.setIdUnitat(2l);
		r.setIdTipusProducte(4l);
		
		// Dono d'alta el Producte
		dao.alta(r);
		// Recupero de nou el Producte
		// (alta m'ha d'haver assignat el nou id)
		Producte nou = dao.obtenirPerId(r.getId());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(r, nou);
	}
	@Test
	public void modificacioCorrecta() throws SQLException {
		ProducteDAO dao = new ProducteDAO(createDataSource());
		// Creo un rol dels que existeixen a la bd
		Producte r = new Producte();
		r.setId(1l);
		r.setNom("Un altre");
		r.setIdUnitat(1l);
		r.setIdTipusProducte(2l);
		// El modifico
		dao.modificar(r);
		// El recupero i comprobo que està modificat
		Producte nou = dao.obtenirPerId(1l);
		Assert.assertEquals(r, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		ProducteDAO dao = new ProducteDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		Producte r = dao.obtenirPerId(1l);
		Assert.assertNotNull(r);
		// Esborrem l'objecte
		dao.esborrar(r.getId());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		Producte rEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(rEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		ProducteDAO dao = new ProducteDAO(createDataSource());
		List<Producte> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres objectes
		Assert.assertEquals(3, tots.size());
		// Comprovem que un a un són correctes
		Assert.assertEquals(tots.get(0).getId(), (Long)2l);
		Assert.assertEquals(tots.get(0).getNom(), "Arros");
		Assert.assertEquals(tots.get(0).getDescripcioCurta(), "Arros thai amb chinchis");
		Assert.assertEquals(tots.get(0).getDescripcio(), "Arros thai amb aires de grandesa i farciment de chinchis de Nairobi");
		Assert.assertTrue(sonIguals(tots.get(0).getIniciVigencia(), 2015, 1, 24, 0, 0, 0));
		Assert.assertTrue(sonIguals(tots.get(0).getFiVigencia(), 2015, 7, 14, 0, 0, 0));
		Assert.assertEquals(tots.get(0).getIdUnitat(), (Long)2l);
		Assert.assertEquals(tots.get(0).getIdTipusProducte(), (Long)1l);
		//
		Assert.assertEquals(tots.get(1).getId(), (Long)3l);
		Assert.assertEquals(tots.get(1).getNom(), "Coca0");
		Assert.assertEquals(tots.get(1).getDescripcioCurta(), "Cocacola zero");
		Assert.assertEquals(tots.get(1).getDescripcio(), "Cocacola amb edulcorants que fan que sembli uai");
		Assert.assertTrue(sonIguals(tots.get(1).getIniciVigencia(), 1999, 1, 1, 0, 0, 0));
		Assert.assertNull(tots.get(1).getFiVigencia());
		Assert.assertEquals(tots.get(1).getIdUnitat(), (Long)1l);
		Assert.assertEquals(tots.get(1).getIdTipusProducte(), (Long)3l);
		//
		Assert.assertEquals(tots.get(2).getId(), (Long)1l);
		Assert.assertEquals(tots.get(2).getNom(), "Wok-fideus");
		Assert.assertEquals(tots.get(2).getDescripcioCurta(), "Wok de fideus amb gambes");
		Assert.assertEquals(tots.get(2).getDescripcio(), "Wok de fideus fins de macadamia amb gambes oblongues");
		Assert.assertTrue(sonIguals(tots.get(2).getIniciVigencia(), 2015, 1, 24, 0, 0, 0));
		Assert.assertTrue(sonIguals(tots.get(2).getFiVigencia(), 2015, 10, 24, 0, 0, 0));
		Assert.assertEquals(tots.get(2).getIdUnitat(), (Long)2l);
		Assert.assertEquals(tots.get(2).getIdTipusProducte(), (Long)1l);
	}
}
