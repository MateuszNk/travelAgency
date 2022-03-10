package com.app.database;

import java.sql.*;

public class Database {
    public void turnOnDatabase() {
        System.out.println("Uruchamianie Bazy");
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try  {
            con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/users", "root", "");

            if ( con == null ) {
                System.out.println("No connection to db");
            } else {
                System.out.println("Connected");
            }

            statement = con.createStatement();
            String sql = "SELECT ID, LOGIN, PASSWORD, EMAIL from users";
            rs = statement.executeQuery(sql);
            while ( rs.next() ) {
                int id = rs.getInt("ID");
                String login = rs.getString("LOGIN");
                String password = rs.getString("PASSWORD");
                String email = rs.getString("EMAIL");
                System.out.println("ID: " + id + " LOGIN: " + login + " PASSWORD: " + password + " EMAIL: " + email);
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
                con.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }

        }
    }
}
