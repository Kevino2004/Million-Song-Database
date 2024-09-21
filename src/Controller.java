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
    private Graph graph;
    //~ Constructors ..........................................................
    /**
     * Create a new Controller object.
     * @param tableSize size given in input for Hash table size.
     */
    public Controller(int tableSize)
    {
        this.artistHash = new Hash(tableSize);
        this.songHash = new Hash(tableSize);
        this.graph = new Graph(tableSize);
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
            String typeArtist = new String("Artist");
            // Artist not found, create new node
            artistNode = new Node<>(artist);
            // Insert into artist hash
            artistHash.insert(artist, artistNode, typeArtist); 
            graph.addNode(artistNode); // Add the artist node to the graph
            System.out.println("|" + artist + "|" + " is added to the Artist "
                + "database.");
        }

        // 2. Search for the song in the song hash table
        Node<String> songNode = songHash.find(song);
        if (songNode == null) 
        {
            String typeSong = new String("Song");
            // Song not found, create new node
            songNode = new Node<>(song);
            // Insert into song hash
            songHash.insert(song, songNode, typeSong); 
            graph.addNode(songNode); // Add the song node to the graph
            System.out.println("|" + song + "|" + " is added to the Song "
                + "database.");
        }

        // 3. Add an edge between the artist node and song node in the graph
        if (!graph.hasEdge(artistNode, songNode) || 
            !graph.hasEdge(songNode, artistNode)) 
        {
            graph.addEdge(artistNode, songNode);
            graph.addEdge(songNode, artistNode);
        } 
        else 
        {
            System.out.println("|" + artist + "<SEP>" + song + "| duplicates a "
                + "record already in the database.");
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
            artistHash.remove(name); // Remove artist from hash table
        } 
        else
        {
            nodeToRemove = songHash.find(name);
            songHash.remove(name); // Remove song from hash table
        } 

        // Step 3: If the node was found and removed from the hash table, 
        // remove it from the graph
        if (nodeToRemove != null) 
        {
            graph.removeEdge(nodeToRemove);
            graph.removeNode(nodeToRemove); // Remove the node from graph
            System.out.println("|" + name + "| is removed from the " + 
                type.substring(0, 1).toUpperCase() + type.substring(1) 
                + " database.");
        } 
        else 
        {
            System.out.println("|" + name + "| does not exist in the " + 
                type.substring(0, 1).toUpperCase() + type.substring(1)  
                + " database.");
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
                System.out.println("total artists: " + artistHash.print());
                break;
            case "song":
                System.out.println("total songs: " + songHash.print());
                break;
            case "graph":
                graph.printGraph();
                break;
            default:
                System.out.println("Invalid type. Use 'artist', 'song',"
                    + " or 'graph'.");
                break;
        }
    }
}