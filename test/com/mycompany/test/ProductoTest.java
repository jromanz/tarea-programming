package com.mycompany.test;
import com.mycompany.clases.Producto;
import com.mycompany.service.ProductoService;
import com.mycompany.service.impl.ProductoServiceImpl;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductoTest {
    
    ProductoService productoService;
    
    public ProductoTest() {
    }
    
    @Test
    public void deberiaInstanciarProducto() {
        String codigo = "PR1001";
        String nombre = "Monitor LCD 19 pulgadas";
        double precio = 220.5;
        int cantidad = 25;
        Producto producto = new Producto(codigo, nombre, precio, cantidad);
        assertNotNull(producto);
    }
    
    @Test
    public void deberiaIngresarProducto() throws SQLException{
        String codigo = "PR1004";
        String nombre = "Disco rigido 1 terabyte";
        double precio = 200.5;
        int cantidad = 50;
        Producto producto = new Producto(codigo, nombre, precio, cantidad);
        productoService = new ProductoServiceImpl();
        productoService.addProducto(producto);
    
        codigo = "PR1005";
        nombre = "Disco rigido 2 terabyte";
        precio = 200.5;
        cantidad = 50;
        producto = new Producto(codigo, nombre, precio, cantidad);
        productoService = new ProductoServiceImpl();
        productoService.addProducto(producto);

    }
    
    @Test
    public void deberiaActualizarProducto() throws SQLException{
        String codigo = "PR1001";
        String nombre = "Monitor LCD 19 pp";
        double precio = 220.5;
        int cantidad = 25;
        Producto producto = new Producto(codigo, nombre, precio, cantidad);
        productoService = new ProductoServiceImpl();
        productoService.updateProducto(producto);
    }
    
    @Test
    public void deberiaEliminarProducto() throws SQLException{
        String codigo = "PR1005";
        String nombre = "Disco rigido 2 terabyte";
        double precio = 200.5;
        int cantidad = 50;
        Producto producto = new Producto(codigo, nombre, precio, cantidad);
        productoService = new ProductoServiceImpl();
        productoService.removeProducto(producto.getCodigo());
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
}
