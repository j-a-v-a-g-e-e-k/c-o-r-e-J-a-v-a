/**
ThreadPoolExecutor.CallerRunsPolicy
By using this handler policy in ThreadPool,the handler will invoke the rejected task in the thread which called the execute method i.e 
the Caller which added this task to the ThreadPool. This handler will slowdown the task addition to the ThreadPool if there is any rejected task 
since the caller(which adds the task to ThreadPool) will run the rejected task.
*/

package _020_Executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _016_RejectionHandler_CallerRunPolicy {	
	public static void main(String... args) {
		int corePoolSize    = 2;
		int maximumPoolSize = 5;
		int keepAliveTime   = 1000;
		int maxWaitingTasks = 3;

		ThreadPoolExecutor es = new ThreadPoolExecutor(
				corePoolSize, maximumPoolSize,
				keepAliveTime, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(maxWaitingTasks),
				new ThreadPoolExecutor.CallerRunsPolicy());	

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
As we can see in the above output the task Job9  were rejected by the ThreadPool and the handler ran it through the Main thread since the 
Main Thread is the one which added the task to the ThreadPool. Also we can notice that the task Job10 was not rejected by the ThreadPool. 
It is due to the slowness in adding the task to the ThreadPool,since the caller i.e Main Thread has run the rejected task Job 9 and by the time 
Job10 was added ,an ideal thread in the pool was found to  run the task.
 */
