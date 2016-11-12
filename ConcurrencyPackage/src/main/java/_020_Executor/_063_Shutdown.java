package _020_Executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _063_Shutdown {	
	public static void main(String... args) {
	int corePoolSize    = 10;
	int maximumPoolSize = 10;
	int keepAliveTime   =  0;
	int maxWaitingTasks = 5;

	ThreadPoolExecutor es = new ThreadPoolExecutor(
	        corePoolSize, maximumPoolSize,
	        keepAliveTime, TimeUnit.SECONDS,
	        new ArrayBlockingQueue<Runnable>(maxWaitingTasks));	

		//only 15 tasks gets queued up. 10 picked up by 10 thread and 5 waiting in the queue
		for (int i = 0; i < 30; i++)
			es.submit(new SleepOneSecond());

		System.out.println("Queue length " + ((ThreadPoolExecutor) es).getQueue().size());
		//shutdown the executor, so remaining 15 tasks cannot be submitted
		es.shutdown();
		System.out.println("After shutdown");
	}
	
	static class SleepOneSecond implements Callable<Void> {
		@Override
		public Void call() throws Exception {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(1000);
			return null;
		}
	}
}
