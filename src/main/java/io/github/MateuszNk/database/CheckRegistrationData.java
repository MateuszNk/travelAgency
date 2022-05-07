package io.github.MateuszNk.database;

import io.github.MateuszNk.errors.ErrorType;
import io.github.MateuszNk.errors.Errors;

import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Map;

public class CheckRegistrationData {

    public Connections connections;
    public ResultSet resultSet;
    private final String login;
    private final String password;
    private final String email;
    public CheckRegistrationData(String login, String email, String password) {
        this.login = login;
        this.password = password;
        this.email = email;

        connections = new Connections();
        if ( connections.isEverythingGood ) {
            resultSet = connections.getResultSet();
        }
        if ( connections.isEverythingGood ) {
            getDataFromDatabase();
        }
    }

    private static int id;
    public void getDataFromDatabase() {
        String sql = "SELECT ID, LOGIN, EMAIL from users";
        connections.createResultSet(sql);
        resultSet = connections.getResultSet();
        if ( !connections.isEverythingGood ) { return; }

        var data = new Hashtable<String, String>();
        try {
            while ( resultSet.next() ) {
                id = resultSet.getInt("ID");
                String databaseLogin = resultSet.getString("LOGIN");
                String databaseEmail = resultSet.getString("EMAIL");
                data.put(databaseLogin, databaseEmail);
            }
            //connections.closeAllConnections();
            isTakenLoginOrEmail(data);
        } catch ( Exception e ) {
            connections.closeAllConnections();
            new Errors(ErrorType.CANNOT_GET_DATA_FROM_DATABASE, null);
        }
    }

    public void isTakenLoginOrEmail(Hashtable<String, String> data) {
        boolean isTaken = false;
        for ( Map.Entry<String, String> entry : data.entrySet() ) {
            String entryKey = entry.getKey();
            String entryValue = entry.getValue();
            if ( entryKey.equals(login) || entryValue.equals(email) ) {
                new Errors(ErrorType.LOGIN_OR_EMAIL_IS_TAKEN, null);
                isTaken = true;
                break;
            }
        }

        if ( !isTaken ) {
            new AddRecordToDatabase(login, password, email);
        }
    }

    public static int getId() { return id+1; }
}
