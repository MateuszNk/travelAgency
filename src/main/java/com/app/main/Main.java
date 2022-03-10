package com.app.main;

import com.app.database.Database;
import com.app.userOptions.Menu;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.menu();
        //cnt();
    }

    public static void cnt() {
        Database db = new Database();
        db.getInDatabase();
    }
}
