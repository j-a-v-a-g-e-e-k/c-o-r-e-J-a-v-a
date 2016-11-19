package _010_lambda;

public class _010_FinalModifier {
	public static void main(String[] args){
		//You can use final modifier in the parameter declaration for explicit lambda expressions.
		myInterface fn1 = (final String x, final String y) -> x.length() + y.length();
		System.out.println(fn1.getLength("bimal", "jain"));
		
		//We can just use one modifier as follows.
		myInterface fn2 = (final String x, String y) -> {
			// x="vimal"; this is not allowed
			y="bakliwal";
			return x.length() + y.length();};
		System.out.println(fn2.getLength("bimal", "jain"));
	}
}

@FunctionalInterface
interface myInterface{
	int getLength(String x, String y);
}
