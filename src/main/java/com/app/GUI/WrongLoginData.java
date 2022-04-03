package com.app.GUI;

import java.awt.*;
import javax.swing.*;

public class WrongLoginData {

    public JFrame frame;
    public WrongLoginData(String title) {
        frame = new JFrame(title);
        ImageIcon icon = new ImageIcon("./graphics/mountain.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension (224, 135));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible (true);
    }

    public void wrongLoginData() {
        JButton okJButton = new JButton("OK");
        JLabel wrongDataJLabel = new JLabel("Wrong login and/or password");

        frame.add(okJButton);
        frame.add(wrongDataJLabel);

        okJButton.setBounds (60, 80, 100, 20);
        wrongDataJLabel.setBounds (15, 30, 200, 25);

        okJButton.addActionListener(e -> {
            frame.dispose();
            LoginPanel loginPanel = new LoginPanel();
        });
    }

    public void successfulLogin() {
        JButton okJButton = new JButton("OK");
        JLabel wrongDataJLabel = new JLabel("You are logged in");

        frame.add(okJButton);
        frame.add(wrongDataJLabel);

        okJButton.setBounds (60, 80, 100, 20);
        wrongDataJLabel.setBounds (15, 30, 200, 25);

        okJButton.addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
    }
}
