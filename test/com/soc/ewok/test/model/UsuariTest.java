package com.soc.ewok.test.model;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.Usuari;


public class UsuariTest {
	
	private Usuari crearUsuari() {
		Usuari user = new Usuari();
		user.setMail("user@user.com");
		user.setPassword("patata");
		user.setActiu(true);
		return user;
	}
	
	@Test
	public void comprovacioGettersISetters() {
		Usuari user = crearUsuari();
		Assert.assertEquals("user@user.com", user.getMail());
		Assert.assertEquals("patata", user.getPassword());
		Assert.assertEquals(true, user.getActiu());
		
	}
	
	
				
	@Test (expected=IllegalArgumentException.class)
	public void comprovarMailNoPotSerBlancs() {
		Usuari user = crearUsuari();
		user.setMail("     ");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void comprovarPasswordNoPotSerBlancs() {
		Usuari user = crearUsuari();
		user.setPassword("     ");
	}
	
}
