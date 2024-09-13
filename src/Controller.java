/**
 * Controller class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class Controller
{
    //~ Fields ................................................................
    private Hash artistHash;
    private Hash songHash;
    private Graph fullGraph;
    private int tableSize;
    //~ Constructors ..........................................................
    
    // ----------------------------------------------------------
    /**
     * Create a new Controller object.
     * @param tableSize size given in input for Hash table size.
     */
    public Controller(int tableSize)
    {
        this.artistHash = new Hash();
        this.songHash = new Hash();
        this.fullGraph = new Graph();
        this.tableSize = tableSize;
    }
    
    // ----------------------------------------------------------
    /**
     * Find artist in hash. If not found make new node insert in artist hash
     * Do the same for song
     * @param artist is the artist 
     * @param song is the song
     */
    //~Public  Methods ........................................................
    public void insert(String artist, String song) {
        // 1. Search for the artist in the artist hash table
        Node<String> artistNode = artistHash.find(artist);
        if (artistNode == null) {
            // Artist not found, create new node
            artistNode = new Node(artist);
            artistHash.insert(artist, artistNode); // Insert into artist hash
            fullGraph.newNode(artistNode); // Add the artist node to the graph
        }
        
        // 2. Search for the song in the song hash table
        Node<String> songNode = songHash.find(song);
        if (songNode == null) {
            // Song not found, create new node
            songNode = new Node(song);
            songHash.insert(song, songNode); // Insert into song hash
            fullGraph.newNode(songNode); // Add the song node to the graph
        }
        
        // 3. Add an edge between the artist node and song node in the graph
        if (!fullGraph.hasEdge(artistNode, songNode)) {
            fullGraph.addEdge(artistNode, songNode); // Create edge in the graph
        } else {
            System.out.println("Duplicate record: Artist '" + artist + "' is already associated with Song '" + song + "'.");
        }
    }
}
