package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.XecDAO;
import com.soc.ewok.model.Xec;

public class XecDAOTest extends AbstractDAOTest {
	
	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de xec
		// (En aquest cas també crea la taula associada)
		executaScript("test/resources/scripts/Xec.sql"); 
	}

	@SuppressWarnings("deprecation")
	@Test
	public void altaCorrecta() throws SQLException {
		Date unaData = new Date();
		XecDAO dao = new XecDAO(createDataSource());
		// Creo un xec
		Xec x = new Xec();
		// Omplo les dades
		x.setNumPunts((short)4);
		x.setDataCaducitat(unaData);
		x.setIdComanda(10l);
		x.setIdClient(33l);
		// Donc d'alta el xec
		dao.alta(x);
		// Recupero de nou el xec
		// (alta m'ha d'haver assignat el nou id)
		Xec xNou = dao.obtenirPerId(x.getId());
		// Comprovem que les dates son iguals
		Assert.assertEquals(
			new Date(
					x.getDataCaducitat().getYear(), 
					x.getDataCaducitat().getMonth() ,
					x.getDataCaducitat().getDay()) 
			,
			new Date(
					xNou.getDataCaducitat().getYear(), 
					xNou.getDataCaducitat().getMonth() ,
					xNou.getDataCaducitat().getDay()) 
				);
		// Un cop veiem que són iguals, la copiem del vell al
		// nou per no tenir problemes amb l'equals
		xNou.setDataCaducitat(x.getDataCaducitat());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(x, xNou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		XecDAO dao = new XecDAO(createDataSource());
		// Obtinc un xec desde base de dades
		Xec x = dao.obtenirPerId(1l);
		// El modifico
		x.setNumPunts((short)5);
		dao.modificar(x);
		// El recupero i comprobo que està modificat
		Xec xNou = dao.obtenirPerId(1l);
		Assert.assertEquals(x, xNou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		XecDAO dao = new XecDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		Xec x = dao.obtenirPerId(1l);
		Assert.assertNotNull(x);
		// Esborrem l'objecte
		dao.esborrar(x.getId());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		Xec xEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(xEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		XecDAO dao = new XecDAO(createDataSource());
		List<Xec> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres xec
		Assert.assertEquals(3, tots.size());
		// Comprovem un a un que són correctos
		Assert.assertEquals(tots.get(0).getId(), (Long)1l);
		Assert.assertEquals((short)tots.get(0).getNumPunts(), (short)4);
		Assert.assertTrue(sonIguals(tots.get(0).getDataCaducitat(), 2015, 5, 1, 0, 0, 0));
		Assert.assertEquals(tots.get(0).getIdComanda(), (Long)17l);
		Assert.assertEquals(tots.get(0).getIdClient(), (Long)1200l);
		
		Assert.assertEquals(tots.get(1).getId(), (Long)2l);
		Assert.assertEquals((short)tots.get(1).getNumPunts(), (short)4);
		Assert.assertTrue(sonIguals(tots.get(1).getDataCaducitat(), 2015, 5, 15, 0, 0, 0));
		Assert.assertEquals(tots.get(1).getIdComanda(), (Long)20l);
		Assert.assertEquals(tots.get(1).getIdClient(), (Long)69l);
		
		Assert.assertEquals(tots.get(2).getId(), (Long)3l);
		Assert.assertEquals((short)tots.get(2).getNumPunts(), (short)4);
		Assert.assertTrue(sonIguals(tots.get(2).getDataCaducitat(), 2015, 5, 26, 0, 0, 0));
		Assert.assertEquals(tots.get(2).getIdComanda(), (Long)25l);
		Assert.assertEquals(tots.get(2).getIdClient(), (Long)800l);
	}
}
