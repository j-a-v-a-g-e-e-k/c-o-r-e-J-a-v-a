/*
 Using RecursiveTask
 */

package _020_Executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class _030_ForkJoinPool extends RecursiveTask<Long> {
	static final int SEQUENTIAL_THRESHOLD = 5000;

	int low;
	int high;
	int[] array;

	_030_ForkJoinPool(int[] arr, int lo, int hi) {
		array = arr;
		low   = lo;
		high  = hi;
	}
	_030_ForkJoinPool(){
		
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
			_030_ForkJoinPool left  = new _030_ForkJoinPool(array, low, mid);
			_030_ForkJoinPool right = new _030_ForkJoinPool(array, mid, high);
			left.fork(); //non-blocking. returns immediately
			long rightAns = right.compute();
			// join() is same as get()
			long leftAns  = left.join(); //blocking until result is available. Thumb rule is to fork() early and join() late.
			return leftAns + rightAns;
		}
	}

	long sumArray(int[] array) {
		return ForkJoinPool.commonPool().invoke(new _030_ForkJoinPool(array,0,array.length));
	}

	public static void main(String[] args){

		int[] array = new int[20000];
		for (int i=0;i<20000;i++){
			array[i]=i;
		}
		_030_ForkJoinPool demo = new _030_ForkJoinPool();
		System.out.println(demo.sumArray(array));
	}
}

/*
It might seem more natural to call fork twice for the two subproblems and then call join twice. This is naturally a little 
less efficient than just calling compute for no benefit since you are creating more parallel tasks than is helpful. But it turns out 
to be a lot less efficient, for reasons that are specific to the current implementation of the library and related to the overhead of 
creating tasks that do very little work themselves.
 */
