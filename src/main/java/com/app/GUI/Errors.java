package com.app.GUI;

import javax.swing.*;
import java.awt.*;

public class Errors {

    public static final int ERROR = 1;
    public JFrame frame;
    public static final int WIDTH = 240;
    public static final int HEIGHT = 150;
    public Errors(String communicate) {
        frame = new JFrame("ERROR");
        ImageIcon icon = new ImageIcon("./graphics/mountain.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        frame.setBounds(centerPoint.x - WIDTH / 2, centerPoint.y - HEIGHT / 2, WIDTH, HEIGHT);

        addComponents(communicate);

        frame.setResizable(false);
        frame.setVisible (true);
    }

    public JLabel communicateJLabel;
    public JButton exitJButton;
    public JButton backJButton;
    public void addComponents(String communicate) {
        communicateJLabel = new JLabel(communicate);
        exitJButton = new JButton("EXIT");
        backJButton = new JButton("BACK");

        communicateJLabel.setBounds(20, 30, 200, 25);
        backJButton.setBounds(15, 80, 100, 20);
        exitJButton.setBounds(130, 80, 100, 20);

        if ( WelcomePanel.getIsDarkTheme() ) {
            setTheme(Color.BLACK, Color.LIGHT_GRAY);
        } else {
            setTheme(Color.WHITE, Color.BLACK);
        }

        backJButton.addActionListener(e -> {
            frame.dispose();
            new LoginPanel();
        });
        exitJButton.addActionListener(e -> {
            frame.dispose();
            System.exit(ERROR);
        });

        frame.add(communicateJLabel);
        frame.add(backJButton);
        frame.add(exitJButton);
    }

    public void setTheme(Color background, Color foreground) {
        frame.getContentPane().setBackground(background);
        communicateJLabel.setBackground(background);
        communicateJLabel.setForeground(foreground);
        backJButton.setBackground(background);
        backJButton.setForeground(foreground);
        exitJButton.setBackground(background);
        exitJButton.setForeground(foreground);
    }
    public static void main(String[] args) {
        new Errors(args[0]);
    }
}
