package com.data.structure;

public class CircularSinglyLinkedList<T> {
	private CNode<T> head;
	private int size;
	
	public void addFirst(T element){
		CNode<T> node = new CNode<T>();
		node.setElement(element);
		if (head == null){
			head=node;
			node.setNext(head);
		} else {
			CNode<T> temp=head;
			while (temp.getNext() != head){
				temp=temp.getNext();
			}
			node.setNext(head);
			head=node;
			temp.setNext(head);			
		}
		size++;
	}
	
	public void addLast(T element){
		CNode<T> node = new CNode<T>();
		node.setElement(element);
		CNode<T> temp = head;
		if (temp == null){
			head = node;
			node.setNext(head);
		} else {
			while (temp.getNext() != head){
				temp=temp.getNext();
			}
			temp.setNext(node);
			node.setNext(head);
		}
		size++;
	}	
	
	public void remove(T element){
		CNode<T> temp = head;
		// traverse to the given node
		while(true){
			if (temp == null)break;
			if (temp.compareTo(element)==0)break;
			temp=temp.getNext();
		}
		// copy the content of next into the current, and delete next
		if (temp != null){
			if (temp.getNext()==head){
			temp.setElement(head.getElement());
			temp.setNext(head.getNext());
			head=temp;
		} else {
			CNode<T> nextNode = temp.getNext();
			temp.setElement(nextNode.getElement());
			temp.setNext(nextNode.getNext());
			nextNode=null;
		} 
		}else{
			System.out.println(element + " does not exits");
		}
	}
	
	public void traverse(){
		CNode<T> temp=head;
		while(temp.getNext() != head){
			System.out.print(temp.getElement() + " ");
			temp=temp.getNext();
		}
		if (temp.getNext() == head) System.out.print(temp.getElement() + " ");
		System.out.println();
	}
	
	public static void main(String a[]){
		CircularSinglyLinkedList<Integer> dll = new CircularSinglyLinkedList<Integer>();
		dll.addFirst(10);
		dll.addFirst(34);
		dll.traverse();
		
		dll.addLast(40);
		dll.addLast(42);
		dll.addFirst(11);
		dll.addFirst(36);
		dll.traverse();
		dll.remove(34);dll.traverse();
		dll.remove(42);dll.traverse();
	}
}

class CNode<T> implements Comparable<T>{
	private T element;
	private CNode<T> next;
	public CNode<T> getNext() {
		return next;
	}
	public void setNext(CNode<T> next) {
		this.next = next;
	}
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}
	public int compareTo(T o) {
		if (this.element.equals(o)) return 0;
		return 1;
	}

}
