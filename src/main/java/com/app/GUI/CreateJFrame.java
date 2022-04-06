package com.app.GUI;

import javax.swing.*;
import java.awt.*;

public record CreateJFrame(String title, int width, int height) {

    public JFrame createJFrame() {
        JFrame frame = new JFrame(title);
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