// Using RecursiveTask

package _020_Executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class _032_ForkJoinPool{
	public static void main(String[] args){

		int[] array = new int[20000];
		for (int i=0;i<20000;i++){
			array[i]=i;
		}
		System.out.println(ForkJoinPool.commonPool().invoke(new ForkJoinTask3(array,0,array.length)));
	}
}

class ForkJoinTask3 extends RecursiveTask<Long> {
	static final int SEQUENTIAL_THRESHOLD = 5000;

	int low;
	int high;
	int[] array;

	ForkJoinTask3(int[] arr, int lo, int hi) {
		array = arr;
		low   = lo;
		high  = hi;
	}
	ForkJoinTask3(){
		
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
			ForkJoinTask3 left  = new ForkJoinTask3(array, low, mid);
			ForkJoinTask3 right = new ForkJoinTask3(array, mid, high);
			//this version is non-parallel because it computes the right before starting to compute the left
			long rightAns = right.compute();//blocking.
			left.fork();
//			try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			long leftAns  = left.join(); 						
			return leftAns + rightAns;
		}
	}
}

/*
OUTPUT:
With sleep:
ForkJoinPool.commonPool-worker-1 else: 0 20000
ForkJoinPool.commonPool-worker-1 else: 10000 20000
ForkJoinPool.commonPool-worker-1 if: 15000 20000
ForkJoinPool.commonPool-worker-2 if: 10000 15000
ForkJoinPool.commonPool-worker-2 else: 0 10000
ForkJoinPool.commonPool-worker-2 if: 5000 10000
ForkJoinPool.commonPool-worker-3 if: 0 5000

Without sleep:
ForkJoinPool.commonPool-worker-1 else: 0 20000
ForkJoinPool.commonPool-worker-1 else: 10000 20000
ForkJoinPool.commonPool-worker-1 if: 15000 20000
ForkJoinPool.commonPool-worker-1 if: 10000 15000
ForkJoinPool.commonPool-worker-1 else: 0 10000
ForkJoinPool.commonPool-worker-1 if: 5000 10000
ForkJoinPool.commonPool-worker-1 if: 0 5000
 */
