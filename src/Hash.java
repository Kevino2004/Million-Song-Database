/**
 * Hash table class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */

public class Hash {

    private Record[] table;  // The hash table array
    private int numRecords;  // Number of records in the table
    private int tableSize;   // Size of the hash table
    private static final double LOAD_FACTOR_THRESHOLD = 0.5;
    
    // Tombstone to mark removed slots
    private static final Record TOMBSTONE = new Record(null, null);
    
    //~ Constructors ..........................................................
    /**
     * Create a new Hash object.
     * @param initialSize initial size of the table
     */
    public Hash(int initialSize) {
        this.tableSize = initialSize;
        this.table = new Record[tableSize];
        this.numRecords = 0;
    }
    
    //~ Methods ...............................................................
    
    /**
     * Insert a new record into the hash table. Uses quadratic probing for 
     * collision resolution. Expands the table if it's more than half full.
     * 
     * @param key The key (string) to insert
     * @param value The value (Node) associated with the key
     */
    public void insert(String key, Node<String> value) {
        if (numRecords >= tableSize * LOAD_FACTOR_THRESHOLD) {
            expand();
        }
        
        int homeSlot = h(key, tableSize);
        int i = 0;
        
        while (table[(homeSlot + i * i) % tableSize] != null && 
            table[(homeSlot + i * i) % tableSize] != TOMBSTONE) {
            i++;
        }
        
        int insertSlot = (homeSlot + i * i) % tableSize;
        table[insertSlot] = new Record(key, value);
        numRecords++;
    }
    
    /**
     * Find a record in the hash table using quadratic probing.
     * 
     * @param key The key to search for
     * @return The Node associated with the key, or null if not found
     */
    public Node<String> find(String key) {
        int homeSlot = h(key, tableSize);
        int i = 0;

        while (table[Math.abs((homeSlot + i * i) % tableSize)] != null) {
            Record current = table[Math.abs((homeSlot + i * i) % tableSize)];
            if (current != TOMBSTONE && current.getKey().equals(key)) {
                return current.getValue();
            }
            i++;
        }

        return null;  // Key not found
    }
    
    /**
     * Remove a record from the hash table by marking the slot as a tombstone.
     * 
     * @param key The key to remove
     */
    public void remove(String key) {
        int homeSlot = h(key, tableSize);
        int i = 0;

        while (table[Math.abs((homeSlot + i * i) % tableSize)] != null) {
            Record current = table[Math.abs((homeSlot + i * i) % tableSize)];
            if (current != TOMBSTONE && current.getKey().equals(key)) {
                table[Math.abs((homeSlot + i * i) % tableSize)] = TOMBSTONE;
                numRecords--;
                return;
            }
            i++;
        }

        System.out.println("Key not found: " + key);
    }
    
    /**
     * Print all the records in the hash table.
     * 
     * @return The count of printed records
     */
    public int print() {
        int count = 0;
        
        for (int i = 0; i < tableSize; i++) {
            if (table[i] != null && table[i] != TOMBSTONE) {
                System.out.println("Key: " + table[i].getKey() + ", Value: " 
                    + table[i].getValue());
                count++;
            }
        }
        
        System.out.println("Total records printed: " + count);
        return count;
    }
    
    /**
     * Expand the hash table by doubling its size and re-inserting all 
     * valid records.
     */
    private void expand() {
        int newSize = tableSize * 2;
        Record[] oldTable = table;
        table = new Record[newSize];
        tableSize = newSize;
        numRecords = 0;  // We'll re-insert, so reset count

        // Re-insert records from old table into the new table
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null && oldTable[i] != TOMBSTONE) {
                insert(oldTable[i].getKey(), oldTable[i].getValue());
            }
        }
    }
    
    
    /**
     * Compute the hash function
     * 
     * @param s
     *            The string that we are hashing
     * @param length
     *            Length of the hash table (needed because this method is
     *            static)
     * @return
     *         The hash function value (the home slot in the table for this key)
     */
    public static int h(String s, int length) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % length);
    }
    
    
    
}
