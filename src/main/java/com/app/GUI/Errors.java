package com.app.GUI;

import com.sun.net.httpserver.Authenticator;

import javax.swing.*;
import java.awt.*;

public class Errors extends JDialog {

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

        //addComponents(communicate);

        createJDialog(communicate);
        frame.setResizable(false);
        frame.setVisible (true);
    }

    public JLabel communicateJLabel;
    public void createJDialog(String communicate) {
        communicateJLabel = new JLabel(communicate);

        Object[] options = {"BACK", "EXIT"};
        int n = JOptionPane.showOptionDialog(frame,
                communicate,
                "ERROR",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                options[0]); //default button title
        /*if ( WelcomePanel.getIsDarkTheme() ) {
            paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
        } else {
            paintAllComponents(Color.WHITE, Color.BLACK);
        }*/

        if ( n == 0 ) {
            frame.dispose();
            new LoginPanel();
        } else if ( n == 1 || n == -1 ) {
            frame.dispose();
            System.exit(100);
        }
        // n == 0 - OK
        // n == 1 - CANCEL
        // n == -1 - X
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(communicateJLabel);
    }
    public static void main(String[] args) {
        new Errors("!Hola!");
    }
}
