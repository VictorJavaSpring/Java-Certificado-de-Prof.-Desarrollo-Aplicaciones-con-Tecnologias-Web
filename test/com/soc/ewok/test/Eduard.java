package com.soc.ewok.test;

import org.junit.Assert;

import org.junit.Test;


public class Eduard {
	
	@Test
	public void unTest(){
		int i=5;
		Assert.assertEquals(5, i);
		
		
	}
	
	@Test
	public void unTestQueNoFunciona(){
		int i=5;
		Assert.assertEquals(67, i + 62);
	}
}
