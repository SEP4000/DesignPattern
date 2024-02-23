package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {

    public static final String CSV_EXTENSION = ".csv";
    public static final String JSON_EXTENSION = ".json";
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
        cliOptions.addOption("d", "done", false, "Flag to mark the todo as done"); // Add the --done option

        CommandLine cmd;
        try {
            cmd = parser.parse(cliOptions, args);
        } catch (ParseException ex) {
            System.err.println("Fail to parse arguments: " + ex.getMessage());
            return 1;
        }

        String fileName = cmd.getOptionValue("s");
        boolean isDone = cmd.hasOption("d"); // Check if --done flag is provided

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

        // Utilisation d'une map pour associer chaque commande à son exécuteur
        Map<String, Command> commandMap = Map.of(
                "insert", new InsertCommand(isDone),
                "list", new ListCommand(),
                "migrate", new MigrateCommand()
        );

        // Utilisation de l'exécuteur de commandes
        CommandExecutor commandExecutor = new CommandExecutor(commandMap);
        commandExecutor.execute(command, fileName, fileContent, positionalArgs);

        System.err.println("Done.");
        return 0;
    }
}
