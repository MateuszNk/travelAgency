package io.github.MateuszNk.database;

import io.github.MateuszNk.errors.ErrorType;
import io.github.MateuszNk.errors.Errors;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeleteRecordFromDatabase {

    private final Connections connections;
    private final String login;
    public DeleteRecordFromDatabase(String login) {
        this.login = login;
        connections = new Connections();
        if ( connections.isEverythingGood ) {
            deleteUserByLogin();
        }
    }

    public void deleteUserByLogin() {
        String sql = "DELETE FROM `users` WHERE `users`.`LOGIN` = ?";
        try {
            PreparedStatement preparedStatement = connections.connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
            try {
                preparedStatement.close();
                connections.connection.close();
            } catch (Exception e) {
                new Errors(ErrorType.CANNOT_CLOSE_PREPARED_STATEMENT_ANDOR_CONNECTION, null);
            }
        } catch ( Exception e ) {
            new Errors(ErrorType.PREPARED_STATEMENT_DOESNT_WORK_PROPERLY, null);
        }
        repairIdsInDatabase();
    }

    public void repairIdsInDatabase() {
        String sql = "SELECT ID, LOGIN from users";
        Connections connections = new Connections();
        connections.createResultSet(sql);
        ResultSet resultSet = connections.getResultSet();
        int beforeInt = 0;
        String updateSQL = "UPDATE `users` SET `ID`=? WHERE `users`.`LOGIN`=?";
        try {
            while ( resultSet.next() ) {
                int id = resultSet.getInt("ID");
                String login2 = resultSet.getString("LOGIN");
                if ( id != beforeInt+1 ) {
                    try {
                        PreparedStatement stmt = connections.connection.prepareStatement(updateSQL);
                        stmt.setInt(1, beforeInt+1);
                        stmt.setString(2, login2);
                        stmt.executeUpdate();
                        stmt.close();
                    } catch(Exception e ){
                        e.printStackTrace();
                    }
                }
                beforeInt++;
            }
        } catch ( Exception e ) {
            connections.closeAllConnections();
            new Errors(ErrorType.CANNOT_GET_DATA_FROM_DATABASE, null);
        }
    }
}