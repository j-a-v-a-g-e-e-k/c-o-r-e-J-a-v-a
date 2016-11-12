package _020_Executor;

import java.util.concurrent.Executor;

class MyExecutor2 implements Executor{
	@Override
	public void execute(Runnable command) {
		//this implementation will run in a separate thread
		new Thread(command).start();		
	}	
}
class MyRunnable5 implements Runnable {
	@Override
	public void run() {
		long sum = 0;
		for (long i = 0; i <= 100; i++) {
			sum += i;
		}
		System.out.println(Thread.currentThread().getName() + ": " + sum);
	}
}

public class _005_Executor {
	public static void main(String[] args) {
		new MyExecutor2().execute(new MyRunnable5());
	}
}