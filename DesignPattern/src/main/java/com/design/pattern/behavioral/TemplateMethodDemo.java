package com.design.pattern.behavioral;

//algorithm base
abstract class HouseTemplate{
	//template method, final so subclasses can't override
	public final void buildHouse(){
		buildFoundation();
		buildPillar();
		buildWall();
		buildWindow();
	}
	//concrete implementation
	private void buildFoundation(){
		System.out.println("Building foundation with cement, iron rods and sand");
	}
	//default implementation
	public void buildWindow(){
		System.out.println("Building glass window");
	}
	//methods to be implemented by subclasses
	abstract public void buildPillar();
	abstract public void buildWall();
}

//concrete algorithm 1/2
class WoodenHouse extends HouseTemplate{
	public void buildPillar(){
		System.out.println("Building pillars with wooden coating");
	}
	public void buildWall(){
		System.out.println("Building wooden walls");
	}
}

//concrete algorithm 2/2
class GlassHouse extends HouseTemplate{
	public void buildPillar(){
		System.out.println("Building pillars with glass coating");
	}
	public void buildWall(){
		System.out.println("Building glass walls");
	}
}

//client
public class TemplateMethodDemo {
	public static void main (String [] args){
		HouseTemplate woodenhouse = new WoodenHouse();
		woodenhouse.buildHouse();
		System.out.println("");
		HouseTemplate glasshouse = new GlassHouse();
		glasshouse.buildHouse();
	}
}
