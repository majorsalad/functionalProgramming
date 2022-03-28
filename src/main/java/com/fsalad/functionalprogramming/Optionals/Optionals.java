package com.fsalad.functionalprogramming.Optionals;

import java.util.Optional;

public class Optionals
{
	/*
	* Introduction:
	*
	* We've all written code like this before:
	*
	* String email = null;
	*
	* if(person.getEmail() != null) { //if a var != null assign it to a value, otherwise assign it to a default value...
	* 	email = email.toLowerCase();
	* } else {
	* 		//default behavior
	* }
	*
	* But that's a code smell, and Java Optionals allow us to cleaner APIs without having to do that for
	* every potential null variable and any client looking at the fields/variables know
	* whether those fields might be nullable or not.
	*
	* The benefit for that is that we can then use functional programming and write cleaner code.
	*
	* */


	public static void main(String[] args)
	{
		// We have Optional.empty() [], Optional.of() [means you know for sure the value is not null], and Optional.ofNullable() [you're not sure whether the value will be present]

		System.out.println("Ex 1) With Optional.empty()");
		Optional<Object> empty = Optional.empty();
		System.out.println(empty); //Prints Optional.empty.
		//We have a few methods within the optional class to find out a few things:
		System.out.println("Is there a value inside the optional?: " + empty.isPresent()); //isPresent tells us if there is a value within that optional
		System.out.println("Is there no value inside the optional?: " + empty.isEmpty()); //isEmpty tells us if there is no value within that optional

		System.out.println("Ex 2) With Optional.of( placing a value inside)");
		Optional<String> stringOptional = Optional.of( "Hello" );
		System.out.println("Is there a value inside the optional?: " + stringOptional.isPresent());
		System.out.println("Is there no value inside the optional?: " + stringOptional.isEmpty());

		System.out.println("Ex 3) .orElse() logic. Or else is used to assign values when the value in the optional is NOT present");

		Optional<String> anotherStringOptional = Optional.ofNullable(null); //if we use Optional.of() with a null value passed in we'll get a null pointer exception, so
		//when we're not sure if the value passed in will be null or not, we use Optional.ofNullable
		String orElse = anotherStringOptional.orElse( "Default backup message (shown when there is no value) :)" );
		System.out.println("Our stringOptional is empty so using orElse() we assigned a default value of: " + orElse);

		System.out.println("Ex 4) Chaining logic based on Optionals");

		Optional<String> hello = Optional.ofNullable( "Hello" );

		String message = hello.map( String::toUpperCase )
				.orElseGet( () -> { //orElseGet takes a Supplier (a function that has no input but returns a result), so we can use it to execute some complex logic in the case that the optional is empty
					// ... imaginary complex logic
					return "World";
				} );

		System.out.println(message); //if you set the optional<String> of hello to "Hello" it prints out HELLO, if you set it to null, it prints "World"
		// For some reason the .map function does not cause a null pointer exception. Maybe since we're expecting a null value with ofNullable it skips that map and goes
		// straight to the orElseGet


		/*
		*
		* In the same chaining we can also use .orElseThrow() to throw an exception if the value in the Optional is not present, ex)
		* hello.map(String::toUpperCase)
		* .orElseThrow(IllegalStateException::new); or whatever other exception type you'd like to throw in the event that the value in the optional is not present
		*
		* */

		System.out.println("Ex 5) Optionals using ifPresent: ");

		/**
		 *
		 * Swapped this out below for method reference which is the equivalent but shorthand
		 *  word -> {
		 * 				System.out.println(word);
		 *          }
		 *
		 *          Below checks if the optional has a value, if it does the consumer performs whatever computations/executions. Since there's a value here, it will print
		 *          the value below
		 */

		hello.ifPresent( System.out::println );

		hello.ifPresentOrElse( System.out::println, //this second part (this is the else part) is a Runnable, an empty action executed in the case that there is no value present
				() -> {
			System.out.println("No word present.");
		} );

		//This covers for any potential null pointer exceptions as opposed to doing something like person.getEmail(). If we did .getEmail after it's been passed a null,
		// we'd get a null pointer exception. The optionals allow us to work around that in a neat and clean way.
		Person person = new Person("James", null);
		String email = person.getEmail()
						 .map( String::toUpperCase )
						 .orElse( "Email not provided." );
		System.out.println( email );

		String personsEmail;

		// Another way of getting the value inside the optional is manually checking using an if-else statement to see if the values present and then assigning
		// .getEmail().get(). The .get() part gets the actual value from within the optional if it exists (which we already checked for). This is a more roundabout way
		// of doing it and kind of defeats the purpose of using an Optional in the first place since we wanted to avoid if-else, but this is for the purpose of showing how
		// to unwrap an optional.

		// How to unwrap an optional manually.
		if(person.getEmail().isPresent()){

			personsEmail = person.getEmail().get();

		} else {
			personsEmail = "Email not provided";
		}

	}


	static class Person {
		private final String name;
		private final String email;

		Person(String nm, String email){
			this.name = nm;
			this.email = email;
		}

		public String getName() {
			return name;
		}

		//instead of our getter returning an email, since we expect our value of email to be nullable, we have our getter return an Optional<String> to avoid
		// null pointer exceptions. See implementation above to understand
		public Optional<String> getEmail(){
			return Optional.ofNullable(email);
		}
	}
}
