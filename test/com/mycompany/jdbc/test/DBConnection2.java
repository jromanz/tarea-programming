package com.mycompany.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection2 {

    public static void main(String[] args) {

        String URL = "jdbc:derby://localhost:1527/tareaprogramming";
        String USER = "jromanz";
        String PASS = "973066";
        String QUERY = "select * from cliente where apellidos like ?";

        try (
                Connection conn = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement pStmt = conn.prepareStatement(QUERY);
            ) {

            String value = "R%";
            pStmt.setString(1, value);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                int dni = rs.getInt("dni");
                String apellidos = rs.getString("apellidos");
                String nombres = rs.getString("nombres");
                String telefono = rs.getString("telefono");
                String domicilio = rs.getString("domicilio");
                System.out.println("dni: " + dni + ", " + "apellidos: " + apellidos + ", "
                        + "nombres: " + nombres + ", " + "telefono: " + telefono + ", "
                        + "domicilio: " + domicilio);
            }

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

}
