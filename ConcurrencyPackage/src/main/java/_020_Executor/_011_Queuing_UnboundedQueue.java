/**
Unbounded Queues:
In this queuing strategy the submitted task will wait in the queue if the corePoolsize theads are busy and the task will be allocated 
if any of the threads become idle.Thus ThreadPool will always have number of threads running as mentioned in the corePoolSize.Thus the 
maxPoolSize will not have any effect in threadpool size. LinkedBlockingQueue without the capacity can be used for this queuing strategy.
If the corePoolsize of the threadpool is less and there are more number of time consuming task were submitted,there is more possibility 
that the task has to wait in the queue for more time before it is run by any of the ideal thread.Also no task will be rejected in 
Threadpool until the threadpool was shutdown.
 */

package _020_Executor;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _011_Queuing_UnboundedQueue {	
	public static void main(String... args) {
	int corePoolSize    = 2;
	int maximumPoolSize = 5;
	int keepAliveTime   = 1000;

	ThreadPoolExecutor es = new ThreadPoolExecutor(
	        corePoolSize, maximumPoolSize,
	        keepAliveTime, TimeUnit.SECONDS,
	        new LinkedBlockingQueue<Runnable>(),
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

/*
OUTPUT:
Thread-1  and Thread-2 were allocated to run the Task Job1 and Job2. So the corepoolsize threads(i.e 2) are busy in running this task.
The forthcoming submitted task job3,Job4 etc will wait in the queue.It will be run by either Thread 1 or Thread 2 when they become idle.
Thus only 2 threads will run in the threadpool and the maxPoolsize which was defined as 5 will not have any effect to the threadPool size.   
*/