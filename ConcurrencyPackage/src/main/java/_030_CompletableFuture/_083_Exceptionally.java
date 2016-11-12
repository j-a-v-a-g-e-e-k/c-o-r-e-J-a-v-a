package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _083_Exceptionally
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		CompletableFuture<Integer> task1 = CompletableFuture
				.supplyAsync(() -> {
					try{
						System.out.println(Thread.currentThread().getName() + ": firstTask");
						TimeUnit.SECONDS.sleep(2);    								
					} catch (Exception e){}
					return 10/0;})
				//input=YES(only if exception is thrown), output=YES(only if exception is thrown)
				//We then have an opportunity to recover by transforming this exception into some value compatible with Future's type.
				.exceptionally((ex)-> {
						System.out.println(Thread.currentThread().getName() + ": secondTask " + ex);
						return 999;
				});

		TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread().getName() + ": " + task1.get());
		
		CompletableFuture<Integer> task2 = CompletableFuture
				.supplyAsync(() -> {
					try{
						System.out.println(Thread.currentThread().getName() + ": firstTask");
						TimeUnit.SECONDS.sleep(2);    								
					} catch (Exception e){}
					return 10/2;})
				//input=YES(both), output=NO
				.exceptionally((ex)-> {
						System.out.println(Thread.currentThread().getName() + ": secondTask " + ex);
						return 999;
				});

		TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread().getName() + ": " + task2.get());
	}
}
