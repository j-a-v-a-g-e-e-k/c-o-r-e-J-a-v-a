package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _071_ThenRunAsync
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		CompletableFuture<Void> task1 = CompletableFuture
				.supplyAsync(() -> {try{
					System.out.println(Thread.currentThread().getName() + ": firstTask");
					TimeUnit.SECONDS.sleep(2);    								
				} catch (Exception e){}
				return "1"; })
				//this task will be executed in a new thread from common ForkJoinPool
				.thenRunAsync(()-> {System.out.println(Thread.currentThread().getName() + ": secondTask ");
				});

		TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread().getName() + ": My future is complete");
	}
}
