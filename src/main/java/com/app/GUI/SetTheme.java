package com.app.GUI;

import javax.swing.*;
import java.awt.*;

public class SetTheme {

    private final Color backgroundColor;
    private final Color foregroundColor;
    public SetTheme(Color backgroundColor, Color foregroundColor) {
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

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
}
