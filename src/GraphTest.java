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

    /**
     * sets it up
     */
    public void setUp() {
        graph = new Graph(5);
    }
    
    // ----------------------------------------------------------
    /**
     * tests add Node
     */
    public void testAddNode()
    {
        // 
    }
    
    // ----------------------------------------------------------
    /**
     * tests remove Node
     */
    public void testRemoveNode()
    {
        // 
    }
    
    // ----------------------------------------------------------
    /**
     * tests expand
     */
    public void testExpand()
    {
        // 
    }
    
    
    /**
     * Add edge test.
     */
    public void testAddEdge() {
        graph.addEdge(new Node<>("0"), new Node<>("1"));
        graph.addEdge(new Node<>("1"), new Node<>("2"));

        assertTrue(graph.hasEdge(new Node<>("0"), new Node<>("1")));
        assertTrue(graph.hasEdge(new Node<>("1"), new Node<>("2")));
        assertFalse(graph.hasEdge(new Node<>("2"), new Node<>("0")));
    }


    /**
     * Remove edge test
     */
    public void testRemoveEdge() {
        graph.addEdge(new Node<>("0"), new Node<>("1"));
        graph.removeEdge(new Node<>("0"), new Node<>("1"));

        assertFalse(graph.hasEdge(new Node<>("0"), new Node<>("1")));
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
    
    // ----------------------------------------------------------
    /**
     * tests print graph
     */
    public void testPrintGraph()
    {
        // 
    }
    
    // ----------------------------------------------------------
    /**
     * tests union
     */
    public void testUnion()
    {
        // 
    }
    
    // ----------------------------------------------------------
    /**
     * tests find
     */
    public void testFind()
    {
        // 
    }
}