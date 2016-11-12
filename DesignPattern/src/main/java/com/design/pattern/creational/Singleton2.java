package com.design.pattern.creational;

public class Singleton2 {
	private Singleton2(){}
	private static Singleton2 instance;
	static{
		try{
			instance = new Singleton2();
		} catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
		}
	}
	public static Singleton2 getInstance(){
		return instance;
	}
}
