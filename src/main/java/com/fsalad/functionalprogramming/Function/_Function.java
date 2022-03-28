package com.fsalad.functionalprogramming.Function;

import com.fsalad.functionalprogramming.Main;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class _Function
{

	/*
	* An exercise of how we'd use the function interface to help us with functional programming.
	* */
	public static void main(String[] args)
	{
		// FUNCTION - accepts 1 argument returns 1 value
		int incrementedNum = incrementByOne( 1 );
		System.out.println( "Incremented number w/ declarative approach: " + incrementedNum );

		int incrementedNum2 = incrementByOneFunction.apply( 1 ); // .apply to apply/execute the function with the passed parameter
		System.out.println("Incremented number w/ imperative/functional approach: " +incrementedNum2);

		// Chaining functions -- how do we apply one function and then immediately apply another? How do we chain functions together?
		int result = incrementByOneFunction.andThen( multiplyByTen ).apply( incrementedNum2 );
		// OR
		Function<Integer, Integer> incrementAndThenMultiplyByTen = incrementByOneFunction.andThen( multiplyByTen );
		int resultWDeclaration = incrementAndThenMultiplyByTen.apply( incrementedNum2 );

		System.out.println("Number result from chained method w/o assignment: " + result);
		System.out.println("Number result from chained method w/ assignment: " + resultWDeclaration);

		// BIFUNCTION - accepts 2 arguments returns 1 value
		System.out.println("Number result from bifunction incrementAndMultiply: " + incrementAndMultiply.apply(3,5));

		// BIPREDICATE - evaluate true or false based on two parameters instead of one (revisit predicate in the main method)
		Person Jack = new Person( "Jack", Gender.MALE );
		Person Sohyun = new Person( "Sohyun", Gender.FEMALE );
		Bathroom maleBathroom = new Bathroom( 45, Gender.MALE );

		// Instead of apply like with functions, and consume like with consumers, we use the keyword .test() to trigger the predicate
		System.out.println(Jack.name + " can use the male bathroom?: " + canUseBathroom.test( Jack.gender, maleBathroom.genderType ));
		System.out.println(Sohyun.name + " can use the male bathroom?: " + canUseBathroom.test( Sohyun.gender, maleBathroom.genderType ));
	}

	// Declarative approach, i.e. using a normal function
	static int incrementByOne(int num)
	{
		return ++num;
	}

	// Imperative approach, i.e. Functional approach using functional interfaces and lambda expressions
	static Function<Integer,Integer> incrementByOneFunction =
			number -> ++number; //on the right hand side we have a lambda expression, with it's input and output

	static Function<Integer, Integer> multiplyByTen = number -> number * 10;

	static BiFunction<Integer, Integer, Integer> incrementAndMultiply = (numToIncrement, multiplier) -> (++numToIncrement) * multiplier;

	static BiPredicate<Gender, Gender> canUseBathroom = (personsGender, bathroomTypeGender) -> personsGender == bathroomTypeGender;

	static class Person {
		private String name;
		Gender gender;
		Person(String nm, Gender gender){
			this.name = nm;
			this.gender = gender;
		}
	}

	static class Bathroom {
		private int space;
		Gender genderType;
		Bathroom(int space, Gender genderType){
			this.space = space;
			this.genderType = genderType;
		}
	}

	enum Gender {
		MALE, FEMALE;
	}

}
