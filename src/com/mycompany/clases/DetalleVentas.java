package com.mycompany.clases;

public class DetalleVentas {
	
	private int id_det_ventas;
	private String nom_prod;
	private int cantidad;
	private double prec_unit;
	private double prec_vent;
	private String cod_prod;
	private int id_ventas;

	public DetalleVentas(int id_det_ventas, String nom_prod, int cantidad, double prec_unit, double prec_vent, String cod_prod, int id_ventas) {
		this.id_det_ventas = id_det_ventas;
		this.nom_prod = nom_prod;
		this.cantidad = cantidad;
		this.prec_unit = prec_unit;
		this.prec_vent = prec_vent;
		this.cod_prod = cod_prod;
		this.id_ventas = id_ventas;
	}
	
	public DetalleVentas(String nom_prod, int cantidad, double prec_unit, double prec_vent, String cod_prod, int id_ventas) {
		this.nom_prod = nom_prod;
		this.cantidad = cantidad;
		this.prec_unit = prec_unit;
		this.prec_vent = prec_vent;
		this.cod_prod = cod_prod;
		this.id_ventas = id_ventas;
	}

	@Override
	public String toString() {
		return "DetalleVentas{" + "id_det_ventas=" + id_det_ventas + ", nom_prod=" + nom_prod + ", cantidad=" + cantidad + ", prec_unit=" + prec_unit + ", prec_vent=" + prec_vent + ", cod_prod=" + cod_prod + ", id_ventas=" + id_ventas + '}';
	}

	public int getId_ventas() {
		return id_ventas;
	}

	public void setId_ventas(int id_ventas) {
		this.id_ventas = id_ventas;
	}
	
	

	public int getId_det_ventas() {
		return id_det_ventas;
	}

	public void setId_det_ventas(int id_det_ventas) {
		this.id_det_ventas = id_det_ventas;
	}

	public String getNom_prod() {
		return nom_prod;
	}

	public void setNom_prod(String nom_prod) {
		this.nom_prod = nom_prod;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrec_unit() {
		return prec_unit;
	}

	public void setPrec_unit(double prec_unit) {
		this.prec_unit = prec_unit;
	}

	public double getPrec_vent() {
		return prec_vent;
	}

	public void setPrec_vent(double prec_vent) {
		this.prec_vent = prec_vent;
	}

	public String getCod_prod() {
		return cod_prod;
	}

	public void setCod_prod(String cod_prod) {
		this.cod_prod = cod_prod;
	}
	
	
}
