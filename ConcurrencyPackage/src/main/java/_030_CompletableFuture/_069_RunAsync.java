/*
CompletableFuture<T> extends Future<T> by providing functional, monadic (!) operations and promoting asynchronous, event-driven programming 
model, as opposed to blocking in older Java.

runAsync() and supplyAsync() by default uses ForkJoinPool.commonPool(), thread pool shared between all CompletableFutures, all parallel streams 
and all applications deployed on the same JVM. This hard-coded, unconfigurable thread pool is completely outside of our control, hard to 
monitor and scale. Therefore you should always specify your own Executor. Another reason can be, if some of the transformations are 
time-consuming, you can supply your own Executor to run them asynchronously.
 */
package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _069_RunAsync
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		CompletableFuture<Void> task1 = CompletableFuture
				//runAsync() takes runnable. it returns CompletableFuture<Void> as Runnable doesn't return anything
				//runAsync() method will immediately return, giving CompletableFuture object to the calling thread.
				//get() method can be called later on completableFuture object to get the result. 
				.runAsync(() -> {try{
					System.out.println(Thread.currentThread().getName() + ": firstTask");
					TimeUnit.SECONDS.sleep(2);    								
				} catch (Exception e){}
				});				

		//sleep will make sure that main thread does not finish before task1 is over, otherwise you will not see the sysout statements.
		TimeUnit.SECONDS.sleep(3);

		//get() method blocks the main thread until the result is available
		//task1.get();
		System.out.println(Thread.currentThread().getName() + ": My future is complete");
	}
}

