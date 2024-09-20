import student.TestCase;

/**
 * Controller Test Class
 * 
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.20.2024
 */
public class ControllerTest extends TestCase {
    //~ Fields ................................................................
    private Controller controller;
    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    /**
     * sets up the controller with a specified table size
     */
    public void setUp() {
        controller = new Controller(5);
    }
    
    /**
     * Tests insert method with artist and song.
     */
    public void testInsert() 
    {
        controller.insert("Blind Lemon Jefferson", "Long Lonesome Blues");

        // Check if artist and song were added
        assertNotNull(controller.artistHash.find("Blind Lemon Jefferson"));
        assertNotNull(controller.songHash.find("Long Lonesome Blues"));

        // Check if the graph contains an edge between artist and song
        Node<String> artistNode = controller.artistHash.find("Blind Lemon Jefferson");
        Node<String> songNode = controller.songHash.find("Long Lonesome Blues");
        assertTrue(controller.graph.hasEdge(artistNode, songNode));
    }

    /**
     * Tests duplicate insert for an existing artist-song combination.
     */
    public void testInsertDuplicate() 
    {
        controller.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
        controller.insert("Blind Lemon Jefferson", "Long Lonesome Blues");

        // No duplicate edge should be created in the graph
        Node<String> artistNode = controller.artistHash.find("Blind Lemon Jefferson");
        Node<String> songNode = controller.songHash.find("Long Lonesome Blues");
        assertTrue(controller.graph.hasEdge(artistNode, songNode));
    }

    /**
     * Tests removal of an artist.
     */
    public void testRemoveArtist() 
    {
        controller.insert("Ma Rainey", "Ma Rainey's Black Bottom");
        controller.remove("artist", "Ma Rainey");

        // Check if artist was removed
        assertNull(controller.artistHash.find("Ma Rainey"));

        // Check if the node was also removed from the graph
        Node<String> artistNode = controller.artistHash.find("Ma Rainey");
        assertFalse(controller.graph.hasEdge(artistNode, controller.songHash.find("Ma Rainey's Black Bottom")));
    }

    /**
     * Tests removal of a song.
     */
    public void testRemoveSong() 
    {
        controller.insert("Ma Rainey", "Ma Rainey's Black Bottom");
        controller.remove("song", "Ma Rainey's Black Bottom");

        // Check if song was removed
        assertNull(controller.songHash.find("Ma Rainey's Black Bottom"));

        // Check if the node was also removed from the graph
        Node<String> songNode = controller.songHash.find("Ma Rainey's Black Bottom");
        assertFalse(controller.graph.hasEdge(controller.artistHash.find("Ma Rainey"), songNode));
    }

    /**
     * Tests removal with non-existing artist.
     */
    public void testRemoveNonExistingArtist() 
    {
        controller.remove("artist", "Non Existing Artist");

        // Ensure appropriate message and no effect on the graph
        assertNull(controller.artistHash.find("Non Existing Artist"));
    }

    /**
     * Tests removal with non-existing song.
     */
    public void testRemoveNonExistingSong() 
    {
        controller.remove("song", "Non Existing Song");

        // Ensure appropriate message and no effect on the graph
        assertNull(controller.songHash.find("Non Existing Song"));
    }

    /**
     * Tests invalid type for removal.
     */
    public void testRemoveInvalidType() 
    {
        controller.remove("invalid", "Some Name");

        // Since the type is invalid, nothing should be removed
        assertNull(controller.artistHash.find("Some Name"));
        assertNull(controller.songHash.find("Some Name"));
    }

    /**
     * Tests print for artist.
     */
    public void testPrintArtist() 
    {
        controller.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
        // Mock print statements in your test to capture the output or check the behavior
        controller.print("artist");

        assertNotNull(controller.artistHash.find("Blind Lemon Jefferson"));
    }

    /**
     * Tests print for song.
     */
    public void testPrintSong() 
    {
        controller.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
        controller.print("song");

        assertNotNull(controller.songHash.find("Long Lonesome Blues"));
    }

    /**
     * Tests invalid type for print.
     */
    public void testPrintInvalidType() 
    {
        controller.print("invalid");

        // Should handle invalid types without throwing exceptions
    }

    /**
     * Tests printing the graph.
     */
    public void testPrintGraph() 
    {
        controller.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
        controller.print("graph");

        // Check if connected components and largest component size are printed
        assertEquals(1, controller.graph.connectedComponents());
        assertTrue(controller.graph.largestComponentSize() > 0);
    }
}
