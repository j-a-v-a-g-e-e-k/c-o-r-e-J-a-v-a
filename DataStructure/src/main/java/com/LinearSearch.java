package com;

public class LinearSearch {
	public static void main(String[] args){
		int [] arr = {1, 2, 3, 4, 5};
		int search = 2;
		linerSearch(arr, search);
	}

	public static void  linerSearch(int[] arr, int search){
		int i =0;
		loop1: for ( ; i<arr.length ; i++){
			if (arr[i] == search) { 
				System.out.println(search + " found"); 
				break loop1; 
			}		
		}
		if (i == arr.length) System.out.println(search + " not found");
	}
}
