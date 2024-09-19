import student.TestCase;

/**
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.18.2024
 */
public class HashTest extends TestCase {
    private Hash hashTable;
    private Node<String> node1;
    private Node<String> node2;
    private Node<String> node3;
    
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        hashTable = new Hash(5);  // Initialize hash table with size 5
        
        // Sample Nodes
        node1 = new Node<>("Artist1");
        node2 = new Node<>("Artist2");
        node3 = new Node<>("Artist3");
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
     // Insert records and verify table behavior
        hashTable.insert("Song1", node1);
        assertEquals(node1, hashTable.find("Song1"));

        // Insert additional records to reach the load factor threshold
        hashTable.insert("Song2", node2);
        hashTable.insert("Song3", node3);

        // Inserting should trigger expand() after reaching threshold
        assertTrue(hashTable.print() == 3);  // Verify 3 elements inserted
        
        // Insert more elements to check if expand() is working
        Node<String> node4 = new Node<>("Song4");
        hashTable.insert("Song4", node4);
        assertEquals(node4, hashTable.find("Song4"));  // Ensure it's found

        // Check if expansion happened
        assertTrue(hashTable.print() == 4);  // 4 records after expansion
    }
    
    /**
     * Test quadratic probing in the insert method.
     */
    public void testQuadraticProbing() {
        // Insert records with intentionally colliding hash codes
        hashTable.insert("SongA", new Node<>("SongA"));
        hashTable.insert("SongB", new Node<>("SongB"));
        
        // They should be placed in different slots due to quadratic probing
        assertEquals("SongA", hashTable.find("SongA").getData());
        assertEquals("SongB", hashTable.find("SongB").getData());
    }

    /**
     * Test finding a record in the hash table.
     */
    public void testFind() {
     // Insert and find records
        hashTable.insert("Song1", node1);
        assertEquals(node1, hashTable.find("Song1"));

        // Try to find a record that doesn't exist
        assertNull(hashTable.find("NonExistentSong"));

        // Test quadratic probing for finding
        hashTable.insert("SongA", new Node<>("SongA"));
        hashTable.insert("SongB", new Node<>("SongB"));  // Forces probing

        // Ensure probing finds the right elements
        assertEquals("SongA", hashTable.find("SongA").getData());
        assertEquals("SongB", hashTable.find("SongB").getData());
    }

    /**
     * Test removing a record from the hash table.
     */
    public void testRemove() {
     // Insert and remove records
        hashTable.insert("Song1", node1);
        hashTable.remove("Song1");
        assertNull(hashTable.find("Song1"));  // Should not find removed element

        // Insert additional records to test removing after quadratic probing
        hashTable.insert("SongA", new Node<>("SongA"));
        hashTable.insert("SongB", new Node<>("SongB"));
        hashTable.remove("SongA");
        
        // Ensure SongA is removed and cannot be found
        assertNull(hashTable.find("SongA"));

        // Ensure SongB is still present
        assertEquals("SongB", hashTable.find("SongB").getData());
    }

    /**
     * Test the expansion of the hash table when the load factor threshold is 
     * exceeded.
     */
    public void testExpand() {
        // Insert enough records to trigger an expansion
        hashTable.insert("Song1", node1);
        hashTable.insert("Song2", node2);
        hashTable.insert("Song3", node3);

        // Expansion should occur, and the records should still be findable
        Node<String> node4 = new Node<>("Song4");
        hashTable.insert("Song4", node4);  // This triggers expand

        assertEquals(node1, hashTable.find("Song1"));
        assertEquals(node2, hashTable.find("Song2"));
        assertEquals(node3, hashTable.find("Song3"));
        assertEquals(node4, hashTable.find("Song4"));
    }
    
    /**
     * Test printing the contents of the hash table.
     */
    public void testPrint() {
     // Insert records and ensure print counts them correctly
        hashTable.insert("Song1", node1);
        hashTable.insert("Song2", node2);

        assertTrue(hashTable.print() == 2);  // 2 records printed

        // Insert and remove records, check print again
        hashTable.insert("Song3", node3);
        hashTable.remove("Song2");

        assertTrue(hashTable.print() == 2);  // Only Song 1 and 3 should print
    }
}
