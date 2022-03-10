package com.app.login;

import java.util.Scanner;

public class Menu {
    private static final int ERROR = 1;

    public void menu() {
        System.out.println("\n\t#Travel Agency#\n");
        System.out.println("1) Log in");
        System.out.println("2) Register");
        System.out.println("Choose option: ");
        chooseOption();
    }

    public void chooseOption() {
        switch ( isSelectedOptionValue() ) {
            case 1 -> {
                Login login = new Login();
                login.login();
            }
            case 2 -> {
                Registration registration = new Registration();
                registration.registration();
            }
            default -> {
                System.out.println("Wrong input data");
            }
        }
    }

    public int isSelectedOptionValue() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        try {
            option = scanner.nextInt();
        } catch ( Exception e ) {
            System.out.println("Wrong input data!");
            System.exit(ERROR);
        }

        return option;
    }
}
