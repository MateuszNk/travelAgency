package com.app.database;

import com.app.GUI.SuccessfullyRegisteredPanel;
import com.app.errors.ErrorType;
import com.app.errors.Errors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Map;

public class AddRecordToDatabase {

    public Connections connections;
    public ResultSet resultSet;
    public AddRecordToDatabase(String login, String password, String email) {
        connections = new Connections();
        if ( connections.isEverythingGood ) {
            resultSet = connections.getResultSet();
        }
        if ( connections.isEverythingGood ) {
            addUserToDatabase(login, password, email);
        }
    }

    public void addUserToDatabase(String login, String password, String email) {
        PreparedStatement preparedStatement = null;
        //connections.createStatement();
        try  {
            String insertQuery = "INSERT INTO `users` (`ID`, `LOGIN`, `PASSWORD`, `EMAIL`)" + "VALUES(?, ?, ?, ?)";
            preparedStatement = connections.connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, CheckRegistrationData.getId());
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.executeUpdate();
        } catch ( Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                //connections.closeAllConnections();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }

        new SuccessfullyRegisteredPanel();
    }
}
