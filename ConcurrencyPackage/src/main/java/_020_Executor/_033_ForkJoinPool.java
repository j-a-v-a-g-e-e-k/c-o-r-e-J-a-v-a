/*
 Using RecursiveTask
 */

package _020_Executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class _033_ForkJoinPool extends RecursiveTask<Long> {
	static final int SEQUENTIAL_THRESHOLD = 5000;

	int low;
	int high;
	int[] array;

	_033_ForkJoinPool(int[] arr, int lo, int hi) {
		array = arr;
		low   = lo;
		high  = hi;
	}
	_033_ForkJoinPool(){
		
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
			_033_ForkJoinPool left  = new _033_ForkJoinPool(array, low, mid);
			_033_ForkJoinPool right = new _033_ForkJoinPool(array, mid, high);
			left.fork(); 
			right.fork();
			long leftAns  = left.join(); //blocking until result is available
			long rightAns = right.join();			
			return leftAns + rightAns;
		}
	}

	long sumArray(int[] array) {
		return ForkJoinPool.commonPool().invoke(new _033_ForkJoinPool(array,0,array.length));
	}

	public static void main(String[] args){

		int[] array = new int[20000];
		for (int i=0;i<20000;i++){
			array[i]=i;
		}
		_033_ForkJoinPool demo = new _033_ForkJoinPool();
		System.out.println(demo.sumArray(array));
	}
}
