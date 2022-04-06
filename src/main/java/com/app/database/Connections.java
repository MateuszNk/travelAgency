package com.app.database;

import com.app.errors.ErrorType;
import com.app.errors.Errors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connections {

    public Database database;
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;
    public Connections() {
        database = new Database();
        connection = database.getInDatabase();
        if ( connection == null ) {
            new Errors(ErrorType.CANNOT_CREATE_CONNECTION);
        }

        statement = database.createStatement(connection);
        if ( statement == null ) {
            try {
                connection.close();
            } catch ( Exception e ) {
                new Errors(ErrorType.CANNOT_CLOSE_CONNECTION);
            }
        }
    }

    public void createResultSet(String sql) {
        resultSet = database.createCommandInDatabase(statement, sql);
        if ( resultSet == null ) {
            try {
                statement.close();
                connection.close();
            } catch ( Exception e ) {
                new Errors(ErrorType.CANNOT_CLOSE_STATEMENT_ANDOR_CONNECTION);
            }
        }
    }

    public void closeAllConnections() {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch ( Exception e ) {
            new Errors(ErrorType.CANNOT_CLOSE_ALL);
        }
    }

    public ResultSet getResultSet() {
        return resultSet;
    }
}
