package com.app.userOptions;

import com.app.database.Database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Administrator {
    private static final int ERROR = 1;
    private static final int DISPLAY_ALL = 1;
    private static final int REGISTER = 2;
    private static final int DELETE = 3;

    public void menu() {
        System.out.println("Hello admin");
        System.out.println("# Admin Panel #");
        System.out.println("1. Display all database");
        System.out.println("2. Add new user to database");
        System.out.println("3. Delete user");
        System.out.print("Choose option: ");
        chosenOperation(chosenOption());
    }

    public int chosenOption() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        try {
            option = scanner.nextInt();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        System.out.println();
        return option;
    }

    public void chosenOperation(int option) {
        switch( option ) {
            case DISPLAY_ALL -> {
                displayAllDatabase();
                wantSavaToFile();
            }
            case REGISTER -> {
                Registration registration = new Registration();
                registration.registration();
            }
            case DELETE -> {
                String loginToDelete = loginToDelete();
                isLoginInDatabase(loginToDelete);
                deleteUserByLogin(loginToDelete);
            }
            default -> {
                System.out.println("Wrong input data");
                System.exit(ERROR);
            }
        }
    }

    public void displayAllDatabase() {
        Database db = new Database();
        Connection connection = db.getInDatabase();
        Statement statement = db.createStatement(connection);
        String sql = "SELECT ID, LOGIN, EMAIL from users";
        ResultSet resultSet = db.createCommandInDatabase(statement, sql);

        try {
            while ( resultSet.next() ) {
                // Wyświetlanie bazy bez password
                int id = resultSet.getInt("ID");
                String login = resultSet.getString("LOGIN");
                String email = resultSet.getString("EMAIL");
                System.out.println("ID: " + id + " LOGIN: " + login + " EMAIL: " + email);
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void wantSavaToFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would You like to save data to file? y/n");
        String option = scanner.nextLine();
        String lowerCaseOption = option.toLowerCase();
        char yesNo = lowerCaseOption.charAt(0);

        if ( option.length() != 1 || (yesNo != 'n' && yesNo != 'y') ) {
            System.out.println("Wrong input");
            System.exit(ERROR);
        }

        savaDataToFile();
    }

    public void savaDataToFile() {
        Database db = new Database();
        Connection connection = db.getInDatabase();
        Statement statement = db.createStatement(connection);
        String sql = "SELECT ID, LOGIN, EMAIL from users";
        ResultSet resultSet = db.createCommandInDatabase(statement, sql);

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("database.txt"));
            while ( resultSet.next() ) {
                int id = resultSet.getInt("ID");
                String login = resultSet.getString("LOGIN");
                String email = resultSet.getString("EMAIL");

                writer.append("ID: ").append(String.valueOf(id)).append(" LOGIN: ")
                        .append(login).append(" EMAIL: ").append(email).append("\n");
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String loginToDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write login to delete: ");
        return scanner.nextLine();
    }

    public void isLoginInDatabase(String login) {
        Database db = new Database();
        Connection connection = db.getInDatabase();
        Statement statement = db.createStatement(connection);
        String sql = "SELECT LOGIN from users";
        ResultSet resultSet = db.createCommandInDatabase(statement, sql);

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
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if ( !loginIsInDatabase ) {
            System.out.println("Login not found");
            System.exit(ERROR);
        }
    }

    public void deleteUserByLogin(String login) {
        Database db = new Database();
        Connection connection = db.getInDatabase();
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
