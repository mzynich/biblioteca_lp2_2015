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
 * Classe com operações de itens de livro.
 *
 * @author mzynich
 */
public class ServicoItemLivro {

    private ItemLivroDAOBD itemLivroDAOBD;

    /**
     * Construtor da classe. Inicia a conexão com o banco de dados
     */
    public ServicoItemLivro() {
        itemLivroDAOBD = new ItemLivroDAOBD();
    }

    /**
     * Pesquisa um item livro pelo ISBN
     *
     * @param ISBN ISBN do livro a ser pesquisado
     * @return Objeto itemLivro pesquisado
     */
    public ItemLivro pesquisaItemLivroISBN(String ISBN) {
        return itemLivroDAOBD.procurarPorISBN(ISBN);
    }

    /**
     * Adiciona um item livro na base de dados
     *
     * @param itemLivro Objeto itemLivro a ser inserido
     * @return Resultado da operação
     */
    public boolean addItemLivro(ItemLivro itemLivro) {
        itemLivroDAOBD.inserir(itemLivro);
        return true;
    }

    /**
     * Retorna uma lista com todos os itemLivro do banco de dados.
     *
     * @return Uma lista com todos os itemLivro do banco de dados.
     */
    public List<ItemLivro> getListaLivros() {
        return itemLivroDAOBD.listar();
    }

    /**
     * Retorna uma lista com os livros que contenham a string desejada.
     *
     * @param nomeLivro Nome do livro a ser pesquisado no banco de dados.
     * @return Uma lista com os livros que contenham a string passada por
     * parâmetro.
     */
    public List<ItemLivro> pesquisaItemLivroNome(String nomeLivro) {
        return itemLivroDAOBD.procurarPorNome(nomeLivro);
    }

    /**
     * Edita um itemLivro no banco de dados.
     *
     * @param itemLivro Itemlivro a ser editado.
     */
    public void editaItemLivro(ItemLivro itemLivro) {
        itemLivroDAOBD.atualizar(itemLivro);
    }

    /**
     * Exclui um itemLivro no banco de dados.
     *
     * @param itemLivro ItemLivro a ser excluído
     */
    public void excluiItemLivro(ItemLivro itemLivro) {
        itemLivroDAOBD.deletar(itemLivro);
    }

    /**
     * Edita um livro no banco de dados
     *
     * @param livro Livro a ser editado
     */
    public void editaLivro(Livro livro) {
        itemLivroDAOBD.editarLivro(livro);
    }

    /**
     * Pesquisa um itemLivro pela sua ID
     *
     * @param id ID do itemLivro
     * @return ItemLivro correspondente
     */
    public ItemLivro pesquisaItemLivroID(int id) {
        return itemLivroDAOBD.procurarPorId(id);
    }
}
