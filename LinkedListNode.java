package from223;

public class LinkedListNode<T> {
	protected T data;
	protected LinkedListNode<T> next;
	
	public LinkedListNode(T newData){
		this.data = newData;
		this.next = null;
	}
	
	public LinkedListNode<T> getNext(){
		return this.next;
	}
	
	public void setNext(LinkedListNode<T> nextNode){
		this.next = nextNode;
	}
	
	public T getData(T newData){
		return this.data;
	}
	
	public void setData(T newData){
		this.data = newData;
	}
}
