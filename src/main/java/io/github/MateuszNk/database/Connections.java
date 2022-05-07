package io.github.MateuszNk.database;

import io.github.MateuszNk.errors.ErrorType;
import io.github.MateuszNk.errors.Errors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connections {

    private final Database database;
    public Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    public boolean isEverythingGood;
    public Connections() {
        database = new Database();
        if ( database.getUrlToDatabase() == null ) {
            isEverythingGood = false;
            return;
        }
        createConnection();
    }

    public void createConnection() {
        connection = database.createConnection();
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
                new Errors(ErrorType.ERROR_OF_ERROR, null);
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
                new Errors(ErrorType.ERROR_OF_ERROR, null);
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
            new Errors(ErrorType.CANNOT_CLOSE_ALL, null);
        }
        isEverythingGood = true;
    }

    public ResultSet getResultSet() { return resultSet; }
}