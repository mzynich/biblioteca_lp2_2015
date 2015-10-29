/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import model.Cliente;
import model.Emprestimo;
import model.ItemLivro;
import model.Livro;
import repositorio.RepositorioCliente;
import repositorio.RepositorioEmprestimo;
import repositorio.RepositorioLivro;
import util.Console;
import view.menu.ClienteMenu;
import view.menu.RelatorioMenu;

/**
 *
 * @author mzynich
 */
public class RelatorioUI {

    private RepositorioLivro listaLivro;
    private RepositorioCliente listaCliente;
    private RepositorioEmprestimo listaEmprestimo;

    public RelatorioUI(RepositorioCliente listaCliente, RepositorioLivro listaLivro, RepositorioEmprestimo listaEmprestimo) {
        this.listaCliente = listaCliente;
        this.listaLivro = listaLivro;
        this.listaEmprestimo = listaEmprestimo;
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(RelatorioMenu.getOpcoes());
            try {
                opcao = Console.scanInt("Digite sua opção:");
            } catch (InputMismatchException e) {
                opcao = -1; //Caso o usuário digite uma letra, ele força a utilização da cláusula default
            }
            switch (opcao) {
                case RelatorioMenu.OP_DETALHES_LIVRO:
                    detalhesLivro();
                    break;
                case RelatorioMenu.OP_LIVROS_DISPONIVEIS:
                    livrosDisponiveis();
                    break;
                case RelatorioMenu.OP_LIVROS_MAIS_RETIRADOS:
                    livrosMaisRetirados();
                    break;
                case RelatorioMenu.OP_CLIENTES_RETIRARAM_UM_LIVRO:
                    clientesMaisRetiraramLivro();
                    break;
                case RelatorioMenu.OP_CLIENTES_MAIS_ATRASARAM:
                    clientesMaisAtrasaram();
                    break;
                case RelatorioMenu.OP_LISTAR_EMPRESTIMOS:
                    listarEmprestimos();
                    break;
                case ClienteMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != RelatorioMenu.OP_VOLTAR);
    }

    private void detalhesLivro() {
        String nome = Console.scanString("Digite o nome do livro: ");
        ItemLivro item = listaLivro.pesquisaItemLivroNome(nome);
        Livro livro = item.getLivro();
        if (livro == null) {
            System.out.println("Livro não cadastrado no sistema.");
        } else {
            System.out.println(String.format("%-13s", "ISBN") + "\t"
                    + String.format("%-30s", "|NOME") + "\t"
                    + String.format("%-20s", "|AUTORES") + "\t"
                    + String.format("%-10s", "|EDITORA") + "\t"
                    + String.format("%-4s", "|ANO") + "\t"
                    + String.format("%-4s", "|QUANTIDADE"));
            System.out.println(String.format("%-13s", item.getLivro().getISBN()) + "\t"
                    + String.format("%-30s", "|" + item.getLivro().getNome()) + "\t"
                    + String.format("%-20s", "|" + item.getLivro().getAutor()) + "\t"
                    + String.format("%-10s", "|" + item.getLivro().getEditora()) + "\t"
                    + String.format("%-4s", "|" + item.getLivro().getAno()) + "\t"
                    + String.format("%-4s", "|" + item.getQuantidadeDisponivel() + "/" + item.getQuantidadeTotal()));
        }
    }

    private void livrosDisponiveis() {
        System.out.println(String.format("%-30s", "|NOME") + "\t"
                + String.format("%-4s", "|QUANTIDADE"));
        for (ItemLivro itemLivro : listaLivro.getListaLivros()) {
            if (itemLivro.getQuantidadeDisponivel() > 0) {
                System.out.println(String.format("%-30s", "|" + itemLivro.getLivro().getNome()) + "\t"
                        + String.format("%-4s", "|" + itemLivro.getQuantidadeDisponivel() + "/" + itemLivro.getQuantidadeTotal()));
            }
        }
    }
    
    private void livrosMaisRetirados() {
        ArrayList<ItemLivro> arrayRanking = new ArrayList<>();
        int quantidade = 0;
        for (ItemLivro itemLivro : listaLivro.getListaLivros()) {
            for (Emprestimo emprestimo : listaEmprestimo.getListaEmprestimos()) {
                if (itemLivro.getLivro().equals(emprestimo.getItemLivro().getLivro())) {
                    quantidade++;
                }
            }
            arrayRanking.add(new ItemLivro(itemLivro.getLivro(), quantidade));
            quantidade = 0;
        }
        System.out.println(String.format("%-30s", "|Livro") + "\t"
                + String.format("%-5s", "|Quantidade já emprestada"));
        for (ItemLivro i : arrayRanking) {
            if (i.getQuantidadeTotal() > 0) {
                System.out.println(String.format("%-30s", "|" + i.getLivro().getNome()) + "\t"
                        + String.format("%-5s", "|" + i.getQuantidadeTotal()));
            }
        }
    }

    private void clientesMaisRetiraramLivro() {
        ArrayList<Cliente> arrayRanking = new ArrayList<>();
        ArrayList<Integer> arrayQuantidade = new ArrayList<>();
        int quantidade = 0;
        String nome = Console.scanString("Digite o nome do livro: ");
        ItemLivro item = listaLivro.pesquisaItemLivroNome(nome);
        for (Cliente cliente : listaCliente.getListaClientes()) {
            for (Emprestimo emprestimo : listaEmprestimo.getListaEmprestimos()) {
                if (emprestimo.getItemLivro().equals(item) && emprestimo.getCliente().equals(cliente)) {
                    quantidade++;
                }
            }
            arrayRanking.add(cliente);
            arrayQuantidade.add(quantidade);
            quantidade = 0;
        }
        System.out.println(String.format("%-30s", "|Cliente") + "\t"
                + String.format("%-3s", "|Quantidade de empréstimos do livro: " + item.getLivro().getNome()));
        for (int i = 0; i < arrayRanking.size(); i++) {
            if (arrayQuantidade.get(i) > 0) {
                System.out.println(String.format("%-30s", "|" + arrayRanking.get(i).getNome()) + "\t"
                        + String.format("%-3s", "|" + arrayQuantidade.get(i)));
            }
        }
    }

    private void clientesMaisAtrasaram() {
        ArrayList<Cliente> arrayRanking = new ArrayList<>();
        ArrayList<Integer> arrayQuantidade = new ArrayList<>();
        int quantidade = 0;
        for (Cliente cliente : listaCliente.getListaClientes()) {
            for (Emprestimo emprestimo : listaEmprestimo.getListaEmprestimos()) {
                if (cliente.equals(emprestimo.getCliente()) && emprestimo.getDiasAtraso() > 0) {
                    quantidade++;
                }
            }
            arrayRanking.add(cliente);
            arrayQuantidade.add(quantidade);
            quantidade = 0;
        }
        System.out.println(String.format("%-30s", "|Cliente") + "\t"
                + String.format("%-3s", "|Quantidade de empréstimos atrasados"));
        for (int i = 0; i < arrayRanking.size(); i++) {
            System.out.println(String.format("%-30s", "|" + arrayRanking.get(i).getNome()) + "\t"
                    + String.format("%-3s", "|" + arrayQuantidade.get(i)));
        }
    }

    private void listarEmprestimos() {
        System.out.println(String.format("%-30s", "|Cliente") + "\t"
                + String.format("%-30s", "|Livro") + "\t"
                + String.format("%-10s", "|Data do empréstimo") + "\t"
                + String.format("%-10s", "|Data de devolução planejada") + "\t"
                + String.format("%-10s", "|Devolução efetiva") + "\t"
                + String.format("%-3s", "|Dias atrasados"));
        for (Emprestimo e : listaEmprestimo.getListaEmprestimos()) {
            System.out.println(String.format("%-30s", "|" + e.getCliente().getNome()) + "\t"
                    + String.format("%-30s", "|" + e.getItemLivro().getLivro().getNome()) + "\t"
                    + String.format("%-18s", "|" + e.getDataEmprestimo().getDayOfMonth() + "/" + e.getDataEmprestimo().getMonthOfYear() + "/" + e.getDataEmprestimo().getYear()) + "\t"
                    + String.format("%-26s", "|" + e.getDataDevolucao().getDayOfMonth() + "/" + e.getDataDevolucao().getMonthOfYear() + "/" + e.getDataDevolucao().getYear()) + "\t"
                    + String.format("%-17s", "|" + e.getDevolucaoEfetiva().getDayOfMonth() + "/" + e.getDevolucaoEfetiva().getMonthOfYear() + "/" + e.getDevolucaoEfetiva().getYear()) + "\t"
                    + String.format("%-3s", "|" + e.getDiasAtraso()));
        }
    }
}
