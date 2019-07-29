package redBlackTree;

public class Node<T> {

    private Node<T> leftChild;
    private Node<T> rightChild;
    private Node<T> parrent;
    private Color color;
    private T data;

    public Node<T> getParrent() {
	return this.parrent;
    }

    public void setParrent(Node<T> parrent) {
	this.parrent = parrent;
    }

    public Node(T data) {
	this.data = data;
	this.color = Color.RED;
    }

    public Node<T> getLeftChild() {
	return this.leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
	this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
	return this.rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
	this.rightChild = rightChild;
    }

    public Color getColor() {
	return this.color;
    }

    public void setColor(Color color) {
	this.color = color;
    }

    public T getData() {
	return this.data;
    }

    public void setData(T data) {
	this.data = data;
    }

    @Override
    public String toString() {
	return this.data.toString();
    }

}
