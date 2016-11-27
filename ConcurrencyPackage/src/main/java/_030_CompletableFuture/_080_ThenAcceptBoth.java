package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class _080_ThenAcceptBoth
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
    	//thread which finishes last will execute third task
    	task1.thenAcceptBoth(task2, (x,y) -> {System.out.println(Thread.currentThread().getName() + ": thirdTask " + x+y);});

    	TimeUnit.SECONDS.sleep(5);
    	
    	System.out.println(Thread.currentThread().getName() + ": My future is complete");
    }
}

/*
 I hope I'm wrong but maybe some of you are asking themselves a question: why can't I simply block on these two futures? Like here:
	
	Future<Customer> customerFuture = loadCustomerDetails(123);
	Future<Shop> shopFuture = closestShop();
	findRoute(customerFuture.get(), shopFuture.get());
	Well, of course you can. But the whole point of CompletableFuture  is to allow asynchronous, 
	event driven programming model instead of blocking and eagerly waiting for result. So functionally two code snippets above are equivalent, 
	but the latter unnecessarily occupies one thread of execution.
*/
