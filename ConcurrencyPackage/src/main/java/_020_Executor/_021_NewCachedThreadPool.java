/*
 Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available, 
 and uses the provided ThreadFactory to create new threads when needed.

 public static ExecutorService newCachedThreadPool() {
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
 }

 The problem with a cached thread pool is that it doesn’t know when to stop spawning more and more threads. Imagine the situation where 
 you have computationally intensive tasks that you submit into this executor. The more threads that consuming the CPU, the slower every 
 individual task takes to process. This has a domino effect in that it means more work gets backlogged. As the result you’ll end up with 
 more and more threads spawned making task processing even slower. This negative feedback loop is a hard problem to solve. So for almost 
 all intents and purposes, Executors::newFixedThreadPool(int nThreads) should be your goto choice for when you need a thread pool. For 
 computationally intensive tasks it will probably give you close to optimal throughput and for IO heavy tasks you won’t be that much worse 
 than anything else. At least until you profile that you got a problem with this kind of executor, you shouldn’t mindlessly try to optimize it.
*/

package _020_Executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable2 implements Callable<Long> {
	private final long countUntil;
	MyCallable2(long countUntil) {
		this.countUntil = countUntil;
	}
	public Long call() throws Exception {
		long sum = 0;
		for (long i = 0; i <= 100 + countUntil; i++) {
			sum += i;
		}
		System.out.println(Thread.currentThread().getName() + ": " + sum);
		return sum;
	}
}

public class _021_NewCachedThreadPool {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		List<Future<Long>> list = new ArrayList<Future<Long>>();
		for (int i = 0; i < 100; i++) {
			Callable<Long> worker = new MyCallable2(i);
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