package com.app.database;

import com.app.GUI.Errors;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AdministratorOperations {

    public Connections connections;
    private static final int ERROR = 1;
    private final String operation;
    public ResultSet resultSet;
    public AdministratorOperations(String operation) {
        connections = new Connections();
        resultSet = connections.getResultSet();
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
        connections.createResultSet(sql);
        try {
            while ( resultSet.next() ) {
                int id = resultSet.getInt("ID");
                String login = resultSet.getString("LOGIN");
                String email = resultSet.getString("EMAIL");
                System.out.println("ID: " + id + " LOGIN: " + login + " EMAIL: " + email);
            }
           connections.closeAllConnections();
        } catch ( Exception e ) {
            new Errors("Cannot get data from database!");
        }
    }

    public String loginToDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write login to delete: ");
        return scanner.nextLine();
    }

    public void isLoginInDatabase(String login) {
        String sql = "SELECT LOGIN from users";
        connections.createResultSet(sql);
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
            connections.closeAllConnections();
        } catch ( Exception e ) {
            new Errors("Cannot get data from Database");
        }
        if ( !loginIsInDatabase ) {
            System.out.println("Login not found");
            System.exit(ERROR);
        }
    }

    public void deleteUserByLogin(String login) {
        String sql = "DELETE FROM `users` WHERE `users`.`LOGIN` = ?";
        try {
            PreparedStatement preparedStatement = connections.connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
            try {
                preparedStatement.close();
                connections.connection.close();
            } catch (Exception e) {
                new Errors("Cannot close preparedStatement and/or connection!");
            }
        } catch ( Exception e ) {
            new Errors("preparedStatement doesn't work properly!");
        }
    }
}