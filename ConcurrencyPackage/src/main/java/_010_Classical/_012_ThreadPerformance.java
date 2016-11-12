/*
--PURPOSE: Compute time taken to complete tasks: thread creation vs thread pool vs same thread(main)
 */
package _010_Classical;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class _012_ThreadPerformance {

	public static void main(String[] kr) throws InterruptedException {
		final BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		Runnable task = new Runnable() {
			@Override
			public void run() {
				queue.add(1);
			}
		};

		for (int t = 0; t < 3; t++) {
			{// via thread creation
				List<Thread> threads = new ArrayList<Thread>();
				long start = System.nanoTime();
				int runs = 20000;
				for (int i = 0; i < runs; i++){
					Thread worker = new Thread(task);
					threads.add(worker);
				}
				long time = System.nanoTime() - start;
				System.out.printf("Time to create a new Thread %.1f us%n", time / runs / 1000.0);
				for (int i = 0; i < runs; i++)
					//start method takes most time.
					threads.get(i).start(); 
				for (int i = 0; i < runs; i++)
					queue.take();
				long time2 = System.nanoTime() - start;
				System.out.printf("Time for a task to complete in a new Thread %.1f us%n", time2 / runs / 1000.0);
			}
			{
				int threads = Runtime.getRuntime().availableProcessors();
				ExecutorService es = Executors.newFixedThreadPool(threads);
				long start = System.nanoTime();
				int runs = 200000;
				for (int i = 0; i < runs; i++)
					es.execute(task);
				for (int i = 0; i < runs; i++)
					queue.take();
				long time = System.nanoTime() - start;
				System.out.printf("Time for a task to complete in a thread pool %.2f us%n", time / runs / 1000.0);
				es.shutdown();
			}
			{
				long start = System.nanoTime();
				int runs = 200000;
				for (int i = 0; i < runs; i++)
					task.run();
				for (int i = 0; i < runs; i++)
					queue.take();
				long time = System.nanoTime() - start;
				System.out.printf("Time for a task to complete in the same thread %.2f us%n", time / runs / 1000.0);
			}
		}
	}
}