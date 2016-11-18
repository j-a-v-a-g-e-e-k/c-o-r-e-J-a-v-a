package _011_FunctionalInterface;

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

		ObjIntConsumer<String> x17 = (x,y) -> System.out.println(x+y);
		x17.accept("bimal", 33);//only method available
		
		ObjLongConsumer<String> x18 = (x,y) -> System.out.println(x+y);
		x18.accept("bimal", 33);//only method available

		ObjDoubleConsumer<String> x16 = (x,y) -> System.out.println(x+y);
		x16.accept("bimal", 33.0);//only method available

		BiConsumer<Integer, String> b = (Integer x, String y) -> System.out.println(x + " , " + y);
		b.accept(77, "Bimal");
		
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
