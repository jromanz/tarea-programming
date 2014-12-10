package com.mycompany.tmodel;

import com.mycompany.clases.Cliente;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ClienteTableModel extends AbstractTableModel{
    
    private static final int DNI_COL = 0;
    private static final int APE_COL = 1;
    private static final int NOM_COL = 2;
    private static final int TEL_COL = 3;
    private static final int DOM_COL = 4;
    
    private String[] columnNames = {"DNI","APELLIDOS","NOMBRES","TELEFONO","DIRECCION"};
    private List<Cliente> clientes;
    
    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente tempCliente = clientes.get(rowIndex);
        switch(columnIndex){
            case DNI_COL:
                return tempCliente.getDni();
            case APE_COL:
                return tempCliente.getApellidos();
            case NOM_COL:
                return tempCliente.getNombres();
            case TEL_COL:
                return tempCliente.getTelefono();
            case DOM_COL:
                return tempCliente.getDomicilio();
            default:
                return tempCliente.getApellidos();
        }
        
    }
    
    public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
    }
    
}
