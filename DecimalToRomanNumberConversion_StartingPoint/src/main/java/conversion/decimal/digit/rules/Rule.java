package conversion.decimal.digit.rules;

import conversion.decimal.digit.Conversion;
import conversion.roman.Number;
import conversion.roman.Symbols;

public abstract class Rule {
	protected final int digit = Conversion.INSTANCE.digit;
	protected final Number romanNumber = Conversion.INSTANCE.romanNumber;
	protected final Symbols symbols = Conversion.INSTANCE.symbols;
	
	public abstract Number apply();

	protected void appendBaseAsNecessary(int index, Symbols symbols) {
		for (int i = index; i < digit; i++) {
			romanNumber.append(symbols.base);
		}
	}

}
