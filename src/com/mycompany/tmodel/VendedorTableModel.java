package com.mycompany.tmodel;

import com.mycompany.clases.Vendedor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class VendedorTableModel extends AbstractTableModel {

    private static final int DNI_COL = 0;
    private static final int APE_COL = 1;
    private static final int NOM_COL = 2;
    private static final int TEL_COL = 3;
    private static final int DOM_COL = 4;
    private static final int COD_COL = 5;
    private static final int FEC_COL = 6;
    private final String[] columnNames = {"DNI", "APELLIDOS", "NOMBRES", "TELEFONO", "DOMICILIO", "CODIGO VENDEDOR","FECHA ALTA"};
    List<Vendedor> vendedores;

    public VendedorTableModel(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    @Override
    public int getRowCount() {
        return vendedores.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vendedor tempVendedor = vendedores.get(rowIndex);
        switch(columnIndex){
            case DNI_COL:
                return tempVendedor.getDni();
            case APE_COL:
                return tempVendedor.getApellidos();
            case NOM_COL:
                return tempVendedor.getNombres();
            case TEL_COL:
                return tempVendedor.getTelefono();
            case DOM_COL:
                return tempVendedor.getDomicilio();
            case COD_COL:
                return tempVendedor.getCodigoVendedor();
            case FEC_COL:
                return tempVendedor.getFechaAlta();
            default:
                return tempVendedor.getDni();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
