package ie.atu.sw;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Outputter {

	public static void writeToFile(String filePath, String content) throws IOException {
	    FileWriter fw = null;
	    try {
	        fw = new FileWriter(new File(filePath));
	        fw.write(content);
	    } finally {
	        if (fw != null) {
	            fw.close();
	        }
	    }
	}


    public static void main(String[] args) throws Exception {
        String outputPath = "./outii.txt";
        String processedText = "Your processed text here"; // Replace with your processed text

        try {
            Outputter.writeToFile(outputPath, processedText);
            System.out.println("Output written to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

