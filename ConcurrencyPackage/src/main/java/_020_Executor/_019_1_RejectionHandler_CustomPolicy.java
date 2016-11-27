package _020_Executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _019_1_RejectionHandler_CustomPolicy {	
	public static void main(String... args) {
		int corePoolSize    = 10;
		int maximumPoolSize = 10;
		int keepAliveTime   =  0;
		int maxWaitingTasks = 5;

		ThreadPoolExecutor es = new ThreadPoolExecutor(
				corePoolSize, maximumPoolSize,
				keepAliveTime, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(maxWaitingTasks),
				//custom handler - the handler to use when execution is blocked because the thread bounds and queue capacities are reached
				new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						try {
							executor.getQueue().put(r);
						} catch (InterruptedException e) {
							throw new RuntimeException("Interrupted while submitting task", e);
						}
					}
				});

		//all tasks get queued up
		for (int i = 0; i < 30; i++)
			es.submit(new SleepOneSecond());
		System.out.println("Queue length " + ((ThreadPoolExecutor) es).getQueue().size());
		//shutdown the executor, so no more new tasks can be submitted
		es.shutdown();
		System.out.println("After shutdown");
		System.out.println("isShutdown: " + es.isShutdown());
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
