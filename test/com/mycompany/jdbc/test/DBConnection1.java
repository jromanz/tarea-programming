package com.mycompany.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection1 {
    
    private static int rowCount(ResultSet rs) throws SQLException{
        int rowCount = 0;
        int currRow = rs.getRow();
        if(!rs.last())
            return -1;
        rowCount = rs.getRow();
        if(currRow==0)
            rs.beforeFirst();
        else
            rs.absolute(currRow);
        return rowCount;
        
    }
    
    public static void main(String[] args) {
        
        String URL = "jdbc:derby://localhost:1527/tareaprogramming";
        String USER = "jromanz";
        String PASS = "973066";
        String QUERY = "select * from cliente";
        
        try (   
                Connection conn = DriverManager.getConnection(URL,USER,PASS);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(QUERY);
                ) {
            
            System.out.println("rowCount: "+rowCount(rs));
            
            rs.setFetchSize(30);
            
            while (rs.next()) {
                int dni = rs.getInt("dni");
                String apellidos = rs.getString("apellidos");
                String nombres = rs.getString("nombres");
                String telefono = rs.getString("telefono");
                String domicilio = rs.getString("domicilio");
                System.out.println("dni: "+ dni + ", "+ "apellidos: "+apellidos+", "
                        + "nombres: "+nombres+", "+"telefono: "+telefono+", "
                        + "domicilio: "+domicilio);
            }
            
        } catch (SQLException e) {
            while (e!=null) {                
                System.out.println("SQLState: "+e.getSQLState());
                System.out.println("Error code: "+e.getErrorCode());
                System.out.println("Message: "+e.getMessage());
                Throwable t = e.getCause();
                while (t!=null) {                    
                    System.out.println("Cause: "+t);
                    t = t.getCause();
                }
                e = e.getNextException();
            }
        }
        
    }
    
}
