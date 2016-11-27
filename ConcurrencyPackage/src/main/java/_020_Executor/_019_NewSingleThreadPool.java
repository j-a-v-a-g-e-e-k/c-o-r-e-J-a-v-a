/*
Creates an Executor that uses a single worker thread operating off an unbounded queue. (Note however that if this single thread terminates due to 
a failure during execution prior to shutdown, a new one will take its place if needed to execute subsequent tasks.) Tasks are guaranteed to execute 
sequentially, and no more than one task will be active at any given time. Unlike the otherwise equivalent newFixedThreadPool(1) the returned executor 
is guaranteed not to be reconfigurable to use additional threads.

public static ExecutorService newSingleThreadExecutor() {
     return new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())); }
 */

package _020_Executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable6 implements Callable<Long> {
	private final long countUntil;
	MyCallable6(long countUntil) {
		this.countUntil = countUntil;
	}
	public Long call() throws Exception {
		long sum = 0;
		for (long i = 0; i <= 100+countUntil; i++) {
			sum += i;
		}
		System.out.println(Thread.currentThread().getName() + ": " + sum);
		return sum;
	}
}

public class _019_NewSingleThreadPool {
	public static void main(String[] args) {
		//result will appear in sequence
		ExecutorService executor = Executors.newSingleThreadExecutor();
		List<Future<Long>> list = new ArrayList<Future<Long>>();
		for (int i = 0; i < 400; i++) {
			Callable<Long> worker = new MyCallable6(i);
			Future<Long> submit = executor.submit(worker);
			list.add(submit);
		}
		long sum = 0;
		System.out.println(list.size());
		// now retrieve the result
		for (Future<Long> future : list) {
			try {
				sum += future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sum);
		executor.shutdown();
	}
}
