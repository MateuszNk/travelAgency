package com.app.database;

import com.app.errors.ErrorType;
import com.app.errors.Errors;

import java.sql.*;

public class Database {
    private static final int ERROR = 1;
    private static final String loginToDatabase = "root";
    private static final String passwordToDatabase = "";
    private static final String urlToDatabase = "jdbc:mysql://localhost:3306/users";

    public Connection getInDatabase() {
        Connection connection = null;
        try  {
            connection = DriverManager.getConnection(
                    urlToDatabase, loginToDatabase, passwordToDatabase);

            if ( connection == null ) { throw new Exception(); }
        } catch ( Exception e) {
            new Errors(ErrorType.NO_CONNECTION_TO_DATABASE);
        }
        return connection;
    }

    public Statement createStatement(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch ( Exception e ) {
            new Errors(ErrorType.NO_CONNECTION_TO_DATABASE);
        }
        return statement;
    }

    public ResultSet createCommandInDatabase(Statement statement, String sql) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch ( Exception e ) {
            new Errors(ErrorType.NO_CONNECTION_TO_DATABASE);
        }
        return resultSet;
    }
}