package com.mycompany.tmodel;

import com.mycompany.clases.DetalleVentas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DetalleVentasTableModel extends AbstractTableModel {

	private static final int COD_PROD = 0;
	private static final int NOM_PROD = 1;
	private static final int CANTIDAD = 2;
	private static final int PREC_UNIT = 3;
	private static final int IMPORTE = 4;
	private final String[] columnNames = {"CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "IMPORTE"};

	List<DetalleVentas> detalleVentas;

	public DetalleVentasTableModel(List<DetalleVentas> detalleVentas) {
		this.detalleVentas = detalleVentas;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return col == 2 ? true : false;
	}

	public void addRow(DetalleVentas dVentas) {
		detalleVentas.add(dVentas);
		this.fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}

	public void deleteRow(int rowIndex) {
		detalleVentas.remove(rowIndex);
		this.fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return detalleVentas.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		DetalleVentas tempDetalleVentas = detalleVentas.get(rowIndex);
		switch (columnIndex) {
			case NOM_PROD:
				return tempDetalleVentas.getNom_prod();
			case COD_PROD:
				return tempDetalleVentas.getCod_prod();
			case PREC_UNIT:
				return tempDetalleVentas.getPrec_unit();
			case IMPORTE:
				return tempDetalleVentas.getPrec_vent();
			case CANTIDAD:
				return tempDetalleVentas.getCantidad();
			default:
				return tempDetalleVentas.getNom_prod();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		DetalleVentas row = detalleVentas.get(rowIndex);
		switch (columnIndex) {
			case NOM_PROD:
				row.setNom_prod((String) aValue);
				break;
			case COD_PROD:
				row.setCod_prod((String) aValue);
				break;
			case PREC_UNIT:
				row.setPrec_unit((Double) aValue);
				break;
			case IMPORTE:
				row.setPrec_vent((Double) aValue);
				break;
			case CANTIDAD:
				row.setCantidad((Integer) aValue);
				break;
			default:
				System.out.println("***");
		}
		row.setPrec_vent(row.getPrec_unit()*row.getCantidad());
	}
}
