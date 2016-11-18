/*
We can get an instance method reference in two ways, from the object instance or from the class name.

Basically we have the following two forms.

instance::MethodName
ClassName::MethodName
Here the instance represents any object instance. ClassName is the name of the class, such as String, Integer.

instance and ClassName are called the receiver. More specifically, instance is called bounded receiver while ClassName is called unbounded receiver.

We call instance bounded receiver since the receiver is bounded to the instance.

ClassName is unbounded receiver since the receiver is bounded later.

 */
package _010_lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class _022_InstanceMethodReference {
	public static void main(String[] args){
		fn1(); 	fn2(); 	fn3(); fn4(); 
	}

	//============Bound Instance Method Reference===============
	//instance::MethodName
	//The following example shows how to use bound receiver and method with no parameters to create Instance Method References.
	public static void fn1(){
		Supplier<Integer> supplier  = () ->  "java2s.com".length(); 
		System.out.println(supplier.get());	    

		Supplier<Integer> supplier1  = "java2s.com"::length; 
		System.out.println(supplier1.get());
	}

	//instance::MethodName
	//The following example shows how to use bound receiver and method with parameters to create Instance Method References.
	public static void fn2(){
		Util util = new Util();

		Consumer<String> consumer  = str ->  util.print(str);
		consumer.accept("Hello"); 	    

		Consumer<String> consumer1  = util::print;
		consumer1.accept("java2s.com");
	}

	//============Unbound Instance Method Reference===============
	//ClassName::MethodName
	//It is the same syntax we use to reference a static method.

	public static void fn3(){
		Function<String,  Integer> strLengthFunc = String::length; 
	    String name ="java2s.com";
	    int len   =  strLengthFunc.apply(name); 
	    System.out.println("name  = "  +  name + ", length = "  + len);
	    
	    name ="www.java2s.com";
	    len   =  strLengthFunc.apply(name); 
	    System.out.println("name  = "  +  name + ", length = "  + len);
	}
	
	public static void fn4(){
	    BiFunction<String,  String,String> strFunc = Util2::append; 
	    String name ="java2s.com";
	    String s=  strFunc.apply(name,"hi"); 
	    System.out.println(s);
	}
	
}

class Util{
	private int count=0;
	public void print(String s){
		System.out.println(s);
		count++;
	}
}

class Util2{
	  public static String append(String s1,String s2){
	    return s1+s2;
	  }  
	}
