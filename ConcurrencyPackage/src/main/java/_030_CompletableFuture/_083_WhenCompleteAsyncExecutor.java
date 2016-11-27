package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _083_WhenCompleteAsyncExecutor
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		ExecutorService executor = Executors.newFixedThreadPool(5);
		CompletableFuture<Integer> task1 = CompletableFuture
				//output=YES
				.supplyAsync(() -> {
					try{
						TimeUnit.SECONDS.sleep(2); 
						System.out.println(Thread.currentThread().getName() + ": firstTask");						   								
					} catch (Exception e){}
					return 10/0;})
				//input=YES(both), output=NO
				.whenCompleteAsync((x,e)-> {
					if (x != null)
						System.out.println(Thread.currentThread().getName() + ": secondTask " + x);
					else if (e != null)
						System.out.println(Thread.currentThread().getName() + ": secondTask " + e);},
				executor);
	

		TimeUnit.SECONDS.sleep(5);

		System.out.println(Thread.currentThread().getName() + ": My future is complete");
		executor.shutdown();
	}
}
