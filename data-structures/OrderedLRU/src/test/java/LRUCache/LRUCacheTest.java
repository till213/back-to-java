/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package LRUCache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import OrderedLRU.LRUCache;

public class LRUCacheTest {

	// This is a fancy way of saying 'private static final int Key1 = 1' ;)
	private static enum Key {
		Key1(1), 
		Key2(2), 
		Key3(3),
		Key4(4),
		Key5(5);
		
		public final int value;
		private Key(int value) {
			this.value = value;
		}
	}
	private static final int Capacity = 3;

	@Test
	public void testContains() {

		LRUCache<Integer, String> sut = new LRUCache<Integer, String>(Capacity);

		// Setup
		sut.insert(Key.Key1.value, "one");

		// Exercise
		boolean containsOne = sut.contains(Key.Key1.value);
		boolean containsTwo = sut.contains(Key.Key2.value);

		// Verify
		assertTrue("Contains 1", containsOne);
		assertFalse("Does not contain 2", containsTwo);

	}

	@Test
	public void testRemove() {

		LRUCache<Integer, String> sut = new LRUCache<Integer, String>(Capacity);

		// Setup
		sut.insert(Key.Key1.value, "one");
		sut.insert(Key.Key2.value, "two");

		// Exercise
		sut.remove(Key.Key1.value);

		// Verify
		assertFalse("Does not contain 1", sut.contains(Key.Key1.value));
		assertTrue("Still contains 2", sut.contains(Key.Key2.value));

	}
	
	@Test
	public void testSize() {

		LRUCache<Integer, String> sut = new LRUCache<Integer, String>(Capacity);
		
		// Verify
		assertTrue("Size 0", sut.size() == 0);

		// Setup
		sut.insert(Key.Key1.value, "one");

		// Exercise
		int size =  sut.size();

		// Verify
		assertTrue("Size 1", size == 1);

	}
	
	@Test
	public void testValue() {

		LRUCache<Integer, String> sut = new LRUCache<Integer, String>(Capacity);
		
		// Setup
		sut.insert(Key.Key1.value, "one");
		sut.insert(Key.Key2.value, "two");


		// Exercise
		Optional<String> value1 = sut.get(Key.Key1.value);
		Optional<String> value2 = sut.get(Key.Key2.value);
		Optional<String> value3 = sut.get(Key.Key3.value);

		// Verify
		assertTrue("Value 1 present", value1.isPresent());
		assertEquals("Value 1", value1.get(), "one");
		
		assertTrue("Value 2 present", value2.isPresent());
		assertEquals("Value 2", value2.get(), "two");
		
		assertFalse("Value 3 present", value3.isPresent());

	}
	
	@Test
	public void testEviction() {

		LRUCache<Integer, String> sut = new LRUCache<Integer, String>(Capacity);
		
		// Setup
		sut.insert(Key.Key1.value, "one");
		sut.insert(Key.Key2.value, "two");
		sut.insert(Key.Key3.value, "three");
		
		// Exercise
		// post: 4, 3, 2
		sut.insert(Key.Key4.value, "four");
		

		// Verify
		assertFalse("Value 1 present", sut.containsKey(Key.Key1.value));
		
		// Setup
		// post: 3, 4, 2
		sut.get(Key.Key3.value);
		// post: 5, 3, 4
		sut.insert(Key.Key5.value, "five");
		
		// Verify
		assertTrue("Value 5 present", sut.containsKey(Key.Key5.value));
		assertTrue("Value 3 present", sut.containsKey(Key.Key3.value));
		assertTrue("Value 4 present", sut.containsKey(Key.Key4.value));
		
		assertFalse("Value 1 not present", sut.containsKey(Key.Key1.value));
		assertFalse("Value 2 not present", sut.containsKey(Key.Key2.value));
		
	}
}