/**
ThreadPoolExecutor.DiscardOldestPolicy
If any added task is about to be rejected,the handler will discards the oldest unallocated task until it finds the ideal thread and then 
execute the current task through the ideal thread.
 */

package _020_Executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _018_RejectionHandler_DiscardOldestPolicy {	
	public static void main(String... args) {
		int corePoolSize    = 2;
		int maximumPoolSize = 5;
		int keepAliveTime   = 1000;
		int maxWaitingTasks = 3;

		ThreadPoolExecutor es = new ThreadPoolExecutor(
				corePoolSize, maximumPoolSize,
				keepAliveTime, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(maxWaitingTasks),
				new ThreadPoolExecutor.DiscardOldestPolicy());	

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
As we can see in the above output,the ThreadPool discard the task Job 3 and Job 4.This due to:
Since there were no ideal threads to run the task Job9 and hence the task will handled by the handler. The handler then discards the oldest 
unallocated task which is task Job3 waiting in the queue. Then an  ideal thread in the poolwas found to run the task Job9. The same apply 
for Job 10 as well which made the handler to discard the oldest task Job4 and then an ideal thread was found to run the task Job10.
 */

