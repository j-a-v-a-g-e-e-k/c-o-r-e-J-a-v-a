/*
Java 8 introduced a new type called an intersection type

An intersection type is an intersection of multiple types.

An intersection type may appear as the target type in a cast.

An ampersand is used between two types as Type1 & Type2 to represent a new type that is an intersection of Type1, Type2.

Lambdas allow us to make an instance that implements several interfaces using something called a cast expression. We can combine as 
many interfaces as we want to, as long as the intersection eventually results in only a single abstract method.
 */

package _010_lambda;

public class _020_IntersectionType {
	public static void main(String[] args){
		Calculator fn2 = (NonFunction & Calculator) (x,y)-> x + y;
		NonFunction fn1 = (NonFunction & Calculator) (x,y)-> x + y;
		//In this way we make a lambda expression serializable.
		java.io.Serializable ser = (java.io.Serializable & Calculator) (x,y)-> x + y;
	}
}

@FunctionalInterface
interface Calculator5{
	long calculate(long x, long y);
}

interface  NonFunction{
}