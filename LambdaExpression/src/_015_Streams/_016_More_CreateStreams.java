/*
New methods have been added to Java libraries to return a stream.
We can create stream in the following ways.

Create Streams from values
Create Streams from functions
Create Streams from Empty streams
Create Streams from arrays
Create Streams from collections
Create Streams from files
Create Streams from other sources
 */

package _015_Streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class _016_More_CreateStreams {
	public static void main(String[] args){
		/*
		Streams from Arrays
		java.util.Arrays class contains stream() method to create sequential streams from arrays.
		We can use it to create an IntStream, a LongStream, a DoubleStream, and a Stream<T>.
		 */
		IntStream s1= Arrays.stream(new int[]{1,2,3});
		Stream<Integer> s2 = Arrays.stream(new Integer[]{1,2,3});
		//IntStream s3= Arrays.stream(new Integer[]{1,2,3}); complier error because IntStream is for primitives		
		Stream<String> s4 = Arrays.stream(new String[]{"bimal","meghna"});

		/*
		Streams from Collections
		The Collection interface contains the stream() and parallelStream() methods that create sequential and parallel streams from a Collection.		
		 */
		Set<String> set = new HashSet<>(); 
		set.add("XML");	set.add("Java");
		Stream<String> sequentialStream  = set.stream();
		Stream<String> parallelStream = set.parallelStream();
	
		List<String> list = new ArrayList<String>();
		list.add("bimal"); list.add("meghna");
		Stream<String> listStream = list.stream();
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "bimal"); map.put(2, "meghna");
		//map.stream() //there is no such method for map
		
		/*
		 Streams from Char Sequence
		 chars() from the CharSequence interface returns an IntStream whose elements are int values representing the characters.
		 We can use chars() method on a String, a StringBuilder, and a StringBuffer.
		 */		
		String str = "5 123,123,qwe,1,123, 25";
		IntStream s11 = str.chars(); //chars returns an IntStream of char values from this sequence
		s11.filter(n ->  !Character.isDigit((char)n) &&   !Character.isWhitespace((char)n))
		.forEach(n ->  System.out.print((char)n));

		/*
		 Streams from Regex
		 splitAsStream(CharSequence input) method from the java.util.regex.Pattern class returns a stream of String whose elements match the pattern.
		 */
		str = "XML,CSS,HTML"; 
		Pattern.compile(",").splitAsStream(str).forEach(System.out::println);
		
		/*
		Java Stream From Files
		java.io and java.nio.file packages from Java 8 has added many methods to support I/O operations using streams.
		We can read text from a file as a stream of strings. Each element in the stream represents one line of text.
		We can also use a stream to read JarEntry from a JarFile and we can read entries in a directory as a stream of Path.

		Auto close
		Calling the close() method on the stream will close the underlying file.
		Alternatively, we can create the stream in a try-with-resources statement so the underlying file is closed automatically.

		 */		
		Path path = Paths.get("C:\\Users\\Bimal\\Documents\\GitHub\\CoreJava\\LambdaExpression\\src\\_020_Create_Stream\\_016_More_CreateStreams.java");
		try (Stream<String> lines = Files.lines(path)) {
			lines.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//how to read a path using stream
		Path dir = Paths.get(".");
	    System.out.printf("%nThe file tree for %s%n", 
	        dir.toAbsolutePath());
	    try (Stream<Path> fileTree = Files.walk(dir)) {
	      fileTree.forEach(System.out::println);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    
	    /*
	    Random Stream
	    java.util.Random class provides ints(), longs(), and doubles() return infinite IntStream, LongStream, and DoubleStream, respectively.
	    */
	    IntStream s21 = new Random().ints();  
		s21.limit(5)
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
