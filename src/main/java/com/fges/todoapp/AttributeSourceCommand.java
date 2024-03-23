package com.fges.todoapp;

import java.io.IOException;
import java.util.List;

public class AttributeSourceCommand implements Command {
    @Override
    public void execute(String fileName, String fileContent, List<String> positionalArgs) {
        if (positionalArgs.size() < 2) {
            System.err.println("Missing attribute name");
            return;
        }

        String attributeName = positionalArgs.get(1);

        // Implement logic to extract the specified attribute from the data source
        // This can be a file or a non-file data source
        // Example:
        String attributeValue = "Value of attribute " + attributeName;
        System.out.println("Attribute value: " + attributeValue);
    }
}
