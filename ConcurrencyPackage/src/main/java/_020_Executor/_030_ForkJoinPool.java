/*
Core Classes used in Fork/Join Framework
The core classes supporting the Fork-Join mechanism are ForkJoinPool and ForkJoinTask.
Let’s learn about their roles in detail.
ForkJoinPool
The ForkJoinPool is basically a specialized implementation of ExecutorService implementing the work-stealing algorithm we talked about above. We 
create an instance of ForkJoinPool by providing the target parallelism level i.e. the number of processors as shown below:
ForkJoinPool pool = new ForkJoinPool(numberOfProcessors);
Where numberOfProcessors = Runtime.getRunTime().availableProcessors();

If you use a no-argument constructor, by default, it creates a pool of size that equals the number of available processors obtained using above 
technique. Although you specify any initial pool size, the pool adjusts its size dynamically in an attempt to maintain enough active threads at any 
given point in time. Another important difference compared to other ExecutorService's is that this pool need not be explicitly shutdown upon program 
exit because all its threads are in daemon mode.

There are three different ways of submitting a task to the ForkJoinPool.
1) execute() method //Desired asynchronous execution; call its fork method to split the work between multiple threads.
2) invoke() method: //Await to obtain the result; call the invoke method on the pool.
3) submit() method: //Returns a Future object that you can use for checking status and obtaining the result on its completion.

ForkJoinTask
This is an abstract class for creating tasks that run within a ForkJoinPool. The Recursiveaction and RecursiveTaskare the only two direct, 
known subclasses of ForkJoinTask. The only difference between these two classes is that the RecursiveAction does not return a value while 
RecursiveTask does have a return value and returns an object of specified type.
In both cases, you would need to implement the compute method in your subclass that performs the main computation desired by the task.
 */

// Using RecursiveTask
package _020_Executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
public class _030_ForkJoinPool{
	public static void main(String[] args){

		int[] array = new int[20000];
		for (int i=0;i<20000;i++){
			array[i]=i;
		}
		System.out.println(ForkJoinPool.commonPool().invoke(new ForkJoinTask(array,0,array.length)));
	}
}

class ForkJoinTask extends RecursiveTask<Long> {
	static final int SEQUENTIAL_THRESHOLD = 5000;

	int low;
	int high;
	int[] array;

	ForkJoinTask(int[] arr, int lo, int hi) {
		array = arr;
		low   = lo;
		high  = hi;
	}
	
	ForkJoinTask(){		
	}

	@Override
	protected Long compute() {		
		if(high - low <= SEQUENTIAL_THRESHOLD) {
			System.out.println(Thread.currentThread().getName() + " if: " + low + " " + high);
			long sum = 0;
			for(int i=low; i < high; ++i) {				
				sum += array[i];
			}
			return sum;
		} else {
			System.out.println(Thread.currentThread().getName() + " else: " + low + " " + high);
			int mid = low + (high - low) / 2;
			ForkJoinTask left  = new ForkJoinTask(array, low, mid);
			ForkJoinTask right = new ForkJoinTask(array, mid, high);
			left.fork(); //non-blocking. returns immediately
			long rightAns = right.compute();
			//join() is same as get(). Gets the result of the left task in this case
			//join() is blocking like get(). Thumb rule is to fork() early and join() late.
			long leftAns  = left.join(); 
			return leftAns + rightAns;
		}
	}
}

/*
It might seem more natural to call fork twice for the two subproblems and then call join twice. This is naturally a little less efficient than 
just calling compute for no benefit since you are creating more parallel tasks than is helpful. But it turns out to be a lot less efficient, for 
reasons that are specific to the current implementation of the library and related to the overhead of creating tasks that do very little work 
themselves.
 */
