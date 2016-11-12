package com.data.structure;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {

	public static void main(String[] args) {

		//ConcurrentHashMap
		Map<String,String> myMap = new ConcurrentHashMap<String,String>();
		myMap.put("1", "1");
		myMap.put("2", "1");
		myMap.put("3", "1");
		myMap.put("4", "1");
		myMap.put("5", "1");
		System.out.println("ConcurrentHashMap before iterator:\n "+myMap);
		
		Iterator<String> it = myMap.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();			
			if(key.equals("1")) myMap.put("newKey", "newValue"); //add
			if(key.equals("2")) myMap.remove("2"); //remove
			if(key.equals("3")) myMap.put("3", "new3");	//update		
			if(key.equals("4")) it.remove(); //remove using iterator
			System.out.print(key + " ");
		}
		System.out.println("\nConcurrentHashMap after iterator:\n "+myMap);

		//HashMap
//		myMap = new HashMap<String,String>(); // HashMap don't maintains order
		myMap = new LinkedHashMap<String,String>(); // linkedHashMap maintains order
		myMap.put("1", "1");
		myMap.put("2", "1");
		myMap.put("3", "1");
		myMap.put("4", "1");
		myMap.put("5", "1");
		System.out.println("HashMap before iterator:\n "+myMap);
		Iterator<String> it1 = myMap.keySet().iterator();

		while(it1.hasNext()){
			String key = it1.next();
            //below code don't throw ConcurrentModificationException
            //because it doesn't change modCount variable of map
			if(key.equals("3")) myMap.put("3", "new3");	//update
            //below code don't throw ConcurrentModificationException
            //because iterator itself is updating the collection.
			if(key.equals("4")) it1.remove(); //remove using iterator
			//below code throw ConcurrentModificationException
			if(key.equals("1")){ myMap.put("newKey", "newValue"); break;}//add
			if(key.equals("2")){ myMap.remove("2"); break;}//remove
			System.out.print(key + " "); 
		}
		System.out.println("\nHashMap after iterator:\n "+myMap);
	}
}