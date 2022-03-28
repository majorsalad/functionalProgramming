package com.fsalad.functionalprogramming.Consumer;

import lombok.Data;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

// Consumer represents an operation that accepts a single input argument and has no return. They are the functional programming version of a void function.
public class _Consumer
{

	public static void main(String[] args)
	{
		// Normal java function / Declarative approach
		Customer maria = new Customer( "Maria", "9999" );
		greetCustomer( maria );
		// Consumer Functional interface / Imperative approach
		greetCustomerConsumer.accept(maria); //instead of apply like with the functional and bifunctional interfaces, we use .accept for Consumers
		greetCustomerConsumer2.accept( maria, false );
	}

	static void greetCustomer(Customer customer){
		System.out.println("Hello " + customer.customerName + ", thanks for registering phone number " +
				customer.customerPhoneNumber);
	}

	static Consumer<Customer> greetCustomerConsumer = customer -> System.out.println("Hello " + customer.customerName + ", thanks for registering phone number " +
			customer.customerPhoneNumber);

	static BiConsumer<Customer, Boolean> greetCustomerConsumer2 = (customer, showPhoneNumber) -> System.out.println("Hello " + customer.customerName + ", thanks for registering phone number " +
			(showPhoneNumber ? customer.customerPhoneNumber : "*********" ));

	@Data
	static class Customer{
		private final String customerName;
		private final String customerPhoneNumber;

	}
}
