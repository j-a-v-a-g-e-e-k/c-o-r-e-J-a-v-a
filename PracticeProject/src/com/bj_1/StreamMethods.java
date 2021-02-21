/* 
 * filter: Returns a stream consisting of the elements of this stream that match the given predicate.
 * map: Returns a stream consisting of the results of applying the given function (Take something, give something) to the elements of this stream. 
 * reduce: The reduce operation takes two parameters called a seed (initial value) and an accumulator (and an optional combiner).
 * 
 * max: Returns the maximum element of this stream according to the provided Comparator. This is a special case of a reduction.
 * min: Returns the minimum element of this stream according to the provided Comparator. This is a special case of a reduction.
 * allMatch, noneMatch, anyMatch
 * findAny, findFirst
 * 
 * collect(supplier, accumulator, combiner)
 * Collectors.toMap(key, value, mergeFunction)
 * Collectors.mapping: think of it like (an extended) map() method of Streams. map() method transforms the object whereas mapping() does transformation 
 * 							as well as collect the object.
 * Collectors.reducing: similar to reduce() method in Streams Collectors reducing(BinaryOperator<T> op) returns a Collector to reduce its input elements 
 * 							under a specified BinaryOperator.
 * Collectors.groupingBy: The groupingBy() method from the Collectors class returns a collector that groups the data before collecting them in a Map.
 *		 					It is similar to "group by" clause in SQL. classifier function generates the keys in the map. collector performs a reduction operation 
 *		 					on the values associated with each key. The third version allows us to specify a Supplier used as the factory to get the Map object.
 * Collectors.partitioningBy: Partitioning is a special case of grouping.
							Grouping data is based on the keys returned from a function. There could be many groups.
							Partitioning only takes care of two groups based on a predicate. The value evaluated to true is one group, false is another group.
							partitioningBy() method, which collects data in a Map whose keys are always of the type Boolean.		 					
 */

package com.bj_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bj_1.Employee.GENDER;

public class StreamMethods {	
	public static void main(String[] args) {
		Employee emp = new Employee();
		List<Employee> e = emp.create();
		
		System.out.println("==============================================================");
		System.out.println("====================INTERMEDIATE OPERATION====================");
		System.out.println("==============================================================");
		System.out.println("==========map=========");
		e.stream().map(Employee::getName).forEach(System.out::println);
		e.stream().map(x->x.getIncome()).sorted().forEach(System.out::println);
		
		System.out.println("==========filter=========");
		e.stream().filter(x->x.getSex().equals(GENDER.MALE)).forEach(System.out::println);
		e.stream().filter(Employee::isMale).forEach(System.out::println);
		
		System.out.println("==========limit=========");
		/* Returns a stream consisting of the elements of this stream, truncated to be no longer than maxSize in length. 
		 */
		e.stream().limit(1).forEach(System.out::println);
		
		System.out.println("==========distinct=========");
		/*Returns a stream consisting of the distinct elements (according to Object.equals(Object)) of this stream. 
		 */
		e.stream().map(Employee::getIncome).distinct().forEach(System.out::println);
		
		System.out.println("===========================================================");
		System.out.println("====================TERMINAL OPERATION=====================");
		System.out.println("===========================================================");

		NameComparator nameComparator = new NameComparator();
		e.stream().max(nameComparator).ifPresent(System.out::println);
		e.stream().map(x->x.getName()).max((String s1, String s2) -> s1.compareTo(s2)).ifPresent(System.out::println);
	
		IncomeComparator incomeComparator = new IncomeComparator();
		e.stream().max(incomeComparator).ifPresent(System.out::println);
		e.stream().map(x->x.getIncome()).max((Double d1, Double d2) -> d1.compareTo(d2)).ifPresent(System.out::println);
		e.stream().map(Employee::getIncome).max((Double d1, Double d2) -> d1.compareTo(d2)).ifPresent(System.out::println);
		System.out.println(e.stream().mapToDouble(Employee::getIncome).max().getAsDouble());
		
		System.out.println(e.stream().allMatch(Employee::isMale));
		System.out.println(e.stream().noneMatch(Employee::isMale));
		System.out.println(e.stream().anyMatch(Employee::isMale));
		
		e.stream().filter(Employee::isMale).findAny().ifPresent(System.out::println);
		e.stream().filter(x->x.getIncome()>3000).findFirst().ifPresent(System.out::println);
		
		System.out.println("===========================================================");
		System.out.println("====================map vs flatMap=========================");
		System.out.println("===========================================================");
		
		String[] x = new String[] {"STACK","OOOVER"};
		Stream<String> y = Arrays.stream(x);
		Stream<String[]> z = y.map(s->s.split(""));
		Stream<Stream<String>> z1 = z.map(Arrays::stream);
		Stream<String> z2 = Arrays.stream(x).map(s->s.split("")).flatMap(Arrays::stream);
		z2.distinct().forEach(System.out::println);
		
		String[] a = new String[] {"STACK","OOOVER"};
		String[] b = new String[] {"OOOVER","STACK"};
		Stream<String[]> c = Stream.of(a,b);
		//Stream<Stream<String>> d = c.map(Arrays::stream);
		Stream<String> d = c.flatMap(Arrays::stream);
		Stream<String[]> g = d.map(f->f.split(""));
//		Stream<Stream<String>> h = g.map(Arrays::stream);
		Stream<String> j = g.flatMap(Arrays::stream);
		j.distinct().forEach(System.out::println);
		
		System.out.println("===========================================================");
		System.out.println("============================Reduce=========================");
		System.out.println("===========================================================");
		
		List<Integer> l1 = new ArrayList<>();
		l1.add(1);l1.add(2);l1.add(3);l1.add(4);l1.add(5);
		l1.stream().reduce((sum, nextValue)-> sum+nextValue).ifPresent(System.out::println);
		System.out.println(l1.stream().reduce(0, (sum, nextValue)-> sum*nextValue));
		System.out.println(l1.stream().reduce(1, (sum, nextValue)-> sum*nextValue));
		e.stream().reduce((Employee e1, Employee e2) -> e1.getIncome()>e2.getIncome() ? e1:e2).ifPresent(System.out::println);
		
		System.out.println(Employee.create().stream()
		.reduce(0.0, (Double partialSum, Employee nextEmployee) -> partialSum + nextEmployee.getIncome(), (Double aa, Double bb) -> aa+bb));
		
		System.out.println(
		Employee.create().stream()
		.reduce(0.0, (Double d1,Employee e1)->{
			Double accumulator = d1+e1.getIncome();
			System.out.println(Thread.currentThread() + "-Accumulator" + accumulator);
			return accumulator;			
		}, 
			(Double d2, Double d3)->{
			Double combiner = d2+d3;
			System.out.println(Thread.currentThread() + "-Combiner" + combiner);
			return combiner;
		}));
		
		System.out.println(
		Employee.create().parallelStream()
		.reduce(0.0, (Double d1,Employee e1)->{
			Double accumulator = d1+e1.getIncome();
			System.out.println(Thread.currentThread() + "-Accumulator" + accumulator);
			return accumulator;			
		}, 
			(Double d2, Double d3)->{
			Double combiner = d2+d3;
			System.out.println(Thread.currentThread() + "-Combiner" + combiner);
			return combiner;
		}));
		
		System.out.println("===========================================================");
		System.out.println("============================Collect========================");
		System.out.println("===========================================================");
		
		System.out.println(
		Employee.create().stream()
		.map(Employee::getIncome)
		.collect(ArrayList::new, ArrayList::add, ArrayList::addAll)
		);
		
		System.out.println(
		Employee.create().stream()
		.map(Employee::getIncome)
		.collect(ArrayList::new, 
				(ArrayList list, Double d4)-> {					
					list.add(-d4);
					System.out.println("Accumulator: " + d4 +" " +list);
				}
				, (ArrayList list1, ArrayList list2)->{					
					list1.addAll(list2);
					System.out.println("Combiner: " + list1+" "+ list2);
				})
		);
		
		Employee.create().stream()
		.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
		
		System.out.println("===========================================================");
		System.out.println("============================Collector Methods==============");
		System.out.println("===========================================================");
		
		System.out.println(Employee.create().stream().collect(Collectors.toList()));
		System.out.println(Employee.create().stream()
				.collect(Collectors.toMap(Employee::getName, Employee::getIncome)));		
		System.out.println(Employee.create().stream()
				.collect(Collectors.toMap(Employee::getSex, Employee::getName, (oldValue, newValue) -> String.join(",", oldValue,newValue))));		
		System.out.println(Employee.create().stream()
				.collect(Collectors.toMap(Employee::getSex, e2->1, (oldValue, newValue) -> oldValue+newValue)));
		System.out.println(Employee.create().stream()
				.collect(Collectors.mapping(Employee::getName, Collectors.toList())));
		System.out.println(Employee.create().stream()
				.collect(Collectors.mapping(Employee::getSex, Collectors.toSet())));
		System.out.println(Employee.create().stream()
				.collect(Collectors.mapping(Employee::getSex, Collectors.counting())));
		
		System.out.println("===========================================================");
		System.out.println("============================Reducing=======================");
		System.out.println("===========================================================");
		
		System.out.println(Employee.create().stream()
				.collect(Collectors.groupingBy(Employee::getSex)));
		System.out.println(Employee.create().stream()
				.collect(Collectors.groupingBy(Employee::getSex, Collectors.reducing(0.0, Employee::getIncome, Double::sum))));
		System.out.println(Employee.create().stream()
				.collect(Collectors.groupingBy(Employee::getSex, Collectors.mapping(Employee::getName, Collectors.joining(",")))));
		System.out.println(Employee.create().stream()
				.collect(Collectors.groupingBy(Employee::getSex, Collectors.toList())));
		
		System.out.println("===========================================================");
		System.out.println("============================Partitioning=======================");
		System.out.println("===========================================================");
		
		System.out.println(Employee.create().stream()
				.collect(Collectors.partitioningBy(Employee::isMale)));
		System.out.println(Employee.create().stream()
				.collect(Collectors.partitioningBy(Employee::isMale, Collectors.mapping(Employee::getName, Collectors.toList()))));
		System.out.println(Employee.create().stream()
				.collect(Collectors.groupingBy(Employee::getSex, Collectors.summingDouble(Employee::getIncome))));
	}
}
