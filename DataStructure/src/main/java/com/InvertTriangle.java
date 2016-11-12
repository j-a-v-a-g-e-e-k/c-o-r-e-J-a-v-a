package com;
/*
 5  5  5  5  5 
 4  4  4  4 
 3  3  3 
 2  2 
 1
 */

public class InvertTriangle {

	public static void main(String[] args){
		invertTriangle(5);
	}

	public static void invertTriangle(int n){
		while(n > 0){
			for(int j=1;j<=n;j++){
				System.out.print(" "+n+" ");
			}
			System.out.print("\n");
			n--;
		}

	}
}
