package com.fges.todoapp;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CommandExecutor {
    private final Map<String, Command> commandMap;

    public CommandExecutor(Map<String, Command> commandMap)
    {
        this.commandMap = commandMap;
    }

    public int execute(String commandName, String fileName, String fileContent, List<String> positionalArgs) throws IOException
    {
        Command command = commandMap.get(commandName);
        if (command != null)
        {
            command.execute(fileName, fileContent, positionalArgs);
            return 0; // Succès
        } else
        {
            System.err.println("Commande inconnue : " + commandName);
            return 1; // Échec
        }
    }
}
