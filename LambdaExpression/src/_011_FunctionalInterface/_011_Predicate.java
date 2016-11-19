package _011_FunctionalInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class _011_Predicate {
	public static void main(String[] args){
		// how to create Predicate from method reference and lambda
		List<Box> boxList = Arrays.asList(new Box(80, "Green"), new Box(55, "Green"), new Box(120, "red"));
		System.out.println(filter(boxList, _011_Predicate::isGreenBox));
		System.out.println(filter(boxList, _011_Predicate::isHeavy));
		System.out.println(filter(boxList, (Box x)->"Green".equals(x.getColor())));
		System.out.println(filter(boxList, (Box x)->x.getWeight()>10));

		//return predicates
		Function<String, Predicate<Box>> fn5 = x-> ((Box b)->x.equals(b.getColor()));
		System.out.println(filter(boxList, fn5.apply("red")));
	}
	
	public static List<Box> filter (List<Box> boxList, Predicate<Box> fn3){
		List<Box> result = new ArrayList<>();
		for(Box box: boxList){
			if(fn3.test(box))
				result.add(box);
		}
		return result;
	}
	public static boolean isGreenBox(Box box){
		return "Green".equals(box.getColor());
	}
	public static boolean isHeavy(Box box){
		return box.getWeight()>10;
	}
}

class Box {
	private int weight = 0;
	private String color = "";

	public Box(int weight, String color) {
		this.weight = weight;
		this.color = color;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String toString() {
		return "Apple{" + "color='" + color + '\'' + ", weight=" + weight + '}';
	}
}
