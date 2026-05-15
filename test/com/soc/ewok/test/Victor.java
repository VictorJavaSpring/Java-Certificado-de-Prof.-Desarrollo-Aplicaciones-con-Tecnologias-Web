package com.soc.ewok.test;

import org.junit.Assert;
import org.junit.Test;

public class Victor {
	@Test
	public void TestVictor(){
		int v = 5;
		Assert.assertEquals(5, v);
	}	
	
	@Test
	public void TestQueNoFuncionaVictor(){
		int v = 5;
		Assert.assertEquals(67, v + 62);
	}
}
