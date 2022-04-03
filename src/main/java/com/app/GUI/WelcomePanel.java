package com.app.GUI;

import java.awt.*;
import javax.swing.*;

public class WelcomePanel extends JComponent{

    private static final int SUCCESS = 0;

    public WelcomePanel() {
        JFrame frame = new JFrame("Travel Agency App");
        ImageIcon icon = new ImageIcon("./graphics/mountain.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton loginJButton = new JButton("LOG IN");
        JButton registerJButton = new JButton("REGISTER");
        JLabel welcomeJLabel = new JLabel("Welcome in Travel Agency App");
        JButton exitJButton = new JButton("EXIT");

        frame.setPreferredSize(new Dimension(284, 268));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        frame.add(loginJButton);
        frame.add(registerJButton);
        frame.add(welcomeJLabel);
        frame.add(exitJButton);

        loginJButton.setBounds(100, 95, 100, 25);
        registerJButton.setBounds(100, 135, 100, 25);
        welcomeJLabel.setBounds(35, 45, 225, 25);
        exitJButton.setBounds(100, 175, 100, 25);

        exitJButton.addActionListener(e -> {
            frame.dispose();
            System.exit(SUCCESS);
        });

        frame.pack();
        frame.setVisible(true);
    }
}