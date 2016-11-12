/**
In the previous post "ThreadPoolexecutor in Java" we created our user defined RejectedHandler,which will be invoked if any task 
is rejected by the ThreadPoolExecutor.In addition to defining user defined RejectedHandler,ThreadPoolExecutor provides four 
predefined handler polices for handling the rejected task:
ThreadPoolExecutor.AbortPolicy
ThreadPoolExecutor.CallerRunsPolicy
ThreadPoolExecutor.DiscardPolicy
ThreadPoolExecutor.DiscardOldestPolicy

ThreadPoolExecutor.AbortPolicy
By using this handler policy in ThreadPoolExecutor,the handler will throw the exception RejectedExecutionException if any task is rejected 
by the Threadpool. 
*/

package _020_Executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _015_RejectionHandler_AbortPolicy {	
	public static void main(String... args) {
		int corePoolSize    = 2;
		int maximumPoolSize = 5;
		int keepAliveTime   = 1000;
		int maxWaitingTasks = 3;

		ThreadPoolExecutor es = new ThreadPoolExecutor(
				corePoolSize, maximumPoolSize,
				keepAliveTime, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(maxWaitingTasks),
				new ThreadPoolExecutor.AbortPolicy());	

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
As we can see in the above output the task Job9 and Job10 were rejected by the ThreadPool and hence the RejectedExecutionException was thrown.
*/
