/*
 Random Stream
 java.util.Random class provides ints(), longs(), and doubles() return infinite IntStream, LongStream, and DoubleStream, respectively.
 */
package _020_Create_Stream;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class _004_RandomStream {
	public static void main(String[] args){
		IntStream s2 = new Random().ints();  
		s2.limit(5)
		.forEach(System.out::println);

		//We can use the nextInt() method of the Random class as the Supplier in the generate() method to achieve the same.
	    Stream<Integer> s3= Stream.generate(new Random()::nextInt);
	    s3.limit(5)
	    .forEach(System.out::println);
	    
	    //To work with only primitive values, use the generate() method of the primitive type stream interfaces.
	    IntStream s41= IntStream.generate(new Random()::nextInt);
	    s41.limit(5)
	    .forEach(System.out::println);
	    
	    //To generate an infinite stream of a repeating value.
	    IntStream.generate(() ->  0)
	    .limit(5)
	    .forEach(System.out::println);
	}

}
