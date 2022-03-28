package com.fsalad.functionalprogramming.Supplier;

import java.util.function.Supplier;

public class _Supplier
{

	public static void main(String[] args)
	{
		// The supplier is simply a functional interface that takes no input but returns a result. For instance if you
		// wanted to provide yourself with a link/url or some other constant. What's the use? Maybe instead of calling
		// a method from a class over an over again when assigning variables values, you can use a supplier as a short hand?

		/*
				Ex)
				int myVar = MyAmazingClass.MyEvenBetterMethod();
				int myOtherVar = MyAmazingClass.MyEvenBetterMethod();

				Supplier<MyAmazingClass> shorter = MyAmazingClass::MyEvenBetterMethod;
				int myVar = shorter.get();
				int myOtherVar = shorter.get();
		 */
		System.out.println("Using normal function: " + DBConnectionURIUsingMethod());
		// For supplier we use .get. You'll see the same link "supplied" by both the function and the functional interface "Supplier"
		System.out.println("Using supplier: " + DBConnectionURI.get());
	}

	static String DBConnectionURIUsingMethod() {
		return "jdbc:mysql://localhost:3306/";
	}

	static Supplier<String> DBConnectionURI = () -> "jdbc:mysql://localhost:3306/";
}
