package _015_Streams;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import _099_helper.Employee;

public class _015_Collect {
	public static void main(String[] args){
		/*
		To group data in a Stream we can use collect() method of the Stream<T> interface.
		only 1 version is available for IntStream
		It takes supplier, accumulator and combiner. 
		A supplier that supplies a mutable container to store the results.
		An accumulator that accumulates the results into the mutable container.
		A combiner that combines the partial results when used in parallel. Combiner comes into play only in case of parallel pipelines.
		*/
		//DoubleStream collect(Supplier<R> supplier, ObjDoubleConsumer<R> accumulator, BiConsumer<R,R> combiner) performs a mutable reduction 
		//operation on the elements of this stream.
		IntStream s1 = IntStream.of(1,2,3,4,5);
		List<Integer> listInt = s1.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		System.out.println(listInt);
		
		//below shows that combiner is NOT in use, in case of non-parallel pipeline
		System.out.println(
		Employee.create().stream().map(Employee::getIncome).collect(
				()->new ArrayList<Double>(),
				(ArrayList<Double> list, Double d1)->{System.out.println("Accumulator: " + d1);list.add(d1);}, 
				(ArrayList<Double> finalList, ArrayList<Double> partialList)->{System.out.println("Combiner: " + partialList.toString());
				finalList.addAll(partialList);}
				));
		//below shows that combiner is in use, in case of parallel pipeline
		System.out.println(
		Employee.create().stream().parallel().map(Employee::getIncome).collect(
				()->new ArrayList<Double>(),
				(ArrayList<Double> list, Double d1)->{System.out.println("Accumulator: " + d1);list.add(d1);}, 
				(ArrayList<Double> finalList, ArrayList<Double> partialList)->{System.out.println("Combiner: " + partialList.toString());
				finalList.addAll(partialList);}
				));
		
		//2 versions are available for Stream
		System.out.println(Employee.create().stream().collect(Collectors.toList()));
		System.out.println(Employee.create().stream().map(Employee::getIncome).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
		System.out.println(Employee.create().stream().map(Employee::getIncome).collect(StringBuilder::new, StringBuilder::append, 
				StringBuilder::append));
		System.out.println(Employee.create().stream().map(Employee::getIncome).collect(DoubleSummaryStatistics::new, 
                DoubleSummaryStatistics::accept, DoubleSummaryStatistics::combine));
		
	}
}
