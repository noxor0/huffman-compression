package huffmantree;

public final class Sort {
	
	private Sort() {
		//don't do this
	}
	/**
	 * I know this is SUPER inefficient, but I really wanted to use my sort 
	 * that I wrote last assignment
	 * 
	 * @param theList
	 * @return
	 */
	public static <T> LinkedList<TreeNode<T>> shellSort(final LinkedList<TreeNode<T>> theList) {
		LinkedNode<TreeNode<T>> head = theList.getHead();
		LinkedNode<TreeNode<T>> pos1, pos2, pre1 = null;
		boolean hasSwap = true;
		pre1 = null;
		pos1 = head;
		pos2 = pos1.getNext();
		while(hasSwap) {
			hasSwap = false;
			while (pos2 != null) {
				if (pos1.getData().compareTo(pos2.getData()) > 0) {
					if (pre1 == null) {
						pos1.setNext(pos2.getNext());
						pos2.setNext(pos1);
						head = pos2;
						head.setNext(pos1);
						pos2 = pos1.getNext();
						pre1 = head;
						hasSwap = true;
					} else {
						pos1.setNext(pos2.getNext());
						pos2.setNext(pos1);
						pre1.setNext(pos2);
						pos2 = pos1.getNext();
						pre1 = pre1.getNext();
						hasSwap = true;
					}
				} else {
					if (pre1 == null) {
						pos1 = pos1.getNext();
						pos2 = pos2.getNext();
						pre1 = head;
					} else {
						pre1 = pre1.getNext();
						pos1 = pos1.getNext();
						pos2 = pos2.getNext();
					}
				}
			}
			pos1 = head;
			pos2 = pos1.getNext();
			pre1 = null;	
		}
		LinkedList<TreeNode<T>> returnList = new LinkedList<TreeNode<T>>();
		pos1 = head;
		while (pos1 != null) {
			returnList.add(pos1.getData());
			pos1 = pos1.getNext();
		}
		return returnList;
	}
}
	