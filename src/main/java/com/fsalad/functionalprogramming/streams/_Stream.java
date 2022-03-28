package com.fsalad.functionalprogramming.streams;

import com.fsalad.functionalprogramming.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.fsalad.functionalprogramming.streams._Stream.Gender.*;

public class _Stream
{
	public static void main(String[] args)
	{
		List<Person> people = new ArrayList<>(
				List.of(new Person("Jane", FEMALE),
						new Person("Jack", MALE),
						new Person("Baki", MALE),
						new Person("Inigo", MALE),
						new Person("Jolyne", FEMALE)));

		/*
		* Everything we learned about functional interfaces so far: Predicate, Function, Consumer, and Supplier are all used in conjuction with streams to write very efficient
		* neat code. All the interfaces we learned so far and their applications will make a lot more sense now when we learn about streams.
		* */

		// The map function handles transformations, and takes in a functional interface. So we pass in a lambda expression which is the equivalent to the functional interface
		// RECALL: Function<Input, Output> functionalInterface = (input) -> output; Notice how the right side, our lambda expression assigns to our left side just fine.
		// So when the stream.map() expects a functional interface, we just pass in a lambda expression similar to how we would when assigning it to a named variable on our right side
		// only this time there's no name

		// 1) We stream our list of people
		// 2) Transform each person in the people list into simply their genders. So we have a list of genders. ex) [male, female, male, female, female]
		// 3) Collect their genders into a Set (RECALL: a set is a data structure that cannot contain duplicate elements). So if MALE or FEMALE is already in the set,
		// it won't assign it again.
		// 4) Then forEach of the elements in the set (will only be 2: {MALE, FEMALE}) we pass a Consumer (RECALL: takes an input and performs an operation/action
		// w/ no return like a void function). I had originally passed .forEach(gender -> System.out.println(gender)), however, there is a shorthand for that. You can replace
		// the lambda with a method reference and it will know to apply the operation you specify on each element. So in the end we have System.out::println.
		// RESULT: We should print each gender, MALE and FEMALE.

		System.out.println("Set of genders");
		people.stream()
			  .map( person -> person.gender )
			  .collect( Collectors.toSet())
			  .forEach( System.out::println );

		System.out.println("Each name's character length:");
		people.stream()
			  .map( person -> person.name )
			  .mapToInt( String::length )
			  .forEach( person -> System.out.print(person + ",") );

		System.out.println();
		System.out.println("Checking to see if all of the people are females");
		Predicate<Person> isFemale = person -> FEMALE.equals( person.gender );
		//allMatch takes a predicate and determines if every entry in the stream matches that criteria/condition. It's possible to pass just the lambda expression on the right
		// but I extracted it as a variable to emphasize that this takes a predicate
		boolean listContainsOnlyFemales = people.stream()
						  .allMatch( isFemale );

		boolean listContainsAFemale = people.stream()
						  .anyMatch( isFemale );

		// There is also a .noneMatch() to check if none of the entries match the criteria, but I won't do an example of that here.

		System.out.println("List contains only females: " + listContainsOnlyFemales);
		System.out.println("List contains a female: " + listContainsAFemale);


		// There are more methods to streams, and I will tackle more of them down below as time goes on
	}

	static class Person {
		private String name;
		Gender gender;
		Person(String nm, Gender gender){
			this.name = nm;
			this.gender = gender;
		}
	}

	enum Gender {
		MALE, FEMALE;
	}
}
