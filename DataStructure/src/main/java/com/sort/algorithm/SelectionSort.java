package com.sort.algorithm;

import java.util.LinkedList;
import java.util.List;

public class SelectionSort {

	public static void main(String a[]){
		SelectionSort ss=new SelectionSort();
		int[] arr={42,34,88,56,7,67,10,2};
		ss.selectionSort(arr);
		ss.stableSelectionSort();
		ss.stableSelectionSortUsingLinkedList();
	}

	public void selectionSort(int[] arr){
		int n=arr.length;
		int swap=0;
		int comparison=0;
		printArray(arr);

		for (int i = 0; i < n-1; i++) {
			// we need not to go upto n-1 (it wont effect if you want to), because during 
			// the pass i=n-2, elements at n-2 & n-1 will get sorted.
			int iMin = i;
			for (int j = i + 1; j < n; j++){
				comparison++;
				if (arr[j] < arr[iMin])
					iMin = j;
			}
			//swap, only if required
			if(iMin != i){
				swap++;
				int temp = arr[iMin]; 
				arr[iMin] = arr[i];
				arr[i] = temp;
			}
			printArray(arr);	
		}
		System.out.println("No. of swaps=" + swap);
		System.out.println("No. of comparisons=" + comparison);
	}

	public void stableSelectionSort(){
		int[] arr={10,34,2,56,7,67,88,42};		
		int n=arr.length;
		int shift=0;
		int comparison=0;
		printArray(arr);

		for (int i = 0; i < n-1; i++) {
			int iMin = i;
			for (int j = i + 1; j < n; j++){
				comparison++;
				if (arr[j] < arr[iMin])
					iMin = j;
			}
			int min=arr[iMin];
			while(iMin>i){
				shift++;
				arr[iMin]=arr[iMin-1];
				iMin--;
			}
			shift++;
			arr[i]=min;
			printArray(arr);
		}
		System.out.println("No. of write operations=" + shift);
		System.out.println("No. of comparisons=" + comparison);
	}

	public void stableSelectionSortUsingLinkedList(){
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(42);list.add(34);list.add(88);list.add(56);
		list.add(7);list.add(67);list.add(10);list.add(2);
		int n=list.size();
		int comparison=0;
		printList(list);

		for (int i=0;i<n-1;i++){
			int min=i;
			for (int j=i+1;j<n;j++){
				comparison++;
				if (list.get(min)>list.get(j)){
					min=j;
				}
			}
			int mini=list.get(min);
			list.remove(min);
			list.add(i, mini);
			printList(list);
		}
		System.out.println("No. of comparisons=" + comparison);
		System.out.println("NO SWAPS, but additional space requirement");
	}

	public void printArray(int[] arr){
		for(int j:arr)	
			System.out.print(j + "  ");
		System.out.println();
	}

	public void printList(List<Integer> arr){
		for(int j:arr)	
			System.out.print(j + "  ");
		System.out.println();
	}
}

