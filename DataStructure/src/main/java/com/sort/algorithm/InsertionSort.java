package com.sort.algorithm;

public class InsertionSort {
	public static void main(String a[]){
		int[] input = {10,34,2,56,7,67,88,42};
		InsertionSort is = new InsertionSort();
		is.insertionSort(input);
	}

	public void insertionSort(int[] input){
		int swap=0;
		int comparison=0;
		printNumbers(input);
		for (int i = 1; i <= input.length-1; i++) {
			int value = input[i];
			int hole = i;
			comparison++;
			while (hole >= 1 && input[hole-1] > value){
				swap++;
				input[hole] = input[hole-1];
				hole--;
			}
			swap++;
			input[hole] = value;
			printNumbers(input);
		}
		System.out.println("No of swaps=" + swap);
		System.out.println("No of comparisons=" + comparison);
	}
	
	private void printNumbers(int[] input) {
		for (int i = 0; i < input.length; i++)
			System.out.print(input[i] + "  ");
		System.out.println();
	}
}
