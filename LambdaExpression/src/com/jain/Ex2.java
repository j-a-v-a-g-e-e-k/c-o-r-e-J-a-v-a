package com.jain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ex2 {

	public static void main(String[] args){
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

		Collections.sort(names, new Comparator<String>() {
			public int compare(String a, String b) {
				return b.compareTo(a);
			}
		});
		
		System.out.println(names);
		
		names = Arrays.asList("peter", "anna", "mike", "xenia");
		System.out.println(names);
		
		//NEW FEATURE
		Collections.sort(names, (a, b) -> b.compareTo(a));
		
		System.out.println(names);
	}
}