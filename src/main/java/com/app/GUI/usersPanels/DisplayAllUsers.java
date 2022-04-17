package com.app.GUI.usersPanels;

import com.app.GUI.CreateJFrame;
import com.app.database.Connections;
import com.app.errors.ErrorType;
import com.app.errors.Errors;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

public class DisplayAllUsers {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private JFrame frame;
    private JButton backJButton;
    private JButton saveDataToFileJButton;
    private JTextArea recordsJTextField;
    public DisplayAllUsers() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Display Users", 500, 500);
        createResults();
        addActionsListeners();
    }

    public void createResults() {
        String sql = "SELECT ID, LOGIN, EMAIL from users";
        Connections connections = new Connections();
        connections.createResultSet(sql);
        ResultSet resultSet = connections.getResultSet();
        ArrayList<String> allRecordsInList = new ArrayList<String>();
        try {
            while ( resultSet.next() ) {
                int id = resultSet.getInt("ID");
                String login = resultSet.getString("LOGIN");
                String email = resultSet.getString("EMAIL");
                String strId = String.valueOf(id);
                String allRecords = strId + "  |  " + login + "  |  " + email + "\n";
                allRecordsInList.add(allRecords);
            }
        } catch ( Exception e ) {
            connections.closeAllConnections();
            new Errors(ErrorType.CANNOT_GET_DATA_FROM_DATABASE);
        }

        String listCustomers = allRecordsInList.stream().map(Object::toString).collect(Collectors.joining());
        System.out.println(listCustomers);
        addComponents(listCustomers);
    }

    public void addComponents(String records) {
        backJButton = new JButton("BACK");
        saveDataToFileJButton = new JButton("SAVE DATA");
        recordsJTextField = new JTextArea("ID:  |  LOGIN:  |  MAIL:\n" + records);
        recordsJTextField.setBounds(10, 10, 200, 200);
        backJButton.setBounds(150, 250, 150, 25);
        saveDataToFileJButton.setBounds(250, 350, 150, 25);

        recordsJTextField.setEditable(false);
        frame.add(recordsJTextField);
        frame.add(backJButton);
        frame.add(saveDataToFileJButton);
    }

    public void addActionsListeners() {
        backJButton.addActionListener(e -> {
            frame.dispose();
            new AdministratorPanel();
        });
        saveDataToFileJButton.addActionListener(e -> {
            frame.dispose();
            System.out.println("Tutaj będzie przekierowanie do zapisywania danych");
        });
    }

    public static void main(String[] args) {
        new DisplayAllUsers();
    }
}
