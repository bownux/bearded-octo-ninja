package conversion.roman;

public class Number {
	private String value = "";

	public void append(String symbol) {
		this.value  += symbol;
	}
	
	public void append(Number symbols) {
		this.value += symbols;
	}
	
	public String toString() {
		return value;
	}
}
