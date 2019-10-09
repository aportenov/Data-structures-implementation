package data.structure.redBlackTree;

import java.util.ArrayDeque;
import java.util.Queue;


public class RedBlackTree<Key extends Comparable<Key>, Value> {
    
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    private Node root;
    
    private class Node {	
	private Key key;
	private Value value;
	private Node leftChild;
	private Node rightChild;
	private boolean color;
	private int size;
	
	public Node(Key key, Value value, boolean color, int size) {
	    this.key = key;
	    this.value = value;
	    this.color = color;
	    this.size = size;
	}
    }
    
    public RedBlackTree() {}
    
    private  boolean isRed(Node node) {
	if(node == null) return false;
	return node.color == RED;
    }
    
    public int size() {
	return size(root);
    }
    
    private  int size(Node node) {
	return node.size;
    }
    
   public boolean isEmpty() {
       return root == null;
   }
    
   public Value get(Key key) {
       if(key == null) return null;
       return get(root, key);
   }

    private Value get(Node node, Key key) {
       while(node != null) {
           int comparedResult = key.compareTo(node.key);
           if(comparedResult < 0) node = node.rightChild;
           else if(comparedResult > 0) node = node.leftChild;
           else return node.value;   
       }
       
       return null;
    }
    
    public boolean contains(Key key) {
	return get(key) != null;
    }
    
    
    public void put(Key key, Value value) {
	if(key == null) return;
	if(value == null) {
	    delete(key);
	    return;
	}
	
	root = put(root, key, value);
	root.color = BLACK;
    }


    private Node put(Node node, Key key, Value value) {
	if(node == null) return new Node(key, value, RED, 1);
	
	int comparedResult = key.compareTo(node.key);
	if(comparedResult < 0) node.leftChild = put(node.leftChild, key, value);
	else if(comparedResult > 0) node.rightChild = put(node.rightChild, key, value);
	else node.value = value;
	
	if(isRed(node.rightChild) && !isRed(node.leftChild)) node = rotateLeft(node);
	if(isRed(node.leftChild) && isRed(node.leftChild.leftChild)) node = rotateRight(node);
	if(isRed(node.leftChild) && isRed(node.rightChild)) flipcolors(node);
	
	node.size = size(node.leftChild) + size(node.rightChild) + 1;
	return node;
    }
    
    private void delete(Key key) {
	if(key == null) return;
	if(!contains(key)) return;
	
	if(!isRed(root.leftChild)  && !isRed(root.rightChild))
	    root.color = RED;
	
	root = delete(root, key);
	if(!isEmpty()) root.color = BLACK;
     }

    private Node delete(Node node, Key key) {
	 Node left = node.leftChild;
	 Node right = node.rightChild;
	if(key.compareTo(node.key) < 0) {
	    if(!isRed(left) && !isRed(left.leftChild)) 
		node = moveRedLeft(node);
	    
	    node.leftChild = delete(left, key);
	}else {
	    if(isRed(left))
		node = rotateRight(node);
	    if(key.compareTo(node.key) == 0 && right == null)
		return null;
	    if(!isRed(right)  && !isRed(right.leftChild))
		node = moveRedRight(node);
	    if (key.compareTo(node.key) == 0) {
		Node temp = min(right);
		node.key = temp.key;
		node.value = temp.value;
		right = deleteMin(right);
	    } else 
		right = delete(right, key);
	}
	
	return balance(node);
    }

   public Key min() {
       if(isEmpty()) return null;
       else return min(root).key;
   }

    private Node min(Node node) {
	if(node.leftChild == null) return node;
	else return min(node.leftChild);
    }
    
    public Key max() {
	if(isEmpty()) return null;
	else return max(root).key;
    }

    private Node max(Node node) {
	if(node.rightChild == null) return node;
	else return max(node.rightChild);
    }
    
    public int height() {
	 return height(root);
    }

    private int height(Node node) {
	if(node == null) return -1;
	else return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    private Node balance(Node node) {
	if (isRed(node.rightChild))                      
	    node = rotateLeft(node);
        if (isRed(node.leftChild) && isRed(node.leftChild.leftChild)) 
            node = rotateRight(node);
        if (isRed(node.leftChild) && isRed(node.rightChild))     
            flipcolors(node);

        node.size = size(node.leftChild) + size(node.rightChild) + 1;
        return node;
    }
    
    public void deleteMin() {
	if(isEmpty()) return;
	if(!isRed(root.leftChild) && !isRed(root.rightChild)) 
	    root.color = RED;
	
	root = deleteMin(root);
	if(!isEmpty()) root.color = BLACK;
    }
    
    

    private Node deleteMin(Node node) {
	if(node.leftChild == null) return null;
	
	Node left = node.leftChild;
	if(!isRed(left) && !isRed(left.leftChild))
	    node = moveRedLeft(node);
	
	left = deleteMin(left);
	return balance(node);
    }
    
    public void deleteMax() {
	if(isEmpty()) return;
	if(!isRed(root.leftChild) && !isRed(root.rightChild))
	    root.color = RED;
	
	root = deleteMax(root);
	if(!isEmpty()) root.color = BLACK;
    } 

    private Node deleteMax(Node node) {
	if(isRed(node.leftChild))
	   node = rotateRight(node);
	
	if(node.rightChild == null)
	    return null;
	
	node.rightChild = deleteMax(node.rightChild);
	return balance(node);
    }

    private Node moveRedRight(Node node) {
	flipcolors(node);
	Node left = node.leftChild;
	if(isRed(left.leftChild)) {
	    node = rotateRight(node);
	    flipcolors(node);
   	}
   	
   	return node;
    }
    
    private Node moveRedLeft(Node node) {
   	flipcolors(node);
   	Node right = node.rightChild;
   	if(isRed(right.leftChild)){
   	   right = rotateRight(right);
   	   node = rotateLeft(node);
   	   flipcolors(node);
   	}
   	
   	return node;
   }

    private void flipcolors(Node node) {
	node.color = !node.color;
	node.leftChild.color = !node.leftChild.color;
	node.rightChild.color = !node.rightChild.color;
    }

    private Node rotateRight(Node node) {
	Node left = node.leftChild;
	node.leftChild = left.rightChild;
	left.rightChild = node;
	left.color = left.rightChild.color;
	left.rightChild.color = RED;
	left.size = node.size;
	node.size = size(node.leftChild) + size(node.rightChild) + 1;
	return left;
    }

    private Node rotateLeft(Node node) {
	Node right = node.rightChild;
	node.rightChild = right.leftChild;
	right.leftChild = node;
	right.color = right.leftChild.color;
	right.leftChild.color = RED;
	right.size = node.size;
	node.size = size(node.leftChild) + size(node.rightChild) + 1;
	return right;
    }

    public Iterable<Key> keys() {
        if (isEmpty()) return new ArrayDeque<Key>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) return null;
        if (hi == null) return null;

        Queue<Key> queue = new ArrayDeque<Key>();
        keys(root, queue, lo, hi);
        return queue;
    } 


    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys(x.leftChild, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.offer(x.key); 
        if (cmphi > 0) keys(x.rightChild, queue, lo, hi); 
    } 
}