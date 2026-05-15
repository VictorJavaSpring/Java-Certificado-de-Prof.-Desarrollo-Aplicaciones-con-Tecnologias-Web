package com.soc.ewok.test.model;

import java.sql.Timestamp;
import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.Pagament;

public class PagamentTest {
	@SuppressWarnings("deprecation")
	Timestamp data = new Timestamp(2015, 01, 01, 01, 23, 10, 10);
	private Pagament crearPagament(){
		Pagament p = new Pagament();
		p.setnId(1l);
		p.setnIdComanda(11l);
		p.setQuantitat(123456.78F);
		p.setIdFormaPagament(111l);
		// Creo una data Timestamp
		p.setData(data);
		return p;
	}
	
	//@Test(expected=IllegalArgumentException.class)
	@Test
	public void testGetteriSetters(){
		Pagament p = crearPagament();
		
		Assert.assertEquals((Long)1l, p.getnId());
		Assert.assertEquals((Long)11l, p.getnIdComanda());
		Assert.assertEquals((Float)123456.78F, p.getQuantitat());
		Assert.assertEquals((Long)111l, p.getIdFormaPagament());
		Assert.assertEquals((Timestamp)data, p.getData());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNoNulls(){
		Pagament p = crearPagament();
		
		p.setnId(null);
		Assert.assertNull(p.getnId());
		p.setnIdComanda(null);
		Assert.assertNull(p.getnIdComanda());
		p.setQuantitat(null);
		Assert.assertNull(p.getQuantitat());
		p.setIdFormaPagament(null);
		Assert.assertNull(p.getIdFormaPagament());
		p.setData(null);
		Assert.assertNull(p.getData());
	}
	
	@Test
	public void comprovarEquals() {
		Pagament p1 = crearPagament();
		Pagament p2 = crearPagament();
		Assert.assertEquals(p1, p2);
		p2.setnId(2l);
		Assert.assertNotEquals(p1, p2);
	}
}
