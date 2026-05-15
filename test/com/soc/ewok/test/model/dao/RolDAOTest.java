package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.RolDAO;
import com.soc.ewok.model.Rol;

public class RolDAOTest extends AbstractDAOTest {

	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de rol
		executaScript("test/resources/scripts/RolData.sql");
	}

	@Test
	public void altaCorrecta() throws SQLException {
		RolDAO dao = new RolDAO(createDataSource());
		// Creo un rol
		Rol r = new Rol();
		// Omplo les dades
		r.setNom("Nom");
		r.setCodi("C4Mindundi");
		// Donc d'alta el rol
		dao.alta(r);
		// Recupero de nou el rol
		// (alta m'ha d'haver assignat el nou id)
		Rol nou = dao.obtenirPerId(r.getId());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(r, nou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		RolDAO dao = new RolDAO(createDataSource());
		// Obtinc un rol desde base de dades
		Rol r = dao.obtenirPerId(1l);
		// El modifico
		r.setNom("Un altre");
		dao.modificar(r);
		// El recupero i comprobo que està modificat
		Rol nou = dao.obtenirPerId(1l);
		Assert.assertEquals(r, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		RolDAO dao = new RolDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		Rol r = dao.obtenirPerId(1l);
		Assert.assertNotNull(r);
		// Esborrem l'objecte
		dao.esborrar(r.getId());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		Rol rEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(rEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		RolDAO dao = new RolDAO(createDataSource());
		List<Rol> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres rols
		Assert.assertEquals(3, tots.size());
		// Comprovem que el un a un són correctes
		Assert.assertEquals(tots.get(0).getId(), (Long)1l);
		Assert.assertEquals(tots.get(0).getNom(), "Administrador");
		Assert.assertEquals(tots.get(0).getCodi(), "C1Administ");
		//
		Assert.assertEquals(tots.get(1).getId(), (Long)3l);
		Assert.assertEquals(tots.get(1).getNom(), "Caixer");
		Assert.assertEquals(tots.get(1).getCodi(), "C3Caixer");
		//
		Assert.assertEquals(tots.get(2).getId(), (Long)2l);
		Assert.assertEquals(tots.get(2).getNom(), "Cuiner");
		Assert.assertEquals(tots.get(2).getCodi(), "C2Cuiner");
	}
}
