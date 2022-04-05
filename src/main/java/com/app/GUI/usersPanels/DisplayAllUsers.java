package com.app.GUI.usersPanels;

import javax.swing.*;
import java.awt.*;

public class DisplayAllUsers {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private JFrame frame;
    private JButton backJButton;
    private JButton saveDataToFileJButton;
    public DisplayAllUsers() {
        frame = new JFrame("Administrator Panel");
        ImageIcon icon = new ImageIcon("./graphics/mountain.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        var centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        frame.setBounds(centerPoint.x - WIDTH / 2, centerPoint.y - HEIGHT / 2, WIDTH, HEIGHT);
        addComponents();

        frame.setResizable(false);
        frame.setVisible (true);
    }

    public void addComponents() {
        backJButton = new JButton("BACK");
        saveDataToFileJButton = new JButton("SAVE DATA");

        backJButton.setBounds(100, 450, 150, 25);
        saveDataToFileJButton.setBounds(250, 450, 150, 25);

        frame.add(backJButton);
        frame.add(saveDataToFileJButton);
    }

    public void addActionsListeners() {
        backJButton.addActionListener(e -> {
            frame.dispose();
            new AdministratorPanel();
        });
        saveDataToFileJButton.addActionListener(e -> {
            frame.dispose();
            System.out.println("Tutaj będzie przekierowanie do zapisywania danych");
        });
    }

    public static void main(String[] args) {
        new DisplayAllUsers();
    }
}
