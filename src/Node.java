/**
 * Node class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class Node
{
    //~ Fields ................................................................
    private String data;  // Data stored in this node (artist or song)
    private LinkedList<Node> adjacentNodes;  // Adjacency list for graph edges
    //~ Constructors ..........................................................
    /**
     * Create a new Node object with the given data.
     * 
     * @param data The data (artist or song) for this node
     */
    public Node(String data) {
        this.data = data;
        this.adjacentNodes = new LinkedList<>();
    }
    //~Public  Methods ........................................................

    /**
     * Get the data of this node.
     * 
     * @return The data (artist or song)
     */
    public String getData() {
        return data;
    }
    
    /**
     * Set the data of this node.
     * 
     * @param data The new data (artist or song) to set
     */
    public void setData(String data) {
        this.data = data;
    }
    
    /**
     * Add an adjacent node (create an edge).
     * 
     * @param node The node to add as adjacent (creates a connection between nodes)
     */
    public void addAdjacentNode(Node node) {
        adjacentNodes.add(node);
    }
    
    /**
     * Remove an adjacent node (remove an edge).
     * 
     * @param node The node to remove from the adjacency list
     */
    public void removeAdjacentNode(Node node) {
        adjacentNodes.remove(node);
    }
    
    /**
     * Get the list of adjacent nodes.
     * 
     * @return A list of adjacent nodes (connections in the graph)
     */
    public LinkedList<Node> getAdjacentNodes() {
        return adjacentNodes;
    }
    
    /**
     * Check if a node is adjacent to this node.
     * 
     * @param node The node to check adjacency with
     * @return true if the node is adjacent, false otherwise
     */
    public boolean isAdjacent(Node node) {
        return adjacentNodes.contains(node);
    }
    
    /**
     * Return a string representation of the node.
     * 
     * @return A string in the form "Node: [data], Adjacent: [adjacent nodes]"
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node: ").append(data).append(", Adjacent: ");
        
        for (Node node : adjacentNodes) {
            sb.append(node.getData()).append(" ");
        }
        
        return sb.toString();
    }
}
