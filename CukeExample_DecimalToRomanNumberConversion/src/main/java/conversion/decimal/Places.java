package conversion.decimal;

public class Places {
	public byte ones;
	public byte tens;
	public byte hundreds;
	public byte thousands;

	public Places(int decimalInteger) {
		extractPlaces(decimalInteger);
	}

	private void extractPlaces(int number) {
		ones = extractOnes(number);
		tens = extractTens(number);
		hundreds = extractHundreds(number);
		thousands = extractThousands(number);
	}

	private byte extractThousands(int number) {
		return (byte) (number / 1000 % 10);
	}

	private byte extractHundreds(int number) {
		return (byte) (number / 100 % 10);
	}

	private byte extractTens(int number) {
		return (byte) (number / 10 % 10);
	}

	private byte extractOnes(int number) {
		return (byte) (number % 10);
	}
}
