package com.mycompany.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager implements AutoCloseable {

    String URL = "jdbc:derby://localhost:1527/tareaprogramming;create=true";
    String USER = "jromanz";
    String PASS = "973066";

    private Connection conn;

    public DBManager() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
			conn.setTransactionIsolation(1);
        } catch (SQLException e) {
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Error code: " + e.getErrorCode());
                System.out.println("Message: " + e.getMessage());
                Throwable t = e.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
                e = e.getNextException();
            }
        }
    }

    public Connection getConnection() {
        if (conn != null) {
            return conn;
        }
        else{
            return null;
        }
    }

    @Override
    public void close() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }
}
