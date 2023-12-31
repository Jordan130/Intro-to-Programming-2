
/**
 * A class that stores a list of doubles, keeping them in numerical order at all
 * times.
 * 
 * @author Beth Katz, Chad Hogg, Jordan Rios
 *
 */
public class SortedList {

	/**
	 * A node in a sorted list.
	 * 
	 * @author Chad Hogg
	 */
	private static class Node {
		/** The number stored in this node. */
		public double value;
		/** A reference to the node that comes after this one (null if none). */
		public Node next;

		/**
		 * Constructs a new Node.
		 * 
		 * @param value The number to store in the new node.
		 * @param next  The node that should come after the new one.
		 */
		public Node(double value, Node next) {
			this.value = value;
			this.next = next;
		}
	}

	// Class Invariant:
	// 1: 0 <= listLength
	// 2: listLength must be equal to the number of nodes in the list.

	/** The first node of this list, or null if there is none. */
	private Node head;
	/** The number of nodes in this list. */
	private int listLength;

	/**
	 * Constructs a new, empty list.
	 * 
	 * @postcondition This list has no nodes.
	 */
	public SortedList() {
		listLength = 0;
		head = null;
	}

	/**
	 * Inserts a new value into this list, maintaining the sorted order.
	 * 
	 * @param value The value to add.
	 * @postcondition The new value has been added to the list, after all elements
	 *                that are smaller than it and before all elements that are
	 *                greater than it.
	 */
	public void insert(double value) {
		Node previous = getPrecedingNode(value);
		Node newNode = new Node(value, head);
		if(previous == null) {
			head = newNode;
		}
		else {
			Node temp = new Node(value, previous.next);
			previous.next = temp;	
		}
		listLength++;
	}

	/**
	 * Gets the node that would come before the new one created to hold a value
	 * (also known as the last node that contains a number less than the value).
	 * 
	 * @param value A value that is going to be inserted.
	 * @return The last node that contains a number less than the value, or null if
	 *         no nodes contain numbers less than the value.
	 */
	private Node getPrecedingNode(double value) {
		Node current = head;
		Node previous = null;
		for(int i = 0; i < listLength; i++) {
			if(value < current.value) {
				return previous;
			}
			previous = current;
			current = current.next;
		}
		return previous;
	}

	/**
	 * Searches this list for a value.
	 * 
	 * @param value A number to search for.
	 * @return The first position at which that value appears (0 = first node, 1 =
	 *         second node, ...) or -1 if it not found anywhere.
	 */
	public int find(double value) {
		Node current = head;
		int index = 0;
		if (head == null) {
			return -1;
		}
		while (current != null) {
			if (value < current.value) {
				return -1;
			}
			if (value == current.value) {
				return index;
			}
			current = current.next;
			index++;

		}
		return -1;
	}

	/**
	 * Removes a node from this list.
	 * 
	 * @param index The position of the node to remove (0 = first node, 1 = second
	 *              node, ...).
	 * @return Whether or not such a node exists. (True if 0 <= index < size().)
	 * @postcondition If there was a node at that position, it has been removed.
	 */
	public boolean removeAt(int index) {
		/*
		 * Node current = head; int position = 0; boolean stop = true;
		 * while(current.next != null && stop) { position += 1; double curValue =
		 * current.value; if(curValue == value) { stop = false; } } return position;
		 */
		if (index < 0 || index >= listLength || listLength == 0) {
			return false;
		}
		if (index == 0 && listLength == 1) {
			head = head.next;
			listLength--;
			return true;
		}
		if(index == 0 && listLength != 0) {
			head = head.next;
			listLength--;
			return true;
		}
		Node previous = null;
		Node current = head;
		
		for(int i = 0; i < index; i++) {
			previous = current;
			current = current.next;
		}
		
		previous.next = current.next;
		listLength--;
		return true; 
	}

	/**
	 * Gets the number of elements in this list.
	 * 
	 * @return The number of elements in this list.
	 */
	public int size() {
		return listLength;
	}

	/**
	 * Gets a String representing this list.
	 * 
	 * @return A String like "( 2.0 4.0 5.0 )".
	 */
	@Override
	public String toString() {
		String answer = "( ";

		for (Node current = head; current != null; current = current.next) {
			answer += current.value + " ";
		}
		answer += ")";
		return answer;
	}
}
