package huffmantree;

public final class LinkedList<T> {
	private LinkedNode<T> myHead;
	private LinkedNode<T> myRunner;
	private LinkedNode<T> myTail;
	private int mySize;
	
	public LinkedList(){
		myHead = null;
		myRunner = null;
		myTail = null;
		mySize = 0;
	}
	public LinkedList(LinkedNode<T> theHead) {
		myHead = theHead;
		myRunner = theHead;
		mySize = 1;
		while(myRunner.getNext() != null) {
			mySize++;
			myRunner = myRunner.getNext();
			myTail = myRunner;
		}
	}
	/**
	 * Adds a node to the end of the list.
	 * 
	 * @param theElement the data to add to a node
	 */
	public void add(final T theElement) {
		if (myHead == null) {
			myHead = new LinkedNode<T>(theElement);
			myTail = myHead;
			mySize++;
		} else {
//			if (myTail == null) {
//				myTail = myHead;
//			}
			myRunner = myTail;				
			myRunner.setNext(new LinkedNode<T>(theElement));
			myTail = myRunner.getNext();
			mySize++;
		}
	}
	/**
	 * Sets node.next at thePosition to a new node that contains theElement.
	 * 
	 * @param theElement the element to add.
	 * @param thePosition the position to add it to.
	 */
	public void add(final T theElement, final int thePosition) {
		goToNode(thePosition);
		LinkedNode<T> newNode = new LinkedNode<T>(theElement);
		newNode.setNext(myRunner.getNext());
		myRunner.setNext(newNode);
		mySize++;
	}
	public void addHead(final T theElement) {
		LinkedNode<T> newFront = new LinkedNode<T>(theElement);
		newFront.setNext(myHead);
		myHead = newFront;
		mySize++;
	}
	public boolean contains(final T theContainer) {
		LinkedNode<T> runner = myHead;
		boolean isContained = false;
		for(int i = 0; i < mySize && !isContained; i++) {
			if (runner.getData().equals(theContainer)) {
				isContained = true;
			}
			runner = runner.getNext();
		}
		return isContained;
	}
	/**
	 * Moves the runner to a position
	 * 
	 * @param thePosition the position to move to
	 * @return the runner at thePosition
	 */
	public LinkedNode<T> goToNode(final int thePosition) {
		if (thePosition >= mySize - 1) {
			myRunner = myTail;
		} else if (thePosition <= 0) {
			myRunner = myHead;
		} else {
			myRunner = myHead;
			for (int i = 0; i != thePosition; i++) {
				myRunner = myRunner.getNext();
			}
		}
		return myRunner;
	}
	/**
	 * Goes to the node with the data specified
	 * 
	 * @param theData
	 * @return the node with the containting data
	 */
	public LinkedNode<T> gotoNode(final T theData) {
		LinkedNode<T> runner = myHead;
		boolean isMatch = false;
		while(runner != null && isMatch == false) {
			if(runner.getData().equals(theData)) {
				isMatch = true;
			} 
			runner = runner.getNext();
		}	
		if (isMatch == false) {
			runner = null;
		}
		return runner;
	}
	/**
	 * Remove the head of the list.
	 */
	public void remove() {
		myHead = myHead.getNext();
		mySize--;
	}
	public void removeLast() {
		myRunner = myHead;
		while (myRunner.getNext().getNext() != null) {
			myRunner = myRunner.getNext();
		}
		myTail = myRunner;
		myRunner.setNext(null);
		mySize--;
		}
	/**
	 * Remove a value at a position.
	 * 
	 * @param thePosition the position of the value to be removed
	 */
	public void removeAt(final int thePosition) {
		if (thePosition >= mySize - 1) {
			removeLast();
		} else if (thePosition <= 0) {
			remove();
		} else {
			myRunner = goToNode(thePosition - 1);
			myRunner.setNext(myRunner.getNext().getNext());
		}
	}
	public void clear() {
		while(myHead != null) {
			remove();
		}
		mySize = 0;
	}
	public void Update() {
		myTail = myHead;
		while(myTail.getNext() != null) {
			myTail = myTail.getNext();
		}
	}
 	public int getSize() {
		return mySize;
	}
	public LinkedNode<T> getHead() {
		return myHead;
	}
	public LinkedNode<T> getTail() {
		return myTail;
	}
	public String getList(){
		final StringBuilder sb = new StringBuilder();
		if (myHead == null) {
			sb.append("empty");
		} else {
			myRunner = myHead;
			while (myRunner != null) {
				sb.append(myRunner.toString());
				myRunner = myRunner.getNext();
			}
		}
		return sb.toString();
	}
}