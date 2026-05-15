package com.soc.ewok.test.model.exemple;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class RolTest {
	private Date unaData = new Date();
	
	private RolData crearRol() {
		RolData r = new RolData();
		r.setId(1l);
		r.setNom("Rol nom");
		r.setDataCreacio(unaData);
		return r;
	}
	
	@Test
	public void comprovacioGettersISetters() {
		RolData r = crearRol();
		Assert.assertEquals((Long)1l, r.getId());
		Assert.assertEquals("Rol nom", r.getNom());
		Assert.assertEquals(unaData, r.getDataCreacio());
		r.setId(null);
		Assert.assertNull(r.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarNomNoPotSerNull() {
		RolData r = new RolData();
		r.setNom(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarNomNoPotSerBlancs() {
		RolData r = new RolData();
		r.setNom("    ");
	}
	
	@Test
	public void comprovarEquals() {
		RolData r1 = crearRol();
		RolData r2 = crearRol();
		Assert.assertEquals(r1, r2);
		r2.setId(2l);
		Assert.assertNotEquals(r1, r2);
	}
}
