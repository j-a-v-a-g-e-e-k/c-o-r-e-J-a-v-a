/*
Both map and flatMap can be applied to a Stream<T> and they both return a Stream<R>. The difference is that the map operation produces one output 
value for each input value, whereas the flatMap operation produces an arbitrary number (zero or more) values for each input value.

The flatMap() operation has the effect of applying a one-to-many transformation to the elements of the stream, and then flattening the resulting 
elements into a new stream. 

Consider a list [“STACK”,”OOOVVVER”] and we are trying to return a list like [“STACKOVER”](returning only unique letters from that list)
 */

package _020_Create_Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _013_MapVsFlatMap {
	public static void main(String[] args){
		//Example-1
		//does not work
		List<String> list = Arrays.asList("STACK","OOOVVVER");
		System.out.println(list);
		Stream<String> z1 =list.stream();
		Stream<String[]> z2 = z1.map(s->s.split(""));
		List<String[]> z3=z2.distinct().collect(Collectors.toList());

		//does not work too
		String[] x1 = {"STACK","OOOVVVER"};
		Stream<String> y1=Arrays.stream(x1);
		Stream<String[]> y2 = y1.map(s->s.split("")); //convert word into array of letters
		Stream<Stream<String>> y3= y2.map(Arrays::stream); //make array into separate stream
		List<Stream<String>> y4 =y3.distinct().collect(Collectors.toList());

		//works
		Stream<String> x4=Arrays.stream(x1);
		Stream<String[]> x5= x4.map(s->s.split(""));
		Stream<String> x6=x5.flatMap(Arrays::stream); //flattens each generated stream into single stream
		List<String> x7= x6.distinct().collect(Collectors.toList());
		System.out.println(x7);

		//Example-2
		//Nothing Special into this example, a Function is applied to return the String in uppercase.
		List<String> collected = Stream.of("a", "b", "hello") // Stream of String 
				.map(String::toUpperCase) // Returns a stream consisting of the results of applying the given function to the elements of this stream.
				.collect(Collectors.toList());

		//a Stream of List is passed. It is NOT a Stream of Integer! 
		//If a tranformation Function has to be used (through map), then first the Stream has to be flattened to something else (a Stream of Integer). 
		//If flatMap is removed then the following error is returned: The operator + is undefined for the argument type(s) List, int. 
		//It is NOT possible to apply + 1 on a List of Integers!
		List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)) // Stream of List<Integer>
				.flatMap(List::stream)
				.map(integer -> integer + 1)
				.collect(Collectors.toList());
	}
}
