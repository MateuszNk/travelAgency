package com.app.configuration;

import com.app.errors.ErrorType;
import com.app.errors.Errors;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class CreateConfigurationFile {

    private final String pathToConfigurationFile = "./configuration.txt";
    private final File configurationFile = new File(pathToConfigurationFile);
    private String urlToDatabase;
    public String loginToDatabase;
    public String passwordToDatabase;
    public CreateConfigurationFile(String url, String login, String password) {
        urlToDatabase = url;
        loginToDatabase = login;
        passwordToDatabase = password;
        createFile();
    }

    public CreateConfigurationFile() {
        ifFileExists();
    }

    public boolean fileExists = false;
    public void ifFileExists() {
        if ( configurationFile.exists() ) {
            try {
                Scanner sc = new Scanner(configurationFile);
                if ( sc.hasNextLine() )
                    urlToDatabase = sc.nextLine();
                else
                    urlToDatabase = null;

                if ( sc.hasNextLine() )
                    loginToDatabase = sc.nextLine();
                else
                    loginToDatabase = null;

                if ( sc.hasNextLine() )
                    passwordToDatabase = sc.nextLine();
                else
                    passwordToDatabase = null;
            } catch ( Exception e ) {
                new Errors(ErrorType.ERROR_OF_ERROR);
            }

            fileExists = true;
            return;
        }
        fileExists = false;
    }

    public void createFile() {
        fileExists = false;
        try {
            configurationFile.createNewFile();
            FileWriter myWriter = new FileWriter(pathToConfigurationFile);
            myWriter.write(urlToDatabase + "\n" + loginToDatabase + "\n" + passwordToDatabase);
            myWriter.close();
        } catch ( Exception e ) {
            new Errors(ErrorType.ERROR_OF_ERROR);
        }
    }

    public String getUrlToDatabase() { return urlToDatabase; }
    public String getLoginToDatabase() { return loginToDatabase; }
    public String getPasswordToDatabase() { return passwordToDatabase; }

    public static void main(String[] args) {
        new CreateConfigurationFile("jdbc:mysql://localhost:3306/users",
            "root",
            "");
    }
}

