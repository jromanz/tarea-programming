package com.mycompany.gui;

import com.mycompany.clases.Cliente;
import com.mycompany.clases.DetalleVentas;
import com.mycompany.clases.Producto;
import com.mycompany.clases.Ventas;
import com.mycompany.service.impl.ClienteServiceImpl;
import com.mycompany.service.impl.ProductoServiceImpl;
import com.mycompany.service.impl.VendedorServiceImpl;
import com.mycompany.service.impl.VentasServiceImpl;
import com.mycompany.tmodel.ClienteTableModel;
import com.mycompany.tmodel.DetalleVentasTableModel;
import com.mycompany.tmodel.ProductoTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

public class MenuVentas extends JFrame {

	private static final double IGV = 0.19;

	private final JLabel lblSubTotal = new JLabel("Sub. Total: ");
	private final JLabel lblIGV = new JLabel("IGV: ");
	private final JLabel lblTotalAPagar = new JLabel("Total a pagar: ");
	private JTextField txtSubTotal;
	private JTextField txtIGV;
	private JTextField txtTotalAPagar;
	private JLabel lblRUC;
	private JLabel lblNro;
	private JTextField txtRUC;

	private JLabel lblNombres;
	private JTextField txtNombres;
	private JButton btnCliente;
	private JLabel lblDni;
	private JTextField txtDni;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JLabel lblDireccion;
	private JTextField txtDireccion;
	private JLabel lblProducto;
	private JButton btnProducto;
	private JLabel lblVendedor;
	private JComboBox cmbVendedor;

	private HashMap<String, String> vendedoresCombo;

	private JTable tblDetalleVentas;
	private List<DetalleVentas> detalleVentas;
	private DetalleVentasTableModel model;
	private int rowSelected;
	private Timestamp fecha;
	private JButton botonEliminar;
	private JButton botonSalir;
	private JButton botonAceptar;
	private JButton botonVenta;

	private String dniVendedor;

	public MenuVentas() {
		setTitle("Registro Ventas");
		setSize(650, 550);
		setResizable(false);
		setLocationRelativeTo(null);
		iniciandoGUI();
	}

	private void iniciandoComponentes() {
		lblNombres = new JLabel("Nombre:");
		txtNombres = new JTextField();
		txtNombres.setPreferredSize(new Dimension(240, 25));
		btnCliente = new JButton("Buscar");
		lblDni = new JLabel("DNI:");
		txtDni = new JTextField();
		txtDni.setPreferredSize(new Dimension(70, 25));
		lblCodigo = new JLabel("Telefono:");
		txtCodigo = new JTextField();
		txtCodigo.setPreferredSize(new Dimension(70, 25));
		lblFecha = new JLabel("Fecha:");
		txtFecha = new JTextField();
		txtFecha.setPreferredSize(new Dimension(70, 25));
		lblDireccion = new JLabel("Dirección:");
		txtDireccion = new JTextField();
		txtDireccion.setPreferredSize(new Dimension(300, 25));
		lblProducto = new JLabel("Productos:");
		btnProducto = new JButton("Buscar");
		lblVendedor = new JLabel("Vendedor:");
		cmbVendedor = new JComboBox();

		botonAceptar = new JButton("Realizar Calculo");
		botonVenta = new JButton("Realizar venta");
		botonSalir = new JButton("Salir");
		botonEliminar = new JButton("Eliminar");

		tblDetalleVentas = new JTable();
		detalleVentas = new ArrayList<DetalleVentas>();
		model = new DetalleVentasTableModel(detalleVentas);
		rowSelected = -1;
		dniVendedor = "";
	}

	private void iniciandoGUI() {
		Container container = getContentPane();

		iniciandoComponentes();

		JPanel panelCabecera = new JPanel();
		panelCabecera.setPreferredSize(new Dimension(620, 210));
		panelCabecera.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
		panelCabecera.setBackground(Color.LIGHT_GRAY);

		JPanel panelIzquierda = new JPanel();
		panelIzquierda.setBackground(Color.cyan);
		panelIzquierda.setPreferredSize(new Dimension(400, 190));
		FlowLayout flowCabecera = new FlowLayout(FlowLayout.TRAILING, 10, 10);
		panelIzquierda.setLayout(flowCabecera);
		panelIzquierda.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelIzquierda.add(lblNombres);
		panelIzquierda.add(txtNombres);
		panelIzquierda.add(btnCliente);
		panelIzquierda.add(lblDni);
		panelIzquierda.add(txtDni);
		panelIzquierda.add(lblCodigo);
		panelIzquierda.add(txtCodigo);
		panelIzquierda.add(lblFecha);
		panelIzquierda.add(txtFecha);
		panelIzquierda.add(lblDireccion);
		panelIzquierda.add(txtDireccion);
		panelIzquierda.add(lblProducto);
		panelIzquierda.add(btnProducto);
		panelIzquierda.add(lblVendedor);
		panelIzquierda.add(cmbVendedor);

		JPanel panelDerecha = new JPanel();
		panelDerecha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 1), "BOLETA DE VENTA", TitledBorder.CENTER, TitledBorder.TOP));
		((TitledBorder) panelDerecha.getBorder()).setTitleFont(new Font("Verdana", 1, 15));
		panelDerecha.setBackground(Color.magenta);
		panelDerecha.setPreferredSize(new Dimension(190, 190));
		lblRUC = new JLabel("20248454753");
		lblRUC.setFont(new Font("Arial", Font.BOLD, 15));
		lblNro = new JLabel("Nro.:");
		txtRUC = new JTextField();
		txtRUC.setPreferredSize(new Dimension(120, 25));
		txtRUC.setEnabled(false);
		txtRUC.setText("014109171208");
		txtRUC.setHorizontalAlignment(JTextField.CENTER);
		txtRUC.setFont(new Font("Arial", Font.BOLD, 15));
		JPanel panelRUC = new JPanel();
		panelRUC.add(lblNro);
		panelRUC.add(txtRUC);
		panelDerecha.add(lblRUC, BorderLayout.CENTER);
		panelDerecha.add(panelRUC, BorderLayout.CENTER);
		panelCabecera.add(panelIzquierda);
		panelCabecera.add(panelDerecha);

		JPanel panelInt = new JPanel();
		panelInt.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
		panelInt.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		panelInt.add(panelCabecera);

		JPanel panelDetalleVentas = new JPanel();
		panelDetalleVentas.setLayout(new FlowLayout(FlowLayout.LEADING));
		tblDetalleVentas.setModel(model);
		tblDetalleVentas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblDetalleVentas.getColumnModel().getColumn(0).setPreferredWidth(70);
		tblDetalleVentas.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblDetalleVentas.getColumnModel().getColumn(2).setPreferredWidth(70);
		tblDetalleVentas.getColumnModel().getColumn(3).setPreferredWidth(70);
		tblDetalleVentas.getColumnModel().getColumn(4).setPreferredWidth(70);
		JScrollPane scrollPane = new JScrollPane(tblDetalleVentas);
		scrollPane.setPreferredSize(new Dimension(450, 180));
		panelDetalleVentas.add(scrollPane);

		JPanel panelBotonesVentas = new JPanel();
		panelBotonesVentas.setPreferredSize(new Dimension(150, 150));
		GridLayout gridLayoutBtn = new GridLayout(4, 1, 10, 10);
		panelBotonesVentas.setLayout(gridLayoutBtn);
		Dimension dimBotones = new Dimension(100, 25);

		botonAceptar.setPreferredSize(dimBotones);
		botonEliminar.setPreferredSize(dimBotones);
		botonVenta.setPreferredSize(dimBotones);
		botonSalir.setPreferredSize(dimBotones);

		panelBotonesVentas.add(botonAceptar);
		panelBotonesVentas.add(botonEliminar);
		panelBotonesVentas.add(botonVenta);
		panelBotonesVentas.add(botonSalir);

		JPanel panelDetalle = new JPanel();
		panelDetalle.setPreferredSize(new Dimension(500, 200));
		panelDetalle.setLayout(new FlowLayout(FlowLayout.LEADING));
		panelDetalle.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		panelDetalle.add(panelDetalleVentas);
		panelDetalle.add(panelBotonesVentas);

		JPanel panelImportes = new JPanel();
		panelImportes.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		panelImportes.setPreferredSize(new Dimension(300, 100));
		JPanel itemImportes = new JPanel();
		itemImportes.setPreferredSize(new Dimension(200, 80));
		itemImportes.setBorder(BorderFactory.createLineBorder(Color.red, 1));

		txtSubTotal = new JTextField();
		txtIGV = new JTextField();
		txtTotalAPagar = new JTextField();
		txtSubTotal.setText("0.0");
		txtIGV.setText("0.0");
		txtTotalAPagar.setText("0.0");
		txtSubTotal.setEnabled(false);
		txtIGV.setEnabled(false);
		txtTotalAPagar.setEnabled(false);
		txtSubTotal.setPreferredSize(new Dimension(70, 20));
		txtIGV.setPreferredSize(new Dimension(70, 20));
		txtTotalAPagar.setPreferredSize(new Dimension(70, 20));

		GridBagLayout gbl1 = new GridBagLayout();
		itemImportes.setBackground(Color.red);
		GridBagConstraints gbc = new GridBagConstraints();
		itemImportes.setLayout(gbl1);

		gbc.anchor = GridBagConstraints.NORTH;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		itemImportes.add(lblSubTotal, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = 1;
		itemImportes.add(lblSubTotal, gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		itemImportes.add(txtSubTotal, gbc);
		gbc.gridwidth = 1;
		itemImportes.add(lblIGV, gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		itemImportes.add(txtIGV, gbc);
		gbc.gridwidth = 1;
		itemImportes.add(lblTotalAPagar, gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		itemImportes.add(txtTotalAPagar, gbc);

		panelImportes.add(itemImportes);

		JPanel panelInterno = new JPanel();
		panelInterno.setLayout(new BoxLayout(panelInterno, BoxLayout.PAGE_AXIS));
		JPanel item1 = new JPanel(new BorderLayout());
		JPanel item2 = new JPanel(new BorderLayout());
		JPanel item3 = new JPanel(new BorderLayout());
		item1.add(panelInt, BorderLayout.NORTH);
		item2.add(panelDetalle, BorderLayout.NORTH);
		item3.add(panelImportes, BorderLayout.NORTH);
		panelInterno.add(item1);
		panelInterno.add(item2);
		panelInterno.add(item3);
		panelInterno.add(Box.createGlue());

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setPreferredSize(new Dimension(650, 600));
		panelPrincipal.add(panelInterno, BorderLayout.PAGE_START);
		container.add(panelPrincipal);

		botonesListener();
		llenarCombo();
	}

	private void botonesListener() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
			}
		});

		botonVenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(
						getParent(),
						"Desea Registrar Venta: ",
						"Registro de Venta",
						JOptionPane.YES_NO_OPTION
				);
				if (n == 0) {
					if (validarDatosVenta()) {
						int i = JOptionPane.showConfirmDialog(
						getParent(),
						"Exitoso",
						"Registro de Venta",
						JOptionPane.YES_NO_OPTION);
						fecha = new Timestamp(System.currentTimeMillis());
						double subTotal = Double.parseDouble(txtSubTotal.getText());
						double igv = Double.parseDouble(txtIGV.getText());
						double total = Double.parseDouble(txtTotalAPagar.getText());
						Ventas ventas = new Ventas(dniVendedor, fecha, subTotal, igv, total, txtDni.getText());
						VentasServiceImpl ventasService = new VentasServiceImpl();
						ventasService.addVenta(ventas, (ArrayList<DetalleVentas>) detalleVentas);
						try {
							ProductoServiceImpl productoService = new ProductoServiceImpl();
							productoService.updateProductosVenta((ArrayList<DetalleVentas>) detalleVentas);
						} catch (SQLException ex) {
							Logger.getLogger(MenuVentas.class.getName()).log(Level.SEVERE, null, ex);
						}
								
						limpiarFormularioVenta();
					}else{
						int j = JOptionPane.showConfirmDialog(
						getParent(),
						"Faltan datos",
						"Registro de Venta",
						JOptionPane.YES_NO_OPTION);
						
					}
				}
			}
		});

		btnCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Window parentWindow = SwingUtilities.windowForComponent(btnCliente);
					JDialog dialog = new JDialog(parentWindow);
					dialog.setPreferredSize(new Dimension(600, 300));
					dialog.setLocationRelativeTo(null);
					dialog.setModal(true);
					dialog.add(jDialogCliente());
					dialog.pack();
					dialog.setVisible(true);
				} catch (SQLException ex) {
					Logger.getLogger(MenuVentas.class.getName()).log(Level.SEVERE, null, ex);
				} catch (Exception ex) {
					Logger.getLogger(MenuVentas.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

		tblDetalleVentas.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				rowSelected = target.getSelectedRow();
			}

		});

		botonSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		botonEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rowSelected != -1) {
					int n = JOptionPane.showConfirmDialog(
							getParent(),
							"Deseas Eliminar Detalle Venta: ",
							"Eliminar Registro Detalle Venta",
							JOptionPane.YES_NO_OPTION
					);
					if (n == 0) {
						model.deleteRow(rowSelected);
						rowSelected = -1;
						calcularTotales();
					}
				}
			}
		});

		tblDetalleVentas.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("tableCellEditor".equals(evt.getPropertyName())) {
					if (tblDetalleVentas.isEditing()) {
						processEditingStarted();
					} else {
						processEditingStopped();
					}
				}
			}

			protected void processEditingStopped() {
				calcularTotales();
			}

			protected void processEditingStarted() {
			}
		});

		btnProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Window parentWindow = SwingUtilities.windowForComponent(btnProducto);
					JDialog dialog = new JDialog(parentWindow);
					dialog.setPreferredSize(new Dimension(600, 300));
					dialog.setLocationRelativeTo(null);
					dialog.setModal(true);
					dialog.add(jDialogProducto());
					dialog.pack();
					dialog.setVisible(true);
				} catch (SQLException ex) {
					Logger.getLogger(MenuVentas.class.getName()).log(Level.SEVERE, null, ex);
				} catch (Exception ex) {
					Logger.getLogger(MenuVentas.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

		cmbVendedor.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent evt) {
				Object item = evt.getItem();
				String valor = "";
				for (Map.Entry<String, String> entrySet : vendedoresCombo.entrySet()) {
					if (item.equals(entrySet.getValue())) {
						valor = entrySet.getKey();
					}
				}
				if (evt.getStateChange() == ItemEvent.SELECTED) {
					dniVendedor = valor;
				}
			}
		});
	}

	private void calcularTotales() {
		double subTotal = 0.0;
		for (DetalleVentas detalleVenta : detalleVentas) {
			subTotal += (detalleVenta.getPrec_unit() * detalleVenta.getCantidad());
		}
		subTotal = (double) Math.round(subTotal * 100) / 100;
		double igv = (double) Math.round(subTotal * IGV * 100) / 100;
		double sum = (double) Math.round((subTotal + igv) * 100) / 100;
		txtSubTotal.setText(String.valueOf(subTotal));
		txtIGV.setText(String.valueOf(igv));
		txtTotalAPagar.setText(String.valueOf(sum));
	}

	private void llenarCombo() {
		VendedorServiceImpl vendedoresService = new VendedorServiceImpl();
		vendedoresCombo = vendedoresService.getVendedoresComboBox();
		for (Map.Entry<String, String> vendedor : vendedoresCombo.entrySet()) {
			String key = vendedor.getKey();
			String value = vendedor.getValue();
			cmbVendedor.addItem(value);
		}
	}

	public JPanel jDialogCliente() throws SQLException, Exception {
		ArrayList<Cliente> clientes = (ArrayList<Cliente>) new ClienteServiceImpl().getAllClientes();
		ClienteTableModel model = new ClienteTableModel(clientes);
		JTable tblListadoDialog = new JTable();
		tblListadoDialog.setModel(model);
		tblListadoDialog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				String dniTemp = (String) target.getValueAt(row, 0);
				String apeTemp = (String) target.getValueAt(row, 1) + " " + (String) target.getValueAt(row, 2);
				String telTemp = (String) target.getValueAt(row, 3);
				String dirTemp = (String) target.getValueAt(row, 4);
				txtDni.setText(dniTemp);
				txtNombres.setText(apeTemp);
				txtCodigo.setText(telTemp);
				txtDireccion.setText(dirTemp);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date hoyDia = new Date();
				String fecha = sdf.format(hoyDia.getTime());
				txtFecha.setText(fecha);
			}
		});
		tblListadoDialog.setModel(model);
		JScrollPane scrollPane = new JScrollPane(tblListadoDialog);
		JPanel pane = new JPanel(new BorderLayout());

		pane.add(scrollPane);
		return pane;
	}

	public JPanel jDialogProducto() throws SQLException, Exception {
		ArrayList<Producto> productos = (ArrayList<Producto>) new ProductoServiceImpl().getAllProductos();
		ProductoTableModel modelTemp = new ProductoTableModel(productos);
		JTable tblListadoProducto = new JTable();
		tblListadoProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					String codTemp = (String) target.getValueAt(row, 0);
					String desTemp = (String) target.getValueAt(row, 1);
					double precTemp = (double) target.getValueAt(row, 2);
					int cantTemp = (int) target.getValueAt(row, 3);
					DetalleVentas detalleVentasTemp = new DetalleVentas(desTemp, 1, precTemp, precTemp, codTemp, 1);
					model.addRow(detalleVentasTemp);
					calcularTotales();
				}
			}
		});
		tblListadoProducto.setModel(modelTemp);
		JScrollPane scrollPane = new JScrollPane(tblListadoProducto);
		JPanel pane = new JPanel(new BorderLayout());

		pane.add(scrollPane);
		return pane;
	}

	private void limpiarFormularioVenta() {
		txtNombres.setText("");
		txtCodigo.setText("");
		txtDni.setText("");
		txtFecha.setText("");
		txtDireccion.setText("");
		detalleVentas.removeAll(detalleVentas);
		model = new DetalleVentasTableModel(detalleVentas);
		tblDetalleVentas.setModel(model);
		tblDetalleVentas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblDetalleVentas.getColumnModel().getColumn(0).setPreferredWidth(70);
		tblDetalleVentas.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblDetalleVentas.getColumnModel().getColumn(2).setPreferredWidth(70);
		tblDetalleVentas.getColumnModel().getColumn(3).setPreferredWidth(70);
		tblDetalleVentas.getColumnModel().getColumn(4).setPreferredWidth(70);
		rowSelected = -1;
	}

	public boolean validarDatosVenta() {
		String nombre = txtNombres.getText();
		String dni = txtDni.getText();
		String telefono = txtCodigo.getText();
		fecha = new Timestamp(System.currentTimeMillis());
		String direccion = txtDireccion.getText();
		String dniTempVendedor = dniVendedor;
		List<DetalleVentas> detalleVentasTemp = detalleVentas;
		return (nombre.equals("") || dni.equals("") || telefono.equals("") || direccion.equals("") || dniTempVendedor.equals("") || detalleVentasTemp == null || detalleVentasTemp.isEmpty()) ? false : true;
	}

}
