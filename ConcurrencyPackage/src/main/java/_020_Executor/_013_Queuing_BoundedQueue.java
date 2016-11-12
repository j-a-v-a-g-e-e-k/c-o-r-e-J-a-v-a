package _020_Executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _013_Queuing_BoundedQueue {	
	public static void main(String... args) {
	int corePoolSize    = 5;
	int maximumPoolSize = 10;
	int keepAliveTime   =  0;
	int maxWaitingTasks = 24;
	//with 31- only 5 thread will work, with 24- 6 thread will work, with 19-all 10 threads will work

	ThreadPoolExecutor es = new ThreadPoolExecutor(
	        corePoolSize, maximumPoolSize,
	        keepAliveTime, TimeUnit.SECONDS,
	        new ArrayBlockingQueue<Runnable>(maxWaitingTasks));	

		for (int i = 0; i < 30; i++)
			es.submit(new SleepOneSecond());

		System.out.println("Queue length " + ((ThreadPoolExecutor) es).getQueue().size());
		//shutdown the executor, so no more new tasks can be submitted
		es.shutdown();
		System.out.println("After shutdown");
	}
	
	static class SleepOneSecond implements Callable<Void> {
		@Override
		public Void call() throws Exception {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(3000);
			return null;
		}
	}
}
