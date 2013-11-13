package conversion.roman;

public enum Symbols {
	ONES  ("I", "V", "X"), 
	TENS  ("X", "L", "C"), 
	HUNDREDS  ("C", "D", "M"), 
	THOUSANDS ("M");
	
	public String base;
	public String betweenFourAndEightModifier;
	public String nineRuleModifier;
	
	private Symbols(String base, String betweenFourAndEightModifier, String nineRuleModifier) {
		this.base = base;
		this.betweenFourAndEightModifier = betweenFourAndEightModifier;
		this.nineRuleModifier = nineRuleModifier;
	}
	
	private Symbols(String base) {
		this.base = base;
	}
}
