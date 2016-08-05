package huffmantree;

/**
 * Node for the tree BST
 * 
 * @author noxor
 * @param <T> the data of the node (for this case it'll be a char)
 */
public final class TreeNode<T> implements Comparable<Object> {

	private TreeNode<T> myLeft;
	private TreeNode<T> myRight;
	private T myData;
	private Integer myFreq = 1;
	private String myCode = null;
	
	public TreeNode(final T theData, final Integer theFreq,
			final TreeNode<T> theLeftNode, final TreeNode<T> theRightNode) {
		myLeft = theLeftNode;
		myRight = theRightNode;
		myData = theData;
		myFreq = theFreq;
		
	}
	
	public TreeNode(final T theData, final Integer theFreq) {
		this(theData, theFreq, null, null);
	}
	
	public TreeNode(final T theData) {
		this(theData, 1, null, null);
	}
	/**
	 * @param theLeft the new left node
	 */
	public void setLeft(final TreeNode<T> theLeft) {
		myLeft = theLeft;
	}
	/**
	 * @param theRight the new right node
	 */
	public void setRight(final TreeNode<T> theRight) {
		myRight = theRight;
	}
	/**
	 * @param theData the new data of the node
	 */
	public void setData(final T theData) {
		myData = theData;
	}
	/**
	 * @param theFreq the new freq of the node
	 */
	public void setFreq(final Integer theFreq) {
		myFreq = theFreq;
	}
	/**
	 * @param theCode the new bit code for the node
	 */
	public void setCode(final String theCode) {
		myCode = theCode;
	}
	
	public boolean isLeaf() {
		return myLeft == null && myRight == null;
	}
	/**
	 * Left node or (0)
	 * @return the left node
	 */
	public TreeNode<T> getLeft() {
		return myLeft;
	}
	public String getCode() {
		return myCode;
	}
	/**
	 * Right node or (1)
	 * @return the right node 
	 */
	public TreeNode<T> getRight() {
		return myRight;
	}
	/**
	 * @return the Data of the node
	 */
	public T getData() {
		return myData;
	}
	/**
	 * @return the Freq of the node
	 */
	public Integer getFreq() {
		return myFreq;
	}
	public void addFreq() {
		myFreq++;
	}
	@Override
	public String toString() {
		return myData + "\t" + myFreq + "\t" + myCode + "\n";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(Object theOther) {
		return this.getFreq() - ((TreeNode<T>) theOther).getFreq();
	}
	
}
