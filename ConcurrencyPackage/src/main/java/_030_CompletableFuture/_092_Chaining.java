package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class _092_Chaining {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		CompletableFuture<Void>  data = CompletableFuture
				.supplyAsync(() -> {
					try {
						System.out.println(Thread.currentThread().getName() + ": first task");
						// no delay
//						Thread.sleep(3000);
					} catch (Exception e) { }
					return 20; })
				.thenApply((Integer count) -> {
					System.out.println(Thread.currentThread().getName() + ": second task");
					int transformedValue = count * 10;
					return transformedValue;})
				.thenApply(transformed -> {
					System.out.println(Thread.currentThread().getName() + ": third task");	
					return "Finally creates a string: " + transformed;})
				.thenAccept(System.out::println)
				;
		
		System.out.println(Thread.currentThread().getName() + ": " + data.get());
		System.out.println("========================");
		
		CompletableFuture<Void>  data2 = CompletableFuture
				.supplyAsync(() -> {
					try {
						System.out.println(Thread.currentThread().getName() + ": first task");
						// simulate long running task
						Thread.sleep(3000);
					} catch (Exception e) { }
					return 20; })
				.thenApply((Integer count) -> {
					System.out.println(Thread.currentThread().getName() + ": second task");
					int transformedValue = count * 10;
					return transformedValue;})
				.thenApply(transformed -> {
					System.out.println(Thread.currentThread().getName() + ": third task");	
					return "Finally creates a string: " + transformed;})
				.thenAccept(System.out::println)
				;

		System.out.println(Thread.currentThread().getName() + ": " + data2.get());
	}
}


