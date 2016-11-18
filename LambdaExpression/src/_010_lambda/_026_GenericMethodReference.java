/*
 We can use generic methods in method reference by specifying the actual type parameter.

The syntax is as follows

ClassName::<TypeName>methodName
The syntax for generic constructor reference

ClassName<TypeName>::new
 */
package _010_lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class _026_GenericMethodReference {

	public static void main(String[] argv){
		Function<String[],List<String>> asList = Arrays::<String>asList;

		System.out.println(asList.apply(new String[]{"a","b","c"}));
	}
}
