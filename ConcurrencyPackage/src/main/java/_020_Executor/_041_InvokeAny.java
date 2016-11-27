//Another way of batch-submitting callables is the method invokeAny() which works slightly different to invokeAll(). 
//Instead of returning future objects this method blocks until the first callable terminates and returns the result of that callable.
package _020_Executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class _041_InvokeAny {
	
	public Callable<String> callable(String result, long sleepSeconds) {
	    return () -> {
	        TimeUnit.SECONDS.sleep(sleepSeconds);
	        return result;
	    };
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		_041_InvokeAny x = new _041_InvokeAny();
		ExecutorService executor = Executors.newFixedThreadPool(10);

		List<Callable<String>> callables = Arrays.asList(
		    x.callable("task1", 2),
		    x.callable("task2", 1),
		    x.callable("task3", 3));
		String result = executor.invokeAny(callables);
		System.out.println(result);
		executor.shutdown();
	}
}
