package com.design.pattern.Structural;

// Component
interface Animal{
	public void describe();
}

// Concrete Component
class LivingAnimal implements Animal{
	public void describe(){
		System.out.println("\nI am an Animal");
	}
}

// Decorator
abstract class Decorator implements Animal{
	Animal animal;
	Decorator (Animal animal){
		this.animal = animal;
	}
}

// Concrete Decorator 1/2
class LegDecorator extends Decorator{
	LegDecorator(Animal animal) {
		super(animal);
	}
	public void describe() {
		animal.describe();
		System.out.println("I have legs and can dance");
	}	
}

// Concrete Decorator 2/2
class WingDecorator extends Decorator{
	WingDecorator(Animal animal) {
		super(animal);
	}
	public void describe() {
		animal.describe();
		System.out.println("I have wings and can fly");
	}	
}

// Client
public class DecoratorDemo {
	public static void main(String[] args){
		Animal animal = new LivingAnimal();
		animal.describe();
		animal = new LegDecorator(animal);
		animal.describe();
		animal = new WingDecorator(animal);
		animal.describe();
	}
}
