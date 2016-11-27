/*
RecursiveAction
These types of tasks are not meant to return anything. These are ideal for cases where you want to do an action, such as delete a file, without 
returning anything. This class is very similar to its predecessors. The main difference lies in the way the final value (the size of the file or 
directory) is returned. Remember that the RecursiveAction cannot return a value. Instead, all tasks will share a common counter of type 
AtomicLong and these will increment this common counter instead of returning the size of the file.
 */

package _020_Executor;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicLong;

public class _036_ForkJoinPool {

	public static void main(final String[] args) {
		final long start = System.nanoTime();
		final long size = DirSize.sizeOf(new File("C:\\Users\\Bimal\\Documents\\GitHub"));
		final long taken = System.nanoTime() - start;

		System.out.println(size);
		System.out.println(taken/1000);
	}

	private static class SizeOfFileAction extends RecursiveAction {
		private final File file;
		private final AtomicLong sizeAccumulator;

		public SizeOfFileAction(final File file, final AtomicLong sizeAccumulator) {
			this.file = Objects.requireNonNull(file);
			this.sizeAccumulator = Objects.requireNonNull(sizeAccumulator);
		}

		@Override
		protected void compute() {
			if (file.isFile()) {
				sizeAccumulator.addAndGet(file.length());
			} else {
				final File[] children = file.listFiles();
				if (children != null) {
					for (final File child : children) {
//						new SizeOfFileAction(child, sizeAccumulator).fork();
						ForkJoinTask.invokeAll(new SizeOfFileAction(child, sizeAccumulator));
					}
				}
			}
		}
	}

	public static long sizeOf(final File file) {
		final ForkJoinPool pool = new ForkJoinPool();
		try {
			final AtomicLong sizeAccumulator = new AtomicLong();
			pool.invoke(new SizeOfFileAction(file, sizeAccumulator));
			return sizeAccumulator.get();
		} finally {
			pool.shutdown();
		}
	}
}

/*
Conclusion
This article provided a detailed explanation of the Fork/Join Framework and how this can be used. It provided a practical example 
and compared several approaches. The Fork/Join Framework is ideal for recursive algorithms but it does not distribute the load amongst 
the threads evenly. The tasks and subtask should not block on anything else but join and should delegate work using fork. Avoid any 
blocking IO operations within tasks and minimise the mutable share state especially modifying the variable as much as possible as this 
has a negative effect on the overall performance.
*/