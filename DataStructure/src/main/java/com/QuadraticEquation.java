package com;

public class QuadraticEquation {
	
	public static void main(String[] args){
		findRoots(1,5,6);
	}

	public static void findRoots(int a, int b, int c) {
		int discriminant = (b*b)-4*a*c;
		if (discriminant < 0) System.out.println("No real roots");
		else {
			double disc = Math.sqrt(discriminant);
			double x = (-b + disc)/(2*a);
			double y = (-b - disc)/(2*a);
			System.out.println("Real roots are: x=" + x + ", y=" + y);
		}
	}
}
