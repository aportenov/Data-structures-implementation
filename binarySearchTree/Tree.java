package binarySearchTree;

public interface Tree<T> {

    void traversal();

    void insert(T data);

    void delete(T data);

    void reverse();

    T getMaxValue();

    T getMinValue();

}
