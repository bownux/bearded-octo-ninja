package conversion.decimal.digit;

import conversion.roman.Number;
import conversion.roman.Symbols;

public enum Conversion {
	INSTANCE;

	public Number romanNumber;
	public Symbols symbols;
	public int digit;

	public void save(int digit, Symbols symbols) {
		this.digit = digit;
		this.symbols = symbols;
		this.romanNumber = new Number();
	}
}
