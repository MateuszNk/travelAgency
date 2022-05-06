package com.app.GUI.usersPanels;

import com.app.GUI.RegisterPanel;
import com.app.GUI.creators.CreateJFrame;
import com.app.GUI.creators.CreateJMenuBar;
import com.app.GUI.creators.SetTheme;
import com.app.GUI.WelcomePanel;

import javax.swing.*;
import java.awt.*;

public class AdministratorPanel {

    private final JFrame frame;
    public AdministratorPanel() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Administrator Panel", 250, 300);
        createJFrame.addWindowListener();
        createComponents();
    }

    private JLabel adminJLabel;
    private JButton displayAllJButton;
    private JButton addNewUserJButton;
    private JButton deleteUserJButton;
    private JButton logOutJButton;
    private JButton exitJButton;
    private final int SUCCESS = 0;
    public void createComponents() {
        adminJLabel = new JLabel("# Admin Panel #");
        displayAllJButton = new JButton("DISPLAY ALL");
        addNewUserJButton = new JButton("ADD NEW USER");
        deleteUserJButton = new JButton("DELETE USER");
        logOutJButton = new JButton("LOG OUT");
        exitJButton = new JButton("EXIT");

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
        adminJLabel.setBounds(70, 20, 200, 20);
        displayAllJButton.setBounds(50, 50 , 150, 25);
        addNewUserJButton.setBounds(50, 90, 150, 25);
        deleteUserJButton.setBounds(50, 130, 150, 25);
        logOutJButton.setBounds(50, 170, 150, 25);
        exitJButton.setBounds(50, 210, 150, 25);
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
            new DisplayAllUsersPanel();
        });

        addNewUserJButton.addActionListener(e -> {
            new RegisterPanel();
        });

        deleteUserJButton.addActionListener(e -> {
            new DeleteUserByLoginPanel();
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

    public void addComponents() {
        frame.add(adminJLabel);
        frame.add(displayAllJButton);
        frame.add(addNewUserJButton);
        frame.add(deleteUserJButton);
        frame.add(logOutJButton);
        frame.add(exitJButton);
    }
}