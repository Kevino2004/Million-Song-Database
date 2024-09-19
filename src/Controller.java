/**
 * Controller class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class Controller {
    //~ Fields ................................................................
    private Hash artistHash;
    private Hash songHash;
    private Graph fullGraph;

    //~ Constructors ..........................................................
    /**
     * Create a new Controller object.
     * @param tableSize size given in input for Hash table size.
     */
    public Controller(int tableSize) {
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
    public void insert(String artist, String song) {
        // 1. Search for the artist in the artist hash table
        Node<String> artistNode = artistHash.find(artist); // Find the artist
        if (artistNode == null) {
            // Artist not found, create new node and insert into artistHash
            artistNode = new Node<>(artist);  // Create a new Node for the art
            artistHash.insert(artist, artistNode);  // Insert into artistHash
            int artistIndex = artistHash.getIndex(artist);  // Get index 
            fullGraph.addNode(artistIndex, artist);  // Add artist node 
        }

        // 2. Search for the song in the song hash table
        Node<String> songNode = songHash.find(song); // Find the song node
        if (songNode == null) {
            // Song not found, create new node and insert into songHash
            songNode = new Node<>(song);  // Create a new Node for the song
            songHash.insert(song, songNode);  // Insert into songHash
            int songIndex = songHash.getIndex(song);  // Get index from hash
            fullGraph.addNode(songIndex, song);  // Add song node to graph
        }

        // 3. Add an edge between artist and song in the graph
        int artistIndex = artistHash.getIndex(artist);  // Get the index 
        int songIndex = songHash.getIndex(song);  // Get the index for the song
        if (!fullGraph.hasEdge(artistIndex, songIndex)) {
            fullGraph.addEdge(artistIndex, songIndex, 1);  
        } 
        else {
            System.out.println("Duplicate: Artist '");
        }
    }

    // ----------------------------------------------------------
    /**
     * Removes the artist or song from the hash and graph.
     * @param type artist|song
     * @param name name of artist or song
     */
    public void remove(String type, String name) {
        if (type.equalsIgnoreCase("artist")) {
            int artistIndex = artistHash.getIndex(name);  
            if (artistIndex != -1) {
                artistHash.remove(name);  // Remove from artist hash
                removeArtistFromGraph(artistIndex);  
                System.out.println("Artist '" + name + "' removed.");
            } 
            else {
                System.out.println("Artist '" + name + "' not found.");
            }
        } 
        else if (type.equalsIgnoreCase("song")) {
            int songIndex = songHash.getIndex(name); 
            if (songIndex != -1) {
                songHash.remove(name);  // Remove from song hash
                removeSongFromGraph(songIndex);  // Remove all edges for song
                System.out.println("Song '" + name + "' removed.");
            } 
            else {
                System.out.println("Song '" + name + "' not found.");
            }
        } 
        else {
            System.out.println("Invalid type specified.");
        }
    }

    // Helper methods to remove all edges related to a node
    private void removeArtistFromGraph(int artistIndex) {
        int[] neighbors = fullGraph.neighbors(artistIndex);
        for (int neighbor : neighbors) {
            fullGraph.removeEdge(artistIndex, neighbor);
        }
    }

    private void removeSongFromGraph(int songIndex) {
        int[] neighbors = fullGraph.neighbors(songIndex);
        for (int neighbor : neighbors) {
            fullGraph.removeEdge(songIndex, neighbor);
        }
    }

    // ----------------------------------------------------------
    /**
     * Prints hash table or graph.
     * @param type either artist, song, or graph
     */
    public void print(String type) {
        switch (type.toLowerCase()) {
            case "artist":
                System.out.println("Artists:");
                artistHash.print();
                break;
            case "song":
                System.out.println("Songs:");
                songHash.print();
                break;
            case "graph":
                System.out.println("Graph:");
                fullGraph.printGraph();
                break;
            default:
                System.out.println("Invalid type.");
        }
    }
}