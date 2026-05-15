package com.soc.ewok.test.model;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.ComentariClient;

public class ComentariClientTest {
	
	Date testData = new Date();
	
	private ComentariClient crearComentariClient() {
		
		ComentariClient cc = new ComentariClient();
		cc.setId(1l);
		cc.setIdClient(2l);
		cc.setComentari("client conflictiu");
		cc.setData(testData);
		
		return cc;
	}
	
	public void comprovacioGettersISetters() {
		ComentariClient cc = crearComentariClient();
		Assert.assertEquals((Long)1l, cc.getId());
		Assert.assertEquals(2, cc.getIdClient());
		Assert.assertEquals("client conflictiu", cc.getComentari());
		Assert.assertEquals(testData, cc.getData());
		cc.setComentari(null);
		Assert.assertNull(cc.getComentari());
		cc.setData(null);
		Assert.assertNull(cc.getData());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void comprovarIdNoPotSerNull() {
		ComentariClient cc = crearComentariClient();
		cc.setId(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void comprovarIdClientNoPotSerNull() {
		ComentariClient cc = crearComentariClient();
		cc.setIdClient(null);
	}
	
		

}
