/*
The Fork/Join Framework provides two types of tasks:
RecursiveTask 
RecursiveAction. 

In this section we will only talk about the RecursiveTask.
A RecursiveTask is a task that when executed it returns a value. 
 */
package _020_Executor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class _035_ForkJoinPool {

	public static void main(final String[] args) {
		final long start = System.nanoTime();
		final long size = DirSize.sizeOf(new File("C:\\Users\\Bimal\\Documents\\GitHub\\REST"));
		final long taken = System.nanoTime() - start;	    
		System.out.println(size);
		System.out.println(taken/1000);
	}

	private static class SizeOfFileTask extends RecursiveTask<Long> {
		private final File file;
		public SizeOfFileTask(final File file) {
			this.file = Objects.requireNonNull(file);
		}

		@Override
		protected Long compute() {	     
			if (file.isFile()) {
				return file.length();
			}
			//if the file is a directory, it lists all its sub-files and creates a new instance of SizeOfFileTask for each of these sub-files.
			final List<SizeOfFileTask> tasks = new ArrayList<>();
			final File[] children = file.listFiles();
			if (children != null) {
				for (final File child : children) {
					final SizeOfFileTask task = new SizeOfFileTask(child);
					//For each instance of the created SizeOfFileTask, the fork() method is called. The fork() method causes the new 
					//instance of SizeOfFileTask to be added to this thread’s queue.
					task.fork();
					tasks.add(task);
				}
			}

			long size = 0;
			for (final SizeOfFileTask task : tasks) {
				size += task.join(); 
			}
			return size;
		}
	}

	public static long sizeOf(final File file) {
		/*
		 The method sizeOf() creates an instance SizeOfFileTask, which class extends RecursiveTask<Long>. Therefore the invoke() method 
		 will return the objects/value returned by this task. 
		 
		 Note that the code will block until the size of the directory is computed. In other words the above code will wait for the 
		 task (and all the subtasks) to finish working before continuing.
		 */
		final ForkJoinPool pool = new ForkJoinPool();
		try {
			return pool.invoke(new SizeOfFileTask(file));
		} finally {
			pool.shutdown();
		}
	}
}