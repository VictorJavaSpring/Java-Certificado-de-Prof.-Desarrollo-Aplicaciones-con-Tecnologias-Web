package com.soc.ewok.test.model;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.Producte;

public class ProducteTest {
	//necessito assignar dates persistents per a poder compararla des de els mètodes test
	Date d1 = new Date(); 
	Date d2 = new Date(d1.getTime()+1000000);
	
	/**
	 * crea un producte de prova amb dades fixes
	 * @return el producte de prova
	 */
	private Producte crearProducte(){
		  
		Producte p = new Producte();
		p.setId(1l);
		p.setNom("Chicha");
		p.setDescripcioCurta("Complement amb substància");
		p.setDescripcio("Complement amb substància per als plats de wok definits i des de zero");
		p.setIniciVigencia(d1);
		p.setFiVigencia(d2);
		p.setIdUnitat(2l);
		p.setIdTipusProducte(3l);
		return p;
	}
	
	@Test
	public void comprovaSettersIGetters(){
		Producte p = crearProducte();
		Assert.assertEquals((Long)1l, p.getId());
		p.setId(null);
		Assert.assertNull(p.getId());
		Assert.assertEquals("Chicha", p.getNom());
		Assert.assertEquals("Complement amb substància", p.getDescripcioCurta());
		Assert.assertEquals("Complement amb substància per als plats de wok definits i des de zero", p.getDescripcio());
		Assert.assertEquals(d1,p.getIniciVigencia());
		Assert.assertEquals(d2, p.getFiVigencia());
		Assert.assertEquals((Long)2l, p.getIdUnitat());
		Assert.assertEquals((Long)3l, p.getIdTipusProducte());
		
	}
	@Test
	public void comprovarEquals() {
		Producte p1 = crearProducte();
		Producte p2 = crearProducte();
		Assert.assertEquals(p1, p2);
		p2.setId(2l);
		Assert.assertNotEquals(p1, p2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarNomNoPotSerNull() {
		Producte p = new Producte();
		p.setNom(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarNomNoPotSerBlancs() {
		Producte p = new Producte();
		p.setNom("    ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarIdUnitatNoPotSerNull() {
		Producte p = new Producte();
		p.setIdUnitat(null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void comprovarIdTipusProducteNoPotSerNull() {
		Producte p = new Producte();
		p.setIdTipusProducte(null);
	}
}
