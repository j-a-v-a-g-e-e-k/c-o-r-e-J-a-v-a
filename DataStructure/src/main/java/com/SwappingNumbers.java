package com;

public class SwappingNumbers {

	public static void main(String[] args){
		int x=10, y=20;
		swapUsingAdditionAndSubstraction(x,y);
		swapUsingBitwiseOpeartor(x,y);
		swapUsingMultiplicationAndDivision(x,y);
	}
	
	public static void swapUsingAdditionAndSubstraction(int x, int y){
		System.out.println("Before swapping: x=" + x + ", y=" + y);
		x=x+y; // now x is 30
		y=x-y; // now x=30, y=10
		x=x-y; // now x=20, y=10
		System.out.println("After swapping: x=" + x + ", y=" + y);
	}
	
	public static void swapUsingMultiplicationAndDivision(int x, int y){
		System.out.println("Before swapping: x=" + x + ", y=" + y);
		x=x*y; // now x is 30
		y=x/y; // now x=30, y=10
		x=x/y; // now x=20, y=10
		System.out.println("After swapping: x=" + x + ", y=" + y);
	}
	
	public static void swapUsingBitwiseOpeartor(int x, int y){
		System.out.println("Before swapping: x=" + x + ", y=" + y);
		x=x^y; // now x is 30
		y=x^y; // now x=30, y=10
		x=x^y; // now x=20, y=10
		System.out.println("After swapping: x=" + x + ", y=" + y);
	}	
}
