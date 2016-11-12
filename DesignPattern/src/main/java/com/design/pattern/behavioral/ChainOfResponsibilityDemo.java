package com.design.pattern.behavioral;

interface DispenserChain{
	void dispense(int amount);
	void setNextDispenser(DispenserChain dispenser);
}

class Dispenser$50 implements DispenserChain{
	DispenserChain dispenser;
	public void setNextDispenser(DispenserChain dispenser){
		this.dispenser = dispenser;
	}
	public void dispense(int amount){
		if (amount >=50){
			int num = amount/50;
			int remainder = amount%50;
			System.out.println("Dispensing " + num + " of $50 note");
			if (remainder != 0) dispenser.dispense(remainder);
		} else{
			dispenser.dispense(amount);
		}
	}
}

class Dispenser$20 implements DispenserChain{
	DispenserChain dispenser;
	public void setNextDispenser(DispenserChain dispenser){
		this.dispenser = dispenser;
	}
	public void dispense(int amount){
		if (amount >=20){
			int num = amount/20;
			int remainder = amount%20;
			System.out.println("Dispensing " + num + " of $20 note");
			if (remainder != 0) dispenser.dispense(remainder);
		} else{
			dispenser.dispense(amount);
		}
	}
}

class Dispenser$10 implements DispenserChain{
	DispenserChain dispenser;
	public void setNextDispenser(DispenserChain dispenser){
		this.dispenser = dispenser;
	}
	public void dispense(int amount){
		if (amount >=10){
			int num = amount/10;
			int remainder = amount%10;
			System.out.println("Dispensing " + num + " of $10 note");
			if (remainder != 0) dispenser.dispense(remainder);
		} else{
			dispenser.dispense(amount);
		}
	}
}

public class ChainOfResponsibilityDemo {
	public static void main(String [] args){
		int amount = 560;
		if (amount%10 != 0){
			System.out.println("Amount must be in multiple of 10");
		} else {
			Dispenser$50 c1 = new Dispenser$50();
			Dispenser$20 c2 = new Dispenser$20();
			Dispenser$10 c3 = new Dispenser$10();		
			c1.setNextDispenser(c2);
			c2.setNextDispenser(c3);
			c1.dispense(amount);
		}
	}
}
