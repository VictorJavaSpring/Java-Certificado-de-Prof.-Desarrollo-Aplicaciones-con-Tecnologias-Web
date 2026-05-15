package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.UnitatDAO;
import com.soc.ewok.model.Unitat;

public class UnitatDAOTest extends AbstractDAOTest {

	@Override
	public void preparaBD() throws SQLException {
		
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		
		// Executem l'script de càrrega de dades de rol
		executaScript("test/resources/scripts/UnitatData.sql"); 
	}

	@Test
	public void altaCorrecta() throws SQLException {
		
		UnitatDAO dao = new UnitatDAO(createDataSource());
		
		// Creo una Unitat
		Unitat u = new Unitat();
		
		// Omplo les dades
		u.setNom("Nom");
		u.setAcron("Acronim");
		
		// Donc d'alta la unitat
		dao.alta(u);
		
		// Recupero de nou la unitat
		// (alta m'ha d'haver assignat el nou id)
		Unitat nou = dao.obtenirPerId(u.getId());
		
		// Miro que els dos objectes són iguals
		Assert.assertEquals(u, nou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		
		UnitatDAO dao = new UnitatDAO(createDataSource());
		
		// Obtinc una unitat desde base de dades
		Unitat u = dao.obtenirPerId(1l);
		
		// El modifico
		u.setNom("Un altre");
		u.setAcron("Acronim");
		dao.modificar(u);
		
		// El recupero i comprobo que està modificat
		Unitat nou = dao.obtenirPerId(1l);
		Assert.assertEquals(u, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		
		UnitatDAO dao = new UnitatDAO(createDataSource());
		
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		Unitat u = dao.obtenirPerId(1l);
		Assert.assertNotNull(u);
		
		// Esborrem l'objecte
		dao.esborrar(u.getId());
		
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		Unitat uEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(uEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		UnitatDAO dao = new UnitatDAO(createDataSource());
		List<Unitat> tots = dao.obtenirTots();
		
		// Comprovem que hem rebut totas les unitat
		Assert.assertEquals(3, tots.size());
		
		// Comprovem que el un a un són correctes
		Assert.assertEquals(tots.get(0).getId(), (Long)1l);
		Assert.assertEquals(tots.get(0).getNom(), "Carne");
		Assert.assertEquals(tots.get(0).getAcron(), "Kg");
		Assert.assertEquals(tots.get(1).getId(), (Long)3l);
		Assert.assertEquals(tots.get(1).getNom(), "Nuez Mozcada");
		Assert.assertEquals(tots.get(1).getAcron(), "Gr");
		Assert.assertEquals(tots.get(2).getId(), (Long)2l);
		Assert.assertEquals(tots.get(2).getNom(), "Salsa de Tomate");
		Assert.assertEquals(tots.get(2).getAcron(), "L");
	}
}

