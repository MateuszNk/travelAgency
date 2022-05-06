package com.app.GUI;

import com.app.GUI.creators.CreateJMenuBar;
import com.app.GUI.creators.SetTheme;

import javax.swing.*;
import java.awt.*;

public class SuccessfulOperationPanel {

    private final JDialog jDialog;
    private final String communicate;
    public SuccessfulOperationPanel(String communicate, JFrame frame) {
        this.communicate = communicate;
        jDialog = new JDialog(frame);
        jDialog.setTitle("Result Window");
        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jDialog.setModal(true);

        ImageIcon icon = new ImageIcon(("/home/admin/IdeaProjects/travelAgency/src/resources/graphics/mountain.png"));
        jDialog.setIconImage(icon.getImage());
        jDialog.setLocationRelativeTo(null);
        jDialog.setLayout(null);
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 200;
        int height = 150;
        jDialog.setBounds(centerPoint.x - width / 2, centerPoint.y - height / 2, width, height);
        jDialog.setResizable(false);

        createComponentsJDialog();
        jDialog.setVisible(true);
    }

    private JLabel communicateJLabel;
    private JButton okJButton;
    public void createComponentsJDialog() {
        communicateJLabel = new JLabel(communicate);
        okJButton = new JButton("OK");

        setParametersOfComponents();

        if (CreateJMenuBar.getIsDarkTheme()) {
            paintAllComponentsJDialog(Color.BLACK, Color.LIGHT_GRAY);
        } else {
            paintAllComponentsJDialog(Color.WHITE, Color.BLACK);
        }

        addActionsListenersJDialog();
        addComponentsJDialog();
    }

    public void paintAllComponentsJDialog(Color backgroundColor, Color foregroundColor) {
        jDialog.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(communicateJLabel);
        setTheme.setJButtonTheme(okJButton);
    }

    public void addComponentsJDialog() {
        jDialog.add(okJButton);
        jDialog.add(communicateJLabel);
    }

    public void addActionsListenersJDialog() {
        okJButton.addActionListener(e -> jDialog.dispose());
    }

    public void setParametersOfComponents() {
        communicateJLabel.setBounds(20, 10, 300, 20);
        okJButton.setBounds(10, 45, 80, 25);
    }
}