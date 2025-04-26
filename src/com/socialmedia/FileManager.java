package com.socialmedia;

import java.io.*;
import java.util.Scanner;

public class FileManager {
    private String baseDirectory;

    // Overloaded constructors
    public FileManager() {
        this.baseDirectory = "data";
    }

    public FileManager(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    // Overloaded methods for file operations
    public void saveToFile(String filename, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(baseDirectory + "/" + filename))) {
            writer.write(content);
        }
    }

    public void saveToFile(String filename, String... contents) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(baseDirectory + "/" + filename))) {
            for (String content : contents) {
                writer.write(content);
                writer.newLine();
            }
        }
    }

    // Method using Scanner for input
    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input: ");
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    // Exception handling examples
    public void handleFileOperation(String filename) {
        try {
            File file = new File(baseDirectory + "/" + filename);
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + filename);
            }
            // Process file
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    public static class FileManagerException extends Exception {
        public FileManagerException(String message) {
            super(message);
        }
    }

    // Nested class for file statistics
    public static class FileStats {
        private Long fileSize;
        private String lastModified;

        public FileStats(Long fileSize, String lastModified) {
            this.fileSize = fileSize;
            this.lastModified = lastModified;
        }

        public Long getFileSize() {
            return fileSize;
        }
    }
} 