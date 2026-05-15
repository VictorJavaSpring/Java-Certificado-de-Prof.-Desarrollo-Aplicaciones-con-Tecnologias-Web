package com.soc.ewok.test.model;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.Unitat;



public class UnitatTest {
	
	private Unitat crearUnitat() {
		Unitat u = new Unitat();
		u.setId(1l);
		u.setNom("nom Unitat");
		u.setAcron("acronim Unitat");
		return u;
	}
	
	@Test
	public void comprovacioGettersISetters() {
		Unitat u = crearUnitat();
		Assert.assertEquals((Long)1l, u.getId());
		Assert.assertEquals("nom Unitat", u.getNom());
		Assert.assertEquals("acronim Unitat", u.getAcron());
		u.setId(null);
		Assert.assertNull(u.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarNomNoPotSerNull() {
		Unitat u = new Unitat();
		u.setNom(null);
		u.setAcron(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarAcronimNoPotSerBlancs() {
		Unitat u = new Unitat();
		u.setNom("    ");
		u.setAcron("   ");
	}
	
	@Test
	public void comprovarEquals() {
		Unitat u1 = crearUnitat();
		Unitat u2 = crearUnitat();
		Assert.assertEquals(u1, u2);
		u2.setId(2l);
		Assert.assertNotEquals(u1, u2); 
	}

}
