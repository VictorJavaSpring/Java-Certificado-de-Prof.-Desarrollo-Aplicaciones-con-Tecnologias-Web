package com.soc.ewok.test;

import org.junit.Assert;

import org.junit.Test;

public class JordiF {
	@Test
	public void unTestQueFunciona() {
		int i = 5;
		Assert.assertEquals(5, i);
	}
	@Test
	public void unTestQueNoFunciona() {
		int i = 67;
		Assert.assertEquals(67, i);
	}
}
