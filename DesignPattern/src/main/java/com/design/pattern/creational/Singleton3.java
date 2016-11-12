package com.design.pattern.creational;

public class Singleton3 {
	private Singleton3(){ }
	private static Singleton3 instance;

	public static Singleton3 getInstance(){
		if (instance != null){
			instance = new Singleton3();
		}
		return instance;
	}
}