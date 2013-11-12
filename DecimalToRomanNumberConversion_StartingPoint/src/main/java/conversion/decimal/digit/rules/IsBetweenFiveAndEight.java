package conversion.decimal.digit.rules;

import conversion.roman.Number;

public class IsBetweenFiveAndEight extends Rule {
	public Number apply() {
		if (digitIsNotBetweenFiveAndEight()) return romanNumber;
		
		romanNumber.append(symbols.betweenFourAndEightModifier);
		appendBaseAsNecessary(5, symbols);
		return romanNumber;
	}

	private boolean digitIsNotBetweenFiveAndEight() {
		return !(digit >= 5 && digit <= 8);
	}

}
