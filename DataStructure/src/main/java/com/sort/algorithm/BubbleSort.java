package com.sort.algorithm;

public class BubbleSort {
	
	public static void main(String[] args) {
		int[] input = {10,34,2,56,7,67,88,42};
		BubbleSort bs = new BubbleSort();
		bs.bubbleSort(input);
	}
	
	// After 1st pass- the last element is sorted, after 2nd pass- last 2 elements are sorted, and so on. 
	// So we need not to iterate through n-1 every time in the inner loop.
	// Use for (int i = 0; i <= n -k - 2; i++)

	// We may not need to iterate n-1 time in the outermost loop, because the array might be already sorted before n-1 numbers of pass.
	// If there are no swaps happening, it means that the array is already sorted. In the current example, last 2 passes are redundent.
	// Use a flag to check if swap is happening or not.

	public void bubbleSort(int[] input) {
		int n = input.length;
		int swap=0;
		int comparison=0;
		printNumbers(input);
		for (int k = 0; k <= n-1; k++) {
			int flag = 0;
			for (int i = 0; i <= n - k - 2; i++) {
				comparison++;
				if (input[i] > input[i+1]) {
					swap++;
					swapNumbers(i, i+1, input);
					flag = 1;
				}
			}
			if (flag == 0) break;
			printNumbers(input);
		}
		System.out.println("No of swaps=" + swap);
		System.out.println("No of comparisons=" + comparison);
	}
	
	private void swapNumbers(int i, int j, int[] array) {
		int temp;
		temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	private void printNumbers(int[] input) {
		for (int i = 0; i < input.length; i++)
			System.out.print(input[i] + "  ");
		System.out.println();
	}
}
