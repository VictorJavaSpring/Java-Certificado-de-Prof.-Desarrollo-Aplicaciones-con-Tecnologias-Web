package com.soc.ewok.test.model.exemple;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.test.model.dao.AbstractDAOTest;

public class RolDataDAOTest extends AbstractDAOTest {

	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de rol
		// (En aquest cas també crea la taula associada)
		executaScript("test/com/soc/ewok/test/model/exemple/RolDataScript.sql"); 
	}

	@Test
	public void altaCorrecta() throws SQLException {
		Date unaData = new Date();
		RolDataDAO dao = new RolDataDAO(createDataSource());
		// Creo un rol
		RolData r = new RolData();
		// Omplo les dades
		r.setNom("Nom");
		r.setDataCreacio(unaData);
		// Donc d'alta el rol
		dao.alta(r);
		// Recupero de nou el rol
		// (alta m'ha d'haver assignat el nou id)
		RolData nou = dao.obtenirPerId(r.getId());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(r, nou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		RolDataDAO dao = new RolDataDAO(createDataSource());
		// Obtinc un rol desde base de dades
		RolData r = dao.obtenirPerId(1l);
		// El modifico
		r.setNom("Un altre");
		dao.modificar(r);
		// El recupero i comprobo que està modificat
		RolData nou = dao.obtenirPerId(1l);
		Assert.assertEquals(r, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		RolDataDAO dao = new RolDataDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		RolData r = dao.obtenirPerId(1l);
		Assert.assertNotNull(r);
		// Esborrem l'objecte
		dao.esborrar(r.getId());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		RolData rEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(rEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		RolDataDAO dao = new RolDataDAO(createDataSource());
		List<RolData> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres rols
		Assert.assertEquals(3, tots.size());
		// Comprovem que el un a un són correctes
		Assert.assertEquals(tots.get(0).getId(), (Long)1l);
		Assert.assertEquals(tots.get(0).getNom(), "Administrador");
		Assert.assertTrue(sonIguals(tots.get(0).getDataCreacio(), 2015, 4, 1, 21, 0, 0));
		Assert.assertEquals(tots.get(1).getId(), (Long)3l);
		Assert.assertEquals(tots.get(1).getNom(), "Caixer");
		Assert.assertTrue(sonIguals(tots.get(1).getDataCreacio(), 2016, 1, 1, 0, 0, 0));
		Assert.assertEquals(tots.get(2).getId(), (Long)2l);
		Assert.assertEquals(tots.get(2).getNom(), "Cuiner");
		Assert.assertTrue(sonIguals(tots.get(2).getDataCreacio(), 2010, 5, 23, 12, 13, 14));
	}

}
