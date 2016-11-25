/*
Streams are lazy because intermediate operations are not evaluated unless terminal operation is invoked.

Each intermediate operation creates a new stream, stores the provided operation/function and return the new stream.

The pipeline accumulates these newly created streams.

The time when terminal operation is called, traversal of streams beings and the associated function is performed one by one.

Parallel streams don't evaluate streams 'one by one' (at terminal point). The operations are rather performed simultaneously, 
depending on the available cores.

What are the advantages of Laziness?
Lazy operations achieve efficiency. They are a way not to work on stale data. They might be useful in the situations where input data is consumed 
gradually rather than having whole complete set of elements beforehand. For example consider the situations where an infinite stream has been created 
using Stream#generate(Supplier<T>) and the provided Supplier function is gradually receiving data from a remote server. In those kind of the situations 
server call will only be made at a terminal operation when it's needed.

Also consider a stream on which we have already applied a number of the intermediate operations but haven't applied the terminal operation yet: 
we can pass around such stream within the application without actually performing any operation on the underlying data, the terminal operation may 
be called at very different part of the application or at very late in time.
 */

package _020_AdvanceStreams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class _020_Lazy_Evaluation {

	public static void main(String[] args){
		List<Integer> numbers = Arrays.asList(1, 2, 3, 5, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
		
		System.out.println("Creating Stream ..");
	      Stream<Integer> stream = numbers.stream()
	             .filter(_019_Performance::isGT3)
	             .filter(_019_Performance::isEven)
	             .map(_019_Performance::doubleIt);
	      
	    System.out.println("Invoking Stream ..");
	    System.out.println(stream.findFirst());

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

