/*
Behavior parameterization Ambiguity
It is not always possible for the compiler to infer the type of a lambda expression.
One such situation is passing lambda expressions to overloaded methods.
 */
package _010_lambda;

public class _014_BehaviourParameter {

	public static void main(String[] argv) {
		//CalculatorEngine((x,y)-> x + y); compiler error
		//we have to indicate the parameters for lambda expression to indicate compiler which overloaded function we want to use.
		CalculatorEngine((int x,int y)-> x + y);
		CalculatorEngine((long x, long y)-> x * y);
		CalculatorEngine((int x,int y)-> x / y);
		CalculatorEngine((long x,long y)-> x % y);
		
		//Or we can use a cast as follows.
		CalculatorEngine((IntCalculator)((x, y)-> x + y));
		CalculatorEngine((LongCalculator)((x, y)-> x * y));
		CalculatorEngine((IntCalculator)((x, y)-> x / y));
		CalculatorEngine((LongCalculator)((x, y)-> x % y));
		
		//Or we can avoid using the lambda expression directly as the parameter. We can assign the lambda expression to a functional interface, 
		//and then pass the variable to the method.
		IntCalculator iCal = (x,y)-> x + y;
		CalculatorEngine(iCal);
		CalculatorEngine((long x, long y)-> x * y);
		CalculatorEngine((int x,int y)-> x / y);
		CalculatorEngine((long x,long y)-> x % y);
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
interface IntCalculator{
	int calculate(int x, int y);
}

@FunctionalInterface
interface LongCalculator{
	long calculate(long x, long y);
}
