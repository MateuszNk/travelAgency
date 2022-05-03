package com.app.GUI.creators;

import com.app.GUI.WelcomePanel;

import javax.swing.*;
import java.awt.*;

public class DoYouWantToClosePanel {

    public JFrame frame;
    public DoYouWantToClosePanel() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Exit", 200, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createComponents();
    }

    public JLabel communicateJLabel;
    public JButton yesJButton;
    public JButton noJButton;
    public void createComponents() {
        communicateJLabel = new JLabel("Do You want to close?");
        yesJButton = new JButton("YES");
        noJButton = new JButton("NO");

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
        communicateJLabel.setBounds(30, 10, 200, 20);
        yesJButton.setBounds(30, 45, 60, 25);
        noJButton.setBounds(110, 45, 60, 25);
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(communicateJLabel);
        setTheme.setJButtonTheme(yesJButton);
        setTheme.setJButtonTheme(noJButton);
    }

    private static final int SUCCESS = 0;
    public void addActionsListeners() {
        yesJButton.addActionListener(e -> {
            frame.dispose();
            System.exit(SUCCESS);
        });

        noJButton.addActionListener(e -> {
            frame.dispose();
        });
    }

    public void addComponents() {
        frame.add(noJButton);
        frame.add(yesJButton);
        frame.add(communicateJLabel);
    }

    public static void main(String[] args) {
        new DoYouWantToClosePanel();
    }
}
