package _010_lambda;

public class _010_Lambda {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				System.out.println("In another thread");
			}
		});
		t1.start();		    
		System.out.println("In main");
		
		/*
		 function has 4 things
			1. name - anonymous
      				2. parameter list
      				3. body
			4. return type - inferred
		 */

		//LAMBDA expression
		Thread t2 = new Thread(() -> System.out.println("Lambda: In another thread"));
		t2.start();
		System.out.println("In main again");		    
	}
}