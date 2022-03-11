package com.app.database;

import java.sql.*;

public class Database {
    private static final int ERROR = 1;
    private static final String loginToDatabase = "root";
    private static final String passwordToDatabase = "";
    private static final String urlToDatabse = "jdbc:mysql://localhost:3306/users";

    public Connection getInDatabase() {
        Connection connection = null;
        try  {
            connection = DriverManager
                    .getConnection(urlToDatabse, loginToDatabase, passwordToDatabase);

            if ( connection == null ) {
                System.out.println("No connection to db");
                System.exit(ERROR);
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }

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
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
