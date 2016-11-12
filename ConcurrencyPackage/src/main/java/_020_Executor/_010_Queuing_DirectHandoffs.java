/**
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue workQueue, 
					ThreadFactory threadFactory, RejectedExecutionHandler handler).
Here’s a description of what the parameters mean and how they answer the questions above:
• int corePoolSize – the number of threads you want to start the Thread pool off with, initially
• int maximumPoolSize – when the core threads are busy, do you want to grow the pool size up to the maximum?
• long keepAliveTime, TimeUnit unit – do you want to shutdown spare threads if there’s no work for them? How long should the pool wait until then?
• BlockingQueue workQueue – how the tasks that cannot be handled right away should be stored? Do you want to limit the task queue size?
• RejectedExecutionHandler handler – What to do if the task cannot be accepted by the executor? Should we throw an exception? 
Should the caller perform the task itself?
 
In ThreadPoolExecutor queues are used to hold the submitted task, if there are no ideal threads to run/allocate the task.
There are three strategies for the Queuing.
Direct Handoffs
Unbounded Queues
Bounded Queues
 
Direct Handoffs:
In this queuing strategy the queue will not hold any submitted task  and it will always create the threads upto maxpoolsize when there 
are no ideal threads(CorePoolSize threads are busy) in the Threadpool. Once the maxpoolsize threads also becomes busy,submission of 
new task will be rejected and can be handled via Rejection Handler. SynchronousQueue can be used for the Direct Handoffs. when the 
maxpoolsize is defined as more and the time consuming task is arriving faster,there is a possibility more number of threads will be 
running in the pool.
*/

package _020_Executor;

import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _010_Queuing_DirectHandoffs {	
	public static void main(String... args) {
	int corePoolSize    = 2;
	int maximumPoolSize = 5;
	int keepAliveTime   = 1000;

	ThreadPoolExecutor es = new ThreadPoolExecutor(
	        corePoolSize, maximumPoolSize,
	        keepAliveTime, TimeUnit.SECONDS,
	        new SynchronousQueue<Runnable>(),
	        new RejectedExecutionHandler() {
				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println("Running through RejectionHandler, since there is no ideal threads in ThreadPool");
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
Thread-1 and Thread-2 were allocated to run the Task Job1 and Job2.So the corepoolsize threads(i.e 2) are busy in running this task.
Once the Job3 is submitted,it is not queued as we used the Direct Handoff.It therefore constructs the thread-4.
The same apply for Job4 and Job5 as well which were run by Thread-4 and Thread-5.
Since there were no ideal threads in ThreadPool(Since the maxpoolsize is 5 and all 5 threads were busy),
submission of task Job6 to Job10 were rejected and were run by the Rejectionhandler. 
*/

