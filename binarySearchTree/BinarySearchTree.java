package binarySearchTree;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public void traversal() {
	if (this.root != null)
	    inOrderTraversal(this.root);
    }
    

    @Override
    public void insert(T data) {
	if (this.root == null)
	    this.root = new Node<T>(data);
	else
	    insertNode(data, this.root);
    }
    

    @Override
    public void delete(T data) {
	if(this.root != null)
	    this.root = delete(this.root, data);
    }
    

    @Override
    public T getMaxValue() {
	if (this.root == null)
	    return null;

	return getMax(this.root);
    }
    

    @Override
    public T getMinValue() {
	if (this.root == null)
	    return null;

	return getMin(this.root);
    }
    
    
    @Override
    public void reverse() {
	if(this.root != null)
	    reverse(this.root);
    }
    

    private void reverse(Node<T> root) {
	if(root == null) {
	    return;
	}
	
	Node<T> tempNode = root.getRightChild();
	root.setRightChild(root.getLeftChild());
	root.setLeftChild(tempNode);
	
	reverse(root.getLeftChild());	
	reverse(root.getRightChild());
    }
    

    private void inOrderTraversal(Node<T> node) {
	if (node.getLeftChild() != null)
	    inOrderTraversal(node.getLeftChild());

	System.out.print(node + " - ");

	if (node.getRightChild() != null)
	    inOrderTraversal(node.getRightChild());

    }
    

    private T getMin(Node<T> node) {
	if (node.getLeftChild() != null)
	    return getMin(node.getLeftChild());

	return node.getData();
    }
    

    private T getMax(Node<T> node) {
	if (node.getRightChild() != null)
	    return getMax(node.getRightChild());

	return node.getData();
    }
    

    private void insertNode(T data, Node<T> node) {

	if (data.compareTo(node.getData()) < 0) {

	    if (node.getLeftChild() != null) {
		insertNode(data, node.getLeftChild());
	    } else {
		Node<T> newNode = new Node<>(data);
		node.setLeftChild(newNode);
	    }
	} else {

	    if (node.getRightChild() != null) {
		insertNode(data, node.getRightChild());
	    } else {
		Node<T> newNode = new Node<>(data);
		node.setRightChild(newNode);
	    }
	}

    }
    
    
    private Node<T> delete(Node<T> node, T data) {
	    if (node == null) return null;
	    
	    if (data.compareTo(node.getData()) < 0) {
		node.setLeftChild(delete(node.getLeftChild(), data));
	    }else if (data.compareTo(node.getData()) > 0) {
		node.setRightChild(delete(node.getRightChild(), data));
	    }else {
		
		// we have found the node we want to remove !!!
		//leaf node
		if(node.getLeftChild() == null && node.getRightChild() == null)
		    return null;
		
		if(node.getLeftChild() == null) {
		    System.out.println("Moving right child");
		    Node<T> tempNode = node.getRightChild();
		    node = null;
		    return tempNode;
		}else if (node.getRightChild() == null) {
		    System.out.println("Moving left child");
		    Node<T> tempNode = node.getLeftChild();
		    node = null;
		    return tempNode;
		}
		
		// this is the node with two children case !!!
		System.out.println("Removing item with two children");
		Node<T> tempNode = getPredecessor(node.getLeftChild());
		
		node.setData(tempNode.getData());
		node.setLeftChild(delete(node.getLeftChild(), tempNode.getData()));
	    }
	    
	    return node;
       }
    

    private Node<T> getPredecessor(Node<T> node) {
	
	if (node.getRightChild() != null)
	    return node.getRightChild();
	
	System.out.println("Predecessor is: "+node);
	
	return node;
    }
}
