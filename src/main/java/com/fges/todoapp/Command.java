package com.fges.todoapp;

import java.io.IOException;
import java.util.List;

public interface Command {
    void execute(String fileName, String fileContent, List<String> positionalArgs) throws IOException;
}

