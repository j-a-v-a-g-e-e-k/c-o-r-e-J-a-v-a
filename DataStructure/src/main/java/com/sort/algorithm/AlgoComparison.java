package com.sort.algorithm;

public class AlgoComparison {
	public static void main(String[] args){
		SelectionSort ss = new SelectionSort();
		BubbleSort bs = new BubbleSort();
		InsertionSort is = new InsertionSort();
		
		System.out.println("-----worst case-----");
		System.out.println("SELECTION SORT");
		ss.selectionSort(new int[]{9,8,7,6,5,4,3,2,1});
		System.out.println("\nBUBBLE SORT");
		bs.bubbleSort(new int[]{9,8,7,6,5,4,3,2,1});
		System.out.println("\nINSERTION SORT");
		is.insertionSort(new int[]{9,8,7,6,5,4,3,2,1});
		
		System.out.println("\n-----best case-----");
		System.out.println("SELECTION SORT");
		ss.selectionSort(new int[]{1,2,3,4,5,6,7,8,9});
		System.out.println("\nBUBBLE SORT");
		bs.bubbleSort(new int[]{1,2,3,4,5,6,7,8,9});
		System.out.println("\nINSERTION SORT");
		is.insertionSort(new int[]{1,2,3,4,5,6,7,8,9});
		
		System.out.println("\n-----average case-----");
		System.out.println("SELECTION SORT");
		ss.selectionSort(new int[]{42,34,88,56,7,67,10,2});
		System.out.println("\nBUBBLE SORT");
		bs.bubbleSort(new int[]{42,34,88,56,7,67,10,2});
		System.out.println("\nINSERTION SORT");
		is.insertionSort(new int[]{42,34,88,56,7,67,10,2});
	}	
}
