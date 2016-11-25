/*
Lambda Behaviour Parameter: We can pass lambda expressions to methods as arguments.
The result of CalculatorEngine method is depending on lambda expressions passed into it.
The behaviour of CalculatorEngine method is parameterized.
Changing the behavior of a method through its parameters is called behavior parameterization.
In behavior parameterization we pass logic encapsulated in lambda expressions to methods as if was data.
 */
package _010_lambda;

class _013_BehaviourParameter {
	public static void main(String[] args){
		CalculatorEngine((x,y)->x+y, 2,3);
		CalculatorEngine((x,y)->x*y, 2,3);		
	}
	
	public static void CalculatorEngine(Calculator c, int i, int j){
		System.out.println(c.calculate(i, j));
	}
}

@FunctionalInterface
interface Calculator{
	int calculate(int x, int y);
}
