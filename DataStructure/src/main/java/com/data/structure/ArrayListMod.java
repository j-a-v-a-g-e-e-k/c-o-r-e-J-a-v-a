package com.data.structure;

import java.util.Arrays;

public class ArrayListMod {
	private Object[] myStore;
	private int actSize = 0;

	public ArrayListMod(){
		myStore = new Object[10];
	}

	public Object get(int index){
		if(index < actSize){
			return myStore[index];
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public void add(Object obj){
		if(myStore.length-actSize <= 5){
			increaseListSize();
		}
		myStore[actSize++] = obj;
	}
	
	public void add(int index, Object obj){
		actSize++;
		if(myStore.length-actSize <= 5){
			increaseListSize();
		}		
		for(int i=actSize;i>index;i--){
			myStore[i]=myStore[i-1];
		}
		myStore[index]=obj;
		
	}

	public Object remove(int index){
		if(index < actSize){
			Object obj = myStore[index];
			myStore[index] = null;
			int tmp = index;
			while(tmp < actSize){
				myStore[tmp] = myStore[tmp+1];
				myStore[tmp+1] = null;
				tmp++;
			}
			actSize--;
			return obj;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}

	}

	public int size(){
		return actSize;
	}

	private void increaseListSize(){
		myStore = Arrays.copyOf(myStore, myStore.length*2);
		System.out.println("\nNew length: "+myStore.length);
	}

	public static void main(String a[]){
		ArrayListMod mal = new ArrayListMod();
		mal.add(new Integer(2));
		mal.add(new Integer(5));
		mal.add(new Integer(1));
		mal.add(new Integer(23));
		mal.add(new Integer(14));
		mal.add(3, new Integer(4));
		for(int i=0;i<mal.size();i++){
			System.out.print(mal.get(i)+" ");
		}
		mal.add(new Integer(29));
		System.out.println("\nElement at Index 5:"+mal.get(5));
		System.out.println("List size: "+mal.size());
		System.out.println("Removing element at index 2: "+mal.remove(2));
		for(int i=0;i<mal.size();i++){
			System.out.print(mal.get(i)+" ");
		}
	}
}
