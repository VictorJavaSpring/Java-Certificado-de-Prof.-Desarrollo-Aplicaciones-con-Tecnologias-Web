package com.soc.ewok.test.model;

import org.junit.Assert;

import org.junit.Test;

import com.soc.ewok.model.Adreca;

/**
 * clase que representa el test d'una adreça.
 * @author Jose
 *
 */
public class AdrecaTest {

	private Adreca crearAdreca(){
		Adreca ad = new Adreca();
		ad.setId(1l);
		ad.setLinia1("Primera linia de l'adreça");
		ad.setLinia2("Segona linia de l'adreça");
		ad.setCp("08009");
		ad.setCiutat("Ciutat");
		ad.setNotes("Notes de l'adreça");
		ad.setTelefon("666999333");
		ad.setIdClient(2l);
		ad.setAlias("Alias adreça");
		return ad;	
	}
	
	
	@Test
	public void comprovacioGettersISetters() {
		Adreca ad = crearAdreca();
		Assert.assertEquals((Long)1l, ad.getId());
		Assert.assertEquals("Primera linia de l'adreça", ad.getLinia1());
		Assert.assertEquals("Segona linia de l'adreça", ad.getLinia2());
		Assert.assertEquals("08009", ad.getCp());
		Assert.assertEquals("Ciutat", ad.getCiutat());
		Assert.assertEquals("Notes de l'adreça", ad.getNotes());
		Assert.assertEquals("666999333", ad.getTelefon());
		Assert.assertEquals((Long)2l, ad.getIdClient());
		Assert.assertEquals("Alias adreça", ad.getAlias());
		
		ad.setId(null);
		Assert.assertNull(ad.getId());
		ad.setLinia2(null);
		Assert.assertNull(ad.getLinia2());
		ad.setNotes(null);
		Assert.assertNull(ad.getNotes());
		ad.setTelefon(null);
		Assert.assertNull(ad.getTelefon());
		ad.setAlias(null);
		Assert.assertNull(ad.getAlias());		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarLinia1NoPotSerNull() {
		Adreca ad = new Adreca();
		ad.setLinia1(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarLinia1NoPotSerBlancs() {
		Adreca ad = new Adreca();
		ad.setLinia1("    ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarCiutatNoPotSerNull() {
		Adreca ad = new Adreca();
		ad.setCiutat(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarTelefonNoPotSerMayorDe15() {
		Adreca ad = new Adreca();
		ad.setTelefon("123456789101112131415");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarCpNoPotSerNull() {
		Adreca ad = new Adreca();
		ad.setCp(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarCpNoPotSerDiferentDe5() {
		Adreca ad = new Adreca();
		ad.setCp("34");
	}
	
	@Test
	public void comprovarEquals() {
		Adreca ad1 = crearAdreca();
		Adreca ad2 = crearAdreca();
		Assert.assertEquals(ad1, ad2);
		ad2.setId(2l);
		Assert.assertNotEquals(ad1, ad2);
		ad2.setLinia1("String linea 1");
		Assert.assertNotEquals(ad1, ad2);
		ad2.setLinia2("String linea 2");
		Assert.assertNotEquals(ad1, ad2);
		ad2.setCp("08034");
		Assert.assertNotEquals(ad1, ad2);
		ad2.setCiutat("String de ciutat");
		Assert.assertNotEquals(ad1, ad2);
		ad2.setNotes("String notes");
		Assert.assertNotEquals(ad1, ad2);
		ad2.setTelefon("666999333");
		Assert.assertNotEquals(ad1, ad2);
		ad2.setIdClient(44l);
		Assert.assertNotEquals(ad1, ad2);
		ad2.setAlias("String alias");
	}
}
