/*
Given a number k, and a count n, find the total of double of n even numbers starting with k, where sqrt of each number is > 20
 */

package _099_Streams;

import java.util.stream.Stream;

public class _023_Infinite_Stream {
	public static int compute1(int k, int n) {
		int result = 0;

		int index = k;
		int count = 0;
		while(count < n) {
			if(index % 2 == 0 && Math.sqrt(index) > 20) {
				result += index * 2;
				count++;
			}      
			index++;
		}
		return result;
	}

	public static int compute2(int k, int n) {

		return Stream.iterate(k, e -> e + 1)         // unbounded, lazy. this line gives you infinite stream
				.filter(e -> e % 2 == 0)        	// unbounded, lazy
				.filter(e -> Math.sqrt(e) > 20) 	// unbounded, lazy
				.mapToInt(e -> e * 2)           	//unbounded, lazy
				.limit(n)                      		//sized, lazy
				.sum();
	}


	public static void main(String[] args) {
		int k = 121;
		int n = 51;
		System.out.println(compute1(k, n));
		System.out.println(compute2(k, n));
	}
}
