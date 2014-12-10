package com.mycompany.test;

import com.mycompany.clases.DetalleVentas;
import com.mycompany.clases.Ventas;
import com.mycompany.service.impl.VentasServiceImpl;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VentasTest {

	@Test
	public void deberiaCrearObjetoVentas() {
		String dni_vendedor = "41091713";
		String dni_cliente = "78787878";
		double subtotal = 1000;
		double igv = subtotal * 0.19;
		double total = subtotal + igv;
		Timestamp fecha = new Timestamp(System.currentTimeMillis());
		Ventas ventas = new Ventas(dni_vendedor, fecha, subtotal, igv, total, dni_cliente);
		assertNotNull(ventas);
		System.out.println(fecha);
	}

	@Test
	public void deberiaIngresarVentas() {
		ArrayList<DetalleVentas> detalleVentas = new ArrayList<>();

		String nom_prod = "Disco rigido 1 terabyte";
		int cantidad = 5;
		double prec_unit = 200.5;
		double prec_vent = cantidad * prec_unit;
		String cod_prod = "PR1004";
		int id_ventas = 0;
		DetalleVentas detalleVentas1 = new DetalleVentas(nom_prod, cantidad, prec_unit, prec_vent, cod_prod, id_ventas);
		detalleVentas.add(detalleVentas1);
		
		nom_prod = "Monitor LCD 19 pp";
		cantidad = 3;
		prec_unit = 220.5;
		prec_vent = cantidad * prec_unit;
		cod_prod = "PR1001";
		id_ventas = 0;
		DetalleVentas detalleVentas2 = new DetalleVentas(nom_prod, cantidad, prec_unit, prec_vent, cod_prod, id_ventas);
		detalleVentas.add(detalleVentas2);
		
		String dni_vendedor = "41091715";
		String dni_cliente = "94949494";
		double subtotal = 3300;
		double igv = subtotal * 0.19;
		double total = subtotal + igv;
		Timestamp fecha = new Timestamp(System.currentTimeMillis());
		Ventas ventas = new Ventas(dni_vendedor, fecha, subtotal, igv, total, dni_cliente);
		
		VentasServiceImpl ventasService = new VentasServiceImpl();
		ventasService.addVenta(ventas,detalleVentas);
	}

	public VentasTest() {
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
