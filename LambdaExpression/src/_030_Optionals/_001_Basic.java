package _030_Optionals;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.stream.IntStream;

import _099_HelperClasses.Employee;
import _099_HelperClasses.Employee.Gender;

public class _001_Basic {
	public static void main(String[] args){
		
		//=========Create Optional Object==========
		//<T> Optional<T> of(T value)
		//Returns an Optional containing the specified value as the non-null value. If the specified value is null, it throws a NullPointerException.
		Optional<String> str1 = Optional.of("Bimal");
		System.out.println(str1);

		//<T> Optional<T> empty()
		//Returns an empty Optional. The Optional<T> returned from this method does not contain a non-null value.
		Optional<Employee> empty  = Optional.empty();
		System.out.println(empty);

		Optional<String> str  = Optional.of("");
		System.out.println(str);

		//custom optional object
		Optional<Employee> emp = Employee.getEmployee();
		System.out.println(emp);
		

		//=============Check for optional value==============
		//get() returns the value contained in the Optional. If the Optional is empty, it throws a NoSuchElementException.
		//System.out.println(empty.get());
		//So we must first check if Optional contain non-null value, before calling get()

		if (emp.isPresent()) {
			Employee value = emp.get();
			System.out.println("Optional contains " + value);
		} else {
			System.out.println("Optional is  empty.");
		}

		if (empty.isPresent()) {
			Employee value = empty.get();
			System.out.println("Optional contains " + value);
		} else {
			System.out.println("Optional is  empty.");
		}

		//Since get() throws exception if optional is empty, another option is to use orElse() (from specified default value), 
		//or use orElseGet() (from specified default supplier)
		//T orElse(T defaultValue)
		//Returns the value contained in the Optional. If the Optional is empty, it returns the specified defaultValue.
		Employee e =emp.orElse(new Employee(99,"dummy", Gender.MALE, 0.0));
		System.out.println("orElse():" + e);
		Employee e4 =empty.orElse(new Employee(99,"dummy", Gender.MALE, 0.0));
		System.out.println("orElse():" + e4);

		//T orElseGet(Supplier<? extends T> defaultSupplier)
		//Returns the value contained in the Optional. If the Optional is empty, it returns the value returned from the specified defaultSupplier.
		Employee e2 =emp.orElseGet(()->new Employee(99,"dummy", Gender.MALE, 0.0));
		System.out.println("orElseGet():" + e2);
		Employee e5 =empty.orElseGet(()->new Employee(99,"dummy", Gender.MALE, 0.0));
		System.out.println("orElseGet():" + e5);

		//ifPresent(Consumer<? super T> action) method from the Optional class takes an action on the value contained in the Optional.
		//If the Optional is empty, this method does not do anything.
		emp.ifPresent((e6)-> System.out.println("ifPresent(): " + e6.getName() + " has " + e6.getIncome())); 
		empty.ifPresent((e7)-> System.out.println("ifPresent(): " + e7.getName() + " has " + e7.getIncome())); 
		
		/*
		 Now that we've looked at functions that simply act based on the existence or non existence of Optional's value, let's move to functions 
		 that changes value inside Optional.
		 */
		
		//if the test fails, filter returns empty Optional
		Employee e8 = emp.filter(e3-> e3.getGender()==Gender.FEMALE)
		.orElseGet(()->new Employee(99,"dummy2", Gender.MALE, 1.0));
		System.out.println("filter():" + e8);

		//Optional also offers the map function, allowing us to transform the value.
		emp.map(e10->e10.getIncome());

		/*
		Optional's API also contain flatMap.
		flatMap could be useful if objects wrapped by Optional also contains Optional properties.		
		To reason about this, let's say that our article has an Optional<Date>, holding the date when published. If the article yet hasn't 
		been published, the Optional would be empty.		
		If we use the map function, we'll end up with a nested Optional
		 */
		Optional<Optional<Double>> d1 = emp.map(Employee::getOptionalSalary);
		Optional<Double> d2 = emp.flatMap(Employee::getOptionalSalary);

		/*
		OptionalInt, OptionalLong, and OptionalDouble deal with optional primitive values.

		getAsInt() method from OptionalInt class returns int value.
		getAsLong() method from OptionalLong class return long value.
		getAsDouble() method from OptionalDouble class return double value.
		The getters for primitive optional classes also throw a NoSuchElementException when they are empty.
		 */
		OptionalInt number = OptionalInt.of(2);
		System.out.println(number);

		OptionalLong number2 = OptionalLong.of(2);
		System.out.println(number2);

		OptionalDouble number3 = OptionalDouble.of(2);
		System.out.println(number3);

		if (number.isPresent()) {
			int value = number.getAsInt();
			System.out.println("Number is " + value);
		} else {
			System.out.println("Number is absent.");
		}
		
		//Some stream operations return optional. For example, the max() method in all stream classes returns an optional object. When using max() 
		//method on an empty stream the return value is an optional object with nothing inside.
		OptionalInt max = IntStream.of(10, 20, 30).filter(n -> n % 2 == 1).max();
		System.out.println(max);

	}
}
