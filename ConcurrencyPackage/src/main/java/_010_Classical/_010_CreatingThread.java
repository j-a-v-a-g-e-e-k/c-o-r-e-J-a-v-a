/*
--PURPOSE: create 500 threads to complete 500 tasks. 

--No control over thread creation
--Due to concurrent execution we cannot predict the order in which these 500 tasks will be executed.
 */
package _010_Classical;

import java.util.ArrayList;
import java.util.List;

class MyRunnable implements Runnable {
	private final long countUntil;
	MyRunnable(long countUntil) {
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

public class _010_CreatingThread {
	public static void main(String[] args) {
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 500; i++) {
			Runnable task = new MyRunnable(100 + i);
			Thread worker = new Thread(task);
			worker.setName(String.valueOf(i));
			worker.start();
			threads.add(worker);
		}

		int running = 0;
		do {
			running = 0;
			for (Thread thread : threads) {
				if (thread.isAlive()) {
					running++;
				}
			}
			System.out.println("We have " + running + " running threads. ");
		} while (running > 0);
	}
}


