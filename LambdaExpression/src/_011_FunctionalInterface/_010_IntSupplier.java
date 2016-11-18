package _011_FunctionalInterface;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class _010_IntSupplier {
	public static void main(String[] args){
		//how to generate fibonnaci from IntSupplier.
		IntSupplier fib = new IntSupplier() {
			int previousValue = 5;
			int currentValue = 6;

			@Override
			public int getAsInt() {
				int nextValue = currentValue + previousValue;
				this.previousValue=currentValue;
				this.currentValue=nextValue;
				return previousValue;
			}
		};	
		//state of IntSupplier will change with every generation, and the changed state will be used to generate next integer.
		IntStream.generate(fib).limit(10).forEach(System.out::println);
	}
}
