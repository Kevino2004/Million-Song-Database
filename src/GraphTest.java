import student.TestCase;
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
     * Test adding nodes
     */
    public void testAddNode() {
        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");

        graph.addNode(nodeA);
        graph.addNode(nodeB);

        assertEquals(2, graph.numberOfNodes());
        assertTrue(graph.containsNode("A"));
        assertTrue(graph.containsNode("B"));
    }

    /**
     * Test adding edges between nodes
     */
    public void testAddEdge() {
        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addEdge(0, 1);

        assertTrue(graph.hasEdge(0, 1));
        assertTrue(graph.hasEdge(1, 0));
    }

    /**
     * Test removing edges
     */
    public void testRemoveEdge() {
        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addEdge(0, 1);
        graph.removeEdge(0, 1);

        assertFalse(graph.hasEdge(0, 1));
        assertFalse(graph.hasEdge(1, 0));
    }

    /**
     * Test removing nodes and their edges
     */
    public void testRemoveNode() {
        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addEdge(0, 1);
        graph.removeNode(0);

        assertFalse(graph.containsNode("A"));
        assertFalse(graph.hasEdge(0, 1));
    }

    /**
     * Test the union-find functionality
     */
    public void testUnionFind() {
        graph.union(0, 1);
        assertEquals(graph.find(0), graph.find(1));

        graph.union(2, 3);
        assertEquals(graph.find(2), graph.find(3));

        graph.union(1, 3);
        assertEquals(graph.find(0), graph.find(3));
    }

    /**
     * Test graph expansion when half full
     */
    public void testExpand() {
        for (int i = 0; i < 3; i++) {
            graph.addNode(new Node<>("Node " + i));
        }
        assertEquals(3, graph.numberOfNodes());
        graph.addNode(new Node<>("Node 3"));  // Should trigger expansion
        assertEquals(4, graph.numberOfNodes());
    }

    /**
     * Test printing the graph
     */
    public void testPrintGraph() {
        graph.addNode(new Node<>("A"));
        graph.addNode(new Node<>("B"));
        graph.addEdge(0, 1);

        graph.printGraph();  // Check output manually
    }
}