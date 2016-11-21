/*
Function represents a function that accepts one argument and produces a result.

Methods:
Function apply
Function compose
Function andThen
Function identity
 */

package _008_FunctionalInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class _002_Function {
	public static void main(String[] args){
		//basic
		Function<Integer,String> fn = (x) -> Integer.toString(x) ;
		System.out.println(fn.apply(2));

		//pass as method parameter
		String result = fn2((x)->Integer.toString(x),3);
		System.out.println(result);
		
		//create function from method reference
		System.out.println(fn9(Integer::doubleValue,3));
		//Cannot use Integer::toString, because toString() is overloaded in Integer class.
		
		//ERROR: Ambiguous method reference: both toString() and toString(int) from the type Integer are eligible
		//Function<Integer,String> fn14 = Integer::toString;
		 
		//use String java.lang.Integer.toString() -- non static method
		Supplier<String> fn13 = new Integer(2)::toString;
		//use String java.lang.Integer.toString(int i) -- static method
		IntFunction<String> fn11 = Integer::toString;
		//use String java.lang.Integer.toString(int i, int radix) -- static method
		BiFunction<Integer, Integer, String> fn12 = Integer::toString;

		//compose()
		//Function compose returns a composed function that first applies the before function to its input, and then applies this function to the result.
		//default <V> Function<V,R> compose(Function<? super V,? extends T> before)
		Function<Integer,String> fn3 = (x)->Integer.toString(x);
		Function<String,Integer> fn4 = (x)->Integer.parseInt(x);		
		System.out.println(fn3.compose(fn4).apply("2222").length());
		System.out.println(fn4.compose(fn3).apply(2222).doubleValue());
		//compose() returns a new function. returns same result as above.
		Function<String,String> fn5 = fn3.compose(fn4);
		System.out.println(fn5.apply("2222").length());

		//andThen()
		//Function andThen returns a composed function that first applies this function to its input, and then applies the after function to the result.
		//default <V> Function<T,V> andThen(Function<? super R,? extends V> after)
		System.out.println(fn3.andThen(fn4).apply(2222).doubleValue());
		System.out.println(fn4.andThen(fn3).apply("2222").length());
		//andThen() returns a new function. returns same result as above.
		Function<Integer,Integer> fn6 = fn3.andThen(fn4);
		System.out.println(fn6.apply(2222).doubleValue());

		//identity()
		//Function identity returns a function that always returns its input argument.
		//static <T> Function<T,T> identity()
		Function<Integer,Integer> fn7 = Function.<Integer>identity();
		System.out.println(fn7.apply(2));

		List<Double> numbers = Arrays.asList(10D, 4D, 12D);
		// you can use identity to not modify them
		System.out.println(fn8(numbers, Function.<Double>identity()));
	}

	public static String fn2(Function<Integer,String> f, Integer i){
		return f.apply(i);
	}
	
	public static Double fn9(Function<Integer,Double> f, Integer i){
		return f.apply(i);
	}

	private static List<Double> fn8(List<Double> numbers, Function<Double, Double> fx) {
		List<Double> result = new ArrayList<>();
		for (Double number : numbers) {
			result.add(fx.apply(number));
		}
		return result;
	}
}
