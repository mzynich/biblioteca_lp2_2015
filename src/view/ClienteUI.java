/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.InputMismatchException;
import model.Cliente;
import repositorio.RepositorioCliente;
import util.Console;
import view.menu.ClienteMenu;

/**
 *
 * @author mzynich
 */
public class ClienteUI {

    private RepositorioCliente lista;

    public ClienteUI(RepositorioCliente lista) {
        this.lista = lista;
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(ClienteMenu.getOpcoes());
            try {
                opcao = Console.scanInt("Digite sua opção:");
            } catch (InputMismatchException e) {
                opcao = -1; //Caso o usuário digite uma letra, ele força a utilização da cláusula default
            }
            switch (opcao) {
                case ClienteMenu.OP_CADASTRAR:
                    cadastrarCliente();
                    break;
                case ClienteMenu.OP_LISTAR_TODOS:
                    listaClientes();
                    break;
                case ClienteMenu.OP_EDITAR:
                    editarCliente();
                    break;
                case ClienteMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != ClienteMenu.OP_VOLTAR);
    }

    private void cadastrarCliente() {
        String matricula = Console.scanString("Matrícula: ");
        if (lista.pesquisaClienteMatricula(matricula) != null) {
            System.out.println("Cliente já existente no sistema");
        } else {
            String nome = Console.scanString("Nome: ");
            String telefone = Console.scanString("Telefone: ");
            if (lista.addCliente(new Cliente(matricula, nome, telefone))) {
                System.out.println("Cliente cadastrado com sucesso!");
            } else {
                System.out.println("Problema encontrado.");
            }
        }
    }

    private void listaClientes() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-11s", "MATRÍCULA") + "\t"
                + String.format("%-30s", "|NOME") + "\t"
                + String.format("%-12s", "|TELEFONE"));
        for (Cliente cliente : lista.getListaClientes()) {
            System.out.println(String.format("%-11s", cliente.getMatricula()) + "\t"
                    + String.format("%-30s", "|" + cliente.getNome()) + "\t"
                    + String.format("%-12s", "|" + cliente.getTelefone()));
        }

    }
    
    private void editarCliente(){
        String matricula = Console.scanString("Matrícula: ");
        Cliente c = lista.pesquisaClienteMatricula(matricula);
        if(c !=null){
            String nome= Console.scanString("Nome: ");
            String telefone = Console.scanString("Telefone: ");
            c.setNome(nome);
            c.setTelefone(telefone);
            System.out.println("Alteração efetuada com sucesso.");
        }
        else{
            System.out.println("Cliente não encontrado.");
        }
    }
}
