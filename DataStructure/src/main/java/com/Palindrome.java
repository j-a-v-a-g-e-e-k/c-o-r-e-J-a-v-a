package com;
/*
 * a word, phrase, or sequence that reads the same backward as forward, e.g., 
 * madam or nurses run.
 */

public class Palindrome {

	public static void main(String[] args){
		String str = "mom";
		int num = 252;

		if (reverse(num) == num) System.out.println("Number " + num + " is a palindrome");
		else System.out.println("Number " + num + " is not a palindrome");

		if (reverse(str).equals(str)) System.out.println("String " + str + " is a palindrome");
		else System.out.println("String " + str + " is not a palindrome");

		reverseStringUsingMiddleChar(str);
		System.out.println(isPalindrome(str));
	}

	// Method to return the reverse of a number
	public static int reverse(int num){
		int reverseNum = 0;
		int rem = 0;
		while (num>0){
			rem = num%10;
			num = num/10;
			reverseNum = rem + reverseNum*10;
		}
		return reverseNum;
	}

	// Method to return the reverse of a string
	public static String reverse(String str){
		String reverseStr = "";
		for (int i = str.length()-1 ; i >=0 ; i--) 
			reverseStr = reverseStr + str.charAt(i);
		return reverseStr;
	}
	
	// Method to return the reverse string using recursion
	public static boolean isPalindrome(String str){
		char chFirst = str.charAt(0);
		char chLast = str.charAt(str.length()-1);
		if(str == null || str.length()<=1) return true;
		else {
			if (chFirst == chLast){
				return isPalindrome(str.substring(1, str.length()-1));
			}				
		}
		return false;
	}

	// Method to return reverse of a string using middle char
	public static void reverseStringUsingMiddleChar(String str){
		int length  = str.length();
		int i, begin, end, middle;

		begin  = 0;
		end    = length - 1;
		middle = (begin + end)/2;

		for (i = begin; i <= middle; i++) {
			if (str.charAt(begin) == str.charAt(end)) {
				begin++;
				end--;
			}
			else break;
		}
		if (i == middle + 1) System.out.println("String " + str + " is a palindrome");
		else System.out.println("String " + str + " is not a palindrome"); 			
	}
}
