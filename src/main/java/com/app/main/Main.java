package com.app.main;

import com.app.GUI.ConfigureConnectionToDataBasePanel;
import com.app.GUI.WelcomePanel;
import com.app.configuration.CreateConfigurationFile;

public class Main {

    public static void main(String[] args) {
        new WelcomePanel();
        CreateConfigurationFile ccf = new CreateConfigurationFile();
        if ( !ccf.fileExists ) {
            new ConfigureConnectionToDataBasePanel();
        }
    }
}
