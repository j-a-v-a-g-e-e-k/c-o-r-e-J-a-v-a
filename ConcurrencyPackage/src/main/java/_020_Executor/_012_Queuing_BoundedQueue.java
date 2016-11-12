/**
Bounded Queues:
The submitted task will wait in the queue if there are no ideal corepoolsize threads.Once the queue is full, the threads will be created 
upto maxpoolsize for every submitted task. ArrayBlockingQueue can be used for this queuing strategy.
 */

package _020_Executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _012_Queuing_BoundedQueue {	
	public static void main(String... args) {
		int corePoolSize    = 2;
		int maximumPoolSize = 5;
		int keepAliveTime   = 1000;
		int queueSize=3;

		ThreadPoolExecutor es = new ThreadPoolExecutor(
				corePoolSize, maximumPoolSize,
				keepAliveTime, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(queueSize),
				new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println("Running through RejectionHandler,Since there is no ideal threads in ThreadPool");
					}
				});	

		for (int i = 0; i < 10; i++)
			es.submit(new SleepOneSecond(i));
		System.out.println("Queue length " + ((ThreadPoolExecutor) es).getQueue().size());
		es.shutdown();
		System.out.println("After shutdown");
	}

	static class SleepOneSecond implements Callable<Void> {
		int jobId;
		SleepOneSecond(int jobId){
			this.jobId=jobId;
		}
		@Override
		public Void call() throws Exception {	
			System.out.println("Job-" + jobId + " " + Thread.currentThread().getName());
			Thread.sleep(3000);
			return null;
		}
	}
}

