package com.soc.ewok.test.model.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.dao.PagamentDAO;
import com.soc.ewok.model.Pagament;


public class PagamentDAOTest extends AbstractDAOTest {
	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de rol
		executaScript("test/resources/scripts/PagamentData.sql"); 
	}

	@Test
	public void altaCorrecta() throws SQLException {
		PagamentDAO dao = new PagamentDAO(createDataSource());
		// Creo un pagament
		Pagament p = new Pagament();
		// Omplo les dades
		// Creo una data Timestamp
		@SuppressWarnings("deprecation")
		Timestamp data = new Timestamp(2015, 01, 01, 23, 10, 10, 0);
		p.setnIdComanda(99l);
		p.setQuantitat((float) 999.99);
		p.setIdFormaPagament(9l);
		p.setData(data);
				
		// Donc d'alta el rol
		dao.alta(p);
		// Recupero de nou el rol
		// (alta m'ha d'haver assignat el nou id)
		Pagament nou = dao.obtenirPerId(p.getnId());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(p, nou);
	}
	
	@Test
	public void modificacioCorrecta() throws SQLException {
		PagamentDAO dao = new PagamentDAO(createDataSource());
		// Creo un Pagament dels que existeixen a la bd
		Pagament p = new Pagament();
		// Creo una data Timestamp
		@SuppressWarnings("deprecation")
		Timestamp data = new Timestamp(2015, 01, 01, 23, 10, 10, 0);
		p.setnId(1l);
		p.setnIdComanda(1l);
		p.setQuantitat((float) 123.99);
		p.setIdFormaPagament(1l);
		p.setData(data);
		
		// El modifico
		dao.modificar(p);
		// El recupero i comprobo que està modificat
		Pagament nou = dao.obtenirPerId(1l);
		Assert.assertEquals(p, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		PagamentDAO dao = new PagamentDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		Pagament p = dao.obtenirPerId(1l);
		Assert.assertNotNull(p);
		// Esborrem l'objecte
		dao.esborrar(p.getnId());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		Pagament pEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(pEsborrat);
	}
	
	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		PagamentDAO dao = new PagamentDAO(createDataSource());
		List<Pagament> tots = dao.obtenirTots();
		// Comprovem que hem rebut tres rols
		Assert.assertEquals(3, tots.size());
		// Comprovem que el un a un són correctes
		Assert.assertEquals(tots.get(0).getnId(), (Long)1l);
		Assert.assertEquals(tots.get(0).getnIdComanda(), (Long)1l);
		Assert.assertEquals((float)tots.get(0).getQuantitat(),(float)123.45,(float)0.00);
		Assert.assertEquals((long)tots.get(0).getIdFormaPagament(), 1l);
		Assert.assertTrue(sonIguals(tots.get(0).getData(), 2015, 01, 01, 21, 00, 00));
		
		Assert.assertEquals(tots.get(1).getnId(), (Long)2l);
		Assert.assertEquals(tots.get(1).getnIdComanda(), (Long)2l);
		Assert.assertEquals((float)tots.get(1).getQuantitat(), (float)678.90,(float)0.00);
		Assert.assertEquals((long)tots.get(1).getIdFormaPagament(), 2l);
		Assert.assertTrue(sonIguals(tots.get(1).getData(), 2016, 02, 02, 10, 00, 00));
		
		Assert.assertEquals(tots.get(2).getnId(), (Long)3l);
		Assert.assertEquals(tots.get(2).getnIdComanda(), (Long)3l);
		Assert.assertEquals((float)tots.get(2).getQuantitat(), (float)123.45, (float)0.00);
		Assert.assertEquals((long)tots.get(2).getIdFormaPagament(), 3l);
		Assert.assertTrue(sonIguals(tots.get(2).getData(), 2017, 03, 03, 8, 00, 00));
	}
}
