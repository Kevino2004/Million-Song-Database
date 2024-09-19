/**
 * Graph class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class Graph
{
    private DoubleLL<Node<String>>[] vertex;  
    private int numberOfNodes;
    private boolean[] freedSlots;
    private int[] parent; 
    private int[] weights;

    /**
     * Constructor to initialize the graph with a given size.
     * 
     * @param initialSize The initial size of the vertex list.
     */
    @SuppressWarnings("unchecked")
    public Graph(int initialSize) {
        this.vertex = new DoubleLL[initialSize];
        this.numberOfNodes = 0;
        this.freedSlots = new boolean[initialSize];
        this.parent = new int[initialSize];
        this.weights = new int[initialSize];

        // Initialize union-find structure
        for (int i = 0; i < initialSize; i++) {
            this.parent[i] = i;
            this.weights[i] = 1;
        }
    }

    /**
     * Add a new node to the graph
     * @param newNode new node.
     */
    public void addNode(Node<String> newNode) {
        if (numberOfNodes >= vertex.length * 0.5) {
            expand();
        }
        int index = findFreeSlot();
        vertex[index] = new DoubleLL<>();
        vertex[index].add(newNode);
        numberOfNodes++;
    }

    /**
     * Add an edge between two nodes
     * @param fromIndex integer
     * @param toIndex integer
     */
    public void addEdge(int fromIndex, int toIndex) {
        if (!hasEdge(fromIndex, toIndex)) {
            vertex[fromIndex].add(new Node<>(vertex[toIndex].get(0).getData()));
            vertex[toIndex].add(new Node<>(vertex[fromIndex].get(0).getData()));
        }
    }

    /**
     * Check if an edge exists
     * @param fromIndex integer
     * @param toIndex integer
     * @return T/F
     */
    public boolean hasEdge(int fromIndex, int toIndex) {
        return vertex[fromIndex].contains(new Node<>(vertex[toIndex].get(0)
            .getData()));
    }

    /**
     * Remove an edge
     * @param fromIndex integer
     * @param toIndex integer
     */
    public void removeEdge(int fromIndex, int toIndex) {
        vertex[fromIndex].remove(new Node<>(vertex[toIndex].get(0).getData()));
        vertex[toIndex].remove(new Node<>(vertex[fromIndex].get(0).getData()));
    }

    /**
     * Remove a node and its edges
     * @param nodeToRemove node to remove
     */
    public void removeNode(Node<String> nodeToRemove) {
        int index = hash(nodeToRemove.getData()); // Use hash to determine the index
        if (vertex[index] != null) { // Check if the index is valid
            // Remove all edges
            Node<String> current = nodeToRemove.getAdjacencyList().get(0);
            while (current != null) {
                current.getAdjacencyList().remove(nodeToRemove);
                current = current.next();
            }
            vertex[index].remove(nodeToRemove); // Remove the node from the adjacency list
            numberOfNodes--;
        }
    }
    
    /**
     * hash data for graph
     */
    private int hash(String data) {
        return Math.abs(data.hashCode() % vertex.length);
    }

    /**
     * Expand the graph when it becomes half full
     */
    @SuppressWarnings("unchecked")
    private void expand() {
        int newSize = vertex.length * 2;
        DoubleLL<Node<String>>[] newVertex = new DoubleLL[newSize];
        boolean[] newFreedSlots = new boolean[newSize];
        int[] newParent = new int[newSize];
        int[] newWeights = new int[newSize];

        System.arraycopy(vertex, 0, newVertex, 0, vertex.length);
        System.arraycopy(freedSlots, 0, newFreedSlots, 0, freedSlots.length);
        System.arraycopy(parent, 0, newParent, 0, parent.length);
        System.arraycopy(weights, 0, newWeights, 0, weights.length);

        this.vertex = newVertex;
        this.freedSlots = newFreedSlots;
        this.parent = newParent;
        this.weights = newWeights;
    }

    /**
     * Find the next free slot in the adjacency list
     */
    private int findFreeSlot() {
        for (int i = 0; i < freedSlots.length; i++) {
            if (freedSlots[i]) {
                freedSlots[i] = false;
                return i;
            }
        }
        return numberOfNodes;
    }

    /**
     * Union operation for union-find
     * @param a integer
     * @param b integer
     */
    public void union(int a, int b) {
        int root1 = find(a);
        int root2 = find(b);
        if (root1 != root2) {
            if (weights[root2] > weights[root1]) {
                parent[root1] = root2;
                weights[root2] += weights[root1];
            } else {
                parent[root2] = root1;
                weights[root1] += weights[root2];
            }
        }
    }

    /**
     * Find operation for union-find (path compression)
     * @param a int to find
     * @return found
     */
    public int find(int a) {
        if (parent[a] != a) {
            parent[a] = find(parent[a]);  // Path compression
        }
        return parent[a];
    }

    /**
     * Print the graph.
     */
    public void printGraph() {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] != null && vertex[i].size() > 0) {
                System.out.print("Node " + i + " (" + vertex[i].get(0).
                    getData() + "): ");

                // Manually iterate over the adjacency list
                Node<String> current = vertex[i].get(0);  // Get the first node in the adjacency list
                while (current != null) {
                    System.out.print(current.getData() + " ");
                    current = current.next();  // Move to the next node in the adjacency list
                }
                System.out.println();  // New line after printing adjacency list
            }
        }
    }
    
    /**
     * Return number of nodes.
     * @return number of nodes
     */
    public int getNumberOfNodes()
    {
        return numberOfNodes;
    }
    
    /**
     * Check if contains node
     * @param data string
     * @return T/F
     */
    public boolean containsNode(String data)
    {
        // Iterate over each list in the vertex array
        for (DoubleLL<Node<String>> list : vertex) {
            if (list != null) {  // Ensure the list is not null
                Node<String> current = list.get(0);  // Start with the first node in the list
                while (current != null) {  // Traverse the linked list
                    if (current.getData().equals(data)) {  // Check if the current node's data matches
                        return true;  // Node with the specified data is found
                    }
                    current = current.next();  // Move to the next node
                }
            }
        }
        return false;  // Node with the specified data was not found
    }
}