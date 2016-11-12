package com;

import java.util.Scanner;

public class Matrix {
	public static void main(String[] args){
		//add();
		transpose();
		//multiply();
	}

	public static void add(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of rows and columns of matrix");
		int m = in.nextInt();
		int n = in.nextInt();
		int first[][]=new int[m][n];
		int second[][]=new int[m][n];
		System.out.println("Enter first matrix");
		for (int i=0; i<m; i++){
			for (int j=0; j<n; j++){
				first[i][j]=in.nextInt();
			}
		}
		System.out.println("Enter second matrix");
		for (int i=0; i<m; i++){
			for (int j=0; j<n; j++){
				second[i][j]=in.nextInt();
			}
		}
		int result[][]=new int[m][n];
		for (int i=0; i<m; i++){
			for (int j=0; j<n; j++){
				result[i][j] = first[i][j] + second[i][j]; // use '-' instead of '+' for substraction
			}
		}
		System.out.println("Addtion matrix is:");
		for (int i=0; i<m; i++){
			for (int j=0; j<n; j++){
				System.out.print(result[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void multiply(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of rows and columns of first matrix");
		int m = in.nextInt();
		int n = in.nextInt();
		int first[][]=new int[m][n];
		System.out.println("Enter first matrix");
		for (int i=0; i<m; i++){
			for (int j=0; j<n; j++){
				first[i][j]=in.nextInt();
			}
		}

		System.out.println("Enter the number of rows and columns of second matrix");
		int p = in.nextInt();
		int q = in.nextInt();
		if (n!=p) System.out.println("Matrices with entered orders can't be multiplied with each other.");
		else {
			int second[][]=new int[p][q];
			int multiply[][] = new int[m][q];
			System.out.println("Enter second matrix");
			for (int i=0; i<p; i++){
				for (int j=0; j<q; j++){
					second[i][j]=in.nextInt();
				}
			}
			int sum=0, i=0, j=0, k=0;
			for(i=0; i<m; i++){
				for (j=0; j<p; j++){
					for (k=0; k<n; k++){
						sum = sum + first[i][k]*second[k][j];
					}
					multiply[i][j]=sum;
					sum=0;
				}
			}
			System.out.println("Product matrix is:");
			for (i=0; i<m; i++){
				for (j=0; j<n; j++){
					System.out.print(multiply[i][j] + "\t");
				}
				System.out.println();
			}
		}
	}

	/*
In linear algebra, the transpose of a matrix A is another matrix created by any one of the following equivalent actions:
reflect A over its main diagonal (which runs from top-left to bottom-right) to obtain AT
write the rows of A as the columns of AT
write the columns of A as the rows of AT
	 */
	public static void transpose(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of rows and columns of matrix");
		int m = in.nextInt();
		int n = in.nextInt();
		int first[][]=new int[m][n];
		System.out.println("Enter matrix");
		for (int i=0; i<m; i++){
			for (int j=0; j<n; j++){
				first[i][j]=in.nextInt();
			}
		}
		int[][] transposeMatrix = new int[n][m];
		for (int i=0; i<m; i++){
			for (int j=0; j<n; j++){
				transposeMatrix[j][i] = first[i][j];	
			}
		}
		System.out.println("Transpose matrix is:");
		for (int i=0; i<n; i++){
			for (int j=0; j<m; j++){
				System.out.print(transposeMatrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
