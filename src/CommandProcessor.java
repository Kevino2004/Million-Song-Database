import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Command processor class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class CommandProcessor
{
    //~ Fields ................................................................
    /**
     * Main for Command Processor.
     */
    //~ Constructors ..........................................................
    public CommandProcessor() 
    {
        //Main not needed
    }
    //~Public  Methods ........................................................
    /**
     * Process method takes the command from main.
     * @param file input file
     * @param controller Graph Project controller
     * @throws FileNotFoundException 
     */
    // Method to process a command string
    public static void process(File file, Controller controller) 
        throws FileNotFoundException 
    {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(file);
        
        // Read line by line from the file
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
                if (!command.isEmpty()) {
                    CommandProcessor.call(command, controller);
                }
            }
        scanner.close();
    }
    /**
     * Call method.
     * @param command String for command
     * @param controller Graph Project controller
     */
    public static void call(String command, Controller controller)
    {
     // Split command into parts
        String[] commandParts = command.split(" ", 2);
        //"add", "remove", "print"
        String action = commandParts[0].toLowerCase(); 
        //song/artist name
        String argument = commandParts.length > 1 ? commandParts[1] : ""; 
        
        switch (action) {
            case "insert":
                controller.insert(argument);
                break;
            case "remove":
                controller.remove(argument);
                break;
            case "print":
                controller.print(argument);
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
}
