package com;
/*
 * A prime number (or a prime) is a natural number greater than 1 
 * that has no positive divisors other than 1 and itself. 
 * A natural number greater than 1 that is not a prime number is called a composite number.
 */
public class PrimeNumbers {

	public static void main(String[] args) {
		int n=100;
		if (n<2) System.out.println("There is no prime number");
		System.out.printf("Prime numbers between 1 to %d are: ", n);
		for (int i=1; i<=n ; i++){
			if (isPrime(i))
				System.out.print(i + " ");
		}

	}
	// Method to check whether the number is prime or not
	public static boolean isPrime(int num) {
		if ( num < 2 ) return false;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if ( num % i == 0 ) {
				return false;
			}
		}
		return true;
	}
}
