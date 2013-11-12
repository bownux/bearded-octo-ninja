 package com.industriallogic.exercise;

public class DecimalToRomanConverter {
		
	public String convert(int decimal) {
		return  thousands(decimal / 1000 % 10) + 
				convertRomanUnit(decimal / 100 % 10, "C", "D", "M") +
				convertRomanUnit(decimal / 10 % 10, "X", "L", "C") + 
				convertRomanUnit(decimal % 10, "I", "V", "X");
	}

	private String thousands(int number) {
		String retValue = "";
		for (int i = 0; i < number; i++) {
			retValue += "M";
		}
		return retValue;
	}

	private String convertRomanUnit(int digit, String unit, String five, String ten) {
		if (digit <= 3) {
			String retValue = "";
			for (int i = 0; i < digit; i++) {
				retValue += unit;
			}
			return retValue;
		}
		if (digit == 4) {
			return unit + five;
		}
		if (digit <= 8) {
			String retValue = five;
			for (int i = 5; i < digit; i++) {
				retValue += unit;
			}
			return retValue;
		}
		return unit + ten;
	}

}
