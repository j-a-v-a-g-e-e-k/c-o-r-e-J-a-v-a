/*
--PURPOSE: complete 500 tasks with just 10 threads in a pool

 */
package _010_Classical;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyRunnable3 implements Runnable {
	private final long countUntil;

	MyRunnable3(long countUntil) {
		this.countUntil = countUntil;
	}

	public void run() {
		long sum = 0;
		for (long i = 1; i < countUntil; i++) {
			sum += i;
		}
		System.out.println(Thread.currentThread().getName() + ": " + sum);
	}
}

public class _011_WithExecutor {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 500; i++) {
			Runnable task = new MyRunnable3(100+i);
			Future<?> future = executor.submit(task); //return future
		}		
		executor.shutdown();
		System.out.println("Finished all threads");
	}
}
