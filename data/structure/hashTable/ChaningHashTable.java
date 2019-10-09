package data.structure.hashTable;

public class ChaningHashTable<Key, Value> {
    
    private static final int INIT_CAPACITY = 4;
    private static final double LOAD_FACTOR = 0.75;
    
    private int currentSize;
    private int capacity;
    private SequentialSearchST<Key, Value>[] searchSTs;
    
    public ChaningHashTable() {
	this(INIT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ChaningHashTable(int newCapacity) {
	this.capacity = newCapacity;
	searchSTs = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[capacity];
	for (int i = 0; i < capacity; i++)
	    searchSTs[i]= new SequentialSearchST<Key, Value>();
    }
    
    public int hash(Key key) {
	return key.hashCode() % capacity;
    }
    
    public int size() {
	return currentSize;
    }
    
    public boolean isEmpty() {
	return size() == 0;
    }
    
    public Value get(Key key) {
	
	if(key == null) return null;
	
	int index = hash(key);
	return searchSTs[index].get(key);
    }
    
    public void put(Key key, Value value) {	
	if(key == null) return;
	if(value == null) {
	    remove(key);
	    return;
	}
	
	if(currentSize >= capacity*LOAD_FACTOR)
	    resize(2*capacity);
	
	int index = hash(key);
	if(!searchSTs[index].contains(key))
	    currentSize++;
	
	searchSTs[index].put(key, value);
    }

    private void resize(int newCapacity) {
	ChaningHashTable<Key, Value> temp = new ChaningHashTable<>(newCapacity);
	for (int index = 0; index < capacity; index++) {
	   for (Key key : searchSTs[index].keys()) {
	       temp.put(key, searchSTs[index].get(key));
	   }
	}
	
	capacity = temp.capacity;
	currentSize = temp.currentSize;
	searchSTs = temp.searchSTs;
	
    }
    
    public void remove(Key key) {
	if(key == null) return;
	
	int index = hash(key);
	if(!searchSTs[index].contains(key)) return;
	
	currentSize--;
	searchSTs[index].delete(key);
	

	if(capacity > INIT_CAPACITY &&  currentSize <= capacity/2 ) 
	    resize((int)(capacity/2));
    }
}
