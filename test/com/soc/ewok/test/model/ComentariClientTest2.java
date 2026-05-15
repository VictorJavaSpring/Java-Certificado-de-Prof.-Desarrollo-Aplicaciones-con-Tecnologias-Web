package com.soc.ewok.test.model;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.soc.ewok.model.ComentariClient;

public class ComentariClientTest2 {
	
	/**
	 * Crea un nou objecte testData 
	 */
	Date testData = new Date();
	
	/**
	 * Crea un nou ComentariClient2
	 * @return ComentariClient cc
	 */
	private ComentariClient crearComentariClient(){
	
		/**
		 * Crea un nou objecte ComentariClient
		 */
		ComentariClient cc = new ComentariClient();
		cc.setId(1l);
		cc.setIdClient(2l);
//		cc.setText("text prova");
		cc.setData(testData);
		
		/**
		 * Retorna cc 	
		 */
		return cc;	
	}
	
	/**
	 * Comprova getters i setters
	 */
public void comprovaGettersSetters(){
	
	/**
	 * Crea nou objecte ComentariClient
	 */
	ComentariClient cc = new ComentariClient();
	
	/**
	 * Comprova equivalència
	 */
		Assert.assertEquals((Long)1l, cc.getId());
		Assert.assertEquals(2, cc.getIdClient());
	//	Assert.assertEquals("text prova", cc.getText());
		Assert.assertEquals(testData, cc.getData());
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
