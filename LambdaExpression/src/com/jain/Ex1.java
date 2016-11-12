package com.jain;

interface Formula {
    double calculate(int a);

    //NEW FEATURE
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}

public class Ex1{
	
	Formula formula = new Formula(){
		public double calculate(int a){
			return a*100;
		}
	};
	
	public static void main(String[] args){
		Ex1 ex1 = new Ex1();
		System.out.println(ex1.formula.calculate(7));
		System.out.println(ex1.formula.sqrt(7));
	}
	
}