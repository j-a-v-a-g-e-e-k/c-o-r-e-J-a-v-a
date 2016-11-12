package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _072_ThenAcceptAsyncExecutor
{
    public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
    {
    	ExecutorService executor = Executors.newFixedThreadPool(3);
    	CompletableFuture<Void> task1 = CompletableFuture
    			.supplyAsync(() -> {try{
    								System.out.println(Thread.currentThread().getName() + ": firstTask");
    								TimeUnit.SECONDS.sleep(2);    								
    								} catch (Exception e){}
    								return "1"; })
    			.thenAcceptAsync((x)-> {System.out.println(Thread.currentThread().getName() + ": secondTask " + x);},
    							executor);

    	TimeUnit.SECONDS.sleep(5);
    	
    	System.out.println(Thread.currentThread().getName() + ": My future is complete");
    	executor.shutdown();
    }
}
