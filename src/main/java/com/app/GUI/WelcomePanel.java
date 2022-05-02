package com.app.GUI;

import com.app.configuration.CreateConfigurationFile;

import java.awt.*;
import java.awt.event.ItemEvent;
import javax.swing.*;

public class WelcomePanel {

    public JFrame frame;
    public JMenuBar menuJMenuBar;
    public JMenu optionsJMenu;
    public JMenu configurationJMenu;
    public WelcomePanel() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Travel Agency App", 260, 255);
        createJFrame.addWindowListener();
        menuJMenuBar = new JMenuBar();
        frame.setJMenuBar(menuJMenuBar);

        optionsJMenu = new JMenu("Options");
        menuJMenuBar.add(optionsJMenu);
        configurationJMenu = new JMenu("Configuration");
        menuJMenuBar.add(configurationJMenu);

        createComponents();
    }

    public JLabel welcomeJLabel;
    public JButton loginJButton;
    public JButton registerJButton;
    public JButton exitJButton;
    public JRadioButtonMenuItem darkThemeJRadioButtonMenuItem;
    public JRadioButtonMenuItem lightThemeJRadioButtonMenuItem;
    public JMenu themeMenu;
    public static boolean isDarkTheme;
    public void createComponents() {
        welcomeJLabel = new JLabel("Welcome in Travel Agency App");
        loginJButton = new JButton("LOG IN");
        registerJButton = new JButton("REGISTER");
        exitJButton = new JButton("EXIT");

        ButtonGroup groupOfThemes = new ButtonGroup();
        themeMenu = new JMenu("Themes");

        lightThemeJRadioButtonMenuItem = new JRadioButtonMenuItem("Light Theme");
        lightThemeJRadioButtonMenuItem.setSelected(true);
        groupOfThemes.add(lightThemeJRadioButtonMenuItem);
        themeMenu.add(lightThemeJRadioButtonMenuItem);

        darkThemeJRadioButtonMenuItem = new JRadioButtonMenuItem("Dark Theme");
        groupOfThemes.add(darkThemeJRadioButtonMenuItem);
        themeMenu.add(darkThemeJRadioButtonMenuItem);

        optionsJMenu.add(themeMenu);

        setParametersOfComponents();
        isDarkThemeOn();
        addActionsListeners();
        addComponents();
        checkIfConfigurationFileExists();
    }

    public void setParametersOfComponents() {
        welcomeJLabel.setBounds(20, 20, 300, 20);
        loginJButton.setBounds(50, 50 , 150, 25);
        registerJButton.setBounds(50, 90 , 150, 25);
        exitJButton.setBounds(50, 130 , 150, 25);
    }

    public void isDarkThemeOn() {
        if ( isDarkTheme ) {
            paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
            isDarkTheme = true;
        } else {
            paintAllComponents(Color.WHITE, Color.BLACK);
            isDarkTheme = false;
        }

        darkThemeJRadioButtonMenuItem.addItemListener(event -> {
            int state = event.getStateChange();
            if (state == ItemEvent.SELECTED && !isDarkTheme ) {
                isDarkTheme = true;
                paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
            } else if ( state == ItemEvent.DESELECTED && isDarkTheme ) {
                paintAllComponents(Color.WHITE, Color.BLACK);
                isDarkTheme = false;
            }
        });

        lightThemeJRadioButtonMenuItem.addItemListener(event -> {
            int state = event.getStateChange();
            if ( state == ItemEvent.SELECTED && isDarkTheme ) {
                paintAllComponents(Color.WHITE, Color.BLACK);
                isDarkTheme = false;
            } else if ( state == ItemEvent.DESELECTED && !isDarkTheme ) {
                paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
                isDarkTheme = true;
            }
        });
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJButtonTheme(loginJButton);
        setTheme.setJButtonTheme(registerJButton);
        setTheme.setJButtonTheme(exitJButton);
        setTheme.setJLabelTheme(welcomeJLabel);
        setTheme.setJMenuBar(menuJMenuBar);
        setTheme.setJMenu(themeMenu);
        setTheme.setJMenu(optionsJMenu);
        setTheme.setJRadioButtonMeuItem(lightThemeJRadioButtonMenuItem);
        setTheme.setJRadioButtonMeuItem(darkThemeJRadioButtonMenuItem);
        setTheme.setJMenu(configurationJMenu);
    }

    public void addActionsListeners() {
        exitJButton.addActionListener(e -> {
            new DoYouWantToClosePanel();
        });

        loginJButton.addActionListener(e -> {
            frame.dispose();
            new LoginPanel();
        });
    }

    public void addComponents() {
        frame.add(loginJButton);
        frame.add(registerJButton);
        frame.add(welcomeJLabel);
        frame.add(exitJButton);
    }

    public static boolean getIsDarkTheme() {
        return isDarkTheme;
    }

    public void checkIfConfigurationFileExists() {
        CreateConfigurationFile ccf = new CreateConfigurationFile();
        if ( !ccf.fileExists ) {
            new ConfigureConnectionToDataBasePanel();
        }
    }

    public static void main(String[] args) {
        new WelcomePanel();
    }
}