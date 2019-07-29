package binarySearchTree;

public class App {

    public static void main(String[] args) {
	Tree<String> bst = new BinarySearchTree<>();
	
//	bst.insert("Kevin");
//	bst.insert("Adam");
//	bst.insert("Joe");
//	bst.insert("Michael");
//	bst.insert("Smith");
//	
//	bst.traversal();
//	
//	bst.delete("Adam");
//	
//	bst.traversal();
//	System.out.println();
//	bst.reverse();
//	bst.traversal();
	
	bst.insert("E");
	bst.insert("B");
	bst.insert("C");
	bst.insert("A");
	bst.insert("J");
	bst.insert("F");
	
	bst.traversal();
	System.out.println();
	bst.reverse();
	bst.traversal();
	

    }

}
