/*
 We can use statements such as break, continue, return, and throw inside the body of a lambda expression.

We cannot use the jump statements to do non-local jump.
 */
package _010_lambda;

import java.util.function.Function;

public class _028_LambdaBody {
	public static void main(String[] args){
		fn1(); fn2();
	}

	public static void fn1(){
		Function<String,String> func1 = y -> {
			for(int i=0;i<10;i++){
				System.out.println(i);
				if(i == 4){
					break;
				}
			}
			return y + " from java2s.com" ;
		};
		System.out.println(func1.apply("hi"));
	}

	//We cannot use break statement in the lambda expression in order to jump out to a for loop outside lambda expression.
	public static void fn2(){
		for(int i=0;i<10;i++){
			System.out.println(i);
			if(i == 4){

				Function<String,String> func1 = y -> {
					//break;
					return y + " from java2s.com" ;
				};
				System.out.println(func1.apply("hi"));
			}
		}
	}
}
