package _010_lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class _013_Function_Composition {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		//given the values, double the even numbers and total.		    
		int result = 0;		    
		for(int e : numbers) {
			if(e % 2 == 0) {
				result += e * 2;
			}
		}		    
		System.out.println(result);

		//given the values, double the even numbers and total.
		System.out.println(
				numbers.stream()
				.filter(new Predicate<Integer>() {
					public boolean test(Integer t) {
						if (t%2==0)
							return true;
						return false;
					}
				})
				.mapToInt(new ToIntFunction<Integer>() {
					public int applyAsInt(Integer value) {
						return value*2;
					}
				})
				.sum());

		//given the values, double the even numbers and total.
		System.out.println(
				numbers.stream()
				.filter(e -> e % 2 == 0)
				.mapToInt(e -> e * 2)
				.sum());
	}
}
