/*
 Using ExecutorService
 this method blocks once all threads are occupied
 */
package _020_Executor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class _037_ForkJoinPool {

	public static void main(final String[] args) {
		final long start = System.nanoTime();
		final long size = DirSize.sizeOf(new File("C:\\Users\\Bimal\\Documents\\GitHub"));
		final long taken = System.nanoTime() - start;

		System.out.println(size);
		System.out.println(taken/1000);
	}

	private static class SizeOfFileCallable implements Callable<Long> {

		private final File file;
		private final ExecutorService executor;

		public SizeOfFileCallable(final File file, final ExecutorService executor) {
			this.file = Objects.requireNonNull(file);
			this.executor = Objects.requireNonNull(executor);
		}

		@Override
		public Long call() throws Exception {
			long size = 0;

			if (file.isFile()) {
				size = file.length();
			} else {
				final List<Future<Long>> futures = new ArrayList<>();
				for (final File child : file.listFiles()) {
					futures.add(executor.submit(new SizeOfFileCallable(child, executor)));
				}

				for (final Future<Long> future : futures) {
					size += future.get();
				}
			}
			return size;
		}
	}

	public static <T> long sizeOf(final File file) {
		final int threads = Runtime.getRuntime().availableProcessors();
		final ExecutorService executor = Executors.newFixedThreadPool(threads);
		try {
			return executor.submit(new SizeOfFileCallable(file, executor)).get();
		} catch (final Exception e) {
			throw new RuntimeException("Failed to calculate the dir size", e);
		} finally {
			executor.shutdown();
		}
	}
}