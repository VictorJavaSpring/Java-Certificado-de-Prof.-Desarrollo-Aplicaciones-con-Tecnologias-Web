package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.FormaPagamentDAO;
import com.soc.ewok.dao.RolDAO;
import com.soc.ewok.model.FormaPagament;
import com.soc.ewok.model.Rol;

public class FormaPagamentDAOTest extends AbstractDAOTest{
	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de rol
		executaScript("test/resources/scripts/FormaPagamentData.sql"); 
	}
	
	@Test
	public void altaCorrecta() throws SQLException {
		FormaPagamentDAO dao = new FormaPagamentDAO(createDataSource());
		// Creo una forma de pagament
		FormaPagament fp = new FormaPagament();
		// Omplo les dades
		fp.setsNom("NovaFormaPagament");
		// Donc d'alta el rol
		dao.alta(fp);
		// Recupero de nou la forma de pagament
		// (alta m'ha d'haver assignat el nou id)
		FormaPagament nova = dao.obtenirPerId(fp.getnId());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(fp, nova);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		FormaPagamentDAO dao = new FormaPagamentDAO(createDataSource());
		// Obtinc un rol desde base de dades
		FormaPagament fp = dao.obtenirPerId(1l);
		// El modifico
		fp.setsNom("Un altre forma de pagament");
		dao.modificar(fp);
		// El recupero i comprobo que està modificat
		FormaPagament nova = dao.obtenirPerId(1l);
		Assert.assertEquals(fp, nova);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		FormaPagamentDAO dao = new FormaPagamentDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		FormaPagament fp = dao.obtenirPerId(1l);
		Assert.assertNotNull(fp);
		// Esborrem l'objecte
		dao.esborrar(fp.getnId());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		FormaPagament fpEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(fpEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		FormaPagamentDAO dao = new FormaPagamentDAO(createDataSource());
		List<FormaPagament> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres rols
		Assert.assertEquals(3, tots.size());
		// Comprovem que el un a un són correctes
		Assert.assertEquals(tots.get(0).getnId(), (Long)1l);
		Assert.assertEquals(tots.get(0).getsNom(), "Efectiu");
		Assert.assertEquals(tots.get(1).getnId(), (Long)2l);
		Assert.assertEquals(tots.get(1).getsNom(), "Visa");
		Assert.assertEquals(tots.get(2).getnId(), (Long)3l);
		Assert.assertEquals(tots.get(2).getsNom(), "Xecs");
	}
}
