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
    }
    
    /**
     * Return the number of vertices
     * @return number of graph lists
     */
    public int listLength() { return vertex.length; }
    
    /**
     * Return the current number of nodes
     * @return number of nodes
     */
    public int nodeCount() { return numNodes; }
    
    /**
     * Get the value of node with index v
     * @param v array index
     * @param w list index
     * @return object of value
     */
    public Object getValue(int v, int w) { return vertex[v].get(w); }
    
    /**
     * Set the value of node with index v
     * @param val node
     */
    public void addNode(Node<String> val) 
    { 
        vertex[numNodes].add(val); 
        numNodes++;
    }
    
    /**
     * Remove the value of node with index v
     * @param val node
     */
    public void removeNode(Node<String> val) 
    { 
        vertex[numNodes].remove(val); 
        numNodes--;
    }

    /**
     * Adds a new edge
     * @param v node
     * @param w node
     */
    public void addEdge(int v, int w) {
        vertex[v].get(v).add(vertex[w].get(w);
    }

    

    /**
     * Removes the edge from the graph.
     * @param v node
     * @param w node
     */
    public void removeEdge(int v, int w) {
        //
    }

    /**
     * Returns true iff the graph has the edge
     * @param v node
     * @param w node
     * @return T/F
     */
    public boolean hasEdge(Node<String> v, Node<String> w) { 
        
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