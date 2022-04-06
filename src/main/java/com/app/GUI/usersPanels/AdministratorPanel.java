package com.app.GUI.usersPanels;

import com.app.GUI.SetTheme;
import com.app.GUI.WelcomePanel;
import com.app.database.AdministratorOperations;

import javax.swing.*;
import java.awt.*;

public class AdministratorPanel {

    public JFrame frame;
    public static final int WIDTH = 250;
    public static final int HEIGHT = 300;
    public AdministratorPanel() {
        frame = new JFrame("Administrator Panel");
        ImageIcon icon = new ImageIcon("./graphics/mountain.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        var centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        frame.setBounds(centerPoint.x - WIDTH / 2, centerPoint.y - HEIGHT / 2, WIDTH, HEIGHT);
        addComponents();

        frame.setResizable(false);
        frame.setVisible (true);
    }

    public JLabel adminJLabel;
    public JButton displayAllJButton;
    public JButton addNewUserJButton;
    public JButton deleteUserJButton;
    public JButton logOutJButton;
    public JButton exitJButton;
    private static final int SUCCESS = 0;
    public void addComponents() {
        adminJLabel = new JLabel("# Admin Panel #");
        displayAllJButton = new JButton("DISPLAY ALL");
        addNewUserJButton = new JButton("ADD NEW USER");
        deleteUserJButton = new JButton("DELETE USER");
        logOutJButton = new JButton("LOG OUT");
        exitJButton = new JButton("EXIT");

        adminJLabel.setBounds(70, 20, 200, 20);
        displayAllJButton.setBounds(50, 50 , 150, 25);
        addNewUserJButton.setBounds(50, 90, 150, 25);
        deleteUserJButton.setBounds(50, 130, 150, 25);
        logOutJButton.setBounds(50, 170, 150, 25);
        exitJButton.setBounds(50, 210, 150, 25);

        if ( WelcomePanel.getIsDarkTheme() ) {
            paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
        } else {
            paintAllComponents(Color.WHITE, Color.BLACK);
        }

        addActionsListeners();

        frame.add(adminJLabel);
        frame.add(displayAllJButton);
        frame.add(addNewUserJButton);
        frame.add(deleteUserJButton);
        frame.add(logOutJButton);
        frame.add(exitJButton);
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(adminJLabel);
        setTheme.setJButtonTheme(displayAllJButton);
        setTheme.setJButtonTheme(addNewUserJButton);
        setTheme.setJButtonTheme(deleteUserJButton);
        setTheme.setJButtonTheme(logOutJButton);
        setTheme.setJButtonTheme(exitJButton);
    }

    public void addActionsListeners() {
        displayAllJButton.addActionListener(e -> {
            frame.dispose();
            new AdministratorOperations("DISPLAY");
        });
        logOutJButton.addActionListener(e -> {
            frame.dispose();
            new WelcomePanel();
        });
        exitJButton.addActionListener(e -> {
            frame.dispose();
            System.exit(SUCCESS);
        });
    }

    public static void main(String[] args) {
        new AdministratorPanel();
    }
}
