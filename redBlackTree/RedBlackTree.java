package redBlackTree;

public class RedBlackTree<T extends Comparable<? super T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public void traverse() {
	if (this.root != null)
	    traverse(this.root);

    }

    @Override
    public void insert(T data) {
	Node<T> node = new Node<>(data);
	root = insert(root, node);

	fixViolations(node);

    }

    @Override
    public void remove(T data) {
	if (this.root != null) {
	    Node<T> node = delete(this.root, data);
	    fixViolations(node);
	}
    }

    @Override
    public void reverse() {
	if (this.root != null)
	    reverse(this.root);

    }

    private Node<T> delete(Node<T> node, T data) {
	if (node == null)
	    return null;

	if (data.compareTo(node.getData()) < 0) {
	    node.setLeftChild(delete(node.getLeftChild(), data));
	} else if (data.compareTo(node.getData()) > 0) {
	    node.setRightChild(delete(node.getRightChild(), data));
	} else {

	    // we have found the node we want to remove !!!
	    // leaf node
	    if (node.getLeftChild() == null && node.getRightChild() == null)
		return null;

	    if (node.getLeftChild() == null) {
		System.out.println("Moving right child");
		Node<T> tempNode = node.getRightChild();
		node = null;
		return tempNode;
	    } else if (node.getRightChild() == null) {
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

	System.out.println("Predecessor is: " + node);

	return node;
    }

    private void reverse(Node<T> root) {

	if (root == null)
	    return;

	Node<T> tempRightNode = root.getRightChild();
	root.setRightChild(root.getLeftChild());
	root.setLeftChild(tempRightNode);

	reverse(root.getLeftChild());
	reverse(root.getRightChild());

    }

    private void fixViolations(Node<T> node) {
	Node<T> parantNode = null;
	Node<T> grandParantNode = null;

	while (node != root && node.getColor() != Color.BLACK && node.getParrent().getColor() == Color.RED) {

	    parantNode = node.getParrent();
	    grandParantNode = node.getParrent().getParrent();

	    if (parantNode == grandParantNode.getLeftChild()) {

		Node<T> uncle = grandParantNode.getRightChild();

		// given node x's parent is a left child + uncle is red --> only recoloring
		if (uncle != null && uncle.getColor() == Color.RED) {
		    grandParantNode.setColor(Color.RED);
		    parantNode.setColor(Color.BLACK);
		    uncle.setColor(Color.BLACK);
		    node = grandParantNode;
		} else {

		    if (node == parantNode.getRightChild()) {
			leftRotation(parantNode);
			node = parantNode;
			parantNode = node.getParrent();
		    }

		    rightRotation(grandParantNode);
		    System.out.println("Recoroling " + parantNode + " + " + grandParantNode);
		    Color tempColor = parantNode.getColor();
		    parantNode.setColor(grandParantNode.getColor());
		    grandParantNode.setColor(tempColor);
		    node = parantNode;
		}
	    } else {

		Node<T> uncle = grandParantNode.getLeftChild();

		if (uncle != null && uncle.getColor() == Color.RED) {
		    grandParantNode.setColor(Color.RED);
		    parantNode.setColor(Color.BLACK);
		    uncle.setColor(Color.BLACK);
		    node = grandParantNode;
		} else {

		    if (node == parantNode.getLeftChild()) {
			rightRotation(parantNode);
			node = parantNode;
			parantNode = node.getParrent();
		    }

		    leftRotation(grandParantNode);
		    System.out.println("Recoroling " + parantNode + " + " + grandParantNode);
		    Color tempColor = parantNode.getColor();
		    parantNode.setColor(grandParantNode.getColor());
		    grandParantNode.setColor(tempColor);
		    node = parantNode;
		}
	    }
	}

	if (this.root.getColor() == Color.RED) {
	    this.root.setColor(Color.BLACK);
	    System.out.println("Recoloring the root to black...");
	}

    }

    private void traverse(Node<T> node) {

	if (node.getLeftChild() != null)
	    traverse(node.getLeftChild());

	System.out.println(node + " with color: " + node.getColor() + " LeftNode: " + node.getLeftChild()
		+ " - RightNode: " + node.getRightChild());

	if (node.getRightChild() != null)
	    traverse(node.getRightChild());

    }

    private Node<T> insert(Node<T> root, Node<T> node) {
	if (root == null)
	    return node;

	if (node.getData().compareTo(root.getData()) < 0) {
	    root.setLeftChild(insert(root.getLeftChild(), node));
	    root.getLeftChild().setParrent(root);
	} else {
	    root.setRightChild(insert(root.getRightChild(), node));
	    root.getRightChild().setParrent(root);
	}

	return root;
    }

    private void rightRotation(Node<T> node) {
	System.out.println("Rotating right " + node);

	Node<T> tempLeftNode = node.getLeftChild();
	node.setLeftChild(tempLeftNode.getRightChild());

	if (node.getLeftChild() != null)
	    node.getLeftChild().setParrent(node);

	tempLeftNode.setParrent(node.getParrent());

	if (tempLeftNode.getParrent() == null)
	    root = tempLeftNode;
	else if (node == node.getParrent().getLeftChild())
	    node.getParrent().setLeftChild(tempLeftNode);
	else
	    node.getParrent().setRightChild(tempLeftNode);

	tempLeftNode.setRightChild(node);
	node.setParrent(tempLeftNode);
    }

    private void leftRotation(Node<T> node) {
	System.out.println("Rotating left " + node);

	Node<T> tempRightNode = node.getRightChild();
	node.setRightChild(tempRightNode.getLeftChild());

	if (node.getRightChild() != null)
	    node.getRightChild().setParrent(node);

	tempRightNode.setParrent(node.getParrent());

	if (tempRightNode.getParrent() == null)
	    root = tempRightNode;
	else if (node == node.getParrent().getLeftChild())
	    node.getParrent().setLeftChild(tempRightNode);
	else
	    node.getParrent().setRightChild(tempRightNode);

	tempRightNode.setLeftChild(node);
	node.setParrent(tempRightNode);
    }

}
