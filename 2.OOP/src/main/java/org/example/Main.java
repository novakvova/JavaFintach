package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        DatabaseWorker dw = new DatabaseWorker();
        dw.createTables();


    }
}