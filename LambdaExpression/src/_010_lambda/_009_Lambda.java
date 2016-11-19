package _010_lambda;

public class _009_Lambda {
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
		
//Each lambda expression can be implicitly assigned to one of the interface called Functional interface. For example we can create 
//Runnable interface’s reference from lambda expression like below:
		Runnable r = () -> System.out.println("Lambda: In another thread");
		
//This type of conversion is automatically taken care by compiler when we don't specify the functional interface. So the compiler automatically 
//deduced that lambda expression can be casted to Runnable interface from Thread class’s constructor signature public Thread(Runnable r) { }.
		new Thread(() -> System.out.println("Lambda: In another thread"));
		
	}
}
