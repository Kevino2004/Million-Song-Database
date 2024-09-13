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
        args[1] = "testData/validCommands.txt"; // Command input file

        // Create controller and process commands
        Controller controller = new Controller(10);
        File inputFile = new File(args[1]);
        CommandProcessor.process(inputFile, controller);

        // Expected output based on valid commands
        String expectedOutput = readFile("testData/expectedValidOutput.txt");

        // Actual output from the system console
        String actualOutput = systemOut().getHistory();

        // Compare the two outputs
        assertFuzzyEquals(expectedOutput, actualOutput);
    }

    /**
     * Example 2: Testing invalid commands
     * This method runs a command sample IO file for invalid commands
     *
     * @throws Exception
     */
    public void testInvalidCommands() throws Exception {
        // Setting up parameters
        String[] args = new String[2];
        args[0] = "10"; // Initial hash size
        args[1] = "testData/invalidCommands.txt"; // Command input file

        // Create controller and process commands
        Controller controller = new Controller(10);
        File inputFile = new File(args[1]);
        CommandProcessor.process(inputFile, controller);

        // Expected output based on invalid commands
        String expectedOutput = readFile("testData/expectedInvalidOutput.txt");

        // Actual output from the system console
        String actualOutput = systemOut().getHistory();

        // Compare the two outputs
        assertFuzzyEquals(expectedOutput, actualOutput);
    }

    /**
     * Example 3: Testing exception handling
     * This method runs a command sample IO file with missing file to 
     * test exception handling
     *
     * @throws Exception
     */
    public void testFileNotFound() throws Exception {
        // Setting up parameters
        String[] args = new String[2];
        args[0] = "10"; // Initial hash size
        args[1] = "testData/nonExistentFile.txt";

        // Capture the system output
        String expectedOutput = "Error: File not found";
        String actualOutput = "";

        try {
            // Invoke main method of our Graph Project
            GraphProject.main(args);
            actualOutput = systemOut().getHistory();
        } 
        catch (Exception e) {
            // Capture the exception and set actual output
            actualOutput = systemOut().getHistory();
        }

        // Compare the two outputs
        assertEquals(expectedOutput, actualOutput.trim());
    }

    /**
     * Testing the command processor for edge cases
     * For example, empty command lines, commands with missing parts, etc.
     *
     * @throws Exception
     */
    public void testEdgeCases() throws Exception {
        // Setting up parameters
        String[] args = new String[2];
        args[0] = "10"; // Initial hash size
        args[1] = "testData/edgeCases.txt"; // Command input file

        // Create controller and process commands
        Controller controller = new Controller(10);
        File inputFile = new File(args[1]);
        CommandProcessor.process(inputFile, controller);

        // Expected output based on edge cases
        String expectedOutput = 
            readFile("testData/expectedEdgeCasesOutput.txt");

        // Actual output from the system console
        String actualOutput = systemOut().getHistory();

        // Compare the two outputs
        assertFuzzyEquals(expectedOutput, actualOutput);
    }
}
