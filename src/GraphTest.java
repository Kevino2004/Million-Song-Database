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

    // Setup method for test initialization
    public void setUp() {
        graph = new Graph(5);
    }
    
    /**
     * Add edge test.
     */
    public void testAddEdge() {
        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 2, 20);

        assertTrue(graph.hasEdge(0, 1));
        assertTrue(graph.hasEdge(1, 2));
        assertFalse(graph.hasEdge(0, 2));
    }

    /**
     * Weight test
     */
    public void testWeight() {
        graph.addEdge(0, 1, 15);
        graph.addEdge(1, 2, 25);

        assertEquals(15, graph.weight(0, 1));
        assertEquals(25, graph.weight(1, 2));
        assertEquals(0, graph.weight(0, 2)); // Edge does not exist
    }

    /**
     * Remove edge test
     */
    public void testRemoveEdge() {
        graph.addEdge(0, 1, 10);
        graph.removeEdge(0, 1);

        assertFalse(graph.hasEdge(0, 1));
    }

    /**
     * Neighbors test
     */
    public void testNeighbors() {
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 20);
        graph.addEdge(1, 3, 30);

        int[] neighborsOf0 = graph.neighbors(0);
        assertArrayEquals(new int[]{1, 2}, neighborsOf0);

        int[] neighborsOf1 = graph.neighbors(1);
        assertArrayEquals(new int[]{3}, neighborsOf1);
    }

    /**
     * Edge count test
     */
    public void testEdgeCount() {
        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 2, 20);
        graph.addEdge(2, 3, 30);

        assertEquals(3, graph.edgeCount());

        graph.removeEdge(1, 2);
        assertEquals(2, graph.edgeCount());
    }

    /**
     * Node count test
     */
    public void testNodeCount() {
        assertEquals(5, graph.nodeCount()); // Node count should be the initial size

        // Testing adding more edges but node count remains unchanged
        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 2, 20);
        assertEquals(5, graph.nodeCount());
    }

    /**
     * Get and set test
     */
    public void testGetValueAndSetValue() {
        graph.setValue(0, "Node0");
        graph.setValue(1, "Node1");

        assertEquals("Node0", graph.getValue(0));
        assertEquals("Node1", graph.getValue(1));
    }
}