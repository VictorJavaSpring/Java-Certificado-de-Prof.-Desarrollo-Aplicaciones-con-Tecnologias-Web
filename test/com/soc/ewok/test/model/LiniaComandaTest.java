package com.soc.ewok.test.model;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.EEstatLiniaComanda;
import com.soc.ewok.model.LiniaComanda;

public class LiniaComandaTest {

	@Test
	public void comprovacioGettersISetters() {
		LiniaComanda nova = new LiniaComanda(1l, 2l, 3l, 4, 5.33f, 6.82f,
				EEstatLiniaComanda.entregat);

		Assert.assertEquals((Long) 1l, nova.getId());
		Assert.assertEquals((Long) 2l, nova.getLinia());
		Assert.assertEquals((Long) 3l, nova.getIdProducte());
		Assert.assertEquals((Integer) 4, nova.getQuantitat());
		Assert.assertEquals((Float) 5.33f, nova.getPreuBrut());
		Assert.assertEquals((Float) 6.82f, nova.getPreuVenda());
		Assert.assertEquals(EEstatLiniaComanda.entregat, nova.getEstat());

	}

	// comprovacio linia Long
	@Test(expected = IllegalArgumentException.class)
	public void comprovarLiniaNoPotSerNull() {
		LiniaComanda nova = new LiniaComanda();
		nova.setLinia(null);
	}

	// comprovacio idProducte Long
	@Test(expected = IllegalArgumentException.class)
	public void comprovarIdProducteNoPotSerNull() {
		LiniaComanda nova = new LiniaComanda();
		nova.setLinia(null);
	}

	// comprovacio quantitat Integer
	@Test(expected = IllegalArgumentException.class)
	public void comprovarQuantitatNoPotSerNull() {
		LiniaComanda nova = new LiniaComanda();
		nova.setLinia(null);
	}

	// comprovacio preuBrut Float
	@Test(expected = IllegalArgumentException.class)
	public void comprovarPreuBrutNoPotSerNull() {
		LiniaComanda nova = new LiniaComanda();
		nova.setLinia(null);
	}

	// comprovacio preuVenta Float
	@Test(expected = IllegalArgumentException.class)
	public void comprovarPreuVentaNoPotSerNull() {
		LiniaComanda nova = new LiniaComanda();
		nova.setLinia(null);
	}

	// comprovacio Boolean estat
	@Test(expected = IllegalArgumentException.class)
	public void comprovarEstatNoPotSerNull() {
		LiniaComanda nova = new LiniaComanda();
		nova.setLinia(null);
	}

	@Test
	public void comprovarEquals() {
		LiniaComanda nova1 = new LiniaComanda();
		LiniaComanda nova2 = new LiniaComanda();
		Assert.assertEquals(nova1, nova2);
		nova1.setId(2l);
		Assert.assertNotEquals(nova1, nova2);
	}

}
