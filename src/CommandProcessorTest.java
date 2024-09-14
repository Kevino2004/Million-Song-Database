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
    private File file;
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
        file = new File("SolutionTestData/P1_commandProcessorTest.txt");
    }
    /**
     * process test
     * @throws IOException 
     */
    public void testProcess() throws IOException
    {
        CommandProcessor.process(file, controller);

        // Expected output based on valid commands
        String expectedOutput = readFile("P1_sampleInput.txt");

        // Actual output from the system console
        String actualOutput = systemOut().getHistory();

        // Compare the two outputs
        assertFuzzyEquals(expectedOutput, actualOutput);
    }
    /**
     * call test
     * @throws FileNotFoundException 
     */
    public void testCall() throws FileNotFoundException
    {
        CommandProcessor.process(file, controller);
        assertTrue(CommandProcessor.assertCompletion());
    }
}
