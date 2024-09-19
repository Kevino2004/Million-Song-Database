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
        // 
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
     * Neighbors test
     */
    public void testNeighbors() {
        graph.addEdge(new Node<>("0"), new Node<>("1"));
        graph.addEdge(new Node<>("0"), new Node<>("2"));
        graph.addEdge(new Node<>("1"), new Node<>("3"));

        int[] neighborsOf0 = graph.neighbors(0);
        assertArrayEquals(new int[]{1, 2}, neighborsOf0);

        int[] neighborsOf1 = graph.neighbors(1);
        assertArrayEquals(new int[]{3}, neighborsOf1);
    }
    
    /**
     * tests print graph
     */
    public void testPrintGraph()
    {
        // 
    }
    
    /**
     * tests union
     */
    public void testUnion()
    {
        // 
    }
    
    /**
     * tests find
     */
    public void testFind()
    {
        // 
    }
}