package com;
/*
 * The Fibonacci sequence is defined by the following rule. The first 2 values in the sequence are 1, 1. 
 * Every subsequent value is the sum of the 2 values preceding it.
 */

public class Fibonacci {
	public static void main(String [] args){
		int input=11;

		//using non-recursion
		Fibo(input);

		// using recursion
		System.out.println("\n" + input + "th fibonacci number is: " + RecFibo(input));		
		// to print the Fibonacci series
		for (int i=1; i<=input ; i++)
			System.out.print(" " + RecFibo(i));
	}

	//Non-recursive Solution
	public static void Fibo(int n){
		int a=1, b=1, c=0;
		System.out.println("Fibonacci Series is:");
		System.out.print(" " + a + " " + b);
		for (int i=0; i<n-2 ; i++){
			c=a+b;
			a=b;
			b=c;
			System.out.print(" " + c);
		}
		System.out.println("\n" + n + "th fibonacci number is: " + c);
	}

	public static int RecFibo(int n){
		if (n==1) return 1;
		else if (n==2) return 1;
		else return RecFibo(n-1) + RecFibo(n-2);
	}
}
