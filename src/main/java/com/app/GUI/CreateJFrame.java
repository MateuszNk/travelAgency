package com.app.GUI;

import javax.swing.*;
import java.awt.*;

public class CreateJFrame {

    public JFrame frame;

    public JFrame createJFrame(String title, int width, int height) {
        frame = new JFrame(title);
        ImageIcon icon = new ImageIcon("./graphics/mountain.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        frame.setBounds(centerPoint.x - width / 2, centerPoint.y - height / 2, width, height);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }
}