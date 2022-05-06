package com.app.database;

import com.app.files.CreateConfigurationFile;
import com.app.errors.ErrorType;
import com.app.errors.Errors;

import java.sql.*;

public class Database {

    private String loginToDatabase = null;
    private String passwordToDatabase = null;
    private String urlToDatabase = null;
    public Database() {
        CreateConfigurationFile ccf = new CreateConfigurationFile();
        if ( !ccf.fileExists ) {
            new Errors(ErrorType.CONFIGURATION_FILE_IS_MISSING, null);
            return;
        }
        urlToDatabase = ccf.getUrlToDatabase();
        loginToDatabase = ccf.getLoginToDatabase();
        passwordToDatabase = ccf.getPasswordToDatabase();
    }

    public Connection createConnection() {
        Connection connection = null;
        try  {
            connection = DriverManager.getConnection(
                    urlToDatabase, loginToDatabase, passwordToDatabase);

            if ( connection == null ) { throw new Exception(); }
        } catch ( Exception e) {
            new Errors(ErrorType.NO_CONNECTION_TO_DATABASE, null);
        }
        return connection;
    }

    public Statement createStatement(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch ( Exception e ) {
            new Errors(ErrorType.NO_CONNECTION_TO_DATABASE, null);
        }
        return statement;
    }

    public ResultSet createCommandInDatabase(Statement statement, String sql) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch ( Exception e ) {
            new Errors(ErrorType.NO_CONNECTION_TO_DATABASE, null);
        }
        return resultSet;
    }

    public String getUrlToDatabase() { return urlToDatabase; }
}