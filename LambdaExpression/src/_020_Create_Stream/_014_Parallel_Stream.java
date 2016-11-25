package _020_Create_Stream;

import java.util.Arrays;
import java.util.List;

public class _014_Parallel_Stream {

	public static void timeIt(Runnable block){
		long start = System.nanoTime();
		block.run();
		long end = System.nanoTime();
		System.out.println("Time taken: " + (end-start)/1.0e9);		
	}

	public static void main(String[] args){
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

		_014_Parallel_Stream.timeIt(() ->
		System.out.println(
				numbers.stream()
				.filter(e -> e%2==0)
				.mapToInt(e -> { 
					try{ Thread.sleep(1000); } catch(Exception ex) {}
					return e*2;})
				.sum()));


		_014_Parallel_Stream.timeIt(() ->
		{
			numbers.parallelStream()
			.filter(e -> e%2==0)
			.mapToDouble(e -> { 
				try{ Thread.sleep(1000); } catch(Exception ex) {}
				return e*2.0;})
			.sum();});
	}

}
