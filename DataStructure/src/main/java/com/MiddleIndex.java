package com;

public class MiddleIndex {
	public static void main(String args[]) {
		int[] array = { 2, 4, 4, 5, 4, 1 };
		for (int index = 0; index < array.length - 1; index++)
			if (getSum(array, 0, index) == getSum(array,index + 1, array.length - 1))
				System.out.println("MiddleIndex :" + index);
	}

	private static int getSum(int[] array, int i, int index) {
		if (i > index) // exit condition
			return 0;
		else
			return array[i] + getSum(array, i + 1, index);
	}
}
