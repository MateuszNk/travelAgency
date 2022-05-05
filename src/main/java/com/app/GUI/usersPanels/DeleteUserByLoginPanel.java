package com.app.GUI.usersPanels;

import com.app.GUI.creators.CreateJFrame;
import com.app.GUI.creators.CreateJMenuBar;
import com.app.GUI.creators.SetTheme;
import com.app.database.DeleteRecordFromDatabase;

import javax.swing.*;
import java.awt.*;

public class DeleteUserByLoginPanel {

    private JFrame frame;
    public DeleteUserByLoginPanel() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Administrator Panel", 300, 200);
        createJFrame.addWindowListener();
        createComponents();
    }

    private JLabel communicateJLabel;
    private JTextField loginJTextField;
    private JLabel loginJLabel;
    private JButton deleteJButton;
    private JButton cancelJButton;
    public void createComponents() {
        communicateJLabel = new JLabel("Please write user login to delete");
        loginJTextField = new JTextField(16);
        loginJLabel = new JLabel("Login:");
        deleteJButton = new JButton("DELETE");
        cancelJButton = new JButton("CANCEL");

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
        communicateJLabel.setBounds(20, 10, 300, 25);
        loginJLabel.setBounds (25, 55, 50, 25);
        loginJTextField.setBounds (105, 55, 175, 25);
        deleteJButton.setBounds(35, 100, 100, 25);
        cancelJButton.setBounds(150, 100, 100, 25);
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(communicateJLabel);
        setTheme.setJLabelTheme(loginJLabel);
        setTheme.setJTextField(loginJTextField);
        setTheme.setJButtonTheme(deleteJButton);
        setTheme.setJButtonTheme(cancelJButton);
    }

    public void addActionsListeners() {
        deleteJButton.addActionListener(e -> {
            frame.dispose();
            new DeleteRecordFromDatabase(loginJTextField.getText());
        });

        cancelJButton.addActionListener(e -> {
            frame.dispose();
        });
    }

    public void addComponents() {
        frame.add(communicateJLabel);
        frame.add(loginJLabel);
        frame.add(loginJTextField);
        frame.add(deleteJButton);
        frame.add(cancelJButton);
    }
}