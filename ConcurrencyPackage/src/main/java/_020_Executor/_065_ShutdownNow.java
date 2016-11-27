package _020_Executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class _065_ShutdownNow {	

	public static void main(String... args) {
		ExecutorService es = Executors.newFixedThreadPool(2);
		//all tasks are queued up
		for (int i = 0; i < 50; i++)
			es.submit(new SleepOneSecond());

		System.out.println("Queue length " + ((ThreadPoolExecutor) es).getQueue().size());
		//shutdown the executor, so no more tasks can be executed, even the submitted ones
		es.shutdownNow();		
		try {
			//submitting more tasks
			es.submit(new SleepOneSecond());
			System.out.println("After shutdown");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	static class SleepOneSecond implements Callable<Void> {
		@Override
		public Void call() throws Exception {			
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName());
			return null;
		}
	}
}
