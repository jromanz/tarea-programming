package com.mycompany.test;

import com.mycompany.clases.Vendedor;
import com.mycompany.service.impl.VendedorServiceImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

public class VendedorTest {

    public VendedorTest() {
    }

    //@Test
    public void deberiaCrearObjetoVendedor() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        java.util.Date fecha = sdf.parse("15-10-2014");
        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
        Vendedor vendedor = new Vendedor("41091712", "CAMPAGNOLO SUAREZ", "ADRIANA MARCELA", "2598966",
                "Av. Juan Domingo Perón 410 Bs. As.", "VEN-001", fechaSql);
        assertNotNull(vendedor);
    }

    //@Test
    public void deberiaIngresarVendedor() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fecha = sdf.parse("2010-10-10");
        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
        Vendedor vendedor = new Vendedor("41091716", "SICILIANI COSSANO", "GRISELDA MARIANA", "5656560",
                "Calle Republica de Uruguay 340 Lima 17", "VEN-005", fechaSql);
        VendedorServiceImpl vendedorService = new VendedorServiceImpl();
        vendedorService.addVendedor(vendedor);
    }

    //@Test
    public void deberiaActualizarVendedor() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fecha = sdf.parse("2010-10-10");
        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
        Vendedor vendedor = new Vendedor("41091716", "SICILIANI COSSANO", "GRISELDA MARIANA", "5656560",
                "Calle Republica de Uruguay 340 Lima 17", "VEN-005", fechaSql);
        VendedorServiceImpl vendedorService = new VendedorServiceImpl();
        vendedorService.updateVendedor(vendedor);
    }

    //@Test
    public void deberiaEliminarVendedor() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fecha = sdf.parse("2010-10-10");
        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
        
        Vendedor vendedor = new Vendedor("41091716", "SICILIANI COSSANO", "GRISELDA MARIANA", "5656560",
                "Calle Republica de Uruguay 340 Lima 17", "VEN-005", fechaSql);
        VendedorServiceImpl vendedorService = new VendedorServiceImpl();
        String codigo = "41091716";
        vendedorService.removeVendedor(codigo);
    }

    //@Test
    public void deberiaEncontrarVendedor() {
        String codigo = "41091714";
        VendedorServiceImpl vendedorService = new VendedorServiceImpl();
        Vendedor vendedor = vendedorService.findById(codigo);
        assertNotNull(vendedor);
    }
    
    //@Test
    public void deberiaTraerTodosVendedores(){
        VendedorServiceImpl vendedorService = new VendedorServiceImpl();
        List<Vendedor> vendedores = vendedorService.getAllVendedores();
        for (Vendedor vendedor : vendedores) {
            System.out.println(vendedor);
        }
        System.out.println(vendedores.size());
        assertNotNull(vendedores);
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
