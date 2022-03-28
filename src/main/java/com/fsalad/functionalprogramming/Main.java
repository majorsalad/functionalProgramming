package com.fsalad.functionalprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>(
                List.of(new Person("Jane", Gender.FEMALE),
                        new Person("Jack", Gender.MALE),
                        new Person("Baki", Gender.MALE),
                        new Person("Inigo", Gender.MALE),
                        new Person("Jolyne", Gender.FEMALE)));

        //Imperative way
        List<Person> females = new ArrayList<>();
        for(Person person : persons){
            if(person.gender == Gender.FEMALE){
                females.add(person);
            }
        }

        for(Person person : females){
            System.out.println(person.name);
        }

        //Declarative Way
        List<Person> women = persons.stream()
                .filter(person -> Gender.FEMALE == person.gender)
                .collect(Collectors.toList());

        women.forEach((woman) -> System.out.println(woman.name));

        /*
        * This is a predicate. A function that accepts one value and returns a boolean. That is what we passed in the .filter
        * method of the stream. This assignment shows the type of what we passed. A Predicate that accepts Person as input and
        * through that, it returns a boolean based on whatever assessment you give it.
        *
        * Below we have a predicate that takes type of Person and using that person, makes an evaluation whether the person is of
        * type MALE and returns true/false.
        * */
        Predicate<Person> personPredicate = person -> Gender.MALE == person.gender;

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
