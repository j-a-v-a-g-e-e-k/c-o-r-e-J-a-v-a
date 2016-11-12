package com;
/*
 * An Armstrong number of three digits is an integer such that the sum of the 
 * cubes of its digits is equal to the number itself. 
 * For example, 371 is an Armstrong number since 3**3 + 7**3 + 1**3 = 371.
 * Other examples, 153 
 */

public class Armstrong {
	public static void main(String[] args){
		armstrong(153);
	}

	public static void armstrong(int num){
		int n = num; //use to check at last time
		int check=0, remainder;
		while(num > 0){
			remainder = num % 10;
			check = check + (int)Math.pow(remainder,3);
			num = num / 10;
		}
		if(check == n) System.out.println(n+" is an Armstrong Number");
		else System.out.println(n+" is not a Armstrong Number");
	}
}
