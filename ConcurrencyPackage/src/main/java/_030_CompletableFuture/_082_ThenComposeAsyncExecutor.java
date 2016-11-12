package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _082_ThenComposeAsyncExecutor
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		_082_ThenComposeAsyncExecutor obj = new _082_ThenComposeAsyncExecutor();
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		CompletableFuture<String> task1 = CompletableFuture
				.supplyAsync(()->{try{
					System.out.println(Thread.currentThread().getName() + ": firstTask");
					TimeUnit.SECONDS.sleep(2);    								
				} catch (Exception e){}
				return "1"; });

		//input=yes, output=yes
		//but this does not return CompletableFuture<CompletableFuture<String>>. Thats the beauty of thenComose()
		CompletableFuture<String> result = task1.thenComposeAsync(obj::secondTask, executor);

		System.out.println(Thread.currentThread().getName() + ": " + result.get());
		System.out.println(Thread.currentThread().getName() + ": My future is complete");
		executor.shutdown();
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
