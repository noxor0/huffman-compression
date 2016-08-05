package huffmantree;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Stack;

/**
 * A binary tree structure. 
 * Encodes, and decodes a string.
 * 
 * @author noxor
 * @param <T> The data field the tree
 */
public final class Tree<T> {
	private TreeNode<String> myRoot;
	private String myString;
	private FrequencyTable myFreqTable;
	private final HashMap<Character, String> encodeMap;
	private final HashMap<String, Character> decodeMap;
	
	public Tree(final TreeNode<String> theRoot) {
		myRoot = theRoot;
		encodeMap = new HashMap<Character, String>();
		decodeMap = new HashMap<String, Character>();
	}
	public Tree(final String theString) {
		myRoot = null;
		encodeMap = new HashMap<Character, String>();
		decodeMap = new HashMap<String, Character>();
		myFreqTable = new FrequencyTable(theString);
		myString = theString;
	}
	/**
	 * Encrypts the parameter using the Huffman tree.
	 * 
	 * @param theString the String to encrypt
	 * @return changes theString to its compressed binary form
	 */
	public String encode() {
		StringBuilder encrypt3d = new StringBuilder();
		char[] letterArray = myString.toCharArray();
		buildMaps(myString);
		for(char letter : letterArray) {
			encrypt3d.append(encodeMap.get(letter));
		}
		return encrypt3d.toString();
	}
	/**
	 * Decodes a bitstream.
	 * 
	 * @param theString the bit code generated from the Huffman tree.
	 * @return returns bit code back to characters. 
	 */
	public static String decode(final String theString, final Tree<String> theTree) {
		StringBuilder decrypt3d = new StringBuilder();
		char[] letterArray = theString.toCharArray();
		TreeNode<String> runner = theTree.getRoot();
		for(char letter : letterArray) {
			if(letter == '0') {
				if(runner.getRight() == null) {
					decrypt3d.append(runner.getData());
					runner = theTree.getRoot();
				}
				runner = runner.getRight();
			} 
			if(letter == '1') {
				if(runner.getLeft() == null) {
					decrypt3d.append(runner.getData());
					runner = theTree.getRoot();
				}
				runner = runner.getLeft();
			}			
		}
		decrypt3d.append(runner.getData());
		return decrypt3d.toString();
	}
//	/**
//	 * Decodes a bitstream.
//	 * 
//	 * @param theString the bit code generated from the Huffman tree.
//	 * @return returns bit code back to characters. 
//	 */
//	public String decode(final String theString) {
//		StringBuilder decrypt3d = new StringBuilder();
//		char[] letterArray = theString.toCharArray();
//		LinkedList<Character> bitCodeList = new LinkedList<Character>();
//		for(char letter : letterArray) {
//			bitCodeList.add(letter);
//			if(decodeMap.get(bitCodeList.getList()) != null) {
//				decrypt3d.append(decodeMap.get(bitCodeList.getList()));
//				bitCodeList.clear();
//			}
//		}
//		return decrypt3d.toString();
//	}
	/**
	 * Finds the leaf node that contains the character, and adds the path to the hash map.
	 * Going left is a 0; Going right is a 1.
	 * 
	 * @param theRoot the Root node of the tree
	 * @param theLetter the Letter of the node
	 * @param thePath A stack that contains the current path.
	 * @param theDirection Simply adding the direction taken to the node.
	 */
	private void getLetterCode(final TreeNode<String> theRoot, 
			final Character theLetter, final Stack<Integer> thePath, final Integer theDirection) {
		if (theRoot == null) {
			return;
		}
		if (theRoot.getData().equals("" + theLetter)) {
			StringBuilder sb = new StringBuilder();
			for (Integer value : thePath) {
				sb.append(value.toString());
			}
			addToHash(theLetter, sb.toString());
		}
		getLetterCode(theRoot.getLeft(), theLetter, thePath, thePath.push(1));
		thePath.pop();
		getLetterCode(theRoot.getRight(), theLetter, thePath, thePath.push(0));
		thePath.pop();
	}
	
	/**
	 * @param theLetter the letter to add to the hashmap
	 * @param theBitEncode the letter's bitcode using the tree
	 */
	private void addToHash(final Character theLetter, final String theBitEncode) {
		encodeMap.put(theLetter, theBitEncode);
		decodeMap.put(theBitEncode, theLetter);
	}
	/**
	 * Builds the hash map that holds the binary equivalence of each character.
	 * 
	 * @param theString the String to build the encode map from.
	 */
	private void buildMaps(final String theString) {
		Stack<Integer> path = new Stack<>();
		FrequencyTable freqTable = new FrequencyTable(theString);
		TreeNode<String> treeRoot = CreateTree();
		LinkedNode<TreeNode<String>> runner = freqTable.getTable().getHead();
		while(runner != null) {
			char[] letterArray = runner.getData().getData().toCharArray();
			getLetterCode(treeRoot, letterArray[0], path, 0);
			runner = runner.getNext();
		}
	}
	/**
	 * Creates a Huffman tree from the frequency table.
	 * Frequency of each character (stored in strings) added when characters are combined.
	 * 
	 * @param theFreqTable a complex frequency table, that contains TreeNodes<String>
	 * @return the root node of a Tree of strings, with a frequency count.
	 */
	private TreeNode<String> CreateTree() {
		FrequencyTable freqCopy = new FrequencyTable(myString);
		freqCopy.updateTable(Sort.shellSort(freqCopy.getTable()));
		while (freqCopy.getTable().getSize() > 1) {
			LinkedNode<TreeNode<String>> head = freqCopy.getTable().getHead();
			LinkedNode<TreeNode<String>> next = head.getNext();
			String combineStr = head.getData().getData() + next.getData().getData();
			Integer combineFreq = head.getData().getFreq() + next.getData().getFreq();				
			freqCopy.getTable().add(new TreeNode<String>(combineStr, combineFreq,
					head.getData(), next.getData()));
			freqCopy.getTable().remove();
			freqCopy.getTable().remove();
			correctOrder(freqCopy);
		}
		myRoot = freqCopy.getTable().getHead().getData();
		return freqCopy.getTable().getHead().getData();
	}
	/**
	 * Okay i know this is messy, but it works! 
	 * The last item in the list was the new, merged tree node.
	 * This just takes the last item and moves it to its correct spot
	 * then removes the copy
	 * 
	 * I used to have it sort the entire list each time a new 'merged' node 
	 * was added. It was cool because I used my sort from the last assignment.
	 * Not even efficient though.
	 * 
	 * @param theFreqTable the table to pass in
	 */
	private void correctOrder(final FrequencyTable theFreqTable) {
		LinkedNode<TreeNode<String>> latest = theFreqTable.getTable().getTail();
		LinkedNode<TreeNode<String>> checkMe = theFreqTable.getTable().getHead();
		int pos = 0;
		while(!checkMe.equals(latest)) {
			if(latest.getData().getFreq() < checkMe.getData().getFreq()){
				theFreqTable.getTable().add(latest.getData(), pos - 1);
				theFreqTable.getTable().removeAt(theFreqTable.getTable().getSize() - 1);
				return;
			} else {
				checkMe = checkMe.getNext();				
			}
			pos++;
		}
	}
	/**
	 * Creates a string table of the Frequency Table and the characters bit codes
	 * 
	 * @return a visual display of the frequency table
	 */
	public String printTable() {
		myFreqTable.addEncodedValues(encodeMap);
		StringBuilder sb = new StringBuilder();
		sb.append("======================\n");
		sb.append("Char    Freq    Code\n");
		sb.append("----------------------\n");
		sb.append(myFreqTable.toString());
		sb.append("======================\n");
		return sb.toString();
	}
	/**
	 * Gives additional information about the compression.
	 * 
	 * @return a string of the bytes of both strings
	 */
	public String getStats() {
		StringBuilder sb = new StringBuilder();
		double before = myString.length() * 16 ;
		double after = encode().length();
		BigDecimal compression = new BigDecimal(100-after/before*100).setScale(2, RoundingMode.UP);
		sb.append("Bytes before encoding: " + before + "\n");
		sb.append("Bytes after encoding " + after +"\n");
		sb.append("Compression Ratio: " + compression +"%");
		return sb.toString();
	}
	public TreeNode<String> getRoot() {
		return myRoot;
	}
}
