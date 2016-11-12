package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class _081_ThenCombineAsyncExecutor
{
    public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
    {
    	ExecutorService executor = Executors.newFixedThreadPool(5);
    	CompletableFuture<String> task1 = CompletableFuture
    			.supplyAsync(() -> {try{
    								System.out.println(Thread.currentThread().getName() + ": firstTask");
    								TimeUnit.SECONDS.sleep(2);    								
    								} catch (Exception e){}
    								return "1"; });
    	CompletableFuture<String> task2 = CompletableFuture
    			.supplyAsync(() -> {try{
									System.out.println(Thread.currentThread().getName() + ": secondTask");
									TimeUnit.SECONDS.sleep(3);    								
									} catch (Exception e){}
									return "2"; });

    	//input=yes, output=yes
    	CompletableFuture<String> result = task1.thenCombineAsync(task2, 
    			(x,y) -> {System.out.println(Thread.currentThread().getName() + ": thirdTask " + x+y);
    			return x+y;},
    			executor);
    	    	
    	System.out.println(Thread.currentThread().getName() + ": " + result.get());
    	executor.shutdown();
    }
}
