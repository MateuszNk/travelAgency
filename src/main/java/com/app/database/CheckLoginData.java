package com.app.database;

import com.app.GUI.Errors;
import com.app.GUI.usersPanels.AdministratorPanel;

import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Map;

public class CheckLoginData {

    public Connections connections;
    public ResultSet resultSet;
    public CheckLoginData(String login, String password) {
        connections = new Connections();
        resultSet = connections.getResultSet();
        getDataFromDatabase(login, password);
    }

    public void getDataFromDatabase(String login, String password) {
        String sql = "SELECT LOGIN, PASSWORD from users";
        connections.createResultSet(sql);

        var data = new Hashtable<String, String>();
        try {
            while ( resultSet.next() ) {
                String databaseLogin = resultSet.getString("LOGIN");
                String databasePassword = resultSet.getString("PASSWORD");
                data.put(databaseLogin, databasePassword);
            }
        } catch ( Exception e ) {
            new Errors("Cannot get data from database!");
        }

        connections.closeAllConnections();
        isCorrectLoginData(login, password, data);
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
