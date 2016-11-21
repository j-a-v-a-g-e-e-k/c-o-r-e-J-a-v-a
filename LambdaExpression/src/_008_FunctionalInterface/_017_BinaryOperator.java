package _008_FunctionalInterface;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class _017_BinaryOperator {
	public static void main(String[] args){
		//basic
		BinaryOperator<Integer> fn1 = (x,y) -> x+y;
		System.out.println(fn1.apply(2, 2));
		
		//minBy()
		//BinaryOperator minBy method returns a BinaryOperator which returns the lesser of two elements according to the specified Comparator.
		//static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator)
		BinaryOperator<Integer> fn2 = BinaryOperator.minBy(Comparator.naturalOrder());
		System.out.println(fn2.apply(2, 3));
		
		//maxBy()
		//BinaryOperator maxBy method returns a BinaryOperator which returns the greater of two elements according to the specified Comparator.
		//static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator)
		BinaryOperator<Integer> fn3 = BinaryOperator.maxBy(Comparator.naturalOrder());
		System.out.println(fn3.apply(2, 3));		
	}
}
