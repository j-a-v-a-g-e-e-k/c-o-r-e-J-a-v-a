package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _082_ThenCompose
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		_082_ThenCompose obj = new _082_ThenCompose();
		
		CompletableFuture<String> task1 = CompletableFuture
				.supplyAsync(()->{try{
					TimeUnit.SECONDS.sleep(2);
					System.out.println(Thread.currentThread().getName() + ": firstTask");					    								
				} catch (Exception e){}
				return "1"; });
		CompletableFuture<CompletableFuture<String>> result2 = task1.thenApply(obj::secondTask);
		//but this does not return CompletableFuture<CompletableFuture<String>>. Thats the beauty of thenComose()
		CompletableFuture<String> result = task1.thenCompose(obj::secondTask);		

		System.out.println(Thread.currentThread().getName() + ": " + result.get());
		System.out.println(Thread.currentThread().getName() + ": My future is complete");
	}

	// this returns a completable future object
	private CompletableFuture<String> secondTask(String y) {
		try{
			TimeUnit.SECONDS.sleep(1);
			System.out.println(Thread.currentThread().getName() + ": secondTask " + y);			
		} catch (Exception e){}
		return CompletableFuture.supplyAsync( ( ) -> y);
	}
}
