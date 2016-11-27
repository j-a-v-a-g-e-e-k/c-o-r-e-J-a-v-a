// Using RecursiveTask

package _020_Executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class _031_ForkJoinPool{
	public static void main(String[] args){

		int[] array = new int[20000];
		for (int i=0;i<20000;i++){
			array[i]=i;
		}
		System.out.println(ForkJoinPool.commonPool().invoke(new ForkJoinTask2(array,0,array.length)));
	}
}

class ForkJoinTask2 extends RecursiveTask<Long> {
	static final int SEQUENTIAL_THRESHOLD = 5000;

	int low;
	int high;
	int[] array;

	ForkJoinTask2(int[] arr, int lo, int hi) {
		array = arr;
		low   = lo;
		high  = hi;
	}
	ForkJoinTask2(){
		
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
			ForkJoinTask2 left  = new ForkJoinTask2(array, low, mid);
			ForkJoinTask2 right = new ForkJoinTask2(array, mid, high);
			//our entire array-summing algorithm would have no parallelism (?? there is.) since each step would completely compute the left before 
			//starting to compute the right. So order is important.
			left.fork();
			//allow tasks to get stolen
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			long leftAns  = left.join(); //blocking.
			long rightAns = right.compute();			
			return leftAns + rightAns;
		}
	}
}
/*
OUTPUT: 
note that output vary with each run

Without sleep: This shows that worker-1 set aside the original task when join() was called, and started executing the next task in its queue.
ForkJoinPool.commonPool-worker-1 else: 0 20000
ForkJoinPool.commonPool-worker-1 else: 0 10000
ForkJoinPool.commonPool-worker-2 if: 0 5000
ForkJoinPool.commonPool-worker-1 if: 5000 10000
ForkJoinPool.commonPool-worker-1 else: 10000 20000
ForkJoinPool.commonPool-worker-1 if: 10000 15000
ForkJoinPool.commonPool-worker-1 if: 15000 20000

With sleep: This shows lot more work stealing by other threads
ForkJoinPool.commonPool-worker-1 else: 0 20000
ForkJoinPool.commonPool-worker-2 else: 0 10000
ForkJoinPool.commonPool-worker-3 if: 0 5000
ForkJoinPool.commonPool-worker-2 if: 5000 10000
ForkJoinPool.commonPool-worker-1 else: 10000 20000
ForkJoinPool.commonPool-worker-2 if: 10000 15000
ForkJoinPool.commonPool-worker-1 if: 15000 20000
*/