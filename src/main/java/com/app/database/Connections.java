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
    public boolean isEverythingGood;
    public Connections() {
        database = new Database();
        createConnection();
    }

    public void createConnection() {
        connection = database.getInDatabase();
        if ( connection == null ) {
            return;
        }
        createStatement();
    }

    public void createStatement() {
        statement = database.createStatement(connection);
        if ( statement == null ) {
            try {
                connection.close();
                isEverythingGood = false;
                return;
            } catch ( Exception e ) {
                new Errors(ErrorType.ERROR_OF_ERROR);
            }
        }
        isEverythingGood = true;
    }

    public void createResultSet(String sql) {
        resultSet = database.createCommandInDatabase(statement, sql);
        if ( resultSet == null ) {
            try {
                isEverythingGood = false;
                statement.close();
                connection.close();
                return;
            } catch ( Exception e ) {
                new Errors(ErrorType.ERROR_OF_ERROR);
            }
        }
        isEverythingGood = true;
    }

    public void closeAllConnections() {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch ( Exception e ) {
            isEverythingGood = false;
            new Errors(ErrorType.CANNOT_CLOSE_ALL);
        }
        isEverythingGood = true;
    }

    public ResultSet getResultSet() { return resultSet; }
    public boolean getIsEverythingGood() { return isEverythingGood; }
}