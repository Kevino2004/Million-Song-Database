/**
 * This represents a node in a doubly linked list. This node stores data, a
 * pointer to the node before it in the list, and a pointer to the node
 * after it in the list
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 * @param <E> This is the type of object that this class will store
 */
public class Node<E> 
{
    private Node<E> next;
    private Node<E> previous;
    private E data;

    /**
     * Creates a new node with the given data
     *
     * @param d
     *            the data to put inside the node
     */
    public Node(E d) {
       data = d;
    }

    /**
     * Sets the node after this node
     *
     * @param n
     *            the node after this one
     */
    public void setNext(Node<E> n) {
        next = n;
    }

    /**
     * Sets the node before this one
     *
     * @param n
     *            the node before this one
     */
    public void setPrevious(Node<E> n) {
        previous = n;
    }

    /**
     * Gets the next node
     *
     * @return the next node
     */
    public Node<E> next() {
        return next;
    }

    /**
     * Gets the node before this one
     *
     * @return the node before this one
     */
    public Node<E> previous() {
        return previous;
    }

    /**
     * Gets the data in the node
     *
     * @return the data in the node
     */
    public E getData() {
        return data;
    }

    public DoubleLL<Node<String>> getAdjacencyList()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
