package com.app.login;

import java.util.Scanner;

public class Login {

    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
    }
}
