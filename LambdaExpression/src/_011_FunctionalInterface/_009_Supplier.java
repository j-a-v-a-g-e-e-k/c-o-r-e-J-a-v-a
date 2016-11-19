/*
 No compose(), andThen(), identity() methods
 compose(): this.Supplier cannot accept anything from before.Supplier
 andThen(): they will act like 2 independent methods which does not accept anything and return something
 identity(): no input param which can be returned
 */

package _011_FunctionalInterface;

import java.util.function.Supplier;

public class _009_Supplier {
	public static void main(String[] args){
		//basic
		Supplier<String> fn1 =()-> "Hello World";
		System.out.println(fn1.get());

		//pass Supplier as parameter
		fn2(()-> "Hello World");

		//use Constructor as method reference for Supplier.
		Supplier<Employee> fn3 = Employee::new;	
		System.out.println(fn3.get());

		//how to assign user defined function to Supplier with method reference.
		Supplier<Employee> fn5 = _009_Supplier::fn4;
		System.out.println(fn5.get());
	}

	public static void fn2(Supplier<String> fn3){
		System.out.println(fn3.get());
	}

	public static Employee fn4(){
		return new Employee();
	}	
}

class Employee{
	public String toString(){
		return "a employee";
	}
}
