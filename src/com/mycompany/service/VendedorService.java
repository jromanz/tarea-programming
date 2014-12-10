package com.mycompany.service;

import com.mycompany.clases.Vendedor;
import java.util.List;

public interface VendedorService {

    public void addVendedor(Vendedor vendedor);
    public void updateVendedor(Vendedor vendedor);
    public void removeVendedor(String codigo);
    public Vendedor findById(String codigo);
    public List<Vendedor> getAllVendedores();
}
