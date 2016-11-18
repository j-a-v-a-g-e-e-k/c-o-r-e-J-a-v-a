/*
 Similar methods are available for LongUnaryOperator and DoubleUnaryOperator 
 */
package _011_FunctionalInterface;

import java.util.function.IntUnaryOperator;

public class _016_IntUnaryOperator {
	public static void main(String[] args){
		//basic
		IntUnaryOperator fn1 = x->x+x;
		System.out.println(fn1.applyAsInt(3));
		
		//compose
		//IntUnaryOperator compose returns a composed operator that first applies the before operator to its input, and then applies this operator to the result.
		//default IntUnaryOperator compose(IntUnaryOperator before)
		IntUnaryOperator fn2 = x->x+x+x;
		System.out.println(fn1.compose(fn2).applyAsInt(3));
		
		//andThen
		//IntUnaryOperator andThen returns a composed operator that first applies this operator to its input, and then applies the after operator to the result.
		//default IntUnaryOperator andThen(IntUnaryOperator after)
		System.out.println(fn1.andThen(fn2).applyAsInt(3));
		
		//identity()
		//IntUnaryOperator identity returns a unary operator that always returns its input argument.
		//static IntUnaryOperator identity()
		IntUnaryOperator fn3 = IntUnaryOperator.identity();
		System.out.println(fn3.applyAsInt(3));		
	}
}
