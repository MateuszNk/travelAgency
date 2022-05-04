package com.app.GUI;

import com.app.GUI.creators.CreateJFrame;
import com.app.GUI.creators.CreateJMenuBar;
import com.app.GUI.creators.SetTheme;
import com.app.database.CheckRegistrationData;
import com.app.errors.ErrorType;
import com.app.errors.Errors;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class RegisterPanel {

    public JFrame frame;
    public RegisterPanel() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Registration Panel", 320, 420);
        new CreateJMenuBar(frame);

        createJFrame.addWindowListener();
        createComponents();
    }

    public JLabel communicateJLabel;
    public JLabel loginJLabel;
    public JTextField loginJTextField;
    public JLabel passwordJLabel;
    public JPasswordField passwordJPasswordField;
    public JLabel repeatPasswordJLabel;
    public JPasswordField repeatPasswordJPasswordField;
    public JLabel emailJLabel;
    public JTextField emailJTextField;
    public JButton confirmJButton;
    public JButton backJButton;
    public JTextArea passwordComplexityJTextArea;
    public void createComponents() {
        communicateJLabel = new JLabel("Please write all fields to continue");
        loginJLabel = new JLabel("Login:");
        loginJTextField = new JTextField(16);
        passwordJLabel = new JLabel("Password:");
        passwordJPasswordField = new JPasswordField(32);
        repeatPasswordJLabel = new JLabel("Repeat:");
        repeatPasswordJPasswordField = new JPasswordField(32);
        emailJLabel = new JLabel("E-mail:");
        emailJTextField = new JTextField(32);
        confirmJButton = new JButton("CONFIRM");
        backJButton = new JButton("BACK");
        passwordComplexityJTextArea = new JTextArea("""
                Rules for creating a password:
                  *) Minimum 8 characters,
                  *) At least one lower case letter,
                  *) At least one capital letter,
                  *) At least one digit,
                  *) At least one special character (ex. "!", "@"),
                  *) Cannot be related to login and e-mail""");
        passwordComplexityJTextArea.setEditable(false);

        setParametersOfComponents();

        if ( CreateJMenuBar.getIsDarkTheme() ) {
            paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
        } else {
            paintAllComponents(Color.WHITE, Color.BLACK);
        }

        addActionsListeners();
        addComponents();
    }

    public void setParametersOfComponents() {
        communicateJLabel.setBounds (35, 20, 300, 25);
        loginJLabel.setBounds (25, 55, 50, 25);
        loginJTextField.setBounds (105, 55, 175, 25);
        passwordJLabel.setBounds (25, 90, 75, 25);
        passwordJPasswordField.setBounds (105, 90, 175, 25);
        repeatPasswordJLabel.setBounds(25, 125, 75, 25);
        repeatPasswordJPasswordField.setBounds(105, 125, 175, 25);
        emailJLabel.setBounds(25, 160, 75, 25);
        emailJTextField.setBounds(105, 160, 175, 25);
        confirmJButton.setBounds (35, 200, 100, 20);
        backJButton.setBounds (170, 200, 100, 20);
        passwordComplexityJTextArea.setBounds(15, 240, 300, 150);
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(emailJLabel);
        setTheme.setJTextField(emailJTextField);
        setTheme.setJLabelTheme(communicateJLabel);
        setTheme.setJLabelTheme(loginJLabel);
        setTheme.setJLabelTheme(passwordJLabel);
        setTheme.setJLabelTheme(repeatPasswordJLabel);
        setTheme.setJPasswordField(repeatPasswordJPasswordField);
        setTheme.setJTextField(loginJTextField);
        setTheme.setJPasswordField(passwordJPasswordField);
        setTheme.setJButtonTheme(confirmJButton);
        setTheme.setJButtonTheme(backJButton);
    }

    public void addActionsListeners() {
        backJButton.addActionListener(e -> {
            frame.dispose();
            new WelcomePanel();
        });

        confirmJButton.addActionListener(e -> {
            checkIfFieldsAreEmpty();
            frame.dispose();
            new CheckRegistrationData();
        });
    }

    public void checkIfFieldsAreEmpty() {
        if ( loginJTextField == null || passwordJPasswordField == null
                || repeatPasswordJPasswordField == null || emailJTextField == null ) {
            new Errors(ErrorType.FIELDS_ARE_EMPTY);
        }
        checkPasswords();
        checkPasswordComplexity();
        checkEmail();
    }

    public void checkPasswords() {
         if ( !String.valueOf(passwordJPasswordField.getPassword()).equals(
                 String.valueOf(repeatPasswordJPasswordField.getPassword())) ) {
            new Errors(ErrorType.PASSWORDS_ARE_NOT_EQUALS);
        }
    }

    public void checkPasswordComplexity() {
        String[] specialChars = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "\\", "|",
                "[", "{", "]", "}", ";", ":", "'", "\"", ",", "<", ".", ">", "/", "?"};
        String letters = "qwertyuiopasdfghjklzxcvbnm";
        String numbers = "1234567890";

        Boolean[] trues = new Boolean[5];
        Arrays.fill(trues, Boolean.FALSE);
        if ( String.valueOf(passwordJPasswordField.getPassword()).length() > 8 ) {
            trues[0] = false;
        }

        for ( String x : specialChars ) {
            if ( !trues[1] && String.valueOf(passwordJPasswordField.getPassword()).contains(x) ) {
                trues[1] = true;
                break;
            }
        }

        for ( int i = 0; i < letters.length(); i++ ) {
            char c = letters.charAt(i);
            if ( String.valueOf(passwordJPasswordField.getPassword()).contains(String.valueOf(c)) ) {
                trues[2] = true;
                break;
            }
        }

        for ( int i = 0; i < letters.length(); i++ ) {
            char c = letters.toUpperCase().charAt(i);
            if ( String.valueOf(passwordJPasswordField.getPassword()).contains(String.valueOf(c)) ) {
                trues[3] = true;
                break;
            }
        }

        for ( int i = 0; i < numbers.length(); i++ ) {
            char c = numbers.charAt(i);
            if ( String.valueOf(passwordJPasswordField.getPassword()).contains(String.valueOf(c)) ) {
                trues[4] = true;
                break;
            }
        }

        for ( boolean isPasswordComplexity : trues ) {
            if ( !isPasswordComplexity ) {
                new Errors(ErrorType.PASSWORD_IS_NOT_COMPLEXITY);
            }
        }
    }

    public void checkEmail() {
        String isCorrectEmail = emailJTextField.getText().substring(emailJTextField.getText().lastIndexOf("@") + 1);
        if ( !emailJTextField.getText().contains("@")  || !isCorrectEmail.contains(".") ) {
            new Errors(ErrorType.EMAIL_IS_INVALID);
        }
    }

    public void addComponents() {
        frame.add(emailJLabel);
        frame.add(emailJTextField);
        frame.add(backJButton);
        frame.add(loginJLabel);
        frame.add(passwordJLabel);
        frame.add(loginJTextField);
        frame.add(communicateJLabel);
        frame.add(passwordJPasswordField);
        frame.add(repeatPasswordJLabel);
        frame.add(repeatPasswordJPasswordField);
        frame.add(confirmJButton);
        frame.add(passwordComplexityJTextArea);
    }

    public String getLoginJTextField() { return loginJTextField.getText(); }
    public String getPasswordJPasswordField() { return String.valueOf(passwordJPasswordField.getPassword()); }
    public String getEmailJTextField() { return emailJTextField.getText(); }

    public static void main(String[] args) {
        new RegisterPanel();
    }
}
