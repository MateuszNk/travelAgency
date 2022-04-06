package com.app.GUI;

import java.awt.*;
import java.awt.event.ItemEvent;
import javax.swing.*;

public class WelcomePanel {

    private static final int SUCCESS = 0;
    public JFrame frame;
    public WelcomePanel() {
        CreateJFrame createJFrame = new CreateJFrame("Travel Agency App", 280, 300);
        frame = createJFrame.createJFrame();
        createComponents();
    }

    public JRadioButton darkThemeJRadioButton;
    public JRadioButton lightThemeJRadioButton;
    public JLabel welcomeJLabel;
    public JButton loginJButton;
    public JButton registerJButton;
    public JButton exitJButton;
    public static boolean isDarkTheme;
    public void createComponents() {
        welcomeJLabel = new JLabel("Welcome in Travel Agency App");
        loginJButton = new JButton("LOG IN");
        registerJButton = new JButton("REGISTER");
        exitJButton = new JButton("EXIT");
        darkThemeJRadioButton = new JRadioButton("Dark Theme");
        lightThemeJRadioButton = new JRadioButton("Light Theme");
        ButtonGroup groupOfThemes = new ButtonGroup();
        groupOfThemes.add(darkThemeJRadioButton);
        groupOfThemes.add(lightThemeJRadioButton);

        setParametersOfComponents();
        isDarkThemeOn();
        addActionsListeners();
        addComponents();
    }

    public void setParametersOfComponents() {
        darkThemeJRadioButton.setToolTipText("Select this option if you want dark theme");
        lightThemeJRadioButton.setToolTipText("Select this option if you want light theme");
        welcomeJLabel.setBounds(35, 25, 225, 25);
        loginJButton.setBounds(85, 65, 100, 25);
        registerJButton.setBounds(85, 110, 100, 25);
        exitJButton.setBounds(85, 155, 100, 25);
        darkThemeJRadioButton.setBounds(150, 190, 200, 25);
        lightThemeJRadioButton.setBounds(150, 210, 200, 25);
    }

    public void isDarkThemeOn() {
        if ( isDarkTheme ) {
            paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
            isDarkTheme = true;
        } else {
            paintAllComponents(Color.WHITE, Color.BLACK);
            isDarkTheme = false;
        }
        darkThemeJRadioButton.addItemListener(event -> {
            int state = event.getStateChange();
            if (state == ItemEvent.SELECTED && !isDarkTheme ) {
                isDarkTheme = true;
                paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
            } else if ( state == ItemEvent.DESELECTED && isDarkTheme ) {
                paintAllComponents(Color.WHITE, Color.BLACK);
                isDarkTheme = false;
            }
        });

        lightThemeJRadioButton.addItemListener(event -> {
            int state = event.getStateChange();
            if (state == ItemEvent.SELECTED && isDarkTheme ) {
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
        setTheme.setJRadioButton(darkThemeJRadioButton);
        setTheme.setJRadioButton(lightThemeJRadioButton);
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
        frame.add(darkThemeJRadioButton);
        frame.add(lightThemeJRadioButton);
    }

    public static boolean getIsDarkTheme() {
        return isDarkTheme;
    }

    public static void main(String[] args) {
        new WelcomePanel();
    }
}