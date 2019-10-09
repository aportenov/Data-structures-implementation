package data.structure.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    
    private static int INIT_VALUE = 0;
    
    private Node<Item> first;
    private Node<Item> last;
    private int currentSize;
    
    private static class Node<Item> {
	private Item item;
	private Node<Item> next;
	
	public Node(Item item, Node<Item> next) {
	    this.item = item;
	    this.next = next;
	}
    }
    
    public Queue() {
	this.currentSize = INIT_VALUE;
    }
    
    public boolean isEmpty() {
	return first == null;
    }
    
    public int size() {
	return currentSize;
    }
    
    public Item peek() {
	if(isEmpty()) throw new NoSuchElementException();
	return first.item;
    }
    
    public void add(Item item) {
	if(isEmpty()) throw new NoSuchElementException();
	Node<Item> oldItem = last;
	last = new Node<Item>(item, null);
	if(isEmpty()) first = last;
	else oldItem.next = last;
	currentSize++;
    }
    
    public Item remove() {
	if(isEmpty()) throw new NoSuchElementException();
	Item item = first.item;
	first = first.next;
	currentSize--;
	if(isEmpty()) last = null;
	return item;	
    }
    
    

    @Override
    public Iterator<Item> iterator() {
	return new ListIterator(first);
    }
    
    private class ListIterator implements Iterator<Item> {
	
	private Node<Item> current;

	public ListIterator(Node<Item> first) {
	    this.current = first;
	}
	
	public boolean hasNext() { return current != null; }
	public void remove() { throw new UnsupportedOperationException(); }
	
	public Item next() {
	    if(!hasNext()) throw new NoSuchElementException();
	    Item item = current.item;
	    current = current.next;
	    return item;
	}	
    }
}
