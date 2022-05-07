package io.github.MateuszNk.GUI.creators;

import javax.swing.*;
import java.awt.*;

public class CreateJFrame {

    public JFrame frame;
    public JFrame createJFrame(String title, int width, int height) {
        frame = new JFrame(title);
        ImageIcon icon = new ImageIcon(("/home/admin/IdeaProjects/travelAgency/src/resources/graphics/mountain.png"));
        frame.setIconImage(icon.getImage());
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        frame.setBounds(centerPoint.x - width / 2, centerPoint.y - height / 2, width, height);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

    public void addWindowListener() {
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                new DoYouWantToClosePanel(frame);
            }
        });
    }
}