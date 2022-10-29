package com.ku.business.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    UserDataBase userDataBase = new UserDataBase();
    private Connection connection;

    public Connect() {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(userDataBase.getURL(), userDataBase.getUSERNAME(), userDataBase.getPASSWORD());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public Connection getConnection() {
        return connection;
    }
}
