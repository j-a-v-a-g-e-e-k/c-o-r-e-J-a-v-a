package _010_lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _018_ToMap {
	public static List<Person> createPeople() {
		return Arrays.asList(
				new Person("Sara", 20),
				new Person("Sara", 22),
				new Person("Bob", 20),
				new Person("Paula", 32),
				new Person("Paul", 32),
				new Person("Jack", 2),
				new Person("Jack", 72),
				new Person("Jill", 12)
				);
	}

	public static void main(String[] args) {
		List<Person> people = createPeople();

		//create a Map with name and age as key, and the person as value.
		//toMap() takes 2 Functions. Functions takes one parameter and return value.
		
		System.out.println(
				people.stream()
				.collect(Collectors.toMap(
						person -> person.getName() + "-" + person.getAge(),
						person -> person)));
		
	    //given a list of people, create a map where their name is the key and value is all the people with that name.
	    
		//groupingBy()- Returns a Collector implementing a "group by" operation on input elements of type T, grouping elements according to 
		//a classification function, and returning the results in a Map.

	    System.out.println(
	      people.stream()
	            .collect(Collectors.groupingBy(Person::getName)));

	    //collect all ages from the incoming stream and dump it into a list or set	    
	    System.out.println(
	  	      people.stream()
	  	            .collect(Collectors.mapping(Person::getAge, Collectors.toList())));
	    	    
	    System.out.println(
		  	      people.stream()
		  	            .collect(Collectors.mapping(Person::getAge, Collectors.toSet())));
	    
	    System.out.println("Simple List: " + 
		  	      people.stream()
		  	            .collect(Collectors.toList()));
	    
	    
	    //given a list of people, create a map where
	    //their name is the key and value is all the ages of people with that name
	    System.out.println(
	      people.stream()
	            .collect(Collectors.groupingBy(Person::getName, 
	                Collectors.mapping(Person::getAge, Collectors.toList()))));
	}
}

class Person {
	private final String name;
	private final int age;

	public Person(String theName, int theAge) {
		name = theName;
		age = theAge;
	}

	public String getName() { 
		return name; 
	}
	public int getAge() { 
		return age; 
	}

	public String toString() {
		return String.format("%s -- %d", name, age);
	}  
}
