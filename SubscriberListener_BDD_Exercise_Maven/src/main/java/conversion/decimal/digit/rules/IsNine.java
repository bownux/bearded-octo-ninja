package conversion.decimal.digit.rules;

import conversion.roman.Number;

public class IsNine extends Rule  {
	public Number apply() {
		if (digit != 9)  return romanNumber;
		
		romanNumber.append(symbols.base);
		romanNumber.append(symbols.nineRuleModifier);
		return romanNumber;
	}

}
