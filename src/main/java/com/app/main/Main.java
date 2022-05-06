package com.app.main;

import com.app.GUI.ConfigureConnectionToDatabasePanel;
import com.app.GUI.WelcomePanel;
import com.app.files.CreateConfigurationFile;
import com.app.errors.ErrorType;
import com.app.errors.Errors;

public class Main {

    public static void main(String[] args) {
        CreateConfigurationFile ccf = new CreateConfigurationFile();
        if ( !ccf.fileExists ) {
            new Errors(ErrorType.CONFIGURATION_FILE_IS_MISSING, null);
            new ConfigureConnectionToDatabasePanel(null, true);
        }
        new WelcomePanel();
    }
}