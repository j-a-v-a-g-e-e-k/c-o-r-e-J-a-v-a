/*
--Executor may execute command in a 
• new thread, or
• in a pooled thread, or 
• in the calling thread
at the discretion of the Executor implementation in java.

 */

package _020_Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyRunnable implements Runnable {
	private final long countUntil;
	MyRunnable(long countUntil) {
		this.countUntil = countUntil;
	}
	public void run() {
		long sum = 0;
		for (long i = 0; i <= 100+countUntil; i++) {
			sum += i;
		}
		System.out.println(Thread.currentThread().getName() + ": " + sum);
	}
}

public class _001_Runnable {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 100; i++) {
			Runnable worker = new MyRunnable(i);
			/*
 			incase of runnable, we can call both execute() and submit() methods
			executor.execute(task); //returns nothing
			Future<?> submit = executor.submit(worker); //return future object
			 */
			executor.execute(worker);			
		}
		executor.shutdown();
		/*
		Executors have to be stopped explicitly - otherwise they keep listening for new tasks.
		shutdown() will make the executor accept no new threads and finish all existing threads in the queue. 		

		executor.shutdownNow();
		shutdownNow() interrupts all running tasks and shut the executor down immediately.
		every non-terminated future will throw exceptions if you call shutdownNow()
		
		executor.awaitTermination(5, TimeUnit.SECONDS);
		The executor shuts down softly by waiting a certain amount of time for termination of currently running tasks. 
		After a maximum of five seconds the executor finally shuts down by interrupting all running tasks.		
		*/
	}
}
