/**
 * Graph class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class Graph
{
    private class Edge { // Doubly linked list node
        int vertex, weight;
        Edge prev, next;

        Edge(int v, int w, Edge p, Edge n) {
          vertex = v;
          weight = w;
          prev = p;
          next = n;
        }
      }
    private Edge[] nodeArray;
    private Object[] nodeValues;
    private int numEdge;

    /**
     * Constructor to initialize the graph with a given size.
     * 
     * @param init The initial size of the vertex list.
     */
    public Graph(int init) {
        init(init);
        
    }
    
    /**
     * Initialize
     * @param n size
     */
    public void init(int n) {
        nodeArray = new Edge[n];
        // List headers;
        for (int i=0; i<n; i++) { nodeArray[i] = new Edge(-1, -1, null, null); }
        nodeValues = new Object[n];
        numEdge = 0;
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
    private Edge find (int v, int w) {
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
    public void addEdge(int v, int w, int wgt) {
      if (wgt == 0) { return; } // Can't store weight of 0
      Edge curr = find(v, w);
      if ((curr.next != null) && (curr.next.vertex == w)) {
        curr.next.weight = wgt;
      }
      else {
        curr.next = new Edge(w, wgt, curr, curr.next);
        numEdge++;
        if (curr.next.next != null) { curr.next.next.prev = curr.next; }
      }
    }

    /**
     * Get the weight value for an edge
     * @param v node
     * @param w node
     * @return int
     */
    public int weight(int v, int w) {
      Edge curr = find(v, w);
      if ((curr.next == null) || (curr.next.vertex != w)) { return 0; }
    return curr.next.weight;
    }

    /**
     * Removes the edge from the graph.
     * @param v node
     * @param w node
     */
    public void removeEdge(int v, int w) {
      Edge curr = find(v, w);
      if ((curr.next == null) || curr.next.vertex != w) { return; }
    curr.next = curr.next.next;
    if (curr.next != null) { curr.next.prev = curr; }
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

   
}