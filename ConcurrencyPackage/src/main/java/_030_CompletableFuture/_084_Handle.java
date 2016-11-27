package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _084_Handle
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		CompletableFuture<Integer> task1 = CompletableFuture
				.supplyAsync(() -> {
					try{
						TimeUnit.SECONDS.sleep(2); 
						System.out.println(Thread.currentThread().getName() + ": firstTask");						   								
					} catch (Exception e){}
					return 10/2;})
				//input=YES(both), output=YES
				.handle((ok,ex)-> {
					if (ok != null){
						System.out.println(Thread.currentThread().getName() + ": secondTask " + ok);
						return ok;
					}
					else if (ex != null){
						System.out.println(Thread.currentThread().getName() + ": secondTask " + ex);
						return null;
					}
					return null;
				});
		TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread().getName() + ": " + task1.get());
		
		CompletableFuture<Integer> task2 = CompletableFuture
				.supplyAsync(() -> {
					try{
						TimeUnit.SECONDS.sleep(2); 
						System.out.println(Thread.currentThread().getName() + ": firstTask");						   								
					} catch (Exception e){}
					return 10/0;})
				//input=YES(both), output=YES
				.handle((ok,ex)-> {
					if (ok != null){
						System.out.println(Thread.currentThread().getName() + ": secondTask " + ok);
						return ok;
					}
					else if (ex != null){
						System.out.println(Thread.currentThread().getName() + ": secondTask " + ex);
						return null;
					}
					return null;
				});
		TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread().getName() + ": " + task2.get());
	}
}
