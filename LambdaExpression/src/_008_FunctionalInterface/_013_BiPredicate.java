/*
Method
BiPredicate test
BiPredicate and
BiPredicate negate
BiPredicate or
 */
package _008_FunctionalInterface;

import java.util.function.BiPredicate;

public class _013_BiPredicate {
	public static void main(String[] args){
		// how to use BiPredicate as function parameter
		fn1((x,y)->x==y, 2, 3);
		
		//and()
		//BiPredicate and method returns a composed predicate that represents a short-circuiting logical AND of this predicate and another.
		//default BiPredicate<T,U> and(BiPredicate<? super T,? super U> other)
		BiPredicate<Integer,Integer> fn2 = (x,y)->x==y;
		BiPredicate<Integer,Integer> fn3 = (x,y)->x>2&&y>2;
		System.out.println(fn2.and(fn3).test(3, 4));
		
		//or()
		//BiPredicate or returns a composed predicate that represents a short-circuiting logical OR of this predicate and another.
		//default BiPredicate<T,U> or(BiPredicate<? super T,? super U> other)
		System.out.println(fn2.or(fn3).test(3, 4));
		
		//negate()
		//BiPredicate negate returns a predicate that represents the logical negation of this predicate.
		//default BiPredicate<T,U> negate()
		System.out.println(fn3.negate().test(1, 1));
	}
	
	public static void fn1(BiPredicate<Integer,Integer> fn3, Integer i1, Integer i2){
		System.out.println(fn3.test(i1, i2));
	}
}
