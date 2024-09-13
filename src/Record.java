/**
 * Record class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class Record
{
    //~ Fields ................................................................
    private String key;
    private Node value; 
    //~ Constructors ..........................................................
    /**
     * Create a new Record object.
     * 
     * @param key The key for this record
     * @param value The value for this record (Node)
     */
    public Record(String key, Node value) {
        this.key = key;
        this.value = value;
    }
    //~Public  Methods ........................................................

    /**
     * Get the key for this record.
     * 
     * @return The key (artist or song)
     */
    public String getKey() {
        return key;
    }
    
    /**
     * Get the value for this record.
     * 
     * @return The value (Node)
     */
    public Node getValue() {
        return value;
    }
    
    /**
     * Set a new value for this record.
     * 
     * @param value The new value (Node) to set
     */
    public void setValue(Node value) {
        this.value = value;
    }
    
    /**
     * Return a string representation of the record.
     * 
     * @return A string in the form "Key: [key], Value: [value]"
     */
    @Override
    public String toString() {
        return "Key: " + key + ", Value: " + value;
    }
}
