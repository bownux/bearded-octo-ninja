 Instructions for the DecimalToRomanNumber_Refactoring exercise:
 
 	Please begin by refactoring this code until it passes the Cengage checkstyle rules, 
 	keeping the existing end-to-end tests running green at all times. Run the tests as 
 	frequently as you can, and check in green-bar code frequently, in order to 
 	make it easy to revert to a green-bar state if you get in trouble with a particular
 	refactoring. 
 	
 	The specific automated and manual refactorings you will use will likely be only these:
 	
 		--Rename
 		--Extract Method
 		--Extract Class/Enum (manual)
 		--Change Method Signature
 		--Pull Up method
 		--Change variable scope
 		--Move class
 
 	As you refactor, try to use the Eclipse code proposals as much as possible. 
 	
 	At that point, examine your resulting classes for violations of the SRP (Single Responsibility Principle). 
 	Are any of your classes or methods doing more work than they should, or holding more state than they should?
 	How can you tell?
 	
 	Another way to ask the same questions: are any of your classes and methods mixing together different domain ideas?
 	Indeed, what are the inherent domain ideas in this problem domain?
 	
 	Do a little google research to learn about the problem domain of converting decimal integers less than 4000
 	to Roman numbers. What terms do people use consistently and precisely? 
 	
 	What are the inherent operands (e.g., decimal number, decimal digit, decimal place)?  
 	What are the inherent operations and rules?
 	
 	What are the least confusing, most commonly used domain terms for these operands? 	
 	What terms do not have multiple definitions?
 	
 	("Roman Numeral," for example, seems to have at least two definitions.)
 	What domain ideas do you prefer for your implementation?
 	
 	--------------------------------------
 	Hint: From a useful definition of number "Place" on-line: 
 	http://www.factmonster.com/ipka/A0881929.html

		Decimal Places

		One decimal place to the left of the decimal point is the ones place. One decimal place to the right of the 
		decimal place is the tenths place.

		Keep your eye on the 9 in the visualization below to see where the decimal places fall.

		millions				9,000,000.0
		hundred thousands	  	900,000.0
		ten thousands	    	90,000.0
		thousands	      		9,000.0
		hundreds	         	900.0
		tens	           		90.0
		ones	             	9.0
		tenths	             	0.9
		hundredths	            0.09
		thousandths	            0.009
		ten thousandths	        0.0009
		hundred thousandths	    0.00009
		millionths	           	0.000009
 	-------------------------------------- 	
 	
 	List out your preferred terms for each domain idea (operand, operation, rule, etc) in a short glossary. 
 	How can you express each domain idea once and only once in the code? 
 	How can you name each package, class, and method consistently, using these domain terms?
 	
 	Given these terms, can you write short pseudo code to express what the algorithm is doing, and how it 
 	is doing it? Example:
 	
 	"Given a single decimal integer less than 4000 :
 		For each decimal place digit in the number, starting with the thousands place..."  etc
   

	Finally, though the checkstyle rule for maximum methods per class limits you to 6, your average should be closer 
	to two methods per class once you are done. 
	
	After you have complete the exercise once, watch the 15-minute example refactoring video to get some more ideas. 
	
	Do you feel that your refactored code is better, or worse than the code at the end of the video?  
	Specifically how? 
	
	Now, starting from the original starting point of the codebase, perform all your refactorings in a single 
	flow, as is shown in the video. Can you take smaller steps than the video takes? Can you end up at a better 
	place? 
	
	
	
	
	
	