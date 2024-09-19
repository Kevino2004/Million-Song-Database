import student.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Command Processor Test class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class CommandProcessorTest extends TestCase 
{
    private Controller controller;
    /**
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException 
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }
    
    //~ Public Methods ........................................................
    /**
     * Set up method
     */
    public void setUp()
    {
        controller = new Controller(10);
    }
    
    /**
     * Tests process method with a valid command.
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    @SuppressWarnings("resource")
    public void testProcessValid() throws IOException, FileNotFoundException 
    {
        // Create a temporary file with a valid command
        File tempFile = File.createTempFile("testProcess", ".txt");
        PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
        writer.println("insert Ma Rainey<SEP>Mississippi Boweavil Blues");
        writer.close();

        // Call process method on the file
        CommandProcessor.process(tempFile, controller);

        // Verify that the controller handled the valid commands
        assertTrue(CommandProcessor.assertCompletion());
        
        // Clean up the temporary file
        tempFile.delete();
    }
    
    /**
     * Tests process method.
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    @SuppressWarnings("resource")
    public void testProcessInvalid() throws IOException, FileNotFoundException 
    {
        // Create a temporary file with a invalid command
        File tempFile = File.createTempFile("testProcess", ".txt");
        PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
        writer.println("");
        writer.close();

        // Call process method on the file
        CommandProcessor.process(tempFile, controller);

        // Verify that the controller handled the valid commands
        assertTrue(CommandProcessor.assertCompletion());
        
        // Clean up the temporary file
        tempFile.delete();
    }
    
    
    /**
     * Tests insert case.
     */
    public void testInsert()
    {
        String insert = "insert Ma Rainey<SEP>Mississippi Boweavil Blues ";
        CommandProcessor.call(insert, controller);
        assertTrue(CommandProcessor.assertCompletion());
    }
    
    /**
     * Tests remove case.
     */
    public void testRemove()
    {
        String remove = "remove song When Summer's Through ";
        CommandProcessor.call(remove, controller);
        assertTrue(CommandProcessor.assertCompletion());
    }
    
    /**
     * Tests print case.
     */
    public void testPrint()
    {
        String print = "print graph ";
        CommandProcessor.call(print, controller);
        assertTrue(CommandProcessor.assertCompletion());
    }
}
