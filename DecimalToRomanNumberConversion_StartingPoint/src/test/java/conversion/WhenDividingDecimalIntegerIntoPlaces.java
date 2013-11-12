package conversion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import conversion.decimal.Places;

public class WhenDividingDecimalIntegerIntoPlaces {
	private Places decimalPlaces;
	
	@Test
	public void ThreeDigitIntegerIsParsedProperly() {
		decimalPlaces = new Places(591);
		assertEquals(0, decimalPlaces.thousands);
		assertEquals(5, decimalPlaces.hundreds);
		assertEquals(9, decimalPlaces.tens);
		assertEquals(1, decimalPlaces.ones);
	}

	@Test
	public void FourDigitIntegerIsParsedProperly() {
		decimalPlaces = new Places(1432);
		assertEquals(1, decimalPlaces.thousands);
		assertEquals(4, decimalPlaces.hundreds);
		assertEquals(3, decimalPlaces.tens);
		assertEquals(2, decimalPlaces.ones);
	}
	
	@Test
	public void NumberGreaterThanMaxThrowsException() {
		try {
			decimalPlaces = new Places(4000);
		} catch (Exception e) {
			assertEquals("Input integer must be between 1 and 3999", e.getMessage());
		}
		
	}
	

}
