package com.app.GUI;

import com.app.GUI.creators.CreateJFrame;
import com.app.GUI.creators.CreateJMenuBar;
import com.app.GUI.creators.SetTheme;
import com.app.database.CheckLoginData;
import com.app.database.CheckRegistrationData;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel {

    public JFrame frame;
    public RegisterPanel() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Registration Panel", 300, 300);
        new CreateJMenuBar(frame);

        createJFrame.addWindowListener();
        createComponents();
    }

    public JLabel communicateJLabel;
    public JLabel loginJLabel;
    public JTextField loginJTextField;
    public JLabel passwordJLabel;
    public JPasswordField passwordJPasswordField;
    public JLabel repeatPasswordJLabel;
    public JPasswordField repeatPasswordJPasswordField;
    public JLabel emailJLabel;
    public JTextField emailJTextField;
    public JButton confirmJButton;
    public JButton backJButton;
    public void createComponents() {
        communicateJLabel = new JLabel("Please write all fields to continue");
        loginJLabel = new JLabel("Login:");
        loginJTextField = new JTextField(16);
        passwordJLabel = new JLabel("Password:");
        passwordJPasswordField = new JPasswordField(32);
        repeatPasswordJLabel = new JLabel("Repeat:");
        repeatPasswordJPasswordField = new JPasswordField(32);
        emailJLabel = new JLabel("E-mail:");
        emailJTextField = new JTextField(32);
        confirmJButton = new JButton("CONFIRM");
        backJButton = new JButton("BACK");

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
        communicateJLabel.setBounds (35, 20, 300, 25);
        loginJLabel.setBounds (25, 55, 50, 25);
        loginJTextField.setBounds (105, 55, 175, 25);
        passwordJLabel.setBounds (25, 90, 75, 25);
        passwordJPasswordField.setBounds (105, 90, 175, 25);
        repeatPasswordJLabel.setBounds(25, 125, 75, 25);
        repeatPasswordJPasswordField.setBounds(105, 125, 175, 25);
        emailJLabel.setBounds(25, 160, 75, 25);
        emailJTextField.setBounds(105, 160, 175, 25);
        confirmJButton.setBounds (35, 200, 100, 20);
        backJButton.setBounds (170, 200, 100, 20);
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(emailJLabel);
        setTheme.setJTextField(emailJTextField);
        setTheme.setJLabelTheme(communicateJLabel);
        setTheme.setJLabelTheme(loginJLabel);
        setTheme.setJLabelTheme(passwordJLabel);
        setTheme.setJLabelTheme(repeatPasswordJLabel);
        setTheme.setJPasswordField(repeatPasswordJPasswordField);
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
            new CheckRegistrationData();
        });
    }

    public void addComponents() {
        frame.add(emailJLabel);
        frame.add(emailJTextField);
        frame.add(backJButton);
        frame.add(loginJLabel);
        frame.add(passwordJLabel);
        frame.add(loginJTextField);
        frame.add(communicateJLabel);
        frame.add(passwordJPasswordField);
        frame.add(repeatPasswordJLabel);
        frame.add(repeatPasswordJPasswordField);
        frame.add(confirmJButton);
    }

    public static void main(String[] args) {
        new RegisterPanel();
    }
}
