package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _083_WhenComplete
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		CompletableFuture<Integer> task1 = CompletableFuture
				.supplyAsync(() -> {
					try{
						System.out.println(Thread.currentThread().getName() + ": firstTask");
						TimeUnit.SECONDS.sleep(2);    								
					} catch (Exception e){}
					return 10/2;})
				//input=YES(both), output=NO
				.whenComplete((ok,ex)-> {
					if (ok != null)
						System.out.println(Thread.currentThread().getName() + ": secondTask " + ok);
					else if (ex != null)
						System.out.println(Thread.currentThread().getName() + ": secondTask " + ex);
				});

		TimeUnit.SECONDS.sleep(5);

		System.out.println(Thread.currentThread().getName() + ": My future is complete");
		
		
		CompletableFuture<Integer> task2 = CompletableFuture
				.supplyAsync(() -> {
					try{
						System.out.println(Thread.currentThread().getName() + ": firstTask");
						TimeUnit.SECONDS.sleep(2);    								
					} catch (Exception e){}
					return 10/0;})
				//input=YES(both), output=NO
				.whenComplete((ok,ex)-> {
					if (ok != null)
						System.out.println(Thread.currentThread().getName() + ": secondTask " + ok);
					else if (ex != null)
						System.out.println(Thread.currentThread().getName() + ": secondTask " + ex);
				});

		TimeUnit.SECONDS.sleep(5);

		System.out.println(Thread.currentThread().getName() + ": My future is complete");
	}
}
