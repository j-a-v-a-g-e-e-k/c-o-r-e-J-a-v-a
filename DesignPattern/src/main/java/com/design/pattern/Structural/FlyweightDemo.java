package com.design.pattern.Structural;

import java.util.HashMap;
import java.util.Map;

// Flyweight
interface Flyweight{
	public void doMath(int a, int b);
}

// Concrete Flyweight 1/2
class FlyweightAdder implements Flyweight{
	FlyweightAdder(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void doMath(int a, int b) {
		System.out.println("Adding a & b: " + (a+b));
	}	
}

//Concrete Flyweight 2/2
class FlyweightMultiplier implements Flyweight{
	FlyweightMultiplier(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void doMath(int a, int b) {
		System.out.println("Adding a & b: " + a*b);
	}	
}

// Flyweight Factory
class FlyweightFactory{
	private static FlyweightFactory flyweightFactory = new FlyweightFactory();
	private Map<String, Flyweight> flyweightPool = new HashMap<String, Flyweight>();
	public static FlyweightFactory getInstance(){
		return flyweightFactory;
	}
	public Flyweight getFlyweight(String key){
		if (flyweightPool.containsKey(key)){
			return flyweightPool.get(key);
		} else {
			Flyweight flyweight = null;
			if (key.equals("add")){
				flyweight = new FlyweightAdder();
			}
			if (key.equals("multiply")){
				flyweight = new FlyweightMultiplier();
			}
			flyweightPool.put(key, flyweight);
			return flyweight;
		}		
	}
}

// Client
public class FlyweightDemo {
	public static void main(String [] args){
		FlyweightFactory factory = FlyweightFactory.getInstance();
		for (int i=1; i<5; i++){
			Flyweight flyweightAdder = factory.getFlyweight("add");
			flyweightAdder.doMath(i, i);
			Flyweight flyweightMultiply = factory.getFlyweight("multiply");
			flyweightMultiply.doMath(i, i);
		}
	}
}
