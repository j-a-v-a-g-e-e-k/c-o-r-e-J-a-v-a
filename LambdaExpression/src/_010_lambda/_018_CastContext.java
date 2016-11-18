/*
Lambda expressions can be used only in the following four contexts.

Assignment Context
Method Invocation Context
Return Context
Cast Context
 */
package _010_lambda;

public class _018_CastContext {
	public static void main(String[] argv) {
		//We can use a lambda expression preceded by a cast. The type specified in the cast is its target type.
		CalculatorEngine((IntCalculator)((x, y)-> x + y));
		CalculatorEngine((LongCalculator)((x, y)-> x * y));
		CalculatorEngine((IntCalculator)((x, y)-> x / y));
		CalculatorEngine((LongCalculator)((x, y)-> x % y));
	}
	private static void CalculatorEngine(IntCalculator calculator){
		int x = 2, y = 4;
		int result = calculator.calculate(x,y);
		System.out.println(result);
	}
	private static void CalculatorEngine(LongCalculator calculator){
		long x = 2, y = 4;
		long result = calculator.calculate(x,y);
		System.out.println(result);
	}  
}

@FunctionalInterface
interface IntCalculator2{
	int calculate(int x, int y);
}

@FunctionalInterface
interface LongCalculator2{
	long calculate(long x, long y);
}
