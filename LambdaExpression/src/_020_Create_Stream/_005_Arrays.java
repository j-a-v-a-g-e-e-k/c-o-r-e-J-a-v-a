/*
Streams from Arrays
java.util.Arrays class contains stream() method to create sequential streams from arrays.

We can use it to create an IntStream, a LongStream, a DoubleStream, and a Stream<T>.
 */
package _020_Create_Stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class _005_Arrays {
	public static void main(String[] args){
		IntStream s1= Arrays.stream(new int[]{1,2,3});
		Stream<Integer> s2 = Arrays.stream(new Integer[]{1,2,3});
		//IntStream s3= Arrays.stream(new Integer[]{1,2,3}); complier error
		
		Stream<String> s4 = Arrays.stream(new String[]{"bimal","meghna"});
	}
}
