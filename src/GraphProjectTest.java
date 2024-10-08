import student.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * This class was designed to test the GraphProject
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.13.2024
 */
public class GraphProjectTest extends TestCase 
{
    /**
     * Read contents of a file into a string
     * 
     * @param path File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException 
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * Set up the tests that follow.
     */
    public void setUp() 
    { 
        // Nothing needed
    }


    /**
     * This method is simply to get code coverage of the class declaration.
     */
    public void testQInit() {
        GraphProject it = new GraphProject();
        assertNotNull(it);
    }


    /**
     * This method runs on a command sample IO file
     * You will write similar test cases
     * using different text files
     *
     * @throws Exception
     */
    public void testSampleIO() throws Exception {
        // Setting up all the parameters
        String[] args = new String[2];
        args[0] = "10";
        args[1] = "solutionTestData/P1_sampleInput.txt";

        // Invoke main method of our Graph Project
        GraphProject.main(args);

        // Actual output from your System console
        String actualOutput = systemOut().getHistory();

        // Expected output from file
        String expectedOutput = readFile(
            "solutionTestData/P1_sampleOutput.txt");

        assertFuzzyEquals(expectedOutput, actualOutput);

    }
}
