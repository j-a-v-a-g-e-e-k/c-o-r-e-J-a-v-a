/*
CompletableFuture allow registering asynchronous callbacks when upside future is completed. We don't have to wait and block until it's ready. 
We can simply say: run this function on a result, when it arrives
 */
package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _071_ThenRun
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		CompletableFuture<Void> task1 = CompletableFuture
				.supplyAsync(() -> {try{
					System.out.println(Thread.currentThread().getName() + ": firstTask");
					TimeUnit.SECONDS.sleep(2);    								
				} catch (Exception e){}
				return "1"; })
				//this task will be executed in the same thread as first task
				//thenRun() and thenAccept() methods do not block (even without explicit executor)
				.thenRun(()-> {System.out.println(Thread.currentThread().getName() + ": secondTask ");
				});

		TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread().getName() + ": My future is complete");
	}
}
