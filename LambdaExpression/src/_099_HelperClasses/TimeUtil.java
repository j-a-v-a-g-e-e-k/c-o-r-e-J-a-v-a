package _099_HelperClasses;

public class TimeUtil {
	public static void timeIt(Runnable block){
		long start = System.nanoTime();
		block.run();
		long end = System.nanoTime();
		System.out.println("Time taken: " + (end-start)/1.0e9);		
	}
}
