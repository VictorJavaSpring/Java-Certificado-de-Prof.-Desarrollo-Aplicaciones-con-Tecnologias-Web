package com.soc.ewok.test.model;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.Preu;

public class PreuTest{
	Date dInicial = new Date();
	Date dFinal = new Date(dInicial.getTime() + 100000);
	
	private Preu crearPreu() {
		Preu p = new Preu();
		p.setId(1l);
		p.setPreu(10.55f);
		p.setIniciVigencia(dInicial);
		p.setFinalVigencia(dFinal);
		p.setIdProducte(2l);
		return p;
	}
	
	@Test
	public void comprovacioGettersISetters() {
		Preu p = crearPreu();
		Assert.assertEquals((Long)1l, p.getId());
		Assert.assertEquals((Float)10.55f, p.getPreu());
		Assert.assertEquals(dInicial, p.getIniciVigencia());
		Assert.assertEquals(dFinal, p.getFinalVigencia());
		Assert.assertEquals((Long)2l, p.getIdProducte());
		p.setId(null);
		p.setIdProducte(null);
		Assert.assertNull(p.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarPreuNoPotSerNull() {
		Preu p = new Preu();
		p.setPreu(null);
	}
	
	@Test
	public void comprovarEquals() {
		Preu p1 = crearPreu();
		Preu p2 = crearPreu();
		Assert.assertEquals(p1, p2);
		p2.setId(2l);
		Assert.assertNotEquals(p1, p2);
	}
	
}
