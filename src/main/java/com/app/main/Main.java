package com.app.main;

import com.app.database.Database;
import com.app.login.Menu;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.menu();
        //cnt();
    }

    public static void cnt() {
        Database db = new Database();
        db.turnOnDatabase();
    }
}
