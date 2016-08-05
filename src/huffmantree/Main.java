package huffmantree;

/**
 * Tester/main class
 * 
 * Okay this is really messy! Its not my best program...
 * It works though! I'm pretty sure it hit all the concepts you wanted!
 * Sorry its the most nicest. 
 * 
 * @author noxor
 * @version 1.0
 */
public final class Main {
	
	private Main() {
		//dont do me
	}
	/**
	 * Starts the program.
	 * @param theArgs the command line arguments
	 */
	public static void main(String[] theArgs) {
		String testString;
		if (theArgs.length != 0) {
			testString = theArgs[0];
		} else {
			testString = "A brown fox jumped over the lazy dog";
		}
		Tree<String> stringTree = new Tree<String>(testString);
		String compressed = stringTree.encode();
		System.out.println(testString);
		System.out.print(stringTree.printTable());
		System.out.println(compressed);
		System.out.println(stringTree.getStats());
		
		System.out.println("Decoded: " + Tree.decode(compressed, stringTree));
	}
}
 