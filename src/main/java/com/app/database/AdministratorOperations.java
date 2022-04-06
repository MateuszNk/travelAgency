package com.app.database;

import com.app.GUI.Errors;
import com.app.database.Database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AdministratorOperations {

    private static final int ERROR = 1;
    private final Database database;
    private final Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String operation;
    public AdministratorOperations(String operation) {
        database = new Database();
        connection = database.getInDatabase();
        if ( connection == null ) {
            return;
        }
        statement = database.createStatement(connection);
        if ( statement == null ) {
            try {
                connection.close();
            } catch ( Exception e ) {
                new Errors("Cannot close connection!");
            }
        }

        this.operation = operation;
        chosenOperation();
    }

    public void chosenOperation() {
        switch ( operation ) {
            case "DISPLAY" -> displayAllRecordsInDatabase();
            case "ADD" -> System.out.println("ADD");
            case "DELETE" -> System.out.println("DELETE");
        }
    }

    public void displayAllRecordsInDatabase() {
        String sql = "SELECT ID, LOGIN, EMAIL from users";
        resultSet = database.createCommandInDatabase(statement, sql);
        if ( resultSet == null ) {
            try {
                statement.close();
                connection.close();
            } catch ( Exception e ) {
                new Errors("Cannot close statement and/or connection!");
            }
            return;
        }

        try {
            while ( resultSet.next() ) {
                int id = resultSet.getInt("ID");
                String login = resultSet.getString("LOGIN");
                String email = resultSet.getString("EMAIL");
                //System.out.println("ID: " + id + " LOGIN: " + login + " EMAIL: " + email);
            }
            closeAllConnections();
        } catch ( Exception e ) {
            new Errors("Cannot get data from database!");
        }
    }

    public void closeAllConnections() {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch ( Exception e ) {
            new Errors("Cannot close resultSet and/or statement, connection!");
        }
    }



        public String loginToDelete() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Write login to delete: ");
            return scanner.nextLine();
        }

        public void isLoginInDatabase(String login) {
            String sql = "SELECT LOGIN from users";
            ResultSet resultSet = database.createCommandInDatabase(statement, sql);

            boolean loginIsInDatabase = false;
            try {
                while ( resultSet.next() ) {
                    String databaseLogin = resultSet.getString("LOGIN");
                    if ( databaseLogin.equals(login) ) {
                        System.out.println("User found");
                        loginIsInDatabase = true;
                        break;
                    }
                }
                closeAllConnections();
            } catch ( Exception e ) {
                e.printStackTrace();
            }

            if ( !loginIsInDatabase ) {
                System.out.println("Login not found");
                System.exit(ERROR);
            }
        }

        public void deleteUserByLogin(String login) {
            String sql = "DELETE FROM `users` WHERE `users`.`LOGIN` = ?";

            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, login);
                preparedStatement.executeUpdate();
            } catch ( Exception e ) {
                e.printStackTrace();
            } finally {
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }