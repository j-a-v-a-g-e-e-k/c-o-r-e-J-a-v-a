package com.design.pattern.behavioral;

import java.util.ArrayList;
import java.util.List;

interface Subject{
	public void register(Observer e);
	public void notifyObservers();
	public String getMessageFromSubject();
}

interface Observer{
	public void getUpdate();
	public void setSubject(Subject sub);
}

class Topic implements Subject{
	String msg;
	List<Observer> list = new ArrayList<Observer>();
	public void register(Observer e) {
		list.add(e);		
	}
	public void notifyObservers() {
		for (Observer observer : list)	
			observer.getUpdate();
	}
	public String getMessageFromSubject(){
		return msg;
	}
	public void setMessage(String msg){
		System.out.println("New message posted to topic: " + msg);
		this.msg = msg;
		notifyObservers();
		this.msg = null;
	}
}

class Subscriber implements Observer{
	String name;
	Subject topic;
	Subscriber(String name){
		this.name = name;
	}
	public void setSubject(Subject sub){
		topic = sub;
	}
	public void getUpdate() {
		if (topic.getMessageFromSubject() == null) 
			System.out.println("No new message");
		else 
			System.out.println(name + " consuming message " + topic.getMessageFromSubject());		
	}
}

public class ObservorDemo {
	public static void main(String[] args){
		Topic topic = new Topic();
		Subscriber sub1 = new Subscriber("Bimal");
		Subscriber sub2 = new Subscriber("Andy");
		Subscriber sub3 = new Subscriber("Sandy");
		sub1.setSubject(topic);
		sub2.setSubject(topic);
		sub3.setSubject(topic);
		topic.register(sub1);
		topic.register(sub2);
		topic.register(sub3);
		sub1.getUpdate();
		topic.setMessage("Hi Subs");
		sub1.getUpdate();
	}
}
