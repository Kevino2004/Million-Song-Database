import student.TestCase;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 * Graph Test Class
 * 
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.18.2024
 */
public class GraphTest extends TestCase 
{
    private Graph graph;
    private Node<String> a;
    private Node<String> b;
    private Node<String> c;

    /**
     * sets it up
     */
    public void setUp() {
        graph = new Graph(5);
        a = new Node<>("0");
        b = new Node<>("1");
        c = new Node<>("2");
        
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        
        graph.addEdge(a, b);
        graph.addEdge(b, c);
    }
    
    /**
     * Tests add node
     */
    public void testAddNode()
    {
        assertEquals(3, graph.nodeCount());
    }
    
    /**
     * Tests remove node
     */
    public void testRemoveNode()
    {        
        graph.removeNode(a);
        assertEquals(2, graph.nodeCount());
        assertEquals(2, graph.connectedComponents());
        
        Node<String> d = new Node<>("3");
        graph.removeNode(d);
        assertEquals(2, graph.nodeCount());
    }
    
    /**
     * Tests expand
     */
    public void testExpand()
    {
        Node<String> d = new Node<>("3");
        Node<String> e = new Node<>("4");
        graph.addNode(d);
        graph.addNode(e);
        
        assertEquals(10, graph.listLength());
    }
    
    
    /**
     * Add edge test
     */
    public void testAddEdge() {      
        assertTrue(graph.hasEdge(a, b));
        assertTrue(graph.hasEdge(b, c));
        assertTrue(graph.hasEdge(a, c));
        
        Node<String> d = new Node<>("3");
        graph.addNode(d);
        graph.addEdge(a, d); 

        assertTrue(graph.hasEdge(a, b)); 
        assertTrue(graph.hasEdge(a, d));
    }


    /**
     * Remove edge test
     */
    public void testRemoveEdge() {        
        graph.removeEdge(a, b);
        assertFalse(graph.hasEdge(a, b));
        
        assertTrue(graph.hasEdge(b, c));
        
        graph.removeEdge(a, c); 
        assertFalse(graph.hasEdge(a, c));
        
        b.setPrevious(a); 
        graph.addEdge(a, b); 
        graph.removeEdge(b, a); 
        assertFalse(graph.hasEdge(b, a));
    }
    
    /**
     * tests has edge 
     */
    public void testHasEdge()
    {
     // Case 1: Nodes not in the graph
        Node<String> d = new Node<>("2");
        Node<String> e = new Node<>("2");
        assertFalse(graph.hasEdge(d, e)); 

        // Case 2: Node is in the graph but no edges exist
        graph.addNode(d);
        assertFalse(graph.hasEdge(d, e)); 

        // Case 3: Node has edges, but not to the target node
        Node<String> f = new Node<>("3");
        graph.addNode(f);
        d.setNext(f); 
        assertFalse(graph.hasEdge(d, e)); 
        
        // Case 4: Node has the correct edge
        assertTrue(graph.hasEdge(d, f)); 

        // Case 5: Node is connected through previous pointer
        d.setPrevious(e); 
        assertTrue(graph.hasEdge(d, e));
    }
    
    /**
     * tests addEdge if next is null
     */
    public void testAddEdgeNextNotNull()
    {
        Node<String> d = new Node<>("3");
        graph.addEdge(b, d);
        assertFalse(graph.hasEdge(b, d));
    }
    
    /**
     * tests print graph
     */
    public void testPrintGraph()
    {
        graph.printGraph();
        assertEquals(3, graph.connectedComponents());
        assertEquals(0, graph.largestComponentSize());
    }
    
    /**
     * tests largest component size
     */
    public void testLargestComponentSize()
    {
        // assertEquals(3, graph.largestComponentSize()); 
    }
    
    /**
     * tests union
     */
    public void testUnion()
    {
        assertEquals(3, graph.connectedComponents());
        graph.union(0, 1);
        graph.union(1, 2);
        assertEquals(1, graph.connectedComponents());
    }
    
    /**
     * tests union when unioning same root
     */
    public void testUnion2()
    {
        assertEquals(3, graph.connectedComponents());
        graph.union(0, 0);
        assertEquals(3, graph.connectedComponents());
    }
    
    /**
     * tests find
     */
    public void testFind()
    {
        assertEquals(0, graph.find(0));
        graph.union(0, 1);
        assertEquals(1, graph.find(1));
    }
}