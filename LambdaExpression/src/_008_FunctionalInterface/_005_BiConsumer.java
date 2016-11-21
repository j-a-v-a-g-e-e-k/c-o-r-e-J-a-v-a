/*
Method
BiConsumer accept
BiConsumer andThen
 */

package _008_FunctionalInterface;

import java.util.function.BiConsumer;

public class _005_BiConsumer {
	public static void main(String[] args){
		//basic
		BiConsumer<Integer,Integer> fn1 = (x,y)->System.out.println(x+y);
		fn1.accept(2, 3);
		
		//pass as method parameter
		fn2((x,y)->System.out.println(x+y),4,5);
		
		//andThen()
		//BiConsumer andThen returns a composed BiConsumer that performs, in sequence, this operation followed by the after operation.
		//default BiConsumer<T,U> andThen(BiConsumer<? super T,? super U> after)
		BiConsumer<Integer,Integer> fn3 = (x,y)->System.out.println(x+y+">>more");
		BiConsumer<Integer,Integer> fn4 = (x,y)->System.out.println(x+y+"more>>");
		fn3.andThen(fn4).accept(6, 7);
	}
	
	public static void fn2(BiConsumer<Integer,Integer> fn3, Integer i1, Integer i2){
		fn3.accept(2, 3);
	}
}
