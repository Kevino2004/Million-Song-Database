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
    //~ Constructors ..........................................................
    /**
     * Create a new Controller object.
     * @param tableSize size given in input for Hash table size.
     */
    public Controller(int tableSize)
    {
        this.artistHash = new Hash(tableSize);
        this.songHash = new Hash(tableSize);
        this.fullGraph = new Graph(tableSize);
    }
    //~Public  Methods ........................................................
    /**
     * Find artist in hash. If not found, make new node and insert in 
     * artist hash.
     * Do the same for song.
     * @param artist is the artist 
     * @param song is the song
     */
    public void insert(String artist, String song) 
    {
        // 1. Search for the artist in the artist hash table
        Node<String> artistNode = artistHash.find(artist);
        if (artistNode == null) 
        {
            // Artist not found, create new node
            artistNode = new Node<>(artist);
            artistHash.insert(artist, artistNode); // Insert into artist hash
            fullGraph.addNode(artistNode); // Add the artist node to the graph
        }

        // 2. Search for the song in the song hash table
        Node<String> songNode = songHash.find(song);
        if (songNode == null) 
        {
            // Song not found, create new node
            songNode = new Node<>(song);
            songHash.insert(song, songNode); // Insert into song hash
            fullGraph.addNode(songNode); // Add the song node to the graph
        }

        // 3. Add an edge between the artist node and song node in the graph
        if (!fullGraph.hasEdge(artistNode, songNode)) 
        {
            fullGraph.addEdge(artistNode, songNode); // Create edge in the graph
        } 
        else 
        {
            System.out.println("Duplicate record: Artist '" + artist + 
                "' is already associated with Song '" + song + "'.");
        }
    }
    
    // ----------------------------------------------------------
    /**
     * deletes song from hash
     * @param type artist|song
     * @param name name of artist or song
     */
    public void remove(String type, String name)
    {
        Node<String> nodeToRemove = null;
        if (type.equalsIgnoreCase("artist")) 
        {
            nodeToRemove = artistHash.find(name);
            if (nodeToRemove != null) 
            {
                artistHash.remove(name); // Remove artist from hash table
            }
        } 
        else if (type.equalsIgnoreCase("song")) 
        {
            nodeToRemove = songHash.find(name);
            if (nodeToRemove != null) 
            {
                songHash.remove(name); // Remove song from hash table
            }
        } 
        else 
        {
            System.out.println("Invalid type specified. Use 'artist' "
                + "or 'song'.");
            return;
        }

        // Step 3: If the node was found and removed from the hash table, 
        // remove it from the graph
        if (nodeToRemove != null) 
        {
            fullGraph.removeNode(nodeToRemove); // Remove the node from graph
            System.out.println(type + " '" + name + "' "
                + "was successfully removed.");
        } 
        else 
        {
            System.out.println(type + " '" + name + "' was not found.");
        }
    }
    
    /**
     * Print song, artist, or graph from hash table or graph.
     * @param type either artist, song, or graph
     */
    public void print(String type) 
    {
        switch (type.toLowerCase()) 
        {
            case "artist":
                System.out.println("Printing all artists:");
                artistHash.print();
                break;
            case "song":
                System.out.println("Printing all songs:");
                songHash.print();
                break;
            case "graph":
                System.out.println("Printing the graph:");
                fullGraph.printGraph(); // Prints the graph
                break;
            default:
                System.out.println("Invalid type. Use 'artist', 'song',"
                    + " or 'graph'.");
                break;
        }
    }
}
