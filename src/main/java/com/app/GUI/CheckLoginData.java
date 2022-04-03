package com.app.GUI;

import com.app.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;

public class CheckLoginData {

    public CheckLoginData(String login, String password) {
        getDataFromDatabase(login, password);
        /*if ( login.equals("admin") ) {
            Administrator administrator = new Administrator();
            administrator.menu();
        }*/
    }

    public void getDataFromDatabase(String login, String password) {
        Database db = new Database();
        Connection connection = db.getInDatabase();
        Statement statement = db.createStatement(connection);
        String sql = "SELECT LOGIN, PASSWORD from users";
        ResultSet resultSet = db.createCommandInDatabase(statement, sql);

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
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        isCorrectLoginData(login, password, data);
    }

    public void isCorrectLoginData(String login, String password, Hashtable<String, String> data) {
        boolean isGoodData = false;
        for ( Map.Entry<String, String> entry : data.entrySet() ) {
            String k = entry.getKey();
            String v = entry.getValue();
            if ( k.equals(login) && v.equals(password) ) {
                WrongLoginData wrongLoginData = new WrongLoginData("Welcome");
                wrongLoginData.successfulLogin();
                isGoodData = true;
                break;
            }
        }

        if ( !isGoodData ) {
            WrongLoginData wrongLoginData = new WrongLoginData("ERROR");
            wrongLoginData.wrongLoginData();
        }
    }
}
