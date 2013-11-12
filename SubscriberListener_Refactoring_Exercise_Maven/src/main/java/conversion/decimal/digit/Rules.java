package conversion.decimal.digit;

import conversion.decimal.digit.rules.IsBetweenFiveAndEight;
import conversion.decimal.digit.rules.IsFour;
import conversion.decimal.digit.rules.IsNine;
import conversion.decimal.digit.rules.IsThreeOrLess;
import conversion.roman.Number;
import conversion.roman.Symbols;

public class Rules {
	public Number applyForPlace(int digit, Symbols symbols) {
		Conversion.INSTANCE.save(digit, symbols);
		
		new IsThreeOrLess().apply();
		new IsFour().apply();
		new IsBetweenFiveAndEight().apply();
		new IsNine().apply();
		return Conversion.INSTANCE.romanNumber;
	}
}
