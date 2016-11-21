/*
New methods have been added to Java libraries to return a stream.
We can create stream in the following ways.

Create Streams from values
Create Streams from Empty streams
Create Streams from functions
Create Streams from arrays
Create Streams from collections
Create Streams from files
Create Streams from other sources

Create Streams from Values: We can use of() from Stream interface to create a sequential Stream from a single value and multiple values.
<T> Stream<T> of(T  t)
<T> Stream<T> of(T...values)

 */

package _020_Create_Stream;

import java.util.stream.Stream;

public class _001_Values {
	public static void main(String[] args){
		//creates a stream which contains a single value
		Stream<String> s1 = Stream.of("Bimal Jain");
		s1.forEach(System.out::println);
		
		//creates a stream with three strings
		Stream<String> s2= Stream.of("bimal","bharat","meghna");
		s2.forEach(System.out::println);
		
		//creates a stream from an array of objects
		String[] s3={"bimal-array","bharat-array","meghna-array"};
		Stream<String> s4 = Stream.of(s3);
		s4.forEach(System.out::println);
		
		//We can use Stream.Builder<T> to create streams.
		Stream<String> s5 = Stream.<String>builder()
							.add("bimal-builder")
							.add("bharat-builder")
							.build();
		s5.forEach(System.out::println);			
	}
}
