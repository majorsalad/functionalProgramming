package com.fsalad.functionalprogramming.Predicate;

import java.util.function.Predicate;

public class _Predicate
{
	public static void main(String[] args)
	{
		System.out.println("Is the phone number 0700000000 valid: " + isPhoneNumberValid( "07000000000" ));
		System.out.println("Is the phone number 0700000 valid: " + isPhoneNumberValid( "0700000" ));
		System.out.println("Is the phone number 0900780000 valid: " + isPhoneNumberValid("09007800000"));

		System.out.println("Phone number '07000000000' valid (using Predicate this time): " + isPhoneNumValidPredicate.test( "07000000000" ));
		System.out.println("Phone number '0700000' valid (using Predicate this time): " + isPhoneNumValidPredicate.test( "0700000" ));
		System.out.println("Phone number '09007800000' valid (using Predicate this time): " + isPhoneNumValidPredicate.test( "09007800000" ));

		// you can also chain predicates like you can chain function interfaces with .andThen
		System.out.println("Chaining two predicates on the phone number '07000330000'. 'isPhoneNumValid' AND 'containsNumber3': " + isPhoneNumValidPredicate.and( containsNumber3 ).test( "07000330000" ));
		// you can use 'or' instead of 'and' just like you would within an if-statement. The following will check to see if one of the predicates returns true, and return true for the whole
		System.out.println("Chaining two predicates on the phone number '07000000000'. 'isPhoneNumValid' OR 'containsNumber3': " + isPhoneNumValidPredicate.or( containsNumber3 ).test( "07000000000" ));
		// Like the other functional interfaces, there is such thing as a BiPredicate. It works the same way, only it takes in 2 arguments instead of one.
	}

	// Just a simple phone validation method
	static boolean isPhoneNumberValid (String phoneNumber){
		return phoneNumber.startsWith( "07" ) && phoneNumber.length() == 11;
	}

	static Predicate<String> isPhoneNumValidPredicate = (number) ->  number.startsWith( "07" ) && number.length() == 11;

	static Predicate<String> containsNumber3 = number -> number.contains("3");

}
