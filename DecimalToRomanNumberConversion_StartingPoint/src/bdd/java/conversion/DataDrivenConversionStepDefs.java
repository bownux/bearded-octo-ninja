package conversion;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class DataDrivenConversionStepDefs {
	private Converter roman;
	
	private List<Conversion> actualConversions;
	private List<Conversion> expectedConversions;
	
	private class Conversion {
		public String digit;
		public String numeral;
		
		public String toString() {
			return ("[digit = " + digit + ", " + "numeral = " + numeral + "]");
		}
	}
	
	@Before
	public void setup() {
		roman = new Converter();
		actualConversions = new ArrayList<Conversion>();
	}

	@Given("^We are converting decimal integers to roman numerals$")
	public void We_Are_converting_decimal_integers_to_roman_numerals() throws Exception {
	}
	
	@When("^We expect the following conversions:$")
	public void We_expect_the_following_conversions(List<Conversion> incomingConversions) {

		this.expectedConversions = incomingConversions;
		
	    for (Conversion conversion : incomingConversions){
	    	Conversion actualConversion = new Conversion();
	    	actualConversion.digit = conversion.digit;
	    	actualConversion.numeral = roman.convert(Integer.parseInt(conversion.digit));
	    	actualConversions.add(actualConversion);
	     }
	}
	
	@Then("^The conversions turn out as expected$")
	public void The_conversions_turn_out_as_expected() {
		assertConversionTablesAreEqual();
	}

	private void assertConversionTablesAreEqual() {
		for (int i = 0; i < actualConversions.size(); i++) {
			Conversion thisExpected = expectedConversions.get(i);
			Conversion thisActual = actualConversions.get(i);
			assertEquals(thisExpected.digit, thisActual.digit);
			assertEquals(thisExpected.numeral, thisActual.numeral);
		}
	}
	

}
