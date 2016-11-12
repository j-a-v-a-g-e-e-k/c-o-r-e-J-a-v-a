package com.design.pattern.creational;

interface Garment {
	String print();
}

class Trouser implements Garment {
	public String print() {
		return "Trouser";
	}
}

class Shirt implements Garment {
	public String print() {
		return "Shirt";
	}
}

interface Gadget {
	String create();
}

class Watch implements Gadget {
	public String create() {
		return "Watch";
	}
}

class Shoe implements Gadget {
	public String create() {
		return "Shoe";
	}
}

interface AbstractFactory{
	public Garment printGarment (String item);
	public Gadget createGadget (String item);
}

class GarmentFactory implements AbstractFactory {
	public Garment printGarment (String item) {
		if (item. equalsIgnoreCase ("Trouser")){
			return new Trouser();
		}else if(item. equalsIgnoreCase ("Shirt")){
			return new Shirt();
		}
		throw new IllegalArgumentException("No such garment");
	}
	public Gadget createGadget(String item) {
		return null;
	}
}

class GadgetFactory implements AbstractFactory {
	public Gadget createGadget (String item) {
		if (item. equalsIgnoreCase ("Watch")){
			return new Watch();
		}else if(item. equalsIgnoreCase ("Shoe")){
			return new Shoe();
		}
		throw new IllegalArgumentException("No such garment");
	}
	public Garment printGarment(String item) {
		return null;
	}
}

public class AbstractFactoryDemo {
	public static void main(String args[]) {
		AbstractFactory garmentFactory = new GarmentFactory();
		Garment garment = garmentFactory.printGarment("Shirt");
		System.out.println(garment.print());
		
		AbstractFactory gadgetFactory = new GadgetFactory();
		Gadget gadget = gadgetFactory.createGadget("Shoe");
		System.out.println(gadget.create());
	}
}
