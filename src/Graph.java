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
     * @return number of nodes
     */
    public int nodeCount() { return nodeArray.length; }
    
    /**
     * Return the current number of edges
     * @return number of edges
     */
    public int edgeCount() { return numEdge; }
    
    /**
     * Get the value of node with index v
     * @param v index
     * @return object of value
     */
    public Object getValue(int v) { return nodeValues[v]; }
    
    /**
     * Set the value of node with index v
     * @param v index
     * @param val node
     */
    public void setValue(int v, Object val) { nodeValues[v] = val; }
    
    /**
     * Return the link in v's neighbor list that preceeds the
     * one with w (or where it would be)
     * 
     */
    private Edge find(int v, int w) {
        Edge curr = nodeArray[v];
        while ((curr.next != null) && (curr.next.vertex < w)) {
            curr = curr.next;
        }
        return curr;
    }

    /**
     * Adds a new edge from node v to node w with weight wgt
     * @param v node
     * @param w node
     * @param wgt weight
     */
    public void addEdge(int v, int w) {
        Edge curr = find(v, w);
        if ((curr.next != null) && (curr.next.vertex == w)) 
        {
            curr.next.weight = wgt;
        }
        else 
        {
            curr.next = new Edge(w, wgt, curr, curr.next);
            numEdge++;
            if (curr.next.next != null) 
            {
                curr.next.next.prev = curr.next; 
            }
        }
    }

    

    /**
     * Removes the edge from the graph.
     * @param v node
     * @param w node
     */
    public void removeEdge(int v, int w) {
        Edge curr = find(v, w);
        if ((curr.next == null) || curr.next.vertex != w) 
        {
            return; 
        }
        curr.next = curr.next.next;
        if (curr.next != null) { 
            curr.next.prev = curr;
        }
        numEdge--;
    }

    /**
     * Returns true iff the graph has the edge
     * @param v node
     * @param w node
     * @return T/F
     */
    public boolean hasEdge(int v, int w) { return weight(v, w) != 0; }

    /**
     * Returns an array containing the indicies of the neighbors of v
     * @param v node
     * @return array
     */
    public int[] neighbors(int v) {
        int cnt = 0;
        Edge curr;
        for (curr = nodeArray[v].next; curr != null; curr = curr.next) {
            cnt++;
        }
        int[] temp = new int[cnt];
        cnt = 0;
        for (curr = nodeArray[v].next; curr != null; curr = curr.next) {
            temp[cnt++] = curr.vertex;
        }
        return temp;
    }
    
    /**
     * Print the graph.
     */
    public void printGraph() {
        for (int i = 0; i < nodeArray.length; i++) {
            System.out.print("Node " + i + " (Value: " + getValue(i) + "): ");
            Edge curr = nodeArray[i].next;
            if (curr == null) {
                System.out.print("No edges.");
            } 
            else {
                while (curr != null) {
                    System.out.print(" -> " + curr.vertex + " (Weight: " + 
                        curr.weight + ")");
                    curr = curr.next;
                }
            }
            System.out.println();
        }
    }
   
    
    // ----------------------------------------------------------
    /**
     * adds the node
     * @param v is the spot
     * @param val is the value
     */
    public void addNode(int v, Object val) {
        if (v >= nodeArray.length) {
            throw new IndexOutOfBoundsException("Index exceeds graph size.");
        }
        setValue(v, val); // Store node value
    }
}