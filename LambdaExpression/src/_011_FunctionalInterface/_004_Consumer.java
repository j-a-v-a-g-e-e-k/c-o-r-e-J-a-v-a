/*
Consumer interface represents an operation that accepts a single input argument and returns no result.

Method
Consumer accept
Consumer andThen
there are no compose() and identity() methods.
	before.Consumer will not return anything for this.Consumer
	consumers don't return anything, so there is no identity() method.
 */
package _011_FunctionalInterface;

import java.util.function.Consumer;

public class _004_Consumer {
	public static void main(String[] args){
		//basic
		Consumer<Integer> fn1 = x-> System.out.println(x+">>more");;
		fn1.accept(2);

		//pass as method parameter
		Consumer<Integer> fn3 = x->System.out.println(x+">>more");
		Consumer<Integer> fn4 = x->System.out.println("more>>" + x);
		printIt(fn3, 2);
		printIt(fn4, 2);
		
		//andThen()
		//Consumer andThen returns a composed Consumer that performs, in sequence, for the current operation followed by the after operation.
		//default Consumer<T> andThen(Consumer<? super T> after)
		fn3.andThen(fn4).accept(2);
	}
	public static void printIt(Consumer<Integer> fn2, Integer i){
		fn2.accept(i);
	}

}
