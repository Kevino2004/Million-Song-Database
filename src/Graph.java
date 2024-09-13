/**
 * Graph class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class Graph
{
    //~ Fields ................................................................
    private DoubleLL<Node<String>> vertexList;  // Adjacency list as a DLL
    private int numberOfNodes;
    //~ Constructors ..........................................................
    
    /**
     * Create a new Graph object.
     */
    public Graph() {
        this.vertexList = new DoubleLL<>();
        this.numberOfNodes = 0;
    }
    //~Public  Methods ........................................................
    
    /**
     * Add a new node (artist or song) to the graph.
     * 
     * @param newNode The node to be added to the graph
     */
    public void addNode(Node<String> newNode) {
        if (!containsNode(newNode.getData())) {
            vertexList.add(newNode);
            numberOfNodes++;
        }
    }

    /**
     * Add an edge between two nodes (artist and song) in the graph.
     * 
     * @param fromNode The starting node (artist)
     * @param toNode The destination node (song)
     */
    public void addEdge(Node<String> fromNode, Node<String> toNode) {
        if (fromNode != null && toNode != null && !hasEdge(fromNode, toNode)) {
            fromNode.getAdjacencyList().add(toNode);
            toNode.getAdjacencyList().add(fromNode);
        }
    }

    /**
     * Check if a node exists in the graph.
     * 
     * @param data The data to check
     * @return true if the node exists, false otherwise
     */
    public boolean containsNode(String data) {
        Node<String> current = vertexList.get(0);
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.next();
        }
        return false;
    }

    /**
     * Check if an edge exists between two nodes.
     * 
     * @param fromNode The starting node
     * @param toNode The destination node
     * @return true if an edge exists, false otherwise
     */
    public boolean hasEdge(Node<String> fromNode, Node<String> toNode) {
        return fromNode.getAdjacencyList().contains(toNode);
    }

    /**
     * Remove a node and all its edges from the graph.
     * 
     * @param nodeToRemove The data for the node to be removed
     */
    public void removeNode(Node<String> nodeToRemove) {
        Node<String> toRemove = getNode(nodeToRemove);
        if (toRemove != null) {
            // Remove all edges
            Node<String> current = toRemove.getAdjacencyList().get(0);
            while (current != null) {
                current.getAdjacencyList().remove(toRemove);
                current = current.next();
            }
            vertexList.remove(toRemove);
            numberOfNodes--;
        }
    }

    /**
     * Get a node by its data.
     * 
     * @param nodeToRemove The data of the node to retrieve
     * @return The node if found, null otherwise
     */
    public Node<String> getNode(Node<String> nodeToRemove) {
        Node<String> current = vertexList.get(0);
        while (current != null) {
            if (current.getData().equals(nodeToRemove)) {
                return current;
            }
            current = current.next();
        }
        return null;
    }

    /**
     * Print all the nodes and their edges.
     */
    public void printGraph() {
        Node<String> current = vertexList.get(0);
        while (current != null) {
            System.out.print(current.getData() + ": ");
            Node<String> adjNode = current.getAdjacencyList().get(0);
            while (adjNode != null) {
                System.out.print(adjNode.getData() + " ");
                adjNode = adjNode.next();
            }
            System.out.println();
            current = current.next();
        }
    }
}
