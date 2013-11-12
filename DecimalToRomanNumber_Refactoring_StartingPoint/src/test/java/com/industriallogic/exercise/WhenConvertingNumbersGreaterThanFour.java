package com.industriallogic.exercise;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.industriallogic.exercise.DecimalToRomanConverter;

public class WhenConvertingNumbersGreaterThanFour  {
	protected DecimalToRomanConverter roman = new DecimalToRomanConverter();

	@Test
	public void canConvertNumbers_AroundThe_V_Symbol() throws Exception {
		assertEquals("IV", roman.convert(4));
		assertEquals("V", roman.convert(5));  
		assertEquals("VI", roman.convert(6));
		assertEquals("VII", roman.convert(7));
		assertEquals("VIII", roman.convert(8));
	}
	
	@Test
	public void canConvertNumbers_AroundThe_X_Symbol() throws Exception {		
		assertEquals("IX", roman.convert(9));
		assertEquals("X", roman.convert(10)); 
		assertEquals("XI", roman.convert(11)); 
		assertEquals("XII", roman.convert(12)); 
		assertEquals("XIII", roman.convert(13)); 
		assertEquals("XIV", roman.convert(14)); 
		assertEquals("XVIII", roman.convert(18)); 
		assertEquals("XX", roman.convert(20)); 
	}
	
	@Test
	public void canConvertNumbers_AroundThe_L_Symbol() throws Exception {
		assertEquals("XL", roman.convert(40)); 
		assertEquals("L", roman.convert(50));
		assertEquals("LXXX", roman.convert(80)); 
	}
	
	@Test
	public void canConvertNumbers_AroundThe_C_Symbol() throws Exception {				
		assertEquals("XCIX", roman.convert(99));
		assertEquals("C", roman.convert(100));
		assertEquals("CXL", roman.convert(140));
		assertEquals("CXC", roman.convert(190));
	}
	
	@Test
	public void canConvertNumbers_AroundThe_D_Symbol() throws Exception {		
		assertEquals("CD", roman.convert(400));
		assertEquals("D", roman.convert(500));
		assertEquals("DX", roman.convert(510));
		assertEquals("DCCC", roman.convert(800));
	}
	
	@Test
	public void canConvertNumbers_AroundThe_M_Symbol() throws Exception {	
		assertEquals("CM", roman.convert(900));
		assertEquals("M", roman.convert(1000));
		assertEquals("MX", roman.convert(1010));
		assertEquals("MD", roman.convert(1500));
		assertEquals("MMMCMXCIX", roman.convert(3999));
	}

}
