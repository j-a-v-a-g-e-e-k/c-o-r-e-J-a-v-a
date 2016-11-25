/*
 We can create an array using array constructor as follows.

ArrayTypeName::new
int[]::new is calling new int[]. new int[] requires an int type value as the array length, therefore the int[]::new needs an int type input value.
 */
package _010_lambda;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;

public class _025_ArrayConstructorReference {
	public static void main(String[] args){
		fn1();
	}
	
	public static void fn1(){
		//using lambda expression
		IntFunction<int[]> i1 = (x)->new int[x];
		int[] intArray1 = i1.apply(5); //this specifies the array length
		System.out.println(Arrays.toString(intArray1));
		
		Function<Integer, int[]> i3 = (x)-> new int[x];
		int[] intArray3 = i3.apply(5);
		System.out.println(Arrays.toString(intArray3));
		
		//using constructor reference
		IntFunction<int[]> i2 = int[]::new;
		int[] intArray2 = i2.apply(5);
		System.out.println(Arrays.toString(intArray2));
		
		Function<Integer, int[]> i4 = int[]::new;
		int[] intArray4 = i4.apply(5);
		System.out.println(Arrays.toString(intArray4));
		
		//2-dimensional array
		Function<Integer, int[][]> i5 = int[][]::new;
		int[][] intArray5 = i5.apply(5);
		intArray5[0] = new int[5];
		intArray5[1] = new int[5];
		intArray5[2] = new int[5];
		intArray5[3] = new int[5];
		intArray5[4] = new int[5];
		System.out.println(Arrays.deepToString(intArray5));
	}
}
