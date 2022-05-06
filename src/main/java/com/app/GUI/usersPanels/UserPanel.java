package com.app.GUI.usersPanels;

import com.app.GUI.creators.CreateJFrame;
import com.app.GUI.creators.CreateJMenuBar;
import com.app.GUI.creators.SetTheme;
import com.app.GUI.WelcomePanel;

import javax.swing.*;
import java.awt.*;

public class UserPanel {

    private final JFrame frame;
    private final String login;
    private final CreateJFrame createJFrame = new CreateJFrame();
    public UserPanel(String login) {
        this.login = login;
        frame = createJFrame.createJFrame("User Panel", 300, 450);
        createComponents();
    }

    private JLabel welcomeJLabel;
    private JButton bookTripJButton;
    private JButton checkBookedTripsJButton;
    private JButton resignationTripJButton;
    private JButton accountOptionsJButton;
    private JButton logoutJButton;
    private JButton exitJButton;
    public void createComponents() {
        welcomeJLabel = new JLabel("Welcome " + login);
        bookTripJButton = new JButton("BOOK TRIP");
        checkBookedTripsJButton = new JButton("CHECK YOUR TRIPS");
        resignationTripJButton = new JButton("RESIGNATION OF TRIP");
        accountOptionsJButton = new JButton("ACCOUNT OPTIONS");
        logoutJButton = new JButton("LOGOUT");
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
        int buttonWidth = 200;
        int buttonHeight = 30;
        int buttonX = 50;
        welcomeJLabel.setBounds(buttonX+50, 30, buttonWidth, buttonHeight);
        bookTripJButton.setBounds(buttonX, 90, buttonWidth, buttonHeight);
        checkBookedTripsJButton.setBounds(buttonX, 140, buttonWidth, buttonHeight);
        resignationTripJButton.setBounds(buttonX, 190, buttonWidth, buttonHeight);
        accountOptionsJButton.setBounds(buttonX, 240, buttonWidth, buttonHeight);
        logoutJButton.setBounds(buttonX, 290, buttonWidth, buttonHeight);
        exitJButton.setBounds(buttonX, 340, buttonWidth, buttonHeight);
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        frame.getContentPane().setBackground(backgroundColor);
        setTheme.setJLabelTheme(welcomeJLabel);
        setTheme.setJButtonTheme(bookTripJButton);
        setTheme.setJButtonTheme(checkBookedTripsJButton);
        setTheme.setJButtonTheme(resignationTripJButton);
        setTheme.setJButtonTheme(accountOptionsJButton);
        setTheme.setJButtonTheme(logoutJButton);
        setTheme.setJButtonTheme(exitJButton);
    }

    private final int SUCCESS = 0;
    public void addActionsListeners() {
        logoutJButton.addActionListener(e -> {
            frame.dispose();
            new WelcomePanel();
        });

        exitJButton.addActionListener(e -> {
            frame.dispose();
            System.exit(SUCCESS);
        });
    }

    public void addComponents() {
        frame.add(welcomeJLabel);
        frame.add(bookTripJButton);
        frame.add(checkBookedTripsJButton);
        frame.add(resignationTripJButton);
        frame.add(accountOptionsJButton);
        frame.add(logoutJButton);
        frame.add(exitJButton);
    }
}