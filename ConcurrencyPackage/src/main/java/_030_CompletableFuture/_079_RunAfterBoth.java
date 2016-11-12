package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class _079_RunAfterBoth
{
    public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
    {
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
    	CompletableFuture<Void> future = task1.runAfterBoth(task2, 
    						() -> {System.out.println(Thread.currentThread().getName() + ": thirdTask ");});
    	
    	System.out.println(Thread.currentThread().getName() + ": " + future.get());
    }
}
