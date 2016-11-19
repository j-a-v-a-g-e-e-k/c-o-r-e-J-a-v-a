/*
We can get an instance method reference in two ways, from the object instance or from the class name.
Basically we have the following two forms.

instance::MethodName
ClassName::MethodName --?? does not work

Here the instance represents any object instance. ClassName is the name of the class, such as String, Integer.
instance and ClassName are called the receiver. More specifically, instance is called bounded receiver while ClassName is called unbounded receiver.
We call instance bounded receiver since the receiver is bounded to the instance.
ClassName is unbounded receiver since the receiver is bounded later.

 */
package _010_lambda;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class _022_InstanceMethodReference {
	public static void main(String[] args){
		fn1(); 
	}

	//============Bound Instance Method Reference===============
	//instance::MethodName
	//The following example shows how to use bound receiver and method with no parameters to create Instance Method References.
	public static void fn1(){
		Supplier<Integer> supplier  = () ->  "Hello".length(); 
		System.out.println(supplier.get());	    

		Supplier<Integer> supplier1  = "Hello"::length; 
		System.out.println(supplier1.get());

	//instance::MethodName
	//The following example shows how to use bound receiver and method with parameters to create Instance Method References.
		Util util = new Util();

		Function<String,Integer> consumer  = str ->  util.print(str);
		consumer.apply("Hello"); 	    

		Function<String,Integer> consumer1  = util::print; 
		consumer1.apply("Hello");

	//============Unbound Instance Method Reference===============
	//ClassName::MethodName
	//It is the same syntax we use to reference a static method.
		BiFunction<Util,String,Integer> fn3=(x,y) ->x.print(y);
		System.out.println(fn3.apply(new Util(), "Hello Bimal"));
		
		Function<String,  Integer> fn1 = String::length; 
		System.out.println(fn1.apply("Hello")); 
		
		BiFunction<Util,String,Integer> fn2 = Util::print; //unbound receiver
		//print takes String and return Integer, but we have to specify Util as well to bound the receiver later.
		System.out.println(fn2.apply(new Util(), "Hello")); //bound the receiver by providing the instance
	}
}

class Util{
	public int print(String s){
		return s.length();
	}
}
