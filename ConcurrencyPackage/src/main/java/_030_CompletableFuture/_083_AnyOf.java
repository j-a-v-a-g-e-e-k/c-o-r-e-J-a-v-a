package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _083_AnyOf
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
    	//Returns a new CompletableFuture that is completed when any of the given CompletableFutures complete, with the same result. 
    	//output=Object only(cannot be string). 
    	//anyOf() is a static function
    	CompletableFuture<Object> future = CompletableFuture.anyOf(task1, task2);

    	if (!future.isDone()) System.out.println("future is not complete yet");
    	System.out.println(task1.get());
    	System.out.println(task2.get());
    	System.out.println(future.get());
	}
}
