package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MigrateCommand implements Command {
    @Override
    public void execute(String fileName, String fileContent, List<String> positionalArgs) {
        if (positionalArgs.size() < 3) {
            System.err.println("Missing source or output file name");
            return;
        }

        String sourceFile = positionalArgs.get(1);
        String outputFile = positionalArgs.get(2);

        String sourceContent;
        try {
            sourceContent = Files.readString(Paths.get(sourceFile));

            if (sourceFile.endsWith(App.JSON_EXTENSION)) {
                migrateJson(sourceContent, outputFile);
            } else if (sourceFile.endsWith(App.CSV_EXTENSION)) {
                migrateCsv(sourceContent, outputFile);
            } else {
                System.err.println("Unsupported file format for migration");
            }
        } catch (IOException e) {
            System.err.println("Error reading source file: " + e.getMessage());
        }
    }

    private void migrateJson(String sourceContent, String outputFile) {
        try {
            // Logic to migrate JSON data
            Files.writeString(Paths.get(outputFile), sourceContent);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private void migrateCsv(String sourceContent, String outputFile) {
        try {
            // Logic to migrate CSV data
            Files.writeString(Paths.get(outputFile), sourceContent);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
