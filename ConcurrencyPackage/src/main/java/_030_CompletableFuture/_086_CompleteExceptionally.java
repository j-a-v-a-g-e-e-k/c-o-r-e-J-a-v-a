package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _086_CompleteExceptionally
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		CompletableFuture<Integer> task1 = CompletableFuture
				.supplyAsync(() -> {
					try{
						TimeUnit.SECONDS.sleep(2); 
						System.out.println(Thread.currentThread().getName() + ": firstTask");						   								
					} catch (Exception e){}
					return 10;});
		
		//If the future is not completed yet, this will complete it exceptionally.
		//If the future has already completed, this will have no effect.
		boolean result = task1.completeExceptionally(new Exception());
	
		TimeUnit.SECONDS.sleep(5);
		
		System.out.println(Thread.currentThread().getName() + ": " + result);
		//this throws exception which we passed above
//		System.out.println(Thread.currentThread().getName() + ": " + task1.get());
		System.out.println(Thread.currentThread().getName() + ": " + task1.isCompletedExceptionally());
		System.out.println(Thread.currentThread().getName() + ": " + task1.isDone());
		System.out.println(Thread.currentThread().getName() + ": " + task1.isCancelled());
		
		

		CompletableFuture<Integer> task2 = CompletableFuture
				.supplyAsync(() -> {
					try{
						System.out.println(Thread.currentThread().getName() + ": firstTask");
					} catch (Exception e){}
					return 10;});

		//allow the task to finish
		TimeUnit.SECONDS.sleep(2); 
		boolean result2 = task2.completeExceptionally(new Exception());

		TimeUnit.SECONDS.sleep(5);

		System.out.println(Thread.currentThread().getName() + ": " + result2);
		System.out.println(Thread.currentThread().getName() + ": " + task2.get());
		System.out.println(Thread.currentThread().getName() + ": " + task2.isCompletedExceptionally());
		System.out.println(Thread.currentThread().getName() + ": " + task2.isDone());
		System.out.println(Thread.currentThread().getName() + ": " + task2.isCancelled());

	}
}
