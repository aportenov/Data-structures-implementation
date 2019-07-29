package redBlackTree;

public class App {
    
    public static void main(String[] args) {
	Tree<Integer> rbt = new RedBlackTree<>();
	   
	    rbt.insert(Integer.valueOf(20));
	    rbt.insert(Integer.valueOf(10));
	    rbt.insert(Integer.valueOf(30));
	    rbt.insert(Integer.valueOf(5));
	    rbt.insert(Integer.valueOf(6));
	    rbt.insert(Integer.valueOf(4));
	    rbt.insert(Integer.valueOf(7));
	    rbt.insert(Integer.valueOf(25));
	    rbt.insert(Integer.valueOf(35));
	    rbt.insert(Integer.valueOf(26));
	    rbt.insert(Integer.valueOf(33));
	    
	    rbt.remove(Integer.valueOf(4));
	    
	    rbt.traverse();
	    
	    rbt.reverse();
	    
	    System.out.println();
	    rbt.traverse();
    }
}