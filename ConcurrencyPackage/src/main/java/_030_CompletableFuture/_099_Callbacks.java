/*
 PURPOSE:
 Which Thread Executes CompletableFuture's Tasks and Callbacks?
 */

package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class _099_Callbacks {

	public static void main(String[] args) throws InterruptedException, ExecutionException{
		_099_Callbacks obj = new _099_Callbacks();
		System.out.println("callback1");obj.callback1();
		System.out.println("callback2");obj.callback2();
		System.out.println("callback3");obj.callback3();
	}


	public void callback1() throws InterruptedException, ExecutionException{
		ExecutorService executor = Executors.newSingleThreadExecutor();
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + ": original task");
			} catch (InterruptedException e) {
				e.printStackTrace();
			};
			return "ABC";
		}, executor);

		future.thenApply(s -> {
			System.out.println(Thread.currentThread().getName() + ": First transformation");
			return s.length();
		});
		
		future.get();
		executor.shutdownNow();
		executor.awaitTermination(1, TimeUnit.MINUTES);

		future.thenApply(s -> {
			System.out.println(Thread.currentThread().getName() + ": Second transformation");
			return s.length();
		});
	}

	/*
	 The first transformation in thenApply() is registered while the task is still running. Thus it will be executed immediately after 
	 task completion in the same thread as the task. However before registering second transformation we wait until the task actually 
	 completes. Even worse, we shutdown the thread pool entirely, to make sure no other code can ever be executed there. So which thread 
	 will run second transformation? We know it must happen immediately since the future we register callback on already completed. It 
	 turns out that by default client thread is used! The output is as follows:

	pool-1-thread-1: original task
	pool-1-thread-1: First transformation
	main: Second transformation
	
	Second transformation, when registered, realizes that the CompletableFuture already finished, so it executes the transformation immediately.
	There is no other thread around so thenApply() is invoked in the context of current main thread. The biggest reason why this behavior is 
	error prone shows up when the actual transformation is costly. Imagine lambda expression inside thenApply() doing some heavy computation or 
	blocking network call. Suddenly our asynchronous CompletableFuture blocks calling thread!
	
	There are two techniques to control which thread executes our callbacks and transformations. Notice that these solutions are only needed 
	if your transformations are costly. Otherwise the difference is negligible. So first of all we can choose the *Async versions of operators
	~~~check callback2()
	This time the second transformation was automatically off-loaded to our friend, ForkJoinPool.commonPool():

	pool-2-thread-1: original task
	pool-2-thread-1: First transformation
	ForkJoinPool.commonPool-worker-1: Second transformation
	
	But we don't like commonPool so we supply our own executor:
	~~~check callback3()
	
	pool-3-thread-1: original task
	pool-3-thread-1: First transformation
	pool-4-thread-1: Second transformation
	 */

	public void callback2() throws InterruptedException, ExecutionException{
		ExecutorService executor = Executors.newSingleThreadExecutor();
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + ": original task");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			return "ABC";
		}, executor);

		future.thenApply(s -> {
			System.out.println(Thread.currentThread().getName() + ": First transformation");
			return s.length();
		});
		future.get();
		executor.shutdownNow();
		executor.awaitTermination(1, TimeUnit.MINUTES);

		future.thenApplyAsync(s -> {
			System.out.println(Thread.currentThread().getName() + ": Second transformation");
			return s.length();
		});
	}

	public void callback3() throws InterruptedException, ExecutionException{
		ExecutorService pool = Executors.newSingleThreadExecutor();
		ExecutorService pool2 = Executors.newSingleThreadExecutor();

		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + ": original task");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			return "ABC";
		}, pool);

		future.thenApply(s -> {
			System.out.println(Thread.currentThread().getName() + ": First transformation");
			return s.length();
		});
		future.get();
		pool.shutdownNow();
		pool.awaitTermination(1, TimeUnit.MINUTES);

		future.thenApplyAsync(s -> {
			System.out.println(Thread.currentThread().getName() + ": Second transformation");
			return s.length();
		},pool2);
	}

}

/*
 Treating Callback Like Another Computation Step
But I believe that if you are having troubles with long-running callbacks and transformations (remember that this article applies to almost all 
other methods on CompletableFuture), you should simply use another explicit CompletableFuture, like here:
	//Imagine this is slow and costly
	CompletableFuture<Integer> strLen(String s) {
	    return CompletableFuture.supplyAsync(
	            () -> s.length(),
	            pool2);
	}
	
	//...
	
	CompletableFuture<Integer> intFuture = 
	        future.thenCompose(s -> strLen(s));

This approach is more explicit. Knowing that our transformation has significant cost we don't risk running it on some arbitrary or uncontrolled 
thread. Instead we explicitly model it as asynchronous operation from String to CompletableFuture<Integer>. However we must replace thenApply() 
with thenCompose(), otherwise we'll end up with CompletableFuture<CompletableFuture<Integer>>.
 */
