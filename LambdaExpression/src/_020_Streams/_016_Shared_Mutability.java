package _020_Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _016_Shared_Mutability {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5);

		//double the even values and put that into a list.

		//wrong way to do this.
		List<Integer> doubleOfEven = new ArrayList<>();

		numbers.parallelStream()
		.filter(e -> e % 2 == 0)
		.map(e -> e * 2)
		.forEach(e -> doubleOfEven.add(e));
		//mutability is OK, sharing is nice, but shared mutability is devil's work

		System.out.println(doubleOfEven); //Don't do this.

		List<Integer> doubleOfEven2 =
				numbers.parallelStream()
				.filter(e -> e % 2 == 0)
				.map(e -> e * 2)
				.collect(Collectors.toList());
		System.out.println(doubleOfEven2);
	}
}

/*
 One possible output:
 [null, 8, 4, 8]
 [4, 8, 4, 8]
 */
