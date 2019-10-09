package data.structure.hashTable;

@SuppressWarnings("unchecked")
public class LinearHashTable<Key, Value> {
    
    private static final int INIT_CAPACITY = 4;
    private static final double LOAD_FACTOR = 0.75;
   
    
    private Key[] keys;
    private Value[] values;
    private int capacity;
    private int currentSize;

    public LinearHashTable() {
   	this(INIT_CAPACITY);
   }
    
    public LinearHashTable(int newCapacity) {
	capacity = newCapacity;
	keys = (Key[]) new Object[capacity];
	values = (Value[]) new Object[capacity];
    }   
    
    
   public int size() {
       return currentSize;
   }
   
   public boolean isEmpty() {
       return size() == 0;
   }
   
   public boolean contains(Key key) {
       return get(key) != null;
   } 
   
   public void put(Key key, Value value) {
       if(key == null) return;
       if(value == null) remove(key);
       
       if(currentSize >= capacity * LOAD_FACTOR)
	   resizeTable(2 * capacity);
       
      int index;
      for (index = hash(key); keys[index] != null; index = (index + 1) % capacity) {
    	if(keys[index].equals(key)) {
    	    values[index] = value;
    	    return;
    	}
	    
	
    }
         
       keys[index] = key;
       values[index] = value;
       currentSize++;
   }

   public Value get(Key key) {
       
       if(key == null) return null;
       
       int index;
       for(index = hash(key); keys[index] != null; index = (index + 1) & capacity) {
	   if (keys[index].equals(key))
               return values[index];
       }
       
       return null;
   }
    
   public void remove(Key key) {
       
       if(key == null) return;
       
       if(!contains(key)) return;
       
       int index = hash(key);
       while( !keys[index].equals(key) ) {
	   index = (index + 1) % capacity;
       }
       
       keys[index] = null;
       values[index] = null;
       
       index = (index + 1) % capacity;
       while(keys[index] != null) {
	   
	   Key keyToRehash = keys[index];
	   Value valToRehash = values[index];
	   keys[index] = null;
	   values[index] = null;
	   currentSize--;
	   
	   put(keyToRehash, valToRehash);
	   index = (index + 1) % capacity;
       }
       
       currentSize--;
       if(currentSize > 0 && currentSize < capacity * LOAD_FACTOR)
	   resizeTable(capacity);
   }
   
   private void resizeTable(int newCapacity) {
       LinearHashTable<Key, Value> temp = new LinearHashTable<>(newCapacity);
       for (int i = 0; i < keys.length; i++) {
	  
	   if(keys[i] != null)
	       temp.put(keys[i], values[i]);
       }
       
       keys = temp.keys;
       values = temp.values;
       currentSize = temp.currentSize;
   }
   
   public int hash(Key key) {
       return key.hashCode() % capacity;
   }
}
