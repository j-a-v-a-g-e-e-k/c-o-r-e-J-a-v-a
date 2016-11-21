package _010_lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class _009_Lambda {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				System.out.println("In another thread");
			}
		});
		t1.start();		    
		System.out.println("In main");
		
		/*
		 function has 4 things
			1. name - anonymous
      				2. parameter list
      				3. body
			4. return type - inferred
		 */

		//LAMBDA expression
		Thread t2 = new Thread(() -> System.out.println("Lambda: In another thread"));
		t2.start();
		System.out.println("In main again");
		
//Each lambda expression can be implicitly assigned to one of the interface called Functional interface. For example we can create 
//Runnable interface’s reference from lambda expression like below:
		Runnable r = () -> System.out.println("Lambda: In another thread");
		
//This type of conversion is automatically taken care by compiler when we don't specify the functional interface. So the compiler automatically 
//deduced that lambda expression can be casted to Runnable interface from Thread class’s constructor signature public Thread(Runnable r) { }.
		new Thread(() -> System.out.println("Lambda: In another thread"));
		
		//2nd example
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

		Collections.sort(names, new Comparator<String>() {
			public int compare(String a, String b) {
				return b.compareTo(a);
			}
		});
		
		System.out.println(names);
		
		names = Arrays.asList("peter", "anna", "mike", "xenia");
		System.out.println(names);
		
		Collections.sort(names, (a, b) -> b.compareTo(a));
		
		System.out.println(names);
	}
}
