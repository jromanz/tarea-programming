package com.mycompany.tmodel;

import com.mycompany.clases.Producto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProductoTableModel extends AbstractTableModel{
    
    private static final int COD_COL = 0;
    private static final int NOM_COL = 1;
    private static final int PREC_COL = 2;
    private static final int CANT_COL = 3;
    
    String[] columnNames = {"CODIGO","DESCRIPCION","PRECIO","CANTIDAD"};
    private List<Producto> productos;

    public ProductoTableModel(List<Producto> productos) {
        this.productos = productos;
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public int getRowCount() {
        return productos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto tempProducto = productos.get(rowIndex);
        switch(columnIndex){
            case COD_COL:
                return tempProducto.getCodigo();
            case NOM_COL:
                return tempProducto.getNombre();
            case PREC_COL:
                return tempProducto.getPrecio();
            case CANT_COL:
                return tempProducto.getCantidad();
            default:
                return tempProducto.getNombre();
        }
        
    }
    
    @Override
    public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
    }    
    
}
