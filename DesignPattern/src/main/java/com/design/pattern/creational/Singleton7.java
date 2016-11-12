package com.design.pattern.creational;

import java.io.*;

public class Singleton7 implements Serializable{
	private Singleton7(){ }
	private static volatile Singleton7 instance;

	public static Singleton7 getInstance(){
		if (instance == null){
			synchronized (Singleton7.class){
				if (instance == null){
					instance = new Singleton7();
				}
			}
		}
		return instance;
	}

	protected Object readResolve() {	    
		return getInstance();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Singleton7 instanceOne = Singleton7.getInstance();
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
		out.writeObject(instanceOne);
		out.close();
		ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
		Singleton7 instanceTwo = (Singleton7) in.readObject();
		in.close();         
		System.out.println("instance1: "+instanceOne.hashCode());
		System.out.println("instance2: "+instanceTwo.hashCode());         
	}

}