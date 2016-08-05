package huffmantree;

public final class LinkedNode<T> implements Comparable<T>{
	//add counter?
	private T myData;
	//private?
	private LinkedNode<T> myNext;
	public LinkedNode(final T theElement) {
		myData = theElement;
		myNext = null;
	}
	public LinkedNode(final LinkedNode<T> theNode) {
		 myData = theNode.getData();
		 this.setNext(theNode.getNext());
	}
	public T getData() {
		T returnMe = myData;
		return returnMe;
	}
	public void setData(final T theData) {
		myData = theData;
	}
	public LinkedNode<T> getNext() {
		return myNext;
	}
	public void setNext(final LinkedNode<T> theNode) {
		myNext = theNode;
	}
	public String toString() {
		return myData.toString();
	}
	//I'm sure ill be fine
	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(T theOther) {
		return  ((Comparable<T>) this.getData()).compareTo(theOther);
	}
	
}
