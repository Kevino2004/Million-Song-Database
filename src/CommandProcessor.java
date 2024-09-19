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
    private static boolean check = false;
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
        while (scanner.hasNextLine()) 
        {
            String command = scanner.nextLine().trim();
            if (!command.isEmpty()) 
            {
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
        String action = commandParts[0].toLowerCase(); 
        String argument = commandParts[1];
        
        switch (action) 
        {
            case "insert":
                String[] parts1 = argument.split("<SEP>");
                String artist = parts1[0].trim();
                String song = parts1[1].trim();
                
                check = true;
                controller.insert(artist, song);
                break;
            case "remove":
                String[] parts2 = argument.split(" ", 2);
                
                String type1 = parts2[0].trim();  // either 'artist' or 'song'
                String name = parts2[1].trim();  // name of the artist or song
                
                check = true;
                controller.remove(type1, name);
                break;
            case "print":
                String type2 = argument.trim();
                
                check = true;
                controller.print(type2);
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
    /**
     * Assert Completion method
     * @return T/F based on 
     */
    public static boolean assertCompletion()
    {
        return check;
    }
}
