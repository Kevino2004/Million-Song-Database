// -------------------------------------------------------------------------

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * Main for Graph project (CS3114/CS5040 Fall 2023 Project 4).
 * Usage: java GraphProject <init-hash-size> <command-file>
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 *
 */
public class GraphProject {
    /**
     * @param args
     *            Command line parameters
     * @throws FileNotFoundException 
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File(args[0]);
        Scanner scanner = new Scanner(inputFile);
                
        // Read line by line from the file
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
                if (!command.isEmpty()) {
                    // Delegate the command to the Command Processor
                    CommandProcessor.process(command);
                }
            }
        scanner.close();
    }
}
