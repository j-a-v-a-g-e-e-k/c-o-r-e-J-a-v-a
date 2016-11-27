package _020_Executor;

import java.util.concurrent.Executor;

class MyExecutor implements Executor{
	@Override
	public void execute(Runnable command) {
		//basic implementation. this implementation will run in the main thread itself.
		//this is not async execution.
		command.run();		
	}	
}

class MyExecutor2 implements Executor{
	@Override
	public void execute(Runnable command) {
		//this implementation will run in a separate thread
		new Thread(command).start();		
	}	
}

class MyRunnable4 implements Runnable {
	@Override
	public void run() {
		long sum = 0;
		for (long i = 0; i <= 100; i++) {
			sum += i;
		}
		System.out.println(Thread.currentThread().getName() + ": " + sum);
	}
}

public class _004_Executor {
	public static void main(String[] args) {
		new MyExecutor().execute(new MyRunnable4());
		new MyExecutor2().execute(new MyRunnable4());
	}
}