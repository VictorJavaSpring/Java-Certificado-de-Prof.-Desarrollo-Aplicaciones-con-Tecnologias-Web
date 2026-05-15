package com.soc.ewok.test.model;
 
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.PuntsPendents;

public class PuntsPendentsTest {
	
	private PuntsPendents crearPuntsPendents() {
		Date data = new Date();
		PuntsPendents pp = new PuntsPendents();
		pp.setId(1l);
		pp.setNumPunts((short)3);
		pp.setDataCaducitat(data);
		pp.setIdClient(303l);
		return pp;
	}

	@Test
	public void comprovacioGettersISetters() {
		PuntsPendents pp = crearPuntsPendents();
		Date data = new Date();
		Assert.assertEquals((Long)1l, pp.getId());
		Assert.assertEquals((Short)(short)3, pp.getNumPunts());
		Assert.assertEquals(data, pp.getDataCaducitat());
		Assert.assertEquals((Long)303l, pp.getIdClient());
		pp.setId(null);
		Assert.assertNull(pp.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarNumPuntsNoPotSerNull() {
		PuntsPendents pp = new PuntsPendents();
		pp.setNumPunts(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarDataCaducitatNoPotSerNull() {
		PuntsPendents pp = new PuntsPendents();
		pp.setDataCaducitat(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarIdClientNoPotSerNull() {
		PuntsPendents pp = new PuntsPendents();
		pp.setIdClient(null);
	}
	
	@Test
	public void comprovarEquals() {
		PuntsPendents pp1 = crearPuntsPendents();
		PuntsPendents pp2 = crearPuntsPendents();
		Assert.assertEquals(pp1, pp2);
		pp2.setId(2l);
		Assert.assertNotEquals(pp1, pp2);
	}

}
