package _099_Streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class _015_Reduce {
	public static void main(String[] args){
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		/*
If you want to perform more complex reduction operations, however, you must use the reduce method. Unlike the map and filter methods, the reduce 
method expects two arguments: an identity element, and a lambda expression. You can think of the identity element as an element which does not 
alter the result of the reduction operation. For example, if you are trying to find the product of all the elements in a stream of numbers, the 
identity element would be the number 1.

The lambda expression you pass to the reduce method must be capable of handling two inputs: a partial result of the reduction operation, and the 
current element of the stream. If you are wondering what a partial result is, it’s the result obtained after processing all the elements of the 
stream that came before the current element.
		 */
		System.out.println(
				numbers.stream()
				.reduce(0, new BinaryOperator<Integer>() {
					public Integer apply(Integer total, Integer e) {
						return total+e;
					}
				}));

		System.out.println("Sum: " +
				numbers.stream()
				.reduce(0, (total, e) -> Integer.sum(total,e)));
		
		System.out.println(
				numbers.stream()
				.reduce(0, (total, e) -> total+e));

		System.out.println("Product: " + 
				numbers.stream()
				.filter(e -> e<5)
				.reduce(1, (total, e) -> total*e));
		
		System.out.println(
				numbers.stream()
				.reduce(0, Integer::sum));

		System.out.println(
				numbers.stream()
				.reduce(Integer::sum));

		System.out.println(
				numbers.stream()
				.map(String::valueOf)
				.reduce("", (carry, str) -> carry.concat(str)));

		System.out.println(
				numbers.stream()
				.map(String::valueOf)
				.reduce("", String::concat));

		System.out.println(
				numbers.stream()
				.map(String::valueOf)
				.reduce(String::concat));
	}
}

/*
 filter: 0 <= number of elements in the output <= number of input
 parameter: Stream<T> filter takes Predicate<T>
    
 map transforms values
 number of output == number of input
 no guarantee on the type of the output with respect to the type of the input
 parameter:  Stream<T> map takes Function<T, R> to return Stream<R>
   
 both filter and map stay within their swimlanes
 
 reduce cuts across the swimlanes
 
 reduce on Stream<T> takes two parameters:
 first parameter is of type T
 second parameter is of type BiFunction<R, T, R> to produce a result of R
 
        filter      map           reduce
                                  0.0
                                   \
 x1       |                         \
---------------------------          \
 x2       ->          x2'     ->      +
---------------------------            \
 x3       |                             \
---------------------------              \
 x4       ->          x4'      ->         +
---------------------------                \
                                            \



      1            2             3           4
      |           |              |           |
1 -> *  -> 1 ->   *  -> 2   ->   *  -> 6  -> * -> 24   
 */
