package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _073_ThenApplySyncExecutor
{
    public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
    {
    	ExecutorService executor = Executors.newFixedThreadPool(3);
    	CompletableFuture<Integer> task1 = CompletableFuture
    			.supplyAsync(() -> {try{
    								System.out.println(Thread.currentThread().getName() + ": firstTask");
    								TimeUnit.SECONDS.sleep(2);    								
    								} catch (Exception e){}
    								return "1"; })
    			.thenApplyAsync((x)-> {System.out.println(Thread.currentThread().getName() + ": secondTask " + x);
    								return Integer.parseInt(x);},
    								executor);
    	
    	TimeUnit.SECONDS.sleep(5); 
    	System.out.println(Thread.currentThread().getName() + ": " + task1.get());
    	executor.shutdown();
    }
}
