package com.data.structure;

public class SinglyLinkedList<T> {
	Node<T> head;
	Node<T> tail;

	public void add(T value){
		Node<T> node = new Node<T>();
		node.setElement(value);
		if (head == null){
			head=node;
			tail=node;
		} else {
			tail.setNextRef(node);
			tail=node;
		}
	}
	
	public void delete(T value){
		
	}

	public void addAfter(T value, T after){
		Node<T> node = new Node<T>();
		node.setElement(value);
		Node<T> temp = head;
		while(true){
			if (temp == null) break;
			if (temp.compareTo(after) == 0) break;
			temp=temp.getNextRef();			
		}
		if (temp != null){
			node.setNextRef(temp.getNextRef());
			temp.setNextRef(node);
			if (temp == tail) tail=node;
		} else{
			System.out.println(after + " does not exists");
		}
	}

	public void deleteAfter(T after){
		Node<T> temp = head;
		while(true){
			if (temp == null) break;
			if (temp.compareTo(after) == 0) break;
			temp=temp.nextRef;
		}
		if (temp != null){
			temp.setNextRef(temp.getNextRef().getNextRef());
			if (temp.getNextRef() == null) tail=temp;
		} else {
			System.out.println(after + " does not exists");
		}
	}

	public void deleteFront(){
		if (head == null){
			System.out.println("underflow");
		}
		if (head.getNextRef() == null){
			head=null;
			tail=null;
		}
		if (head.getNextRef() != null){
			head = head.getNextRef();
		}
	}

	public void traverse(){
		Node<T> temp = head;
		if (temp != null){
			while (true){
				if (temp == null) break;
				System.out.print(temp.getElement() + " ");
				temp=temp.getNextRef();
			}
			System.out.println();
		}
	}

	public static void main(String a[]){
		SinglyLinkedList<Integer> sl = new SinglyLinkedList<Integer>();
		sl.add(3);
		sl.add(32);
		sl.add(54);
		sl.add(89);
		sl.traverse();
		sl.addAfter(76, 54);
		sl.traverse();
		sl.deleteAfter(76);
		sl.traverse();
		sl.deleteFront();
		sl.traverse();
	}
}

class Node<T> implements Comparable<T>{
	T element;
	Node<T> nextRef;
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}
	public Node<T> getNextRef() {
		return nextRef;
	}
	public void setNextRef(Node<T> nextRef) {
		this.nextRef = nextRef;
	}
	public int compareTo(T o) {
		if (this.element == o) return 0;
		else return 1;
	}
}
