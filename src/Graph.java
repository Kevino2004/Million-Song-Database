/**
 * Graph class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class Graph
{
    private DoubleLL<Node<String>>[] vertex;
    private int numNodes;
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
            if (vertex[i].contains(v)) {
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
                    vertex[i].remove(w);
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
     * Print the graph.
     */
    public void printGraph() {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] != null && vertex[i].size() > 0) {
                System.out.print("Node " + i + " (" + vertex[i].get(0).
                    getData() + "): ");

                // Manually iterate over the adjacency list
                Node<String> current = vertex[i].get(0);  
                // Get the first node in the adjacency list
                while (current != null) {
                    System.out.print(current.getData() + " ");
                    current = current.next();  
                    // Move to the next node in the adjacency list
                }
                System.out.println();  // New line after printing adjacency list
            }
        }
    }

    /**
     * Returns an array containing the indicies of the neighbors of v
     * @param v node
     * @return array
     */
    public int[] neighbors(int v) {
        int cnt = 0;
        DoubleLL<Node<String>> curr;
        for (curr = vertex[v].next; curr != null; curr = curr.next) {
            cnt++;
        }
        int[] temp = new int[cnt];
        cnt = 0;
        for (curr = vertex[v].next; curr != null; curr = curr.next) {
            temp[cnt++] = curr.vertex;
        }
        return temp;
    }
    
    /**
     * Merge two subtrees if they are different
     * @param a key for node a
     * @param b key for node b
     */
    public void UNION(int a, int b) {
      int root1 = FIND(a);     // Find root of node a
      int root2 = FIND(b);     // Find root of node b
      if (root1 != root2) {          // Merge two trees
        vertex[root1] = root2;
      }
    }

    /**
     *  Return the root of curr's tree
     *  @param curr tree
     *  @return int
     */
    public int FIND(int curr) {
      while (array[curr] != -1) {
        curr = array[curr];
      }
      return curr; // Now at root
    }
}