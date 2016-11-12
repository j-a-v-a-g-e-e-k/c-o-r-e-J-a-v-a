package com.design.pattern.creational;

import java.io.*;
import java.lang.reflect.Constructor;

public class Singleton8 implements Serializable{
	private Singleton8(){ }
	private static volatile Singleton8 instance;

	public static Singleton8 getInstance(){
		if (instance == null){
			synchronized (Singleton8.class){
				if (instance == null){
					instance = new Singleton8();
				}
			}
		}
		return instance;
	}

	protected Object readResolve() {	    
		return getInstance();
	}	

    public static void main(String[] args) {
    	Singleton8 instanceOne = Singleton8.getInstance();
    	Singleton8 instanceTwo = null;
        try {
            Constructor[] constructors = Singleton8.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                //Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                instanceTwo = (Singleton8) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("instance1: " + instanceOne.hashCode());
        System.out.println("instance2: " + instanceTwo.hashCode());
    }

}
