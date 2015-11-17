/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.InputMismatchException;
import java.util.List;
import model.Emprestimo;
import model.ItemLivro;
import servico.ServicoEmprestimo;
import servico.ServicoItemLivro;
import util.Console;
import view.menu.ClienteMenu;
import view.menu.RelatorioMenu;

/**
 *
 * @author mzynich
 */
public class RelatorioUI {

    private ServicoItemLivro servicoItemLivro;
    private ServicoEmprestimo servicoEmprestimo;

    public RelatorioUI() {
        servicoItemLivro = new ServicoItemLivro();
        servicoEmprestimo = new ServicoEmprestimo();
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
        List<ItemLivro> listaItemLivro = servicoItemLivro.pesquisaItemLivroNome(nome);
        if (listaItemLivro.size() <= 0) {
            System.out.println("Livro não cadastrado no sistema.");
        } else {
            System.out.println(String.format("%-13s", "ISBN") + "\t"
                    + String.format("%-50s", "|NOME") + "\t"
                    + String.format("%-50s", "|AUTORES") + "\t"
                    + String.format("%-50s", "|EDITORA") + "\t"
                    + String.format("%-4s", "|ANO") + "\t"
                    + String.format("%-4s", "|QUANTIDADE"));
            for (ItemLivro i : listaItemLivro) {
                System.out.println(String.format("%-13s", i.getLivro().getISBN()) + "\t"
                        + String.format("%-50s", "|" + i.getLivro().getNome()) + "\t"
                        + String.format("%-50s", "|" + i.getLivro().getAutor()) + "\t"
                        + String.format("%-50s", "|" + i.getLivro().getEditora()) + "\t"
                        + String.format("%-4s", "|" + i.getLivro().getAno()) + "\t"
                        + String.format("%-4s", "|" + i.getQuantidadeDisponivel() + "/" + i.getQuantidadeTotal()));
            }
        }
    }

    private void livrosDisponiveis() {
        System.out.println(String.format("%-50s", "NOME") + "\t"
                + String.format("%-4s", "|QUANTIDADE"));
        List<ItemLivro> listaLivro = servicoItemLivro.getListaLivros();
        for (ItemLivro itemLivro : listaLivro) {
            if (itemLivro.getQuantidadeDisponivel() > 0) {
                System.out.println(String.format("%-50s", itemLivro.getLivro().getNome()) + "\t"
                        + String.format("%-4s", "|" + itemLivro.getQuantidadeDisponivel() + "/" + itemLivro.getQuantidadeTotal()));
            }
        }
    }

    private void livrosMaisRetirados() {
        servicoEmprestimo.livrosMaisEmprestados();
    }

    private void clientesMaisRetiraramLivro() {
        servicoEmprestimo.clientesMaisRetiraramLivro();
    }

    private void clientesMaisAtrasaram() {
        servicoEmprestimo.clientesMaisAtrasaram();
    }

    private void listarEmprestimos() {
        System.out.println(String.format("%-50s", "Cliente") + "\t"
                + String.format("%-50s", "|Livro") + "\t"
                + String.format("%-10s", "|Data do empréstimo") + "\t"
                + String.format("%-10s", "|Data de devolução planejada") + "\t"
                + String.format("%-10s", "|Devolução efetiva") + "\t"
                + String.format("%-3s", "|Dias atrasados"));
        List<Emprestimo> listaEmprestimo = servicoEmprestimo.getListaEmprestimo();
        for (Emprestimo e : listaEmprestimo) {
            System.out.println(String.format("%-50s", e.getCliente().getNome()) + "\t"
                    + String.format("%-50s", "|" + e.getItemLivro().getLivro().getNome()) + "\t"
                    + String.format("%-18s", "|" + e.getDataEmprestimo().getDayOfMonth() + "/" + e.getDataEmprestimo().getMonthOfYear() + "/" + e.getDataEmprestimo().getYear()) + "\t"
                    + String.format("%-26s", "|" + e.getDataDevolucao().getDayOfMonth() + "/" + e.getDataDevolucao().getMonthOfYear() + "/" + e.getDataDevolucao().getYear()) + "\t"
                    + String.format("%-17s", "|" + e.getDevolucaoEfetiva().getDayOfMonth() + "/" + e.getDevolucaoEfetiva().getMonthOfYear() + "/" + e.getDevolucaoEfetiva().getYear()) + "\t"
                    + String.format("%-3s", "|" + e.getDiasAtraso()));
        }
    }
}
