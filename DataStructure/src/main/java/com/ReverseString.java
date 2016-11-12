package com;

public class ReverseString {

	public static void main(String [] args){
		//original string
		String str = "bimal Jain";
		System.out.println("Original string: " + str);
		
		//reversed string using Stringbuffer
		System.out.println("Reverse string using StringBuffer: " + new StringBuffer(str).reverse().toString());
		
		//iterative method to reverse String
		System.out.println("Reverse string using iteration: " + reverseIteratively(str));
		
		//iterative method using array to reverse String
		System.out.println("Reverse string using iteration & array: " + reverseIterativelyUsingArray(str));
		
		//recursive method to reverse String
		System.out.println("Reverse string using recursion: " + reverseRecursively(str));
		
		reverseNumber(457852);
	}
	
	public static String reverseIteratively(String str){
		StringBuilder builder = new StringBuilder();
		for (int i = str.length()-1 ; i >=0 ; i--){
			builder.append(str.charAt(i));
		}
		return builder.toString();
	}
	
	public static String reverseIterativelyUsingArray(String str){
		char[] arr = str.toCharArray();
		StringBuilder builder = new StringBuilder();
		for (int i = arr.length-1 ; i >=0 ; i--){
			builder.append(arr[i]);
		}
		return builder.toString();
	}
	
	public static String reverseRecursively(String str){
		//base case to handle one char string and empty string		
		if (str.length()<=1){
			return str;
		}
		return reverseRecursively(str.substring(1)) + str.charAt(0);
	}
	
    public static void reverseNumber(int number){        
        int reverse = 0;
        while(number != 0){
            reverse = (reverse*10)+(number%10);
            number = number/10;
        }
        System.out.println(reverse);
    }
}
