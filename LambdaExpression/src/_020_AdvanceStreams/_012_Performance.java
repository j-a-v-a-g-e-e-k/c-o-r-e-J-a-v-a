/*
how much work? 8 units of work in both cases
But Streams are slower 
 */
package _020_AdvanceStreams;

import java.util.Arrays;
import java.util.List;

import _099_helper.TimeUtil;

public class _012_Performance {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 5, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

		//given an ordered list find the double of the first even number greater than 3.

		TimeUtil.timeIt(() -> {
			int result = 0;
			for(int e : numbers) {
				if(e > 3 && e % 2 == 0) {
					result = e * 2;
					break;
				}
			}
			System.out.println(result);});
		
		TimeUtil.timeIt(() -> {
			System.out.println(
					numbers.stream()
					.filter(_012_Performance::isGT3)
					.filter(_012_Performance::isEven)
					.map(_012_Performance::doubleIt)
					.findFirst()
					);});
	}
	public static boolean isGT3(int number) {
		return number > 3;
	}
	public static boolean isEven(int number) {
		return number % 2 == 0;
	}
	public static int doubleIt(int number) {
		return number * 2;
	}
}
