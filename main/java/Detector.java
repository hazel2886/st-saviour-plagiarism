package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class Detector {
    public static void main(String[] args) {
        // Define your file paths
        File originalFile = new File("./resources/original.txt");
        File cheatFile = new File("./resources/cheat.txt");

        // Use your logic to check if files exist before processing
        if (originalFile.exists() && cheatFile.exists()) {
            System.out.println("Reading files...");
            System.out.println("Original: " + originalFile.getName() + " (" + originalFile.length() + " bytes)");
            System.out.println("Plagiarized: " + cheatFile.getName() + " (" + cheatFile.length() + " bytes)");
            
            runDetection(originalFile, cheatFile);
        } else {
            System.out.println("Error: THIS IS PLAGERISED CORNY CORNY.");
        }
    }

    public static void runDetection(File originalFile, File cheatFile) {
        try {
            // Milestone 1: Read original content (UTF-8)
            StringBuilder sb = new StringBuilder();
            Scanner originalScanner = new Scanner(originalFile, StandardCharsets.UTF_8.name());
            while (originalScanner.hasNextLine()) {
                sb.append(originalScanner.nextLine()).append(" ");
            }
            originalScanner.close();
            String fullText = sb.toString();

            // Milestone 2: Check patterns line-by-line
            Scanner cheatScanner = new Scanner(cheatFile, StandardCharsets.UTF_8.name());
            int lineNum = 1;
            boolean foundMatch = false;

            while (cheatScanner.hasNextLine()) {
                String pattern = cheatScanner.nextLine().trim();
                
                // Invoke Rabin-Karp (ensure the search method is in this class or RabinKarp.java)
                if (!pattern.isEmpty() && RabinKarp.search(pattern, fullText)) {
                    System.out.println("Plagiarism detected at Line " + lineNum + ": " + pattern);
                    foundMatch = true;
                }
                lineNum++;
            }
            cheatScanner.close();

            if (!foundMatch) System.out.println("No plagiarism detected.");

        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
        





