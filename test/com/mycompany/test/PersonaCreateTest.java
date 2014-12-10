package com.mycompany.test;

import com.mycompany.clases.Cliente;
import com.mycompany.clases.Persona;
import com.mycompany.clases.Producto;
import com.mycompany.clases.Vendedor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class PersonaCreateTest {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fechaEnString = "19-09-2014";
        java.util.Date fecha = sdf.parse(fechaEnString);
        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
        Persona persona1 = new Cliente("41091712", "Román Zapata", "Julio Cesar", "2598966", "Av. Juan Velasco 1340 Lima 42");
        Persona persona2 = new Vendedor("41091713", "Salguero Coletti", "Gilda Licet", "2598966", "Av. Juan Velasco 1340 Lima 42", "VEN-0001", fechaSql);
        
        System.out.println(persona1);
        System.out.println(persona2);
        
        Producto producto1 = new Producto("P0001","Monitores 19 pulgadas",200.10,50);
        System.out.println(producto1);

    }
}
