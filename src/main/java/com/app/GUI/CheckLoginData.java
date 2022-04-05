package com.app.GUI;

import com.app.GUI.usersPanels.AdministratorPanel;
import com.app.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;

public class CheckLoginData {

    public CheckLoginData(String login, String password) {
        getDataFromDatabase(login, password);
    }

    public void getDataFromDatabase(String login, String password) {
        Database db = new Database();
        Connection connection = db.getInDatabase();
        if ( connection == null ) { return; }
        Statement statement = db.createStatement(connection);
        if ( statement == null ) { return; }
        String sql = "SELECT LOGIN, PASSWORD from users";
        ResultSet resultSet = db.createCommandInDatabase(statement, sql);
        if ( resultSet == null ) { return; }

        var data = new Hashtable<String, String>();
        try {
            while ( resultSet.next() ) {
                String databaseLogin = resultSet.getString("LOGIN");
                String databasePassword = resultSet.getString("PASSWORD");
                data.put(databaseLogin, databasePassword);
            }

            resultSet.close();
            statement.close();
            connection.close();
            isCorrectLoginData(login, password, data);
        } catch ( Exception e ) {
            new Errors("Cannot close connection");
        }
    }

    public void isCorrectLoginData(String login, String password, Hashtable<String, String> data) {
        for ( Map.Entry<String, String> entry : data.entrySet() ) {
            String entryKey = entry.getKey();
            String entryValue = entry.getValue();
            if ( entryKey.equals(login) && entryValue.equals(password) ) {
                if ( entryKey.equals("admin") ) {
                    new AdministratorPanel();
                } else {
                    // tutaj będzie przekierowanie do menu usera :D
                    System.out.println("Póki co nic się nie dzieje *sad pepe*");
                }

                return;
            }
        }

        new Errors("Wrong login and/or password");
    }
}
