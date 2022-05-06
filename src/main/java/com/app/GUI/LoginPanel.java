package com.app.GUI;

import com.app.GUI.creators.CreateJFrame;
import com.app.GUI.creators.CreateJMenuBar;
import com.app.GUI.creators.SetTheme;
import com.app.database.CheckLoginData;

import javax.swing.*;
import java.awt.*;

public class LoginPanel {

    private final JFrame frame;
    public LoginPanel() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Login Panel", 300, 250);
        new CreateJMenuBar(frame);

        createJFrame.addWindowListener();
        createComponents();
    }

    private JLabel loginJLabel;
    private JLabel passwordJLabel;
    private JLabel welcomeJLabel;
    private JTextField loginJTextField;
    private JPasswordField passwordJPasswordField;
    private JButton loginJButton;
    private JButton backJButton;
    public void createComponents() {
        loginJButton = new JButton("CONFIRM");
        backJButton = new JButton("BACK");
        loginJLabel = new JLabel("Login:");
        passwordJLabel = new JLabel("Password:");
        loginJTextField = new JTextField(16);
        welcomeJLabel = new JLabel("Please login to continue");
        passwordJPasswordField = new JPasswordField(32);

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
        welcomeJLabel.setBounds (70, 20, 200, 25);
        loginJLabel.setBounds (25, 55, 50, 25);
        passwordJLabel.setBounds (25, 90, 75, 25);
        loginJTextField.setBounds (105, 55, 175, 25);
        passwordJPasswordField.setBounds (105, 85, 175, 25);
        loginJButton.setBounds (35, 150, 100, 20);
        backJButton.setBounds (170, 150, 100, 20);
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(welcomeJLabel);
        setTheme.setJLabelTheme(loginJLabel);
        setTheme.setJLabelTheme(passwordJLabel);
        setTheme.setJTextField(loginJTextField);
        setTheme.setJPasswordField(passwordJPasswordField);
        setTheme.setJButtonTheme(loginJButton);
        setTheme.setJButtonTheme(backJButton);
    }

    public void addActionsListeners() {
        backJButton.addActionListener(e -> {
            frame.dispose();
            new WelcomePanel();
        });

        loginJButton.addActionListener(e -> {
            frame.dispose();
            new CheckLoginData(loginJTextField.getText(),
                    String.valueOf(passwordJPasswordField.getPassword()));
        });
    }

    public void addComponents() {
        frame.add(loginJButton);
        frame.add(backJButton);
        frame.add(loginJLabel);
        frame.add(passwordJLabel);
        frame.add(loginJTextField);
        frame.add(welcomeJLabel);
        frame.add(passwordJPasswordField);
    }
}