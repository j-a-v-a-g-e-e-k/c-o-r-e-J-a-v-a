package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _090_ObtrudeValue
{
	public static void main( String[] args ) throws InterruptedException, ExecutionException, TimeoutException
	{
		CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {return 10;});	
		System.out.println(Thread.currentThread().getName() + ": " + task1.get());
		task1.obtrudeValue(11);
		System.out.println(Thread.currentThread().getName() + ": " + task1.get());
		
		CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {return 10/0;});
//		System.out.println(Thread.currentThread().getName() + ": " + task2.get());
		task2.obtrudeValue(999);
		System.out.println(Thread.currentThread().getName() + ": " + task2.get());
	}
}
