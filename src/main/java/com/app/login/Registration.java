package com.app.login;

import java.util.Scanner;

public class Registration {
    private static final int ERROR = 1;

    public void registration(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Repeat password: ");
        String repeatedPassword = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        if ( !password.equals(repeatedPassword) ) {
            System.out.println("Given passwords are NOT equals");
            System.exit(ERROR);
        } else if ( !email.contains("@") ) {
            System.out.println("Email is invalid");
            System.exit(ERROR);
        }
    }
}
