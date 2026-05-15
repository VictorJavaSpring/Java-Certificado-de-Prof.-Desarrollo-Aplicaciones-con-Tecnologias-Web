package com.soc.ewok.test.model;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.OfertaComanda;

public class OfertaComandaTest {
	//creo dates accessibles des de qualsevol mètode 
	//per a provar iniciVigencia i fiVigencia on calgui
	
	Date data1 = new Date();
	Date data2 = new Date(data1.getTime() + 1000000);

	private OfertaComanda crearOfertaComanda() {
		OfertaComanda ofc = new OfertaComanda();
		ofc.setId(1l);
		ofc.setLimitInferior(10.50F);
		ofc.setPctDescompte(9.5F);
		ofc.setIniciVigencia(data1);
		ofc.setFiVigencia(data2);
		return ofc;
	}
	
	@Test
	public void checkGettersISetters() {
		OfertaComanda of = crearOfertaComanda();
		Assert.assertEquals((Long)1l, of.getId());
		Assert.assertEquals((Float)10.50F, of.getLimitInferior());
		Assert.assertEquals((Float)9.5F, of.getPctDescompte());
		Assert.assertEquals(data1, of.getIniciVigencia());
		Assert.assertEquals(data2, of.getFiVigencia());		
		of.setId(null);
		Assert.assertNull(of.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void checkLimitInferiorNotNull() {
		OfertaComanda of = new OfertaComanda();
		of.setLimitInferior(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void checkPctDescompteNotNull() {
		OfertaComanda of = new OfertaComanda();
		of.setPctDescompte(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void checkIniciVigenciaNotNull() {
		OfertaComanda of = new OfertaComanda();
		of.setIniciVigencia(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void checkFiVigenciaNotNull() {
		OfertaComanda of = new OfertaComanda();
		of.setFiVigencia(null);
	}
	
	@Test
	public void checkEquals() {
		OfertaComanda ofc1 = crearOfertaComanda();
		OfertaComanda ofc2 = crearOfertaComanda();
		Assert.assertEquals(ofc1, ofc2);
		ofc2.setId(222l);
		Assert.assertNotEquals(ofc1, ofc2);
	}
}
