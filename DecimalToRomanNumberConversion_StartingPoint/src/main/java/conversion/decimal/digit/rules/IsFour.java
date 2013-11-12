package conversion.decimal.digit.rules;

import conversion.roman.Number;

public class IsFour extends Rule {
	public Number apply() {
		if (digit != 4)  return romanNumber;
		
		romanNumber.append(symbols.base);
		romanNumber.append(symbols.betweenFourAndEightModifier);
		return romanNumber;
	}

}
