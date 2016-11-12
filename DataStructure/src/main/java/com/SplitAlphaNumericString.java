package com;

public class SplitAlphaNumericString {
	public static void main(String [] args){
		String strNum = "****123Jdk1.5, Oracle10g 45Hello 678Java 999 world!!!111****";
		splitIntoNumericAndAlphaStrings(strNum);
	}
	
	public static void splitIntoNumericAndAlphaStrings(String strNum){
		char ch;
	    /*
	     * Try to use StringBuffer when there is more text concatenation is
	     * required.
	     * 
	     * Using String class will lead to lot of Memory allocation/deallocation
	     * on the fly,
	     * 
	     * which may cause performance issues in bigger applications doing huge
	     * text manipulation.
	     */
		StringBuffer num = new StringBuffer();
		StringBuffer str = new StringBuffer();
		for (int i=0; i<strNum.length(); i++){
            // better to use charAt method rather substr method which creates
            // string object on fly
			ch = strNum.charAt(i); 
            // Integer.parseInt does lot of things which is not necessary in our
            // context.

            // Moreover it throws exception if the char is not a number, that
            // means Memory allocation/deallocation happens on the fly.

            // isDigit is simple and sufficient in our context. It doesn't
            // involve exception object creations
			if(Character.isDigit(ch)) {
				num.append(ch);				
			}
			else str.append(ch);
		}
		System.out.println("numbers " + num);
		System.out.println("alphabets " + str);
	}
}
