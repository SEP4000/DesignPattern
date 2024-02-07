package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InsertCommand implements Command {
    @Override
    public void execute(String fileName, String fileContent, List<String> positionalArgs) throws IOException {
        if (positionalArgs.size() < 2) {
            System.err.println("Missing TODO name");
            System.exit(1);
        }

        String todo = positionalArgs.get(1);

        if (fileName.endsWith(App.JSON_EXTENSION)) {
            handleJsonInsert(fileName, fileContent, todo);
        } else if (fileName.endsWith(App.CSV_EXTENSION)) {
            handleCsvInsert(fileName, fileContent, todo);
        }
    }

    private void handleJsonInsert(String fileName, String fileContent, String todo) throws IOException
    {
        // Implémentation de la gestion des fichiers JSON pour l'insertion
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);
        if (actualObj instanceof MissingNode)
        {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.add(todo);
        }

        Files.writeString(Paths.get(fileName), actualObj.toString());
    }

    private void handleCsvInsert(String fileName, String fileContent, String todo) throws IOException
    {
        // Implémentation de la gestion des fichiers CSV pour l'insertion
        if (!fileContent.endsWith("\n") && !fileContent.isEmpty())
        {
            fileContent += "\n";
        }
        fileContent += todo;

        Files.writeString(Paths.get(fileName), fileContent);
    }
}
