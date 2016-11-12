package com;

public class BinarySearch {

	public static void main(String[] args){
		binarySearch(new int[]{1,3,5,7,9}, 9);
	}
	
	public static void binarySearch(int[] array, int search){
		int first=0;
		int last=array.length-1;
		int middle=(first + last)/2;
		
		while(first <= last){
			if (array[middle] == search){
				System.out.println(search + " found at location " + (middle+1));
				break;
			} else if (array[middle] < search){
				first = middle + 1;
				middle = (first + last)/2;				
			} else if (array[middle] > search){
				last = middle - 1;
				middle = (first + last)/2;
			}
		}
		if (first > last){
			System.out.println(search + " not found in the array");
		}
	}
}
