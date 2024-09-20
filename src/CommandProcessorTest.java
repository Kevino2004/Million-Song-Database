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
    
    /**
     * Tests the process method with valid commands.
     * @throws IOException when needed
     */
    public void testProcessValidCommands() throws IOException {
        File tempFile = File.createTempFile("testCommands", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("insert Ma Rainey<SEP>Mississippi Boweavil Blues\n");
            writer.write("remove song When Summer's Through\n");
            writer.write("print graph\n");
        }
        
        CommandProcessor.process(tempFile, controller);
        assertTrue(CommandProcessor.assertCompletion());
    }

    /**
     * Tests the process method with an empty line.
     * @throws IOException when needed
     */
    public void testProcessWithEmptyLine() throws IOException {
        File tempFile = File.createTempFile("testEmptyLine", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("insert Ma Rainey<SEP>Mississippi Boweavil Blues\n");
            writer.write("\n"); // empty line
            writer.write("print graph\n");
        }
        
        CommandProcessor.process(tempFile, controller);
        assertTrue(CommandProcessor.assertCompletion());
    }

    /**
     * Tests the process method with only whitespace.
     * @throws IOException when needed
     */
    public void testProcessWithWhitespace() throws IOException {
        File tempFile = File.createTempFile("testWhitespace", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("   \n"); // only whitespace
            writer.write("insert Ma Rainey<SEP>Mississippi Boweavil Blues\n");
        }
        
        CommandProcessor.process(tempFile, controller);
        assertTrue(CommandProcessor.assertCompletion());
    }
}
