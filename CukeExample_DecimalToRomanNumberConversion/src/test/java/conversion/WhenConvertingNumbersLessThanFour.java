package conversion;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class WhenConvertingNumbersLessThanFour  {
	protected Converter roman;

	@Before
	public void setup() {
		roman = new Converter();
	}
	
	@Test
	public void canConvertNumbersBetweenOneAndThree() throws Exception {
		assertEquals("I", roman.convert(1));
		assertEquals("II", roman.convert(2));
		assertEquals("III", roman.convert(3));
	}
}
