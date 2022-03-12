package com.app.userOptions;

import com.app.database.Database;

import java.sql.*;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class Registration {
    private static final int ERROR = 1;

    public void registration(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Repeat password: ");
        String repeatedPassword = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        checkPassword(password, repeatedPassword);
        checkEmail(email);

        int lastUsedIdInDatabase = getDataFromDatabase(login, email);
        addUserToDatabase(lastUsedIdInDatabase, login, password, email);
    }

    public void checkPassword(String password, String repeatedPassword) {
        if ( !password.equals(repeatedPassword) ) {
            System.out.println("Given passwords are NOT equals");
            System.exit(ERROR);
        }
    }

    public void checkEmail(String email) {
        String isCorrectEmail = email.substring(email.lastIndexOf("@") + 1);
        if ( !email.contains("@")  || !isCorrectEmail.contains(".") ) {
            System.out.println("Email is invalid");
            System.exit(ERROR);
        }
    }

    public int getDataFromDatabase(String login, String email) {
        Database db = new Database();
        Connection connection = db.getInDatabase();
        Statement statement = db.createStatement(connection);
        String sql = "SELECT ID, LOGIN, EMAIL from users";
        ResultSet resultSet = db.createCommandInDatabase(statement, sql);

        var data = new Hashtable<String, String>();
        int id = 0;
        try {
            while ( resultSet.next() ) {
                id = resultSet.getInt("ID");
                String databaseLogin = resultSet.getString("LOGIN");
                String databaseEmail = resultSet.getString("EMAIL");
                data.put(databaseLogin, databaseEmail);
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
        isTakenLoginOrEmail(login, email, data);
        return id+1;
    }

    public void isTakenLoginOrEmail(String login, String email, Hashtable<String, String> data) {
        for ( Map.Entry<String, String> entry : data.entrySet() ) {
            String key = entry.getKey();
            String value = entry.getValue();
            if ( key.equals(login) ) {
                System.out.println("This login is already taken!");
                System.exit(ERROR);
            } else if ( value.equals(email) ) {
                System.out.println("This email is already taken!");
                System.exit(ERROR);
            }
        }

        System.out.println("Successfully registered");
    }

    public void addUserToDatabase(int id, String login, String password, String email) {
        Database db = new Database();
        Connection connection = db.getInDatabase();
        PreparedStatement preparedStatement = null;
        try  {
            String insertQuery = "INSERT INTO `users` (`ID`, `LOGIN`, `PASSWORD`, `EMAIL`)" + "VALUES(?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.executeUpdate();
        } catch ( Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }
}
