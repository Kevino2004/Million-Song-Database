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
        assertFalse(graph.hasEdge(a, c));
    }


    /**
     * Remove edge test
     */
    public void testRemoveEdge() {        
        graph.removeEdge(a, b);
        assertFalse(graph.hasEdge(a, b));
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
     * tests union
     */
    public void testUnion()
    {
        graph.union(0, 1);
        graph.union(1, 2);
        assertEquals(1, graph.connectedComponents());
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