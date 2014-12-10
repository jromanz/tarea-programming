package com.mycompany.gui;

import com.mycompany.clases.Vendedor;
import com.mycompany.service.VendedorService;
import com.mycompany.service.impl.VendedorServiceImpl;
import com.mycompany.tmodel.VendedorTableModel;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class MenuVendedor extends JFrame {

    private JLabel lblTitulo;

    private JLabel lblDni;
    private JLabel lblApellidos;
    private JLabel lblNombres;
    private JLabel lblTelefono;
    private JLabel lblDomicilio;
    private JLabel lblCodVendedor;
    private JLabel lblFechaAlta;

    private JTextField txtDni;
    private JTextField txtApellidos;
    private JTextField txtNombres;
    private JTextField txtTelefono;
    private JTextField txtDomicilio;
    private JTextField txtCodigoVendedor;

    private JSpinner spFechaAlta;
    private SpinnerDateModel spinnerModel;
    private JSpinner.DateEditor editor;

    private JButton btnIngresar;
    private JButton btnCancelar;
    private JButton btnEliminar;
    private JButton btnActualizar;

    private JTable tblListado;
    private JScrollPane scrollPane;

    private VendedorService vendedorService;
    List<Vendedor> vendedores;
    private String dniSelected;

    public MenuVendedor() throws Exception {
        setTitle("Registro Vendedores");
        setSize(600, 400);
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
        txtCodigoVendedor.setText("");

        txtDni.requestFocus();
    }

    private void iniciandoComponentes() {
        lblTitulo = new JLabel("Ingreso de Vendedores");
        lblDni = new JLabel("DNI: ");
        lblApellidos = new JLabel("Apellidos: ");
        lblNombres = new JLabel("Nombres: ");
        lblTelefono = new JLabel("Telefono: ");
        lblDomicilio = new JLabel("Domicilio: ");
        lblCodVendedor = new JLabel("Cod. Vend.: ");
        lblFechaAlta = new JLabel("Fecha Alta: ");
        txtDni = new JTextField(20);
        txtApellidos = new JTextField(20);
        txtNombres = new JTextField(20);
        txtTelefono = new JTextField(20);
        txtDomicilio = new JTextField(20);
        txtCodigoVendedor = new JTextField(20);

        spinnerModel = new SpinnerDateModel(new Date(), null, null, Calendar.MONTH);
        spFechaAlta = new JSpinner(spinnerModel);
        editor = new JSpinner.DateEditor(spFechaAlta, "dd-MMM-yyyy");
        spFechaAlta.setEditor(editor);

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
        vendedores = new VendedorServiceImpl().getAllVendedores();
        VendedorTableModel model = new VendedorTableModel(vendedores);
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

        gbc.gridwidth = 1;
        panel.add(lblCodVendedor, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(txtCodigoVendedor, gbc);

        gbc.gridwidth = 1;
        panel.add(lblFechaAlta, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(spFechaAlta, gbc);

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
                String codTemp = (String) target.getValueAt(row, 5);
                String fecTemp = String.valueOf(target.getValueAt(row, 6));

                txtDni.setText(dniSelected);
                txtApellidos.setText(apeTemp);
                txtNombres.setText(nomTemp);
                txtTelefono.setText(telTemp);
                txtDomicilio.setText(domTemp);
                txtCodigoVendedor.setText(codTemp);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateTemp = null;
                try {
                    dateTemp = sdf.parse(fecTemp);
                } catch (ParseException ex) {
                    Logger.getLogger(MenuVendedor.class.getName()).log(Level.SEVERE, null, ex);
                }
                spFechaAlta.setValue(dateTemp);
            }
        });

        camposListener();
        botonesListener();
    }

    private void botonesListener() {

        btnIngresar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        String codigo = txtDni.getText();
                        String apellidos = txtApellidos.getText();
                        String nombres = txtNombres.getText();
                        String telefono = txtTelefono.getText();
                        String domicilio = txtDomicilio.getText();
                        String codigoVendedor = txtCodigoVendedor.getText();
                        java.sql.Date fechaAlta = new java.sql.Date(((Date) spFechaAlta.getValue()).getTime());
                        if (codigo.equals("") || apellidos.equals("") || nombres.equals("") || telefono.equals("") || domicilio.equals("") || codigoVendedor.equals("")) {
                            JOptionPane.showMessageDialog(null, "No ingrese valores nulos");
                        } else {
                            try {
                                vendedorService = new VendedorServiceImpl();
                                Vendedor vendedor = new Vendedor(codigo, apellidos, nombres, telefono, domicilio, codigoVendedor, fechaAlta);
                                vendedorService.addVendedor(vendedor);
                                JOptionPane.showMessageDialog(null, "Vendedor registrado!");
                                vendedorService = new VendedorServiceImpl();
                                tblListado.setModel(new VendedorTableModel(vendedorService.getAllVendedores()));
                                limpiarFormulario();
                            } catch (Exception ex) {
                                Logger.getLogger(MenuVendedor.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }

                }
        );

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = txtDni.getText();
                String apellidos = txtApellidos.getText();
                String nombres = txtNombres.getText();
                String telefono = txtTelefono.getText();
                String domicilio = txtDomicilio.getText();
                String codigoVendedor = txtCodigoVendedor.getText();
                java.sql.Date fechaAlta = new java.sql.Date(((Date) spFechaAlta.getValue()).getTime());

                if (codigo.equals("") || apellidos.equals("") || nombres.equals("") || telefono.equals("") || domicilio.equals("") || codigoVendedor.equals("")) {
                    JOptionPane.showMessageDialog(null, "No ingrese valores nulos");
                    txtDni.setFocusable(true);
                } else {
                    int n = JOptionPane.showConfirmDialog(
                            getParent(),
                            "Deseas Actualizar Vendedor con DNI: " + codigo,
                            "Actualizar Registro Vendedor",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (n == 0) {
                        try {
                            vendedorService = new VendedorServiceImpl();
                            Vendedor vendedor = new Vendedor(codigo, apellidos, nombres, telefono, domicilio, codigoVendedor, fechaAlta);
                            vendedorService.updateVendedor(vendedor);
                            vendedorService = new VendedorServiceImpl();
                            tblListado.setModel(new VendedorTableModel(vendedorService.getAllVendedores()));
                        } catch (Exception ex) {
                            Logger.getLogger(MenuVendedor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        limpiarFormulario();
                        spFechaAlta.setValue(new Date());
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
                                "Deseas eliminar Vendedor con DNI: " + dniSelected,
                                "Eliminar Registro Vendedor",
                                JOptionPane.YES_NO_OPTION
                        );
                        if (n == 0) {
                            try {
                                vendedorService = new VendedorServiceImpl();
                                vendedorService.removeVendedor(dniSelected);
                                vendedorService = new VendedorServiceImpl();
                                tblListado.setModel(new VendedorTableModel(vendedorService.getAllVendedores()));
                            } catch (Exception ex) {
                                Logger.getLogger(MenuVendedor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            limpiarFormulario();
                        }
                    }
                }
        );

        btnCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                spFechaAlta.setValue(new Date());
                btnIngresar.setEnabled(true);
                btnActualizar.setEnabled(false);
                btnEliminar.setEnabled(false);
            }
        });
    }

    private void camposListener() {

    }
}
