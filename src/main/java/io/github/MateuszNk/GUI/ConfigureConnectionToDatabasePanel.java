package io.github.MateuszNk.GUI;

import io.github.MateuszNk.GUI.creators.CreateJMenuBar;
import io.github.MateuszNk.GUI.creators.SetTheme;
import io.github.MateuszNk.files.CreateConfigurationFile;
import io.github.MateuszNk.errors.ErrorType;
import io.github.MateuszNk.errors.Errors;

import javax.swing.*;
import java.awt.*;

public class ConfigureConnectionToDatabasePanel {

    private final JDialog errorJDialog;
    private final boolean firstUse;
    public ConfigureConnectionToDatabasePanel(JFrame frame2, boolean firstUse) {
        this.firstUse = firstUse;
        errorJDialog = new JDialog(frame2);
        errorJDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        errorJDialog.setTitle("Configure connection");
        errorJDialog.setModal(true);

        ImageIcon icon = new ImageIcon(("/home/admin/IdeaProjects/travelAgency/src/resources/graphics/mountain.png"));
        errorJDialog.setIconImage(icon.getImage());
        errorJDialog.setLocationRelativeTo(null);
        errorJDialog.setLayout(null);
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 310;
        int height = 255;
        errorJDialog.setBounds(centerPoint.x - width / 2, centerPoint.y - height / 2, width, height);
        errorJDialog.setResizable(false);
        createComponentsJDialog();
        errorJDialog.setVisible(true);
    }

    public JLabel urlJLabel;
    public JLabel passwordJLabel;
    public JLabel welcomeJLabel;
    public JLabel loginJLabel;
    public JTextField urlJTextField;
    public JTextField loginJTextField;
    public JPasswordField passwordJPasswordField;
    public JButton confirmJButton;
    public JButton backJButton;
    private JButton exitJButton;
    public void createComponentsJDialog() {
        confirmJButton = new JButton("CONFIRM");
        backJButton = new JButton("BACK");
        urlJLabel = new JLabel("URL:");
        urlJTextField = new JTextField(225);
        loginJLabel = new JLabel("Login:");
        passwordJLabel = new JLabel("Password:");
        loginJTextField = new JTextField(32);
        exitJButton = new JButton("EXIT");

        welcomeJLabel = new JLabel("Please write data to database");
        passwordJPasswordField = new JPasswordField(32);

        CreateConfigurationFile ccf = new CreateConfigurationFile();
        if ( ccf.fileExists ) {
            urlJTextField.setText(ccf.getUrlToDatabase());
            loginJTextField.setText(ccf.getLoginToDatabase());
            passwordJPasswordField.setText(ccf.getPasswordToDatabase());
        }

        setParametersOfComponents();

        if ( CreateJMenuBar.getIsDarkTheme() ) {
            paintAllComponentsJDialog(Color.BLACK, Color.LIGHT_GRAY);
        } else {
            paintAllComponentsJDialog(Color.WHITE, Color.BLACK);
        }

        addActionsListenersJDialog();
        addComponentsJDialog();
    }

    public void setParametersOfComponents() {
        welcomeJLabel.setBounds(50, 15, 300, 25);
        urlJLabel.setBounds(25, 55, 50, 25);
        urlJTextField.setBounds(105, 55, 175, 25);
        loginJLabel.setBounds(25, 80, 50, 25);
        loginJTextField.setBounds (105, 80, 175, 25);
        passwordJLabel.setBounds (25, 105, 75, 25);
        passwordJPasswordField.setBounds (105, 105, 175, 25);
        confirmJButton.setBounds (35, 150, 100, 20);
        backJButton.setBounds (170, 150, 100, 20);
        exitJButton.setBounds (170, 150, 100, 20);
    }

    public void paintAllComponentsJDialog(Color backgroundColor, Color foregroundColor) {
        errorJDialog.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(welcomeJLabel);
        setTheme.setJLabelTheme(urlJLabel);
        setTheme.setJTextField(urlJTextField);
        setTheme.setJLabelTheme(loginJLabel);
        setTheme.setJTextField(loginJTextField);
        setTheme.setJLabelTheme(passwordJLabel);
        setTheme.setJPasswordField(passwordJPasswordField);
        setTheme.setJButtonTheme(confirmJButton);
        setTheme.setJButtonTheme(backJButton);
        setTheme.setJButtonTheme(exitJButton);
    }

    private final int SUCCESS = 0;
    public void addActionsListenersJDialog() {
        backJButton.addActionListener(e -> errorJDialog.dispose());
        exitJButton.addActionListener(e -> System.exit(SUCCESS));

        confirmJButton.addActionListener(e -> {
            if ( urlJTextField.getText().isEmpty()
                    || loginJTextField.getText().isEmpty() ) {
                new Errors(ErrorType.FIELDS_ARE_EMPTY, null);
                return;
            }

            errorJDialog.dispose();
            new CreateConfigurationFile(urlJTextField.getText(),
                    loginJTextField.getText(),
                    String.valueOf(passwordJPasswordField.getPassword()));
        });
    }

    public void addComponentsJDialog() {
        errorJDialog.add(welcomeJLabel);
        errorJDialog.add(urlJLabel);
        errorJDialog.add(urlJTextField);
        errorJDialog.add(loginJLabel);
        errorJDialog.add(loginJTextField);
        errorJDialog.add(passwordJLabel);
        errorJDialog.add(passwordJPasswordField);
        errorJDialog.add(confirmJButton);
        if ( !firstUse ) {
            errorJDialog.add(backJButton);
        } else {
            errorJDialog.add(exitJButton);
        }
        errorJDialog.repaint();
    }
}
