/*
The reduce() operation combines all elements in a stream to produce a single value.

The reduce operation takes two parameters called a seed (initial value) and an accumulator.

The accumulator is a function. If the stream is empty, the seed is the result.

The seed and an element are passed to the accumulator, which returns partial result. And then the partial result and the next element are passed 
to the accumulator function. This repeats until all elements are passed to the accumulator. The last value returned from the accumulator is the 
result of the reduce operation.

The stream-related interfaces contain two methods called reduce() and collect() to perform generic reduce operations.

The IntStream<T> interface contains a reduce() method to perform the reduce operation. The method has two overloaded versions:
OptionalInt reduce(IntBinaryOperator accumulator)
int reduce(int identity, IntBinaryOperator accumulator)

The Stream<T> interface contains a reduce() method to perform the reduce operation. The method has three overloaded versions:
Optional<T> reduce(BinaryOperator<T> accumulator)
T  reduce(T identity, BinaryOperator<T> accumulator)
<U> U reduce(U identity, BiFunction<U,? super  T,U> accumulator, BinaryOperator<U> combiner)
 */

package _015_Streams;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import _099_helper.Employee;
import _099_helper.Employee.Gender;

public class _014_Reduce {
	public static void main(String[] args){

		IntStream s1 = IntStream.of(1,2,3,4,5);
		System.out.println(s1.reduce((partialCount,nextElement)->partialCount+nextElement));

		s1 = IntStream.of(1,2,3,4,5);
		System.out.println(s1.reduce(0, (partialCount,nextElement)->partialCount+nextElement));

		s1 = IntStream.of(1,2,3,4,5);
		System.out.println(s1.reduce(0, Integer::sum));

		System.out.println(
		Employee.create().stream().reduce((Employee obj1, Employee obj2) -> obj1.getIncome()>obj2.getIncome() ? obj1:obj2));

		System.out.println(
		Employee.create().stream()
		.reduce(new Employee(0,"",Gender.MALE, 0.0), (Employee obj1, Employee obj2) -> obj1.getIncome()>obj2.getIncome() ? obj1:obj2));

		System.out.println(
		Employee.create().stream().map(Employee::getIncome).reduce((Double partialCount, Double nextIncome)-> partialCount + nextIncome));

		System.out.println(
		Employee.create().stream().map(Employee::getIncome).reduce(0.0, (Double partialCount, Double nextIncome)-> partialCount + nextIncome));

		System.out.println(
		Employee.create().stream().map(Employee::getIncome).reduce(0.0, Double::sum));		

		//Streams Reduce without default value
		Optional<Integer> max2 = Stream.of(1, 2, 3, 4, 5).reduce(Integer::max);
		if (max2.isPresent()) {System.out.println("max = " + max2.get()); }
		else {System.out.println("max is not  defined.");	}

		max2 = Stream.<Integer> empty().reduce(Integer::max);
		if (max2.isPresent()) { System.out.println("max = " + max2.get()); } 
		else {System.out.println("max is not  defined."); }

		//since you are sending 2 different kind of inputs to accumulator, so you have to specify combiner.
		//however note that combiner is no being used here at all. The combiner is used for combining the partial results when the reduce operation 
		//is performed in parallel.
		System.out.println(Employee.create().stream()
				.reduce(0.0, (Double partialCount, Employee nextEmp)-> partialCount + nextEmp.getIncome(), Double::sum));

		//in this example, combiner is not used at all
		System.out.println(
		Employee.create().stream()
		.reduce(0.0,(Double partialCount, Employee nextEmp)-> {
		double accumulated = partialCount + nextEmp.getIncome();
		System.out.println(Thread.currentThread().getName() + "-Accumulator : partialCount=" + partialCount + " ,Accumulated=" + accumulated);
		return accumulated;	},
		(Double aa, Double b)->{
		double combined=aa+b;
		System.out.println(Thread.currentThread().getName() + "-Combiner : combined=" + combined);
		return combined;}
		));

		//Java Stream Map Reduce in parallel
		//When using the following reduce method, each thread accumulates the partial results using the accumulator. At the end, the combiner 
		//is used to combine the partial results from all threads to get the result.
		System.out.println(
		Employee.create().parallelStream()
		.reduce(0.0,(Double partialCount, Employee nextEmp)-> {
		double accumulated = partialCount + nextEmp.getIncome();
		System.out.println(Thread.currentThread().getName() + "-Accumulator : partialCount=" + partialCount + " ,Accumulated=" + accumulated);
		return accumulated;	},
		(Double aa, Double b)->{
		double combined=aa+b;
		System.out.println(Thread.currentThread().getName() + "-Combiner : combined=" + combined);
		return combined;}
		));
	}
}
