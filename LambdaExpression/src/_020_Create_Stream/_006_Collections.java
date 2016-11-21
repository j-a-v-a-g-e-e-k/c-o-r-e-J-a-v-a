/*
Streams from Collections
The Collection interface contains the stream() and parallelStream() methods that create sequential and parallel streams from a Collection, respectively.
 */

package _020_Create_Stream;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class _006_Collections {
	public static void main(String[] args) {
		Set<String> names = new HashSet<>(); 
		names.add("XML");
		names.add("Java");

		Stream<String> sequentialStream  = names.stream();
		sequentialStream.forEach(System.out::println);

		Stream<String> parallelStream = names.parallelStream();
		parallelStream.forEach(System.out::println);
	}
}
