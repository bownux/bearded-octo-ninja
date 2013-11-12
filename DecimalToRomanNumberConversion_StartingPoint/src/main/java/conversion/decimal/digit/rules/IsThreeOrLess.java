package conversion.decimal.digit.rules;

import conversion.roman.Number;

public class IsThreeOrLess extends Rule {
	public Number apply() {
		if (digit > 3) return romanNumber;
		
		appendBaseAsNecessary(0, symbols);
		return romanNumber;
	}
}
