package com.soc.ewok.test.model;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.Components;


public class ComponentsTest {
	
	private Components crearComponent() {
		Components c = new Components();
		c.setIdProducte(1l);
		c.setIdComponent(2l);
		c.setQuantitat(8);
		c.setOrdre(9);
		return c;
	}
	
	@Test
	public void comprovacioGettersISetters() {
		Components c = crearComponent();
		Assert.assertEquals((Long)1l, c.getIdProducte());
		Assert.assertEquals((Long)2l, c.getIdComponent());
		Assert.assertEquals((Integer)8, c.getQuantitat());
		Assert.assertEquals((Integer)9, c.getOrdre());
		c.setIdProducte(null);
		c.setIdComponent(null);
		Assert.assertNull(c.getIdProducte());
		Assert.assertNull(c.getIdComponent());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarQuantitatNoPotSerNull() {
		Components c = new Components();
		c.setQuantitat(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarOrdreNoPotSerNull() {
		Components c = new Components();
		c.setOrdre(null);
	}
	
	@Test
	public void comprovarEquals() {
		Components c1 = crearComponent();
		Components c2 = crearComponent();
		Assert.assertEquals(c1, c2);
		c2.setIdProducte(10l);
		Assert.assertNotEquals(c1, c2);
	}
	
}
