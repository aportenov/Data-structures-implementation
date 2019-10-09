package data.structure.hashTable;

import java.util.ArrayDeque;
import java.util.Queue;

public class SequentialSearchST<Key, Value> {
    
    private int currentSize;
    private Node first;
    
    private class Node {
	
	private Key key;
	private Value value;
	private Node next;
	
	public Node(Key key, Value value, Node next)  {
            this.key  = key;
            this.value  = value;
            this.next = next;
        }
	
    }
    
    public SequentialSearchST() {}
    
    public int size() {
	return currentSize;
    }
    
    public boolean isEmpty() {
	return size() == 0;
    }
    
    public boolean contains(Key key) {
	return get(key) != null;
    }

    public Value get(Key key) {
	for (Node i = first;  i != null; i = i.next) {
	    if(key.equals(i.key))
		return i.value;
	}
	    
	return null;
    }
	
    public void put(Key key, Value value) {
	
	if(key == null || value == null) return;
	
	for(Node i = first; i != null; i = i.next) {
	    if(key.equals(i.key)) {
		i.value = value;
		return;
	    }
	}
	
	first = new Node(key, value, first);
	currentSize++;
    }
    
    public void delete(Key key) {
	first = remove(first, key);
    }
    
    public Iterable<Key> keys(){
	 Queue<Key> queue = new ArrayDeque<Key>();
	 for(Node node = first; node != null; node = node.next) {
	     queue.offer(node.key);
	 }
	 
	 return queue;
    }

    private Node remove(Node node, Key key) {
	if(node == null) return null;
	
	if(key.equals(node.key)) {
	    currentSize--;
	    return node.next;
	}
	
	node.next = remove(node.next, key);
	return node;	
    }

}
