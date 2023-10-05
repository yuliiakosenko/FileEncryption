package ie.atu.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    public static String readFile(String filePath) throws Exception {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (Exception e) {
            throw new Exception("An error occurred while reading the file: " + e.getMessage());
        }
        return content.toString();
    }

    public List<String> parse(String inputFile) {
        List<String> parsedLines = new ArrayList<>();
        try {
            String fileContent = readFile(inputFile);
            String[] lines = fileContent.split("\\n");

            for (String line : lines) {
                parsedLines.add(line); // Store the line in the List
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return parsedLines;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java FileParser <inputFile>");
            return;
        }
        String inputFile = args[0];
        FileParser fileParser = new FileParser();
        List<String> parsedLines = fileParser.parse(inputFile);

        for (String line : parsedLines) {
            System.out.println("Parsed Line: " + line);
        }
    }
}


