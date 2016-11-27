/*
In a typical Java ExecutorService application where some threads will be assigned from the internal thread pool or created on-demand 
to perform tasks. Each ExecutorService has an associated ThreadFactory and a default ThreadFactory if the application does not specify one. 
For non-trivial apps, it’s always a good idea to create a custom ThreadFactory. Why??
1. To set a more descriptive thread name. With the default ThreadFactory, it gives thread names in the form of pool-m-thread-n, 
such as pool-1-thread-1, pool-2-thread-1, pool-3-thread-1, etc. When analyzing a thread dump, it’s hard to know their purpose and 
how they were started. So, using a descriptive thread name is the only clue to trace to the source where the ThreadPoolExecutor or 
ExecutorService is created.
2. To set thread daemon status. The default ThreadFactory produces non-daemon threads.
3. To set thread priority. The default ThreadFactory creates a normal priority threads.
 */

package _020_Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class _027_ThreadFactory {
	public static void main(String[] args) {

		ThreadFactory customThreadfactory = new CustomThreadFactoryBuilder()
				.setNamePrefix("BimalDemo-Thread")
				.setDaemon(false)
				.setPriority(Thread.MAX_PRIORITY)
				.build();

		ExecutorService executorService = Executors.newFixedThreadPool(3, customThreadfactory);

		// Create three simple tasks with 1000 ms sleep time
		SimpleTask simpleTask1 = new SimpleTask(1000);
		SimpleTask simpleTask2 = new SimpleTask(1000);
		SimpleTask simpleTask3 = new SimpleTask(1000);

		// Execute three simple tasks with 1000 ms sleep time
		executorService.execute(simpleTask1);
		executorService.execute(simpleTask2);
		executorService.execute(simpleTask3);

	}
}

class CustomThreadFactoryBuilder {		
	private String namePrefix = null;
	private boolean daemon = false;
	private int priority = Thread.NORM_PRIORITY;

	public CustomThreadFactoryBuilder setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
		return this;
		
	}

	public CustomThreadFactoryBuilder setDaemon(boolean daemon) {
		this.daemon = daemon;
		return this;
	}

	public CustomThreadFactoryBuilder setPriority(int priority) {
		this.priority = priority;
		return this;
	}

	public ThreadFactory build() {
		final AtomicLong count = new AtomicLong(1);
		return new ThreadFactory() {
			@Override
			public Thread newThread(Runnable runnable) {
				Thread thread = new Thread(runnable);
				thread.setName(namePrefix + "-" + count.getAndIncrement());
				thread.setDaemon(daemon);
				thread.setPriority(priority);
				return thread;
			}
		};
	}
}

class SimpleTask implements Runnable {
	private long sleepTime;
	public SimpleTask(long sleepTime) {
		super();
		this.sleepTime = sleepTime;
	}
	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Simple task is running on " + Thread.currentThread().getName() + " with priority " + Thread.currentThread().getPriority());
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
