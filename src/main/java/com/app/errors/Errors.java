
package com.app.errors;

import com.app.GUI.creators.CreateJFrame;
import com.app.GUI.creators.CreateJMenuBar;
import com.app.GUI.creators.SetTheme;

import javax.swing.*;
import java.awt.*;

public class Errors {
    public JFrame frame;
    public Errors(ErrorType errorType) {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Error", 200, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String communicate = ReturnErrorCommunicate.returnCommunicate(errorType);
        createComponents(communicate);
    }

    public JLabel communicateJLabel;
    public JButton okJButton;
    public JButton exitJButton;
    public void createComponents(String communicate) {
        communicateJLabel = new JLabel(communicate);
        okJButton = new JButton("OK");
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
        communicateJLabel.setBounds(20, 10, 200, 20);
        okJButton.setBounds(10, 45, 80, 25);
        exitJButton.setBounds(110, 45, 80, 25);
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(communicateJLabel);
        setTheme.setJButtonTheme(okJButton);
        setTheme.setJButtonTheme(exitJButton);
    }

    private static final int SUCCESS = 0;
    public void addActionsListeners() {
        exitJButton.addActionListener(e -> {
            frame.dispose();
            System.exit(SUCCESS);
        });

        okJButton.addActionListener(e -> {
            frame.dispose();
        });
    }

    public void addComponents() {
        frame.add(okJButton);
        frame.add(exitJButton);
        frame.add(communicateJLabel);
    }

    public static void main(String[] args) { new Errors(ErrorType.ERROR_OF_ERROR); }
}