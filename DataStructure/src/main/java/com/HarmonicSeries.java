package com;
/*
 * a series whose terms are in harmonic progression, as in 
 * 1 + 1/2 + 1/3 + …
 */
public class HarmonicSeries {
	
	public static void main(String[] args){
		harmonicSeries(6);
	}

	public static void harmonicSeries(int n){
		double result = 0.0;
		while(n > 0){
			result = result + (double) 1 / n;
			n--;
		}
		System.out.println("Output of Harmonic Series is "+result);
	}
}
