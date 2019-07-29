package avlTree;

public interface Tree<T> {

    void insert(T data);

    void traverse();

    void delete(T data);
    
    void reverse();
}
