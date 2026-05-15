package com.soc.ewok.test.model;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.Comanda;
import com.soc.ewok.model.EEstatComanda;

public class ComandaTest {

	private Date dataTest = new Date();

	private Comanda crearComanda() {
		Comanda c = new Comanda();
		
		c.setId(1l);
		c.setIdClient(2l);
		c.setAdreca(3l);
		c.setComentaris("Comentaris de la Comanda");
		c.setData(dataTest);
		c.setDescompte(23.22f);
		c.setEstat(EEstatComanda.entregada);
		c.setPreuFinal(60.2f);
		c.setPunts((short)5);
		return c;
	}
	
	@Test
	public void comprovacioSettersIGetters(){
		Comanda c = crearComanda();

		Assert.assertEquals((Long)1l, c.getId());
		Assert.assertEquals((Long)2l, c.getIdClient());
		Assert.assertEquals((Long)3l, c.getAdreca());
		Assert.assertEquals("Comentaris de la Comanda", c.getComentaris());
		Assert.assertEquals(dataTest, c.getData());
		Assert.assertEquals((Float)23.22f, c.getDescompte());
		Assert.assertEquals(EEstatComanda.entregada, c.getEstat());
		Assert.assertEquals((Float)60.2f, c.getPreuFinal());
		Assert.assertEquals((Short)(short)5, c.getPunts());
		c.setId(null);
		Assert.assertNull(c.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarPuntsGeneratsNoPotSerNull() {
		Comanda c = new Comanda();
		c.setPunts(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarDataComandaNoPotSerNull() {
		Comanda c = new Comanda();
		c.setData(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarEstatNoPotSerNull() {
		Comanda c = new Comanda();
		c.setEstat(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarPreuFinalNoPotSerNull() {
		Comanda c = new Comanda();
		c.setPreuFinal(null);
	}
	
	@Test
	public void comprovarEquals() {
		Comanda c1 = crearComanda();
		Comanda c2 = crearComanda();
		Assert.assertEquals(c1, c2);
		c2.setId(2l);
		Assert.assertNotEquals(c1, c2);
	}
	
}
