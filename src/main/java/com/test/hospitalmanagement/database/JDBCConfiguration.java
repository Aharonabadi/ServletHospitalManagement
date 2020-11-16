package com.test.hospitalmanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConfiguration {

    private static String username = "root";
    private static String password = "root";
    private static String url = "jdbc:mysql://localhost:3306/hospital";

    private static Connection JDBCConnection;

    public static Connection getJDBCConnection() {

        if (JDBCConnection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                JDBCConnection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JDBCConnection;
    }
}