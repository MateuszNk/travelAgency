package com.app.errors;

import com.app.GUI.CreateJFrame;
import com.app.GUI.LoginPanel;
import com.app.GUI.SetTheme;

import javax.swing.*;
import java.awt.*;

public class Errors extends JDialog {

    public JFrame frame;
    public Errors(ErrorType errorType) {
        CreateJFrame createJFrame = new CreateJFrame("ERROR", 240, 150);
        frame = createJFrame.createJFrame();
        String communicate = ReturnErrorCommunicate.returnCommunicate(errorType);
        createJDialog(communicate);
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
        new Errors(ErrorType.CANNOT_CREATE_CONNECTION);
    }
}
