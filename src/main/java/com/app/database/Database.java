package com.app.database;

import java.sql.*;
import java.util.HashSet;
import java.util.Hashtable;

public class Database {
    private static final int ERROR = 1;
    private static final String loginToDatabase = "root";
    private static final String passwordToDatabase = "";

    public Connection getInDatabase() {
        Connection connection = null;
        try  {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/users", loginToDatabase, passwordToDatabase);

            if ( connection == null ) {
                System.out.println("No connection to db");
                System.exit(ERROR);
            } /*else {
                System.out.println("Connected");
                //createCommandInDatabase(connection);
            }*/
        } catch ( Exception e) {
            e.printStackTrace();
        } /*finally {
            try {
                connection.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }*/

        return connection;
    }

    public Statement createStatement(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return statement;
    }

    public ResultSet createCommandInDatabase(Statement statement, String sql) {
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(sql);
            //executeCommandInDatabase(resultSet);
        } catch ( Exception e ) {
            e.printStackTrace();
        } /*finally {
            try {
                resultSet.close();
                statement.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }*/

        return resultSet;
    }

/*    public Hashtable executeCommandInDatabase(ResultSet resultSet) {
        var data = new Hashtable<String, String>();
        try {
            while ( resultSet.next() ) {
                String login = resultSet.getString("LOGIN");
                String password = resultSet.getString("PASSWORD");
                data.put(login, password);
                //System.out.println("LOGIN: " + login + " PASSWORD: " + password);
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return data;
    }*/
}
