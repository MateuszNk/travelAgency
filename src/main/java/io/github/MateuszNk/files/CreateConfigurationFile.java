package io.github.MateuszNk.files;

import io.github.MateuszNk.errors.ErrorType;
import io.github.MateuszNk.errors.Errors;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class CreateConfigurationFile {

    private final String pathToConfigurationFile = "./configuration.txt";
    private final File configurationFile = new File(pathToConfigurationFile);
    private String urlToDatabase;
    private String loginToDatabase;
    private String passwordToDatabase;
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
                Scanner scanner = new Scanner(configurationFile);
                if ( scanner.hasNextLine() ) {
                    urlToDatabase = scanner.nextLine();
                } else {
                    urlToDatabase = null;
                }

                if ( scanner.hasNextLine() ) {
                    loginToDatabase = scanner.nextLine();
                } else {
                    loginToDatabase = null;
                }

                if ( scanner.hasNextLine() ) {
                    passwordToDatabase = scanner.nextLine();
                } else {
                    passwordToDatabase = null;
                }
            } catch ( Exception e ) {
                new Errors(ErrorType.ERROR_OF_ERROR, null);
            }

            fileExists = true;
            return;
        }
        fileExists = false;
    }

    public void createFile() {
        fileExists = false;
        try {
            FileWriter myWriter = new FileWriter(pathToConfigurationFile);
            myWriter.write(urlToDatabase + "\n" + loginToDatabase + "\n" + passwordToDatabase);
            myWriter.close();
        } catch ( Exception e ) {
            new Errors(ErrorType.ERROR_OF_ERROR, null);
        }
    }

    public String getUrlToDatabase() { return urlToDatabase; }
    public String getLoginToDatabase() { return loginToDatabase; }
    public String getPasswordToDatabase() { return passwordToDatabase; }
}

