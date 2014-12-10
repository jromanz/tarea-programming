package com.mycompany.test;

import com.mycompany.clases.Cliente;
import com.mycompany.service.ClienteService;
import com.mycompany.service.impl.ClienteServiceImpl;

public class ClienteDeleteDAOTest {

    public static void main(String[] args) throws Exception {
        Cliente cliente = new Cliente("99999999","Veloso Goias","Mariane Divina","2598900","Av. Los Nogales 440 Lima 30");
        ClienteService clienteService = new ClienteServiceImpl();
        clienteService.removeCliente(cliente.getDni());
    }
}
