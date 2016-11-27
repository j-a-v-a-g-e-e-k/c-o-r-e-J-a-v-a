/* 
 If you want to execute some tasks periodically or at certain time in future, use newScheduledThreadPool
 
     public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }
*/
package _020_Executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _024_NewScheduledThreadPool {
	public static void main(String[] args) throws InterruptedException{
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		Runnable task = () -> System.out.println("\nScheduling: " + System.nanoTime());
		//After the delay of 3 seconds has elapsed the task will be executed concurrently
		ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

		TimeUnit.MILLISECONDS.sleep(1337);

		long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
		System.out.printf("Remaining Delay: %sms", remainingDelay);
	}
}
