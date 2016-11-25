//Inside Story: How lambda expression access local and instance variables
package _010_lambda;

import java.util.Arrays;
import java.util.List;

public class _028_Scope {
	public static void main(String[] args){
		repeatMessage("Hello", 5);
		System.out.println("Finished repeatMessage()");

		forLoop();
		enhancedForLoop();
		//repeatMessage("Hi",1,1);
		array();
	}

	public static void repeatMessage(String text, int count){
		Thread t = new Thread(()->
		{
			for(int i=0;i<count;i++){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(text);
			}
		});
		t.start();		
	}


	/*
OUTPUT:
Finished repeatMessage()
Hello
Hello
Hello
Hello
Hello

The code of the lambda expression has run long after the call to repeatMessage has returned and the parameter variables are gone. 
How do the text and count variables stay around when the lambda expression is ready to execute?

To understand what is happening, we need to refine our understanding of a lambda expression. A lambda expression has three ingredients:
1. A block of code
2. Parameters
3. Values for the free variables—that is, the variables that are not parameters and not defined inside the code

In our example, the lambda expression has two free variables, text and count. The data structure representing the lambda expression must store the 
values for these variables—in our case, "Hello" and 1000. We say that these values have been captured by the lambda expression. (It’s an 
implementation detail how that is done. For example, one can translate a lambda expression into an object with a single method, so that the values 
of the free variables are copied into instance variables of that object.)

NOTE: The technical term for a block of code together with the values of free variables is a closure. In Java, lambda expressions are closures.

As you have seen, a lambda expression can capture the value of a variable in the enclosing scope. To ensure that the captured value is well defined, 
there is an important restriction. In a lambda expression, you can only reference variables whose value doesn’t change. This is sometimes described by 
saying that lambda expressions capture values, not variables. For example, the following is a compile-time error:
	 */

	public static void forLoop(){
		for (int i=0; i>10;i++){
			//new Thread(()->System.out.println(i)).start(); // Error—cannot capture i
		}
	}

	/*
The lambda expression tries to capture i, but this is not legal because i changes. There is no single value to capture. The rule is that a lambda 
expression can only access local variables from an enclosing scope that are effectively final. An effectively final variable is never modified—it 
either is or could be declared as final
	 */

	public static void enhancedForLoop(){
		List<String> args = Arrays.asList("red","yellow","blue");
		for(String arg: args){
			new Thread(()->System.out.println(arg)).start();; // this works
		}
	}

	/*
A new variable arg is created in each iteration and assigned the next value from the args array. In contrast, the scope of the variable i in 
the preceding example was the entire loop.
As a consequence of the “effectively final” rule, a lambda expression cannot mutate any captured variables.
	 */
	
	public static void repeatMessage(String text, int count, int threads) {
	    new Thread( () -> {
	        while (count > 0) {
	            //count--; // Error: Can't mutate captured variable
	            System.out.println(text);
	        }
	    }).start();
	}
	
	/*
This is actually a good thing, as we don't want shared mutability.

However don’t count on the compiler to catch all concurrent access errors. The prohibition against mutation only holds for local variables. If count 
is an instance variable or static variable of an enclosing class, then no error is reported even though the result is just as undefined.

Also even for local variables, One can find a way around the check for inappropriate mutations by using an array of length 1
	 */

	public static void array(){
		int[] counter = new int[1];
		new Thread(() -> System.out.println(counter[0]=99)).start();
	}
	
	/*
The counter variable is effectively final—it is never changed since it always refers to the same array, so you can access it in the lambda expression.

Also you cannot access default interface methods from within lambda expressions. But you can access static interface methods.
	 */
	
	public static void fn4(){
		//MyInterface3 fn5 = x->MyInterface3.getDouble(x); //compiler error
		MyInterface3 fn6 = x->MyInterface3.getStaticDouble(x);
	}	
}

interface MyInterface3{
	int getInt(String s);
	default int getDouble(String s){
		return Integer.parseInt(s) *2;
	}
	static int getStaticDouble(String s){
		return Integer.parseInt(s) *2;
	}
}
	
	
	