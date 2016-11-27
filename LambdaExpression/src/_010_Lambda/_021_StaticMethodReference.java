/*
A lambda expression represents an anonymous function defined in a functional interface.
A method reference creates a lambda expression using an existing method.
The general syntax for a method reference is

Qualifier::MethodName

Two consecutive colons act as a separator.
The MethodName is the name of the method.
Qualifier tells where to find the method reference.

Example
For example we can use String::length to reference the length method from String class. Here String is the qualifier and length is the method name.
We only need to specify the method name.
There is no need to specify the parameter types and return type for the method.
The target type of the method reference is a functional interface. It determines the method's signature and resolves the overloaded method if necessary.

Types of Method References
There are six types of method reference.

TypeName::staticMethod - reference to a static method of a class, an interface, or an enum
objectRef::instanceMethod - reference to an instance method from a object
ClassName::instanceMethod - reference to an instance method from a class
TypeName.super::instanceMethod - reference to an instance method from the supertype of an object
ClassName::new - reference to the constructor of a class
ArrayTypeName::new - reference to the constructor of the specified array type

 */

package _010_Lambda;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _021_StaticMethodReference {
	public static void main(String[] argv) {
		fn1();		fn2();		fn3();
	}  
	
	public static void fn1(){
		// Using  a  lambda  expression
		Function<Integer, String> func1  = x -> Integer.toBinaryString(x); //static method call
		System.out.println(func1.apply(10));

		// Using  a  method  reference
		Function<Integer, String> func2  = Integer::toBinaryString;
		System.out.println(func2.apply(10));
	}
	
	public static void fn2(){
		// Uses a lambda expression
	    BiFunction<Integer, Integer, Integer> func1 = (x, y) -> Integer.sum(x, y); //static method call
	    System.out.println(func1.apply(2, 3));

	    // Uses a method reference
	    BiFunction<Integer, Integer, Integer> func2 = Integer::sum;
	    System.out.println(func2.apply(2, 3));
	}
	
	/*
		Static Method References in Overloading
		We can use overloaded static method in static method reference.
		When the overloaded method we have to pay more attention to the method signature and corresponding functional interface.
	 */
	public static void fn3(){
		// Uses  Integer.valueOf(int)
	    Function<Integer, Integer> func1  = Integer::valueOf; //static method call

	    // Uses  Integer.valueOf(String)
	    Function<String, Integer> func2  = Integer::valueOf; 

	    // Uses  Integer.valueOf(String, int)
	    BiFunction<String, Integer,  Integer> func3  = Integer::valueOf;

	    System.out.println(func1.apply(7)); 
	    System.out.println(func2.apply("7")); 
	    System.out.println(func3.apply("101010101010", 2));
	}
}
