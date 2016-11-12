package com.sort.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class MS4 {
	
	public static <T extends Comparable<T>> Queue<T> naturalMergeSort(Queue<T> input) {
	    int arrayIndex = 0;
	    Queue<T> output = new LinkedList<T>();
	    Queue<T> tempArray1 = new LinkedList<T>();
	    Queue<T> tempArray2 = new LinkedList<T>();
	    while (arrayIndex < input.size()) {
	        while (input.size() > 0) {
	            while (input.size() > 0) {
	                naturalMerge(input, output, tempArray1);
	                naturalMerge(input, output, tempArray2);
	            }
	            while (tempArray1.size() > 0 || tempArray2.size() > 0) {
	                naturalMerge(tempArray1, tempArray2, output);
	                naturalMerge(tempArray1, tempArray2, input);
	            }
	        }
	    }
	    return output;
	}

	public static <T extends Comparable<T>> void naturalMerge(Queue<T> left, Queue<T> right, Queue<T> output) {
	    T prevLeft = null;
	    T prevRight = null;
	    while ((left.size() > 0 && (prevLeft == null || prevLeft.compareTo(left.peek()) <= 0)) ||
	           (right.size() > 0 && (prevRight == null || prevRight.compareTo(right.peek()) <= 0))) {

	        if (right.size() == 0 || (left.size() > 0 && left.peek().compareTo(right.peek()) <= 0)) {
	            prevLeft = left.poll();
	            output.add(prevLeft);
	        } else {
	            prevRight = right.poll();
	            output.add(prevRight);
	        }
	    }
	}

}
