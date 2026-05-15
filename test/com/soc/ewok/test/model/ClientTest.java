package com.soc.ewok.test.model;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.Client;

public class ClientTest {
	
	Date data = new Date();

	private Client crearClient() {
		Client cl = new Client();
		cl.setId(1l);
		cl.setNom("Nom Client");
		cl.setCognoms("Cognoms Client");
		cl.setTelefon("123456789");
		cl.setEmail("client@email.com");		
		cl.setDataAlta(data);
		cl.setDni("123456A");
		cl.setIdUsuari("idDelUsuari");
		return cl;
	}
	
	@Test
	public void checkGettersISetters() {
		Client cl = crearClient();
		Assert.assertEquals((Long)1l, cl.getId());
		Assert.assertEquals("Nom Client", cl.getNom());
		Assert.assertEquals("Cognoms Client", cl.getCognoms());
		Assert.assertEquals("123456789", cl.getTelefon());
		Assert.assertEquals("client@email.com", cl.getEmail());		
		Assert.assertEquals(data, cl.getDataAlta());
		Assert.assertEquals("123456A", cl.getDni());
		Assert.assertEquals("idDelUsuari", cl.getIdUsuari());
		cl.setId(null);
		Assert.assertNull(cl.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void checkNomNotNull() {
		Client c = new Client();
		c.setNom(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void checkNomNotBlank() {
		Client c = new Client();
		c.setNom("      ");
	}
	
	@Test
	public void checkEquals() {
		Client c1 = crearClient();
		Client c2 = crearClient();
		Assert.assertEquals(c1, c2);
		c2.setId(222l);
		Assert.assertNotEquals(c1, c2);
	}
}
