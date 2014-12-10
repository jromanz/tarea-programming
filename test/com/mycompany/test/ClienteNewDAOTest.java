package com.mycompany.test;

import com.mycompany.clases.Cliente;
import com.mycompany.service.ClienteService;
import com.mycompany.service.impl.ClienteServiceImpl;

public class ClienteNewDAOTest {
    public static void main(String[] args) throws Exception {
        
        Cliente cliente = new Cliente("91738246","Veloso Goias","Mariane","2598900","Av. Los Nogales 440 Lima 30");
        
        ClienteService clienteService = new ClienteServiceImpl();
        clienteService.addCliente(cliente);
        
        clienteService = new ClienteServiceImpl();
        for (Cliente elem : clienteService.getAllClientes()) {
            System.out.println(elem);
        }
                
    }
}
