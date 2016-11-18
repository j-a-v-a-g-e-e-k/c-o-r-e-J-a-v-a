package _020_Streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

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
		//forEach takes a funtional interface- Consumer
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
	}
}
