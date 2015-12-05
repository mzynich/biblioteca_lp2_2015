/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.ItemLivroDAOBD;
import java.util.List;
import model.ItemLivro;
import model.Livro;

/**
 *
 * @author mzynich
 */
public class ServicoItemLivro {

    private ItemLivroDAOBD itemLivroDAOBD;

    public ServicoItemLivro() {
        itemLivroDAOBD = new ItemLivroDAOBD();
    }

    public ItemLivro pesquisaItemLivroISBN(String ISBN) {
        return itemLivroDAOBD.procurarPorISBN(ISBN);
    }

    public boolean addItemLivro(ItemLivro itemLivro) {
        itemLivroDAOBD.inserir(itemLivro);
        return true;
    }

    public List<ItemLivro> getListaLivros() {
        return itemLivroDAOBD.listar();
    }

    public List<ItemLivro> pesquisaItemLivroNome(String nomeLivro) {
        return itemLivroDAOBD.procurarPorNome(nomeLivro);
    }

    public void editaItemLivro(ItemLivro itemLivro) {
        itemLivroDAOBD.atualizar(itemLivro);
    }

    public void excluiItemLivro(ItemLivro l) {
        itemLivroDAOBD.deletar(l);
    }

    public void editaLivro(Livro l) {
        itemLivroDAOBD.editarLivro(l);
    }

    public ItemLivro pesquisaItemLivroID(int id) {
        return itemLivroDAOBD.procurarPorId(id);
    }
}
