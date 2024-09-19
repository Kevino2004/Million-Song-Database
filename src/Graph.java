/**
 * Graph class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class Graph
{
    private DoubleLL<Node<String>>[] vertex;
    private int[] parent; // To track parent of each node
    private int[] size;   // To track size of each component
    private int numNodes;
    private int numComponents;
    private static final double LOAD_FACTOR_THRESHOLD = 0.5;
    private boolean freedSlots[];

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
        this.freedSlots = new boolean[init];
        this.parent = new int[init];
        this.size = new int[init];
        this.numComponents = 0;
        
        for (int i = 0; i < init; i++) {
            parent[i] = i;  // Initialize each node to be its own parent
            size[i] = 1;    // Initially, each component has size 1
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
        
        int index = findFreeSlot();
        vertex[index].add(val); 
        freedSlots[index] = true;
        numNodes++;
        numComponents++;
    }
    
    /**
     * Remove the value of node with index v
     * @param val node
     */
    public void removeNode(Node<String> val) 
    { 
        for (int i = 0; i < vertex.length; i++)
        {
            if (vertex[i].contains(val))
            {
                vertex[i].clear();
                freedSlots[i] = false;
            }
        }
        numNodes--;
    }
    
    /**
     * Expand the graph when it becomes half full
     */
    @SuppressWarnings("unchecked")
    private void expand() {
        int newSize = vertex.length * 2;
        DoubleLL<Node<String>>[] newVertex = new DoubleLL[newSize];
        boolean[] newFreedSlots = new boolean[newSize];

        System.arraycopy(vertex, 0, newVertex, 0, vertex.length);
        System.arraycopy(freedSlots, 0, newFreedSlots, 0, freedSlots.length);

        this.vertex = newVertex;
        this.freedSlots = newFreedSlots;
    }

    /**
     * Adds a new edge
     * @param v node
     * @param w node
     */
    public void addEdge(Node<String> v, Node<String> w) {
        for (int i = 0; i < vertex.length; i++)
        {
            if (vertex[i].contains(v) && v.next() == null) 
            {
                v.setNext(w);
            }
        }
    }

    

    /**
     * Removes the edge from the graph.
     * @param v node
     * @param w node
     */
    public void removeEdge(Node<String> v, Node<String> w) {
        for (int i = 0; i < vertex.length; i++)
        {
            if (vertex[i].contains(v)) {
                if (v.next() == w || v.previous() == w) {
                    v.setNext(null);
                }
            }
        }
    }

    /**
     * Returns true iff the graph has the edge
     * @param v node
     * @param w node
     * @return T/F
     */
    public boolean hasEdge(Node<String> v, Node<String> w) { 
        for (int i = 0; i < vertex.length; i++)
        {
            if (vertex[i].contains(v)) 
            {
                return v.next() == w || v.previous() == w;
                
            }
        }
        return false;
    }
    
    /**
     * Find the next free slot in the adjacency list
     * @return next available slot
     */
    private int findFreeSlot() {
        for (int i = 0; i < freedSlots.length; i++) {
            if (!freedSlots[i]) {
                return i;
            }
        }
        return numNodes;
    }
    
    /**
     * Prints the graph along with connected components information.
     */
    public void printGraph() {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] != null && vertex[i].size() > 0) {
                System.out.print("Node " + i + " (" + vertex[i].get(0)
                    .getData() + "): ");
                Node<String> current = vertex[i].get(0);
                while (current != null) {
                    System.out.print(current.getData() + " ");
                    current = current.next();
                }
                System.out.println();
            }
        }
        System.out.println("Number of connected components: " 
            + connectedComponents());
        System.out.println("Size of the largest connected component: " 
            + largestComponentSize());
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
        return numComponents;
    }
    
    /**
     * Merge two subtrees if they are different.
     * @param a key for node a
     * @param b key for node b
     */
    public void UNION(int a, int b) {
        int rootA = FIND(a);
        int rootB = FIND(b);
        
        if (rootA != rootB) {
            if (size[rootA] < size[rootB]) {
                parent[rootA] = rootB;
                size[rootB] += size[rootA];
            } else {
                parent[rootB] = rootA;
                size[rootA] += size[rootB];
            }
            numComponents--;  // Decrease the number of components
        }
    }

    /**
     *  Find the root of the component that contains node v.
     *  @param v tree
     *  @return int
     */
    public int FIND(int v) {
        if (parent[v] != v) {
            parent[v] = FIND(parent[v]); // Path compression
        }
        return parent[v];
    }
}