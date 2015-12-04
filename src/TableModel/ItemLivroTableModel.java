/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Cliente;
import model.ItemLivro;

/**
 * @author lhries
 */
public class ItemLivroTableModel extends AbstractTableModel {

    private String header[];
    private List<ItemLivro> itemLivro;

    public ItemLivroTableModel() {
        this.header = new String[]{"ID", "ISBN", "Nome", "Autor", "Editora", "Ano", "Quantidade Total", "Quantidade Disponível"};
        this.itemLivro = new ArrayList<ItemLivro>();
    }

    public ItemLivroTableModel(String[] header, List<ItemLivro> itemLivro) {
        this.header = header;
        this.itemLivro = itemLivro;

    }

    @Override
    public int getRowCount() {
        return (this.itemLivro.size());
    }

    @Override
    public int getColumnCount() {
        return (8);
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return (this.itemLivro.get(rowIndex).getLivro().getId());
            case 1:
                return (this.itemLivro.get(rowIndex).getLivro().getISBN());
            case 2:
                return (this.itemLivro.get(rowIndex).getLivro().getNome());
            case 3:
                return (this.itemLivro.get(rowIndex).getLivro().getAutor());
            case 4:
                return (this.itemLivro.get(rowIndex).getLivro().getEditora());
            case 5:
                return (this.itemLivro.get(rowIndex).getLivro().getAno());
            case 6:
                return (this.itemLivro.get(rowIndex).getQuantidadeTotal());
            case 7:
                return (this.itemLivro.get(rowIndex).getQuantidadeDisponivel());
            default:
                return null;
        }
    }

    public void setItemLivro(List<ItemLivro> itemLivro) {
        this.itemLivro = itemLivro;
    }

    public ItemLivro getItemLivro(int linha) {
        return (itemLivro.get(linha));
    }
}
