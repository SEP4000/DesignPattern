package com.fges.todoapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InsertCommand implements Command {
    private final boolean isDone;

    public InsertCommand(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public void execute(String fileName, String fileContent, List<String> positionalArgs) {
        if (positionalArgs.size() < 2) {
            System.err.println("Missing TODO name");
            return;
        }

        String todo = positionalArgs.get(1);

        if (fileName.endsWith(App.JSON_EXTENSION)) {
            handleJsonInsert(fileName, fileContent, todo);
        } else if (fileName.endsWith(App.CSV_EXTENSION)) {
            handleCsvInsert(fileName, fileContent, todo);
        } else if (positionalArgs.contains("-nf")) {
            handleNewFileSourceInsert(fileName, fileContent, todo);
        }
    }

    private void handleJsonInsert(String fileName, String fileContent, String todo) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode todosArray;

        try {
            if (fileContent.isEmpty()) {
                todosArray = mapper.createArrayNode();
            } else {
                todosArray = (ArrayNode) mapper.readTree(fileContent);
            }

            ObjectNode newTodo = mapper.createObjectNode();
            newTodo.put("done", isDone);
            newTodo.put("task", todo); // Ajouter la tâche
            todosArray.add(newTodo);

            Files.writeString(Paths.get(fileName), todosArray.toString());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private void handleCsvInsert(String fileName, String fileContent, String todo) {
        try {
            if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
                fileContent += "\n";
            }
            fileContent += todo + "," + isDone + "\n";

            Files.writeString(Paths.get(fileName), fileContent);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private void handleNewFileSourceInsert(String fileName, String fileContent, String todo) {
        System.out.println("Inserting todo from new file source: " + todo);
    }
}
