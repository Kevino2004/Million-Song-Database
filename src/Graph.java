/**
 * Graph class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class Graph
{
    private DoubleLL<Node<String>>[] vertex;
    private int[] parent;
    private int[] size;
    private int numNodes;
    private int numComponents;
    private static final double LOAD_FACTOR_THRESHOLD = 0.5;
    private boolean[] slotTaken;

    /**
     * Constructor to initialize the graph with a given size.
     * 
     * @param init The initial size of the vertex list.
     */
    @SuppressWarnings("unchecked")
    public Graph(int init) {
        this.vertex = new DoubleLL[init];
        for (int i = 0; i < init; i++) {
            vertex[i] = new DoubleLL<>();
        }
        this.numNodes = 0;  
        this.slotTaken = new boolean[init];
        this.parent = new int[init];
        this.size = new int[init];
        this.numComponents = 0;
        
        for (int i = 0; i < init; i++) {
            parent[i] = i;
            size[i] = 0;
        }
    }
    
    /**
     * Return the current number of nodes
     * @return number of nodes
     */
    public int nodeCount() { return numNodes; }
    
    /**
     * Return the number of vertices
     * @return number of graph lists
     */
    public int listLength() { return vertex.length; }
    
    /**
     * Set the value of node with index v
     * @param val node
     */
    public void addNode(Node<String> val) 
    { 
        if (numNodes >= vertex.length * LOAD_FACTOR_THRESHOLD)
        {
            expand();
        }
        
        
        for (int i = 0; i < vertex.length; i++)
        {
            if (vertex[i].contains(val))
            {
                if (vertex[i].get(find(val)) == val)
                {
                    return;
                }
            }
        }
        
        int index = findFreeSlot();
        vertex[index].add(val); 
        slotTaken[index] = true;
        size[index]++;
        numNodes++;
        numComponents++;
    }
    
    /**
     * Remove the value of node with index v
     * @param val node
     */
    public void removeNode(Node<String> val) 
    { 
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i].contains(val)) 
            {
                vertex[i].clear();
                slotTaken[i] = false;
                size[i] = 0;
                numNodes--;
                numComponents--;
                break;
            }
        }
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
        int[] newSizeArray = new int[newSize];
        
        // Copy existing elements
        System.arraycopy(vertex, 0, newVertex, 0, vertex.length);
        System.arraycopy(slotTaken, 0, newFreedSlots, 0, slotTaken.length);
        System.arraycopy(parent, 0, newParent, 0, parent.length);
        System.arraycopy(size, 0, newSizeArray, 0, size.length);

        // Initialize new slots
        for (int i = vertex.length; i < newSize; i++) {
            newVertex[i] = new DoubleLL<>();
            newParent[i] = i; // New nodes are their own parent
            newSizeArray[i] = 0; // New nodes have size 0
        }

        this.vertex = newVertex;
        this.slotTaken = newFreedSlots;
        this.parent = newParent;
        this.size = newSizeArray;
    }

    /**
     * Adds a new edge
     * @param v Parent Node
     * @param w Edge Node
     */
    public void addEdge(Node<String> v, Node<String> w) {
        for (int i = 0; i < vertex.length; i++)
        {
            if (vertex[i].get(find(v)) == v) 
            {
                vertex[i].add(w);
            }
        }
    }

    

    /**
     * Removes the edge from the graph.
     * @param v Parent node
     * @param w Child node
     */
    public void removeEdge(Node<String> v, Node<String> w) {
        for (int i = 0; i < vertex.length; i++)
        {
            if (vertex[i].get(find(v)) == v) {
                vertex[i].remove(w);
            }
        }
    }

    /**
     * Returns true iff the graph has the edge
     * @param v Parent node
     * @param w Child node
     * @return T/F
     */
    public boolean hasEdge(Node<String> v, Node<String> w) { 
        for (int i = 0; i < vertex.length; i++)
        {
            if (vertex[i].get(find(v)) == v) 
            {
                if (vertex[i].contains(w)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Find the next free slot in the adjacency list
     * @return next available slot
     */
    private int findFreeSlot() {
        for (int i = 0; i < slotTaken.length; i++) {
            if (!slotTaken[i]) {
                return i;
            }
        }
        return numNodes;
    }
    
    /**
     * Prints the graph along with connected components information.
     */
    public void printGraph() {
        
        System.out.println("There are " + connectedComponents() + " connected"
            + " components");
        System.out.println("The largest connected component has " 
            + largestComponentSize() + " elements");
    }
    
    /**
     * Returns the size of the largest connected component.
     * @return largest component size
     */
    public int largestComponentSize() {
        int largest = 0;
        for (int i = 0; i < size.length; i++) {
            if (size[i] > largest) {
                largest = size[i];
            }
        }
        return largest;
    }

    /**
     * Computes and returns the number of connected components.
     * @return number of connected components.
     */
    public int connectedComponents() {
        int count = 0;
        for (int i = 0; i < vertex.length; i++)
        {
            //if (vertex)
        }
        return numComponents;
    }
    
    /**
     * Merge two nodes if they are different.
     * @param a key for node a
     * @param b key for node b
     */
    public void union(Node<String> a, Node<String> b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA != rootB) {
            // Attach the smaller tree under the larger tree
            if (size[rootA] < size[rootB]) {
                parent[rootA] = rootB;
                size[rootB] += size[rootA];  // Update size of rootB's component
            } else {
                parent[rootB] = rootA;
                size[rootA] += size[rootB];  // Update size of rootA's component
            }
            numComponents--;  // Reduce the number of components
        }
    }

    /**
     *  Find the root of the component that contains node v.
     *  @param node Node<String> instance.
     *  @return int
     */
    public int find(Node<String> node) {
        // Find the index of the node in the vertex array
        int nodeIndex = getNodeIndex(node);
        
        // Path compression to find the root of the component
        if (parent[nodeIndex] != nodeIndex) {
            parent[nodeIndex] = find(vertex[parent[nodeIndex]].get(0));
        }
        return parent[nodeIndex];
    }
    
    /**
     * Helper method to find the index of a node in the vertex array.
     * @param node Node<String> instance.
     * @return int - index of the node in the vertex array.
     */
    private int getNodeIndex(Node<String> node) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i].contains(node)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Node not found in graph.");
    }
}