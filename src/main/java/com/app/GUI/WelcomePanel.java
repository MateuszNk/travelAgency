package com.app.GUI;

import com.app.GUI.creators.CreateJFrame;
import com.app.GUI.creators.CreateJMenuBar;
import com.app.GUI.creators.DoYouWantToClosePanel;
import com.app.GUI.creators.SetTheme;

import java.awt.*;
import javax.swing.*;

public class WelcomePanel {

    private final JFrame frame;
    public WelcomePanel() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Travel Agency App", 260, 255);
        createJFrame.addWindowListener();
        new CreateJMenuBar(frame);

        createComponents();
        frame.revalidate();
        frame.repaint();
    }

    private JLabel welcomeJLabel;
    private JButton loginJButton;
    private JButton registerJButton;
    private JButton exitJButton;
    public void createComponents() {
        welcomeJLabel = new JLabel("Welcome in Travel Agency App");
        loginJButton = new JButton("LOG IN");
        registerJButton = new JButton("REGISTER");
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
        welcomeJLabel.setBounds(20, 20, 300, 20);
        loginJButton.setBounds(50, 50 , 150, 25);
        registerJButton.setBounds(50, 90 , 150, 25);
        exitJButton.setBounds(50, 130 , 150, 25);
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJButtonTheme(loginJButton);
        setTheme.setJButtonTheme(registerJButton);
        setTheme.setJButtonTheme(exitJButton);
        setTheme.setJLabelTheme(welcomeJLabel);
    }

    public void addActionsListeners() {
        exitJButton.addActionListener(e -> new DoYouWantToClosePanel());

        loginJButton.addActionListener(e -> {
            frame.dispose();
            new LoginPanel();
        });

        registerJButton.addActionListener(e -> {
            frame.dispose();
            new RegisterPanel();
        });
    }

    public void addComponents() {
        frame.add(loginJButton);
        frame.add(registerJButton);
        frame.add(welcomeJLabel);
        frame.add(exitJButton);
    }
}