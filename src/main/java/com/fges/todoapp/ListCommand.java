package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListCommand implements Command {
    @Override
    public void execute(String fileName, String fileContent, List<String> positionalArgs) throws IOException {
        boolean showDone = positionalArgs.contains("-d"); // Check if --done flag is provided

        if (fileName.endsWith(App.JSON_EXTENSION)) {
            handleJsonList(fileContent, showDone);
        } else if (fileName.endsWith(App.CSV_EXTENSION)) {
            handleCsvList(fileContent);
        }
    }

    private void handleJsonList(String fileContent, boolean showDone) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode todosArray = (ArrayNode) mapper.readTree(fileContent);

        todosArray.forEach(todo -> {
            boolean done = todo.get("done").asBoolean();
            if (!showDone || done) { // Check if todo should be displayed based on showDone flag
                System.out.println("- " + (done ? " (Done)" : ""));
            }
        });
    }

    private void handleCsvList(String fileContent) {
        System.out.println(fileContent.trim());
    }
}
