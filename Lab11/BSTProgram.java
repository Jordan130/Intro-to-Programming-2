import java.util.Scanner;

/**
 * A simple program for testing BinarySearchTree.
 * 
 * @author William Killian, Chad Hogg
 */
public class BSTProgram {

	/**
	 * Runs the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		BinarySearchTree<Character> tree = new BinarySearchTree<Character>();
		
		System.out.print("Line of characters to insert: ");
		String data = console.next();
		for (char c : data.toCharArray()) {
			tree.add(c);
		}
		System.out.println("After inserting: " + tree.toString());
		System.out.println();
		System.out.println(tree);
		
		System.out.print("Line of characters to remove: ");
		data = console.next();
		for (char c : data.toCharArray()) {
			tree.remove(c);
		}
		System.out.println("After removing: " + tree.toString());
		System.out.println();
		System.out.println(tree);
		console.close();
	}

}
