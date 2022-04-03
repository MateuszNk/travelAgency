package com.app.main;

import com.app.GUI.WelcomePanel;
import com.app.userOptions.Menu;

public class Main {

    public static void main(String[] args) {
        WelcomePanel welcomePanel = new WelcomePanel();
        Menu menu = new Menu();
        menu.menu();
    }
}
