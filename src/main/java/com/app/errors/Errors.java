package com.app.errors;

import com.app.GUI.LoginPanel;
import com.app.GUI.WelcomePanel;

import javax.swing.*;
import java.awt.*;

public class Errors {

    public Errors(ErrorType errorType) {
        String communicate = ReturnErrorCommunicate.returnCommunicate(errorType);
        createJDialog(communicate);
    }

    public JFrame frame;
    public JLabel communicateJLabel;
    public void createJDialog(String communicate) {
        communicateJLabel = new JLabel(communicate);

        Object[] options = {"BACK", "EXIT"};

        if ( WelcomePanel.getIsDarkTheme() ) {
            paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
        } else {
            paintAllComponents(Color.WHITE, Color.BLACK);
        }

        int optionOfJOptionPane = JOptionPane.showOptionDialog(
                frame,
                communicate,
                "ERROR",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                options[0]
        );

        // optionOfJOptionPane == 0 - OK
        // optionOfJOptionPane == 1 - CANCEL
        // optionOfJOptionPane == -1 - X
        if ( optionOfJOptionPane == 0 ) {
            new LoginPanel();
        } else if ( optionOfJOptionPane == 1 || optionOfJOptionPane == -1 ) {
            int SUCCESS = 0;
            System.exit(SUCCESS);
        }
    }

    void paintAllComponents(Color background, Color foreground) {
        UIManager.put("OptionPane.background", background);
        UIManager.put("Panel.background", background);
        UIManager.put("OptionPane.messageForeground", foreground);
        UIManager.put("Button.background", background);
        UIManager.put("Button.foreground", foreground);
    }

    public static void main(String[] args) {
        new Errors(ErrorType.ERROR_OF_ERROR);
    }
}