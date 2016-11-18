/*
Lambda expressions can be used only in the following four contexts.

Assignment Context
Method Invocation Context
Return Context
Cast Context
 */
package _010_lambda;

public class _016_MethodInvocationContext {
	public static void main(String[] args){
		//We can use a lambda expression as an argument for a method or constructor.
		CalculatoEngine((x,y)->x+y, 2, 3);
	}

	public static void CalculatoEngine(Calculator3 c, int i, int j){
		System.out.println(c.calculate(i, j));
	}
}

@FunctionalInterface
interface Calculator3{
	int calculate(int x, int y);
}
