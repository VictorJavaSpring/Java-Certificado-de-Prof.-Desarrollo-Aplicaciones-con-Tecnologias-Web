package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.LiniaComandaDAO;
import com.soc.ewok.dao.RolDAO;
import com.soc.ewok.model.EEstatLiniaComanda;
import com.soc.ewok.model.LiniaComanda;
import com.soc.ewok.model.Rol;


public class LiniaComandaDAOTest extends AbstractDAOTest {

	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de rol
		executaScript("test/resources/scripts/LiniaComandaData.sql"); 
	}

	@Test
	public void altaCorrecta() throws SQLException {
		LiniaComandaDAO dao = new LiniaComandaDAO(createDataSource());
		// Creo un LiniaComanda
		LiniaComanda l = new LiniaComanda();
		// Omplo les dades
		l.setId(1l);
		l.setLinia(1l);
		l.setIdProducte(22l);
		l.setQuantitat(50);
		l.setPreuBrut(3.22f);
		l.setPreuVenda(4.52f);
		l.setEstat(EEstatLiniaComanda.entregat);
		
		// Donc d'alta el Linia Comanda
		dao.alta(l);
		// Recupero de nou la Linia Comanda
		// (alta m'ha d'haver assignat el nou id)
		LiniaComanda nou = dao.obtenirPerId(l.getId(),l.getLinia());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(l, nou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		LiniaComandaDAO dao = new LiniaComandaDAO(createDataSource());
		// Obtinc un rol desde base de dades
		LiniaComanda l = dao.obtenirPerId(100l,1000l);
		// El modifico
		l.setIdProducte(33l);
		l.setQuantitat(60);
		l.setPreuBrut(5.55f);
		l.setPreuVenda(7.22f);
		l.setEstat(EEstatLiniaComanda.enPreparacio);
		dao.modificar(l);
		// El recupero i comprobo que està modificat
		LiniaComanda nou = dao.obtenirPerId(100l,1000l);
		Assert.assertEquals(l, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		LiniaComandaDAO dao = new LiniaComandaDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		LiniaComanda l = dao.obtenirPerId(100l,1000l);
		Assert.assertNotNull(l);
		// Esborrem l'objecte
		dao.esborrar(l.getId(),l.getLinia());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		LiniaComanda rEsborrat = dao.obtenirPerId(100l,1000l);
		Assert.assertNull(rEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		LiniaComandaDAO dao = new LiniaComandaDAO(createDataSource());
		List<LiniaComanda> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres rols
		Assert.assertEquals(3, tots.size());
		// Comprovem que un a un són correctes
		// objecte 1
		Assert.assertEquals(tots.get(0).getId(), (Long)100l);
		Assert.assertEquals(tots.get(0).getLinia(), (Long)1000l);
		Assert.assertEquals(tots.get(0).getIdProducte(),(Long)111l);
		Assert.assertEquals(tots.get(0).getQuantitat(),(Integer)11);
		Assert.assertEquals(tots.get(0).getPreuBrut(),(Float)1.11f);
		Assert.assertEquals(tots.get(0).getPreuVenda(),(Float)1.11f);
		Assert.assertEquals(tots.get(0).getEstat(),EEstatLiniaComanda.inicial);
		// objecte 2
		Assert.assertEquals(tots.get(1).getId(), (Long)101l);
		Assert.assertEquals(tots.get(1).getLinia(), (Long)1001l);
		Assert.assertEquals(tots.get(1).getIdProducte(), (Long)112l);
		Assert.assertEquals(tots.get(1).getQuantitat(),(Integer)22);
		Assert.assertEquals(tots.get(1).getPreuBrut(),(Float)2.22f);
		Assert.assertEquals(tots.get(1).getPreuVenda(),(Float)2.22f);
		Assert.assertEquals(tots.get(1).getEstat(),EEstatLiniaComanda.enPreparacio);
		// objecte 3
		Assert.assertEquals(tots.get(2).getId(), (Long)102l);
		Assert.assertEquals(tots.get(2).getLinia(), (Long)1002l);
		Assert.assertEquals(tots.get(2).getIdProducte(), (Long)113l);
		Assert.assertEquals(tots.get(2).getQuantitat(),(Integer)33);
		Assert.assertEquals(tots.get(2).getPreuBrut(),(Float)3.33f);
		Assert.assertEquals(tots.get(2).getPreuVenda(),(Float)3.33f);
		Assert.assertEquals(tots.get(2).getEstat(),EEstatLiniaComanda.preparat);
	}
}

	

	

