package com.mycompany.clases;

public class Persona{
    private String dni;
    private String apellidos;
    private String nombres;
    private String telefono;
    private String domicilio;

    public Persona(String dni, String apellidos, String nombres, String telefono, String domicilio) {
        this.dni = dni;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.telefono = telefono;
        this.domicilio = domicilio;
    }
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    
}
