package com.app.userOptions;

import com.app.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class Login {
    private static final int ERROR = 1;

    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        getDataFromDatabase(login, password);
    }

    public void getDataFromDatabase(String login, String password) {
        Database db = new Database();
        Connection connection = db.getInDatabase();
        ResultSet resultSet = db.createCommandInDatabase(connection);
        var data = db.executeCommandInDatabase(resultSet);

        try {
            resultSet.close();
            // nie zamykam statement
            connection.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        isCorrectLoginData(login, password, data);
    }

    public void isCorrectLoginData(String login, String password, Hashtable<String, String> data) {
        boolean isGoodData = false;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (k.equals(login) && v.equals(password)) {
                System.out.println("Welcome " + login);
                isGoodData = true;
                break;
            }
        }

        if ( !isGoodData ) {
            System.out.println("Wrong login or/and password!");
            System.exit(ERROR);
        }
    }
}
