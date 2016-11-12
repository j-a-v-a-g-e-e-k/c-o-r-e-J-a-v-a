package com.jain;

class Person{
	String firstName;
	String lastName;

	Person(){	}

	Person(String firstName, String lastName){
		this.firstName=firstName;
		this.lastName=lastName;
	}
	
	public String toString(){
		return firstName + " " + lastName;
	}
}

@FunctionalInterface
interface PersonFactory{
	Person create(String firstName, String lastName);
}

public class Ex4 {

	public static void main(String[] args){
		PersonFactory factory = Person::new;
		Person p=factory.create("Bimal", "Jain");
		System.out.println(p);
	}
}
