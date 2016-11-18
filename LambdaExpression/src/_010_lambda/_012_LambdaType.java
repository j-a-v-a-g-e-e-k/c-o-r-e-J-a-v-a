/*
A lambda expression represents an instance of a functional interface.
One lambda expression may map to different functional interface types depending on the context.
The compiler infers the type of a lambda expression.
 */

package _010_lambda;

public class _012_LambdaType {
	public static void main(String[] argv) {
		Processor stringProcessor = (String str) -> str.length();
		SecondProcessor secondProcessor = (String str) -> str.length();
		//stringProcessor = secondProcessor; //compile error
		String name = "Java Lambda";
		int length = stringProcessor.getStringLength(name);
		System.out.println(length);
		length = secondProcessor.noName(name);
		System.out.println(length);
	}
}

@FunctionalInterface
interface Processor {
	int getStringLength(String str);
}

@FunctionalInterface
interface SecondProcessor {
	int noName(String str);
}

/*
Processor or SecondProcessor is called target type.

The process of inferring the type of a lambda expression is called target typing.

The compiler uses the following rules to determine whether a lambda expression is assignable to its target type:
It must be a functional interface.
The parameters of lambda expression must match the abstract method in functional interface.
The return type from the lambda expression is compatible with the return type from the abstract method in functional interface.
The checked exceptions thrown from the lambda expression must be compatible with the declared throws clause of the abstract method in functional interface.

*/
