/*
 Executors support batch submitting of multiple callables at once via invokeAll(). 
 This method accepts a collection of callables and returns a list of futures.
 */
package _020_Executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _040_InvokeAll {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Callable<String>> callables = Arrays.asList(
				() -> "task1",
				() -> "task2",
				() -> "task3");
		executor.invokeAll(callables)
		.stream()
		.map(future -> {
			try {
				return future.get();
			}
			catch (Exception e) {
				throw new IllegalStateException(e);
			}
		})
		.forEach(System.out::println);
	}
}

