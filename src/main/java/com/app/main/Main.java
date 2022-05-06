package com.app.main;

import com.app.GUI.ConfigureConnectionToDataBasePanel;
import com.app.GUI.WelcomePanel;
import com.app.configuration.CreateConfigurationFile;
import com.app.errors.ErrorType;
import com.app.errors.Errors;

public class Main {

    public static void main(String[] args) {
        CreateConfigurationFile ccf = new CreateConfigurationFile();
        if ( !ccf.fileExists ) {
            new Errors(ErrorType.CONFIGURATION_FILE_IS_MISSING, null);
            new ConfigureConnectionToDataBasePanel(null, true);
        }
        new WelcomePanel();
    }
}
