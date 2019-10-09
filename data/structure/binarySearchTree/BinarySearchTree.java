package data.structure.binarySearchTree;

public class BinarySearchTree<Key extends Comparable<Key>, Value>  {

    private Node root;
    
    private class Node {
	
	private Key key;
	private Value value;
	private Node leftChild;
	private Node rightChild;
	private int size;
	
	public Node(Key key, Value value, int size) {
	    this.key = key;
	    this.value = value;
	    this.size = size;	  
	}
    }
    
    public BinarySearchTree() {}
    
    public boolean isEmpty() {
	return size() == 0;
    }
    
    public int size() {
	return size(root);
    }

    private int size(Node root) {
	if(root == null) return 0;
	return root.size;
    }
    
    public boolean contains(Key key) {
	if(key == null) return false;
	return get(key) != null;
    }

    private Value get(Key key) {
	return get(root, key);	
    }

    private Value get(Node node, Key key) {
	if(key == null) return null;
	if(node == null) return null;
	 
	int comparedResult = key.compareTo(node.key);
	if(comparedResult > 0) return get(node.rightChild, key);
	else if(comparedResult < 0) return get(node.leftChild, key);
	else return node.value;	
    }
    
    public void put(Key key, Value value) {
	if(key == null) return;
	if(value == null) {
	    remove(key);
	    return;
	}
	
	root = put(root, key, value);	
    }

    private Node put(Node node, Key key, Value value) {
	if(node == null) return new Node(key, value, 1);
	int comparedResult = key.compareTo(node.key);
	if(comparedResult > 0) node.rightChild = put(node.rightChild, key, value);
	else if (comparedResult < 0) node.leftChild = put(node.leftChild, key,value); 
	else node.value = value;
	
	node.size =  1 + (size(node.leftChild) + size(node.rightChild));
	return node;
    }

    private void remove(Key key) {
	if(key == null)  return;
	root = remove(root, key);
	
    }

    private Node remove(Node node, Key key) {
	if(node == null) return null;
	
	int comparedResult = key.compareTo(node.key);
	if(comparedResult > 0) node.rightChild = remove(node.rightChild, key);
	else if(comparedResult < 0) node.leftChild = remove(node.leftChild, key);
	else {
	   if(node.rightChild == null) return node.leftChild;
	   if(node.leftChild == null) return node.rightChild;
	   Node nodeWithLeafs = node;
	   node = min(nodeWithLeafs.rightChild);
	   node.rightChild = deleteMin(nodeWithLeafs.rightChild); 
	   node.leftChild = nodeWithLeafs.leftChild;   
	}
	
	node.size = size(node.leftChild) + size(node.rightChild) + 1;
	return node;
    }
    
    public void deleteMin() {
	if(isEmpty()) return;
	root = deleteMin(root); 
    }

    private Node deleteMin(Node node) {
	if(node.leftChild == null) return node.rightChild;
	node.leftChild = deleteMin(node.leftChild);
	node.size = size(node.leftChild) + size(node.rightChild) + 1;
	return node;
    }
    
    public void deleteMax() {
	if(isEmpty()) return;
	root = deleteMax(root);
    }
    

    private Node deleteMax(Node node) {
	if(node.rightChild == null) return node.leftChild;
	node.rightChild = deleteMax(node.rightChild);
	node.size = size(node.leftChild) + size(node.rightChild) + 1;
	return node;
    }

    public Key min() {
	if(isEmpty()) return null;
	return min(root).key;
    }   
    
    private Node min(Node node) {
	if(node.leftChild == null) return node;
	return min(node.leftChild);
    }
    
    public Key max() {
	if(isEmpty()) return null;
	return max(root).key;
    }

    private Node max(Node node) {
	if(node.rightChild == null) return node;
	else return max(node.rightChild);
    }
    
    public Key floor(Key key) {
	if(key == null) return null;
	if(isEmpty()) return null;
	
	Node node = floor(root, key);
	if(node == null) return null;
	else return node.key;
    }

    private Node floor(Node node, Key key) {
	if(node == null) return null;
	
	int comparedResult = key.compareTo(node.key);
	if(comparedResult == 0) return node;
	if(comparedResult < 0) return floor(node.leftChild, key);
	
	Node temp = floor(node.rightChild, key);
	if(temp != null) return temp;
	else return node;
    }
    
    public Key ceiling(Key key) {
	if(key == null) return null;
	if(isEmpty()) return null;
	
	Node temp = ceiling(root, key);
	if(temp == null) return null;
	else return temp.key;
    }

    private Node ceiling(Node node, Key key) {
	if(key == null) return null;
	
	int comparedResult = key.compareTo(node.key);
	if(comparedResult == 0) return node;
	if(comparedResult < 0) {
	    Node temp = ceiling(node.leftChild,key);
	    if(temp != null) return temp;
	    else return node;
	}
	
	return ceiling(node.rightChild, key);
    }
    
    
    public Key select(int index) {
	if(index < 0 || index >= size()) return null;
	Node node = select(root, index);
	return node.key;
    }

    private Node select(Node node, int index) {
	if(node == null) return null;
	int currentSize = size(node.leftChild);
	if(currentSize > index) return select(root.leftChild, index);
	else if(currentSize < index) return select(root.rightChild, index-currentSize-1);
	else return node;
    }
}
