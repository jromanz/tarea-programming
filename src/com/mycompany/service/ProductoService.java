package com.mycompany.service;

import com.mycompany.clases.Producto;
import java.util.List;

public interface ProductoService {
    public void addProducto(Producto producto);
    public void updateProducto(Producto producto);
    public void removeProducto(String codigo);
    public Producto findById(String codigo);
    public List<Producto> getAllProductos();
}
