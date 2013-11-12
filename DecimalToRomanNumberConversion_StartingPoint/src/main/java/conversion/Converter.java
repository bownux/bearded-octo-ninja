package conversion;

import conversion.decimal.Places;
import conversion.decimal.digit.Rules;
import conversion.roman.Number;
import conversion.roman.Symbols;

/*
Patrick's Domain Terms for Converting Decimal (Arabic) Integers Less than 4000 to Roman Numbers

	Decimal Number ("3999")
	Decimal Digit ("4")
	Decimal Place ("Thousands, Hundreds, Tens, Ones")
	
	Roman Number ("III")
	Roman Symbol ("C")
	
	Digit Rule ("is between 1 and 3," "is 4", "is between 5 and 8", "is 9")
 * 
 */
public class Converter {
	private Places digits;
	private Number romanNumber;
	private Rules rules;

	public String convert(int decimalNumber) {
		initialize(decimalNumber);
		romanNumber = convertDecimalPlacesInReverseOrder();
		return romanNumber.toString();
	}

	private Number convertDecimalPlacesInReverseOrder() {
		romanNumber = convertPlace(digits.thousands, Symbols.THOUSANDS);
		romanNumber = convertPlace(digits.hundreds, Symbols.HUNDREDS);
		romanNumber = convertPlace(digits.tens, Symbols.TENS);
		romanNumber = convertPlace(digits.ones, Symbols.ONES);
		
		return romanNumber;
	}
	
	private Number convertPlace(int digit, Symbols symbols) {
		romanNumber.append(rules.applyForPlace(digit, symbols));
		return romanNumber;
	}

	//TODO Will need dependency injection once we retrofit characterization tests
	private void initialize(int decimal) {
		digits = new Places(decimal);
		romanNumber = new Number();
		rules = new Rules();
	}
}
