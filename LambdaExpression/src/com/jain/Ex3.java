package com.jain;

@FunctionalInterface
interface Convertor<F,T>{

	T convert (F from);
}

public class Ex3 {

	public static void main(String[] args){
		//NEW FEATURE
		Convertor<String, Integer> convertor = (from) -> Integer.valueOf(from);
		System.out.println(convertor.convert("2000"));

		//NEW FEATURE
		Convertor<String, Integer> convertor2 = Integer::valueOf;
		System.out.println(convertor2.convert("2000"));

		//NEW FEATURE
		Something something = new Something();
		Convertor<String, String> convertor3 = something::startsWith;
		System.out.println(convertor3.convert("Java"));
	}
}

class Something {
	String startsWith(String s) {
		return String.valueOf(s.charAt(0));
	}

	//this does not affect
	int multiply(int i){
		return i*1000;
	}
}


