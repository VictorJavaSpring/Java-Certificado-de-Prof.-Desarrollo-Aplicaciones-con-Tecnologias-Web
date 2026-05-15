package com.soc.ewok.test.model.dao;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.soc.ewok.dao.OfertaPuntsDAO;
import com.soc.ewok.model.OfertaPunts;



public class OfertaPuntsDAOTest extends AbstractDAOTest {
	private Date unaData = new Date();
	
	@Override
	public void preparaBD() throws SQLException {
		// Creem l'esquema a través de la classe base
		super.preparaBD();
		// Executem l'script de càrrega de dades de rol
		executaScript("test/resources/scripts/OfertaPuntsData.sql"); 
	}
	
	@Test
	public void altaCorrecta() throws SQLException {
		OfertaPuntsDAO dao = new OfertaPuntsDAO(createDataSource());
		// Creo OfertaPunts
		OfertaPunts op = new OfertaPunts();
		// Omplo les dades
		op.setEurosPerPunt(1.1F);
		op.setPuntsPerXec((short)1.1);
		op.setDiesVigenciaPunts(1L);
		op.setDiesVigenciaXecs(1L);
		op.setIniciVigencia(unaData);
		op.setFiVigencia(unaData);
		
		// Donc d'alta OfertaPunts
		dao.alta(op);
		// Recupero la nova OfertaPunts
		// (alta m'ha d'haver assignat el nou id)
		OfertaPunts nou = dao.obtenirPerId(op.getId());
		// Miro que els dos objectes són iguals
		Assert.assertEquals(op, nou);
	}
	
	

	@Test
	public void modificacioCorrecta() throws SQLException {
		OfertaPuntsDAO dao = new OfertaPuntsDAO(createDataSource());		
		// Obtinc una OfertaPunts desde base de dades
		OfertaPunts op = dao.obtenirPerId(1l);		
		// la modifico
		op.setEurosPerPunt(1.1F);
			dao.modificar(op);
		// La recupero i comprobo que està modificada
		OfertaPunts nou = dao.obtenirPerId(1l);
		Assert.assertEquals(op, nou);
	}
	
	@Test
	public void eliminacioCorrecta() throws SQLException {
		OfertaPuntsDAO dao = new OfertaPuntsDAO(createDataSource());
		// Recuperem l'objecte que esborrarem
		// per assegurar-nos que hi és
		OfertaPunts op = dao.obtenirPerId(1l);
		Assert.assertNotNull(op);
		// Esborrem l'objecte
		dao.esborrar(op.getId());
		// Comprovem que si intentem tornar
		// a recuperar-lo, no l'obtenim
		OfertaPunts rEsborrat = dao.obtenirPerId(1l);
		Assert.assertNull(rEsborrat);
	}

	@Test
	public void obtenirTotsCorrecte() throws SQLException {
		OfertaPuntsDAO dao = new OfertaPuntsDAO(createDataSource());
		List<OfertaPunts> tots = dao.obtenirTots();
		
		
		// Comprovem que hem rebut 3 Ofertes de  Punts
		
		Assert.assertEquals(3, tots.size());
		// Comprovem que una a una són correctes
				
		Assert.assertEquals(tots.get(0).getId(), (Long)1l);
		Assert.assertEquals(tots.get(0).getEurosPerPunt(), (float)1,1f);
		Assert.assertEquals(tots.get(0).getPuntsPerXec(),(float)1,1f);
		Assert.assertEquals(tots.get(0).getDiesVigenciaPunts(), (Long)1l);
		Assert.assertEquals(tots.get(0).getDiesVigenciaXecs(), (Long)1l);	 
		Assert.assertTrue(sonIguals(tots.get(0).getIniciVigencia(), 2010, 03, 20, 0, 0, 0));
		Assert.assertTrue(sonIguals(tots.get(0).getFiVigencia(), 2010, 03, 21, 0, 0, 0));
		
		Assert.assertEquals(tots.get(1).getId(), (Long)2l);
		Assert.assertEquals(tots.get(1).getEurosPerPunt(),(float)2,2f);
		Assert.assertEquals(tots.get(1).getPuntsPerXec(), (float)2,2f);
		Assert.assertEquals(tots.get(1).getDiesVigenciaPunts(), (Long)1l);
		Assert.assertEquals(tots.get(1).getDiesVigenciaXecs(), (Long)1l);	
	    Assert.assertTrue(sonIguals(tots.get(1).getIniciVigencia(), 2010, 03, 21, 0, 0, 0));
		Assert.assertTrue(sonIguals(tots.get(1).getFiVigencia(), 2010, 03, 22, 0, 0, 0));
		
		Assert.assertEquals(tots.get(2).getId(), (Long)3l);
		Assert.assertEquals(tots.get(2).getEurosPerPunt(),(float)3,3f);
		Assert.assertEquals(tots.get(2).getPuntsPerXec(),(float)3,3f);
		Assert.assertEquals(tots.get(2).getDiesVigenciaPunts(), (Long)1l);
		Assert.assertEquals(tots.get(2).getDiesVigenciaXecs(), (Long)1l);	
		Assert.assertTrue(sonIguals(tots.get(2).getIniciVigencia(), 2010, 03, 22, 0, 0, 0));
		Assert.assertTrue(sonIguals(tots.get(2).getFiVigencia(), 2010, 03, 23, 0, 0, 0));


	}
}

