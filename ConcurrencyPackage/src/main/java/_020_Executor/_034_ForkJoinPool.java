/*
To demonstrate the use of the Fork/Join Framework, we will calculate the size of a directory, which can be solved recursively. 
Using a Single Thread (no-concurrency)
 */

package _020_Executor;

import java.io.File;

public class _034_ForkJoinPool {
	public static void main(final String[] args) {
		final long start = System.nanoTime();
		final long size = DirSize.sizeOf(new File("C:\\Users\\Bimal\\Documents\\GitHub\\REST"));
		final long taken = System.nanoTime() - start;
		System.out.println(size);
		System.out.println(taken/1000);
	}
}

class DirSize {
	public static long sizeOf(final File file) {
		System.out.println("Computing size of: " + file);
		long size = 0;
		// Ignore files which are not files and dirs
		if (file.isFile()) {
			size = file.length();
		} else {
			final File[] children = file.listFiles();
			if (children != null) {
				for (final File child : children) {
					size += DirSize.sizeOf(child);
				}
			}
		}
		return size;
	}
}