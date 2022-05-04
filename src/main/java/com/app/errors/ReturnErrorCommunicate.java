package com.app.errors;

public class ReturnErrorCommunicate {

    public static String returnCommunicate(ErrorType errorType) {
        switch ( errorType ) {
            case CANNOT_CLOSE_ALL -> {
                return "Cannot close resultSet and/or statement, connection!";
            } case CANNOT_CLOSE_STATEMENT_ANDOR_CONNECTION -> {
                return "Cannot close statement and/or connection!";
            } case CANNOT_CLOSE_RESULT_SET -> {
                return "Cannot close resultSet!";
            } case CANNOT_CREATE_CONNECTION -> {
                return "Cannot create connection!";
            } case CANNOT_CLOSE_CONNECTION -> {
                return "Cannot close connection!";
            } case CANNOT_GET_DATA_FROM_DATABASE -> {
                return "Cannot get data from database!";
            } case WRONG_LOGIN_ANDOR_PASSWORD -> {
                return "Wrong login and/or password!";
            } case CANNOT_CLOSE_PREPARED_STATEMENT_ANDOR_CONNECTION -> {
                return "Cannot close preparedStatement and/or connection!";
            } case PREPARED_STATEMENT_DOESNT_WORK_PROPERLY -> {
                return "preparedStatement doesn't work properly!";
            } case NO_CONNECTION_TO_DATABASE -> {
                return "No connection to database!";
            } case ERROR_OF_ERROR -> {
                return "Critical error!";
            } case CONFIGURATION_FILE_IS_MISSING -> {
                return "Configuration file is broken or missing!";
            } case PASSWORDS_ARE_NOT_EQUALS -> {
                return "Passwords are not equals!";
            } case EMAIL_IS_INVALID -> {
                return "Email is invalid!";
            } case FIELDS_ARE_EMPTY -> {
                return "Some field/s are empty!";
            } case PASSWORD_IS_NOT_COMPLEXITY -> {
                return "Password does not meet the requirements";
            }
            default -> {
                return "Error of Error!";
            }
        }
    }
}
