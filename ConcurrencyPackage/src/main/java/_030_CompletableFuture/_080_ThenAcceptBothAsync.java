package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class _080_ThenAcceptBothAsync
{
    public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
    {
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

    	task1.thenAcceptBothAsync(task2, (x,y) -> {System.out.println(Thread.currentThread().getName() + ": thirdTask " + x+y);});

    	TimeUnit.SECONDS.sleep(5);
    	
    	System.out.println(Thread.currentThread().getName() + ": My future is complete");
    }
}
