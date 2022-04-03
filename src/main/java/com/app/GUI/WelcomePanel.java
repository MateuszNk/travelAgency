package com.app.GUI;

import java.awt.*;
import java.awt.event.ItemEvent;
import javax.swing.*;

public class WelcomePanel {

    private static final int SUCCESS = 0;
    public JFrame frame;

    public WelcomePanel() {
        frame = new JFrame("Travel Agency App");
        ImageIcon icon = new ImageIcon("./graphics/mountain.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(284, 268));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        addComponents();
        
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public JRadioButton darkThemeJRadioButton;
    public JRadioButton whiteThemeJRadioButton;
    public JLabel welcomeJLabel;
    public JButton loginJButton;
    public JButton registerJButton;
    public JButton exitJButton;

    public void addComponents() {
        loginJButton = new JButton("LOG IN");
        registerJButton = new JButton("REGISTER");
        welcomeJLabel = new JLabel("Welcome in Travel Agency App");
        exitJButton = new JButton("EXIT");
        darkThemeJRadioButton = new JRadioButton("Dark Theme");
        whiteThemeJRadioButton = new JRadioButton("White Theme");
        ButtonGroup groupOfThemes = new ButtonGroup();
        groupOfThemes.add(darkThemeJRadioButton);
        groupOfThemes.add(whiteThemeJRadioButton);

        darkThemeJRadioButton.addItemListener(event -> {
            int state = event.getStateChange();
            if (state == ItemEvent.SELECTED) {
                setTheme(Color.BLACK, Color.WHITE);
            } else if ( state == ItemEvent.DESELECTED ) {
                setTheme(Color.WHITE, Color.BLACK);
            }
        });

        loginJButton.setBounds(100, 95, 100, 25);
        registerJButton.setBounds(100, 135, 100, 25);
        welcomeJLabel.setBounds(35, 45, 225, 25);
        exitJButton.setBounds(100, 175, 100, 25);
        darkThemeJRadioButton.setBounds(150, 210, 200, 25);
        whiteThemeJRadioButton.setBounds(150, 230, 200, 25);

        exitJButton.addActionListener(e -> {
            frame.dispose();
            System.exit(SUCCESS);
        });

        loginJButton.addActionListener(e -> {
            frame.dispose();
            new LoginPanel();
        });

        frame.add(loginJButton);
        frame.add(registerJButton);
        frame.add(welcomeJLabel);
        frame.add(exitJButton);
        frame.add(darkThemeJRadioButton);
        frame.add(whiteThemeJRadioButton);
    }

    public void setTheme(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        loginJButton.setBackground(backgroundColor);
        loginJButton.setForeground(foregroundColor);
        registerJButton.setBackground(backgroundColor);
        registerJButton.setForeground(foregroundColor);
        exitJButton.setBackground(backgroundColor);
        exitJButton.setForeground(foregroundColor);
        welcomeJLabel.setForeground(foregroundColor);
        darkThemeJRadioButton.setBackground(backgroundColor);
        darkThemeJRadioButton.setForeground(foregroundColor);
        whiteThemeJRadioButton.setBackground(backgroundColor);
        whiteThemeJRadioButton.setForeground(foregroundColor);
    }

    public static void main(String[] args) {
        new WelcomePanel();
    }
}

