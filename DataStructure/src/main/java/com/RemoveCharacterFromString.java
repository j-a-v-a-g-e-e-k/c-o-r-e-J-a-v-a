package com;

public class RemoveCharacterFromString {
	
	public static void main(String [] args){
		String str = "Bimal";
		
		//Using String API
		System.out.println(str.replaceAll("a", ""));
		
		// Without using String API
		System.out.println(removeCharInString(str, 'a'));
		
	}

	public static String removeCharInString(String str, char c){
		StringBuilder strBuild = new StringBuilder();
		for(int i = 0; i<str.length() ; i++){
			char ch = str.charAt(i);
			if (ch == c) continue;
			strBuild.append(ch);
		}
		return strBuild.toString();
	}
	
}
