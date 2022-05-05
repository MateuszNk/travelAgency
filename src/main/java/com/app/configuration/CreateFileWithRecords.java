package com.app.configuration;

import com.app.GUI.SuccessfullyRegisteredPanel;
import com.app.database.Connections;
import com.app.errors.ErrorType;
import com.app.errors.Errors;

import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CreateFileWithRecords {

    private final String pathToConfigurationFile = "./database.txt";
    private final File configurationFile = new File(pathToConfigurationFile);
    public CreateFileWithRecords() {
        createFile();
    }

    public void createFile() {
        try {
            configurationFile.createNewFile();
            FileWriter myWriter = new FileWriter(pathToConfigurationFile);
            //myWriter.write(id + "   " + login + "   " + email);
            /*myWriter.append(String.valueOf(id)).append("   ").append(login).append("   ").append(email);
            myWriter.close();*/
            createResults(myWriter);
        } catch ( Exception e ) {
            new Errors(ErrorType.ERROR_OF_ERROR);
        }
    }

    public void createResults(FileWriter fileWriter) {
        String sql = "SELECT ID, LOGIN, EMAIL from users";
        Connections connections = new Connections();
        connections.createResultSet(sql);
        ResultSet resultSet = connections.getResultSet();
        try {
            while ( resultSet.next() ) {
                int id = resultSet.getInt("ID");
                String login = resultSet.getString("LOGIN");
                String email = resultSet.getString("EMAIL");
                String strId = String.valueOf(id);
                String allRecords = strId + "    " + login + "    " + email + "\n";
                fileWriter.append(allRecords);
            }
            fileWriter.close();
        } catch ( Exception e ) {
            connections.closeAllConnections();
            new Errors(ErrorType.CANNOT_GET_DATA_FROM_DATABASE);
        }

        new SuccessfullyRegisteredPanel("File created");
    }

    public static void main(String[] args) {
        new CreateFileWithRecords();
    }
}
