/*
Method
IntPredicate test
IntPredicate and
IntPredicate negate
IntPredicate or

Similar operations are available for LongPredicate and DoublePredicate
 */

package _011_FunctionalInterface;

import java.util.function.IntPredicate;

public class _014_IntPredicate {
	public static void main(String[] args){
		//and()
		//IntPredicate and returns a composed predicate that represents a short-circuiting logical AND of this predicate and another.
		//default IntPredicate and(IntPredicate other)
		IntPredicate fn1 = x->x>0;
		IntPredicate fn2 = x->x>4;
		System.out.println(fn1.and(fn2).test(3));
		
		//or()
		//Returns a composed predicate that represents a short-circuiting logical OR of this predicate and another. When evaluating the 
		//composed predicate, if this predicate is true, then the other predicate is not evaluated. Any exceptions thrown during evaluation of 
		//either predicate are relayed to the caller; if evaluation of this predicate throws an exception, the other predicate will not be evaluated.
		//default IntPredicate or(IntPredicate other)
		System.out.println(fn1.or(fn2).test(3));
		
		//negate()
		//IntPredicate negate returns a predicate that represents the logical negation of this predicate.
		//default IntPredicate negate()
		System.out.println(fn1.negate().test(3));
	}
}
