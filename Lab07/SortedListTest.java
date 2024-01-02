import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.FixMethodOrder;


/*
 * You must include the Junit4 library to use this test unit.
 * 
 * The simple way to add it is to hover your mouse over one of the @Test annotations below 
 *   and accept Eclipse's offer to fix the problem by adding JUnit4 to the build path.
 *
 * The long way is to select the project and then choose Properties... from the File menu.
 * Click on "Java Build Path" in the panel at the left.
 * Select Libraries from the list at the top.
 * Select Add Library... from the right side of the panel.
 * Select JUnit and click on Next.
 * Change the version to JUnit 4 and click on Finish.
 * Click on OK in the properties panel.
 * 
 * You can then run these tests using "Run As JUnit Test" instead of "Runs As Java Application".
 */


/**
 * JUnit tests for SortedList.
 *
 * @author Chad Hogg, Jordan Rios
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SortedListTest {

	/**
	 * Creates a default SortedList and confirms that it was initialized correctly.
	 */
	@Test
	public void test01Constructor() {
		SortedList list = new SortedList();
		assertEquals("New list had wrong size.", 0, list.size());
		assertEquals("New list toString was wrong.", "( )", list.toString());
	}

	/**
	 * Inserts one element into a SortedList.
	 */
	@Test
	public void test02InsertOne() {
		SortedList list = new SortedList();
		list.insert(4.0);
		assertEquals("Inserting 4.0 to empty list yielded wrong size.", 1, list.size());
		assertEquals("Inserting 4.0 to empty list yielded wrong string.", "( 4.0 )", list.toString());
	}
	
	/**
	 * Inserts multiple elements into a SortedList.
	 */
	@Test
	public void test03InsertMultiple() {
		SortedList list = new SortedList();
		list.insert(4.5);
		list.insert(3.0);
		list.insert(2.0);
	
		assertEquals("Inserting 4.5, 3.0, 2.0 to empty list yielded wrong size.", 3, list.size());
		assertEquals("Inserting 4.0 to empty list yielded wrong string.", "( 2.0 3.0 4.5 )", list.toString());
	}
	
	/**
	 * Removes multiple elements from a list.
	 */
	@Test
	public void test04RemoveMultiple() {
        SortedList list = new SortedList();
        list.insert(1.5);
        list.insert(2.0);
        list.insert(7.0);

        list.removeAt(0);
        list.removeAt(0);
        list.removeAt(0);
        assertEquals("Removing produced the wrong size.", 0, list.size());
        assertEquals("Removing produced the wrong string.", "( )", list.toString());
    }
	
	/**
	 * Removes multiple elements from an empty SortedList.
	 */
	@Test
	public void test05RemoveFromEmptyList() {
        SortedList list = new SortedList();
 
        list.removeAt(0);
        assertEquals("Removing produced the wrong size.", 0, list.size());
        assertEquals("Removing produced the wrong string.", "( )", list.toString());
    }
	
	/**
	 * Removes a single element from a SortedList.
	 */
	@Test 
	public void test06RemoveOne() {
		 SortedList list = new SortedList();
		 list.insert(8.0);
		 
		 list.removeAt(0);
		 assertEquals("Removing produced the wrong size.", 0, list.size());
		 assertEquals("Removing produced the wrong string.", "( )", list.toString());
	}
	
	/**
	 * Removes an element from the beginning of a SortedList.
	 */
	@Test 
	public void test07RemoveFromBeginning() {
		 SortedList list = new SortedList();
		 list.insert(3.0);
		 list.insert(5.0);
		 list.insert(8.0);
		 
		 list.removeAt(0);
		 assertEquals("Removing produced the wrong size.", 2, list.size());
		 assertEquals("Removing produced the wrong string.", "( 5.0 8.0 )", list.toString());
	}
	
	/*
	 * Removes an element from the middle of a SortedList.
	 */
	@Test 
	public void test08RemoveFromMiddle() {
		 SortedList list = new SortedList();
		 list.insert(3.0);
		 list.insert(5.0);
		 list.insert(8.0);
		 
		 list.removeAt(1);
		 assertEquals("Removing produced the wrong size.", 2, list.size());
		 assertEquals("Removing produced the wrong string.", "( 3.0 8.0 )", list.toString());
	}
	
	/**
	 * Removes an element from the end of a SortedList.
	 */
	@Test 
	public void test09RemoveFromEnd() {
		 SortedList list = new SortedList();
		 list.insert(8.0);
		 list.insert(7.0);
		 list.insert(9.0);
		 
		 list.removeAt(2);
		 assertEquals("Removing produced the wrong size.", 2, list.size());
		 assertEquals("Removing produced the wrong string.", "( 7.0 8.0 )", list.toString());
	}
	
	/*
	 * Finds a particular element at the beginning of a SortedList.
	 */
	@Test
	public void test10FindAtBeginning() {
		SortedList list = new SortedList();
		list.insert(2.0);
		list.insert(6.0);
		list.insert(7.0);
		
		list.find(2.0);
		assertEquals("Removing produced the wrong size.", 3, list.size());
		assertEquals("Removing produced the wrong string.", "( 2.0 6.0 7.0 )", list.toString());
	}
	
	/*
	 * Finds a particular element in the middle of a SortedList.
	 */
	@Test
	public void test11FindInMiddle() {
		SortedList list = new SortedList();
		list.insert(1.0);
		list.insert(8.0);
		list.insert(10.0);
		
		list.find(8.0);
		assertEquals("Removing produced the wrong size.", 3, list.size());
		assertEquals("Removing produced the wrong string.", "( 1.0 8.0 10.0 )", list.toString());
	}
	
	/*
	 * Finds a particular element at the end SortedList.
	 */
	@Test
	public void test12FindAtEnd() {
		SortedList list = new SortedList();
		list.insert(2.0);
		list.insert(5.0);
		list.insert(11.0);
		
		list.find(11.0);
		assertEquals("Removing produced the wrong size.", 3, list.size());
		assertEquals("Removing produced the wrong string.", "( 2.0 5.0 11.0 )", list.toString());
	}
	
	/*
	 * Finds a particular element in a SortedList that is null.
	 */
	@Test
	public void test13FindNull() {
		SortedList list = new SortedList();
		
		list.find(6.0);
		assertEquals("Removing produced the wrong size.", 0, list.size());
		assertEquals("Removing produced the wrong string.", "( )", list.toString());
	}
	
}
