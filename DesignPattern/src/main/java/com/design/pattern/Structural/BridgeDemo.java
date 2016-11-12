package com.design.pattern.Structural;

// Implementor
interface Color{
	public void applyColor(); 
}

// Concrete Implementor 1/2
class RedColor implements Color{
	public void applyColor() {
		System.out.println("Red.");
	}	
}

//Concrete Implementor 2/2
class BlueColor implements Color{
	public void applyColor() {
		System.out.println("Blue.");
	}
}

// Abstraction
abstract class Shape{
	Color color;
	Shape (Color color){
		this.color = color;
	}
	abstract public void getShape();
}

// Refined Abstracton 1/2
class Triangle extends Shape{
	Triangle(Color color) {
		super(color);
	}
	public void getShape(){
		System.out.print("Triangle filled with ");
		color.applyColor();
	}
}

//Refined Abstracton 2/2
class Circle extends Shape{
	Circle(Color color) {
		super(color);
	}
	public void getShape(){
		System.out.print("Circle filled with ");
		color.applyColor();
	}
}

//Client
public class BridgeDemo {
	public static void main(String [] args){
		Shape triangle = new Triangle(new RedColor());
		triangle.getShape();
		triangle = new Triangle(new BlueColor());
		triangle.getShape();
		Shape circle = new Circle(new RedColor());
		circle.getShape();
	}
}
