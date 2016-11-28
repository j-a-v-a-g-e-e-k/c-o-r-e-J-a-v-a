package _015_Streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import _099_HelperClasses.Employee;
import _099_HelperClasses.Employee.Gender;

public class _012_Stream_Methods {
	public static void main(String[] args){
		Employee e1 = new Employee(1,"Jake", Gender.MALE, 2000.0);
		Employee e2 = new Employee(2,"Jack", Gender.MALE, 2000.0);
		Employee e3 = new Employee(3,"Jane", Gender.FEMALE, 3000.0);
		Employee e4 = new Employee(4,"Jode", Gender.MALE, 4000.0);
		Employee e5 = new Employee(5,"Jeny", Gender.FEMALE, 3000.0);

		System.out.println("=====================================================");
		System.out.println("====================CREATE STREAM====================");
		System.out.println("=====================================================");
		Stream.of(e1)
		.forEach(System.out::println);

		Stream.of(e1, e2)
		.forEach(System.out::println);

		Stream.builder()
		.add(e1).add(e2).add(e3).add(e4).add(e5).build()
		.forEach(System.out::println);

		Builder<Employee> builder = Stream.builder();
		builder.add(e5);
		builder.add(e4);
		builder.build().forEach(System.out::println);

		Stream.generate(Employee::create) //generate() will keep calling create() method to generate unlimited stream
		.limit(10).forEach(System.out::println);

		Stream.iterate(e1, (e9)->{e9.setIncome(10000); return e9;})
		.limit(10).forEach(System.out::println);

		Long count = Stream.empty().count();
		System.out.println(count);
		

		System.out.println("==============================================================");
		System.out.println("====================INTERMEDIATE OPERATION====================");
		System.out.println("==============================================================");
		Employee.create().stream().parallel().forEach(System.out::println);
		
		Employee.create().stream().map(Employee::getIncome).forEach(System.out::println);		
		Employee.create().stream().mapToInt(Employee::getId).forEach(System.out::println);
		Employee.create().stream().mapToLong(Employee::getId).forEach(System.out::println);
		Employee.create().stream().mapToDouble(Employee::getIncome).forEach(System.out::println);
		
		List<String> stringList = Arrays.asList("2","5","3","4","1");
		//Stream flatMap(Function<? super T,? extends Stream<? extends R>> mapper) returns a stream by replacing this stream with a mapped 
		//stream by applying the provided mapping function.
		stringList.stream().flatMap(n-> Stream.of(n)).forEach(System.out::println);

		//Stream flatMapToDouble(Function<? super T,? extends DoubleStream> mapper) returns an DoubleStream by replacing each element with 
		//the contents of a mapped stream produced by applying the mapping function.		
		stringList.stream().flatMapToDouble(n-> DoubleStream.of(Double.parseDouble(n))) //creating 5 streams here
		.forEach(System.out::println);

		//incase of using map, you get streams and not doubles
		stringList.stream().map(n-> DoubleStream.of(Double.parseDouble(n))).forEach(System.out::println);

		stringList.stream().flatMapToInt(n-> IntStream.of(Integer.parseInt(n))).forEach(System.out::println);
		stringList.stream().flatMapToLong(n-> LongStream.of(Long.parseLong(n))).forEach(System.out::println);

		Employee.create().stream().filter(Employee::isMale).forEach(System.out::println);
		Employee.create().stream().limit(2).forEach(System.out::println);
		Employee.create().stream().skip(3).forEach(System.out::println);
		Employee.create().stream().distinct().forEach(System.out::println);

		//sort according to natural order
		Employee.create().stream().map(Employee::getIncome).sorted().forEach(System.out::println);
		//sort according to given comparator
		Employee.create().stream().map(Employee::getIncome).sorted(Comparator.reverseOrder()).forEach(System.out::println);

		System.out.println(
				Employee.create().stream()
				.peek(System.out::println)
				.filter(Employee::isMale)
				.peek(e->System.out.println(e.getName() + " is a male"))
				.filter(e->e.getIncome()>2000)
				.collect(Collectors.toList()));
		

		System.out.println("===========================================================");
		System.out.println("====================TERMINAL OPERATION=====================");
		System.out.println("===========================================================");

		System.out.println("================AGGREGATE OPERATIONS================");
		//max(), min() from Stream needs a comparator, whereas the same methods from IntStream() needs nothing. 
		Optional<Employee> maxSalary = 
		Employee.create().stream().max((Employee emp1, Employee emp2) -> new Double(emp1.getIncome()).compareTo(new Double(emp2.getIncome())));
		System.out.println(maxSalary);
		
		Optional<Employee> minSalary1 = 
		Employee.create().stream().min((Employee emp1, Employee emp2) -> new Double(emp1.getIncome()).compareTo(new Double(emp2.getIncome())));
		System.out.println(minSalary1);

		Optional<Double> maxSalary1=
		Employee.create().stream().map(Employee::getIncome).max((Double d1, Double d2) -> d1.compareTo(d2));
		System.out.println(maxSalary1);

		Optional<Double> minSalary=		
		Employee.create().stream().map(Employee::getIncome).min((Double d1, Double d2) -> d1.compareTo(d2));
		System.out.println(minSalary);
		
		OptionalDouble maxSalary2=
		Employee.create().stream().mapToDouble(Employee::getIncome).max();
		System.out.println(maxSalary1);
		
		System.out.println(Employee.create().stream().count());
		
		//Mixed Mode: Different ways to count
		System.out.println(Employee.create().stream().mapToInt(e->1).sum()); //without using count(), but using sum()
		System.out.println(Employee.create().stream().map(e->1).reduce(0,Integer::sum));
		System.out.println(Employee.create().stream().map(e->1).reduce(0,(partialCount,nextElement)->partialCount + nextElement));
		System.out.println(Employee.create().stream().reduce(0,(partialCount, e)->Integer.sum(partialCount,1), Integer::sum));

		System.out.println("================MATCH OPERATIONS================");
		System.out.println(Employee.create().stream().allMatch(Employee::isMale));
		System.out.println(Employee.create().stream().allMatch(e->e.getIncome()>1000));

		System.out.println(Employee.create().stream().noneMatch(Employee::isMale));
		System.out.println(Employee.create().stream().noneMatch(e->e.getIncome()>1000));

		System.out.println(Employee.create().stream().anyMatch(Employee::isMale));
		System.out.println(Employee.create().stream().anyMatch(e->e.getIncome()>1000));

		System.out.println("=================FIND OPERATIONS==================");
		System.out.println(Employee.create().stream().filter(Employee::isMale).findAny());
		System.out.println(Employee.create().stream().filter(e->e.getIncome()>1000).findAny());

		System.out.println(Employee.create().stream().filter(Employee::isMale).findFirst());
		System.out.println(Employee.create().stream().filter(e->e.getIncome()>1000).findFirst());

		System.out.println("====================ARRAY==================");
		//this returns an object array
		Object[] objArray = Employee.create().stream().filter(Employee::isMale).toArray();
		//this returns an employee array
		Employee[] empArray = Employee.create().stream().filter(Employee::isMale).toArray(Employee[]::new);
		
		System.out.println("==================REDUCE==================");
		//check another class specific to reduce()
		
		System.out.println("====================COLLECT==================");
		//check another class specific to collect()
		
	}
}
