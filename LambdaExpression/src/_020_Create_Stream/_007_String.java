package _020_Create_Stream;

import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class _007_String {
	public static void main(String[] args) {
		/*
		 Streams from Char Sequence
		 chars() from the CharSequence interface returns an IntStream whose elements are int values representing the characters.
		 We can use chars() method on a String, a StringBuilder, and a StringBuffer.
		 */		
		String str = "5 123,123,qwe,1,123, 25";
		IntStream s1 = str.chars(); //chars returns an IntStream of char values from this sequence
		s1.filter(n ->  !Character.isDigit((char)n) &&   !Character.isWhitespace((char)n))
		.forEach(n ->  System.out.print((char)n));

		System.out.println();
		/*
		 Streams from Regex
		 splitAsStream(CharSequence input) method from the java.util.regex.Pattern class returns a stream of String whose elements match the pattern.
		 */
		str = "XML,CSS,HTML"; 
		Pattern.compile(",")
		.splitAsStream(str)
		.forEach(System.out::println);
	}
}
