package com.mycompany.clases;

import java.sql.Timestamp;

public class Ventas {

	private int id;
	private String dni_vendedor;
	private Timestamp fecha;
	private double subtotal;
	private double igv;
	private double total;
	private String dni_cliente;

	public Ventas(int id, String dni_vendedor, Timestamp fecha, double subtotal, double igv, double total, String dni_cliente) {
		this.id = id;
		this.dni_vendedor = dni_vendedor;
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.igv = igv;
		this.total = total;
		this.dni_cliente = dni_cliente;
	}

	public Ventas(String dni_vendedor, Timestamp fecha, double subtotal, double igv, double total, String dni_cliente) {
		this.dni_vendedor = dni_vendedor;
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.igv = igv;
		this.total = total;
		this.dni_cliente = dni_cliente;
	}

	@Override
	public String toString() {
		return "Ventas{" + "dni_vendedor=" + dni_vendedor + ", fecha=" + fecha + ", subtotal=" + subtotal + ", igv=" + igv + ", total=" + total + ", dni_cliente=" + dni_cliente + '}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni_vendedor() {
		return dni_vendedor;
	}

	public void setDni_vendedor(String dni_vendedor) {
		this.dni_vendedor = dni_vendedor;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getIgv() {
		return igv;
	}

	public void setIgv(double igv) {
		this.igv = igv;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getDni_cliente() {
		return dni_cliente;
	}

	public void setDni_cliente(String dni_cliente) {
		this.dni_cliente = dni_cliente;
	}

	
}
