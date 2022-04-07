package com.app.database;

import com.app.GUI.usersPanels.UserPanel;
import com.app.errors.ErrorType;
import com.app.errors.Errors;
import com.app.GUI.usersPanels.AdministratorPanel;

import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Map;

public class CheckLoginData {

    public Connections connections;
    public ResultSet resultSet;
    public CheckLoginData(String login, String password) {
        connections = new Connections();
        if ( connections.isEverythingGood ) {
            resultSet = connections.getResultSet();
        }
        if ( connections.isEverythingGood ) {
            getDataFromDatabase(login, password);
        }
    }

    public void getDataFromDatabase(String login, String password) {
        String sql = "SELECT LOGIN, PASSWORD from users";
        connections.createResultSet(sql);
        resultSet = connections.getResultSet();
        if ( !connections.isEverythingGood ) { return; }

        var data = new Hashtable<String, String>();
        try {
            while ( resultSet.next() ) {
                String databaseLogin = resultSet.getString("LOGIN");
                String databasePassword = resultSet.getString("PASSWORD");
                data.put(databaseLogin, databasePassword);
            }
            connections.closeAllConnections();
            isCorrectLoginData(login, password, data);
        } catch ( Exception e ) {
            connections.closeAllConnections();
            new Errors(ErrorType.CANNOT_GET_DATA_FROM_DATABASE);
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
                    new UserPanel(login);
                }
                return;
            }
        }

        new Errors(ErrorType.WRONG_LOGIN_ANDOR_PASSWORD);
    }
}
