package com.mycompany.clases;

public class Cliente extends Persona {

    public Cliente(String dni, String apellidos, String nombres, String telefono, String domicilio) {
        super(dni, apellidos, nombres, telefono, domicilio);
    }

    @Override
    public String toString() {
        return "Cliente{" + "dni=" + super.getDni() + ", apellidos=" + super.getApellidos() + ", nombres=" + super.getNombres() + ", telefono=" + super.getTelefono() + ", domicilio=" + super.getDomicilio() + '}';
    }

}
