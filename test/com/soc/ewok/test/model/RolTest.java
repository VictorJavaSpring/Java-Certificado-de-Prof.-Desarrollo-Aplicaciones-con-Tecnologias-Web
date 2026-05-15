package com.soc.ewok.test.model;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.Rol;

public class RolTest {

	private Rol crearRol() {
		Rol r = new Rol();
		r.setId(1l);
		r.setNom("Rol nom");
		r.setCodi("C1Administ");
		return r;
	}
	
	@Test
	public void comprovacioGettersISetters() {
		Rol r = crearRol();
		Assert.assertEquals((Long)1l, r.getId());
		Assert.assertEquals("Rol nom", r.getNom());
		Assert.assertEquals("C1Administ", r.getCodi());
		r.setId(null);
		Assert.assertNull(r.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarNomNoPotSerNull() {
		Rol r = new Rol();
		r.setNom(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarNomNoPotSerBlancs() {
		Rol r = new Rol();
		r.setNom("    ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarCodiNoPotSerNull() {
		Rol r = new Rol();
		r.setCodi(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarCodiNoPotSerBlancs() {
		Rol r = new Rol();
		r.setCodi("    ");
	}

	@Test
	public void comprovarEquals() {
		Rol r1 = crearRol();
		Rol r2 = crearRol();
		Assert.assertEquals(r1, r2);
		r2.setId(2l);
		Assert.assertNotEquals(r1, r2);
	}
}
