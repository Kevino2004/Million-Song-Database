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
        hashTable.insert("Artist1", node1);
        hashTable.insert("Artist2", node2);
        
        // Check if the records were inserted correctly
        assertEquals(node1, hashTable.find("Artist1"));
        assertEquals(node2, hashTable.find("Artist2"));
    }

    /**
     * Test finding a record in the hash table.
     */
    public void testFind() {
        hashTable.insert("Artist1", node1);
        
        // Test valid find
        Node<String> foundNode = hashTable.find("Artist1");
        assertEquals(node1, foundNode);
        
        // Test finding a non-existent key
        assertNull(hashTable.find("NonExistent"));
    }

    /**
     * Test removing a record from the hash table.
     */
    public void testRemove() {
        hashTable.insert("Artist1", node1);
        hashTable.insert("Artist2", node2);
        
        // Remove Artist1
        hashTable.remove("Artist1");
        
        // Ensure Artist1 is removed and cannot be found
        assertNull(hashTable.find("Artist1"));
        
        // Ensure Artist2 still exists
        assertEquals(node2, hashTable.find("Artist2"));
    }

    /**
     * Test the expansion of the hash table when the load factor threshold is 
     * exceeded.
     */
    public void testExpand() {
        // Insert multiple records to exceed the load factor threshold
        hashTable.insert("Artist1", node1);
        hashTable.insert("Artist2", node2);
        hashTable.insert("Artist3", node3);
        
        // The records should still be accessible after expansion
        assertEquals(node1, hashTable.find("Artist1"));
        assertEquals(node2, hashTable.find("Artist2"));
        assertEquals(node3, hashTable.find("Artist3"));
    }
    
    /**
     * Test printing the contents of the hash table.
     */
    public void testPrint() {
        hashTable.insert("Artist1", node1);
        hashTable.insert("Artist2", node2);
        
        // Capture the printed output
        systemOut().clearHistory();
        hashTable.print();
        
        String output = systemOut().getHistory();
        assertTrue(output.contains("Key: Artist1"));
        assertTrue(output.contains("Key: Artist2"));
    }
}
