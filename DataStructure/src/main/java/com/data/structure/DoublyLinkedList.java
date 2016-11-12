package com.data.structure;

public class DoublyLinkedList<T> {
	DNode<T> head;
	DNode<T> tail;

	public void addFirst(T element){
		DNode<T> temp = new DNode<T>();
		temp.setElement(element);
		if (head == null){
			head=temp; tail=temp;
		} else{
			temp.setNext(head);
			head.setPrev(temp);
			head=temp;
		}		
	}

	public void addLast(T element){
		DNode<T> temp = new DNode<T>();
		temp.setElement(element);
		if (tail == null){
			head=temp; tail=temp;
		} else{
			temp.setPrev(tail);
			tail.setNext(temp);
			tail=temp;
		}
	}

	public void addAfter(T element, T after){

	}

	public void addBefore(T element, T before){

	}

	public void removeFirst(){
		if (head == null) System.out.println("nothing to remove");
		else if (head == tail){
			head=null; tail=null;			
		} else{
			DNode<T> temp=head.getNext();
			temp.setPrev(null);
			head.setNext(null);
			head=temp;
		}

	}

	public void removeLast(){
		if (tail == null) System.out.println("nothing to remove");
		else if (head == tail){
			head=null; tail=null;
		}else{
			DNode<T> temp = tail.getPrev();
			temp.setNext(null);
			tail.setPrev(null);
			tail=temp;
		}

	}

	public void removeAfter(T element){

	}

	public void removeBefore(T element){

	}

	public void iterateForward(){
		DNode<T> temp = head;
		if (temp == null) System.out.println("list is empty");
		while (temp != null){
			System.out.print(temp.getElement() + " ");
			temp=temp.getNext();
		}
		System.out.println();
	}

	public void iterateBackward(){
		DNode<T> temp = tail;
		if (temp == null) System.out.println("list is empty");
		while(temp != null){
			System.out.print(temp.getElement() + " ");
			temp=temp.getPrev();
		}
		System.out.println();
	}

	public int size(){

		return 0;
	}	

	public static void main(String a[]){
		DoublyLinkedList<Integer> dll = new DoublyLinkedList<Integer>();
		dll.addFirst(10);
		dll.addFirst(34);
		dll.addLast(56);
		dll.addLast(364);
		dll.iterateForward();
		dll.removeFirst();
		dll.removeLast();
		dll.iterateForward();
		dll.iterateBackward();
	}

}

class DNode<T>{
	T element;
	DNode<T> prev;
	DNode<T> next;
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}
	public DNode<T> getPrev() {
		return prev;
	}
	public void setPrev(DNode<T> prev) {
		this.prev = prev;
	}
	public DNode<T> getNext() {
		return next;
	}
	public void setNext(DNode<T> next) {
		this.next = next;
	}
}
