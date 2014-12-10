package com.mycompany.service.impl;

import com.mycompany.clases.DetalleVentas;
import com.mycompany.clases.Producto;
import com.mycompany.service.ProductoService;
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

public class ProductoServiceImpl implements ProductoService {

	private final String URL = "jdbc:derby://localhost:1527/tareaprogramming";
	private final String USER = "jromanz";
	private final String PASS = "973066";
	private final Connection conn;

	public ProductoServiceImpl() throws SQLException {
		conn = DriverManager.getConnection(URL, USER, PASS);
	}

	@Override
	public void addProducto(Producto producto) {
		String query = "insert into producto (codigo,nombre,precio,cantidad) values (?,?,?,?)";
		try (PreparedStatement pStmt = conn.prepareStatement(query)) {
			pStmt.setString(1, producto.getCodigo());
			pStmt.setString(2, producto.getNombre());
			pStmt.setDouble(3, producto.getPrecio());
			pStmt.setInt(4, producto.getCantidad());
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
		} finally {
			try {
				close(conn);
			} catch (SQLException ex) {
				Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	@Override
	public void updateProducto(Producto producto) {
		String query = "update producto set nombre=?, precio=?, cantidad=? where codigo=?";
		try (PreparedStatement pStmt = conn.prepareStatement(query);) {
			pStmt.setString(1, producto.getNombre());
			pStmt.setDouble(2, producto.getPrecio());
			pStmt.setInt(3, producto.getCantidad());
			pStmt.setString(4, producto.getCodigo());
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
	public void removeProducto(String codigo) {
		String query = "delete from producto where codigo=?";
		try (PreparedStatement pStmt = conn.prepareStatement(query);) {
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
	public Producto findById(String codigo) {
		Producto producto = null;
		String query = "select * from producto where codigo = ?";
		try (PreparedStatement pStmt = conn.prepareStatement(query);) {
			pStmt.setString(1, codigo);
			ResultSet rs = pStmt.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					producto = new Producto(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
					System.out.println(rs.getString(1));
					System.out.println(rs.getString(2));
					System.out.println(rs.getString(3));
					System.out.println(rs.getDouble(4));
					System.out.println(rs.getInt(5));
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
				Logger.getLogger(ProductoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return producto;
	}

	@Override
	public List<Producto> getAllProductos() {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Producto producto = null;
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from producto");) {
			while (rs.next()) {
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				double precio = rs.getDouble("precio");
				int cantidad = rs.getInt("cantidad");
				producto = new Producto(codigo, nombre, precio, cantidad);
				productos.add(producto);
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
				Logger.getLogger(ProductoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return productos;
	}

	private void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	public void updateProductosVenta(ArrayList<DetalleVentas> detalleVentas) {
		String query = "update producto set cantidad = cantidad - ? where codigo = ?";
		if (detalleVentas != null) {
			try (PreparedStatement pStmt = conn.prepareStatement(query);) {
				for (DetalleVentas detalleVenta : detalleVentas) {
					pStmt.setInt(1, detalleVenta.getCantidad());
					pStmt.setString(2, detalleVenta.getCod_prod());
					int updatedRow = pStmt.executeUpdate();
					System.out.println(updatedRow);
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
		}
	}
}
