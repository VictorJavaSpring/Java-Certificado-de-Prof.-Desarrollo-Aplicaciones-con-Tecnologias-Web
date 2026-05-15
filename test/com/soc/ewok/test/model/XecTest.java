package com.soc.ewok.test.model;
 
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.Xec;

public class XecTest {

	private Xec crearXecs() {
		Date data = new Date();
		Xec x = new Xec();
		x.setId(1l);
		x.setNumPunts((short)2);
		x.setDataCaducitat(data);
		x.setIdComanda(52l);
		x.setIdClient(105l);
		return x;
	}

	@Test
	public void comprovacioGettersISetters() {
		Xec x = crearXecs();
		Date data = new Date();
		Assert.assertEquals((Long)1l, x.getId());
		Assert.assertEquals((Short)(short)2, x.getNumPunts());
		Assert.assertEquals(data, x.getDataCaducitat());
		Assert.assertEquals((Long)52l, x.getIdComanda());
		Assert.assertEquals((Long)105l, x.getIdClient());
		x.setId(null);
		Assert.assertNull(x.getId());
	}

	@Test(expected=IllegalArgumentException.class)
	public void comprovarNumPuntsNoPotSerNull() {
		Xec x = new Xec();
		x.setNumPunts(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void comprovarDataCaducitatNoPotSerNull() {
		Xec x = new Xec();
		x.setDataCaducitat(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void comprovarIdClientNoPotSerNull() {
		Xec x = new Xec();
		x.setIdClient(null);
	}

	@Test
	public void comprovarEquals() {
		Xec x1 = crearXecs();
		Xec x2 = crearXecs();
		Assert.assertEquals(x1, x2);
		x2.setId(2l);
		Assert.assertNotEquals(x1, x2);
	}


}
