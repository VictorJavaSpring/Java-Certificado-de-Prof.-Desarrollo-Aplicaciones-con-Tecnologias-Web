package com.soc.ewok.test.model;

import org.junit.Assert;
import org.junit.Test;
import com.soc.ewok.model.FormaPagament;


public class FormaPagamentTest {
	private FormaPagament crearFormaPagament() {
		FormaPagament FP = new FormaPagament();
		FP.setnId(1l);
		FP.setsNom("Forma Pagament 1");
		return FP;
	}
	
	@Test
	public void comprovacioGettersISetters() {
		FormaPagament FP = crearFormaPagament();
		Assert.assertEquals((Long)1l, FP.getnId());
		Assert.assertEquals("Forma Pagament 1", FP.getsNom());
		FP.setnId(null);
		Assert.assertNull(FP.getnId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarNomNoPotSerNull() {
		FormaPagament FP = new FormaPagament();
		FP.setsNom(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarNomNoPotSerBlancs() {
		FormaPagament FP = new FormaPagament();
		FP.setsNom("    ");
	}
	
	@Test
	public void comprovarEquals() {
		FormaPagament FP1 = crearFormaPagament();
		FormaPagament FP2 = crearFormaPagament();
		Assert.assertEquals(FP1, FP2);
		FP2.setnId(2l);
		Assert.assertNotEquals(FP1, FP2);
	}
}