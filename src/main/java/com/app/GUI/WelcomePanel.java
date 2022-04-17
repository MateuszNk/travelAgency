package com.app.GUI;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class WelcomePanel {

    private static final int SUCCESS = 0;
    public JFrame frame;
    public JMenuBar menuJMenuBar;
    public JMenu optionsJMenu;
    public WelcomePanel() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Travel Agency App", 270, 250);

        menuJMenuBar = new JMenuBar();
        frame.setJMenuBar(menuJMenuBar);
        optionsJMenu = new JMenu("Options");
        //optionsJMenu.setMnemonic(KeyEvent.VK_R);
        menuJMenuBar.add(optionsJMenu);

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
        //optionsJMenu.addSeparator();
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
    }

    public void addActionsListeners() {
        exitJButton.addActionListener(e -> {
            frame.dispose();
            System.exit(SUCCESS);
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

    public static void main(String[] args) {
        new WelcomePanel();
    }
}