/*
Can I use Callable threads without ExecutorService?
Since there is no constructor Thread(Callable) using a Callable with a Thread without an ExecutorService requires slightly more code
 */

package _020_Executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable implements Callable<Long> {
	private final long countUntil;
	MyCallable(long countUntil) {
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

public class _002_Callable {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<Long>> list = new ArrayList<Future<Long>>();
		for (int i = 0; i < 200; i++) {
			Callable<Long> worker = new MyCallable(i);
			Future<Long> submit = executor.submit(worker);
			list.add(submit);
		}
		long sum = 0;
		System.out.println(list.size());
		// now retrieve the result
		for (Future<Long> future : list) {
			sum += future.get();
		}
		System.out.println(sum);
		executor.shutdown();
	}
}