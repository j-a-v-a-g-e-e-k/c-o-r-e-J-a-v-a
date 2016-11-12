package com;

public class PerfectNumber {
	
	public static void main(String[] args){
		find();
	}

	public static void find(){
		int input = 28;
		int temp = 0;
		for(int i=1;i<=input/2;i++){
			if (input%i == 0){
				temp=temp+i;
			}
		}
		if(temp==input) System.out.println(input + " is perfect");
		else System.out.println(input + " is NOT perfect");
	}
}
