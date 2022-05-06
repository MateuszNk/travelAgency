package com.app.files;

import com.app.GUI.SuccessfulOperationPanel;
import com.app.database.Connections;
import com.app.errors.ErrorType;
import com.app.errors.Errors;

import java.io.FileWriter;
import java.sql.ResultSet;

public class CreateFileWithRecords {

    private final String pathToConfigurationFile = "./database.txt";
    public CreateFileWithRecords() {
        createFile();
    }

    public void createFile() {
        try {
            FileWriter myWriter = new FileWriter(pathToConfigurationFile);
            createResults(myWriter);
        } catch ( Exception e ) {
            new Errors(ErrorType.ERROR_OF_ERROR, null);
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
            new Errors(ErrorType.CANNOT_GET_DATA_FROM_DATABASE, null);
        }

        new SuccessfulOperationPanel("File created", null);
    }
}