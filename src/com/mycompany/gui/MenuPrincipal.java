package com.mycompany.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPrincipal extends JFrame {

    private final JLabel lblTitulo;
    private final JButton btnCliente;
    private final JButton btnVendedor;
    private final JButton btnProducto;
    private final JButton btnVentas;

    private final MenuCliente menuCliente;
    private final MenuVendedor menuVendedor;
    private final MenuProducto menuProducto;
    private final MenuVentas menuVentas;
    
	
    public MenuPrincipal() throws Exception {
        lblTitulo = new JLabel("REGISTRO DE VENTA PRODUCTOS");
        btnCliente = new JButton("Clientes");
        btnVendedor = new JButton("Vendedores");
        btnProducto = new JButton("Productos");
        btnVentas = new JButton("Ventas");

        this.menuCliente = new MenuCliente();
        this.menuVendedor = new MenuVendedor();
        this.menuProducto = new MenuProducto();
        this.menuVentas = new MenuVentas();
        setTitle("Java 7 Programming");
        setSize(350, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciandoGUI();
    }

    private void iniciandoGUI() {
        Container container = getContentPane();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(lblTitulo);
        panel.add(btnCliente);
        panel.add(btnVendedor);
        panel.add(btnProducto);
        panel.add(btnVentas);
        container.add(panel);

        btnCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuCliente.setVisible(true);
            }
        });

        btnVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuVendedor.setVisible(true);
            }
        });

        btnProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuProducto.setVisible(true);
            }
        });

        btnVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuVentas.setVisible(true);
            }
        });
        
    }

}
