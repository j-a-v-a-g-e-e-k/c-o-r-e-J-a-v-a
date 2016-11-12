package com.design.pattern.creational;

interface Unicellular extends Cloneable {
	public Unicellular reproduce();
}

class Amoeba implements Unicellular {
	public Unicellular reproduce() {
		Unicellular amoeba = null;
		try {
			amoeba = (Unicellular) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return amoeba;
	}
	public String toString() {
		return "Bla bla bla it's a new amoeba...";
	}
}

public class PrototypeDemo{	
	public static void main(String[] args) {
		Unicellular amoeba1 = new Amoeba();
		Unicellular amoeba2 = amoeba1.reproduce();
		System.out.println(amoeba1);
		System.out.println(amoeba2);
	}
}
