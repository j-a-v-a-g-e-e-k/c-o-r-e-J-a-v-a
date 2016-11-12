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
					System.out.println(Thread.currentThread().getName() + ": firstTask");
					TimeUnit.SECONDS.sleep(2);    								
				} catch (Exception e){}
				return "1"; });

		//input=yes, output=yes
		//but this does not return CompletableFuture<CompletableFuture<String>>. Thats the beauty of thenComose()
		CompletableFuture<String> result = task1.thenCompose(obj::secondTask);

		System.out.println(Thread.currentThread().getName() + ": " + result.get());
		System.out.println(Thread.currentThread().getName() + ": My future is complete");
	}

	// this returns a completable future object
	private CompletableFuture<String> secondTask(String y) {
		try{
			System.out.println(Thread.currentThread().getName() + ": secondTask");
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e){}
		return CompletableFuture.supplyAsync( ( ) -> y);
	}
}