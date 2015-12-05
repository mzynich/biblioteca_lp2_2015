/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Emprestimo;

/**
 * @author lhries
 */
public class EmprestimoTableModel extends AbstractTableModel {

    private String header[];
    private List<Emprestimo> lista;

    public EmprestimoTableModel() {
        this.header = new String[]{"ID", "ISBN", "Nome do livro", "Data de empréstimo", "Data de devolução"};
        this.lista = new ArrayList<Emprestimo>();
    }

    public EmprestimoTableModel(String[] header, List<Emprestimo> lista) {
        this.header = header;
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return (this.lista.size());
    }

    @Override
    public int getColumnCount() {
        return (5);
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return (this.lista.get(rowIndex).getId());
            case 1:
                return (this.lista.get(rowIndex).getItemLivro().getLivro().getISBN());
            case 2:
                return (this.lista.get(rowIndex).getItemLivro().getLivro().getNome());
            case 3:
                return (this.lista.get(rowIndex).getDataEmprestimo());
            case 4:
                return (this.lista.get(rowIndex).getDataDevolucao());
            default:
                return null;
        }
    }

    public void setLista(List<Emprestimo> lista) {
        this.lista = lista;
    }

    public Emprestimo getEmprestimo(int linha) {
        return (lista.get(linha));
    }
}
