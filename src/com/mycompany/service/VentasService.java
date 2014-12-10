package com.mycompany.service;

import com.mycompany.clases.Ventas;
import java.util.List;

public interface VentasService {
	public void addVenta(Ventas ventas);
    public void updateVenta(Ventas ventas);
    public void removeVenta(Integer id);
    public Ventas findById(Integer id);
    public List<Ventas> getAllVentas();
    
    
}
