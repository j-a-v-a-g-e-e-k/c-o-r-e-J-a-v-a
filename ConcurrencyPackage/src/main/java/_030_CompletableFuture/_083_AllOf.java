package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _083_AllOf
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
    	CompletableFuture<String> task1 = CompletableFuture
    			.supplyAsync(() -> {try{
    								TimeUnit.SECONDS.sleep(2);
    								System.out.println(Thread.currentThread().getName() + ": firstTask");    								    								
    								} catch (Exception e){}
    								return "1"; });
    	
    	CompletableFuture<String> task2 = CompletableFuture
    			.supplyAsync(() -> {try{
    								TimeUnit.SECONDS.sleep(3); 
									System.out.println(Thread.currentThread().getName() + ": secondTask");									   								
									} catch (Exception e){}
									return "2"; });
	
    	//You can just use this future to determine if all the supplied future have completed or not
    	//allOf() is a static function    	
    	CompletableFuture<Void> future = CompletableFuture.allOf(task1, task2);
    	
    	if (!future.isDone()) System.out.println("future is not complete yet");
    	System.out.println(task1.get());
    	System.out.println(task2.get());
    	System.out.println(future.get());
	}
}
