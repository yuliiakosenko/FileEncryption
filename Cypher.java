package ie.atu.sw;

import java.util.List;
import java.util.Scanner;

public class Cypher {

    private char[][] square = {
            {' ', 'A', 'D', 'F', 'G', 'V', 'X'},
            {'A', 'P', 'H', '0', 'Q', 'G', '6'},
            {'D', '4', 'M', 'E', 'A', '1', 'Y'},
            {'F', 'L', '2', 'N', 'O', 'F', 'D'},
            {'G', 'X', 'K', 'R', '3', 'C', 'V'},
            {'V', 'S', '5', 'Z', 'W', '7', 'B'},
            {'X', 'J', '9', 'U', 'T', 'I', '8'}
    };

    public String key;


    public Cypher(String key) {
        this.key = key;
    }

 
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cypher cypher = new Cypher(null);
/*  Uncomment the following lines to text encryption and decryption
      System.out.print("Enter the text: ");
        String text = scanner.nextLine();
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        cypher.setKey(key);
        String encryptedText = cypher.encrypt(text, key);
        String decryptedText = cypher.decrypt(encryptedText, key);

        System.out.println("Original Text: " + text);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);*/

        scanner.close(); 
    }
																																								
    public String encrypt(String text, String key) {
        int numRows = (int) Math.ceil((double) text.length() / key.length());
        int numCols = key.length();

        char[][] matrix = new char[numRows][numCols];
        int textIndex = 0;

        // Fill the matrix with characters from the input text
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (textIndex < text.length()) {
                    matrix[i][j] = text.charAt(textIndex++);
                } else {
                    matrix[i][j] = ' ';
                }
            }
        }

        StringBuilder encryptedText = new StringBuilder();

        // Append characters from the matrix column by column
        for (char c : key.toCharArray()) {
            int colIndex = indexOfKey(c); // Get the index of the current key character
            for (int i = 0; i < numRows; i++) {
                encryptedText.append(matrix[i][colIndex]);
            }
        }

        return encryptedText.toString();
    }

    public String decrypt(String encryptedText, String key) {
        int[] order = quickSortKey(key);
        int numColumns = key.length();
        int numRows = (int) Math.ceil((double) encryptedText.length() / numColumns);

        char[][] matrix = new char[numRows][numColumns];
        int encryptedTextIndex = 0;

  
        int colIndex = 0;
        for (int index : order) {
            for (int i = 0; i < numRows; i++) {
                if (encryptedTextIndex < encryptedText.length()) {
                    matrix[i][colIndex] = encryptedText.charAt(encryptedTextIndex++);
                }
            }
            colIndex++;
        }

        StringBuilder decryptedText = new StringBuilder();

        // Read characters from the matrix column by column
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                decryptedText.append(matrix[i][j]);
            }
        }

        return decryptedText.toString();
    }

    private int[] quickSortKey(String key) {
        int n = key.length();
        int[] order = new int[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        quickSort(order, key, 0, n - 1);

        return order;
    }

    private int indexOfKey(char c) {
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    private int partition(int[] order, String key, int low, int high) {
        char pivot = key.charAt(order[high]);
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (key.charAt(order[j]) < pivot) {
                i++;
                int temp = order[i];
                order[i] = order[j];
                order[j] = temp;
            }
        }

        int temp = order[i + 1];
        order[i + 1] = order[high];
        order[high] = temp;

        return i + 1;
    }

    private void quickSort(int[] order, String key, int low, int high) {
        if (low < high) {
            int pi = partition(order, key, low, high);

            quickSort(order, key, low, pi - 1);
            quickSort(order, key, pi + 1, high);
        }
    }

    
	public void setKey(String key) {
	    this.key = key; // Store the key
	}	
	
	//using parsed text
	 public String encryptLines(List<String> lines) {
	        StringBuilder encryptedText = new StringBuilder();
	        for (String line : lines) {
	            encryptedText.append(encrypt(line, key)).append("\n");
	        }
	        return encryptedText.toString();
	    }

	    public String decryptLines(List<String> encryptedLines) {
	        StringBuilder decryptedText = new StringBuilder();
	        for (String encryptedLine : encryptedLines) {
	            decryptedText.append(decrypt(encryptedLine, key)).append("\n");
	        }
	        return decryptedText.toString();
	    }
	}



