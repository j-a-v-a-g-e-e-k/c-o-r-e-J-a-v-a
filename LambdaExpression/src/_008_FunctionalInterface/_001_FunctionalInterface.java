/*
Functional interfaces have a single functionality to exhibit. For example, a Comparable interface with a single method ‘compareTo’ is used for 
comparison purpose. Java 8 has defined a lot of functional interfaces to be used extensively in lambda expressions. Following is the list of 
functional interfaces defined in java.util.Function package.

1	BiConsumer<T,U>
Represents an operation that accepts two input arguments, and returns no result.

2	BiFunction<T,U,R>
Represents a function that accepts two arguments and produces a result.

3	BinaryOperator<T>
Represents an operation upon two operands of the same type, producing a result of the same type as the operands.

4	BiPredicate<T,U>
Represents a predicate (Boolean-valued function) of two arguments.

5	BooleanSupplier
Represents a supplier of Boolean-valued results.

6	Consumer<T>
Represents an operation that accepts a single input argument and returns no result.

7	DoubleBinaryOperator
Represents an operation upon two double-valued operands and producing a double-valued result.

8	DoubleConsumer
Represents an operation that accepts a single double-valued argument and returns no result.

9	DoubleFunction<R>
Represents a function that accepts a double-valued argument and produces a result.

10	DoublePredicate
Represents a predicate (Boolean-valued function) of one double-valued argument.

11	DoubleSupplier
Represents a supplier of double-valued results.

12	DoubleToIntFunction
Represents a function that accepts a double-valued argument and produces an int-valued result.

13	DoubleToLongFunction
Represents a function that accepts a double-valued argument and produces a long-valued result.

14	DoubleUnaryOperator
Represents an operation on a single double-valued operand that produces a double-valued result.

15	Function<T,R>
Represents a function that accepts one argument and produces a result.

16	IntBinaryOperator
Represents an operation upon two int-valued operands and produces an int-valued result.

17	IntConsumer
Represents an operation that accepts a single int-valued argument and returns no result.

18	IntFunction<R>
Represents a function that accepts an int-valued argument and produces a result.

19	IntPredicate
Represents a predicate (Boolean-valued function) of one int-valued argument.

20	IntSupplier
Represents a supplier of int-valued results.

21	IntToDoubleFunction
Represents a function that accepts an int-valued argument and produces a double-valued result.

22	IntToLongFunction
Represents a function that accepts an int-valued argument and produces a long-valued result.

23	IntUnaryOperator
Represents an operation on a single int-valued operand that produces an int-valued result.

24	LongBinaryOperator
Represents an operation upon two long-valued operands and produces a long-valued result.

25	LongConsumer
Represents an operation that accepts a single long-valued argument and returns no result.

26	LongFunction<R>
Represents a function that accepts a long-valued argument and produces a result.

27	LongPredicate
Represents a predicate (Boolean-valued function) of one long-valued argument.

28	LongSupplier
Represents a supplier of long-valued results.

29	LongToDoubleFunction
Represents a function that accepts a long-valued argument and produces a double-valued result.

30	LongToIntFunction
Represents a function that accepts a long-valued argument and produces an int-valued result.

31	LongUnaryOperator
Represents an operation on a single long-valued operand that produces a long-valued result.

32	ObjDoubleConsumer<T>
Represents an operation that accepts an object-valued and a double-valued argument, and returns no result.

33	ObjIntConsumer<T>
Represents an operation that accepts an object-valued and an int-valued argument, and returns no result.

34	ObjLongConsumer<T>
Represents an operation that accepts an object-valued and a long-valued argument, and returns no result.

35	Predicate<T>
Represents a predicate (Boolean-valued function) of one argument.

36	Supplier<T>
Represents a supplier of results.

37	ToDoubleBiFunction<T,U>
Represents a function that accepts two arguments and produces a double-valued result.

38	ToDoubleFunction<T>
Represents a function that produces a double-valued result.

39	ToIntBiFunction<T,U>
Represents a function that accepts two arguments and produces an int-valued result.

40	ToIntFunction<T>
Represents a function that produces an int-valued result.

41	ToLongBiFunction<T,U>
Represents a function that accepts two arguments and produces a long-valued result.

42	ToLongFunction<T>
Represents a function that produces a long-valued result.

43	UnaryOperator<T>
Represents an operation on a single operand that produces a result of the same type as its operand.
 */

package _008_FunctionalInterface;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

public class _001_FunctionalInterface {
	public static void main(String[] args){
		Function<Integer, String> e = x -> Integer.toString(x);
		System.out.println(e.apply(89));
		
		IntFunction<String> x2 = x -> x + "" ;
		System.out.println(x2.apply(4)); //only method available
		
		LongFunction<String> x10 = (x) -> x + "";
		System.out.println(x10.apply(2)); //only method available
		
		DoubleFunction<String> l = (x) -> x + "";
		System.out.println(l.apply(3.0));//only method available
		
		ToIntFunction<String> x23 = x -> Integer.parseInt(x);
		System.out.println(x23.applyAsInt("2"));//only method available
		
		ToLongFunction<Integer> x25 = x -> x;
		System.out.println(x25.applyAsLong(2)); //only method available
		
		ToDoubleFunction<Integer> x21 = x -> x;
		System.out.println(x21.applyAsDouble(3));//only method available				

		IntToLongFunction x6 = (x) -> x;
		System.out.println(x6.applyAsLong(3)); //only method available
		
		IntToDoubleFunction x5 = (x) -> x;
		System.out.println(x5.applyAsDouble(3)); //only method available
		
		LongToIntFunction x14 = x -> (int)x;
		System.out.println(x14.applyAsInt(3)); //only method available
		
		LongToDoubleFunction x13 = x -> x;
		System.out.println(x13.applyAsDouble(3)); //only method available
		
		DoubleToIntFunction q = (x) -> (int)x ;
		System.out.println(q.applyAsInt(3.235)); //only method available
		
		DoubleToLongFunction r = (x) -> (long)x;
		System.out.println(r.applyAsLong(23.23)); //only method available
		
		BiFunction<Integer, String, String> d = (Integer x, String y) -> x + " , " + y;
		System.out.println(d.apply(99, "bimal"));
		
		//why there is no IntBiFunction()?
		
		ToIntBiFunction<String, String> x22 = (x,y) -> Integer.parseInt(x) + Integer.parseInt(y);
		System.out.println(x22.applyAsInt("2", "3")); //only method available
		
		ToLongBiFunction<Integer, Integer> x24 = (x,y) -> x+y;
		System.out.println(x24.applyAsLong(2, 3)); //only method available
		
		ToDoubleBiFunction<Integer, Integer> x20 = (x,y) -> x+y;
		System.out.println(x20.applyAsDouble(1, 2));//only method available
		
		System.out.println("======================");
		
		Consumer<Integer> c = (x) -> System.out.println(x*2); 
		c.accept(99);
		
		//here int is primitive
		IntConsumer ic = x -> System.out.println(x);
		ic.accept(2);
		
		LongConsumer x9 = (x) -> System.out.println(x);
		x9.accept(3);
		
		DoubleConsumer k = (x) -> System.out.println(x);
		k.accept(4.0);

		//there is no ToIntConsumer(), since consumer don't return anything
		
		BiConsumer<Integer, String> b = (Integer x, String y) -> System.out.println(x + " , " + y);
		b.accept(77, "Bimal");

		ObjIntConsumer<String> x17 = (x,y) -> System.out.println(x+y);
		x17.accept("bimal", 33);//only method available
		
		ObjLongConsumer<String> x18 = (x,y) -> System.out.println(x+y);
		x18.accept("bimal", 33);//only method available

		ObjDoubleConsumer<String> x16 = (x,y) -> System.out.println(x+y);
		x16.accept("bimal", 33.0);//only method available
		
		System.out.println("======================");
		
		Supplier<String> x19 = () -> "bimal";
		System.out.println(x19.get());//only method available
		
		IntSupplier x4 = () -> 7;
		System.out.println(x4.getAsInt());//only method available
		
		LongSupplier x12 = () -> 2;
		System.out.println(x12.getAsLong());//only method available
		
		DoubleSupplier n = () -> 2.0;
		System.out.println(n.getAsDouble());//only method available
		
		BooleanSupplier h = () -> 1==1;
		System.out.println(h.getAsBoolean());//only method available
		
		//there are no BiSupplier() since supplier don't take any parameter
		
		System.out.println("======================");
		
		Predicate<String> p = (String s) -> s == null;
		System.out.println(p.test("bimal"));

		IntPredicate x3 = x -> x==0;
		System.out.println(x3.test(0));
		
		LongPredicate x11 = (x) -> x==0;
		System.out.println(x11.test(2));
		
		DoublePredicate m = (x) -> x==3.0;
		System.out.println(m.test(3.0));

		BiPredicate<String, Integer> g = (String x, Integer y) -> x!=null && y!=null;
		System.out.println(g.test("bimal", 999));
		
		System.out.println("======================");
		
		//UnaryOperator represents an operation on a single operand that produces a result of the same type as its operand. 
		//This is a specialization of Function for the case where the operand and result are of the same type.
		//UnaryOperator extends Function. So you inherit all Function methods
		UnaryOperator<Integer> x26 = x->x;
		System.out.println(x26.apply(2));
		
		//IntUnaryOperator represents an operation on a single int-valued operand that produces an int-valued result. 
		//This is the primitive type specialization of UnaryOperator for int.
		IntUnaryOperator x7 = (x) -> x;
		System.out.println(x7.applyAsInt(3));
		
		LongUnaryOperator x15 = x -> x;
		System.out.println(x15.applyAsLong(3));
		
		DoubleUnaryOperator s = (x) -> x*2.0;
		System.out.println(s.applyAsDouble(3.0));
		
		BinaryOperator<Integer> f = (Integer x, Integer y) -> x * y;
		System.out.println(f.apply(2, 3));
		
		IntBinaryOperator t = (x,y) -> x+y;
		System.out.println(t.applyAsInt(2, 2)); //only method available
		
		LongBinaryOperator x8 = (x,y) -> x*y;
		System.out.println(x8.applyAsLong(1, 2)); //only method available
		
		//The type DoubleBinaryOperator is not generic; it cannot be parameterized with arguments <Double>
		//in lambda expression, type is inferred
		DoubleBinaryOperator j = (x,y) -> x*y;
		System.out.println(j.applyAsDouble(2.0, 3.0)); //only method available
	}
}
