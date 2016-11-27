package _020_Executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable4 implements Callable<Long> {
	private final long countUntil;
	MyCallable4(long countUntil) {
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

public class _023_NewWorkStealingPool {
	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
		//with parallelism=2
		ExecutorService executor = Executors.newWorkStealingPool(2);
		List<Future<Long>> list = new ArrayList<Future<Long>>();
		for (int i = 0; i < 100; i++) {
			Callable<Long> worker = new MyCallable4(i);
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