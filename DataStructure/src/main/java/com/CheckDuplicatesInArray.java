package com;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class CheckDuplicatesInArray {

	public static void main(String[] args){
		bruteForce();
		checkDuplicateUsingSet();
		findDuplicateNumber();
		findDuplicateNumber2();
		findDuplicateNumber3();
		findDuplicateNumber4();
		findDuplicateNumber5();
	}
	
	public static void bruteForce(){
		String[] input = new String[]{"one", "two", "one", "two", "three", "one"};
		for (int i=0 ; i<input.length ; i++){
			for (int j=i+1; j<input.length ; j++){
				if (input[i] == input[j])
					System.out.print(input[i] + " ");
			}
		}
	}
	
	public static boolean checkDuplicateUsingSet(){
		String[] input = new String[]{"one", "two", "one", "two", "three", "one"};
		Set<String> set = new HashSet<String>();
		for (String str: input){
			set.add(str);
		}
		if(set.size() < input.length) return true;
		return false;
	}
	
	/* Find out duplicate number between 1 to N numbers */
	public static void findDuplicateNumber(){
		System.out.println("\nfindDuplicateNumber");
		int[] input = {1,2,3,4,5,6,7,8,9,10,5};
		int size = input.length-1;
		int total = 0;
		for (int i=0;i<=size;i++){
			total = total + input[i];
		}
		int duplicate = total-(size*(size+1)/2);
		System.out.println(duplicate);
	}
	
	// However, if numbers in a set are not consecutive, Algorithm described above won't work
	public static void findDuplicateNumber2(){
		System.out.println("findDuplicateNumber2");
		int[] input = {5,6,3,4,6,2,4,5,6,6,6};
		Set<Integer> encounteredNumbers = new HashSet<Integer>();
		for (int i:input){
			boolean added = encounteredNumbers.add(i);
			if(!added)
				System.out.print(i + " ");
		}
	}
	
	// if the dups are present more than twice, above algorithm will print dups multiple times
	public static void findDuplicateNumber3(){
		System.out.println("\nfindDuplicateNumber3");
		int[] input = {5,6,3,4,6,2,4,5,6,6,6};
		Set<Integer> encounteredNumbers = new HashSet<Integer>();
		Set<Integer> duplicateNumbers = new LinkedHashSet<Integer>(); // LinkedHashSet to keep in same order as encountered.
		for (int i:input){
			if (encounteredNumbers.contains(i)){
				duplicateNumbers.add(i);
			}
			encounteredNumbers.add(i);
		}
		System.out.println(duplicateNumbers);
	}
	
	// sorting first and then finding the dups
	public static void findDuplicateNumber4(){
		System.out.println("\nfindDuplicateNumber4");
		int[] input = {5,6,3,4,6,2,4,5,6,6,6,6};
		Arrays.sort(input);
		for (int i=1;i<=input.length-1;i++){
			if(input[i-1] == input[i]){
				System.out.print(input[i] + " ");
				i=i+1;
			}
		}
	}
	
	// if the dups are present more than twice, above algorithm will print dups multiple times
	public static void findDuplicateNumber5(){
		System.out.println("\nfindDuplicateNumber5");
		int[] input = {5,6,3,4,6,2,4,5,6,6,6,6};
		int lastDup = 0;
		Arrays.sort(input);
		for (int i=1;i<=input.length-1;i++){
			if(input[i-1] == input[i] && input[i] != lastDup){
				lastDup = input[i];
				System.out.print(input[i] + " ");
			}
		}
	}	
}
