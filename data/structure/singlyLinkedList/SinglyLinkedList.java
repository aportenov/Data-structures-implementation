package data.structure.singlyLinkedList;


public class SinglyLinkedList {
        
    static <T> Node<T> reverse(Node<T> head){
	
	if(head == null) return head;
	
	Node<T> current = head;
	Node<T> prev = null;
	Node<T> next = null;
	
	while(current != null) {
	  next = current.next;
	  current.next = prev;
	  prev = current;
	  current = next;
	}
	
	
	return prev;
    }
    
    static <T> void print(Node<T> head) {
	Node<T> current = head;
	while (current != null){
	    System.out.print(current.data);
	    if(current.next != null) System.out.print(" - ");
	    current = current.next;
	}
	
	System.out.println();
    }
}
