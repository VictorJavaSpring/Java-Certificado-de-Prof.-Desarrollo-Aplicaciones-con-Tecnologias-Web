package com.soc.ewok.test;

import org.junit.Assert;
import org.junit.Test;

public class CarlosM {
	@Test
	public void unTestCarlosMOK(){
		int i = 5;
		Assert.assertEquals(5, i);
	}
	
	@Test
	public void unTestCarlosMNOOK(){
		int i = 5;
		Assert.assertEquals(67, i + 62);
	}
}
