package com.design.pattern.behavioral;

import java.util.ArrayList;
import java.util.List;

// Mediator base
interface ChatMediator{
	public void addUser (User user);
	public void send(User user, String msg);
}

// Colleague base
abstract class User{
	String name;
	protected ChatMediator chatMediator;
	User(ChatMediator chatMediator, String name){
		this.name = name;
		this.chatMediator = chatMediator;
	}
	public abstract void send(String msg);
	public abstract void receive(String msg);
}

// Concrete Colleague 
class UserImpl extends User{
	UserImpl(ChatMediator chatMediator, String name) {
		super(chatMediator, name);
	}
	public void send(String msg){
		System.out.println(this.name + " sent msg: " + msg);
		chatMediator.send(this, msg);
	}
	public void receive(String msg){
		System.out.println(this.name + " received msg: " + msg);
	}
}

// Concrete mediator
class ChatMediatorImpl implements ChatMediator{
	List<User> users = new ArrayList<User>();
	public void addUser(User user) {
		users.add(user);
	}
	public void send(User user, String msg) {
		for (User usr : users){
			if (!(usr.equals(user)))
				usr.receive(msg);
		}
	}
}

// client
public class MediatorDemo {
	public static void main (String [] args){
		ChatMediator mediator = new ChatMediatorImpl();
		User user1 = new UserImpl(mediator, "Dan");
		User user2 = new UserImpl(mediator, "Cathy");
		User user3 = new UserImpl(mediator, "Yali");
		User user4 = new UserImpl(mediator, "Sandy");
		mediator.addUser(user1);
		mediator.addUser(user2);
		mediator.addUser(user3);
		mediator.addUser(user4);
		user1.send("Hi All");
	}
}
