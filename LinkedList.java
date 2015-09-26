package from223;

public class LinkedList<T> {
	private LinkedListNode<T> head;
	private LinkedListNode<T> tail;
	
	public LinkedList(){
		this.head = null;
	}
	
	public boolean isEmpty(){
		return this.head == null;
	}
	
	public void addFront(T node){
		LinkedListNode<T> newNode = new LinkedListNode<T>(node);
		if(isEmpty()){
			this.head = this.tail = newNode;
		} else{
			newNode.setNext(head);
			this.head = newNode;
		}
	}
	
	public T removeFront(){
		LinkedListNode<T> temp;
		if(!isEmpty()){
			temp = this.head;
			this.head = this.head.next;
			return temp.data;
		} else{
			return null;
		}
	}
	
	public void addEnd(T node){
		LinkedListNode<T> newNode = new LinkedListNode<T>(node);
		if(isEmpty()){
			this.head = this.tail = newNode;
		}else{
			this.tail.next = newNode;
		}
	}
}
