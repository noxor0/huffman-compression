package huffmantree;

import java.util.HashMap;

public final class FrequencyTable {
	LinkedList<TreeNode<String>> myTable;
	public FrequencyTable(final String theString) {
		myTable = makeTable(theString);
	}
	/**
	 * Fills in the bit code values of each TreeNode<String> value
 	 * 
	 * @param theNewTable a table that now includes the bit codes
	 */
	public void updateTable(final LinkedList<TreeNode<String>> theNewTable) {
		myTable = theNewTable;
	}
	/**
	 * Adds the letter's bitcode value
	 * 
	 * @param theEncodeMap the map that holds the bitcode values
	 */
	public void addEncodedValues(HashMap<Character, String> theEncodeMap) {
		LinkedNode<TreeNode<String>> runner = myTable.getHead();
		while(runner != null) {
			char letter[] = runner.getData().getData().toCharArray();
			runner.getData().setCode(theEncodeMap.get(letter[0]));
			runner = runner.getNext();
		}
	}
	/**
	 * Creates a frequency table of the provided string.
	 * 
	 * @param theString string to parse
	 * @return a linked list of characters and frequencies.
	 */
	private LinkedList<TreeNode<String>>makeTable(final String theString) {
		LinkedList<TreeNode<String>> frequencyTable = new LinkedList<>();
		char[] charArray = theString.toCharArray();
		LinkedNode<TreeNode<String>> runner = frequencyTable.getHead();
		//add in order?
		for (char letter : charArray) {
			boolean isContained = false;
			runner = frequencyTable.getHead();
			while (runner != null && !isContained) {
				if(runner.getData().getData().equals("" + letter)) {
					isContained = true;
				} else {
					runner = runner.getNext();
				}
			}
			if(isContained) {
				runner.getData().addFreq();
			} else {	
				frequencyTable.add(new TreeNode<String>("" + letter));
			}
		}
		if (frequencyTable.getSize() < 2) {
			frequencyTable.clear();
			frequencyTable.add(new TreeNode<String>("" + '2'));
			frequencyTable.add(new TreeNode<String>("" + 'S'));
			frequencyTable.add(new TreeNode<String>("" + 'M'));
			frequencyTable.add(new TreeNode<String>("" + 'A'));
			frequencyTable.add(new TreeNode<String>("" + 'L', 2));
		}
		return frequencyTable;
	}
	public LinkedList<TreeNode<String>> getTable() {
		return myTable;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		LinkedNode<TreeNode<String>> runner = myTable.getHead();
		while(runner != null) {
			sb.append(runner.getData().toString());
			runner = runner.getNext();
		}
		return sb.toString();
	}
	
}
