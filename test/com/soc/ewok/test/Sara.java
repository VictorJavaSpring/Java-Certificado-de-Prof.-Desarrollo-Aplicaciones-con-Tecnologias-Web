package com.soc.ewok.test;

import org.junit.Test;
import org.junit.Assert;

public class Sara {
	
	@Test
	public void testQueFunciona() {
		int i = 5;
		Assert.assertEquals(5, i);
	}
	
	@Test
	public void testQueNoFunciona() {
		int i = 5;
		Assert.assertEquals(88, i + 83);
	}
}
