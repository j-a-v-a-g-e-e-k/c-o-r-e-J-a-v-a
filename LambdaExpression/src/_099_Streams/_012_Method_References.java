package _099_Streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class _012_Method_References {

	public static void main(String[] args){
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		//instance method reference
		numbers.forEach(System.out::println);

		//static method reference
		//map takes functional interface-Function
		numbers.stream()
				.map(new Function<Integer, String>() {
					public String apply(Integer x) {
						return String.valueOf(x); //Static method
					}})
				.forEach(System.out::println);
		
		//static method reference
		numbers.stream()
				.map(String::valueOf)
				.forEach(System.out::println);
		
		//calling method on the object itself
		numbers.stream()
				.map(new Function<Integer, String>() {
					public String apply(Integer x) {
						String s = String.valueOf(x);
						return s.toString(); //calling method on the object itself
					}
				})
				.forEach(System.out::println);
		
		//calling method on the object itself
		numbers.stream()
				.map(String::valueOf)
				.map(String::toString)
				.forEach(System.out::println);	    
	}
}
