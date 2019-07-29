package singlyLinkedList;

import static singlyLinkedList.SinglyLinkedList.*;

public class App {

    public static void main(String[] args) {
	Node<Integer> head = new Node<>(10);
	Node<Integer> f = new Node<>(5);
	Node<Integer> s = new Node<>(3);
	Node<Integer> t = new Node<>(1);
	
	head.next = f;
	f.next  = s;
	s.next = t;
	
	print(head);
	Node<Integer> rnode = reverse(head);
	print(rnode);
	
    }
}
