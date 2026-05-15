package com.soc.ewok.test.model;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import com.soc.ewok.model.OfertaProducte;




public class OfertaProducteTest {
	
	Date datainici = new Date();
	Date datafinal = new Date(datainici.getTime() + 1000000);
	
	private OfertaProducte crearOfertaProducte() {
		OfertaProducte ofp = new OfertaProducte();
		ofp.setId((long) 1l);
		ofp.setPctDescompte(9.5F);
		ofp.setIniciVigencia(datainici);
		ofp.setFiVigencia(datafinal);
		ofp.setIdProducte((long) 1l);
		ofp.setNom("Oferta nom");
		ofp.setOtext("Oferta descripció");			
		return ofp;
	}
	
	@Test
	public void comprovacioGettersISetters() {
		OfertaProducte ofp = crearOfertaProducte();
		Assert.assertEquals((Long)1l, ofp.getId());
		Assert.assertEquals((Float)9.5F, ofp.getPctDescompte());
		Assert.assertEquals(datainici, ofp.getIniciVigencia());
		Assert.assertEquals(datafinal, ofp.getFiVigencia());
		Assert.assertEquals((Long)1l, ofp.getIdProducte());
		Assert.assertEquals("Oferta nom", ofp.getNom());
		Assert.assertEquals("Oferta descripció", ofp.getOtext());
		ofp.setId(null);
		Assert.assertNull(ofp.getId());
		Assert.assertEquals((Long)1l, ofp.getIdProducte());
		
	}
	@Test(expected=IllegalArgumentException.class)
	public void comprovarPctDescompteNotNull() {
		OfertaProducte ofp = new OfertaProducte();
		ofp.setPctDescompte(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarIniciVigenciaNotNull() {
		OfertaProducte ofp = new OfertaProducte();
		ofp.setIniciVigencia(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarFiVigenciaNotNull() {
		OfertaProducte ofp = new OfertaProducte();
		ofp.setFiVigencia(null);
	}

	@Test
	public void checkEquals() {
		OfertaProducte ofp1 = crearOfertaProducte();
		OfertaProducte ofp2 = crearOfertaProducte();
		Assert.assertEquals(ofp1, ofp2);
		ofp2.setId(2l);
		Assert.assertNotEquals(ofp1, ofp2);
	}
	
	
	
	
	
	
	
	
}
