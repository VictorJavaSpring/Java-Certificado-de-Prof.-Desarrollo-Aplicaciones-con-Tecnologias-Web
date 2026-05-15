package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.PuntsPendentsDAO;
import com.soc.ewok.model.PuntsPendents;

public class PuntsPendentsDAOTest extends AbstractDAOTest {
	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de PuntsPendents
		// (En aquest cas també crea la taula associada)
		executaScript("test/resources/scripts/PuntsPendents.sql"); 
	}

	@SuppressWarnings("deprecation")
	@Test
	public void altaCorrecta() throws SQLException {
		Date unaData = new Date();
		PuntsPendentsDAO dao = new PuntsPendentsDAO(createDataSource());
		// Creo un PuntsPendents
		PuntsPendents pp = new PuntsPendents();
		// Omplo les dades
		pp.setNumPunts((short)3);
		pp.setDataCaducitat(unaData);
		pp.setIdClient(45l);
		// Donc d'alta el PuntsPendents
		dao.alta(pp);
		// Recupero de nou el PuntsPendents
		// (alta m'ha d'haver assignat el nou id)
		PuntsPendents ppNou = dao.obtenirPerId(pp.getId());
		// Comprovem que les dates son iguals
		Assert.assertEquals(
				new Date(
						pp.getDataCaducitat().getYear(), 
						pp.getDataCaducitat().getMonth() ,
						pp.getDataCaducitat().getDay()) 
				,
				new Date(
						ppNou.getDataCaducitat().getYear(), 
						ppNou.getDataCaducitat().getMonth() ,
						ppNou.getDataCaducitat().getDay()) 
					);
			// Un cop veiem que són iguals, la copiem del vell al
			// nou per no tenir problemes amb l'equals
			ppNou.setDataCaducitat(pp.getDataCaducitat());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(pp, ppNou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		PuntsPendentsDAO dao = new PuntsPendentsDAO(createDataSource());
		// Obtinc un PuntsPendents desde base de dades
		PuntsPendents pp = dao.obtenirPerId(1l);
		// El modifico
		pp.setNumPunts((short)2);
		dao.modificar(pp);
		// El recupero i comprobo que està modificat
		PuntsPendents ppNou = dao.obtenirPerId(1l);
		Assert.assertEquals(pp, ppNou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		PuntsPendentsDAO dao = new PuntsPendentsDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		PuntsPendents pp = dao.obtenirPerId(1l);
		Assert.assertNotNull(pp);
		// Esborrem l'objecte
		dao.esborrar(pp.getId());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		PuntsPendents ppEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(ppEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		PuntsPendentsDAO dao = new PuntsPendentsDAO(createDataSource());
		List<PuntsPendents> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres PuntsPendents
		Assert.assertEquals(3, tots.size());
		// Comprovem un a un que són correctos
		Assert.assertEquals(tots.get(0).getId(), (Long)1l);
		Assert.assertEquals((short)tots.get(0).getNumPunts(), (short)3);
		Assert.assertTrue(sonIguals(tots.get(0).getDataCaducitat(), 2015, 6, 3, 0, 0, 0));
		Assert.assertEquals(tots.get(0).getIdClient(), (Long)1200l);
		
		Assert.assertEquals(tots.get(1).getId(), (Long)2l);
		Assert.assertEquals((short)tots.get(1).getNumPunts(), (short)1);
		Assert.assertTrue(sonIguals(tots.get(1).getDataCaducitat(), 2015, 6, 14, 0, 0, 0));
		Assert.assertEquals(tots.get(1).getIdClient(), (Long)69l);
		
		Assert.assertEquals(tots.get(2).getId(), (Long)3l);
		Assert.assertEquals((short)tots.get(2).getNumPunts(), (short)2);
		Assert.assertTrue(sonIguals(tots.get(2).getDataCaducitat(), 2015, 6, 22, 0, 0, 0));
		Assert.assertEquals(tots.get(2).getIdClient(), (Long)800l);
	}

}
