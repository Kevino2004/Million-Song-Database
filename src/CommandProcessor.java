/**
 * Command processor class
 *
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9.4.2024
 */
public class CommandProcessor
{
    //~ Fields ................................................................
    Controller controller;
    // ----------------------------------------------------------
    /**
     * Main for Command Processor.
     */
    //~ Constructors ..........................................................
    public CommandProcessor() {
        controller = new Controller();
    }
    //~Public  Methods ........................................................
    /**
     * Process method takes the command from main.
     * @param command
     */
    // Method to process a command string
    public static void process(String command) {
        // Split command into parts
        String[] commandParts = command.split(" ", 2);
        //"add", "remove", "print"
        String action = commandParts[0].toLowerCase(); 
        //song/artist name
        String argument = commandParts.length > 1 ? commandParts[1] : ""; 
        
        switch (action) {
            case "insert":
                insert(argument);
                break;
            case "remove":
                remove(argument);
                break;
            case "print":
                print(argument);
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
    
    // Method to handle the 'add' command
    private static void insert(String argument) {
        // You will need to implement logic for adding songs/artists
        //controller.insert();
        System.out.println("Add command received for: " + argument);
    }
    
    // Method to handle the 'remove' command
    private static void remove(String argument) {
        // You will need to implement logic for removing songs/artists
        controller.remove();
        System.out.println("Remove command received for: " + argument);
    }
    
    // Method to handle the 'print' command
    private static void print(String argument) {
        if (argument.equalsIgnoreCase("song")) {
            // Logic to print song-related data
            System.out.println("Print song data");
        } else if (argument.equalsIgnoreCase("artist")) {
            // Logic to print artist-related data
            System.out.println("Print artist data");
        } else {
            System.out.println("Unknown print command: " + argument);
        }
    }
}
