package avlTree;

public class AvlTree<T extends Comparable<? super T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public void insert(T data) {
	root = insert(root, data);

    }
    
    @Override
    public void traverse() {
	if (root == null)
	    return;

	inOrderTraversel(root);
    }
    
    @Override
    public void reverse() {
	if(this.root == null)
	    return;
	
	reverse(this.root);
	
    }
    
    @Override
    public void delete(T data) {
	root = delete(root, data);
    }
    
    private Node<T> insert(Node<T> node, T data) {
	
   	if (node == null)
   	    return new Node<>(data);

   	if (data.compareTo(node.getData()) < 0)
   	    node.setLeftNode(insert(node.getLeftNode(), data));
   	else
   	    node.setRightNode(insert(node.getRightNode(), data));

   	node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) + 1);
   	node = settleViolation(data, node);

   	return node;
    }
    
    
    private Node<T> delete(Node<T> node, T data) {
	
	if (node == null)
	    return node;

	// look for the node we want to delete
	
	 // go left recursively
	if (data.compareTo(node.getData()) < 0) {
	    node.setLeftNode(delete(node.getLeftNode(), data));
	    
	// go right recursively
	} else if (data.compareTo(node.getData()) > 0) {
	    node.setRightNode(delete(node.getRightNode(), data));
	
	// found node to remove
	} else {

	    if (node.getLeftNode() == null && node.getRightNode() == null) { // leaf node
		System.out.println("Removing a leaf node");
		return null;
	    }

	    if (node.getLeftNode() == null) {
		System.out.println("Removing right child");
		Node<T> tempNode = node.getRightNode();
		node = null;
		return tempNode;
	    } else if (node.getRightNode() == null) {
		System.out.println("Removing left child");
		Node<T> tempNode = node.getLeftNode();
		node = null;
		return tempNode;
	    }

	    // removing item with 2 childs
	    System.out.println("Removing item with 2 childs");
	    Node<T> tempNode = getPredeccesor(node.getLeftNode());
	    node.setData(tempNode.getData());
	    node.setLeftNode(delete(node.getLeftNode(), tempNode.getData()));
	}

	node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) + 1);

	// check on every delete operation whether the tree has become unbalanced
	return settleDeletion(node);
    }
    
    private Node<T> settleDeletion(Node<T> node) {
	int balance = getBalance(node);

	// we know tree is left heavy BUT it can be left-right heavy or left - left heavy
	if (balance > 1) {

	    // left - right heavy situation: left rotation on parant + right rotation on grand parrent
	    if (getBalance(node.getLeftNode()) < 0)
		node.setLeftNode(leftRotating(node.getLeftNode()));

	    // this is the right rotation on grandparant ( if left - left heavy then single right rotation is needed
	    return rightRotating(node);
	}

	// we know tree i right heavy BUT it can be left - right havy or right - right heavy
	if (balance < -1) {

	    // right - left heavy so we need right rotation ( on parant !!!) before left rotatoion
	    if (getBalance(node.getRightNode()) > 0)
		node.setRightNode(rightRotating(node.getRightNode()));

	    // left rotation on grand parrent
	    return leftRotating(node);
	}

	return node;
    }
    
    private Node<T> getPredeccesor(Node<T> node) {
	
	Node<T> predeccesor = node;
	
	while(predeccesor.getRightNode() != null) {
	    predeccesor = predeccesor.getRightNode();
	}
	
	return predeccesor;
    }
    
    private Node<T> settleViolation(T data, Node<T> node) {
	int balance = getBalance(node);

	// left - left
	if (balance > 1 && data.compareTo(node.getLeftNode().getData()) < 0) {
	    System.out.println("Tree is left left heavy");
	    return rightRotating(node);
	}

	// right - right
	if (balance < -1 && data.compareTo(node.getRightNode().getData()) > 0) {
	    System.out.println("Tree is right right heavy");
	    return leftRotating(node);
	}
    
	// right - left
	if (balance > 1 && data.compareTo(node.getLeftNode().getData()) > 0) {
	    System.out.println("Tree is left right heavy");
	    node.setLeftNode(leftRotating(node.getLeftNode()));
	    return rightRotating(node);
	}

	// left - right
	if (balance < -1 && data.compareTo(node.getLeftNode().getData()) < 0) {
	    System.out.println("Tree is right left heavy");
	    node.setRightNode(rightRotating(node.getRightNode()));
	    return leftRotating(node);
	}

	return node;
    }

    private Node<T> rightRotating(Node<T> node) {
	
	System.out.println("Rotationg right on node " + node);

	Node<T> tempLeftNode = node.getLeftNode();
	Node<T> t = tempLeftNode.getRightNode();

	tempLeftNode.setRightNode(node);
	node.setLeftNode(t);

	node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) + 1);
	tempLeftNode.setHeight(Math.max(height(tempLeftNode.getLeftNode()), height(tempLeftNode.getRightNode())) + 1);

	return tempLeftNode;

    }

    private Node<T> leftRotating(Node<T> node) {
	
	System.out.println("Rotationg left on node " + node);

	Node<T> tempRightNode = node.getRightNode();
	Node<T> t = tempRightNode.getLeftNode();

	tempRightNode.setLeftNode(node);
	node.setRightNode(t);

	node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) + 1);
	tempRightNode
		.setHeight(Math.max(height(tempRightNode.getLeftNode()), height(tempRightNode.getRightNode())) + 1);

	return tempRightNode;
    }

    private int height(Node<T> node) {
	if (node == null)
	    return -1;

	return node.getHeight();
    }

    private int getBalance(Node<T> node) {
	if (node == null)
	    return 0;

	return height(node.getLeftNode()) - height(node.getRightNode());
    }

    private void inOrderTraversel(Node<T> node) {
	if (node.getLeftNode() != null)
	    inOrderTraversel(node.getLeftNode());

	System.out.println(node);

	if (node.getRightNode() != null)
	    inOrderTraversel(node.getRightNode());
    }
    
    private void reverse(Node<T> root) {
	if(root == null) {
	    return;
	}
	
	Node<T> tempNode = root.getRightNode();
	root.setRightNode(root.getLeftNode());
	root.setLeftNode(tempNode);
	
	reverse(root.getLeftNode());	
	reverse(root.getRightNode());
    }

}
