package com.app.GUI;

import javax.swing.*;
import java.awt.*;

public class LoginPanel {

    public JFrame frame;
    public static final int WIDTH = 300;
    public static final int HEIGHT = 250;
    public LoginPanel() {
        frame = new JFrame ("Login Panel");
        ImageIcon icon = new ImageIcon("./graphics/mountain.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        Point p=GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        frame.setBounds(p.x - WIDTH / 2, p.y - HEIGHT / 2, WIDTH, HEIGHT);
        addComponents();

        frame.setResizable(false);
        frame.setVisible (true);
    }

    public JLabel loginJLabel;
    public JLabel passwordJLabel;
    public JLabel welcomeJLabel;
    public JTextField loginJTextField;
    public JPasswordField passwordJPasswordField;
    public JButton loginJButton;
    public JButton backJButton;
    public void addComponents() {
        loginJButton = new JButton("CONFIRM");
        backJButton = new JButton("BACK");
        loginJLabel = new JLabel("Login:");
        passwordJLabel = new JLabel("Password:");
        loginJTextField = new JTextField(16);
        welcomeJLabel = new JLabel("Please login to continue");
        passwordJPasswordField = new JPasswordField(32);

        welcomeJLabel.setBounds (70, 20, 200, 25);
        loginJLabel.setBounds (25, 55, 50, 25);
        passwordJLabel.setBounds (25, 90, 75, 25);
        loginJTextField.setBounds (105, 55, 175, 25);
        passwordJPasswordField.setBounds (105, 85, 175, 25);
        loginJButton.setBounds (35, 150, 100, 20);
        backJButton.setBounds (170, 150, 100, 20);

        if ( WelcomePanel.getIsDarkTheme() ) {
            setTheme(Color.BLACK, Color.LIGHT_GRAY);
        } else {
            setTheme(Color.WHITE, Color.BLACK);
        }

        backJButton.addActionListener(e -> {
            frame.dispose();
            new WelcomePanel();
        });

        loginJButton.addActionListener(e -> {
            frame.dispose();
            new CheckLoginData(loginJTextField.getText(), String.valueOf(passwordJPasswordField.getPassword()));
        });

        frame.add(loginJButton);
        frame.add(backJButton);
        frame.add(loginJLabel);
        frame.add(passwordJLabel);
        frame.add(loginJTextField);
        frame.add(welcomeJLabel);
        frame.add(passwordJPasswordField);
    }

    public void setTheme(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        welcomeJLabel.setBackground(backgroundColor);
        welcomeJLabel.setForeground(foregroundColor);
        loginJLabel.setBackground(backgroundColor);
        loginJLabel.setForeground(foregroundColor);
        passwordJLabel.setBackground(backgroundColor);
        passwordJLabel.setForeground(foregroundColor);
        loginJTextField.setBackground(backgroundColor);
        loginJTextField.setForeground(foregroundColor);
        loginJTextField.setCaretColor(foregroundColor);
        passwordJPasswordField.setBackground(backgroundColor);
        passwordJPasswordField.setForeground(foregroundColor);
        passwordJPasswordField.setCaretColor(foregroundColor);
        loginJButton.setBackground(backgroundColor);
        loginJButton.setForeground(foregroundColor);
        backJButton.setBackground(backgroundColor);
        backJButton.setForeground(foregroundColor);
    }

    public static void main(String[] args) {
        new LoginPanel();
    }
}
