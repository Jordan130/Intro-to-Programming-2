
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.FixMethodOrder;


/*
 * You must include the Junit4 library to use this test unit.
 *
 * To add it, select the project and then choose Properties... from the File menu.
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
 * JUnit tests for ArrayDoubleSequence.
 *
 * @author Chad Hogg
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P02TestFunctionality {
	
	/** The maximum allowable difference when comparing doubles. */
	private static final double EPSILON = 0.00001;
	/** The start of the message when size is wrong. */
	private static final String WRONG_SIZE = "Wrong size";
	/** The start of the message when capacity is wrong. */
	private static final String WRONG_CAPACITY = "Wrong capacity";
	/** The start of the message when current element is wrong. */
	private static final String WRONG_CURRENT = "Wrong current element";
	/** The start of the message when string is wrong. */
	private static final String WRONG_STRING = "Wrong toString value";
	/** The start of the message when should have had a current element. */
	private static final String NEED_CURRENT = "Should have had current element";
	/** The start of the message when should not have had a current element. */
	private static final String UNEXPECTED_CURRENT = "Should not have had current element";
	
	/**
	 * Creates a message string for an assertion.
	 * 
	 * @param type The type of error.
	 * @param action The action that was taken.
	 * @param description The object the action was applied to.
	 * @return The concatenation of this information into a sentence.
	 */
	private String message(String type, String action, String description) {
		return type + " after " + action + " " + description + ".";
	}
	
	/**
	 * Creates a default ArrayDoubleSequence and confirms that it was initialized correctly.
	 */
	@Test
	public void test01ConstructorNoParameters() {
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		assertEquals("New default sequence had wrong capacity.", 10, sequence.getCapacity());
		assertEquals("New default sequence had wrong size.", 0, sequence.size());
		assertFalse("New default sequence should not have had current element.", sequence.isCurrent());
		assertEquals("New default sequence toString was wrong.", "empty sequence", sequence.toString());
	}

	/**
	 * Creates an ArrayDoubleSequence with a capacity of 5 and confirms that it was initialized correctly.
	 */
	@Test
	public void test02ConstructorCapacity5() {
		ArrayDoubleSequence sequence = new ArrayDoubleSequence(5);
		assertEquals("New capacity 5 sequence had wrong capacity.", 5, sequence.getCapacity());
		assertEquals("New capacity 5 sequence had wrong size.", 0, sequence.size());
		assertFalse("New capacity 5 sequence should not have had current element.", sequence.isCurrent());
		assertEquals("New capacity 5 sequence toString was wrong.", "empty sequence", sequence.toString());		
	}

	/**
	 * Creates an ArrayDoubleSequence with a capacity of 0 and confirms that it was initialized correctly.
	 */
	@Test
	public void test03ConstructorCapacity0() {
		ArrayDoubleSequence sequence = new ArrayDoubleSequence(0);
		assertEquals("New capacity 5 sequence had wrong capacity.", 0, sequence.getCapacity());
		assertEquals("New capacity 5 sequence had wrong size.", 0, sequence.size());
		assertFalse("New capacity 5 sequence should not have had current element.", sequence.isCurrent());
		assertEquals("New capacity 5 sequence toString was wrong.", "empty sequence", sequence.toString());		
	}

	/**
	 * Creates an ArrayDoubleSequence with a capacity of -3 and confirms that it throws an exception.
	 */
	@Test
	public void test04ConstructorCapacityNegative() {
		try {
			@SuppressWarnings("unused")
			ArrayDoubleSequence sequence = new ArrayDoubleSequence(-3);
			fail("Creating a sequence with negative capacity did not throw an exception.");
		}
		catch(IllegalArgumentException exception) {
			// expected
		}
		catch(Exception exception) {
			fail("Creating a sequence with negative capacity threw the wrong kind of exception.");
		}
	}
	
	/**
	 * Calls addBefore on an empty sequence.
	 */
	@Test
	public void test05AddBeforeEmpty() {
		final String ACTION  = "adding (before) 5.0 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		final String DESCRIPTION = sequence.toString();
		sequence.addBefore(5.0);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 1, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 5.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "(5.0) ", sequence.toString());
	}
	
	/**
	 * Calls addBefore on a sequence with 1 element that is the current.
	 */
	@Test
	public void test06AddBeforeHasOneCurrent() {
		final String ACTION = "adding (before) 3.0 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(5.0);
		final String DESCRIPTION = sequence.toString();
		sequence.addBefore(3.0);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 2, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 3.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "(3.0) 5.0 ", sequence.toString());
	}
	
	/**
	 * Calls advance on a sequence with 1 element that is the current.
	 */
	@Test
	public void test07AdvanceHasOneCurrent() {
		final String ACTION = "advancing";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(8.0);
		final String DESCRIPTION = sequence.toString();
		sequence.advance();
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 1, sequence.size());
		assertFalse(message(UNEXPECTED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "8.0 ", sequence.toString());
	}
	
	/**
	 * Calls addBefore on a sequence with 1 element and no current.
	 */
	@Test
	public void test08AddBeforeOneNoCurrent() {
		final String ACTION = "adding (before) 9.0 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(6.0);
		sequence.advance();
		final String DESCRIPTION = sequence.toString();
		sequence.addBefore(9.0);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 2, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 9.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "(9.0) 6.0 ", sequence.toString());
	}
	
	/**
	 * Calls addBefore on a sequence with 2 elements, first is current.
	 */
	@Test
	public void test09AddBeforeTwoFirstCurrent() {
		final String ACTION = "adding (before) 5.0 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(8.0);
		sequence.addBefore(2.0);
		final String DESCRIPTION = sequence.toString();
		sequence.addBefore(5.0);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 3, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 5.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "(5.0) 2.0 8.0 ", sequence.toString());
	}

	/**
	 * Calls addBefore on a sequence with 2 elements, second is current.
	 */
	@Test
	public void test10AdvanceHasTwoFirstCurrent() {
		final String ACTION = "advancing";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(8.0);
		sequence.addBefore(2.0);
		final String DESCRIPTION = sequence.toString();
		sequence.advance();
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 2, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 8.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "2.0 (8.0) ", sequence.toString());
	}

	/**
	 * Calls addBefore on a sequence with 2 elements, first is current.
	 */
	@Test
	public void test11AdvanceHasTwoSecondCurrent() {
		final String ACTION = "advancing";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(8.0);
		sequence.addBefore(2.0);
		sequence.advance();
		final String DESCRIPTION = sequence.toString();
		sequence.advance();
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 2, sequence.size());
		assertFalse(message(UNEXPECTED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "2.0 8.0 ", sequence.toString());
	}
	
	/**
	 * Calls addBefore on a sequence with 2 elements, second is current.
	 */
	@Test
	public void test12AddBeforeTwoFirstCurrent() {
		final String ACTION = "adding (before) 5.0 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(8.0);
		sequence.addBefore(2.0);
		sequence.advance();
		final String DESCRIPTION = sequence.toString();
		sequence.addBefore(5.0);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 3, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 5.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "2.0 (5.0) 8.0 ", sequence.toString());
	}

	/**
	 * Calls addBefore on a sequence with 2 elements, neither is current.
	 */
	@Test
	public void test13AddBeforeTwoNoCurrent() {
		final String ACTION = "adding (before) 5.0 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(8.0);
		sequence.addBefore(2.0);
		sequence.advance();
		sequence.advance();
		final String DESCRIPTION = sequence.toString();
		sequence.addBefore(5.0);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 3, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 5.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "(5.0) 2.0 8.0 ", sequence.toString());
	}
	
	/**
	 * Calls advance on a sequence with 2 elements, none is current.
	 */
	@Test
	public void test14AdvanceHasTwoNoCurrent() {
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(8.0);
		sequence.addBefore(2.0);
		sequence.advance();
		sequence.advance();
		try {
			sequence.advance();
			fail("Advancing a sequence without a current element should have thrown an exception.");
		}
		catch(IllegalStateException exception) {
			// expected
		}
		catch(Exception exception) {
			fail("Advancing a sequence without a current element threw the wrong kind of exception.");
		}
	}
	
	/**
	 * Calls advance on an empty sequence.
	 */
	@Test
	public void test15AdvanceEmpty() {
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		try {
			sequence.advance();
			fail("Advancing an empty sequence should have thrown an exception.");
		}
		catch(IllegalStateException exception) {
			// expected
		}
		catch(Exception exception) {
			fail("Advancing an empty sequence threw the wrong kind of exception.");
		}
	}
	
	/**
	 * Calls addAfter on an empty sequence.
	 */
	@Test
	public void test16AddAfterEmpty() {
		final String ACTION = "adding (after) 6.0 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		final String DESCRIPTION = sequence.toString();
		sequence.addAfter(6.0);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 1, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 6.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "(6.0) ", sequence.toString());
	}
	
	/**
	 * Calls addAfter on a sequence with one element, that is current.
	 */
	@Test
	public void test17AddAfterOneIsCurrent() {
		final String ACTION = "adding (after) 3.0 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addAfter(6.0);
		final String DESCRIPTION = sequence.toString();
		sequence.addAfter(3.0);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 2, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 3.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "6.0 (3.0) ", sequence.toString());
	}
	
	/**
	 * Calls addAfter on a sequence with one element, but no current.
	 */
	@Test
	public void test18AddAfterOneNoCurrent() {
		final String ACTION = "adding (after) 3.0 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addAfter(6.0);
		sequence.advance();
		final String DESCRIPTION = sequence.toString();
		sequence.addAfter(3.0);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 2, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 3.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "6.0 (3.0) ", sequence.toString());
	}
	
	/**
	 * Calls start on a sequence with no elements.
	 */
	@Test
	public void test19StartEmpty() {
		final String ACTION = "starting";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		final String DESCRIPTION = sequence.toString();
		sequence.start();
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 0, sequence.size());
		assertFalse(message(UNEXPECTED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "empty sequence", sequence.toString());
	}
	
	/**
	 * Calls start on a sequence with one element that is current.
	 */
	@Test
	public void test20StartHasCurrent() {
		final String ACTION = "starting";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(1.0);
		final String DESCRIPTION = sequence.toString();
		sequence.start();
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 1, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 1.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "(1.0) ", sequence.toString());
	}

	/**
	 * Calls start on a sequence with one element but no current.
	 */
	@Test
	public void test21StartNoCurrent() {
		final String ACTION = "starting";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(1.0);
		sequence.advance();
		final String DESCRIPTION = sequence.toString();
		sequence.start();
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 1, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 1.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "(1.0) ", sequence.toString());
	}
	
	/**
	 * Calls addAfter on a sequence with two elements, first current.
	 */
	@Test
	public void test22AddAfterTwoFirstCurrent() {
		final String ACTION = "adding (after) 4.0 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addAfter(6.0);
		sequence.addAfter(3.0);
		sequence.start();
		final String DESCRIPTION = sequence.toString();
		sequence.addAfter(4.0);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 3, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 4.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "6.0 (4.0) 3.0 ", sequence.toString());
	}

	/**
	 * Calls addAfter on a sequence with two elements, second current.
	 */
	@Test
	public void test23AddAfterTwoSecondCurrent() {
		final String ACTION = "adding (after) 4.0 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addAfter(6.0);
		sequence.addAfter(3.0);
		final String DESCRIPTION = sequence.toString();
		sequence.addAfter(4.0);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 3, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 4.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "6.0 3.0 (4.0) ", sequence.toString());
	}

	/**
	 * Calls addAfter on a sequence with two elements, none current.
	 */
	@Test
	public void test24AddAfterTwoNoCurrent() {
		final String ACTION = "adding (after) 4.0 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addAfter(6.0);
		sequence.addAfter(3.0);
		sequence.advance();
		final String DESCRIPTION = sequence.toString();
		sequence.addAfter(4.0);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 3, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 4.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "6.0 3.0 (4.0) ", sequence.toString());
	}

	
	/**
	 * Calls ensureCapacity when capacity is already large enough.
	 */
	@Test
	public void test25EnsureCapacitySufficient() {
		final String ACTION = "ensuring capacity of 5 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence(20);
		sequence.addBefore(5.0);
		sequence.addBefore(3.0);
		sequence.addAfter(9.0);
		final String DESCRIPTION = sequence.toString() + " with capacity of 20";
		sequence.ensureCapacity(5);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 3, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 9.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 20, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "3.0 (9.0) 5.0 ", sequence.toString());
	}
	
	/**
	 * Calls ensureCapacity when it needs to grow.
	 */
	@Test
	public void test26EnsureCapacityNeedsToGrow() {
		final String ACTION = "ensuring capacity of 25 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence(12);
		sequence.addBefore(8.0);
		sequence.addBefore(7.0);
		sequence.addBefore(6.0);
		sequence.addBefore(5.0);
		final String DESCRIPTION = sequence.toString() + " with capacity of 12";
		sequence.ensureCapacity(25);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 4, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 5.0, sequence.getCurrent(), EPSILON);
		assertTrue(message("Capacity should have been at least 25", ACTION, DESCRIPTION), sequence.getCapacity() >= 25);
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "(5.0) 6.0 7.0 8.0 ", sequence.toString());
	}
	
	/**
	 * Calls addBefore at a time that will require increasing capacity.
	 */
	@Test
	public void test27AddBeforeFull() {
		final String ACTION = "adding (before) 1.0 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence(3);
		sequence.addBefore(5.0);
		sequence.addBefore(3.0);
		sequence.addBefore(8.0);
		final String DESCRIPTION = sequence.toString() + " with capacity of 3";
		sequence.addBefore(1.0);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 4, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 1.0, sequence.getCurrent(), EPSILON);
		assertTrue(message("Capacity should have been at least 6", ACTION, DESCRIPTION), sequence.getCapacity() >= 6);
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "(1.0) 8.0 3.0 5.0 ", sequence.toString());
	}
	
	/**
	 * Calls addAfter at a time that will require increasing capacity.
	 */
	@Test
	public void test28AddAfterFull() {
		final String ACTION = "adding (after) 5.0 to";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence(4);
		sequence.addAfter(1.0);
		sequence.addAfter(2.0);
		sequence.addAfter(3.0);
		sequence.addAfter(4.0);
		final String DESCRIPTION = sequence.toString() + " with capacity of 4";
		sequence.addAfter(5.0);
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 5, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 5.0, sequence.getCurrent(), EPSILON);
		assertTrue(message("Capacity should have been at least 8", ACTION, DESCRIPTION), sequence.getCapacity() >= 8);
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "1.0 2.0 3.0 4.0 (5.0) ", sequence.toString());
	}
	
	/**
	 * Calls trimToSize.
	 */
	@Test
	public void test29TrimToSize() {
		final String ACTION = "trimming to size";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addAfter(3.0);
		sequence.addAfter(9.0);
		sequence.addBefore(1.0);
		sequence.addBefore(1.0);
		final String DESCRIPTION = sequence.toString();
		sequence.trimToSize();
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 4, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 1.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), sequence.size(), sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "3.0 (1.0) 1.0 9.0 ", sequence.toString());		
	}
	
	/**
	 * Calls getCurrent when there is no current element.
	 */
	@Test
	public void test30GetCurrentNoCurrent() {
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(1.0);
		sequence.addBefore(3.0);
		sequence.advance();
		sequence.advance();
		try {
			sequence.getCurrent();
			fail("Getting current should have thrown an exception when there was no current element.");
		}
		catch(IllegalStateException exception) {
			// expected
		}
		catch(Exception exception) {
			fail("Getting current threw the wrong kind of exception when there was no current elemnt.");
		}
	}
	
	/**
	 * Calls removeCurrent when there is no current element.
	 */
	@Test
	public void test31RemoveCurrentNoCurrent() {
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addAfter(5.0);
		sequence.addAfter(2.0);
		sequence.advance();
		try {
			sequence.removeCurrent();
			fail("Removing current should have thrown an exception when there was no current element.");
		}
		catch(IllegalStateException exception) {
			// expected
		}
		catch(Exception exception) {
			fail("Removing current threw the wrong kind of exception when there was no current element.");
		}
	}

	/**
	 * Calls removeCurrent when there is only one element.
	 */
	@Test
	public void test32RemoveCurrentJustOne() {
		final String ACTION = "removing current from";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addAfter(7.0);
		final String DESCRIPTION = sequence.toString();
		sequence.removeCurrent();
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 0, sequence.size());
		assertFalse(message(UNEXPECTED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "empty sequence", sequence.toString());				
	}
	
	/**
	 * Calls removeCurrent when there are three elements, first is current.
	 */
	@Test
	public void test33RemoveCurrentFirstOfThree() {
		final String ACTION = "removing current from";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(8.0);
		sequence.addBefore(2.0);
		sequence.addBefore(5.0);
		final String DESCRIPTION = sequence.toString();
		sequence.removeCurrent();
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 2, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 2.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "(2.0) 8.0 ", sequence.toString());		
	}
	
	/**
	 * Calls removeCurrent when there are three elements, second is current.
	 */
	@Test
	public void test34RemoveCurrentSecondOfThree() {
		final String ACTION = "removing current from";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(8.0);
		sequence.addBefore(2.0);
		sequence.addBefore(5.0);
		sequence.advance();
		final String DESCRIPTION = sequence.toString();
		sequence.removeCurrent();
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 2, sequence.size());
		assertTrue(message(NEED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CURRENT, ACTION, DESCRIPTION), 8.0, sequence.getCurrent(), EPSILON);
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "5.0 (8.0) ", sequence.toString());		
	}

	/**
	 * Calls removeCurrent when there are three elements, third is current.
	 */
	@Test
	public void test35RemoveCurrentThirdOfThree() {
		final String ACTION = "removing current from";
		ArrayDoubleSequence sequence = new ArrayDoubleSequence();
		sequence.addBefore(8.0);
		sequence.addBefore(2.0);
		sequence.addBefore(5.0);
		sequence.advance();
		sequence.advance();
		final String DESCRIPTION = sequence.toString();
		sequence.removeCurrent();
		assertEquals(message(WRONG_SIZE, ACTION, DESCRIPTION), 2, sequence.size());
		assertFalse(message(UNEXPECTED_CURRENT, ACTION, DESCRIPTION), sequence.isCurrent());
		assertEquals(message(WRONG_CAPACITY, ACTION, DESCRIPTION), 10, sequence.getCapacity());
		assertEquals(message(WRONG_STRING, ACTION, DESCRIPTION), "5.0 2.0 ", sequence.toString());		
	}
}
