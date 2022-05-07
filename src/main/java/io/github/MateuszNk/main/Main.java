package io.github.MateuszNk.main;

import io.github.MateuszNk.GUI.ConfigureConnectionToDatabasePanel;
import io.github.MateuszNk.GUI.WelcomePanel;
import io.github.MateuszNk.files.CreateConfigurationFile;
import io.github.MateuszNk.errors.ErrorType;
import io.github.MateuszNk.errors.Errors;

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