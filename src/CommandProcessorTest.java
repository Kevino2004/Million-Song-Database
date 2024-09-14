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
public class CommandProcessorTest extends TestCase {

    // ----------------------------------------------------------
    /**
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }
    
    /**
     * setup method
     */
    public void setUp()
    {
     // Setting up parameters
        String[] args = new String[2];
        args[0] = "10"; // Initial hash size
        args[1] = "P1_sampleInput.txt"; // Command input file

        // Create controller and process commands
        Controller controller = new Controller(10);
        File inputFile = new File(args[1]);
    }

    //~ Public Methods ........................................................

    /**
     * Example 1: Testing valid commands
     * This method runs a command sample IO file for valid commands
     *
     * @throws Exception
     */
    public void testValidCommands() throws Exception {
        // Setting up parameters
        String[] args = new String[2];
        args[0] = "10"; // Initial hash size
        args[1] = "P1_sampleInput.txt"; // Command input file

        // Create controller and process commands
        Controller controller = new Controller(10);
        File inputFile = new File(args[1]);
        CommandProcessor.process(inputFile, controller);

        // Expected output based on valid commands
        String expectedOutput = readFile("P1_sampleInput.txt");

        // Actual output from the system console
        String actualOutput = systemOut().getHistory();

        // Compare the two outputs
        assertFuzzyEquals(expectedOutput, actualOutput);
    }
}
