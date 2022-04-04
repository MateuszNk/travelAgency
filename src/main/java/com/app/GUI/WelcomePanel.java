package com.app.GUI;

import java.awt.*;
import java.awt.event.ItemEvent;
import javax.swing.*;

public class WelcomePanel {

    private static final int SUCCESS = 0;
    public JFrame frame;
    public static final int WIDTH = 280;
    public static final int HEIGHT = 320;
    public WelcomePanel() {
        frame = new JFrame("Travel Agency App");
        ImageIcon icon = new ImageIcon("./graphics/mountain.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        frame.setBounds(centerPoint.x - WIDTH / 2, centerPoint.y - HEIGHT / 2, WIDTH, HEIGHT);

        addComponents();

        frame.setResizable(false);
        frame.setVisible(true);
    }

    public JRadioButton darkThemeJRadioButton;
    public JRadioButton lightThemeJRadioButton;
    public JLabel welcomeJLabel;
    public JButton loginJButton;
    public JButton registerJButton;
    public JButton exitJButton;
    public void addComponents() {
        welcomeJLabel = new JLabel("Welcome in Travel Agency App");
        loginJButton = new JButton("LOG IN");
        registerJButton = new JButton("REGISTER");
        exitJButton = new JButton("EXIT");
        darkThemeJRadioButton = new JRadioButton("Dark Theme");
        lightThemeJRadioButton = new JRadioButton("Light Theme");
        ButtonGroup groupOfThemes = new ButtonGroup();
        groupOfThemes.add(darkThemeJRadioButton);
        groupOfThemes.add(lightThemeJRadioButton);

        darkThemeJRadioButton.addItemListener(event -> {
            int state = event.getStateChange();
            if (state == ItemEvent.SELECTED) {
                setTheme(Color.BLACK, Color.LIGHT_GRAY);
            } else if ( state == ItemEvent.DESELECTED ) {
                setTheme(Color.WHITE, Color.BLACK);
            }
        });

        welcomeJLabel.setBounds(35, 25, 225, 25);
        loginJButton.setBounds(85, 65, 100, 25);
        registerJButton.setBounds(85, 110, 100, 25);
        exitJButton.setBounds(85, 155, 100, 25);
        darkThemeJRadioButton.setBounds(150, 210, 200, 25);
        lightThemeJRadioButton.setBounds(150, 230, 200, 25);

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
        frame.add(lightThemeJRadioButton);
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
        lightThemeJRadioButton.setBackground(backgroundColor);
        lightThemeJRadioButton.setForeground(foregroundColor);
    }

    public static void main(String[] args) {
        new WelcomePanel();
    }
}

