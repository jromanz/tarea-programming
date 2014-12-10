package com.mycompany.service.impl;

import com.mycompany.clases.Cliente;
import com.mycompany.service.ClienteService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteServiceImpl implements ClienteService {

    private final String URL = "jdbc:derby://localhost:1527/tareaprogramming";
    private final String USER = "jromanz";
    private final String PASS = "973066";
    private final Connection conn;

    public ClienteServiceImpl() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASS);
    }

    @Override
    public void addCliente(Cliente cliente) {
        String query = "insert into cliente (dni,apellidos,nombres,telefono,domicilio) values(?,?,?,?,?)";
        try (PreparedStatement pStmt = conn.prepareStatement(query);) {
            pStmt.setString(1, cliente.getDni());
            pStmt.setString(2, cliente.getApellidos());
            pStmt.setString(3, cliente.getNombres());
            pStmt.setString(4, cliente.getTelefono());
            pStmt.setString(5, cliente.getDomicilio());
            int rowAffected = pStmt.executeUpdate();
            System.out.println(rowAffected);
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
        } finally {
            try {
                close(conn);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void updateCliente(Cliente cliente) {
        String query = "update cliente set apellidos=?, nombres=?, telefono=?, domicilio=? where dni=?";
        try (PreparedStatement pStmt = conn.prepareStatement(query);) {
            pStmt.setString(1, cliente.getApellidos());
            pStmt.setString(2, cliente.getNombres());
            pStmt.setString(3, cliente.getTelefono());
            pStmt.setString(4, cliente.getDomicilio());
            pStmt.setString(5, cliente.getDni());
            int updatedRow = pStmt.executeUpdate();
            System.out.println(updatedRow);
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
        } finally {
            try {
                close(conn);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void removeCliente(String codigo) {
        String query = "delete from cliente where dni=?";
        try (   PreparedStatement pStmt = conn.prepareStatement(query);
                ) {
            pStmt.setString(1, codigo);
            int rowAffected = pStmt.executeUpdate();
            System.out.println(rowAffected);
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
        } finally {
            try {
                close(conn);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Cliente findById(String codigo) {
        Cliente cliente = null;
        String query = "select * from cliente where dni = ?";
        try (PreparedStatement pStmt = conn.prepareStatement(query);) {
            pStmt.setString(1, codigo);
            ResultSet rs = pStmt.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    cliente = new Cliente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                    System.out.println(rs.getString(1));
                    System.out.println(rs.getString(2));
                    System.out.println(rs.getString(3));
                    System.out.println(rs.getString(4));
                    System.out.println(rs.getString(5));
                }
            } else {
                System.out.println("Not Found");
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
        } finally {
            try {
                close(conn);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return cliente;
    }

    @Override
    public List<Cliente> getAllClientes() throws Exception {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Cliente cliente = null;
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from cliente");) {
            while (rs.next()) {
                String dni = rs.getString("dni");
                String apellidos = rs.getString("apellidos");
                String nombres = rs.getString("nombres");
                String telefono = rs.getString("telefono");
                String domicilio = rs.getString("domicilio");
                cliente = new Cliente(dni, apellidos, nombres, telefono, domicilio);
                clientes.add(cliente);
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
        } finally {
            close(conn);
        }
        return clientes;
    }

    private static void close(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }
}
