package com.mycompany.test;

import com.mycompany.service.ClienteService;
import com.mycompany.service.impl.ClienteServiceImpl;

public class ClienteFindDAOTest {

    public static void main(String[] args) throws Exception {

        ClienteService clienteService = new ClienteServiceImpl();
        System.out.println(clienteService.findById("99999999"));

    }
}
