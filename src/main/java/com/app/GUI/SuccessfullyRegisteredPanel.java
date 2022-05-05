package com.app.GUI;

import com.app.GUI.creators.CreateJFrame;
import com.app.GUI.creators.CreateJMenuBar;
import com.app.GUI.creators.SetTheme;

import javax.swing.*;
import java.awt.*;

public class SuccessfullyRegisteredPanel {

    public JFrame frame;
    private final String communicate;
    public SuccessfullyRegisteredPanel(String communicate) {
        this.communicate = communicate;
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Success", 200, 150);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        createComponents();
    }

    public JLabel communicateJLabel;
    public JButton okJButton;
    public void createComponents() {
        communicateJLabel = new JLabel(communicate);
        okJButton = new JButton("OK");

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
        communicateJLabel.setBounds(20, 10, 200, 20);
        okJButton.setBounds(60, 45, 60, 25);
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(communicateJLabel);
        setTheme.setJButtonTheme(okJButton);
    }

    public void addActionsListeners() {
        okJButton.addActionListener(e -> {
            frame.dispose();
            new LoginPanel();
        });
    }

    public void addComponents() {
        frame.add(okJButton);
        frame.add(communicateJLabel);
    }

    public static void main(String[] args) {
        new SuccessfullyRegisteredPanel("Wololo");
    }
}
