package com.data.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample
{
	public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		System.out.print("before iteration: "+list + "\n");
		Iterator<String> it = list.iterator();	         
		//manipulate list while iterating
		while(it.hasNext()){			
			String str = it.next();
			if(str.equals("1"))list.add("1 new");
			if(str.equals("2"))list.remove("2");
			//below code don't throw ConcurrentModificationException
			//because it doesn't change modCount variable of list
			if(str.equals("3")) list.set(3, "3 new");
//			if(str.equals("4")) it.remove(); //don't work
			System.out.print(str + " ");
		}
		System.out.println(list);

		list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		Iterator<String> it1 = list.iterator();	         
		//manipulate list while iterating
		while(it1.hasNext()){
			System.out.println("list is:"+list);
			String str = it1.next();
			System.out.println(str);	            
//			if(str.equals("1"))list.add("1 new");
//			if(str.equals("2"))list.remove("2");
			//below code don't throw ConcurrentModificationException
			//because it doesn't change modCount variable of list
			if(str.equals("3")) list.set(3, "3 new");
			//iterator can itself change the collection. no exception
			if(str.equals("4")) it1.remove();
		}
		System.out.println(list);
	}
}