Feature:  Convert Single Digit

	As film-maker, 
	I want to be able to convert a number from decimal to Roman representation,
	So I can make the film completion date confusing


Scenario: Convert Single-Digit Decimal Integer

	Given  Input integer is 1
	When   We convert to Roman representation
	Then   We get back an I
	
