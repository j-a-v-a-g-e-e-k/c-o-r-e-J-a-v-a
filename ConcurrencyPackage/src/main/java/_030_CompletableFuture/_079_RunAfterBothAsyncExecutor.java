package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _079_RunAfterBothAsyncExecutor
{
    public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
    {
    	ExecutorService executor = Executors.newFixedThreadPool(5);
    	CompletableFuture<String> task1 = CompletableFuture
    			.supplyAsync(() -> {try{
    								System.out.println(Thread.currentThread().getName() + ": firstTask");
    								TimeUnit.SECONDS.sleep(3);    								
    								} catch (Exception e){}
    								return "1"; });
    	CompletableFuture<String> task2 = CompletableFuture
    			.supplyAsync(() -> {try{
									System.out.println(Thread.currentThread().getName() + ": secondTask");
									TimeUnit.SECONDS.sleep(1);    								
									} catch (Exception e){}
									return "2"; });
    	
    	//input-NO, output-NO
    	CompletableFuture<Void> future = task1.runAfterBothAsync(task2, 
    						() -> {System.out.println(Thread.currentThread().getName() + ": thirdTask ");},
    						executor);
    	
    	System.out.println(Thread.currentThread().getName() + ": " + future.get());
    	executor.shutdown();
    }
}
