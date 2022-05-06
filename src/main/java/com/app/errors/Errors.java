
package com.app.errors;

import com.app.GUI.ConfigureConnectionToDataBasePanel;
import com.app.GUI.creators.CreateJFrame;
import com.app.GUI.creators.CreateJMenuBar;
import com.app.GUI.creators.SetTheme;

import javax.swing.*;
import java.awt.*;

public class Errors {
    public JFrame frame;
    public Errors(ErrorType errorType) {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Error", 320, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String communicate = ReturnErrorCommunicate.returnCommunicate(errorType);
        createComponents(communicate);
    }

    public JDialog errorJDialog;
    public Errors(ErrorType errorType, JFrame frame2) {
        errorJDialog = new JDialog(frame2);
        errorJDialog.setTitle("Error");
        errorJDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        errorJDialog.setModal(true);

        ImageIcon icon = new ImageIcon(("/home/admin/IdeaProjects/travelAgency/src/resources/graphics/mountain.png"));
        errorJDialog.setIconImage(icon.getImage());
        errorJDialog.setLocationRelativeTo(null);
        errorJDialog.setLayout(null);
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 320;
        int height = 150;
        errorJDialog.setBounds(centerPoint.x - width / 2, centerPoint.y - height / 2, width, height);
        errorJDialog.setResizable(false);

        String communicate = ReturnErrorCommunicate.returnCommunicate(errorType);
        createComponentsJDialog(communicate);
        errorJDialog.setVisible(true);
    }

    public JLabel communicateJLabel;
    public JButton okJButton;
    public JButton exitJButton;
    public void createComponentsJDialog(String communicate) {
        communicateJLabel = new JLabel(communicate);
        okJButton = new JButton("OK");
        exitJButton = new JButton("EXIT");

        setParametersOfComponents();
        if ( CreateJMenuBar.getIsDarkTheme() ) {
            paintAllComponentsJDialog(Color.BLACK, Color.LIGHT_GRAY);
        } else {
            paintAllComponentsJDialog(Color.WHITE, Color.BLACK);
        }
        addActionsListenersJDialog();
        addComponentsJDialog();
    }

    public void paintAllComponentsJDialog(Color backgroundColor, Color foregroundColor) {
        errorJDialog.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(communicateJLabel);
        setTheme.setJButtonTheme(okJButton);
        setTheme.setJButtonTheme(exitJButton);
    }

    public void addComponentsJDialog() {
        errorJDialog.add(okJButton);
        errorJDialog.add(exitJButton);
        errorJDialog.add(communicateJLabel);
    }

    public void addActionsListenersJDialog() {
        exitJButton.addActionListener(e -> {
            errorJDialog.dispose();
            System.exit(SUCCESS);
        });

        okJButton.addActionListener(e -> {
            errorJDialog.dispose();
        });
    }

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
        communicateJLabel.setBounds(20, 10, 300, 20);
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
}