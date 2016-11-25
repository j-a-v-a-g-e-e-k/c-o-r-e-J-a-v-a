// How to get rid of loops in java

package _020_AdvanceStreams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import _099_helper.TimeUtil;

public class _011_Lamdba {
	public static void main(String[] args){
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);		

		//1. external iterator
		for (int i=0; i<numbers.size(); i++)
			System.out.print(numbers.get(i));
		System.out.println();

		//2. external iterator
		for (int i:numbers)
			System.out.print(i);
		System.out.println();

		//3. internal iterator
		//forEach takes a functional interface- Consumer
		numbers.forEach(new Consumer<Integer>() {
			public void accept(Integer t) {
				System.out.print(t);
			}			
		});
		System.out.println();

		//4. internal iterator using lambda
		numbers.forEach((Integer t) -> System.out.print(t));
		System.out.println();

		//5. lambda expressions has type inference
		numbers.forEach((t) -> System.out.print(t));
		System.out.println();

		//6. parenthesis is optional, but only for one parameter lambdas.
		numbers.forEach(t -> System.out.print(t));
		System.out.println();

		//7. method reference
		numbers.forEach(System.out::println);

		//given the values, double the even numbers and total.		    
		int result = 0;		    
		for(int e : numbers) {
			if(e % 2 == 0) {
				result += e * 2;
			}
		}		    
		System.out.println(result);

		//given the values, double the even numbers and total.
		System.out.println(
				numbers.stream()
				.filter(new Predicate<Integer>() {
					public boolean test(Integer t) {
						if (t%2==0)
							return true;
						return false;
					}
				})
				.mapToInt(new ToIntFunction<Integer>() {
					public int applyAsInt(Integer value) {
						return value*2;
					}
				})
				.sum());

		//given the values, double the even numbers and total.
		TimeUtil.timeIt(()->{
		System.out.println(
				numbers.stream()
				.filter(e -> e % 2 == 0)
				.mapToInt(e -> e * 2)
				.sum());});
		
		/* Another important feature is to process loop in parallel:
		The external iteration typically means sequential code. The sequential code can be executed only by one thread.
		Streams are designed to process elements in parallel.
		The following code computes the same in parallel.
		*/
		TimeUtil.timeIt(()->{
		System.out.println(
				numbers.parallelStream()
				.filter(e -> e % 2 == 0)
				.mapToInt(e -> e * 2)
				.sum());});
		
	}
}
