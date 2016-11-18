/*
Lambda expressions can be used only in the following four contexts.

Assignment Context
Method Invocation Context
Return Context
Cast Context
 */
package _010_lambda;

public class _017_ReturnContext {
	public static void main(String[] args){
		//We can use a lambda expression in a return statement, and its target type is declared in the method return type.
		System.out.println(CalculatorEngine().calculate(2, 3));
	}

	public static Calculator4 CalculatorEngine(){
		return (x,y)->x+y;
	}
}

@FunctionalInterface
interface Calculator4{
	int calculate(int x, int y);
}
