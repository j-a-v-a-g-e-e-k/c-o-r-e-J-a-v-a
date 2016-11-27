/*
 executor.submit vs. executor.execute
 1. execute() simply starts the task without any further ado, whereas submit() returns a Future object to manage the task.
 2. You have to take precaution while using submit(). If any exception is thrown, it hides exception in the framework itself.
 */

package _020_Executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _061_ExceptionHandling {	
	
	public static void main(String args[]){
		_061_ExceptionHandling demo = new _061_ExceptionHandling();
		//check method call one at a time
//		demo.withRunnableInExecute(); //throws exception
//		demo.withRunnableInSubmit(); //no exception is thrown, which is a problem
//		demo.withCallableInSubmit(); //no exception is thrown, which is a problem
		demo.withRunnableInCustomSubmit(); //solution. get custom exception message	
		System.out.println("In main thread");
	}
	
	public void withRunnableInExecute(){
		ExecutorService service = Executors.newFixedThreadPool(1);
		service.execute(new Runnable(){
			public void run(){
				int a=4, b = 0;
				System.out.println("a and b="+a+":"+b);
				System.out.println("a/b:"+(a/b));
				System.out.println("Thread Name in Runnable after divide by zero:"+Thread.currentThread().getName());
			}
		});
		service.shutdown();
	}
	
	public void withRunnableInSubmit(){
		ExecutorService service = Executors.newFixedThreadPool(1);
		service.submit(new Runnable(){
			public void run(){
				int a=4, b = 0;
				System.out.println("a and b="+a+":"+b);
				System.out.println("a/b:"+(a/b));
				System.out.println("Thread Name in Runnable after divide by zero:"+Thread.currentThread().getName());
			}
		});
		service.shutdown();
	}
	
	public void withCallableInSubmit() {
//		ExtendedExecutor service = new ExtendedExecutor();
		ExecutorService service = Executors.newFixedThreadPool(1);
		Future<Object> future = service.submit(new Callable<Object>(){
			public Object call(){
				int a=4, b = 0;
				System.out.println("a and b="+a+":"+b);
				System.out.println("a/b:"+(a/b));
				System.out.println("Thread Name in Runnable after divide by zero:"+Thread.currentThread().getName());
				return null;
			}
		});
		service.shutdown();
	}

	public void withRunnableInCustomSubmit() {
		ExtendedExecutor service = new ExtendedExecutor();
		service.submit(new Runnable(){
			public void run(){
				int a=4, b = 0;
				System.out.println("a and b="+a+":"+b);
				System.out.println("a/b:"+(a/b));
				System.out.println("Thread Name in Runnable after divide by zero:"+Thread.currentThread().getName());
			}
		});
		service.shutdown();
	}
}
	
class ExtendedExecutor extends ThreadPoolExecutor {
	public ExtendedExecutor() { 
		super(1,1,60,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(100));
	}
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		if (t == null && r instanceof Future<?>) {
			try {
				Object result = ((Future<?>) r).get();
			} catch (CancellationException ce) {
				t = ce;
			} catch (ExecutionException ee) {
				t = ee.getCause();
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt(); // ignore/reset
			}
		}
		if (t != null)
			System.out.println(t); // this is how exception is printed
	}
}
