package com;

public class LargestInteger {

	public static void main(String[] args) {
		int x=10, y=9, z=8;
		if (x>y && x>z) System.out.println(x + " is greater than " + y + " and " + z);
		if (y>x && y>z) System.out.println(y + " is greater than " + x + " and " + z);
		if (z>x && z>y) System.out.println(z + " is greater than " + x + " and " + y);		
	}

}
