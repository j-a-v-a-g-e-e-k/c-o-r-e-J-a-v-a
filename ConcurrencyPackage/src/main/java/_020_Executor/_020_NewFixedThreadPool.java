/*
Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue.

public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
} //queue is unbounded
    
If you want to process all submitted tasks in order of arrival, just use newFixedThreadPool(1)
*/

package _020_Executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable5 implements Callable<Long> {
	private final long countUntil;
	MyCallable5(long countUntil) {
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

public class _020_NewFixedThreadPool {
	public static void main(String[] args) {
		//result will not follow any sequence because of 10 threads
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<Long>> list = new ArrayList<Future<Long>>();
		for (int i = 0; i < 400; i++) {
			Callable<Long> worker = new MyCallable5(i);
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