package com.sort.algorithm;

import java.util.Arrays;

public class MergeSortBottomUp {
	
	   public static void main( String[] args )
	   {
	      double[] x = {6.4, 3.5, 7.5, 2.5, 8.9, 4.2, 9.2, 1.1} ;
	      double[] help = new double[x.length];

	      System.out.println("Before sort:  " + Arrays.toString(x) );

	      MergeSortBottomUp.sort( x, help );  // Merge sort

	      System.out.println("\nAfter sort:   " + Arrays.toString(x) );
	   }
	
	public static void merge(double[] a, 
			int iLeft, int iMiddle, int iRight, 
			double[] tmp){
		System.out.println(iLeft + " " + iMiddle + " " + iRight);
		int i, j, k;

		i = iLeft;
		j = iMiddle;
		k = iLeft;

		while ( i < iMiddle || j < iRight )	{
			if ( i < iMiddle && j < iRight ) {  
				// Both array have elements
				if ( a[i] < a[j] )
					tmp[k++] = a[i++];
				else
					tmp[k++] = a[j++];
			} else if ( i == iMiddle )
				tmp[k++] = a[j++];     // a is empty
			else if ( j == iRight )
				tmp[k++] = a[i++];     // b is empty
		}
	}

	public static void sort(double[] a, double[] tmp) {
		int width;

		for ( width = 1; width < a.length; width = 2*width )
		{
			// Combine sections of array a of width "width"
			for ( int i = 0; i < a.length; i = i + 2*width )
			{
				int left, middle, right;

				left = i;
				middle = i + width;
				right  = i + 2*width;

				merge( a, left, middle, right, tmp );

			}

			/* ================================================
	    Copy tmp[ ] back to a[ ] for next iteration
	    ================================================ */
			for ( int j = 0; j < a.length; j++ )
				a[j] = tmp[j];

			System.out.println("After 1 iter: " + Arrays.toString(a) );
		}
	}
}
