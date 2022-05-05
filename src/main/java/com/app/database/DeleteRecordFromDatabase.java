package com.app.database;

import com.app.errors.ErrorType;
import com.app.errors.Errors;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeleteRecordFromDatabase {

    private final Connections connections;
    public DeleteRecordFromDatabase() {
        connections = new Connections();
        if ( connections.isEverythingGood ) {
            deleteUserByLogin("test");
        }
    }

    public void deleteUserByLogin(String login) {
        String sql = "DELETE FROM `users` WHERE `users`.`LOGIN` = ?";
        try {
            PreparedStatement preparedStatement = connections.connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
            try {
                preparedStatement.close();
                connections.connection.close();
            } catch (Exception e) {
                new Errors(ErrorType.CANNOT_CLOSE_PREPARED_STATEMENT_ANDOR_CONNECTION);
            }
        } catch ( Exception e ) {
            new Errors(ErrorType.PREPARED_STATEMENT_DOESNT_WORK_PROPERLY);
        }
    }
}