/*
 Stream#peek(), note that this method is recommended for logging purposes only. We shouldn't perform stateful operations or side effects in this function. 
 Here's the API note:

 This method exists mainly to support debugging, where you want to see the elements as they flow past a certain point in a pipeline
 */

package _099_Streams;

import java.time.LocalTime;
import java.util.stream.IntStream;

public class _021_Debug {

	   public static void main (String[] args) {
	        IntStream stream = IntStream.range(1, 5);
	        stream = stream.peek(i -> log("starting", i))
	                       .filter(i -> { log("filtering", i);
	                                      return i % 2 == 0;})
	                       .peek(i -> log("post filtering", i));
	        log("Invoking terminal method count.");
	        log("The count is", stream.count());
	    }
	   
	   public static void log (Object... objects) {
	        String s = LocalTime.now().toString();
	        for (Object object : objects) {
	            s += " - " + object.toString();
	        }
	        System.out.println(s);
	        // putting a little delay so that we can see a clear difference
	        // with parallel stream.
	        try {
	            Thread.sleep(1);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
}
