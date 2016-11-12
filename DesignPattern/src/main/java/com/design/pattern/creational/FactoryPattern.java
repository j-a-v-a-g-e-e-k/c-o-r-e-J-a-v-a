package com.design.pattern.creational;
//package org.bjava.creational;
//
//interface Garment {
//	String print();
//}
//
//class Trouser implements Garment {
//	public String print() {
//		return "Trouser";
//	}
//}
//
//class Shirt implements Garment {
//	public String print() {
//		return "Shirt";
//	}
//}
//
//class GarmentFactory {
//	public static Garment printGarment (String item) {
//		if (item. equalsIgnoreCase ("Trouser")){
//			return new Trouser();
//		}else if(item. equalsIgnoreCase ("Shirt")){
//			return new Shirt();
//		}
//		throw new IllegalArgumentException("No such garment");
//	}
//}
//
//public class FactoryPattern {
//	public static void main(String args[]) {
//		Garment garment = GarmentFactory.printGarment("Shirt");
//		System.out.println(garment.print());
//		garment = GarmentFactory.printGarment("Trouser");
//		System.out.println(garment.print());
//	}
//}
