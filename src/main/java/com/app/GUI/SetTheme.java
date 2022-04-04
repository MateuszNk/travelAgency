package com.app.GUI;

import javax.swing.*;
import java.awt.*;

public record SetTheme(Color backgroundColor, Color foregroundColor) {

    public void setJButtonTheme(JButton jButton) {
        jButton.setBackground(backgroundColor);
        jButton.setForeground(foregroundColor);
    }

    public void setJLabelTheme(JLabel jLabel) {
        jLabel.setBackground(backgroundColor);
        jLabel.setForeground(foregroundColor);
    }

    public void setJRadioButton(JRadioButton jRadioButton) {
        jRadioButton.setBackground(backgroundColor);
        jRadioButton.setForeground(foregroundColor);
    }

    public void setJTextField(JTextField jTextField) {
        jTextField.setBackground(backgroundColor);
        jTextField.setForeground(foregroundColor);
        jTextField.setCaretColor(foregroundColor);
    }

    public void setJPasswordField(JPasswordField jPasswordField) {
        jPasswordField.setBackground(backgroundColor);
        jPasswordField.setForeground(foregroundColor);
        jPasswordField.setCaretColor(foregroundColor);
    }
}
