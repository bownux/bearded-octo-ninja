package com.industriallogic.exercise;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.industriallogic.exercise.DecimalToRomanConverter;

public class WhenConvertingNumbersLessThanFour  {
	protected DecimalToRomanConverter roman;

	@Before
	public void setup() {
		roman = new DecimalToRomanConverter();
	}
	
	@Test
	public void canConvertNumbersBetweenOneAndThree() throws Exception {
		assertEquals("I", roman.convert(1));
		assertEquals("II", roman.convert(2));
		assertEquals("III", roman.convert(3));
	}
}
