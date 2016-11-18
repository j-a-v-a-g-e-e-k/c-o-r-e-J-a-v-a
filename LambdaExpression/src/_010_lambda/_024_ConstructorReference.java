/*
 We can use constructor to create a lambda expression.

The syntax to use a constructor reference is

ClassName::new
The keyword new refers to the constructor of the class. The compiler chooses a constructor based on the context.
 */

package _010_lambda;

import java.util.function.Function;
import java.util.function.Supplier;

public class _024_ConstructorReference {
	public static void main(String[] args){
		fn1();
	}
	
	public static void fn1(){
		Supplier<String> s1 = ()->new String();
		System.out.println("Empty String: " + s1.get());
		
		Supplier<String> s2 = String::new;
		System.out.println("Empty String: " + s2.get());
		
		Function<String, String> s3 = (x)->new String(x);
		System.out.println(s3.apply("bimal"));
		
		Function<String, String> s4 = String::new;
		System.out.println(s4.apply("bimal"));
 		
	}
	
}
