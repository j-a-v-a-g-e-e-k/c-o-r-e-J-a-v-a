package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _088_Cancel
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		CompletableFuture<Integer> task1 = CompletableFuture
				//output=Yes
				.supplyAsync(() -> {try{
					System.out.println(Thread.currentThread().getName() + ": firstTask");
					TimeUnit.SECONDS.sleep(5);    								
				} catch (Exception e){}
				return 10;
				});				

		task1.cancel(true);
		System.out.println(Thread.currentThread().getName() + ": " + task1.isCancelled());
		System.out.println(Thread.currentThread().getName() + ": " + task1.isCompletedExceptionally());
		System.out.println(Thread.currentThread().getName() + ": " + task1.isDone());

		
		
		CompletableFuture<Integer> task2 = CompletableFuture
				//output=Yes
				.supplyAsync(() -> {try{
					System.out.println(Thread.currentThread().getName() + ": secondTask");
					TimeUnit.SECONDS.sleep(2);    								
				} catch (Exception e){}
				return 10;
				});				

		task2.cancel(false);
		System.out.println(Thread.currentThread().getName() + ": " + task2.isCancelled());
		System.out.println(Thread.currentThread().getName() + ": " + task2.isCompletedExceptionally());
		System.out.println(Thread.currentThread().getName() + ": " + task2.isDone());
	}
}
