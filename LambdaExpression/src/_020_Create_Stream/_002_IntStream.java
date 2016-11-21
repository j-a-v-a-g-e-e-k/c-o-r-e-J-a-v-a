package _020_Create_Stream;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class _002_IntStream {
	public static void main(String[] args){
		/*
		IntStream from range
		We can use the following two methods from IntStream interfaces to create IntStream from a range of int values.
		IntStream range(int start, int end)
		IntStream rangeClosed(int start, int end).
		
		They create an IntStream that contains ordered integers between the start and end.
		*/
		IntStream s6 = IntStream.range(1,5); //1-inclusive, 5-exclusive
		s6.forEach(System.out::println);

		IntStream s7 = IntStream.rangeClosed(6,10); //both-inclusive
		s7.forEach(System.out::println);
		
		LongStream s8 = LongStream.range(1,5); //1-inclusive, 5-exclusive
		s8.forEach(System.out::println);

		LongStream s9 = LongStream.rangeClosed(6,10); //both-inclusive
		s9.forEach(System.out::println);		
		
		//Empty Streams: An empty stream has no elements.
		Stream<String> s10 = Stream.empty();
		s10.forEach(System.out::println);
		
		IntStream s11 = IntStream.empty();
		LongStream s12 = LongStream.empty();
		DoubleStream s13 = DoubleStream.empty();
	}
	

}
