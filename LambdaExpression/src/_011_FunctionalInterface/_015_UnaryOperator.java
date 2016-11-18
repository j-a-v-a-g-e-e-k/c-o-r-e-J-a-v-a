package _011_FunctionalInterface;

import java.util.function.UnaryOperator;

public class _015_UnaryOperator {
	public static void main(String[] args){
		//UnaryOperator identity returns a unary operator that always returns its input argument.
		UnaryOperator<Integer> fn1 = UnaryOperator.<Integer>identity() ;
		System.out.println(fn1.apply(2));
	}
}
