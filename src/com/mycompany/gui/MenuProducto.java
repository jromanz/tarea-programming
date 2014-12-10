package com.mycompany.gui;

import com.mycompany.clases.Producto;
import com.mycompany.service.ProductoService;
import com.mycompany.service.impl.ProductoServiceImpl;
import com.mycompany.tmodel.ProductoTableModel;
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

public class MenuProducto extends JFrame {

    private JLabel lblTitulo;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblCantidad;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtCantidad;

    private JButton btnIngresar;
    private JButton btnCancelar;
    private JButton btnEliminar;
    private JButton btnActualizar;

    private JTable tblListado;
    private JScrollPane scrollPane;

    private ProductoService productoService;
    List<Producto> productos;
    private String codigoSelected;

    public MenuProducto() throws Exception {
        setTitle("Registro Productos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        iniciandoComponentes();
        iniciandoGUI();
    }

    private void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
        txtCodigo.requestFocus();
    }

    private void iniciandoComponentes() {
        lblTitulo = new JLabel("Ingreso de Productos");
        lblCodigo = new JLabel("Codigo: ");
        lblNombre = new JLabel("Nombre: ");
        lblPrecio = new JLabel("Precio: ");
        lblCantidad = new JLabel("Cantidad: ");
        txtCodigo = new JTextField(20);
        txtNombre = new JTextField(20);
        txtPrecio = new JTextField(20);
        txtCantidad = new JTextField(20);
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
        productos = new ProductoServiceImpl().getAllProductos();
        ProductoTableModel model = new ProductoTableModel(productos);
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
        panel.add(lblCodigo, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(txtCodigo, gbc);

        gbc.gridwidth = 1;
        panel.add(lblNombre, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(txtNombre, gbc);

        gbc.gridwidth = 1;
        panel.add(lblPrecio, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(txtPrecio, gbc);

        gbc.gridwidth = 1;
        panel.add(lblCantidad, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(txtCantidad, gbc);

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
                codigoSelected = (String) target.getValueAt(row, 0);
                String nomTemp = (String) target.getValueAt(row, 1);
                double preTemp = (double) target.getValueAt(row, 2);
                int canTemp = (int) target.getValueAt(row, 3);
                txtCodigo.setText(codigoSelected);
                txtNombre.setText(nomTemp);
                txtPrecio.setText(String.valueOf(preTemp));
                txtCantidad.setText(String.valueOf(canTemp));
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
                String codigo = txtCodigo.getText();
                String nombre = txtNombre.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                int cantidad = Integer.parseInt(txtCantidad.getText());
                //a revisar
                if (codigo.equals("") || nombre.equals("") || precio == 0 || cantidad <= 0) {
                    JOptionPane.showMessageDialog(null, "No ingrese valores nulos");
                    txtCodigo.setFocusable(true);
                } else {
                    int n = JOptionPane.showConfirmDialog(
                            getParent(),
                            "Deseas Actualizar Producto con codigo: " + codigoSelected,
                            "Actualizar Registro Producto",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (n == 0) {
                        try {
                            productoService = new ProductoServiceImpl();
                            Producto producto = new Producto(codigo, nombre, precio, cantidad);
                            productoService.updateProducto(producto);
                            productoService = new ProductoServiceImpl();
                            tblListado.setModel(new ProductoTableModel(productoService.getAllProductos()));
                        } catch (SQLException ex) {
                            Logger.getLogger(MenuProducto.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuProducto.class.getName()).log(Level.SEVERE, null, ex);
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
                                "Deseas eliminar Producto con codigo: " + codigoSelected,
                                "Eliminar Registro Producto",
                                JOptionPane.YES_NO_OPTION
                        );
                        if (n == 0) {
                            try {
                                productoService = new ProductoServiceImpl();
                                productoService.removeProducto(codigoSelected);
                                productoService = new ProductoServiceImpl();
                                tblListado.setModel(new ProductoTableModel(productoService.getAllProductos()));

                            } catch (SQLException ex) {
                                Logger.getLogger(MenuProducto.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(MenuProducto.class.getName()).log(Level.SEVERE, null, ex);
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
                        String codigo = txtCodigo.getText();
                        String nombre = txtNombre.getText();
                        String precioCad = txtPrecio.getText();
                        String cantidadCad = txtCantidad.getText();

                        if (codigo.equals("") || nombre.equals("") || precioCad.equals("") || cantidadCad.equals("")) {
                            JOptionPane.showMessageDialog(null, "No ingrese valores nulos");
                        } else {
                            try {
                                double precio = Double.parseDouble(txtPrecio.getText());
                                int cantidad = Integer.parseInt(txtCantidad.getText());

                                productoService = new ProductoServiceImpl();
                                Producto producto = new Producto(codigo, nombre, precio, cantidad);
                                productoService.addProducto(producto);
                                JOptionPane.showMessageDialog(null, "Producto registrado!");
                                productoService = new ProductoServiceImpl();
                                tblListado.setModel(new ProductoTableModel(productoService.getAllProductos()));
                                limpiarFormulario();
                            } catch (SQLException ex) {
                                Logger.getLogger(MenuProducto.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(MenuProducto.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }

                }
        );
    }

    private void camposListener() {

    }
}
