package ie.atu.sw;

import static java.lang.System.out;

//lecture 9.5
import java.util.Scanner;

//labs for new switch statement/vg cipher
public class Menu {
		private String key;
		
	    private String inputFile;
	    private String outputFilePath;

	private Scanner s;
	private Cypher cypher;
	private boolean keepRunning = true;

	public Menu() {
		s = new Scanner(System.in);
		cypher = new Cypher(key);
	}

	public void start() throws Exception { // taking from student manager app
		while (keepRunning) {
			show();// Display the menu options

			int choice = Integer.parseInt(s.next());// Read user's choice

			// Use a switch statement to perform actions based on the choice
			switch (choice) {
            case 1 -> specifyInputFileDirectory();
            case 2 -> specifyOutputFileDirectory();
            case 3 -> setKey();
            case 4 -> encrypt();
            case 5 -> decrypt();
            case 6 -> {keepRunning = false;
            out.println("[INFO] Exiting... Bye!");}
            default -> {
                System.out.println("[Error] Invalid Selection");
                // Add an option to go back to the main menu
                System.out.println("Press 'B' to go back to the main menu or 'Q' to quit:");
                String response = s.next();
                if (response.equalsIgnoreCase("B")) {
                    continue; // Go back to the main menu
                } else if (response.equalsIgnoreCase("Q")) {
                    keepRunning = false; // Quit the program
                }
            }
        }

        
    }
	}

	public String specifyInputFileDirectory()  {
        System.out.print("Enter the path for the input directory: ");
        s.nextLine(); // Clear the newline character from the buffer
        inputFile = s.nextLine(); // Read user's input for input directory

        FileParser fileParser = new FileParser();
        fileParser.parse(inputFile);
        return inputFile;
    }

	public void specifyOutputFileDirectory() {
        System.out.print("Enter the path for the output directory: ");
        s.nextLine();
        outputFilePath = s.nextLine();
        
        Outputter outputter = new Outputter();
        try {
            // Perform actions with the outputter instance
            System.out.println("Output written to file successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

	  public void setKey() {
	        System.out.print("Enter the key: ");
	        s.nextLine();
	        key = s.nextLine();
	        cypher = new Cypher(key); // Initialize the Cypher object with the key
	        System.out.println("Key set successfully.");
	  }

	public void encrypt() throws Exception {
	    if (key == null || inputFile == null || outputFilePath == null) {
	        System.out.println("Please provide encryption key, input file, and output file path.");
	        return;
	    }

	    String contentToEncrypt = FileParser.readFile(inputFile);
	    String encryptedText = cypher.encrypt(contentToEncrypt, key);

        try {
            Outputter.writeToFile(outputFilePath, encryptedText);
            System.out.println("Encryption is successful. Encrypted data is saved to " + outputFilePath);
        } catch (Exception e) {
            System.out.println("An error occurred during encryption: " + e.getMessage());
        }
    }

	public void decrypt() throws Exception {
	    if (key == null || inputFile == null || outputFilePath == null) {
	        System.out.println("Please provide decryption key, input file, and output file path.");
	        return;
	    }

	    String encryptedContent = FileParser.readFile(inputFile);
	    String decryptedText = cypher.decrypt(encryptedContent, key);

        try {
            Outputter.writeToFile(outputFilePath, decryptedText);
            System.out.println("Decryption is successful. Decrypted data is saved to " + outputFilePath);
        } catch (Exception e) {
            System.out.println("An error occurred during decryption: " + e.getMessage());
        }
    }





	public void show() 
	{

		System.out.println(ConsoleColour.CYAN);
		System.out.println("************************************************************");
		System.out.println("*       ATU - Dept. Computer Science & Applied Physics     *");
		System.out.println("*                                                          *");
		System.out.println("*                   ADFGVX File Encryption                 *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify Input File Directory");
		System.out.println("(2) Specify Output File Directory");
		System.out.println("(3) Set Key");
		System.out.println("(4) Encrypt");
		System.out.println("(5) Decrypt");
		// Add as many menu items as you like.
		System.out.println("(6) Quit");

		// Output a menu of options and solicit text from the user
		System.out.print(ConsoleColour.CYAN_BOLD);
		System.out.print("Select Option [1-6]>");
		// System.out.println();

		// Scanner s = new Scanner(System.in);
		// String choice = s.next();

		// System.out.println(choice); // get the option and give it switch statement

	}
}
