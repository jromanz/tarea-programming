package com.mycompany.service.impl;

import com.mycompany.clases.Vendedor;
import com.mycompany.db.DBManager;
import com.mycompany.service.VendedorService;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VendedorServiceImpl implements VendedorService {

    @Override
    public void addVendedor(Vendedor vendedor) {
        String query = "insert into vendedor (dni,apellidos,nombres,telefono,domicilio,codigovendedor,fechaalta) "
                + "values (?,?,?,?,?,?,?)";
        if (vendedor != null) {
            try (DBManager dbManager = new DBManager();
                    PreparedStatement pStmt = dbManager.getConnection().prepareStatement(query)) {
                pStmt.setString(1, vendedor.getDni());
                pStmt.setString(2, vendedor.getApellidos());
                pStmt.setString(3, vendedor.getNombres());
                pStmt.setString(4, vendedor.getTelefono());
                pStmt.setString(5, vendedor.getDomicilio());
                pStmt.setString(6, vendedor.getCodigoVendedor());
                pStmt.setDate(7, vendedor.getFechaAlta());
                int rowAffected = pStmt.executeUpdate();
                System.out.println("filas afectadas: " + rowAffected);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Objeto vendedor null (add)...");
        }

    }

    @Override
    public void updateVendedor(Vendedor vendedor) {
        String query = "update vendedor set apellidos=?, nombres=?, telefono=?, domicilio=?, codigovendedor=?, "
                + "fechaalta=? where dni=?";
        if (vendedor != null) {
            try (DBManager dbManager = new DBManager();
                    PreparedStatement pStmt = dbManager.getConnection().prepareStatement(query);) {
                pStmt.setString(1, vendedor.getApellidos());
                pStmt.setString(2, vendedor.getNombres());
                pStmt.setString(3, vendedor.getTelefono());
                pStmt.setString(4, vendedor.getDomicilio());
                pStmt.setString(5, vendedor.getCodigoVendedor());
                pStmt.setDate(6, vendedor.getFechaAlta());
                pStmt.setString(7, vendedor.getDni());
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
                    e.getNextException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Objeto Vendedor null (update)...");
        }
    }

    @Override
    public void removeVendedor(String codigo) {
        String query = "delete from vendedor where dni=?";
        if (!codigo.equals("")) {
            try (DBManager dbManager = new DBManager();
                    PreparedStatement pStmt = dbManager.getConnection().prepareStatement(query);) {
                pStmt.setString(1, codigo);
                int rowAffected = pStmt.executeUpdate();
                System.out.println("filas afectadas: " + rowAffected);
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
                    e.getNextException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Object vendedor null (remove)...");
        }
    }

    @Override
    public Vendedor findById(String codigo) {
        String query = "select * from vendedor where dni=?";
        Vendedor vendedorTemp = null;
        if (!codigo.equals("")) {
            try (DBManager dbManager = new DBManager();
                    PreparedStatement pStmt = dbManager.getConnection().prepareStatement(query);) {
                pStmt.setString(1, codigo);
                ResultSet rs = pStmt.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        vendedorTemp = new Vendedor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));
                        System.out.println(rs.getString(1));
                        System.out.println(rs.getString(2));
                        System.out.println(rs.getString(3));
                        System.out.println(rs.getString(4));
                        System.out.println(rs.getString(5));
                        System.out.println(rs.getString(6));
                        System.out.println(rs.getDate(7));
                    }
                } else {
                    System.out.println("ResultSet not found...");
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
                    e.getNextException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Object vendedor null (findById)...");
        }
        return vendedorTemp;
    }
	
	public HashMap<String,String> getVendedoresComboBox(){
		HashMap<String,String> vendedores = new HashMap<String,String>();
		String query = "select dni, apellidos ||', '|| nombres from vendedor";

        try (   DBManager dbManager = new DBManager();
                Statement pStmt = dbManager.getConnection().createStatement();
                ResultSet rs = pStmt.executeQuery(query);
                ){
            while(rs.next()){
                String dni = rs.getString(1);
                String apellidos = rs.getString(2);
				vendedores.put(dni, apellidos);
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
        } catch (Exception e){
            e.printStackTrace();
        }
		
		return vendedores;
	}
	
    @Override
    public List<Vendedor> getAllVendedores() {
        ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
        Vendedor vendedor = null;
        String query = "select * from vendedor";
        
        try (   DBManager dbManager = new DBManager();
                Statement pStmt = dbManager.getConnection().createStatement();
                ResultSet rs = pStmt.executeQuery(query);
                ){
            while(rs.next()){
                String dni = rs.getString(1);
                String apellidos = rs.getString(2);
                String nombres = rs.getString(3);
                String telefono = rs.getString(4);
                String domicilio = rs.getString(5);
                String codigoVendedor =rs.getString(6);
                Date fechaAlta = rs.getDate(7);
                vendedor = new Vendedor(dni, apellidos, nombres, telefono, domicilio, codigoVendedor, fechaAlta);
                vendedores.add(vendedor);
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
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return vendedores;
    }

}
