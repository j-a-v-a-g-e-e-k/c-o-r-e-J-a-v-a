/**
Rules of a ThreadPoolExecutor pool size
The rules for the size of a ThreadPoolExecutor’s pool are generally miss-understood, because it doesn’t work the way that you think it ought to or 
in the way that you want it to. Take this example. Starting thread pool size is 1, core pool size is 5, max pool size is 10 and the queue is 100.
Sun’s way: as requests come in threads will be created up to 5, then tasks will be added to the queue until it reaches 100. When the queue is full 
new threads will be created up to maxPoolSize. Once all the threads are in use and the queue is full tasks will be rejected. As the queue reduces 
so does the number of active threads.
User anticipated way: as requests come in threads will be created up to 10, then tasks will be added to the queue until it reaches 100 at which 
point they are rejected. The number of threads will rename at max until the queue is empty. When the queue is empty the threads will die off until 
there are corePoolSize left. The difference is that the users want to start increasing the pool size earlier and want the queue to be smaller, 
where as the Sun method want to keep the pool size small and only increase it once the load becomes to much.
Here are Sun’s rules for thread creation in simple terms:
1. If the number of threads is less than the corePoolSize, create a new Thread to run a new task.
2. If the number of threads is equal (or greater than) the corePoolSize, put the task into the queue.
3. If the queue is full, and the number of threads is less than the maxPoolSize, create a new thread to run tasks in.
4. If the queue is full, and the number of threads is greater than or equal to maxPoolSize, reject the task.
The long and the short of it is that new threads are only created when the queue fills up, so if you’re using an unbounded queue then the number 
of threads will not exceed corePoolSize.

Most people want it the other way around, so that you increase the number of threads to avoid adding to the queue. When the threads are all in 
use the queue starts to fill up.
Using Sun’s way, I think you are going to end up with a system that runs slower when the load is light and a bit quicker as the load increases. 
Using the other way means you are running flat out all the time to process outstanding work.
 */

/**
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue workQueue, 
					ThreadFactory threadFactory, RejectedExecutionHandler handler).
Here’s a description of what the parameters mean:
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
			Thread.sleep(3000);
			System.out.println("Job-" + jobId + " " + Thread.currentThread().getName());
			return null;
		}
	}
}

/*
OUTPUT: 
Thread-1 and Thread-2 were allocated to run the Task Job1 and Job2.So the corepoolsize threads(i.e 2) are busy in running this task.
Once the Job3 is submitted,it is not queued as we used the Direct Handoff.It therefore constructs the thread-3.
The same apply for Job4 and Job5 as well which were run by Thread-4 and Thread-5.
Since there were no ideal threads in ThreadPool(Since the maxpoolsize is 5 and all 5 threads were busy), submission of task Job6 to Job10 were 
rejected and were run by the Rejectionhandler. 
*/

