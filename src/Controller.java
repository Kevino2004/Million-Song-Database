/**
 * Controller class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class Controller
{
    //~ Fields ................................................................
    private Hash artist;
    private Hash song;
    private Graph fullGraph;
    //~ Constructors ..........................................................
    
    // ----------------------------------------------------------
    /**
     * Create a new Controller object.
     */
    public Controller()
    {
        this.artist = new Hash();
        this.song = new Hash();
        this.fullGraph = new Graph();
    }
    
    // ----------------------------------------------------------
    /**
     * Find artist in hash. If not found make new node insert in artist hash
     * Do the same for song
     * @param artist is the artist 
     * @param song is the song
     */
    //~Public  Methods ........................................................
    public void insert(String artist, String song)
    {
        
    }
    
    public void remove(String type, String name) {
        boolean removed = false;
        if (type.equals("artist")) {
            removed = artistTable.remove(name);
            if (removed) {
                graph.removeNode(name);
                System.out.println("Removed artist: " + name);
            }
        } else if (type.equals("song")) {
            removed = songTable.remove(name);
            if (removed) {
                graph.removeNode(name);
                System.out.println("Removed song: " + name);
            }
        }

        if (!removed) {
            System.out.println(name + " does not exist in the " + type + " database.");
        }
    }
    
    
    public void print(String type) {
        if (type.equals("song")) {
            songTable.printRecords();
        } else if (type.equals("artist")) {
            artistTable.printRecords();
        } else if (type.equals("graph")) {
            graph.printGraph();
            int connectedComponents = graph.countConnectedComponents();
            System.out.println("Connected components: " + connectedComponents);
            System.out.println("Size of the largest connected component: " + graph.getLargestComponentSize());
        }
}
