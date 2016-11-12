package com.sort.algorithm;

public class MergeSort {

	private int[] array = {45,23,89,11,77,98,4,28};
	private int length = array.length;
	private int[] tempMergArr = new int[length];

	public static void main(String a[]){
		MergeSort mms = new MergeSort();
		mms.mergeSort(0, mms.length - 1);
	}

	private void mergeSort(int lowerIndex, int higherIndex) {
		if (lowerIndex < higherIndex) {
			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
			mergeSort(lowerIndex, middle);
			mergeSort(middle + 1, higherIndex);
			mergeParts(lowerIndex, middle, higherIndex);
		}
	}

	private void mergeParts(int lowerIndex, int middle, int higherIndex) {
		for (int i = lowerIndex; i <= higherIndex; i++) {
			tempMergArr[i] = array[i];
		}
		int i = lowerIndex;
		int j = middle + 1;
		int k = lowerIndex;
		while ( i <= middle || j <= higherIndex )	{
			if ( i <= middle && j <= higherIndex ) {  
				if ( tempMergArr[i] < tempMergArr[j] )
					array[k++] = tempMergArr[i++];
				else
					array[k++] = tempMergArr[j++];
			} else if (j <= higherIndex)
				array[k++] = tempMergArr[j++];
			else if (i <= middle)
				array[k++] = tempMergArr[i++];
		}
		//		while (i <= middle && j <= higherIndex) {
		//			if (tempMergArr[i] <= tempMergArr[j]) {
		//				array[k] = tempMergArr[i];
		//				i++;
		//			} else {
		//				array[k] = tempMergArr[j];
		//				j++;
		//			}
		//			k++;
		//		}
		//		while (i <= middle) {
		//			array[k] = tempMergArr[i];
		//			k++;
		//			i++;
		//		}
		//		while (j <= higherIndex) {
		//			array[k] = tempMergArr[j];
		//			k++;
		//			j++;
		//		}
		for(int i2:array)
			System.out.print(i2 + " ");
		System.out.println();
	}
}
