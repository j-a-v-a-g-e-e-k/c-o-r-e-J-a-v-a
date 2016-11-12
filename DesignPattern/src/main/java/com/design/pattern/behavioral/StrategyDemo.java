package com.design.pattern.behavioral;

import java.util.ArrayList;
import java.util.List;

interface PaymentStrategy{
	public void pay();
}

class CreditCardStrategy implements PaymentStrategy{
	String name;
	String ccNum;
	String expirationDate;
	String cvv;
	CreditCardStrategy(String name, String ccNum, String expirationDate, String cvv){
		this.name = name;
		this.ccNum = ccNum;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
	}
	public void pay(){
		System.out.println(" paid through CreditCard");
	}
}

class PaypalStrategy implements PaymentStrategy{
	String email;
	String password;
	PaypalStrategy(String email, String password){
		this.email = email;
		this.password = password;
	}
	public void pay(){
		System.out.println(" paid through Paypal");
	}
}

class Item{
	String name;
	int price;
	Item(String name, int price){
		this.name = name;
		this.price = price;
	}
}

class ShoppingCart{
	List<Item> cart = new ArrayList<Item>();
	int total;
	public void addItem(Item item){
		cart.add(item);
		total= total + item.price;
	}
	public void pay(PaymentStrategy strategy){
		System.out.print("$" + total);
		strategy.pay();
	}	
}

public class StrategyDemo {
	public static void main(String [] args){
		ShoppingCart cart = new ShoppingCart();
		Item item1=new Item("Milk", 3);
		Item item2=new Item("Bread", 4);
		cart.addItem(item1);		
		cart.addItem(item2);
		cart.pay(new PaypalStrategy("bijain@gmail.com", "password"));
		cart.pay(new CreditCardStrategy("bimal jain", "123456789", "10/10", "420"));
	}
}
