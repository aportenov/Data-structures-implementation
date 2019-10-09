package data.structure.singlyLinkedList;

class Node<T> {
    
    Node<T> next;
    T data;

    Node(T data) {
	this.data = data;
    }
    

    @Override
    public String toString() {
	return this.data.toString();
    }
}