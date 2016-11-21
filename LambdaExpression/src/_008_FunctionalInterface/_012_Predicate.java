/*
Method
Predicate test
Predicate and
Predicate negate
Predicate or
Predicate isEqual
 */

package _008_FunctionalInterface;

import java.util.function.Predicate;

public class _012_Predicate {
	public static void main(String[] args){
		//and()
		//Predicate and returns a composed predicate that represents a short-circuiting logical AND of this predicate and another.
		//default Predicate<T> and(Predicate<? super T> other)
		Predicate<String> fn1 = x->x.length()>0;
		Predicate<String> fn2 = x->x.length()>4;
		System.out.println(fn1.and(fn2).test("Hi"));

		Predicate<String> fn4 = fn1.and(fn2);
		System.out.println(fn4.test("Hi"));
 		
		//or()
		//Predicate or returns a composed predicate that represents a short-circuiting logical OR of this predicate and another.
		//default Predicate<T> or(Predicate<? super T> other)
		System.out.println(fn1.or(fn2).test("Hi"));
		
		//negate()
		//Predicate negate returns a predicate that represents the logical negation of this predicate.
		//default Predicate<T> negate()
		System.out.println(fn2.negate().test("Hi"));
		
		//isEqual()
		//Predicate isEqual returns a predicate that tests if two arguments are equal according to Objects.equals(Object, Object).
		//static <T> Predicate<T> isEqual(Object targetRef)
		System.out.println(Predicate.isEqual("Hi").test("Hi"));
	}
}
