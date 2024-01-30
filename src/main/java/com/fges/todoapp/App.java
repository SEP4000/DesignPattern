package com.fges.todoapp;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {

    private static final String JSON_EXTENSION = ".json";
    private static final String CSV_EXTENSION = ".csv";

    /**
     * Do not change this method
     */
    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }

    public static int exec(String[] args) throws IOException {
        Options cliOptions = new Options();
        CommandLineParser parser = new DefaultParser();

        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");

        CommandLine cmd;
        try {
            cmd = parser.parse(cliOptions, args);
        } catch (ParseException ex) {
            System.err.println("Fail to parse arguments: " + ex.getMessage());
            return 1;
        }

        String fileName = cmd.getOptionValue("s");

        List<String> positionalArgs = cmd.getArgList();
        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        String command = positionalArgs.get(0);

        Path filePath = Paths.get(fileName);

        String fileContent = Files.exists(filePath) ? Files.readString(filePath) : "";

        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);
        }

        if (command.equals("insert")) {
            handleInsertCommand(fileName, positionalArgs, fileContent);
        } else if ("list".equals(command)) {
            handleListCommand(fileName, fileContent);
        } else {
            System.err.println("Unknown command: " + command);
            return 1;
        }
        return 0;
    }

    private static void handleListCommand(String fileName, String fileContent) throws IOException {
        if (fileName.endsWith(JSON_EXTENSION)) {
            handleJsonList(fileContent);
        } else if (fileName.endsWith(CSV_EXTENSION)) {
            handleCsvList(fileContent);
        }
    }

    private static void handleJsonList(String fileContent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.forEach(node -> System.out.println("- " + node.toString()));
        }
    }

    private static void handleCsvList(String fileContent) {
        System.out.println(Arrays.stream(fileContent.split("\n"))
                .map(todo -> "- " + todo)
                .collect(Collectors.joining("\n"))
        );
    }

    private static void handleInsertCommand(String fileName, List<String> positionalArgs, String fileContent) throws IOException {
        if (positionalArgs.size() < 2) {
            System.err.println("Missing TODO name");
            System.exit(1);
        }

        String todo = positionalArgs.get(1);

        if (fileName.endsWith(JSON_EXTENSION)) {
            handleJsonInsert(fileName,fileContent, todo);
        } else if (fileName.endsWith(CSV_EXTENSION)) {
            handleCsvInsert(fileName,fileContent, todo);
        }
    }

    private static void handleJsonInsert(String fileName,String fileContent, String todo) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.add(todo);
        }

        Files.writeString(Paths.get(fileName), actualObj.toString());
    }

    private static void handleCsvInsert(String fileName,String fileContent, String todo) throws IOException {
        if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
            fileContent += "\n";
        }
        fileContent += todo;

        Files.writeString(Paths.get(fileName), fileContent);
    }

}
