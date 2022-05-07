package io.github.MateuszNk.GUI.usersPanels;

import io.github.MateuszNk.GUI.creators.CreateJFrame;
import io.github.MateuszNk.GUI.creators.CreateJMenuBar;
import io.github.MateuszNk.GUI.creators.SetTheme;
import io.github.MateuszNk.files.CreateFileWithRecords;
import io.github.MateuszNk.database.Connections;
import io.github.MateuszNk.errors.ErrorType;
import io.github.MateuszNk.errors.Errors;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class DisplayAllUsersPanel {

    private final JFrame frame;
    private JButton backJButton;
    private JButton saveDataToFileJButton;
    private JTextArea recordsJTextField;
    public DisplayAllUsersPanel() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Display Users", 500, 500);
        createResults();
    }

    public void createResults() {
        String sql = "SELECT ID, LOGIN, EMAIL from users";
        Connections connections = new Connections();
        connections.createResultSet(sql);
        ResultSet resultSet = connections.getResultSet();
        ArrayList<String> allRecordsInList = new ArrayList<>();
        try {
            while ( resultSet.next() ) {
                int id = resultSet.getInt("ID");
                String login = resultSet.getString("LOGIN");
                String email = resultSet.getString("EMAIL");
                String strId = String.valueOf(id);
                String allRecords = strId + "    " + login + "    " + email + "\n";
                allRecordsInList.add(allRecords);
            }
        } catch ( Exception e ) {
            connections.closeAllConnections();
            new Errors(ErrorType.CANNOT_GET_DATA_FROM_DATABASE, null);
        }

        String listCustomers = allRecordsInList.stream().map(Object::toString).collect(Collectors.joining());
        createComponents(listCustomers);
    }

    public void createComponents(String records) {
        backJButton = new JButton("BACK");
        saveDataToFileJButton = new JButton("SAVE DATA");
        recordsJTextField = new JTextArea("ID:  |  LOGIN:  |  MAIL:\n" + records);
        recordsJTextField.setEditable(false);

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
        recordsJTextField.setBounds(10, 10, 350, 400);
        backJButton.setBounds(370, 150, 120, 25);
        saveDataToFileJButton.setBounds(370, 200, 120, 25);
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        frame.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJButtonTheme(backJButton);
        setTheme.setJButtonTheme(saveDataToFileJButton);
    }

    public void addActionsListeners() {
        backJButton.addActionListener(e -> {
            frame.dispose();
            new AdministratorPanel();
        });

        saveDataToFileJButton.addActionListener(e -> new CreateFileWithRecords());
    }

    public void addComponents() {
        frame.add(recordsJTextField);
        frame.add(backJButton);
        frame.add(saveDataToFileJButton);
    }
}