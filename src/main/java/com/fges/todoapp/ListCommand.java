package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListCommand implements Command {
    @Override
    public void execute(String fileName, String fileContent, List<String> positionalArgs) throws IOException {
        if (fileName.endsWith(App.JSON_EXTENSION)) {
            handleJsonList(fileContent);
        } else if (fileName.endsWith(App.CSV_EXTENSION)) {
            handleCsvList(fileContent);
        }
    }

    private void handleJsonList(String fileContent) throws IOException
    {
        // Implémentation de la gestion des fichiers JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);
        if (actualObj instanceof MissingNode)
        {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode)
        {
            arrayNode.forEach(node -> System.out.println("- " + node.toString()));
        }
    }

    private void handleCsvList(String fileContent)
    {
        // Implémentation de la gestion des fichiers CSV
        System.out.println(Arrays.stream(fileContent.split("\n"))
                .map(todo -> "- " + todo)
                .collect(Collectors.joining("\n"))
        );
    }
}
