package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _082_ThenComposeAsync
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		_082_ThenComposeAsync obj = new _082_ThenComposeAsync();
		
		CompletableFuture<String> task1 = CompletableFuture
				.supplyAsync(()->{try{
					TimeUnit.SECONDS.sleep(2); 
					System.out.println(Thread.currentThread().getName() + ": firstTask");					   								
				} catch (Exception e){}
				return "1"; });

		//input=yes, output=yes
		//but this does not return CompletableFuture<CompletableFuture<String>>. Thats the beauty of thenComose()
		CompletableFuture<String> result = task1.thenComposeAsync(obj::secondTask);

		System.out.println(Thread.currentThread().getName() + ": " + result.get());
		System.out.println(Thread.currentThread().getName() + ": My future is complete");
	}

	// this returns a completable future object
	private CompletableFuture<String> secondTask(String y) {
		try{
			TimeUnit.SECONDS.sleep(1);
			System.out.println(Thread.currentThread().getName() + ": secondTask");			
		} catch (Exception e){}
		return CompletableFuture.supplyAsync( ( ) -> y);
	}
}
