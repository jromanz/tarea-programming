package com.mycompany.test;

import com.mycompany.clases.DetalleVentas;
import com.mycompany.service.impl.DetalleVentasServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DetalleVentasTest {

	public DetalleVentasTest() {
	}

	@Test
	public void deberiaIngresarObjetosDetalleVentas() {
		String nom_prod = "Fuente 600 watts";
		int cantidad = 2;
		double prec_unit = 40.7;
		double prec_vent = cantidad * prec_unit;
		String cod_prod = "PR1002";
		int id_ventas = 1;
		DetalleVentas detalleVentas1 = new DetalleVentas(nom_prod, cantidad, prec_unit, prec_vent, cod_prod, id_ventas);
		nom_prod = "Monitor LCD 19 pp";
		cantidad = 3;
		prec_unit = 220.5;
		prec_vent = cantidad * prec_unit;
		cod_prod = "PR1001";
		id_ventas = 1;
		DetalleVentas detalleVentas2 = new DetalleVentas(nom_prod, cantidad, prec_unit, prec_vent, cod_prod, id_ventas);
		
		DetalleVentasServiceImpl detalleService = new DetalleVentasServiceImpl();
		boolean resultado = detalleService.addDetalleVentas(detalleVentas1);
		assertTrue(resultado);
		detalleService = new DetalleVentasServiceImpl();
		resultado = detalleService.addDetalleVentas(detalleVentas2);
		assertTrue(resultado);
	}

	@Test
	public void deberiaCrearObjetoDetalleVentas() {
		String nom_prod = "Fuente 600 watts";
		int cantidad = 2;
		double prec_unit = 40.7;
		double prec_vent = cantidad * prec_unit;
		String cod_prod = "PR1002";
		int id_ventas = 1;
		DetalleVentas detalleVentas1 = new DetalleVentas(nom_prod, cantidad, prec_unit, prec_vent, cod_prod, id_ventas);
		nom_prod = "Monitor LCD 19 pp";
		cantidad = 3;
		prec_unit = 220.5;
		prec_vent = cantidad * prec_unit;
		cod_prod = "PR1001";
		id_ventas = 1;
		DetalleVentas detalleVentas2 = new DetalleVentas(nom_prod, cantidad, prec_unit, prec_vent, cod_prod, id_ventas);
		assertNotNull(detalleVentas1);
		assertNotNull(detalleVentas2);
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
