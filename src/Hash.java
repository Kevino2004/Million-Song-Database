/**
 * Hash table class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */

public class Hash 
{
    private Record[] table;  
    private int numRecords;
    private int tableSize;   
    private static final double LOAD_FACTOR_THRESHOLD = 0.5;
    private static final Record TOMBSTONE = new Record(null, null);
    
    //~ Constructors ..........................................................
    /**
     * Create a new Hash object.
     * @param initialSize initial size of the table
     */
    public Hash(int initialSize) 
    {
        this.tableSize = initialSize;
        this.table = new Record[initialSize];
        this.numRecords = 0;
    }
    
    //~ Methods ...............................................................

    /**
     * gets the table size
     * @return the tables size
     */
    public int getTableSize() { return tableSize; }
    
    /**
     * gets the table size
     * @return the tables size
     */
    public int getNumRecords() { return numRecords; }
    
    /**
     * Probing method
     * @param homeSlot homeslot
     * @param i index
     * @return proper slot
     */
    public int getNextSlot(int homeSlot, int i) 
    {
        return (homeSlot + i * i) % tableSize;
    }
    
    /**
     * Insert a new record into the hash table.
     * Expands the table if it's more than half full.
     * 
     * @param key The key (string) to insert
     * @param value The value (Node) associated with the key
     * @param type artist or song
     */
    public void insert(String key, Node<String> value, String type) 
    {
        // Expand if capacity reaches more than 50%
        if (numRecords >= tableSize * LOAD_FACTOR_THRESHOLD) 
        {
            expand(type);
            System.out.println(type + " hash table size doubled.");
        }
        
        // Find hash key using hash function
        int homeSlot = h(key, tableSize);
        int i = 0;
        
        // Iterate to find insert slot
        while (table[getNextSlot(homeSlot, i)] != null &&
            table[(homeSlot + i * i) % tableSize] != TOMBSTONE) 
            
        {
            i++;
        }
        
        // Insert
        int insertSlot = getNextSlot(homeSlot, i);
        table[insertSlot] = new Record(key, value);
        numRecords++;
    }
    
    /**
     * Find a record in the hash table.
     * 
     * @param key The key to search for
     * @return The Node associated with the key, or null if not found
     */
    public Node<String> find(String key) 
    {
        int homeSlot = h(key, tableSize);
        int i = 0;

        // Iterate through hash table and find key
        while (table[Math.abs(getNextSlot(homeSlot, i))] != null) 
        {
            Record current = table[Math.abs(getNextSlot(homeSlot, i))];
            if (current != TOMBSTONE && current.getKey().equals(key)) 
            {
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
    public void remove(String key) 
    {
        int homeSlot = h(key, tableSize);
        int i = 0;
        
        // Iterate through Hash, find key and then remove
        while (table[Math.abs(getNextSlot(homeSlot, i))] != null) 
        {
            Record current = table[Math.abs(getNextSlot(homeSlot, i))];
            if (current != TOMBSTONE && current.getKey().equals(key)) 
            {
                table[Math.abs(getNextSlot(homeSlot, i))] = TOMBSTONE;
                numRecords--;
                return;
            }
            i++;
        }
        return;
    }
    
    /**
     * Print all the records in the hash table.
     * 
     * @return The count of printed records
     */
    public int print() 
    {
        int count = 0;
        
        // Iterates through hash table and prints every stored record
        for (int i = 0; i < tableSize; i++) 
        {
            if (table[i] != null && table[i] != TOMBSTONE) 
            {
                System.out.println(i + ": |" + table[i].getKey() + "|");
                count++;
            } 
            else if (table[i] == TOMBSTONE) 
            {
                System.out.println(i + ": TOMBSTONE");
            }
        }
        return count;
    }
    
    /**
     * Expand the hash table by doubling its size and re-inserting all 
     * valid records.
     * @param type artist or song
     */
    private void expand(String type) 
    {
        int newSize = tableSize * 2;
        Record[] oldTable = table;
        table = new Record[newSize];
        tableSize = newSize;
        numRecords = 0;  // re-insert, reset count

        // Re-insert records from old table into the new table
        for (int i = 0; i < oldTable.length; i++) 
        {
            if (oldTable[i] != null && oldTable[i] != TOMBSTONE) 
            {
                insert(oldTable[i].getKey(), oldTable[i].getValue(), type);
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
    public static int h(String s, int length) 
    {
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


    /**
     * gets the index of it
     * @param name is name of artist or song
     * @return int is integer
     */
    public int getIndex(String name) 
    {
        // Loop through the hash table to find the index of the record 
        for (int i = 0; i < table.length; i++) 
        {
            Record record = table[i];
            if (record != null && record.getKey().equals(name)) 
            {
                return i;  // Return the index if the name matches
            }
        }
        return -1;
    }
}
