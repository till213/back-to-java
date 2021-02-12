package OrderedLRU;

import java.util.HashMap;
import java.util.Optional;

public class LRUCache<K, V> {
	
	private class Node<K1, V1> {
		
		Node<K1, V1> prev;
		Node<K1, V1> next;
		K1 key;
		V1 value;
		
	}

	private HashMap<K, Node<K, V>> index;
	private Node<K, V> head;
	private Node<K, V> tail;
	private final int capacity;
	
	public LRUCache(int capacity) {
		super();
		index = new HashMap<>();
		this.head = null;
		this.tail = null;
		this.capacity = capacity;
	}
    
	public void insert(K key, V value) {
		
		var node = index.get(key);
		if (node != null) {
			// Update existing value
			node.value = value;
			moveToFront(node);
		} else {
			// Add new key
			if (index.size() == capacity) {
				evict();
			}
		    addFirst(key, value);
			index.put(key, head);			
		}
		
	}
	
	public Optional<V> get(K key) {
		var node = index.get(key);
		if (node != null) {
			return Optional.of(node.value);
		} else {
			return Optional.ofNullable(null);
		}
	}
	
	public boolean containsKey(K key) {
		return index.containsKey(key);
	}
	
	public void remove(K key) {
		var node = index.remove(key);
		if (node != null) {
			remove(node);
		}
	}
	
	public boolean contains(K key) {
		return index.containsKey(key);
	}
	
	
	public int size() {
		return index.size();
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	private void addFirst(K key, V value) {
		Node<K, V> node = new Node<>();
		node.key = key;
		node.value = value;
		addFirst(node);
	}
	
	private void addFirst(Node<K, V> node) {
		if (head == null) {
			// first node
			head = node;
			tail = head;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
	}
	
	private void remove(Node<K, V> node) {
	
		int size = index.size();
		if (size > 1) {					
			if (node == tail) {
				// Remove tail node
				tail = tail.prev;
				tail.next = null;
			} else if (node == head) {
				// Remove head node
				head = head.next;
				head.prev = null;
			} else {
				// Node in the middle
				node.prev.next = node.next;
				node.next.prev = node.prev;
			}
		} else if (size == 1) {
			// Remove last node
			head = null;
			tail = null;
		}
      
	}
	
	private void removeLast() {
		remove(tail);
	}
	
	private void evict() {
		if (!isEmpty()) {
			index.remove(tail.key);
			removeLast();
		}
	}
		
	private void moveToFront(Node<K, V> node) {
		if (node != head) {
			// Unlink node
			node.prev.next = node.next;
			if (node.next != null) {
				node.next.prev = node.prev;
			}
			// Re-insert at head
			addFirst(node);
		}
	}
	
}
