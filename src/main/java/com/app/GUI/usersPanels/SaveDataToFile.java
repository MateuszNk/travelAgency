package com.app.GUI.usersPanels;

import com.app.GUI.Errors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class SaveDataToFile {

    public SaveDataToFile() {
        /*String sql = "SELECT ID, LOGIN, EMAIL from users";
        resultSet = database.createCommandInDatabase(statement, sql);
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
                closeAllConnections();
            } catch (Exception e) {
                new Errors("Cannot close writer!");
            }
        }*/
    }
}
