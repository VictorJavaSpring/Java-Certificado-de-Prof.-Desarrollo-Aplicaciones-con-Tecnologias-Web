package com.soc.ewok.test;

import org.junit.Assert;
import org.junit.Test;

public class Jose {
	@Test
	public void testetandoOk(){
		int i= 5;
		Assert.assertEquals(5, i);
	}
	
	@Test
	public void testetandoBad(){
		int i= 67;
		Assert.assertEquals(67, i);
	}
}
