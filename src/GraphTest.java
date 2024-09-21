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
    }
    
    /**
     * Tests add node
     */
    public void testAddNode()
    {
        assertEquals(3, graph.nodeCount());
        graph.addNode(a);
        assertEquals(3, graph.nodeCount());
        
        Node<String> d = new Node<>("2");
        graph.addNode(d);
        assertEquals(4, graph.nodeCount());
    }
    
    /**
     * Tests remove node
     */
    public void testRemoveNode()
    {        
        graph.addEdge(a, b);
        assertTrue(graph.hasEdge(a, b));
        graph.removeNode(a);
        assertEquals(2, graph.nodeCount());
        
        Node<String> d = new Node<>("3");
        graph.addNode(d);
        graph.removeNode(d);
        assertEquals(2, graph.nodeCount());
        assertFalse(graph.hasEdge(a, b));
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
        graph.addEdge(a, b);
        graph.addEdge(b, c);
        
        assertTrue(graph.hasEdge(a, b));
        assertTrue(graph.hasEdge(b, c));
        assertFalse(graph.hasEdge(a, c));
        
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
        graph.addEdge(a, b);
        graph.addEdge(b, c);
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
        graph.addEdge(a, b);
        graph.addEdge(b, c);
        
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
    }
    
    /**
     * tests print graph
     */
    public void testPrintGraph()
    {
        graph.addEdge(a, b);
        graph.addEdge(b, c);
        graph.printGraph();
        assertEquals(3, graph.connectedComponents());
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
        int[] p = new int[5];
        p[0] = 1; p[1] = 2; p[2] = 2; p[3] = 4; p[4] = 4;
        graph.union(0,1);
        graph.union(1,2);
        graph.union(3,4);
        assertEquals(p[0], graph.getParent(0));
        assertEquals(p[1], graph.getParent(1));
        assertEquals(p[2], graph.getParent(2));
        assertEquals(p[3], graph.getParent(3));
        assertEquals(p[4], graph.getParent(4));
    }
    
    /**
     * tests union with same root
     */
    public void testUnion2()
    {
        graph.addEdge(a, b);
        graph.addEdge(b, c);
        assertEquals(2, graph.getParent(0));
    }
}