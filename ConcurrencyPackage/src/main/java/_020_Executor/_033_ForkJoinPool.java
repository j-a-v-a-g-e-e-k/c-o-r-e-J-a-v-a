// Using RecursiveTask

package _020_Executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class _033_ForkJoinPool{
	public static void main(String[] args){

		int[] array = new int[20000];
		for (int i=0;i<20000;i++){
			array[i]=i;
		}
		System.out.println(ForkJoinPool.commonPool().invoke(new ForkJoinTask4(array,0,array.length)));
	}
}

class ForkJoinTask4 extends RecursiveTask<Long> {
	static final int SEQUENTIAL_THRESHOLD = 5000;

	int low;
	int high;
	int[] array;

	ForkJoinTask4(int[] arr, int lo, int hi) {
		array = arr;
		low   = lo;
		high  = hi;
	}
	ForkJoinTask4(){
		
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
			ForkJoinTask4 left  = new ForkJoinTask4(array, low, mid);
			ForkJoinTask4 right = new ForkJoinTask4(array, mid, high);
			left.fork(); 
			right.fork();
			long leftAns  = left.join(); //blocking until result is available
			long rightAns = right.join();			
			return leftAns + rightAns;
		}
	}
}
