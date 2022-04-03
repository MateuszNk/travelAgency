package com.app.GUI;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JComponent {

    public JFrame frame;
    public JTextField loginJTextField;
    public JPasswordField passwordJPasswordField;

    public LoginPanel() {
        frame = new JFrame ("Login Panel");
        ImageIcon icon = new ImageIcon("./graphics/mountain.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        JButton loginJButton = new JButton("LOGIN");
        JButton returnJButton = new JButton("BACK");
        JLabel loginJLabel = new JLabel("Login:");
        JLabel passwordJLabel = new JLabel("Password:");
        loginJTextField = new JTextField(16);
        JLabel welcomeJLabel = new JLabel("Please login to continue");
        passwordJPasswordField = new JPasswordField(32);

        frame.setPreferredSize(new Dimension (319, 250));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        frame.add(loginJButton);
        frame.add(returnJButton);
        frame.add(loginJLabel);
        frame.add(passwordJLabel);
        frame.add(loginJTextField);
        frame.add(welcomeJLabel);
        frame.add(passwordJPasswordField);

        loginJButton.setBounds (30, 150, 100, 20);
        returnJButton.setBounds (170, 150, 100, 20);
        loginJLabel.setBounds (25, 55, 50, 25);
        passwordJLabel.setBounds (25, 90, 75, 25);
        loginJTextField.setBounds (105, 55, 175, 25);
        welcomeJLabel.setBounds (70, 15, 200, 25);
        passwordJPasswordField.setBounds (105, 85, 175, 25);

        returnJButton.addActionListener(e -> {
            frame.dispose();
            WelcomePanel welcomePanel = new WelcomePanel();
        });

        loginJButton.addActionListener(e -> {
            frame.dispose();
            CheckLoginData checkLoginData = new CheckLoginData(
                    loginJTextField.getText(), String.valueOf(passwordJPasswordField.getPassword()));
        });

        frame.setResizable(false);
        frame.pack();
        frame.setVisible (true);
    }
}
