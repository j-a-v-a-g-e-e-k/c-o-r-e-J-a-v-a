package com.sort.algorithm;

public class QuickSort {
	public static void quickSort(int[] array, int start, int end){
		if(start<end){
			int partitionIndex=partition(array,start,end);
			quickSort(array,start,partitionIndex-1);
			quickSort(array,partitionIndex+1,end);
		}
		
	}
	
	public static int partition(int[] array, int start, int end){
		int pivot = array[end];
		int partitionIndex = start;
		for (int i=start; i<end; i++){
			if(array[i]<=pivot){
				int temp=array[i];array[i]=array[partitionIndex];array[partitionIndex]=temp;
				partitionIndex++;
			}
		}
		int temp=array[partitionIndex]; array[partitionIndex]=array[end]; array[end]=temp;
		return partitionIndex;
	}

	public static void main(String a[]){
		int[] array=new int[]{5,3,9,1,8,2,7};
		quickSort(array,0,6);
		for(int i=0;i<=6;i++)System.out.print(array[i] + " ");
	}
}
