

import static org.junit.Assert.assertEquals;
import conversion.Converter;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class ConversionStepDefs {
	private Converter roman;
	private int decimalIntToConvert;
	private String romanNumber;

	@Before
	public void setup() {
		roman = new Converter();
	}

	@Given("^Input integer is (\\d+)$")
	public void Input_Integer_Is(int decimal) throws Exception {
		decimalIntToConvert = decimal;
	}

	@When("^We convert to Roman representation$")
	public void We_convert_to_roman_representation() throws Exception {
		romanNumber = roman.convert(decimalIntToConvert);
	}

	@Then("^We get back an (.*)$")
	public void We_get_back_an(String expectedRomanNumber) throws Exception {
		assertEquals(expectedRomanNumber, romanNumber);
	}

}
