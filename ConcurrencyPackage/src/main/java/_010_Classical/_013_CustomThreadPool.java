package _010_Classical;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.util.SystemPropertyUtils;

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
			} catch (InterruptedException e) {
			}
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
