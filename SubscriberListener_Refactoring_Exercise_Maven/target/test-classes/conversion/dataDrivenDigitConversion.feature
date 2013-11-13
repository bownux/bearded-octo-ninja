Feature:  Convert Several Digits

	As film-maker, 
	I want to be able to convert numbers from decimal to Roman representation,
	So I can make film completion dates really confusing

Scenario: Convert Single-Digit Decimal Integer

	Given  We are converting decimal integers to roman numerals
	
	When   We expect the following conversions: 
	
	|digit   | numeral  |
	|1       | I        |
	|2       | II       |
	|8       | VIII     |
	|18      | XVIII    |
	|80      | LXXX     |
	|81      | LXXXI     |
	
	Then   The conversions turn out as expected
	