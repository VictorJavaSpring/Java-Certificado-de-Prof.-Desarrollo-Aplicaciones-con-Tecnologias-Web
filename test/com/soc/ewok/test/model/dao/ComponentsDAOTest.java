package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.ComponentsDAO;
import com.soc.ewok.model.Components;

public class ComponentsDAOTest extends AbstractDAOTest {
	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de components
		executaScript("test/resources/scripts/ComponentsData.sql"); 
	}

	@Test
	public void altaCorrecta() throws SQLException {
		ComponentsDAO dao = new ComponentsDAO(createDataSource());
		// Creo un component
		Components c = new Components();
		// Omplo les dades
		c.setIdProducte(4l);
		c.setIdComponent(4l);
		c.setQuantitat(4);
		c.setOrdre(4);
		// Dono d'alta el component
		dao.alta(c);
		// Recupero de nou el component
		// (alta m'ha d'haver assignat el nou id)
		Components nou = dao.obtenirPerId(c.getIdProducte(), c.getIdComponent());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(c, nou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		ComponentsDAO dao = new ComponentsDAO(createDataSource());
		// Obtinc un component des de base de dades
		Components c = dao.obtenirPerId(1l, 1l);
		// El modifico
		c.setQuantitat(1);
		c.setOrdre(1);
		dao.modificar(c);
		// El recupero i comprobo que està modificat
		Components nou = dao.obtenirPerId(1l, 1l);
		Assert.assertEquals(c, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		ComponentsDAO dao = new ComponentsDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		Components c = dao.obtenirPerId(1l, 1l);
		Assert.assertNotNull(c);
		// Esborrem l'objecte
		dao.esborrar(c.getIdProducte(), c.getIdComponent());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		Components cEsborrat = dao.obtenirPerId(1l, 1l);
		Assert.assertNull(cEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		ComponentsDAO dao = new ComponentsDAO(createDataSource());
		List<Components> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres components
		Assert.assertEquals(3, tots.size());
		// Comprovem que un a un són correctes
		Assert.assertEquals(tots.get(0).getIdProducte(), (Long)1l);
		Assert.assertEquals(tots.get(0).getIdComponent(), (Long)1l);
		Assert.assertEquals(tots.get(0).getQuantitat(), (Integer)1);
		Assert.assertEquals(tots.get(0).getOrdre(), (Integer)1);
		
		Assert.assertEquals(tots.get(1).getIdProducte(), (Long)2l);
		Assert.assertEquals(tots.get(1).getIdComponent(), (Long)2l);
		Assert.assertEquals(tots.get(1).getQuantitat(), (Integer)2);
		Assert.assertEquals(tots.get(1).getOrdre(), (Integer)2);
		
		Assert.assertEquals(tots.get(2).getIdProducte(), (Long)3l);
		Assert.assertEquals(tots.get(2).getIdComponent(), (Long)3l);
		Assert.assertEquals(tots.get(2).getQuantitat(), (Integer)3);
		Assert.assertEquals(tots.get(2).getOrdre(), (Integer)3);
	}
}
