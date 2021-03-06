//This method works just like the counterpart described above. 
//The difference is that the wait time period applies between the end of a task and the start of the next task.

package _020_Executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class _026_NewScheduledThreadPool {
	public static void main(String[] args) throws InterruptedException{
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		Runnable task = () -> {
		    try {
		        TimeUnit.SECONDS.sleep(2);
		        System.out.println("Scheduling: " + System.nanoTime());
		    }
		    catch (InterruptedException e) {
		        System.err.println("task interrupted");
		    }
		};
		executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
	}
}
