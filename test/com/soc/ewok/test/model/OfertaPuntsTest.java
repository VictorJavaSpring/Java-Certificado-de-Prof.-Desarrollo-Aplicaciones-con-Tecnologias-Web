package com.soc.ewok.test.model;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.OfertaPunts;
import com.soc.ewok.model.Rol;

public class OfertaPuntsTest {
	private Date unaData = new Date();
	
	private OfertaPunts crearOfertaPunts (){
		OfertaPunts op = new OfertaPunts();
	
		op.setId(1l);
		op.setEurosPerPunt((float) 3.2);
		op.setPuntsPerXec((short) 5.3);
		op.setDiesVigenciaPunts((Long) 7l);
		op.setDiesVigenciaXecs((Long) 7l);
		op.setIniciVigencia(unaData);
		op.setFiVigencia(unaData);	
		return op;	
	}
	
	@Test
	public void comprovacioGettersISetters() {
		OfertaPunts op = crearOfertaPunts();
		Assert.assertEquals((Long)1l, op.getId());
		Assert.assertEquals((Float)3.2f, op.getEurosPerPunt());
		Assert.assertEquals((Float)5.3f, op.getPuntsPerXec());
		Assert.assertEquals((Long)7l, op.getDiesVigenciaPunts());
		Assert.assertEquals((Long)7l, op.getDiesVigenciaXecs());
		Assert.assertEquals(unaData, op.getIniciVigencia());
		Assert.assertEquals(unaData, op.getFiVigencia());
		op.setId(null);
		Assert.assertNull(op.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarEurosPerPuntNoPotSerNull() {
		OfertaPunts op = new OfertaPunts();
		op.setEurosPerPunt(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarPuntsPerXecNoPotSerNull() {
		OfertaPunts op = new OfertaPunts();
		op.setPuntsPerXec(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarDiesVigenciaPuntsNoPotSerNull() {
		OfertaPunts op = new OfertaPunts();
		op.setDiesVigenciaPunts(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarDiesVigenciaXecsNoPotSerNull() {
		OfertaPunts op = new OfertaPunts();
		op.setDiesVigenciaXecs(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarIniciVigeniaNoPotSerNull() {
		OfertaPunts op = new OfertaPunts();
		op.setIniciVigencia(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void comprovarFiVigeniaNoPotSerNull() {
		OfertaPunts op = new OfertaPunts();
		op.setFiVigencia(null);
	}
	@Test
	public void comprovarEquals() {	
		OfertaPunts op1 = crearOfertaPunts();
		OfertaPunts op2 = crearOfertaPunts();
		Assert.assertEquals(op1, op2);
		op2.setId(2l);
		Assert.assertNotEquals(op1, op2);
	}	
}

