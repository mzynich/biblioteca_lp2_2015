/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import biblioteca.Biblioteca;
import java.util.InputMismatchException;
import model.Cliente;
import model.Emprestimo;
import model.ItemLivro;
import model.Livro;
import org.joda.time.DateTime;
import servico.ServicoCliente;
import servico.ServicoItemLivro;
import util.Console;
import view.menu.LivroMenu;

/**
 *
 * @author mzynich
 */
public class LivroUI {

    private ServicoItemLivro servicoItemLivro;
    private ServicoCliente servicoCliente;

    public LivroUI() {
        servicoItemLivro = new ServicoItemLivro();
        servicoCliente = new ServicoCliente();
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(LivroMenu.getOpcoes());
            try {
                opcao = Console.scanInt("Digite sua opção:");
            } catch (InputMismatchException e) {
                opcao = -1; //Caso o usuário digite uma letra, ele força a utilização da cláusula default
            }
            switch (opcao) {
                case LivroMenu.OP_CADASTRAR:
                    cadastrarLivro();
                    break;
                case LivroMenu.OP_LISTAR_TODOS:
                    mostrarLivros();
                    break;
                case LivroMenu.OP_EMPRESTAR:
                    emprestar();
                    break;
                case LivroMenu.OP_DEVOLVER:
                    devolver();
                    break;
                case LivroMenu.OP_EDITAR:
                    editarLivro();
                    break;
                case LivroMenu.OP_EDITAR_QUANTIDADE:
                    editarQuantidade();
                    break;
                case LivroMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != LivroMenu.OP_VOLTAR);
    }

    private void cadastrarLivro() {
        String ISBN = Console.scanString("ISBN: ");
        if (servicoItemLivro.pesquisaItemLivroISBN(ISBN) != null) {
            System.out.println("Livro já existente no sistema");
        } else {
            String nome = Console.scanString("Nome: ");
            String autor = Console.scanString("Autores: ");
            String editora = Console.scanString("Editora: ");
            try {
                int ano = Console.scanInt("Ano: ");
                int quantidade = Console.scanInt("Quantidade disponível: ");
                Livro livro = new Livro(ISBN, nome, autor, editora, ano);
                if (servicoItemLivro.addItemLivro(new ItemLivro(livro, quantidade))) {
                    System.out.println("Livro cadastrado com sucesso");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ano e quantidade devem ser números.");
            }

        }
    }

    public void mostrarLivros() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-13s", "ISBN") + "\t"
                + String.format("%-30s", "|NOME") + "\t"
                + String.format("%-20s", "|AUTORES") + "\t"
                + String.format("%-10s", "|EDITORA") + "\t"
                + String.format("%-4s", "|ANO") + "\t"
                + String.format("%-4s", "|QUANTIDADE"));
        for (ItemLivro item : servicoItemLivro.getListaLivros()) {
            System.out.println(String.format("%-13s", item.getLivro().getISBN()) + "\t"
                    + String.format("%-30s", "|" + item.getLivro().getNome()) + "\t"
                    + String.format("%-20s", "|" + item.getLivro().getAutor()) + "\t"
                    + String.format("%-10s", "|" + item.getLivro().getEditora()) + "\t"
                    + String.format("%-4s", "|" + item.getLivro().getAno()) + "\t"
                    + String.format("%-4s", "|" + item.getQuantidadeDisponivel() + "/" + item.getQuantidadeTotal()));
        }
    }

    public void emprestar() {
        String nomeLivro = Console.scanString("Nome do livro: ");
        ItemLivro i = servicoItemLivro.pesquisaItemLivroNome(nomeLivro);
        if (i == null) {
            System.out.println("Livro não existente no sistema");
        } else if (i.getQuantidadeDisponivel() <= 0) {
            System.out.println("Todos exemplares deste livro estão emprestados.");
        } else {
            String matriculaCliente = Console.scanString("Matricula do cliente: ");
            Cliente cliente = servicoCliente.pesquisaClienteMatricula(matriculaCliente);
            if (cliente == null) {
                System.out.println("Cliente não cadastrado no sistema");
            } else if (servicoCliente.getQtdEmprestimosAtuais(cliente) >= Biblioteca.limiteEmprestimos) {
                System.out.println("Cliente possui " + Biblioteca.limiteEmprestimos + " livros emprestados.");
            } else {
                Emprestimo emprestimo = new Emprestimo(cliente, i);
                servicoItemLivro.addEmprestimo(emprestimo);
                i.removerQtdLivroDisponivel(1);
                System.out.println("Empréstimo efetuado com sucesso. Data de devolução: "
                        + emprestimo.getDataDevolucao().getDayOfMonth() + "/"
                        + emprestimo.getDataDevolucao().getMonthOfYear() + "/"
                        + emprestimo.getDataDevolucao().getYear());
            }
        }
    }

    private void devolver() {
        String matriculaCliente = Console.scanString("Matrícula do cliente: ");
        Cliente cliente = listaCliente.pesquisaClienteMatricula(matriculaCliente);
        if (cliente == null) {
            System.out.println("Cliente não cadastrado no sistema");
        } else {
            String nomeLivro = Console.scanString("Nome do livro: ");
            Emprestimo emprestimo = cliente.getEmprestimoNomeLivro(nomeLivro);
            if (emprestimo == null) {
                System.out.println("Cliente não possui este livro emprestado.");
            } else {
                emprestimo.setDevolucaoEfetiva(new DateTime());
                emprestimo.getItemLivro().adicionarQtdLivroDisponivel(1);
                cliente.removeEmprestimoAtual(emprestimo);
                if (emprestimo.getDiasAtraso() > 0) {
                    System.out.println("Devolução efetuada com sucesso." + " Dias de atraso: " + emprestimo.getDiasAtraso());
                } else {
                    System.out.println("Devolução efetuada com sucesso.");
                }
            }
        }
    }

    private void editarLivro() {
        String ISBN = Console.scanString("ISBN: ");
        try {
            Livro l = listaLivro.pesquisaItemLivroISBN(ISBN).getLivro();
            if (l != null) {
                String nome = Console.scanString("Nome: ");
                String autor = Console.scanString("Autores: ");
                String editora = Console.scanString("Editora: ");
                int ano = Console.scanInt("Ano: ");
                l.setNome(nome);
                l.setAutor(autor);
                l.setEditora(editora);
                l.setAno(ano);
                System.out.println("Alteração efetuada com sucesso.");
            }
        } catch (NullPointerException e) {
            System.out.println("Livro não encontrado.");
        } catch (InputMismatchException e) {
            System.out.println("Ano inválido.");
        }
    }
    
    private void editarQuantidade(){
        String ISBN = Console.scanString("ISBN: ");
        ItemLivro i = listaLivro.pesquisaItemLivroISBN(ISBN);
        if(i!= null){
            try{
                int qtd = Console.scanInt("Quantidade: ");
                if(qtd>0){
                    i.setQuantidade(qtd);
                    System.out.println("Quantidade alterada com sucesso.");
                }
                else{
                    System.out.println("Quantidade deve ser maior que zero.");
                }
            }
            catch(InputMismatchException e){
                System.out.println("A quantidade deve ser um número válido.");
            }
        }
        else{
            System.out.println("Livro não cadastrado.");
        }
    }

}
