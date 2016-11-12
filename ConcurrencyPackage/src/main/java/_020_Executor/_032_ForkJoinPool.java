/*
 Using RecursiveTask
 */

package _020_Executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class _032_ForkJoinPool extends RecursiveTask<Long> {
	static final int SEQUENTIAL_THRESHOLD = 5000;

	int low;
	int high;
	int[] array;

	_032_ForkJoinPool(int[] arr, int lo, int hi) {
		array = arr;
		low   = lo;
		high  = hi;
	}
	_032_ForkJoinPool(){
		
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
			_032_ForkJoinPool left  = new _032_ForkJoinPool(array, low, mid);
			_032_ForkJoinPool right = new _032_ForkJoinPool(array, mid, high);
			//this version is non-parallel because it computes the right before starting to compute the left
			long rightAns = right.compute();//blocking.
			left.fork();
			long leftAns  = left.join(); 						
			return leftAns + rightAns;
		}
	}

	long sumArray(int[] array) {
		return ForkJoinPool.commonPool().invoke(new _032_ForkJoinPool(array,0,array.length));
	}

	public static void main(String[] args){

		int[] array = new int[20000];
		for (int i=0;i<20000;i++){
			array[i]=i;
		}
		_032_ForkJoinPool demo = new _032_ForkJoinPool();
		System.out.println(demo.sumArray(array));
	}
}
