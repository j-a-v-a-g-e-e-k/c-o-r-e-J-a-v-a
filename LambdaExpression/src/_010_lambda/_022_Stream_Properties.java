package _010_lambda;

import java.util.Arrays;
import java.util.List;

public class _022_Stream_Properties {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5);

		numbers.stream()
		.filter(e -> e % 2 == 0)
		.forEach(System.out::println);
		/*this stream has below proeprties:
		    sized- since source is sized
		    ordered- since source is ordered 
		    non-distinct-since source is non-distinct
		    non-sorted- since source is non-sorted

		    note that we did not change any of the above properties in the stream
		 */

		numbers.stream()
		.filter(e -> e % 2 == 0)
		.sorted()
		.forEach(System.out::println);
		//sized, ordered, non-distinct, sorted

		numbers.stream()
		.filter(e -> e % 2 == 0)
		.distinct()
		.forEach(System.out::println);
		//sized, ordered, distinct, non-sorted
	}
}
