package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _083_WhenCompleteAsync
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		CompletableFuture<Integer> task1 = CompletableFuture
				//output=YES
				.supplyAsync(() -> {
					try{
						TimeUnit.SECONDS.sleep(2);
						System.out.println(Thread.currentThread().getName() + ": firstTask");						    								
					} catch (Exception e){}
					return 10/0;})
				//input=YES(both), output=NO
				.whenCompleteAsync((ok,ex)-> {
					if (ok != null)
						System.out.println(Thread.currentThread().getName() + ": secondTask " + ok);
					else if (ex != null)
						System.out.println(Thread.currentThread().getName() + ": secondTask " + ex);
				});

		TimeUnit.SECONDS.sleep(5);

		System.out.println(Thread.currentThread().getName() + ": My future is complete");
	}
}
