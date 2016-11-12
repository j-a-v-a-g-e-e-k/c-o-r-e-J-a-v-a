package com.design.pattern.behavioral;

import java.util.ArrayList;
import java.util.List;

class Person{
	String name;
	Person (String name){
		this.name = name;
	}
	public String toString(){
		return "Me name is " + name;
	}
}

interface PersonCollection{
	public void addPerson(Person e);
	public void removePerson(Person e);
	public PersonIterator iterator();
}

interface PersonIterator{
	public boolean hasNext();
	public Person next();
}

class PersonCollectionImpl implements PersonCollection{
	List<Person> list = new ArrayList<Person>();
	public void addPerson(Person e){
		list.add(e);
	}
	public void removePerson(Person e){
		list.remove(e);
	}
	public PersonIterator iterator(){
		return new PersonIteratorImpl();
	}
	private class PersonIteratorImpl implements PersonIterator{
		int index;
		public boolean hasNext(){
			if (index >= list.size()) return false;
			else return true;
		}
		public Person next(){
			return list.get(index++);
		}
	}
}
public class IteratorDemo {
	public static void main(String [] args){
		PersonCollection collection = new PersonCollectionImpl();
		collection.addPerson(new Person("Bimal"));
		collection.addPerson(new Person("Andy"));
		collection.addPerson(new Person("Sandy"));
		PersonIterator iterator = collection.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}
