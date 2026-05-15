package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.TipusProducteDAO;
import com.soc.ewok.model.TipusProducte;

public class TipusProducteDAOTest extends AbstractDAOTest {
	
	@Override
	public void preparaBD() throws SQLException {
		
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		
		// Executem l'script de càrrega de dades de TipusProducte
		executaScript("test/resources/scripts/TipusProducteData.sql"); 
	}

	@Test
	public void altaCorrecta() throws SQLException {
		
		TipusProducteDAO dao = new TipusProducteDAO(createDataSource());
		
		// Creo un TipusProducte
		TipusProducte tp = new TipusProducte();
		
		// Omplo les dades
		tp.setNom("Nom");
		tp.setCodi("Codi");
		
		// Donc d'alta el Tipus de producte
		dao.alta(tp);
		
		// Recupero de nou el tipus de producte
		// (alta m'ha d'haver assignat el nou id)
		TipusProducte nou = dao.obtenirPerId(tp.getId());
		
		// Miro que els dos objectes són iguals
		Assert.assertEquals(tp, nou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		
		TipusProducteDAO dao = new TipusProducteDAO(createDataSource());
		
		// Obtinc un tipus de producte desde base de dades
		TipusProducte tp = dao.obtenirPerId(1l);
		
		// El modifico
		tp.setNom("Un altre");
		tp.setCodi("Codi Nou");
		dao.modificar(tp);
		
		// El recupero i comprobo que està modificat
		TipusProducte nou = dao.obtenirPerId(1l);
		Assert.assertEquals(tp, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		
		TipusProducteDAO dao = new TipusProducteDAO(createDataSource());
		
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		TipusProducte tp = dao.obtenirPerId(1l);
		Assert.assertNotNull(tp);
		
		// Esborrem l'objecte
		dao.esborrar(tp.getId());
		
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		TipusProducte tpEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(tpEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		TipusProducteDAO dao = new TipusProducteDAO(createDataSource());
		List<TipusProducte> tots = dao.obtenirTots();
		
		// Comprovem que hem rebut totas les unitat
		Assert.assertEquals(3, tots.size());
		
		// Comprovem que el un a un són correctes
		Assert.assertEquals(tots.get(0).getId(), (Long)1l);
		Assert.assertEquals(tots.get(0).getNom(), "Ingredientes");
		Assert.assertEquals(tots.get(0).getCodi(), "01");
		
		Assert.assertEquals(tots.get(1).getId(), (Long)2l);
		Assert.assertEquals(tots.get(1).getNom(), "Menu");
		Assert.assertEquals(tots.get(1).getCodi(), "02");
		
		Assert.assertEquals(tots.get(2).getId(), (Long)3l);
		Assert.assertEquals(tots.get(2).getNom(), "Plato Predefinido");
		Assert.assertEquals(tots.get(2).getCodi(), "03");
	}

}
