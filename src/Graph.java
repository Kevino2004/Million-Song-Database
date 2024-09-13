/**
 * Graph class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class Graph
{
    //~ Fields ................................................................
    private DoubleLL vertexList;  // Adjacency list as a doubly linked list
    private int numberOfNodes;
    //~ Constructors ..........................................................
    
    /**
     * Create a new Graph object.
     */
    public Graph() {
        this.vertexList = new DoubleLL();
        this.numberOfNodes = 0;
    }
    //~Public  Methods ........................................................
    
    /**
     * Add a new node (artist or song) to the graph.
     * 
     * @param data The data for the new node (artist or song)
     */
    public void addNode(String data) {
        if (!containsNode(data)) {
            Node<String> newNode = new Node(data);
            vertexList.add(newNode);
            numberOfNodes++;
        }
    }

    /**
     * Add an edge between two nodes (artist and song) in the graph.
     * 
     * @param from The data of the start node (e.g., artist)
     * @param to The data of the destination node (e.g., song)
     */
    public void addEdge(String from, String to) {
        Node<String> fromNode = getNode(from);
        Node<String> toNode = getNode(to);

        if (fromNode != null && toNode != null && !hasEdge(fromNode, toNode)) {
            fromNode.getAdjacencyList().add(toNode);
            toNode.getAdjacencyList().add(fromNode);  // Since it's an undirected graph
        }
    }

    /**
     * Check if a node exists in the graph.
     * 
     * @param data The data to check
     * @return true if the node exists, false otherwise
     */
    public boolean containsNode(String data) {
        Node<String> current = vertexList.getHead();
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
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
     * @param data The data for the node to be removed
     */
    public void removeNode(String data) {
        Node<String> toRemove = getNode(data);
        if (toRemove != null) {
            // Remove all edges
            Node<String> current = toRemove.getAdjacencyList().getHead();
            while (current != null) {
                current.getAdjacencyList().remove(toRemove);
                current = current.getNext();
            }
            vertexList.remove(toRemove);
            numberOfNodes--;
        }
    }

    /**
     * Get a node by its data.
     * 
     * @param data The data of the node to retrieve
     * @return The node if found, null otherwise
     */
    public Node<String> getNode(String data) {
        Node<String> current = vertexList.getHead();
        while (current != null) {
            if (current.getData().equals(data)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Print all the nodes and their edges.
     */
    public void printGraph() {
        Node<String> current = vertexList.getHead();
        while (current != null) {
            System.out.print(current.getData() + ": ");
            Node<String> adjNode = current.getAdjacencyList().getHead();
            while (adjNode != null) {
                System.out.print(adjNode.getData() + " ");
                adjNode = adjNode.getNext();
            }
            System.out.println();
            current = current.getNext();
        }
    }
}
