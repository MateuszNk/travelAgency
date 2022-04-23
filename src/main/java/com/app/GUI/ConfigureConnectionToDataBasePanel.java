package com.app.GUI;

import com.app.database.CheckLoginData;

import javax.swing.*;
import java.awt.*;

public class ConfigureConnectionToDataBasePanel {

    JFrame frame;
    public ConfigureConnectionToDataBasePanel() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Configure Connection", 400, 400);

        createComponents();
    }

    public JLabel urlJLabel;
    public JLabel passwordJLabel;
    public JLabel welcomeJLabel;
    public JLabel loginJLabel;
    public JTextField urlJTextField;
    public JTextField loginJTextField;
    public JPasswordField passwordJPasswordField;
    public JButton confirmJButton;
    public JButton backJButton;
    public void createComponents() {
        confirmJButton = new JButton("CONFIRM");
        backJButton = new JButton("BACK");
        loginJLabel = new JLabel("Login:");
        urlJLabel = new JLabel("URL:");
        passwordJLabel = new JLabel("Password:");
        loginJTextField = new JTextField(16);
        urlJTextField = new JTextField(128);
        welcomeJLabel = new JLabel("Please write data to database");
        passwordJPasswordField = new JPasswordField(32);

        setParametersOfComponents();

        if ( WelcomePanel.getIsDarkTheme() ) {
            paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
        } else {
            paintAllComponents(Color.WHITE, Color.BLACK);
        }

        addActionsListeners();
        addComponents();
    }

    public void setParametersOfComponents() {
        welcomeJLabel.setBounds(80, 1, 200, 70);

        urlJLabel.setBounds(25, 55, 50, 25);
        urlJTextField.setBounds(105, 55, 175, 25);

        loginJLabel.setBounds(25, 80, 50, 25);
        loginJTextField.setBounds (105, 80, 175, 25);

        passwordJLabel.setBounds (25, 105, 75, 25);
        passwordJPasswordField.setBounds (105, 105, 175, 25);

        confirmJButton.setBounds (35, 170, 100, 20);
        backJButton.setBounds (170, 170, 100, 20);
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(welcomeJLabel);
        setTheme.setJLabelTheme(urlJLabel);
        setTheme.setJLabelTheme(passwordJLabel);
        setTheme.setJTextField(loginJTextField);
        setTheme.setJPasswordField(passwordJPasswordField);
        setTheme.setJButtonTheme(confirmJButton);
        setTheme.setJButtonTheme(backJButton);
    }

    public void addActionsListeners() {
        backJButton.addActionListener(e -> {
            frame.dispose();
            new WelcomePanel();
        });

        confirmJButton.addActionListener(e -> {
            frame.dispose();
            new CheckLoginData(loginJTextField.getText(), String.valueOf(passwordJPasswordField.getPassword()));
        });
    }

    public void addComponents() {
        frame.add(confirmJButton);
        frame.add(backJButton);
        frame.add(urlJLabel);
        frame.add(passwordJLabel);
        frame.add(loginJTextField);
        frame.add(welcomeJLabel);
        frame.add(passwordJPasswordField);
        frame.add(urlJTextField);
        frame.add(loginJLabel);
    }

    public static void main(String[] args) {
        new ConfigureConnectionToDataBasePanel();
    }
}
