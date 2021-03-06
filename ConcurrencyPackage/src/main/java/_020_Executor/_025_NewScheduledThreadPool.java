/*This method is capable of executing tasks with a fixed time rate, e.g. once every second
Please keep in mind that scheduleAtFixedRate() doesn't take into account the actual duration of the task. So if you specify a period of one second
but the task needs 2 seconds to be executed then the thread pool will working to capacity very soon.
*/
package _020_Executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class _025_NewScheduledThreadPool {
	public static void main(String[] args) throws InterruptedException{
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
		int initialDelay = 0;
		int period = 1;
		executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
	}
}
