package com.app.configuration;

import java.io.File;
import java.io.FileWriter;

public class CreateConfigurationFile {

    private final File configurationFile = new File("./configuration.txt");
    private String urlToDatabase;
    public String loginToDatabase;
    public String passwordToDatabase;
    public CreateConfigurationFile(String url, String login, String password) {
        urlToDatabase = url;
        loginToDatabase = login;
        passwordToDatabase = password;
        ifFileExists();
        if ( !fileExists ) { createFile(); }
    }

    public CreateConfigurationFile() {
        ifFileExists();
    }

    public boolean fileExists = false;
    public void ifFileExists() {
        if ( configurationFile.exists() ) {
            System.out.println("File exists");
            fileExists = true;
            return;
        }
        fileExists = false;
    }

    public void createFile() {
        fileExists = false;
        try {
            configurationFile.createNewFile();
            FileWriter myWriter = new FileWriter("configuration.txt");
            myWriter.write(urlToDatabase + "\n" + loginToDatabase + "\n" + passwordToDatabase);
            myWriter.close();
        } catch ( Exception e ) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CreateConfigurationFile("jdbc:mysql://localhost:3306/users",
            "root",
            "");
    }
}
