package com.soc.ewok.test.model;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.TipusProducte;


public class TipusProducteTest {
	
	private TipusProducte crearTipusProducte() {
		TipusProducte tp = new TipusProducte();
		tp.setId(1l);
		tp.setNom("nom Tipus Producte");
		tp.setDescripcio("descripcio Tipus Producte");
		return tp;
	}
	
	@Test
	public void comprovacioGettersISetters() {
		TipusProducte tp = crearTipusProducte();
		Assert.assertEquals((Long)1l, tp.getId());
		Assert.assertEquals("nom Tipus Producte", tp.getNom());
		Assert.assertEquals("descripcio Tipus Producte", tp.getDescripcio());
		tp.setId(null);
		Assert.assertNull(tp.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarNomNoPotSerNull() {
		TipusProducte tp = new TipusProducte();
		tp.setNom(null);
		tp.setDescripcio(null);
	}
	
	@Test
	public void comprovarEquals() {
		TipusProducte tp1 = crearTipusProducte();
		TipusProducte tp2 = crearTipusProducte();
		Assert.assertEquals(tp1, tp2);
		tp2.setId(2l);
		Assert.assertNotEquals(tp1, tp2); 
	}
}
