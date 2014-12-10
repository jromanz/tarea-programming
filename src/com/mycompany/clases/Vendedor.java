package com.mycompany.clases;

import java.sql.Date;

public class Vendedor extends Persona {

    private String codigoVendedor;
    private Date fechaAlta;
	
    public Vendedor(String dni, String apellidos, String nombres, String telefono, String domicilio, String codigoVendedor, Date fechaAlta) {
        super(dni,apellidos,nombres,telefono,domicilio);
        this.codigoVendedor = codigoVendedor;
        this.fechaAlta = fechaAlta;
    }

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "dni=" + super.getDni() + ", apellidos=" + super.getApellidos() + ", nombres=" + super.getNombres() + ", telefono=" + super.getTelefono() + ", domicilio=" + super.getDomicilio() + ", codigoVendedor=" + codigoVendedor + ", fechaAlta=" + fechaAlta + '}';
    }

    
    
}
