import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;

/**
 * Hash Test Class
 * 
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.18.2024
 */
public class HashTest extends TestCase {
    private Hash hashTable;
    private Node<String> node1;
    private Node<String> node2;
    private Node<String> node3;
    private String type;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        hashTable = new Hash(5);
        
        node1 = new Node<>("Song1");
        node2 = new Node<>("Song2");
        node3 = new Node<>("Song3");
        type = "Song";
        System.setOut(new PrintStream(out));
    }


    /**
     * Check out the sfold method
     *
     * @throws Exception
     *             either a IOException or FileNotFoundException
     */
    public void testSfold() throws Exception {
        assertTrue(Hash.h("a", 10000) == 97);
        assertTrue(Hash.h("b", 10000) == 98);
        assertTrue(Hash.h("aaaa", 10000) == 1873);
        assertTrue(Hash.h("aaab", 10000) == 9089);
        assertTrue(Hash.h("baaa", 10000) == 1874);
        assertTrue(Hash.h("aaaaaaa", 10000) == 3794);
        assertTrue(Hash.h("Long Lonesome Blues", 10000) == 4635);
        assertTrue(Hash.h("Long   Lonesome Blues", 10000) == 4159);
        assertTrue(Hash.h("long Lonesome Blues", 10000) == 4667);
    }
    
    /**
     * Test inserting records into the hash table.
     */
    public void testInsert() {
        hashTable.insert("Song1", node1, type);
        assertEquals(node1, hashTable.find("Song1"));

        hashTable.insert("Song2", node2, type);
        hashTable.insert("Song3", node3, type);

        assertTrue(hashTable.print() == 3);
        
        Node<String> node4 = new Node<>("Song4");
        hashTable.insert("Song4", node4, type);
        assertEquals(node4, hashTable.find("Song4"));
        

        assertTrue(hashTable.print() == 4);
        assertEquals("1: |Song1|\r\n"
            + "2: |Song2|\r\n"
            + "3: |Song3|\r\n"
            + "Song hash table size doubled.\r\n"
            + "6: |Song1|\r\n"
            + "7: |Song2|\r\n"
            + "8: |Song3|\r\n"
            + "9: |Song4|\n", out.toString());
        
        hashTable.remove("Song3");
        assertNull(hashTable.find("Song3")); 
        
        Node<String> node5 = new Node<>("Song5");
        hashTable.insert("Song5", node5, type);
        assertEquals(node5, hashTable.find("Song5"));
        
        Node<String> node6 = new Node<>("Song6");
        hashTable.insert("Song6", node6, type);
        assertEquals(node6, hashTable.find("Song6"));
    }
    
    /**
     * Test quadratic probing in the insert method.
     */
    public void testGetNextSlot() {
        
        int homeSlot = 2;
        
        assertEquals(2, hashTable.getNextSlot(homeSlot, 0));  
        // i = 0, (2 + 0 * 0) % 5 = 2
        assertEquals(3, hashTable.getNextSlot(homeSlot, 1));  
        // i = 1, (2 + 1 * 1) % 5 = 3
        assertEquals(1, hashTable.getNextSlot(homeSlot, 2));  
        // i = 2, (2 + 2 * 2) % 5 = 1
        assertEquals(1, hashTable.getNextSlot(homeSlot, 3));  
        // i = 3, (2 + 3 * 3) % 5 = 1
        assertEquals(3, hashTable.getNextSlot(homeSlot, 4));  
        // i = 4, (2 + 4 * 4) % 5 = 3
    }

    /**
     * Test finding a record in the hash table.
     */
    public void testFind() {
     // Insert and find records
        hashTable.insert("Song1", node1, type);
        assertEquals(node1, hashTable.find("Song1"));

        // Try to find a record that doesn't exist
        assertNull(hashTable.find("NonExistentSong"));

        // Test quadratic probing for finding
        hashTable.insert("SongA", new Node<>("SongA"), type);
        hashTable.insert("SongB", new Node<>("SongB"), type);  // Forces probing

        // Ensure probing finds the right elements
        assertEquals("SongA", hashTable.find("SongA").getData());
        assertEquals("SongB", hashTable.find("SongB").getData());
    }

    /**
     * Test removing a record from the hash table.
     */
    public void testRemove() {
        hashTable.insert("Song1", node1, type);
        hashTable.remove("Song1");      
        assertNull(hashTable.find("Song1"));    
               
        hashTable.insert("SongA", new Node<>("SongA"), type);  
        hashTable.insert("SongB", new Node<>("SongB"), type);   
        hashTable.remove("SongA");
        
        assertNull(hashTable.find("SongA"));    
        assertEquals("SongB", hashTable.find("SongB").getData());
    }

    /**
     * Test the expansion of the hash table when the load factor threshold is 
     * exceeded.
     */
    public void testExpand() {
        assertEquals(5, hashTable.getTableSize());
        // Insert enough records to trigger an expansion
        hashTable.insert("Song1", node1, type);
        hashTable.insert("Song2", node2, type);
        hashTable.insert("Song3", node3, type);

        // Expansion should occur, and the records should still be findable
        Node<String> node4 = new Node<>("Song4");
        hashTable.insert("Song4", node4, type);  // This triggers expand

        assertEquals(node1, hashTable.find("Song1"));
        assertEquals(node2, hashTable.find("Song2"));
        assertEquals(node3, hashTable.find("Song3"));
        assertEquals(node4, hashTable.find("Song4"));
        
        assertEquals(10, hashTable.getTableSize());
        
        assertTrue(hashTable.print() == 4);
    }
    
    /**
     * Test printing the contents of the hash table.
     */
    public void testPrint() {
     // Insert records and ensure print counts them correctly
        hashTable.insert("Song1", node1, type);
        hashTable.insert("Song2", node2, type);

        assertTrue(hashTable.print() == 2);  // 2 records printed

        // Insert and remove records, check print again
        hashTable.insert("Song3", node3, type);
        hashTable.remove("Song2");

        assertTrue(hashTable.print() == 2);  // Only Song 1 and 3 should print
    }
    
    /**
     * Test the getIndex method in the Hash class.
     */
    public void testGetIndex() {
        // Insert records into the hash table
        hashTable.insert("Song1", node1, type);
        hashTable.insert("Song2", node2, type);
        hashTable.insert("Song3", node3, type);
        
        // Test finding existing records
        int index1 = hashTable.getIndex("Song1");
        assertTrue(index1 >= 0); // Ensure the index is non-negative
        assertEquals(node1.getData(), hashTable.find("Song1").getData());
        
        int index2 = hashTable.getIndex("Song2");
        assertTrue(index2 >= 0);
        assertEquals(node2.getData(), hashTable.find("Song2").getData());
        
        int index3 = hashTable.getIndex("Song3");
        assertTrue(index3 >= 0);
        assertEquals(node3.getData(), hashTable.find("Song3").getData());
        
        // Test finding a record that doesn't exist
        int nonExistentIndex = hashTable.getIndex("NonExistentSong");
        assertEquals(-1, nonExistentIndex); // Ensure -1 is returned

        // Test with an empty hash table
        Hash emptyHashTable = new Hash(5);
        int emptyIndex = emptyHashTable.getIndex("AnySong");
        assertEquals(-1, emptyIndex); // Ensure -1 is returned
    }

    /**
     * Test the expand method in the Hash class, specifically checking 
     * the handling of records when expanding the table.
     */
    public void testExpandWithTombstones() {
        // Check the table size before expansion
        int initialSize = hashTable.getTableSize();
        
        // Initialize the hash table and insert records
        hashTable.insert("Song1", node1, type);
        hashTable.insert("Song2", node2, type);
        hashTable.insert("Song3", node3, type);
        
        // Verify the new table size
        int newSize = hashTable.getTableSize();
        assertEquals(initialSize, newSize); // Ensure the size has doubled
        
        // Remove one record to create a tombstone
        hashTable.remove("Song2");

        // Verify that "Song1" and "Song3" are still in the hash table
        assertEquals(node1, hashTable.find("Song1"));
        assertEquals(node3, hashTable.find("Song3"));
        
        // Verify that "Song2" is not found (was removed)
        assertNull(hashTable.find("Song2"));
    }

}
