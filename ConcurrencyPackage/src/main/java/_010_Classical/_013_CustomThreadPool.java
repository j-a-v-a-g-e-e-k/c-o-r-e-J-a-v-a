/*Writing your own Thread pool from scratch in Java

here we can see that the Threadpool Executor accepts Runnable objects and puts it into a Runnable Queue. This queue represents all the tasks that 
are sent to be executed by the Threadpool. The Threadpool itself is a bunch of threads that are waiting to pull out Runnables from the queue and 
execute them in their own run() methods. When the Threadpool is running, or in other words, the threads in the Threadpool are alive and ready to 
execute runnables, the run() method inside them is constantly polling the queue for any new objects. When there’s a new Runnable in the queue, one 
of the threads pulls it out and calls the run()method of the Runnable. In this way, we restrict the number of running threads to the number of 
threads set by the creator of the Threadpool. If you have 4 CPU threads in your machine, and only want your application to take a maximum of 50% 
(In practice, there are other threads spawned by the JVM, or other libraries that you might be using, which might cross this limit), you would 
restrict your pool to only run two threads at a time. However, if a Runnable being executed by the Thread in the Threadpool goes to sleep, that 
thread is effectively blocked until the Runnable decides that it’s time to continue working again. There is no way to stop a Runnable, not cleanly.
*/

package _010_Classical;

import java.util.LinkedList;
import java.util.Queue;

interface CustomQueue<E>{
	public void enqueue(E e);
	public E dequeue();
}

class MyQueue<E> implements CustomQueue<E>{
	// queue backed by a linkedlist
	private Queue<E> queue = new LinkedList<E>();

	//Enqueue will add an object to this queue, and will notify any waiting thread that now there is an object available.
	//In enqueue method we just adding the elements not caring of size,  we can even introduce some check of size here also.
	@Override
	public synchronized void enqueue(E e) {
		queue.add(e);
		// Wake up anyone waiting on the queue to put some item.
		notifyAll();
	}

	//Make a blocking call so that we will only return when the queue has something on it, otherwise wait until something is put on it.
	@Override
	public synchronized E dequeue(){
		while(queue.isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		return queue.remove();
	}
}

class Worker implements Runnable{
	//  Worker keep listening on the queue and will process the task if submitted.
	private MyQueue<Runnable> myQueue;
	private String name;

	public Worker(MyQueue<Runnable> myQueue, String name){
		this.myQueue = myQueue;
		this.name = name;
	}

	@Override
	public void run() {
		while(true){
			Runnable r = myQueue.dequeue();
			System.out.println("Task Started by Thread :" + this.name );
			r.run();
			System.out.println("Task Finished by Thread :" + this.name);
		}
	}
}

class ThreadPoolManager {
	private final int THREADPOOL_CAPACITY;
	private MyQueue<Runnable> myQueue = new MyQueue<Runnable>();

	public ThreadPoolManager(int capacity){
		this.THREADPOOL_CAPACITY = capacity;
		initAllConsumers();
	}
	private void initAllConsumers(){
		for(Integer i = 0; i < THREADPOOL_CAPACITY; i++){
			Thread thread = new Thread(new Worker(myQueue, i.toString()));
			thread.start();
		}
	}
	public void submitTask(Runnable r){
		myQueue.enqueue(r);
	}
}

public class _013_CustomThreadPool {
	public static void main(String[] args) {
		ThreadPoolManager poolManager = new ThreadPoolManager(2);
		for (int i=0; i<4;i++){
			poolManager.submitTask(() -> {
				try{
					Thread.sleep(1000);
					System.out.println("Running task..");
				} catch (Exception ex){ }});
		}
	}
}
