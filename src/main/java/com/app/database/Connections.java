package com.app.database;

import com.app.GUI.Errors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connections {

    public Database database;
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;
    public Connections(String sql) {
        database = new Database();
        connection = database.getInDatabase();
        if ( connection == null ) { return; }
        statement = database.createStatement(connection);
        if ( statement == null ) {
            try {
                connection.close();
            } catch ( Exception e ) {
                new Errors("Cannot close connection!");
            }
        }
        resultSet = database.createCommandInDatabase(statement, sql);
        if ( resultSet == null ) {
            try {
                statement.close();
                connection.close();
            } catch ( Exception e ) {
                new Errors("Cannot close statement and/or connection!");
            }
        }
    }
}
