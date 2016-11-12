package com.design.pattern.Structural;

import java.util.List;
import java.util.ArrayList;

//Base component
interface Component{
	public void sayHello();
	public void sayGoodBye();
}

//Leaf
class Leaf implements Component{
	String name;
	Leaf (String name){
		this.name = name;
	}
	public void sayHello() {
		System.out.println(name + " says Hello");
	}
	public void sayGoodBye() {
		System.out.println(name + " says Goodbye");
	}
}

// composite
class Composite implements Component{
	List<Component> components = new ArrayList<Component>();

	public void sayHello() {
		for (Component component:components )
			component.sayHello();
	}
	public void sayGoodBye() {
		for (Component component:components )
			component.sayGoodBye();
	}
	public void addComponent(Component component){
		components.add(component);
	}
	public void removeComponent(Component component){
		components.remove(component);
	}
}

//client
public class CompositeDemo {
	public static void main(String [] args){
		Leaf leaf1 = new Leaf("dan");
		Leaf leaf2 = new Leaf("robert");
		Leaf leaf3 = new Leaf("alex");
		Leaf leaf4 = new Leaf("mark");
		Leaf leaf5 = new Leaf("stefhen");
		Composite composite1 = new Composite();
		composite1.addComponent(leaf1);
		composite1.addComponent(leaf2);
		Composite composite2 = new Composite();
		composite2.addComponent(leaf3);
		composite2.addComponent(leaf4);
		Composite composite3 = new Composite();
		composite3.addComponent(composite1);
		composite3.addComponent(composite2);
		composite3.addComponent(leaf5);

		System.out.println("Calling 'sayHello' on leaf1");
		leaf1.sayHello();
		System.out.println("\nCalling 'sayHello' on composite1");
		composite1.sayHello();
		System.out.println("\nCalling 'sayHello' on composite2");
		composite2.sayHello();
		System.out.println("\nCalling 'sayGoodbye' on composite3");
		composite3.sayGoodBye();
	}
}
