/*
Lambda expressions can be used only in the following four contexts.

Assignment Context
Method Invocation Context
Return Context
Cast Context
 */
package _010_Lambda;

public class _015_AssignmentContext {
	public static void main(String[] args){
		//A lambda expression can appear to the right of the assignment operator.
		Calculator2 fn1 = (x,y)->x+y;
		System.out.println(fn1.calculate(2, 3));
	}
}

@FunctionalInterface
interface Calculator2{
	int calculate(int x, int y);
}
