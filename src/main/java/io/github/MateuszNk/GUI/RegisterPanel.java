package io.github.MateuszNk.GUI;

import io.github.MateuszNk.GUI.creators.CreateJFrame;
import io.github.MateuszNk.GUI.creators.CreateJMenuBar;
import io.github.MateuszNk.GUI.creators.SetTheme;
import io.github.MateuszNk.files.CreateConfigurationFile;
import io.github.MateuszNk.database.CheckRegistrationData;
import io.github.MateuszNk.database.Database;
import io.github.MateuszNk.errors.ErrorType;
import io.github.MateuszNk.errors.Errors;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class RegisterPanel {

    private final JFrame frame;
    public RegisterPanel() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Registration Panel", 320, 440);
        createJFrame.addWindowListener();
        new CreateJMenuBar(frame);
        createJFrame.addWindowListener();

        CreateConfigurationFile ccf = new CreateConfigurationFile();
        if ( !ccf.fileExists ) {
            new Errors(ErrorType.CONFIGURATION_FILE_IS_MISSING, frame);
            new ConfigureConnectionToDatabasePanel(frame, true);
        } else if ( new Database().createConnection() == null ) {
            new ConfigureConnectionToDatabasePanel(frame, true);
        }

        frame.setVisible(true);
        createComponents();
    }

    private JLabel communicateJLabel;
    private JLabel loginJLabel;
    private JTextField loginJTextField;
    private JLabel passwordJLabel;
    private JPasswordField passwordJPasswordField;
    private JLabel repeatPasswordJLabel;
    private JPasswordField repeatPasswordJPasswordField;
    private JLabel emailJLabel;
    private JTextField emailJTextField;
    private JButton confirmJButton;
    private JButton backJButton;
    private JTextArea passwordComplexityJTextArea;
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
        setTheme.setJTextArea(passwordComplexityJTextArea);
        setTheme.setJPasswordField(passwordJPasswordField);
        setTheme.setJButtonTheme(confirmJButton);
        setTheme.setJButtonTheme(backJButton);
    }

    private boolean isError = false;
    public void addActionsListeners() {
        frame.getRootPane().setDefaultButton(confirmJButton);

        backJButton.addActionListener(e -> {
            frame.dispose();
            new WelcomePanel();
        });

        confirmJButton.addActionListener(e -> {
            checkIfFieldsAreEmpty();
            if ( isError ) { return; }
            frame.dispose();
            new CheckRegistrationData(loginJTextField.getText(), emailJTextField.getText(),
                    String.valueOf(passwordJPasswordField.getPassword()));
            new LoginPanel();
        });
    }

    public void checkIfFieldsAreEmpty() {
        if ( loginJTextField.getText().isEmpty()
                || String.valueOf(passwordJPasswordField.getPassword()).isEmpty()
                || String.valueOf(repeatPasswordJPasswordField.getPassword()).isEmpty()
                || emailJTextField.getText().isEmpty() ) {
            isError = true;
            new Errors(ErrorType.FIELDS_ARE_EMPTY, null);
            return;
        }
        checkPasswords();
    }

    public void checkPasswords() {
         if ( !String.valueOf(passwordJPasswordField.getPassword()).equals(
                 String.valueOf(repeatPasswordJPasswordField.getPassword())) ) {
             isError = true;
            new Errors(ErrorType.PASSWORDS_ARE_NOT_EQUALS, null);
            return;
        }
        checkPasswordComplexity();
    }

    public void checkPasswordComplexity() {
        String[] specialChars = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "\\", "|",
                "[", "{", "]", "}", ";", ":", "'", "\"", ",", "<", ".", ">", "/", "?"};
        String letters = "qwertyuiopasdfghjklzxcvbnm";
        String numbers = "1234567890";

        Boolean[] isPasswordComplexity = new Boolean[5];
        Arrays.fill(isPasswordComplexity, Boolean.FALSE);
        if ( String.valueOf(passwordJPasswordField.getPassword()).length() > 8 ) {
            isPasswordComplexity[0] = true;
        }

        for ( String x : specialChars ) {
            if ( !isPasswordComplexity[1] &&
                    String.valueOf(passwordJPasswordField.getPassword()).contains(x) ) {
                isPasswordComplexity[1] = true;
                break;
            }
        }

        for ( int i = 0; i < letters.length(); i++ ) {
            char c = letters.charAt(i);
            if ( String.valueOf(passwordJPasswordField.getPassword()).contains(String.valueOf(c)) ) {
                isPasswordComplexity[2] = true;
                break;
            }
        }

        for ( int i = 0; i < letters.length(); i++ ) {
            char c = letters.toUpperCase().charAt(i);
            if ( String.valueOf(passwordJPasswordField.getPassword()).contains(String.valueOf(c)) ) {
                isPasswordComplexity[3] = true;
                break;
            }
        }

        for ( int i = 0; i < numbers.length(); i++ ) {
            char c = numbers.charAt(i);
            if ( String.valueOf(passwordJPasswordField.getPassword()).contains(String.valueOf(c)) ) {
                isPasswordComplexity[4] = true;
                break;
            }
        }

        for ( boolean x : isPasswordComplexity ) {
            if ( !x ) {
                isError = true;
                new Errors(ErrorType.PASSWORD_IS_NOT_COMPLEXITY, null);
                return;
            }
        }
        checkEmail();
    }

    public void checkEmail() {
        String isCorrectEmail = emailJTextField.getText().substring(emailJTextField.getText().lastIndexOf("@") + 1);
        if ( !emailJTextField.getText().contains("@")  || !isCorrectEmail.contains(".") ) {
            new Errors(ErrorType.EMAIL_IS_INVALID, frame);
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
}