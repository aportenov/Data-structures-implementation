package data.structure.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
    
    private static int INIT_VALUE = 0;
    
    private Node<Item> first;
    private int currentSize;
    
    private static class Node<Item> {
	private Item item;
	private Node<Item> next;
	
	public Node(Item item, Node<Item> next) {
	    this.item = item;
	    this.next = next;
	}
    }
    
    public Stack() {
	currentSize = INIT_VALUE;
    }
    
    public int size() {
	return currentSize;
    }
    
    public boolean isEmpty() {
	return first == null;
    }
    
    public void push(Item item) {
	Node<Item> oldFirst = first;
	first = new Node<>(item, oldFirst);
	currentSize++;
    }
    
    public Item pop(){
	if(isEmpty()) throw new NoSuchElementException();
	Item item = first.item;
	first = first.next;
	currentSize--;
	return item;
    }
    
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return first.item;
    }
    
    

    @Override
    public Iterator<Item> iterator() {
	return new ListIterator(first);
    }
    
    private class ListIterator implements Iterator<Item> {
        
	private Node<Item> current;
        
	public ListIterator(Node<Item> first) {
	    current = first;
	}
	
	public boolean hasNext() {
	    return current != null;
	}
	
	public void remove() {
	    throw new UnsupportedOperationException();
	}
        
	public Item next() {
	    if(!hasNext()) throw new NoSuchElementException();
	    Item item = current.item;
	    current = current.next;
	    return item;
	}
    }    
}
