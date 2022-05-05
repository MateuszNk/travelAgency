package com.app.database;

import com.app.GUI.SuccessfullyRegisteredPanel;

import java.sql.PreparedStatement;

public class AddRecordToDatabase {

    public Connections connections;
    public AddRecordToDatabase(String login, String password, String email) {
        connections = new Connections();
        if ( connections.isEverythingGood ) {
            addUserToDatabase(login, password, email);
        }
    }

    public void addUserToDatabase(String login, String password, String email) {
        PreparedStatement preparedStatement = null;
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
                connections.connection.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }

        new SuccessfullyRegisteredPanel("Successfully Registered");
    }
}
