package com;

public class Traingle {

	public static void main(String [] args){
		int rows = 6;
		output1(rows);
		System.out.println("");
		output2(rows);
		System.out.println("");
		output3(rows);
		System.out.println("");
		output4(rows);
	}

	/* Display Triangle as follow. This is also called Floyd's triangle
1 
2 4 
3 6 9 
4 8 12 16 
	 */
	public static void output1(int n){
		for(int i=1; i<=n; i++){
			for(int j=1; j<=i; j++){
				System.out.print((i*j) + " ");
			}
			System.out.print("\n");
		}	
	}

	/* Display Triangle as follow
0 
1 0 
0 1 0 
1 0 1 0
	 */

	public static void output2(int n){
		for(int i=1; i<=n; i++){
			for(int j=1; j<=i; j++){
				System.out.print(((i+j)%2) + " ");
			}
			System.out.print("\n");
		}		
	}

	/* Display Triangle as follow
1 
2 3 
4 5 6 
	 */	
	public static void output3(int n){
		int c=0;
		loop1: for(int i=1; i<=n; i++){
			loop2: for(int j=1; j<=i; j++){
				if(c!=n){
					c++;
					System.out.print(c+" ");
				}
				else break loop1;
			}
		System.out.print("\n");
		}
	}

	/* Display Triangle as follow
 1 
 2  2 
 3  3  3 
 4  4  4  4 
 5  5  5  5  5  
	 */	
	public static void output4(int n){
		for(int i=1;i<=n;i++){
			for(int j=1;j<=i;j++){
				System.out.print(" "+i+" ");
			}
			System.out.print("\n");
		}		
	}
}
