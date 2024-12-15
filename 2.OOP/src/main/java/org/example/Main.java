package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String host = "ep-still-morning-a2sdufws.eu-central-1.aws.neon.tech";
        String url = "jdbc:postgresql://"+host+":5432/neondb";
        String user = "neondb_owner";
        String password = "oh5pxmtGZN0J";

        // Initialize the connection
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            //e.printStackTrace();
        }
    }
}