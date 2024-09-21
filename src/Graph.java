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
        
        for (int i = 0; i < init; i++) {
            parent[i] = i;
            size[i] = 1;
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
     * Return the number of nodes from parent
     * @param i index
     * @return number of graph lists
     */
    public int getParent(int i) { return parent[i]; }
    
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
                if (vertex[i].get(0) == val)
                {
                    return;
                }
            }
        }
        
        int index = findFreeSlot();
        vertex[index].add(0, val); 
        slotTaken[index] = true;
        numNodes++;
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
                if (vertex[i].get(0) == val)
                {
                    vertex[i].clear();
                    slotTaken[i] = false;
                    numNodes--;
                    break;
                }
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
        
        // Initialize new slots
        for (int i = vertex.length; i < newSize; i++) {
            newVertex[i] = new DoubleLL<>();
            newParent[i] = i; // New nodes are their own parent
            newSizeArray[i] = 0; // New nodes have size 0
        }
       
        // Copy existing elements
        System.arraycopy(vertex, 0, newVertex, 0, vertex.length);
        System.arraycopy(slotTaken, 0, newFreedSlots, 0, slotTaken.length);
        System.arraycopy(parent, 0, newParent, 0, parent.length);
        System.arraycopy(size, 0, newSizeArray, 0, size.length);

        this.vertex = newVertex;
        this.slotTaken = newFreedSlots;
        this.parent = newParent;
        this.size = newSizeArray;
    }

    /**
     * Adds a new edge
     * @param v Parent node
     * @param w Edge node
     */
    public void addEdge(Node<String> v, Node<String> w) {
        int indexV = -1;
        int indexW = -1;
        
        for (int i = 0; i < vertex.length; i++)
        {
            if (vertex[i].contains(v))
            {
                if (vertex[i].get(0) == v)
                {
                    vertex[i].add(w);
                    indexV = i;
                }
            }
            if (vertex[i].contains(w))
            {
                if (vertex[i].get(0) == w)
                {
                    indexW = i;
                }
            }
        }
        union(indexV, indexW);
    }

    

    /**
     * Removes the edge from the graph.
     * @param v Parent node
     * @param w Edge node
     */
    public void removeEdge(Node<String> v, Node<String> w) {
        for (int i = 0; i < vertex.length; i++)
        {
            if (vertex[i].contains(v))
            {
                if (vertex[i].get(0) == v)
                {
                    vertex[i].remove(w);
                }
            }
        }
    }

    /**
     * Returns true iff the graph has the edge
     * @param v Parent node
     * @param w Edge node
     * @return T/F
     */
    public boolean hasEdge(Node<String> v, Node<String> w) { 
        for (int i = 0; i < vertex.length; i++)
        {
            if (vertex[i].contains(v))
            {
                if (vertex[i].get(0) == v)
                {
                    if (vertex[i].contains(w))
                    {
                        return true;
                    }
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
        if (numNodes == 0)
        {
            return 0;
        }
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
        
        // Loop over all possible nodes, 
        // checking if slot is taken and if it's a root
        for (int i = 0; i < numNodes; i++) {
            if (parent[i] == i) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Merge two nodes if they are different.
     * @param a key for node a
     * @param b key for node b
     */
    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            // Attach the smaller tree under the larger tree
            if (size[rootA] < size[rootB]) {
                parent[rootA] = rootB;
                size[rootB] += size[rootA];
            } 
            else {
                parent[rootB] = rootA;
                size[rootA] += size[rootB];
            }
        }
    }

    /**
     *  Find the root of the component that contains node v.
     *  @param i index
     *  @return int
     */
    public int find(int i) {
        // Path compression: point node to its root directly
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }
    
}