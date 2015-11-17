/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import biblioteca.Biblioteca;
import java.util.InputMismatchException;
import java.util.List;
import model.Cliente;
import model.Emprestimo;
import model.ItemLivro;
import model.Livro;
import org.joda.time.LocalDate;
import servico.ServicoCliente;
import servico.ServicoEmprestimo;
import servico.ServicoItemLivro;
import util.Console;
import util.Validador;
import view.menu.LivroMenu;

/**
 *
 * @author mzynich
 */
public class LivroUI {

    private ServicoItemLivro servicoItemLivro;
    private ServicoCliente servicoCliente;
    private ServicoEmprestimo servicoEmprestimo;

    public LivroUI() {
        servicoItemLivro = new ServicoItemLivro();
        servicoCliente = new ServicoCliente();
        servicoEmprestimo = new ServicoEmprestimo();
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
                    editarItemLivro();
                    break;
                case LivroMenu.OP_EXCLUIR:
                    excluirLivro();
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
        if (Validador.ISBNValido(ISBN)) {
            if (servicoItemLivro.pesquisaItemLivroISBN(ISBN) != null) {
                System.out.println("Livro já existente no sistema");
            } else {
                String nome = Console.scanString("Nome: ");
                if (Validador.nomeLivroValido(nome)) {
                    String autor = Console.scanString("Autores: ");
                    if (Validador.autorValido(autor)) {
                        String editora = Console.scanString("Editora: ");
                        if (Validador.editoraValida(editora)) {
                            try {
                                int ano = Console.scanInt("Ano: ");
                                if (Validador.anoValido(ano)) {
                                    int quantidade = Console.scanInt("Quantidade disponível: ");
                                    if (Validador.quantidadeValida(quantidade)) {
                                        Livro livro = new Livro(ISBN, nome, autor, editora, ano);
                                        if (servicoItemLivro.addItemLivro(new ItemLivro(livro, quantidade))) {
                                            System.out.println("Livro cadastrado com sucesso");
                                        }
                                    }
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ano e quantidade devem ser números.");
                            }
                        }
                    }
                }
            }
        }
    }

    private void mostrarLivros() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-13s", "ISBN") + "\t"
                + String.format("%-50s", "|NOME") + "\t"
                + String.format("%-50s", "|AUTORES") + "\t"
                + String.format("%-50s", "|EDITORA") + "\t"
                + String.format("%-4s", "|ANO") + "\t"
                + String.format("%-4s", "|QUANTIDADE"));
        for (ItemLivro item : servicoItemLivro.getListaLivros()) {
            System.out.println(String.format("%-13s", item.getLivro().getISBN()) + "\t"
                    + String.format("%-50s", "|" + item.getLivro().getNome()) + "\t"
                    + String.format("%-50s", "|" + item.getLivro().getAutor()) + "\t"
                    + String.format("%-50s", "|" + item.getLivro().getEditora()) + "\t"
                    + String.format("%-4s", "|" + item.getLivro().getAno()) + "\t"
                    + String.format("%-4s", "|" + item.getQuantidadeDisponivel() + "/" + item.getQuantidadeTotal()));
        }
    }

    private void emprestar() {
        String nomeLivro = Console.scanString("Nome do livro: ");
        if (Validador.nomeLivroValido(nomeLivro)) {
            List<ItemLivro> lista = servicoItemLivro.pesquisaItemLivroNome(nomeLivro);
            if (lista.size() <= 0) {
                System.out.println("Livro não existente no sistema");
            } else {
                System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                        + String.format("%-50s", "|NOME") + "\t");
                for (int i = 0; i < lista.size(); i++) {
                    System.out.println(String.format("%-13s", i) + "\t"
                            + String.format("%-30s", "|" + lista.get(i).getLivro().getNome()));
                }
                int opcao = Console.scanInt("Digite o código do livro ou -1 para cancelar: ");
                if (opcao != -1 && opcao < lista.size()) {
                    if (lista.get(opcao).getQuantidadeDisponivel() <= 0) {
                        System.out.println("Todos exemplares deste livro estão emprestados.");
                    } else {
                        String matriculaCliente = Console.scanString("Matricula do cliente: ");
                        if (Validador.matriculaValida(matriculaCliente)) {
                            Cliente cliente = servicoCliente.pesquisaClienteMatricula(matriculaCliente);
                            if (cliente == null) {
                                System.out.println("Cliente não cadastrado no sistema");
                            } else if (servicoCliente.getQtdEmprestimosAtuais(cliente) >= Biblioteca.limiteEmprestimos) {
                                System.out.println("Cliente possui " + Biblioteca.limiteEmprestimos + " livros emprestados.");
                            } else {
                                Emprestimo emprestimo = new Emprestimo(cliente, lista.get(opcao));
                                servicoEmprestimo.addEmprestimo(emprestimo);
                                lista.get(opcao).removerQtdLivroDisponivel(1);
                                servicoItemLivro.editaItemLivro(lista.get(opcao));
                                System.out.println("Empréstimo efetuado com sucesso. Data de devolução: "
                                        + emprestimo.getDataDevolucao().getDayOfMonth() + "/"
                                        + emprestimo.getDataDevolucao().getMonthOfYear() + "/"
                                        + emprestimo.getDataDevolucao().getYear());
                            }
                        }
                    }
                } else {
                    System.out.println("Operação cancelada.");
                }
            }
        }
    }

    private void devolver() {
        String matriculaCliente = Console.scanString("Matrícula do cliente: ");
        if (Validador.matriculaValida(matriculaCliente)) {
            Cliente cliente = servicoCliente.pesquisaClienteMatricula(matriculaCliente);
            if (cliente == null) {
                System.out.println("Cliente não cadastrado no sistema");
            } else {
                String isbnLivro = Console.scanString("ISBN do livro: ");
                if (Validador.ISBNValido(isbnLivro)) {
                    Emprestimo emprestimo = servicoEmprestimo.getEmprestimoClienteISBNLivro(cliente, isbnLivro);
                    if (emprestimo == null) {
                        System.out.println("Cliente não possui este livro emprestado.");
                    } else {
                        emprestimo.setDevolucaoEfetiva(new LocalDate());
                        emprestimo.getItemLivro().adicionarQtdLivroDisponivel(1);
                        servicoItemLivro.editaItemLivro(emprestimo.getItemLivro());
                        emprestimo.setAtivo(false);
                        servicoEmprestimo.editarEmprestimo(emprestimo);
                        if (emprestimo.getDiasAtraso() > 0) {
                            System.out.println("Devolução efetuada com sucesso." + " Dias de atraso: " + emprestimo.getDiasAtraso());
                        } else {
                            System.out.println("Devolução efetuada com sucesso.");
                        }
                    }
                }
            }
        }
    }

    private void editarItemLivro() {
        String ISBN = Console.scanString("ISBN: ");
        if (Validador.ISBNValido(ISBN)) {
            try {
                Livro l = servicoItemLivro.pesquisaItemLivroISBN(ISBN).getLivro();
                if (l != null) {
                    String nome = Console.scanString("Nome: ");
                    if (Validador.nomeLivroValido(nome)) {
                        String autor = Console.scanString("Autores: ");
                        if (Validador.autorValido(autor)) {
                            String editora = Console.scanString("Editora: ");
                            if (Validador.editoraValida(editora)) {
                                int ano = Console.scanInt("Ano: ");
                                if (Validador.anoValido(ano)) {
                                    l.setNome(nome);
                                    l.setAutor(autor);
                                    l.setEditora(editora);
                                    l.setAno(ano);
                                    servicoItemLivro.editaLivro(l);
                                    System.out.println("Alteração efetuada com sucesso.");
                                }
                            }
                        }
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("Livro não encontrado.");
            } catch (InputMismatchException e) {
                System.out.println("Ano inválido.");
            }
        }

    }

    private void excluirLivro() {
        String ISBN = Console.scanString("ISBN: ");
        if (Validador.ISBNValido(ISBN)) {
            try {
                ItemLivro l = servicoItemLivro.pesquisaItemLivroISBN(ISBN);
                if (l != null) {
                    int qtd = l.getQuantidadeTotal() - l.getQuantidadeDisponivel();
                    if (qtd == 0) {
                        servicoItemLivro.excluiItemLivro(l);
                        System.out.println("Livro excluído com sucesso.");
                    } else {
                        System.out.println(qtd + " exemplares estão emprestados. Efetue a devolução deles para habilitar a exclusão de um livro.");
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("Livro não encontrado.");
            } catch (InputMismatchException e) {
                System.out.println("Ano inválido.");
            }
        }
    }
}
