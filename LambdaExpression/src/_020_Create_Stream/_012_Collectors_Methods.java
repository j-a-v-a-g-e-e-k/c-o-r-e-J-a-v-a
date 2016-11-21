/*
Collectors provides various useful reduction operations, such as accumulating elements into collections, summarizing elements according to various criteria, etc.

<R, A> R collect(Collector<? super T, A, R> collector)
When executed in parallel, multiple intermediate results may be instantiated, populated, and merged so as to maintain isolation of mutable 
data structures. Therefore, even when executed in parallel with non-thread-safe data structures (such as ArrayList), no additional synchronization 
is needed for a parallel reduction.

Type Parameters:
<R> the type of the result
<A> the intermediate accumulation type of the Collector
Parameters:
collector the Collector describing the reduction
Returns:
the result of the reduction

*/

package _020_Create_Stream;

import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import _098_helper.Employee;
import _098_helper.Employee.Gender;

public class _012_Collectors_Methods {
	public static void main(String[] args){
		Stream<String> s = Stream.of("1","2","3");
		
		System.out.println("====================Directly to Collections====================");
		//Collectors toCollection(Supplier<C> collectionFactory) returns a Collector that accumulates the input elements into a new 
		//Collection, in encounter order.
		TreeSet<String> treeSet = s.collect(Collectors.toCollection(TreeSet::new));
		System.out.println(treeSet);

		//Collectors toList() returns a Collector that accumulates the input elements into a new List.
		s = Stream.of("1","2","3");
		List<Employee> list = Employee.create().stream()
				.collect(Collectors.toList());
		System.out.println(list);

		//Collectors toSet() returns a Collector that accumulates the input elements into a new Set.
		s = Stream.of("1","2","3","2");
		Set<Employee> set = Employee.create().stream()
				.collect(Collectors.toSet());
		System.out.println(set.toString());

		/*
		We can collect data from a stream to a Map.
		The first argument maps the stream elements to keys in the map.
		The second argument maps stream elements to values in the map.
		If duplicate keys are found, an IllegalStateException is thrown.
		 */
		
		//Collectors toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper) returns a Collector that 
		//accumulates elements into a Map whose keys and values are the result of applying the provided mapping functions.
		Map<Integer,String> empStream = Employee.create().stream()
				.collect(Collectors.toMap(Employee::getId, Employee::getName));
		System.out.println(empStream);

		//Collectors toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction) 
		//returns a Collector that accumulates elements into a Map whose keys and values are from the mapping functions.

		//		Map<Gender,String> empStream2 = Employee.create()
		//		.stream()
		//		.collect(Collectors.toMap(Employee::getGender, Employee::getName));
		//		System.out.println(empStream2); //compiler error. when second male or female tries to enter the map, it will throw duplicate key error 

		/*
		The second form of toMap listed as follows has an extra merged function.
		mergeFunction is used to resolve collisions between values associated with the same key
		The merged function is passed the old and new values for the duplicate key. The function should merge the two values and return a new value that will be used for the key.
		*/
		Map<Gender,String> empStream3 = Employee.create().stream()
				.collect(Collectors.toMap(Employee::getGender, Employee::getName, (oldValue, newValue)  ->  String.join(", ", oldValue,  newValue)));
		System.out.println(empStream3);

		//Collectors toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper) returns a concurrent 
		//Collector that accumulates elements into a ConcurrentMap whose keys and values are the result from the mapping functions.
		Map<Integer,String> empStream4 = Employee.create().stream()
				.collect(Collectors.toConcurrentMap(Employee::getId, Employee::getName));
		System.out.println(empStream4);

		//Collectors toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction) 
		//returns a concurrent Collector that accumulates elements into a ConcurrentMap whose keys and values are from the mapping functions.
		Map<Gender,Integer> empStream5 = Employee.create().stream()
				.collect(Collectors.toConcurrentMap(Employee::getGender, e->1, (oldCount, newCount)  -> oldCount + newCount));
		System.out.println(empStream5);

		//Collectors toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, 
		//BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier) returns a concurrent Collector that accumulates elements into a ConcurrentMap 
		//whose keys and values are the result from the mapping functions.	
		//The third version listed as follows allows us to use a Supplier to provide a Map object.
		Employee.create().stream()
		.collect(Collectors.toConcurrentMap(Employee::getGender, e->1, (oldCount, newCount)  -> oldCount + newCount, 
				ConcurrentHashMap<Gender,Integer>::new)).forEach((x,y)->System.out.println(x +":"+ y));;
				
		//Collectors collectingAndThen(Collector<T,A,R> downstream, Function<R,RR> finisher) adapts a Collector to perform an additional finishing transformation.
		/*
		We can convert the result of the collector to a different type.
		The first argument is a collector that collects the data. The second argument is a finisher which converts the result.
		 */
		System.out.println(Employee.create().stream()
		.collect(Collectors.collectingAndThen(Collectors.toList(), result->Collections.unmodifiableList(result))));

		
		
		System.out.println("=================Joining================");
		/*
		The joining() method from Collectors class returns a collector that concatenates the stream of CharSequence and returns the result as a String.
		 */
		
		//Collectors joining() returns a Collector that concatenates the input elements into a String, in encounter order.
		s = Stream.of("1","2","3");
		String join = s.collect(Collectors.joining());
		System.out.println(join);

		//Collectors joining(CharSequence delimiter) returns a Collector that concatenates the input elements, separated by the specified delimiter, 
		//in encounter order.
		s = Stream.of("1","2","3");
		join = s.collect(Collectors.joining(", "));
		System.out.println(join);

		//Collectors joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix) returns a Collector that concatenates the elements, 
		//separated by the delimiter, with the specified prefix and suffix, in encounter order.
		s = Stream.of("1","2","3");
		join = s.collect(Collectors.joining(", ", "joining.. " , " ..done"));
		System.out.println(join);
		
		System.out.println(Employee.create().stream().map(Employee::getName).collect(Collectors.joining(", ")));
		
		
		
		System.out.println("=================Mapping(Reduce operation)(like SQL)================");
		//Collectors mapping(Function<? super T,? extends U> mapper, Collector<? super U,A,R> downstream) adapts a Collector accepting elements of 
		//type U to one accepting elements of type T by applying a mapping function.
		List<Employee.Gender> listMapping = Employee.create().stream()
		.collect(Collectors.mapping(Employee::getGender, Collectors.toList())); //(from Employee stream-->get Gender-->create list of genders)
		System.out.println(listMapping);
		
		Set<Employee.Gender> setMapping = Employee.create().stream()
		.collect(Collectors.mapping(Employee::getGender, Collectors.toSet()));
		System.out.println(setMapping);
		
		Long mapping2 = Employee.create().stream()
		.collect(Collectors.mapping(Employee::getGender, Collectors.counting()));
		System.out.println(mapping2);
		
		
		
		System.out.println("=================Grouping================");
		/*
		 The groupingBy() method from the Collectors class returns a collector that groups the data before collecting them in a Map.
		 It is similar to "group by" clause in SQL
		 classifier function generates the keys in the map. collector performs a reduction operation on the values associated with each key. The 
		 third version allows us to specify a Supplier used as the factory to get the Map object.
		 */
		
		//Collectors groupingBy(Function<? super T,? extends K> classifier) returns a Collector implementing a "group by" operation on input elements of type T.
		Map<Gender, List<Employee>> m1 =Employee.create().stream()
		.collect(Collectors.groupingBy(Employee::getGender));
		System.out.println(m1);
		
		//Collectors groupingBy(Function<? super T,? extends K> classifier, Collector<? super T,A,D> downstream) returns a Collector implementing a cascaded 
		//"group by" operation on input elements of type T.
		Map<Gender, String> m2= Employee.create().stream()
		.collect(Collectors.groupingBy(Employee::getGender, Collectors.mapping(Employee::getName, Collectors.joining(", "))));
		System.out.println(m2);
		
		Map<Gender, List<String>> m3= Employee.create().stream()
		.collect(Collectors.groupingBy(Employee::getGender, Collectors.mapping(Employee::getName, Collectors.toList())));
		System.out.println(m3);
		
		Map<Employee.Gender, Map<Double, String>> m4 = Employee.create().stream()
		.collect(Collectors.groupingBy(Employee::getGender, Collectors.groupingBy(Employee::getIncome, 
				Collectors.mapping(Employee::getName, Collectors.joining(", ")))));
		System.out.println(m4);

		//Collectors groupingBy(Function<? super T,? extends K> classifier, Supplier<M> mapFactory, Collector<? super T,A,D> downstream) returns 
		//a Collector implementing a cascaded "group by" operation on input elements of type T, grouping elements according to a classification 
		//function, and performing a reduction operation on the values associated with a given key using the downstream Collector.
		Map<Gender, List<String>> m5= Employee.create().stream()
		.collect(Collectors.groupingBy(Employee::getGender, HashMap<Gender, List<String>>::new, 
				Collectors.mapping(Employee::getName, Collectors.toList())));
		System.out.println(m5);
		
		//Collectors groupingByConcurrent(Function<? super T,? extends K> classifier) returns a concurrent Collector implementing a "group by" 
		//operation on input elements of type T, grouping elements according to a classification function.
		Map<Employee.Gender, List<Employee>> m6 = Employee.create().stream()
		.collect(Collectors.groupingByConcurrent(Employee::getGender));
		System.out.println(m6);
		
		//Collectors groupingByConcurrent(Function<? super T,? extends K> classifier, Collector<? super T,A,D> downstream) returns a concurrent 
		//Collector implementing a cascaded "group by" operation on input elements of type T, grouping elements according to a classification function, 
		//and performing a reduction operation on the values with a given key using the downstream Collector.
		Map<Employee.Gender, Long> m7 = Employee.create().stream()
		.collect(Collectors.groupingByConcurrent(Employee::getGender, Collectors.counting()));
		System.out.println(m7);
		
		Map<Employee.Gender, Long> m8 = Employee.create().stream()
		.collect(Collectors.groupingByConcurrent(Employee::getGender, ConcurrentHashMap<Gender, Long>::new, Collectors.counting()));
		System.out.println(m8);		
		
		
		
		System.out.println("=================Partitioning================");
		/*
		Partitioning is a special case of grouping.
		Grouping data is based on the keys returned from a function. There could be many groups.
		Partitioning only takes care of two groups based on a predicate. The value evaluated to true is one group, false is another group.
		partitioningBy() method, which collects data in a Map whose keys are always of the type Boolean.
		 */
		
		//Collectors partitioningBy(Predicate<? super T> predicate) returns a Collector which partitions the input elements according to a Predicate, 
		//and organizes them into a Map<Boolean, List<T>>.
		Map<Boolean, List<Employee>> mapping3 = Employee.create().stream()
		.collect(Collectors.partitioningBy(Employee::isMale));
		System.out.println(mapping3);
		
		//Collectors partitioningBy(Predicate<? super T> predicate, Collector<? super T,A,D> downstream) returns a Collector which partitions the 
		//input elements according to a Predicate, reduces the values in each partition according to another Collector, and organizes them into a 
		//Map<Boolean, D> from downstream reduction.
		Map<Boolean, List<String>> mapping4 = Employee.create().stream()
		.collect(Collectors.partitioningBy(Employee::isMale, Collectors.mapping(Employee::getName, Collectors.toList())));
		System.out.println(mapping4);
		
		
		
		System.out.println("===================Reducing (SQL where clause and returns all columns)=====================");
		//Collectors reducing(BinaryOperator<T> op) returns a Collector to reduce its input elements under a specified BinaryOperator.
		Map<Gender, Optional<Employee>> m9 = Employee.create().stream()
		.collect(Collectors.groupingBy(Employee::getGender, Collectors.reducing((Employee x,Employee y) -> x.getIncome()>y.getIncome()?x:y)));
		System.out.println(m9);
		
		//Collectors reducing(U identity, Function<? super T,? extends U> mapper, BinaryOperator<U> op) returns a Collector to reduce its input elements under 
		//a specified mapping function and BinaryOperator.
		Map<Gender, Double> m10 = Employee.create().stream()
		.collect(Collectors.groupingBy(Employee::getGender, Collectors.reducing(0.0,Employee::getIncome, Double::sum)));
		System.out.println(m10);
		
		
		
		System.out.println("=================Summing===================");
		//Collectors summingDouble(ToDoubleFunction<? super T> mapper) returns a Collector that produces the sum of a double-valued function applied to the input elements.
		Double m11 = Employee.create().stream()
		.collect(Collectors.summingDouble(Employee::getIncome));
		System.out.println(m11);
		
		//Collectors summingInt(ToIntFunction<? super T> mapper) returns a Collector that produces the sum of a integer-valued function applied to the input elements.
		Integer m12 = Employee.create().stream()
		.map((Employee e) -> (int)e.getIncome())
		.collect(Collectors.summingInt(e->e));
		System.out.println(m12);
	    
	    //Collectors summingLong(ToLongFunction<? super T> mapper) returns a Collector that produces the sum of a long-valued function applied to the input elements.
		Long m13 = Employee.create().stream()
		.map((Employee e) -> (long)e.getIncome())
		.collect(Collectors.summingLong(e->e));
		System.out.println(m13);
		
		
	    
	    System.out.println("=================Averaging==================");
	    //Collectors averagingDouble(ToDoubleFunction<? super T> mapper) returns a Collector that produces the arithmetic mean of a double-valued function applied 
	    //to the input elements.
	    Double avg1 = Employee.create().stream()
	    .collect(Collectors.averagingDouble(Employee::getIncome));
	    System.out.println(avg1);
	    
	    //Collectors averagingInt(ToIntFunction<? super T> mapper) returns a Collector that produces the arithmetic mean of an integer-valued function applied 
	    //to the input elements.
		Double avg2 = Employee.create().stream() //averagingInt() returns double
		.map((Employee e) -> (int)e.getIncome())
		.collect(Collectors.averagingInt(e->e));
		System.out.println(avg2);
		
		//Collectors averagingLong(ToLongFunction<? super T> mapper) returns a Collector that produces the arithmetic mean of a long-valued function applied 
		//to the elements.
		Double avg3 = Employee.create().stream() //averagingLong() returns double
		.map((Employee e) -> (long)e.getIncome())
		.collect(Collectors.averagingLong(e->e));
		System.out.println(avg3);	
		
		
		
		System.out.println("==================Counting=================");
		//Collectors counting() returns a Collector accepting elements of type T that counts the number of input elements.
		Long count = Employee.create().stream().collect(Collectors.counting());
		System.out.println(count);
		
		
				
		System.out.println("====================Min & Max==================");
		//Collectors minBy(Comparator<? super T> comparator) returns a Collector that produces the minimal element according to a given Comparator, 
		//described as an Optional<T>.
		Optional<Employee> min = Employee.create().stream()
		.collect(Collectors.minBy((Employee e1, Employee e2)->new Double(e1.getIncome()).compareTo(new Double(e2.getIncome()))));
		System.out.println(min);
		
		//Collectors maxBy(Comparator<? super T> comparator) returns a Collector that produces the maximal element according to a given Comparator, 
		//described as an Optional<T>.
		Optional<Employee> max = Employee.create().stream()
		.collect(Collectors.maxBy((Employee e1, Employee e2)->new Double(e1.getIncome()).compareTo(new Double(e2.getIncome()))));
		System.out.println(max);	
		
		
		
	    System.out.println("=================Summarizing===================");
	    /*
	    The java.util package contains three classes to collect statistics:
		DoubleSummaryStatistics
		LongSummaryStatistics
		IntSummaryStatistics
		We can use them to compute the summary statistics on any group of numeric data.
	     */
	    //Collectors summarizingDouble(ToDoubleFunction<? super T> mapper) returns a Collector which applies an double-producing mapping function, and returns summary 
	    //statistics for the resulting values.
	    DoubleSummaryStatistics stat1 = Employee.create().stream()
	    .collect(Collectors.summarizingDouble(Employee::getIncome));
	    System.out.println(stat1);
	    
	    //Collectors summarizingInt(ToIntFunction<? super T> mapper) returns a Collector which applies an int-producing mapping function, and returns summary statistics 
	    //for the resulting values.
	    IntSummaryStatistics  stat2 = Employee.create().stream()
	    .map((Employee e) -> (int)e.getIncome())
	    .collect(Collectors.summarizingInt(e->e));
	    System.out.println(stat2);
	    System.out.println(stat2.getAverage());
	    System.out.println(stat2.getCount());
	    System.out.println(stat2.getMax());
	    System.out.println(stat2.getMin());
	    System.out.println(stat2.getSum());	    
	    
	    //Collectors summarizingLong(ToLongFunction<? super T> mapper) returns a Collector which applies an long-producing mapping function, and returns summary statistics 
	    //for the resulting values.
	    LongSummaryStatistics  stat3 = Employee.create().stream()
	    .map((Employee e) -> (long)e.getIncome())
	    .collect(Collectors.summarizingLong(e->e));
	    System.out.println(stat3);
    
	}
}
