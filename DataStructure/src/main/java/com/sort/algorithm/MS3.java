package com.sort.algorithm;

public class MS3 {
	
	public static void main(String[] args){
		int[] input = {6, 3, 7, 7, 8, 4, 1};
		bottomUpMergeSort(input);
		for(int i=0;i<input.length;i++) System.out.println(input[i]);
	}

	public static void bottomUpMergeSort(int[] array) {
	    int[] workArray = new int[array.length];
	    int chunkSize = 1;
	    while (chunkSize < array.length) {
	        int i = 0;
	        while (i < array.length - chunkSize) {
	            bottomUpMerge(array, i, chunkSize, workArray);
	            i += chunkSize * 2;
	        }
	        chunkSize *= 2;
	    }
	}

	public static void bottomUpMerge(int[] array, int leftPosition, int chunkSize, int[] workArray) {
	    int rightPosition = leftPosition + chunkSize;
	    int endPosition = Math.min(leftPosition + chunkSize * 2 - 1, array.length - 1);
	    int leftIndex = leftPosition;
	    int rightIndex = rightPosition;

	    for (int i = 0; i <= endPosition - leftPosition; i++) {
	        if (leftIndex < rightPosition && (rightIndex > endPosition || array[leftIndex] <= array[rightIndex])) {
	            workArray[i] = array[leftIndex++];
	        } else {
	            workArray[i] = array[rightIndex++];
	        }
	    }

	    for (int i = leftPosition; i <= endPosition; i++) {
	        array[i] = workArray[i - leftPosition];
	    }
	}
}
