package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
	//Osman Warsi 
	//Comp 250
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
    	this.numEntries = 0;
        this.numBuckets = initialCapacity;
        
        //initially array will have initialCapaity buckets and 0 entries
        
        //now we make a new ArrayList with initialCapacity size
        buckets = new ArrayList<LinkedList<HashPair<K,V>>>(initialCapacity);
        
        //now we loop through the array and add Linked<Hash> to all the slots and we're done
        int i;
        for (i=0; i < numBuckets; i++) {
        	buckets.add(new LinkedList<HashPair<K,V>>());
        }
        //END
        //ADD YOUR CODE ABOVE THIS
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets vairable. Usefull for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        //  ADD YOUR CODE BELOW HERE
        int index = hashFunction(key); //key position
      
      LinkedList<HashPair<K,V>> linkedlisthash = buckets.get(index); //out linked list for the bucket referencing to the hashed key
      HashPair<K,V> newPair = new HashPair<K,V>(key, value);
      
      //looping through the linked list to find the key
      for (HashPair<K,V> entry : linkedlisthash) {
    	  //during the loop if we find the key we're looking for then get it and return its value
    	  if (entry.getKey().equals(key)) {
    		  V valueneeded = entry.getValue();  
    		  entry.setValue(value); //set value of the entry to the input
    		  return valueneeded;
    	  }
      }
        //now the number of entries has increased and we need to add a new pair of entries in the bucket
     numEntries++;
     buckets.get(index).add(newPair);
     double loadFactor = ((double)numEntries )/ numBuckets; //defining the load factor
     if (loadFactor >= MAX_LOAD_FACTOR) {
    	 this.rehash(); //rehash if there are less buckets than entries
     }
     //END
        //  ADD YOUR CODE ABOVE HERE
    	return null;
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime = O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE
    	int index = hashFunction(key);  
    	
    	//looping through the Linked list of the bucket already there
    	Iterator<HashPair<K,V>> loop = buckets.get(index).iterator();
    	while(loop.hasNext()) { //while there is a next entry
    		//current is next
    		HashPair<K,V> cur = loop.next();
    		if(cur.getKey().equals(key)) { //if the keys are equal
    			return cur.getValue(); //return its value
    		}
    	}
    	//END
    	return null;
    	
    	
    	
    	
    	
    	
        
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair correspoinding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE
    	int index = hashFunction(key);
    	LinkedList<HashPair<K,V>> rLinkedList = this.buckets.get(index);
    	Iterator<HashPair<K,V>> loop = rLinkedList.iterator();
    	//looping through the new linked list
    	while (loop.hasNext()) { 
    		
    		HashPair<K,V> cur = loop.next();
    		//new pair
    		//if keys are equal get the value and remove it and decrease the size of entry
    		if (cur.getKey().equals(key)) {
    			V oldValue = cur.getValue();
    			rLinkedList.remove(cur);
    			numEntries--;
    			return oldValue;  //return the removed value
    		}
    		
    		
    	}
    	return null;
    	//ADD CODE ABOVE
    }
    
    
    // Method to double the size of the hashtable if load factor increases
    // beyond MAX_LOAD_FACTOR.
    // Made public for ease of testing.
    
    public void rehash() {
        //ADD YOUR CODE BELOW HERE
		int rehashSize = numBuckets * 2; 
		ArrayList<LinkedList<HashPair<K, V>>> newhash = new ArrayList<LinkedList<HashPair<K, V>>>();
		//newhash will contain more buckets
		for (int i = 0; i < rehashSize; i++) {
			newhash.add(new LinkedList<HashPair<K, V>>()); //adding LL in the buckets
		}
		
		for (int i = 0; i < numBuckets; i++) {

			if (buckets.get(i).size() > 0) {

				Iterator<HashPair<K, V>> loop = buckets.get(i).iterator();

				while (loop.hasNext()) {
//while there is a next find the hashvalue and add it to the current position of new hash LL
					HashPair<K, V> current = loop.next();
					int position = Math.abs(current.getKey().hashCode()) % rehashSize;

					newhash.get(position).add(current);
				}
			}
		}

		buckets = newhash; //buckets is the newhash now
		numBuckets = buckets.size();  //set the numBuckets back to default *2
		
    	//END
    	//ADD YOUR CODE ABOVE HERE
    
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     */
    
    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE
    	
    	ArrayList<K> keyList = new ArrayList<K>();
    	
    	for (int i =0; i<numBuckets; i++) {
    		
    		if (buckets.get(i).size() > 0) {
    			Iterator<HashPair<K,V>> loop = buckets.get(i).iterator();
    	//while there is a next get the current next and add it to the ArrayList		
    			while (loop.hasNext() ) {
    				HashPair<K,V> current = loop.next();
    				keyList.add(current.getKey());
    			}
    			
    		}
    	}
   return keyList;
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(n)
     */
    public ArrayList<V> values() {
        //ADD CODE BELOW HERE
    	
    	//new HashTable with numEntries size
    	MyHashTable<V, Integer> valuestable = new MyHashTable<V, Integer>(numEntries);
    	
    	//loop through
    	for (int i=0; i< numBuckets; i++) {
    			if (buckets.get(i).size() > 0) {
    		//if the size is greater than 0	
    			Iterator<HashPair<K,V>> iter = buckets.get(i).iterator();
    			//iterate through the pair
    			while (iter.hasNext()) {
    				
    				HashPair<K,V> cur = iter.next();
    				valuestable.put(cur.getValue(), 0); 
    			}
    			
    		}
    		
    	}
    	ArrayList<V> valueslist = new ArrayList<V>();
    	valueslist = valuestable.keys(); 
        return valueslist;
        //ADD CODE ABOVE HERE
    }
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }
    
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        private LinkedList<HashPair<K,V>> entries;
        
        private MyHashIterator() {
            //ADD YOUR CODE BELOW HERE
            entries = new LinkedList<HashPair<K,V>>();
            for (LinkedList<HashPair<K,V>> i : buckets) {
            	for(HashPair<K,V> hp: i ) {
            		entries.add(hp);
            	}
            }
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public boolean hasNext() {
            //ADD YOUR CODE BELOW HERE
        	if(entries.peek() == null) {
        		return false;
        	} else {
        		return true;
        	}
        	
            
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public HashPair<K,V> next() {
            //ADD YOUR CODE BELOW HERE
        	if(!this.hasNext()) {
        		return null;
        	} else {
        		return entries.removeFirst();
        	}
            
            //ADD YOUR CODE ABOVE HERE
        }
        
    }
}
