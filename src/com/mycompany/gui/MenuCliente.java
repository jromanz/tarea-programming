package com.mycompany.gui;

import com.mycompany.clases.Cliente;
import com.mycompany.service.ClienteService;
import com.mycompany.service.impl.ClienteServiceImpl;
import com.mycompany.tmodel.ClienteTableModel;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MenuCliente extends JFrame {

    private JLabel lblTitulo;
    private JLabel lblDni;
    private JLabel lblApellidos;
    private JLabel lblNombres;
    private JLabel lblTelefono;
    private JLabel lblDomicilio;
    private JTextField txtDni;
    private JTextField txtApellidos;
    private JTextField txtNombres;
    private JTextField txtTelefono;
    private JTextField txtDomicilio;

    private JButton btnIngresar;
    private JButton btnCancelar;
    private JButton btnEliminar;
    private JButton btnActualizar;

    private JTable tblListado;
    private JScrollPane scrollPane;

    private ClienteService clienteService;
    List<Cliente> clientes;
    private String dniSelected;

    public MenuCliente() throws Exception {
        setTitle("Registro Clientes");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        iniciandoComponentes();
        iniciandoGUI();
    }

    private void limpiarFormulario() {
        txtDni.setText("");
        txtApellidos.setText("");
        txtNombres.setText("");
        txtTelefono.setText("");
        txtDomicilio.setText("");
        txtDni.requestFocus();
    }

    private void iniciandoComponentes() {
        lblTitulo = new JLabel("Ingreso de Cliente");
        lblDni = new JLabel("DNI: ");
        lblApellidos = new JLabel("Apellidos: ");
        lblNombres = new JLabel("Nombres: ");
        lblTelefono = new JLabel("Telefono: ");
        lblDomicilio = new JLabel("Domicilio: ");
        txtDni = new JTextField(20);
        txtApellidos = new JTextField(20);
        txtNombres = new JTextField(20);
        txtTelefono = new JTextField(20);
        txtDomicilio = new JTextField(20);
        btnIngresar = new JButton("Ingresar");
        btnCancelar = new JButton("Cancelar");
        btnEliminar = new JButton("Eliminar");
        btnActualizar = new JButton("Actualizar");
        btnIngresar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnEliminar.setEnabled(false);
        btnActualizar.setEnabled(false);
    }

    private void iniciandoJTable() throws Exception {
        clientes = new ClienteServiceImpl().getAllClientes();
        ClienteTableModel model = new ClienteTableModel(clientes);
        tblListado = new JTable();
        tblListado.setModel(model);
    }

    private void iniciandoGUI() throws Exception {

        Container container = getContentPane();
        BoxLayout border1 = new BoxLayout(container, BoxLayout.PAGE_AXIS);
        container.setLayout(border1);

        iniciandoJTable();
        scrollPane = new JScrollPane(tblListado);

        JPanel panel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        panel.setBackground(Color.red);
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(gbl);

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(lblTitulo, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        panel.add(lblDni, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(txtDni, gbc);

        gbc.gridwidth = 1;
        panel.add(lblApellidos, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(txtApellidos, gbc);

        gbc.gridwidth = 1;
        panel.add(lblNombres, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(txtNombres, gbc);

        gbc.gridwidth = 1;
        panel.add(lblTelefono, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(txtTelefono, gbc);

        gbc.gridwidth = 1;
        panel.add(lblDomicilio, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(txtDomicilio, gbc);

        FlowLayout flow1 = new FlowLayout();
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.YELLOW);
        panelBotones.setLayout(flow1);
        panelBotones.add(btnIngresar);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);

        container.add(panel);
        container.add(panelBotones);
        container.add(scrollPane);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                btnIngresar.setEnabled(true);
                btnCancelar.setEnabled(true);
                btnEliminar.setEnabled(false);
                btnActualizar.setEnabled(false);
                limpiarFormulario();
            }
        });

        tblListado.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                btnIngresar.setEnabled(false);
                btnCancelar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnActualizar.setEnabled(true);
                JTable target = (JTable) e.getSource();
                int row = target.getSelectedRow();
                dniSelected = (String) target.getValueAt(row, 0);
                String apeTemp = (String) target.getValueAt(row, 1);
                String nomTemp = (String) target.getValueAt(row, 2);
                String telTemp = (String) target.getValueAt(row, 3);
                String domTemp = (String) target.getValueAt(row, 4);
                txtDni.setText(dniSelected);
                txtApellidos.setText(apeTemp);
                txtNombres.setText(nomTemp);
                txtTelefono.setText(telTemp);
                txtDomicilio.setText(domTemp);
            }
        });

        camposListener();
        botonesListener();
    }

    private void botonesListener() {

        btnCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                btnIngresar.setEnabled(true);
                btnActualizar.setEnabled(false);
                btnEliminar.setEnabled(false);
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = txtDni.getText();
                String apellidos = txtApellidos.getText();
                String nombres = txtNombres.getText();
                String telefono = txtTelefono.getText();
                String domicilio = txtDomicilio.getText();

                if (dni.equals("") || apellidos.equals("") || nombres.equals("") || telefono.equals("") || domicilio.equals("")) {
                    JOptionPane.showMessageDialog(null, "No ingrese valores nulos");
                    txtDni.setFocusable(true);
                } else {
                    int n = JOptionPane.showConfirmDialog(
                            getParent(),
                            "Deseas Actualizar Cliente con dni: " + dniSelected,
                            "Actualizar Registro Cliente",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (n == 0) {
                        try {
                            clienteService = new ClienteServiceImpl();
                            Cliente cliente = new Cliente(dni, apellidos, nombres, telefono, domicilio);
                            clienteService.updateCliente(cliente);
                            clienteService = new ClienteServiceImpl();
                            tblListado.setModel(new ClienteTableModel(clienteService.getAllClientes()));
                        } catch (SQLException ex) {
                            Logger.getLogger(MenuCliente.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        limpiarFormulario();
                        btnIngresar.setEnabled(true);
                        btnCancelar.setEnabled(true);
                        btnActualizar.setEnabled(false);
                        btnEliminar.setEnabled(false);
                    }
                }
            }
        }
        );

        btnEliminar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        int n = JOptionPane.showConfirmDialog(
                                getParent(),
                                "Deseas eliminar Cliente con dni: " + dniSelected,
                                "Eliminar Registro Cliente",
                                JOptionPane.YES_NO_OPTION
                        );
                        if (n == 0) {
                            try {
                                clienteService = new ClienteServiceImpl();
                                clienteService.removeCliente(dniSelected);
                                clienteService = new ClienteServiceImpl();
                                tblListado.setModel(new ClienteTableModel(clienteService.getAllClientes()));

                            } catch (SQLException ex) {
                                Logger.getLogger(MenuCliente.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(MenuCliente.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            limpiarFormulario();
                        }
                    }
                }
        );

        btnIngresar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        String dni = txtDni.getText();
                        String apellidos = txtApellidos.getText();
                        String nombres = txtNombres.getText();
                        String telefono = txtTelefono.getText();
                        String domicilio = txtDomicilio.getText();

                        if (dni.equals("") || apellidos.equals("") || nombres.equals("") || telefono.equals("") || domicilio.equals("")) {
                            JOptionPane.showMessageDialog(null, "No ingrese valores nulos");
                        } else {
                            try {
                                clienteService = new ClienteServiceImpl();
                                Cliente cliente = new Cliente(dni, apellidos, nombres, telefono, domicilio);
                                clienteService.addCliente(cliente);
                                JOptionPane.showMessageDialog(null, "Cliente registrado!");
                                clienteService = new ClienteServiceImpl();
                                tblListado.setModel(new ClienteTableModel(clienteService.getAllClientes()));
                                limpiarFormulario();
                            } catch (SQLException ex) {
                                Logger.getLogger(MenuCliente.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(MenuCliente.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }

                }
        );
    }

    private void camposListener() {

    }
}
