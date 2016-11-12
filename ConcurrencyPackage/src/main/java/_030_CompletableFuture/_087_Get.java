package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _087_Get
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		CompletableFuture<Integer> task1 = CompletableFuture
				.supplyAsync(() -> {try{
					System.out.println(Thread.currentThread().getName() + ": task1");					   								
				} catch (Exception e){}
				return 10;
				});	
		TimeUnit.SECONDS.sleep(2); 
		System.out.println(Thread.currentThread().getName() + ": " + task1.get());
		
		
		CompletableFuture<Integer> task2 = CompletableFuture
				.supplyAsync(() -> {try{
					System.out.println(Thread.currentThread().getName() + ": task2");					    								
				} catch (Exception e){}
				return 10;
				});
		TimeUnit.SECONDS.sleep(2);
		//getNow() method doesn't block if the future 
		//is not completed yet, returns default value. Useful when building robust systems where we don't want to wait too much.

		System.out.println(Thread.currentThread().getName() + ": " + task2.getNow(11));
		
		
		CompletableFuture<Integer> task3 = CompletableFuture
				.supplyAsync(() -> {try{
					System.out.println(Thread.currentThread().getName() + ": task3");					    								
				} catch (Exception e){}
				return 10;
				});	
		TimeUnit.SECONDS.sleep(2);
//		task3.get(1, TimeUnit.SECONDS);
		System.out.println(Thread.currentThread().getName() + ": " + task3.isCompletedExceptionally());
		
		
		CompletableFuture<Integer> task4 = CompletableFuture
				.supplyAsync(() -> {try{
					System.out.println(Thread.currentThread().getName() + ": task4");					  								
					TimeUnit.SECONDS.sleep(2);  
				} catch (Exception e){}
				return 10;
				});				
		task4.complete(11);
		System.out.println(Thread.currentThread().getName() + ": " + task4.get());
	}
}
