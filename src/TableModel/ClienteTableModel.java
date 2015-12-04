/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Cliente;

/**
 * @author lhries
 */
public class ClienteTableModel extends AbstractTableModel {

    private String header[];
    private List<Cliente> clientes;

    public ClienteTableModel() {
        this.header = new String[]{ "ID" , "Matrícula", "Nome", "Telefone"};
        this.clientes = new ArrayList<Cliente>();
    }

    public ClienteTableModel(String[] header, List<Cliente> pessoas) {
        this.header = header;
        this.clientes = pessoas;

    }

    @Override
    public int getRowCount() {
        return (this.clientes.size());
    }

    @Override
    public int getColumnCount() {
        return (4);
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return (this.clientes.get(rowIndex).getId());
        } else if (columnIndex == 1) {
            return (this.clientes.get(rowIndex).getMatricula());
        } else if (columnIndex == 2) {
            return (this.clientes.get(rowIndex).getNome());
        } else if (columnIndex == 3) {
            return (this.clientes.get(rowIndex).getTelefone());
        } else {
            return null;
        }
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getCliente(int linha) {
        return (clientes.get(linha));
    }
}
