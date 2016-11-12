package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

public class FrequencyOfWords {

	public static void main(String[] args) throws IOException {
		String file = "C:\\demo.txt";
		countWordsWithStringTokenizer(file);
		countWordsWithStringSplit(file);
	}

	// using StringTokenizer
	public static void countWordsWithStringTokenizer(String file) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
		String currentLine = null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		while ((currentLine = reader.readLine()) != null){
			StringTokenizer token = new StringTokenizer(currentLine, " ");
			while (token.hasMoreTokens()){
				String currentWord = token.nextToken();
				Integer frequency = map.get(currentWord);
				if (frequency == null){
					frequency = 1;
				} else {
					frequency = frequency + 1;
				}
				map.put(currentWord, frequency);					
			}
		}
		System.out.println(map);
	}
	
	// using String.split()
	public static void countWordsWithStringSplit(String file) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
		String currentLine = null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		while ((currentLine = reader.readLine()) != null){
			for (String s : currentLine.split(" ")){
				Integer frequency = map.get(s);
				if (frequency == null){
					frequency = 1;
				} else {
					frequency = frequency + 1;
				}
				map.put(s, frequency);
			}
		}
		System.out.println(map);
	}	
}
