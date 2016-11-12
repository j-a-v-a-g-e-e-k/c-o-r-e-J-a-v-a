/**
ThreadPoolExecutor.DiscardPolicy
This  handler will silently discards the rejected task and will not take any action for the rejected task.
 */

package _020_Executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _017_RejectionHandler_DiscardPolicy {	
	public static void main(String... args) {
		int corePoolSize    = 2;
		int maximumPoolSize = 5;
		int keepAliveTime   = 1000;
		int maxWaitingTasks = 3;

		ThreadPoolExecutor es = new ThreadPoolExecutor(
				corePoolSize, maximumPoolSize,
				keepAliveTime, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(maxWaitingTasks),
				new ThreadPoolExecutor.DiscardPolicy());	

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

/*
As we can see in the above output the rejected task Job9 and Job10 were silently discarded by the handler and were no action taken by the handler.
 */

