package com.app.GUI;

import com.app.GUI.creators.CreateJFrame;
import com.app.GUI.creators.CreateJMenuBar;
import com.app.GUI.creators.SetTheme;
import com.app.configuration.CreateConfigurationFile;

import javax.swing.*;
import java.awt.*;

public class ConfigureConnectionToDataBasePanel {

    public JFrame frame;
    public ConfigureConnectionToDataBasePanel() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Configure Connection", 310, 255);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        urlJLabel = new JLabel("URL:");
        urlJTextField = new JTextField(225);
        loginJLabel = new JLabel("Login:");
        passwordJLabel = new JLabel("Password:");
        loginJTextField = new JTextField(32);

        welcomeJLabel = new JLabel("Please write data to database");
        passwordJPasswordField = new JPasswordField(32);

        CreateConfigurationFile ccf = new CreateConfigurationFile();
        if ( ccf.fileExists ) {
            urlJTextField.setText(ccf.getUrlToDatabase());
            loginJTextField.setText(ccf.getLoginToDatabase());
            passwordJPasswordField.setText(ccf.getPasswordToDatabase());
        }
        setParametersOfComponents();

        if ( CreateJMenuBar.getIsDarkTheme() ) {
            paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
        } else {
            paintAllComponents(Color.WHITE, Color.BLACK);
        }

        addActionsListeners();
        addComponents();
    }

    public void setParametersOfComponents() {
        welcomeJLabel.setBounds(50, 15, 300, 25);

        urlJLabel.setBounds(25, 55, 50, 25);
        urlJTextField.setBounds(105, 55, 175, 25);

        loginJLabel.setBounds(25, 80, 50, 25);
        loginJTextField.setBounds (105, 80, 175, 25);

        passwordJLabel.setBounds (25, 105, 75, 25);
        passwordJPasswordField.setBounds (105, 105, 175, 25);

        confirmJButton.setBounds (35, 150, 100, 20);
        backJButton.setBounds (170, 150, 100, 20);
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(welcomeJLabel);
        setTheme.setJLabelTheme(urlJLabel);
        setTheme.setJTextField(urlJTextField);
        setTheme.setJLabelTheme(loginJLabel);
        setTheme.setJTextField(loginJTextField);
        setTheme.setJLabelTheme(passwordJLabel);
        setTheme.setJPasswordField(passwordJPasswordField);
        setTheme.setJButtonTheme(confirmJButton);
        setTheme.setJButtonTheme(backJButton);
    }

    public void addActionsListeners() {
        backJButton.addActionListener(e -> frame.dispose());

        confirmJButton.addActionListener(e -> {
            frame.dispose();
            new CreateConfigurationFile(urlJTextField.getText(),
                    loginJTextField.getText(),
                    String.valueOf(passwordJPasswordField.getPassword()));
        });
    }

    public void addComponents() {
        frame.add(welcomeJLabel);
        frame.add(urlJLabel);
        frame.add(urlJTextField);
        frame.add(loginJLabel);
        frame.add(loginJTextField);
        frame.add(passwordJLabel);
        frame.add(passwordJPasswordField);
        frame.add(confirmJButton);
        frame.add(backJButton);
        frame.repaint();
    }

    public static void main(String[] args) {
        new ConfigureConnectionToDataBasePanel();
    }
}
