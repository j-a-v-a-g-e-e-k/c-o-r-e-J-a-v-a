/*
BiFunction represents a function that accepts two arguments and produces a result. This is the two-arity specialization of Function.

Methods:
BiFunction apply
BiFunction andThen
compose() method is not possible because before.BiFunction can return only one, but this.BiFunction need 2 input params. 
no identity() method since it cannot return 2 input params.
 */

package _011_FunctionalInterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _003_BiFunction {
	public static void main(String[] args){
		//basic
		BiFunction<Integer,Integer,String> fn1 = (x,y)-> x+y+"";
		System.out.println(fn1.apply(2, 3));
		
		//pass as method parameter
		String result = fn2((x,y)->x+y+"",2,3);
		System.out.println(result);
		
		//andThen()
		//Returns a composed function that first applies this function to its input, and then applies the after function to the result. 
		//If evaluation of either function throws an exception, it is relayed to the caller of the composed function.
		//default <V> BiFunction<T,U,V> andThen(Function<? super R,? extends V> after)
		BiFunction<Integer,Integer,String> fn3 = (x,y)-> x+y+"";
		Function<String,String> fn4 = x->x.concat(">>more") ;
		System.out.println(fn3.andThen(fn4).apply(2, 3));
		System.out.println(fn3.andThen(fn4).andThen(fn4).apply(2, 3));
	}
	
	public static String fn2(BiFunction<Integer,Integer,String> fn3,Integer i1,Integer i2){
		return fn3.apply(2, 3);
	}

}
