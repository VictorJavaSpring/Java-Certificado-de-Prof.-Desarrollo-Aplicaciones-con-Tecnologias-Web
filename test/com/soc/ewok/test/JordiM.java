package com.soc.ewok.test;

import org.junit.Assert;
import org.junit.Test;


public class JordiM {
	@Test
	public void elmeuTest(){
		int i = 5;
		Assert.assertEquals(5, i);
	}
	@Test
	public void elmeuTestErroni(){
		int i = 5;
		Assert.assertEquals("nanai", i);
	}
}
