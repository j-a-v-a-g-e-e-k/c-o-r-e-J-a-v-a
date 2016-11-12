package com.design.pattern.creational;

public class Singleton1 {
	private Singleton1(){ }
	private static Singleton1 instance = new Singleton1();
	public static Singleton1 getInstance(){ return instance; }

	public static void main(String args[]){
		Singleton1 instance1 = Singleton1.getInstance();
		System.out.println("instance1: " + instance1.hashCode());
		Singleton1 instance2 = Singleton1.getInstance();
		System.out.println("instance2: " + instance2.hashCode());
	}}
