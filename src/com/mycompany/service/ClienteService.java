package com.mycompany.service;

import com.mycompany.clases.Cliente;
import java.util.List;

public interface ClienteService{
    
    public void addCliente(Cliente cliente);
    public void updateCliente(Cliente cliente);
    public void removeCliente(String codigo);
    public Cliente findById(String codigo);
    public List<Cliente> getAllClientes() throws Exception;
    
    
}
