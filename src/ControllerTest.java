import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;

/**
 * Controller Test Class
 * 
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.20.2024
 */
public class ControllerTest extends TestCase 
{
    //~ Fields ................................................................
    private Controller controller;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    /**
     * sets up the controller with a specified table size
     */
    public void setUp() 
    {
        controller = new Controller(5);
        System.setOut(new PrintStream(out));
    }
    
    /**
     * Tests insert method with artist and song.
     */
    public void testInsert() 
    {
        controller.insert("artist", "song");

        assertEquals("|artist| is added to the Artist database.\n" 
            + "|song| is added to the Song database.\n"
            , out.toString() );
    }

    /**
     * Tests duplicate insert for an existing artist-song combination.
     */
    public void testInsertDuplicate() 
    {
        controller.insert("artist", "song");

        assertEquals("|artist| is added to the Artist database.\n" 
            + "|song| is added to the Song database.\n"
            , out.toString() );
        
        controller.insert("artist", "song");
        
        assertEquals("|artist| is added to the Artist database.\n" 
            + "|song| is added to the Song database.\n"
            + "|artist<SEP>song| duplicates a record already in "
            + "the database.\n"
            , out.toString() );
    }

    /**
     * Tests removal of an artist.
     */
    public void testRemoveArtist() 
    {
        controller.insert("artist", "song");
        controller.remove("artist", "artist");
        
        assertEquals("|artist| is added to the Artist database.\n" 
            + "|song| is added to the Song database.\n"
            + "|artist| is removed from the Artist database.\n"
            , out.toString() );
    }

    /**
     * Tests removal of a song.
     */
    public void testRemoveSong() 
    {
        controller.insert("artist", "song");
        controller.remove("song", "song");
        
        assertEquals("|artist| is added to the Artist database.\n" 
            + "|song| is added to the Song database.\n"
            + "|song| is removed from the Song database.\n"
            , out.toString() );
    }

    /**
     * Tests removal with non-existing artist.
     */
    public void testRemoveNonExistingArtist() 
    {
        controller.remove("artist", "artist");

        assertEquals("|artist| does not exist in the Artist database.\n"
            , out.toString() );
    }

    /**
     * Tests removal with non-existing song.
     */
    public void testRemoveNonExistingSong() 
    {
        controller.remove("song", "song");

        assertEquals("|song| does not exist in the Song database.\n"
            , out.toString() );
    }

    /**
     * Tests print for artist.
     */
    public void testPrintArtist() 
    {
        controller.insert("artist", "song");
        controller.print("artist");

        assertEquals("|artist| is added to the Artist database.\n" 
            + "|song| is added to the Song database.\n"
            + "3: |artist|\r\n"
            + "total artists: 1\n"
            , out.toString() );
    }

    /**
     * Tests print for song.
     */
    public void testPrintSong() 
    {
        controller.insert("artist", "song");
        controller.print("song");

        assertEquals("|artist| is added to the Artist database.\n" 
            + "|song| is added to the Song database.\n"
            + "4: |song|\r\n"
            + "total songs: 1\n"
            , out.toString() );
    }

    /**
     * Tests invalid type for print.
     */
    public void testPrintInvalidType() 
    {
        controller.print("invalid");

        assertEquals("Invalid type. Use 'artist', 'song', or 'graph'.\n"
            , out.toString());
    }

    /**
     * Tests printing the graph.
     */
    public void testPrintGraph() 
    {
        controller.insert("artist", "song");
        controller.print("graph");
        
        assertEquals("|artist| is added to the Artist database.\n" 
            + "|song| is added to the Song database.\n"
            + "There are 1 connected components\r\n"
            + "The largest connected component has 2 elements\r\n"
            , out.toString() );
    }
}
