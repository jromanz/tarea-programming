package com.mycompany.jdbc.test;

import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class DbJdbcRowSet {

    static String URL = "jdbc:derby://localhost:1527/tareaprogramming";
    static String USER = "jromanz";
    static String PASS = "973066";
    static String QUERY = "select * from cliente where apellidos like ?";

    public static void main(String[] args) {
        
        try (   JdbcRowSet jdbcRs = RowSetProvider.newFactory().createJdbcRowSet()
                ){
            
            jdbcRs.setUrl(URL);
            jdbcRs.setUsername(USER);
            jdbcRs.setPassword(PASS);
            jdbcRs.setCommand(QUERY);
            String cadena = "R%";
            jdbcRs.setString(1, cadena);
            jdbcRs.execute();
            
            while (jdbcRs.next()) {
                int dni = jdbcRs.getInt("dni");
                String apellidos = jdbcRs.getString("apellidos");
                String nombres = jdbcRs.getString("nombres");
                String telefono = jdbcRs.getString("telefono");
                String domicilio = jdbcRs.getString("domicilio");
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
